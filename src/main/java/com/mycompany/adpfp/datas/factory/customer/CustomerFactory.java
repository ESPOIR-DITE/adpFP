package com.mycompany.adpfp.datas.factory.customer;

import com.mycompany.adpfp.datas.customer.Customer;
import com.mycompany.adpfp.util.ServerToken;
import com.mycompany.adpfp.util.ServerTokenFactory;

import java.util.Date;
import java.util.StringTokenizer;

public class CustomerFactory {
    public static String updateNewCustomer(Customer customer){
        return   new ServerTokenFactory().getJson(ServerToken.builder()
                .request("update")
                .date(new Date())
                .domain("customer")
                .value(getCustomerFromObject(customer))
                .build());
    }
    public static String deleteCustomer(String id){
        return   new ServerTokenFactory().getJson(ServerToken.builder()
                .request("delete")
                .date(new Date())
                .domain("customer")
                .value(id)
                .build());
    }
    public static String createNewCustomer(Customer customer){
        return   new ServerTokenFactory().getJson(ServerToken.builder()
                .request("create")
                .date(new Date())
                .domain("customer")
                .value(getCustomerFromObject(customer))
                .build());
    }
    public static String getCustomerFromObject(Customer customer){
        return customer.getEmail()+"/"+customer.getName()+"/"+customer.getSurname()+"/"+customer.getDate();
    }
    public static Customer getCustomerFromValue(String value){
        StringTokenizer st = new StringTokenizer(value,"/");
        return Customer.builder()
                .email(st.nextToken())
                .name(st.nextToken())
                .surname(st.nextToken())
                .date(st.nextToken())
                .build();
    }
    public static String readAllCustomer(){
        return   new ServerTokenFactory().getJson(ServerToken.builder()
                .request("reads")
                .date(new Date())
                .domain("customer")
                .build());
    }
}
