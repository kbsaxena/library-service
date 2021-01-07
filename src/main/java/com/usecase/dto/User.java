package com.usecase.dto;

import lombok.Data;

@Data
public class User {
    
    private Long userId;
    private String fullName;
    private String address;
    private String city;
    private String state;
}
