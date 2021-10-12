package com.mycompany.adpfp.gui.user;

import com.mycompany.adpfp.datas.user.Users;
import com.mycompany.adpfp.gui.venue.UpdateFrame;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.ArrayList;
import java.util.List;

public class TableGui {
    JFrame jFrame = new JFrame();
    JFrame updateFrame = new JFrame();
    JTable table = new JTable();

    static String[][] listToArray(List<Users> list) {
        int size = list.size();
        String[][] tab2d = new String[size][4];
        List<String> stringList = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            stringList=(setList2(list.get(i)));
            for(int j = 0; j < 4; j++) {
                tab2d[i][j] = stringList.get(j);
            }
        }
        //System.out.println("looping list: "+size);
        return tab2d;
    }

    static List<String> setList2(Users list){
        List<String> stringList = new ArrayList<>();

        stringList.add(list.getEmail());
        stringList.add(list.getName());
        stringList.add(list.getSurname());
        stringList.add(list.getDate().toString());

        return  stringList;
    }
    public JFrame getTableJFrame(List<Users> usersList){
        jFrame.setTitle("User Table");
        String [] columnNames = {"Email","Name","Surname","Date"};
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
        UpdateFrame updateJF = new UpdateFrame();
        updateFrame.add(updateJF.getUpdateVenue(rowSelected,mode));
        updateFrame.setLocationRelativeTo(null);
        updateFrame.setVisible(true);
        updateFrame.setSize(550,400);
    }
}
