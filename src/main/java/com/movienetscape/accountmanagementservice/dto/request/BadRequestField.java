package com.movienetscape.accountmanagementservice.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BadRequestField {
    private String field;
    private String message;
}

