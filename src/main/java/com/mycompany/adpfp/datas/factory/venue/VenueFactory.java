package com.mycompany.adpfp.datas.factory.venue;

import com.mycompany.adpfp.datas.user.Users;
import com.mycompany.adpfp.datas.venue.Venue;
import com.mycompany.adpfp.util.ServerToken;
import com.mycompany.adpfp.util.ServerTokenFactory;

import java.util.Date;
import java.util.StringTokenizer;

public class VenueFactory {
    public static String createNewVenue(Venue venue){
        return   new ServerTokenFactory().getJson(ServerToken.builder()
                .request("create")
                .date(new Date())
                .domain("venue")
                .value(getVenueFromObject(venue))
                .build());
    }
    public static String getVenueFromObject(Venue venue){
        return venue.getId()+"/"+ venue.getName()+"/"+ venue.getLocation()+"/"+ venue.getCost()+"/"+ venue.getMaxNumGuest()+"/"+ venue.isAvailability()+"/"+ venue.getDate()+"/"+ venue.getDescription()+"/"+ venue.getCategoryId();
    }
    public static Venue getVenueFromValue(String value){
        StringTokenizer st = new StringTokenizer(value,"/");
        return Venue.builder()
                .name(st.nextToken())
                .location(st.nextToken())
                .cost(Double.parseDouble(st.nextToken())) // for now.
                .maxNumGuest(Integer.parseInt(st.nextToken())) // for now.
                .date(st.nextToken())
                .description(st.nextToken())
                .categoryId(st.nextToken())
                .build();
    }
    public static String readAllVenues(){
        return   new ServerTokenFactory().getJson(ServerToken.builder()
                .request("reads")
                .date(new Date())
                .domain("venue")
                .build());
    }
}
