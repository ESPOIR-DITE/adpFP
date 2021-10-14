package com.mycompany.adpfp.datas;

import lombok.*;

import java.time.LocalDate;

@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Booking {
    private int id;
    private String customerEmail;
    private String userEmail;
    private String venueId;
    private String date;
    private String description;
}
