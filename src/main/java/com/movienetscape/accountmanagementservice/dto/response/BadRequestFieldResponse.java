package com.movienetscape.accountmanagementservice.dto.response;


import com.movienetscape.accountmanagementservice.dto.request.BadRequestField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BadRequestFieldResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private List<BadRequestField> errors;
}
