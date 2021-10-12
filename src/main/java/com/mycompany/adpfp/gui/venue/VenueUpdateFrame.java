package com.mycompany.adpfp.gui.venue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VenueUpdateFrame implements ActionListener {
    JFrame updateJFrame = new JFrame();
    private JTextArea jTextUpdateArea = new JTextArea(3,5);
    JPanel userUpdatePanel = new JPanel();
    private JPanel westUpdatePanel = new JPanel();
    private JPanel eastUpdatePanel = new JPanel();
    private JLabel space = new JLabel("");
    private JLabel title = new JLabel("UPDATE USER",JLabel.CENTER);
    private JLabel nameLabel = new JLabel("Name");
    private JLabel idLabel = new JLabel("ID");
    private JLabel locationLabel = new JLabel("Location");
    private JLabel typeLabel = new JLabel("Type");
    private JLabel costLabel = new JLabel("Cost");
    private JLabel maxNumbOfPplLabel = new JLabel("Max Num Of Ppl");
    private JLabel availableLabel = new JLabel("Availability");
    private JLabel dateLabel = new JLabel("Availability");

    private JTextField idJTextField = new JTextField(20);
    private JTextField nameJTextField = new JTextField(20);
    private JTextField locationJTextField = new JTextField(20);
    private JTextField typeJTextField = new JTextField(20);
    private JTextField costJTextField = new JTextField(20);
    private JTextField maxNumOfPplJTextField = new JTextField(20);
    private JTextField availabilityTextField = new JTextField(20);
    private JTextField dateTextField = new JTextField(20);

    private JButton updateUser = new JButton("Update");
    private JButton deleteUser = new JButton("Delete");
    private Color btnBrown = new Color(81,43,40);
    Font f = new Font("Verdana",Font.BOLD,20);

    public VenueUpdateFrame() {
        userUpdatePanel.setLayout(new BorderLayout(5,5));
        updateUser.addActionListener(this);
        jTextUpdateArea.setEditable(false);
        title.setFont(f);
        title.setForeground(btnBrown);
        userUpdatePanel.add(title,BorderLayout.NORTH);
        userUpdatePanel.add(getWestUpdatePanel(),BorderLayout.WEST);
        userUpdatePanel.add(getEastUpdatePanel(),BorderLayout.CENTER);
        userUpdatePanel.add(jTextUpdateArea,BorderLayout.SOUTH);
    }

    public JPanel getUpdateVenue(int rowSelected, JTable mode) {
        setUpdateVenueData(
                mode.getModel().getValueAt(rowSelected,0).toString(),
                mode.getModel().getValueAt(rowSelected,1).toString(),
                mode.getModel().getValueAt(rowSelected,2).toString(),
                mode.getModel().getValueAt(rowSelected,3).toString(),
                mode.getModel().getValueAt(rowSelected,4).toString(),
                mode.getModel().getValueAt(rowSelected,5).toString(),
                mode.getModel().getValueAt(rowSelected,6).toString(),
                mode.getModel().getValueAt(rowSelected,7).toString(),
                mode.getModel().getValueAt(rowSelected,8).toString()
                );
        return userUpdatePanel;
    }
    private void setUpdateVenueData(String id, String name, String location, String cost, String maxNumb, String available, String date, String description, String category){
        idJTextField.setText(id);
        nameJTextField.setText(name);
        locationJTextField.setText(location);
        costJTextField.setText(cost);
        maxNumOfPplJTextField.setText(maxNumb);
        availabilityTextField.setText(available);
        dateTextField.setText(date);
        typeJTextField.setText(category);
        jTextUpdateArea.setText("READY TO UPDATE !!");
    }

    public JPanel getWestUpdatePanel() {
        westUpdatePanel.setLayout(new GridLayout(15,1));
        westUpdatePanel.add(idLabel);
        westUpdatePanel.add(nameLabel);
        westUpdatePanel.add(locationLabel);
        westUpdatePanel.add(costLabel);
        westUpdatePanel.add(maxNumbOfPplLabel);
        westUpdatePanel.add(availableLabel);
        westUpdatePanel.add(dateLabel);
        westUpdatePanel.add(typeLabel);
        return westUpdatePanel;
    }

    public JPanel getEastUpdatePanel() {
        eastUpdatePanel.setLayout(new GridLayout(15,1));
        eastUpdatePanel.add(idJTextField);
        eastUpdatePanel.add(nameJTextField);
        eastUpdatePanel.add(locationJTextField);
        eastUpdatePanel.add(costJTextField);
        eastUpdatePanel.add(maxNumOfPplJTextField);
        eastUpdatePanel.add(availabilityTextField);
        eastUpdatePanel.add(dateTextField);
        eastUpdatePanel.add(typeJTextField);
        eastUpdatePanel.add(space);
        eastUpdatePanel.add(updateUser);
        eastUpdatePanel.add(deleteUser);
        return eastUpdatePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== updateUser){
            System.out.println("about to update");
        }
    }
}
