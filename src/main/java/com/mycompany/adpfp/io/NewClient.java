package com.mycompany.adpfp.io;

import com.mycompany.adpfp.util.ServerToken;
import com.mycompany.adpfp.util.ServerTokenFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NewClient {
    ObjectOutputStream out = null;
    ObjectInputStream in = null;
    private Socket server;

    public NewClient() {
    try{
        server = new Socket("127.0.0.1", 12345);
        out = new ObjectOutputStream(server.getOutputStream());
        out.flush();
        in = new ObjectInputStream(server.getInputStream());
    }
    catch (IOException ioe) {
        System.out.println("IOException: " + ioe.getMessage());
        }
    }
//    public ServerToken communicate(String serverTokenString) {
//        try {
//            // Step 1: create channels
//            ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
//            out.flush();
//            ObjectInputStream in = new ObjectInputStream(server.getInputStream());
//            try {
//                out.writeObject(serverTokenString);
//                out.flush();
//            } catch (IOException ex) {
//                Logger.getLogger(ClientApp.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            String response = (String)in.readObject();
//            System.out.println("From SERVER>> " + response);
//            do{
//                 response = (String)in.readObject();
//                //Processing
//            }while(!response.equals("TERMINATE"));
//
//            // Step 3: close down
//            out.close();
//            in.close();
//            server.close();
//        }catch (IOException ioe)
//        {
//            System.out.println("IO Exception: " + ioe.getMessage());
//        }
//        catch (ClassNotFoundException cnfe)
//        {
//            System.out.println("Class not found: " + cnfe.getMessage());
//        }
//    }
    public String communicate(String serverTokenString) {
        String response = null;
        try {
            try {
                out.writeObject(serverTokenString);
                out.flush();
            } catch (IOException ex) {
                Logger.getLogger(ClientApp.class.getName()).log(Level.SEVERE, null, ex);
            }
            response = (String)in.readObject();
            System.out.println("From SERVER>> " + response);

            // Step 3: close down
        }catch (IOException ioe)
        {
            System.out.println("IO Exception: " + ioe.getMessage());
        }
        catch (ClassNotFoundException cnfe)
        {
            System.out.println("Class not found: " + cnfe.getMessage());
        }
        return response;
    }
    void close() throws IOException {
        out.close();
        in.close();
        server.close();
    }
}
