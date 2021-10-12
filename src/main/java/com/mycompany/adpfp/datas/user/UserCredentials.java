package com.mycompany.adpfp.datas.user;

import lombok.*;

import java.io.Serializable;

@Getter @ToString
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserCredentials implements Serializable {
    private String id;
    private String email;
    private String password;
    private Boolean active;
    private String creator;
    private String userTypeId;
}
