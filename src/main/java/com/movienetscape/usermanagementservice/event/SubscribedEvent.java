package com.movienetscape.usermanagementservice.event;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscribedEvent {
    private String subscribedAccountId;
    private String subscribedPlanId;
    private boolean active;
}
