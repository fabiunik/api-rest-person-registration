package com.springboot.personapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PhoneType {
    
    NOME("Home"),
    MOBILE("Mobile"),
    COMMERCIAL("Commercial");

    private final String description;
}
