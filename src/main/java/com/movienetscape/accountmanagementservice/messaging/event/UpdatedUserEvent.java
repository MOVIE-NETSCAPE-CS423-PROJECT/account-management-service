package com.movienetscape.accountmanagementservice.messaging.event;

import com.movienetscape.accountmanagementservice.model.Account;
import com.movienetscape.accountmanagementservice.model.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdatedUserEvent {

    private String firstName;
    private String lastName;
    private String userId;
    private String profileImageUrl;
    private String activePlanName;
    private String street;
    private String city;
    private String state;
    private String zip;


    public Account toEntity(Account account) {
        account.setUserId(userId);
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setProfileImageUrl(profileImageUrl);
        account.setAddress(new Address(street, city, state, zip));
        return account;
    }
}



