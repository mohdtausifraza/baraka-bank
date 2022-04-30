package com.baraka.bank.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ContactDetails {

    private String emailId;

    private String homePhone;

    private String workPhone;
}
