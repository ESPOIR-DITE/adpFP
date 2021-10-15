package com.mycompany.adpfp.datas.factory.venue;

import com.mycompany.adpfp.datas.user.Users;
import com.mycompany.adpfp.datas.venue.Venue;
import com.mycompany.adpfp.util.ServerToken;
import com.mycompany.adpfp.util.ServerTokenFactory;

import java.util.Date;
import java.util.StringTokenizer;

public class VenueFactory {
    public static String updateSetUnavailableVenue(String venueId){
        return   new ServerTokenFactory().getJson(ServerToken.builder()
                .request("update-unavailable")
                .date(new Date())
                .domain("venue")
                .value(venueId)
                .build());
    }
    public static String updateSetAvailableVenue(String venueId){
        return   new ServerTokenFactory().getJson(ServerToken.builder()
                .request("update-available")
                .date(new Date())
                .domain("venue")
                .value(venueId)
                .build());
    }
    public static String updateVenue(Venue venue){
        return   new ServerTokenFactory().getJson(ServerToken.builder()
                .request("update")
                .date(new Date())
                .domain("venue")
                .value(getVenueFromObject(venue))
                .build());
    }
    public static String deleteVenue(String id){
        return   new ServerTokenFactory().getJson(ServerToken.builder()
                .request("delete")
                .date(new Date())
                .domain("venue")
                .value(id)
                .build());
    }
    public static String createNewVenue(Venue venue){
        return   new ServerTokenFactory().getJson(ServerToken.builder()
                .request("update")
                .date(new Date())
                .domain("venue")
                .value(getVenueFromObjectForCreation(venue))
                .build());
    }
    public static String getVenueFromObjectForCreation(Venue venue){
        return venue.getName()+"/"+ venue.getLocation()+"/"+ venue.getCost()+"/"+ venue.getMaxNumGuest()+"/"+ venue.isAvailability()+"/"+ venue.getDate()+"/"+ venue.getDescription()+"/"+ venue.getCategoryId();
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
    public static String readAllAvailableVenues(){
        return   new ServerTokenFactory().getJson(ServerToken.builder()
                .request("read-available")
                .date(new Date())
                .domain("venue")
                .build());
    }
}
