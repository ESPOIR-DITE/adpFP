package com.mycompany.adpfp.io.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.adpfp.datas.factory.UserCredentialFactory;
import com.mycompany.adpfp.datas.factory.UserFactory;
import com.mycompany.adpfp.datas.user.UserCredentials;
import com.mycompany.adpfp.datas.user.Users;
import com.mycompany.adpfp.io.NewClient;

import java.util.ArrayList;
import java.util.List;

public class UserCredentialIO {
    public String readUserCredential(NewClient client, String email){
        String userCredentialString = UserCredentialFactory.readUserCredential(email);
        String result = client.communicate(userCredentialString);
        return result;
    }

}
