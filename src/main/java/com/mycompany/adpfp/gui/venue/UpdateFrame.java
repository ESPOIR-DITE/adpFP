package com.mycompany.adpfp.gui.venue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateFrame  implements ActionListener {
    JFrame updateJFrame = new JFrame();
    private JTextArea jTextUpdateArea = new JTextArea(3,5);
    JPanel userUpdatePanel = new JPanel();
    private JPanel westUpdatePanel = new JPanel();
    private JPanel eastUpdatePanel = new JPanel();
    private JLabel space = new JLabel("");
    private JLabel title = new JLabel("UPDATE VENUE",JLabel.CENTER);
    private JLabel nameUpdateLabel = new JLabel("Name");
    private JLabel emailUpdateLabel = new JLabel("Email");
    private JLabel surnameUpdateLabel = new JLabel("Surname");
    private JLabel dateUpdateLabel = new JLabel("Date");

    private JTextField nameJTextUpdateField = new JTextField(20);
    private JTextField emailJTextUpdateField = new JTextField(20);
    private JTextField surnameJTextUpdateField = new JTextField(20);
    private JTextField dateJTextUpdateField = new JTextField(20);
    private JButton updateUser = new JButton("Update");
    private JButton deleteUser = new JButton("Delete");
    private Color btnBrown = new Color(81,43,40);
    Font f = new Font("Verdana",Font.BOLD,20);

    public UpdateFrame() {
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
                mode.getModel().getValueAt(rowSelected,3).toString()
                );
        return userUpdatePanel;
    }
    private void setUpdateVenueData(String email, String name, String surname, String date){
        nameJTextUpdateField.setText(name);
        emailJTextUpdateField.setText(email);
        dateJTextUpdateField.setText(date);
        surnameJTextUpdateField.setText(surname);
        jTextUpdateArea.setText("READY TO UPDATE !!");
    }

    public JPanel getWestUpdatePanel() {
        westUpdatePanel.setLayout(new GridLayout(9,1));
        westUpdatePanel.add(nameUpdateLabel);
        westUpdatePanel.add(emailUpdateLabel);
        westUpdatePanel.add(surnameUpdateLabel);
        westUpdatePanel.add(dateUpdateLabel);
        return westUpdatePanel;
    }

    public JPanel getEastUpdatePanel() {
        eastUpdatePanel.setLayout(new GridLayout(9,1));
        eastUpdatePanel.add(nameJTextUpdateField);
        eastUpdatePanel.add(emailJTextUpdateField);
        eastUpdatePanel.add(surnameJTextUpdateField);
        eastUpdatePanel.add(dateJTextUpdateField);
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
