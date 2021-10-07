package com.mycompany.adpfp.util;

import java.io.File;
import java.nio.file.Paths;

public class Images {
    public static File getAdminImage(){
        return new File(Paths.get("").toAbsolutePath().toString()+"\\src\\main\\java\\com\\mycompany\\adpfp\\util\\Event-Venue3.jpg");
    }
    public static File getUserImage(){
        return new File(Paths.get("").toAbsolutePath().toString()+"\\src\\main\\java\\com\\mycompany\\adpfp\\util\\User.jpg");
    }
}
