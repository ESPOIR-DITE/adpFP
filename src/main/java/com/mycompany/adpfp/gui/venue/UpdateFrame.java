package com.mycompany.adpfp.gui.venue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateFrame  implements ActionListener {
    JFrame updateJFrame = new JFrame();
    private JTextArea jTextUpdateArea = new JTextArea(3,5);
    JPanel venueUpdatePanel = new JPanel();
    private JPanel westUpdatePanel = new JPanel();
    private JPanel eastUpdatePanel = new JPanel();
    private JLabel space = new JLabel("");
    private JLabel title = new JLabel("Update Venue");
    private JLabel nameUpdateLabel = new JLabel("Name");
    private JLabel locationUpdateLabel = new JLabel("Location");
    private JLabel typeUpdateLabel = new JLabel("Type");
    private JLabel costUpdateLabel = new JLabel("Cost");
    private JLabel MaxNumbOfPplUpdateLabel = new JLabel("Max Num Of Ppl");

    private JTextField nameJTextUpdateField = new JTextField(20);
    private JTextField locationJTextUpdateField = new JTextField(20);
    private JTextField typeJTextUpdateField = new JTextField(20);
    private JTextField costJTextUpdateField = new JTextField(20);
    private JTextField maxNumOfPplJTextUpdateField = new JTextField(20);
    private JButton updateVenue = new JButton("Update");
    private JButton deleteVenue = new JButton("Delete");

    public UpdateFrame() {
        venueUpdatePanel.setLayout(new BorderLayout(5,5));
        updateVenue.addActionListener(this);
        jTextUpdateArea.setEditable(false);
        venueUpdatePanel.add(title,BorderLayout.NORTH);
        venueUpdatePanel.add(getWestUpdatePanel(),BorderLayout.WEST);
        venueUpdatePanel.add(getEastUpdatePanel(),BorderLayout.CENTER);
        venueUpdatePanel.add(jTextUpdateArea,BorderLayout.SOUTH);
    }

    public JPanel getUpdateVenue(int rowSelected, JTable mode) {
        setUpdateVenueData(
                mode.getModel().getValueAt(rowSelected,0).toString(),
                mode.getModel().getValueAt(rowSelected,1).toString(),
                mode.getModel().getValueAt(rowSelected,2).toString(),
                mode.getModel().getValueAt(rowSelected,3).toString(),
                mode.getModel().getValueAt(rowSelected,4).toString()
                );
        return venueUpdatePanel;
    }
    private void setUpdateVenueData(String name, String location, String cost, String maxNumGuest, String categoryId){
        nameJTextUpdateField.setText(name);
        locationJTextUpdateField.setText(location);
        typeJTextUpdateField.setText(categoryId);
        costJTextUpdateField.setText(cost);
        maxNumOfPplJTextUpdateField.setText(maxNumGuest);
        jTextUpdateArea.setText("date");
    }

    public JPanel getWestUpdatePanel() {
        westUpdatePanel.setLayout(new GridLayout(10,1));
        westUpdatePanel.add(nameUpdateLabel);
        westUpdatePanel.add(locationUpdateLabel);
        westUpdatePanel.add(typeUpdateLabel);
        westUpdatePanel.add(costUpdateLabel);
        westUpdatePanel.add(MaxNumbOfPplUpdateLabel);
        return westUpdatePanel;
    }

    public JPanel getEastUpdatePanel() {
        eastUpdatePanel.setLayout(new GridLayout(10,1));
        eastUpdatePanel.add(nameJTextUpdateField);
        eastUpdatePanel.add(locationJTextUpdateField);
        eastUpdatePanel.add(typeJTextUpdateField);
        eastUpdatePanel.add(costJTextUpdateField);
        eastUpdatePanel.add(maxNumOfPplJTextUpdateField);
        eastUpdatePanel.add(space);
        eastUpdatePanel.add(updateVenue);
        eastUpdatePanel.add(deleteVenue);
        return eastUpdatePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==updateVenue){
            System.out.println("about to update");
        }

    }
}
