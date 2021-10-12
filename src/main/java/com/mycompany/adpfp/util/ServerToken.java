package com.mycompany.adpfp.util;

import com.mycompany.adpfp.datas.user.Users;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ServerToken  {
    private Users users;
    private String requestType;
    private Date date;
    private String domain;
    private String request;
    private String value;
}

