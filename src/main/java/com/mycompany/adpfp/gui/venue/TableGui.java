package com.mycompany.adpfp.gui.venue;

import com.mycompany.adpfp.datas.user.Users;
import com.mycompany.adpfp.datas.venue.Venue;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.ArrayList;
import java.util.List;

public class TableGui {
    JFrame jFrame = new JFrame();
    JFrame updateFrame = new JFrame();
    JTable table = new JTable();

    static String[][] listToArray(List<Venue> list) {
        int size = list.size();
        String[][] tab2d = new String[size][9];
        List<String> stringList = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            stringList=(setList2(list.get(i)));
            for(int j = 0; j < 9; j++) {
                tab2d[i][j] = stringList.get(j);
            }
        }
        //System.out.println("looping list: "+size);
        return tab2d;
    }

    static List<String> setList2(Venue list){
        List<String> stringList = new ArrayList<>();

        stringList.add(list.getId()+"");
        stringList.add(list.getName());
        stringList.add(list.getLocation());
        stringList.add(list.getCost()+"");
        stringList.add(list.getMaxNumGuest()+"");
        stringList.add(list.isAvailability()+"");
        stringList.add(list.getDate());
        stringList.add(list.getDescription());
        stringList.add(list.getCategoryId());

        return  stringList;
    }
    public JFrame getTableJFrame(List<Venue> usersList){
        jFrame.setTitle("Venue Table");
        String [] columnNames = {"id","Name","Location","Cost","Max numb","Available","Date","Description","Category"};
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
        //UpdateFrame updateJF = new UpdateFrame();
        VenueUpdateFrame venueUpdateFrame = new VenueUpdateFrame();
        //updateFrame.add(updateJF.getUpdateVenue(rowSelected,mode));
        updateFrame.add(venueUpdateFrame.getUpdateVenue(rowSelected,mode));
        updateFrame.setLocationRelativeTo(null);
        updateFrame.setVisible(true);
        updateFrame.setSize(550,400);
    }
}
