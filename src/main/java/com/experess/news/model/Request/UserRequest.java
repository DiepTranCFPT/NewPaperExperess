package com.experess.news.model.Request;

import com.experess.news.infor.Gender;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class UserRequest {

    @NotNull
    private String id;
    @NotNull
    private String name;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Invalid phone number format")
    @Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 digits")
    private String phone;

    private Gender isGender; // true if MALE ,f= FEMALE

    @NotNull
    private byte[] avata;

    private String address;

    private String describe;


}
