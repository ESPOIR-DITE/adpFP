package com.mycompany.adpfp.datas.factory;


import com.mycompany.adpfp.datas.user.UserType;

import java.util.StringTokenizer;

public class UserTypeFactory {
    public static UserType getUserTypeFromValue(String value){
        StringTokenizer st = new StringTokenizer(value,"/");
        return UserType.builder()
                .id(st.nextToken())
                .typeName(st.nextToken())
                .description(st.nextToken()) // for now.
                .build();
    }

}
