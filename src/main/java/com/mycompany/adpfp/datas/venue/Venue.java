package com.mycompany.adpfp.datas.venue;

import lombok.*;

import java.lang.reflect.Field;
import java.time.LocalDate;


@Getter
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Venue {
    private int id;
    private String name;
    private String location;
    private double cost;
    private int maxNumGuest;
    private boolean availability;
    private String date;
    private String description;
    private String categoryId;

}
