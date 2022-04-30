package com.baraka.bank.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class AddressDetails {

    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String country;

}
