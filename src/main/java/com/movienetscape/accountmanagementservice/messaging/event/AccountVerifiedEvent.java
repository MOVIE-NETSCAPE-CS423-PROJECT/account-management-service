package com.movienetscape.accountmanagementservice.messaging.event;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountVerifiedEvent {

    private String accountId;
    private boolean verified;
}
