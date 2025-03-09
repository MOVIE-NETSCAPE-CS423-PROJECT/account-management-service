package com.movienetscape.usermanagementservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LinkAccountRequest {

    @JsonProperty(required = true)
    private String email;
    @JsonProperty(required = true)
    private String firstname;
    @JsonProperty(required = true)
    private String lastname;
}
