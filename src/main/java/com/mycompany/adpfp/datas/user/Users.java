package com.mycompany.adpfp.datas.user;



import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Users implements Serializable {
    private String email;
    private String name;
    private String surname;
    private String date;

    public String toString(){
        return "{" +this.email+
                ","+this.name+
                ","+this.surname+
                ","+this.date+
                "}";
    }
}
