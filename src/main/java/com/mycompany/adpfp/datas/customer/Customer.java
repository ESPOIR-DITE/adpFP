package com.mycompany.adpfp.datas.customer;

import lombok.*;

@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Customer {
    private String email;
    private String name;
    private String surname;
    private String date;
}
