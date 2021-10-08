package com.mycompany.adpfp.datas.user;

import lombok.*;

@Getter @ToString
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserCridentials {
    private String id;
    private String email;
    private String password;
    private Boolean active;
    private String creator;
    private String userTypeId;
}
