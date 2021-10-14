package com.mycompany.adpfp.datas.factory.booking;

import com.mycompany.adpfp.datas.Booking;
import com.mycompany.adpfp.datas.customer.Customer;
import com.mycompany.adpfp.util.ServerToken;
import com.mycompany.adpfp.util.ServerTokenFactory;

import java.util.Date;
import java.util.StringTokenizer;

public class BookingFactory {
    public static String updateNewBooking(Booking booking){
        return   new ServerTokenFactory().getJson(ServerToken.builder()
                .request("update")
                .date(new Date())
                .domain("booking")
                .value(getBookingFromObject(booking))
                .build());
    }
    public static String deleteBooking(String id){
        return   new ServerTokenFactory().getJson(ServerToken.builder()
                .request("delete")
                .date(new Date())
                .domain("booking")
                .value(id)
                .build());
    }
    public static String createNewBooking(Booking customer){
        return   new ServerTokenFactory().getJson(ServerToken.builder()
                .request("create")
                .date(new Date())
                .domain("booking")
                .value(getBookingFromObject(customer))
                .build());
    }
    public static String getBookingFromObject(Booking booking){
        return booking.getId()+"/"+booking.getCustomerEmail()+"/"+booking.getUserEmail()+"/"+booking.getVenueId()+"/"+booking.getDate()+"/"+booking.getDescription();
    }
    public static Booking getBookingFromValue(String value){
        StringTokenizer st = new StringTokenizer(value,"/");
        return Booking.builder()
                .id(Integer.parseInt(st.nextToken()))
                .customerEmail(st.nextToken())
                .userEmail(st.nextToken())
                .venueId(st.nextToken())
                .date(st.nextToken())
                .description(st.nextToken())
                .build();
    }
    public static String readAllBooking(){
        return   new ServerTokenFactory().getJson(ServerToken.builder()
                .request("reads")
                .date(new Date())
                .domain("booking")
                .build());
    }
}
