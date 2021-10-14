package com.mycompany.adpfp.gui.user.customer;

import com.mycompany.adpfp.datas.customer.Customer;
import com.mycompany.adpfp.gui.user.customer.UpdateFrame;
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

    static String[][] listToArray(List<Customer> list) {
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

    static List<String> setList2(Customer list){
        List<String> stringList = new ArrayList<>();

        stringList.add(list.getEmail());
        stringList.add(list.getName());
        stringList.add(list.getSurname());
        stringList.add(list.getDate());
        return  stringList;
    }
    public JFrame getTableJFrame(NewClient newClient,List<Customer> usersList){
        jFrame.setTitle("User Table");
        this.newClient = newClient;
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
        UpdateFrame updateJF = new UpdateFrame(this.newClient);
        updateFrame.add(updateJF.getUpdateVenue(rowSelected,mode));
        updateFrame.setLocationRelativeTo(null);
        updateFrame.setVisible(true);
        updateFrame.setSize(550,500);
    }
}
