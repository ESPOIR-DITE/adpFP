package com.mycompany.adpfp.datas.user;

import lombok.*;

@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserType {
    private String id;
    private String typeName;
    private String description;
}
