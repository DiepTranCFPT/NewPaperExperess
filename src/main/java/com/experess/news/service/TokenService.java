package com.experess.news.service;


import com.experess.news.entity.RevokedToken;
import com.experess.news.entity.User;
import com.experess.news.exception.AuthException;

import com.experess.news.repository.AuthenticationRepository;
import com.experess.news.repository.RevokedTokenRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService {

    @Autowired
    private RevokedTokenRepository revokedTokenRepository;

    private final String SECRET_KEY = "HT4bb6d1dfbafb64a681139d1586b6f1160d18159afd57c8c79136d7490630407c";
    private final long ACCESS_TOKEN_EXPIRATION = (long) 1 * 24 * 60 * 60 * 1000; // 24 giờ
    private final long REFRESH_TOKEN_EXPIRATION = (long) 30 * 24 * 60 * 60 * 1000; // 30 ngày

    private final AuthenticationRepository authenticationRepository;

    public TokenService(AuthenticationRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
    }

    // Phương thức tạo Access Token và Refresh Token
    public Map<String, String> generateTokens(User user) {
        String accessToken = generateAccessToken(user);
        String refreshToken = generateRefreshToken(user);

        // Lưu refreshToken vào database
        user.setRefreshToken(refreshToken);
        authenticationRepository.save(user);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);

        return tokens;
    }

    private String generateAccessToken(User user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getEmail())
                .issueTime(new Date(System.currentTimeMillis()))
                .expirationTime(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION))
                .claim("scope", "ROLE_" + user.getRole()) // Assuming User has a role field
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SECRET_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateRefreshToken(User user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(user.getEmail())
                .issueTime(new Date(System.currentTimeMillis()))
                .expirationTime(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION))
                .build();

        JWSObject jwsObject = new JWSObject(header, new Payload(claimsSet.toJSONObject()));

        try {
            jwsObject.sign(new MACSigner(SECRET_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    // Phương thức làm mới token
    public Map<String, String> refreshTokens(String refreshToken) throws ParseException, JOSEException {
        SignedJWT signedJWT = SignedJWT.parse(refreshToken);
        JWSVerifier verifier = new MACVerifier(Base64.getDecoder().decode(SECRET_KEY));

        if (signedJWT.verify(verifier) && !isTokenExpired(signedJWT.getJWTClaimsSet().getExpirationTime())) {
            String email = signedJWT.getJWTClaimsSet().getSubject();
            User user = authenticationRepository.findByEmail(email)
                    .orElseThrow(() -> new AuthException("User not found"));

            if (user.getRefreshToken().equals(refreshToken)) {
                // Tạo mới access token và refresh token
                String newAccessToken = generateAccessToken(user);
                String newRefreshToken = generateRefreshToken(user);

                // Cập nhật refresh token mới vào cơ sở dữ liệu
                user.setRefreshToken(newRefreshToken);
                authenticationRepository.save(user);

                // Trả về cả hai token mới
                Map<String, String> tokens = new HashMap<>();
                tokens.put("accessToken", newAccessToken);
                tokens.put("refreshToken", newRefreshToken);

                return tokens;
            } else {
                throw new AuthException("Invalid refresh token");
            }
        } else {
            throw new AuthException("Refresh token expired or invalid");
        }
    }

    // Hủy token
    public void revokeToken(String token) throws ParseException, JOSEException {
        SignedJWT signedJWT = SignedJWT.parse(token);
        String tokenId = signedJWT.getJWTClaimsSet().getJWTID();
        Date expirationDate = signedJWT.getJWTClaimsSet().getExpirationTime();


        // Convert Date datatype by LocalTime format

        LocalTime localTime = Instant.ofEpochMilli(expirationDate.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalTime();


        RevokedToken revokedToken = new RevokedToken();
        revokedToken.setId(tokenId);
        revokedToken.setTimeUpdatedLast(localTime);

        revokedTokenRepository.save(revokedToken);
    }

    // Kiểm tra token có bị hủy không trước khi xác thực
    public boolean isTokenRevoked(String token) throws ParseException {
        SignedJWT signedJWT = SignedJWT.parse(token);
        String tokenId = signedJWT.getJWTClaimsSet().getJWTID();

        return revokedTokenRepository.existsByTokenId(tokenId);
    }

    private boolean isTokenExpired(Date expirationDate) {
        return expirationDate.before(new Date());
    }
}
