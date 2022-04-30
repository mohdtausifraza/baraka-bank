package com.baraka.bank.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class CustomerDetails {

    private String firstName;
    
    private String lastName;
    
    private String middleName;

    private Long customerNumber;

    private String status;
    
    private AddressDetails customerAddress;
    
    private ContactDetails contactDetails;
    
}
