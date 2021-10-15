package com.mycompany.adpfp.gui.user.booking;

import com.mycompany.adpfp.datas.Booking;
import com.mycompany.adpfp.io.NewClient;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.ArrayList;
import java.util.List;

public class TableGui {
    JFrame jFrame = new JFrame();
    JFrame updateFrame = new JFrame();
    JTable table = new JTable();
    private NewClient newClient;
    JPanel userVenue;
    String userEmail;
    private List<Booking> bookings = new ArrayList<>();

    static String[][] listToArray(List<Booking> list) {
        int size = list.size();
        String[][] tab2d = new String[size][6];
        List<String> stringList = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            stringList=(setList2(list.get(i)));
            for(int j = 0; j < 6; j++) {
                tab2d[i][j] = stringList.get(j);
            }
        }
        return tab2d;
    }

    static List<String> setList2(Booking list){
        List<String> stringList = new ArrayList<>();

        stringList.add(list.getId()+"");
        stringList.add(list.getCustomerEmail());
        stringList.add(list.getUserEmail());
        stringList.add(list.getVenueId());
        stringList.add(list.getDate());
        stringList.add(list.getDescription());
        return  stringList;
    }
    public JFrame getTableJFrame(JPanel userVenue,String userEmail,NewClient newClient,List<Booking> usersList){
        this.bookings = usersList;
        this.userVenue = userVenue;
        this.userEmail = userEmail;
        jFrame.setTitle("Booking Table");
        this.newClient = newClient;
        System.out.println("booking List: "+usersList);
        String [] columnNames = {"ID","CUSTOMER EMAIL","User","VENUE ID","DATE","DESCRIPTION"};
        table = new JTable(listToArray(usersList),columnNames);
        ListSelectionModel listSelectionModel = table.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = table.getSelectedRow();
                getUpdateJFrameGui(row,table);
            }
        });
        int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
        int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
        table.setBounds(30,40,400,400);
        JScrollPane sp = new JScrollPane(table, v, h);
        jFrame.add(sp);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
        jFrame.setSize(500,200);
        return jFrame;
    }
    void getUpdateJFrameGui(int rowSelected, JTable mode){
        UpdateFrame updateJF = new UpdateFrame(this.newClient);
        updateJF.setUpFrame(this.userVenue,this.userEmail,rowSelected,mode,this.bookings);
        jFrame.dispose();
    }

//    void getUpdateJFrameGui(int rowSelected, JTable mode){
//        UpdateFrame updateJF = new UpdateFrame(this.newClient);
//        updateFrame.add(updateJF.getUpdateVenue(rowSelected,mode));
//        updateFrame.setLocationRelativeTo(null);
//        updateFrame.setVisible(true);
//        updateFrame.setSize(550,400);
//    }
}
