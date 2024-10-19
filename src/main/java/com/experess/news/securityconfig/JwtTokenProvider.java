//package com.experess.news.securityconfig;
//
//import com.experess.news.entity.CustomUserDetails;
//import com.nimbusds.jose.*;
//import com.nimbusds.jose.crypto.MACSigner;
//import com.nimbusds.jwt.JWTClaimsSet;
//import org.springframework.stereotype.Component;
//
//import java.time.Instant;
//import java.time.temporal.ChronoUnit;
//import java.util.Date;
//
//@Component
//public class JwtTokenProvider {
//
//    public final String KEY = "R6BqqCiObGvvO2wYzq3UrBByfaQ7RTKxDi6aXevPsnLowHWR6oRsxcKkSD8I1s2JR6BqqCiObGvvO2wYzq3UrBByfaQ7RTKx";
//
//    public String generateToken(CustomUserDetails userDetails){
//        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
//
//        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
//                .subject(userDetails.getUser().getEmail())
//                .issuer("your-issuer")
//                .issueTime(new Date())
//                .expirationTime(Date.from(Instant.now().plus(1, ChronoUnit.HOURS)))
//                .claim("password", userDetails.getUser().getPassword())
//                .claim("token", userDetails)
//                .build();
//
//        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
//        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
//
//        try {
//            jwsObject.sign(new MACSigner(KEY.getBytes()));
//            return jwsObject.serialize();
//        } catch (JOSEException e) {
//            throw new RuntimeException("Error signing the JWT token", e);
//        }
//    }
//}
