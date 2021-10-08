package com.mycompany.adpfp.datas.user;



import lombok.*;

import java.time.LocalDate;

@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Users {
    private String email;
    private String name;
    private String surname;
    private LocalDate date;

}
