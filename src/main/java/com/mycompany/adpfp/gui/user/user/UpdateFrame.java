package com.mycompany.adpfp.gui.user.user;

import com.mycompany.adpfp.datas.customer.Customer;
import com.mycompany.adpfp.datas.factory.UserCredentialFactory;
import com.mycompany.adpfp.datas.user.UserCredentials;
import com.mycompany.adpfp.datas.user.Users;
import com.mycompany.adpfp.io.NewClient;
import com.mycompany.adpfp.io.customer.CustomerIO;
import com.mycompany.adpfp.io.user.UserCredentialIO;
import com.mycompany.adpfp.io.user.UserIO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateFrame implements ActionListener {
    JFrame updateJFrame = new JFrame();
    private JTextArea jTextUpdateArea = new JTextArea(3,5);
    JPanel userUpdatePanel = new JPanel();
    private JPanel westUpdatePanel = new JPanel();
    private JPanel eastUpdatePanel = new JPanel();
    private JLabel space = new JLabel("");
    private JLabel title = new JLabel("UPDATE USER",JLabel.CENTER);
    private JLabel nameUpdateLabel = new JLabel("Name");
    private JLabel emailUpdateLabel = new JLabel("Email");
    private JLabel surnameUpdateLabel = new JLabel("Surname");
    private JLabel idLabel = new JLabel("Id");
    private JLabel dateLabel = new JLabel("Date");

    private JTextField nameJTextUpdateField = new JTextField(20);
    private JTextField emailJTextUpdateField = new JTextField(20);
    private JTextField surnameJTextUpdateField = new JTextField(20);
    private JTextField idField = new JTextField(20);
    private JTextField dateField = new JTextField(20);
    private JButton updateUser = new JButton("Update");
    private JButton deleteUser = new JButton("delete");
    private JButton deactivate = new JButton("Deactivate");
    private JButton activate = new JButton("activate");
    private Color btnBrown = new Color(81,43,40);
    Font f = new Font("Verdana",Font.BOLD,20);


    private UserIO userIO = new UserIO();
    private NewClient newClient;
    private UserCredentialIO userCredentialIO= new UserCredentialIO();
    private UserCredentials userCredentials= null;
    public UpdateFrame(NewClient newClient) {
        this.newClient=newClient;
        userUpdatePanel.setLayout(new BorderLayout(5,5));
        updateUser.addActionListener(this);
        deactivate.addActionListener(this);
        activate.addActionListener(this);
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
    private void setUpdateVenueData(String email, String name, String surname,String date){
        nameJTextUpdateField.setText(name);
        emailJTextUpdateField.setText(email);
        surnameJTextUpdateField.setText(surname);
        dateField.setText(date);

        userCredentials = UserCredentialFactory.getUserCredentialFromToken(userCredentialIO.readUserCredential(this.newClient,email));
        if(userCredentials!=null){
            jTextUpdateArea.setText(userCredentials.toString());
        }else {
            jTextUpdateArea.setText("User credential error");
        }
    }
    void clearCustomerField(){
        nameJTextUpdateField.setText("");
        emailJTextUpdateField.setText("");
        surnameJTextUpdateField.setText("");
        dateField.setText("");
    }
    Users getUser(){
       return Users.builder()
                .name(nameJTextUpdateField.getText())
                .surname(surnameJTextUpdateField.getText())
                .date(dateField.getText())
                .email(emailJTextUpdateField.getText()).build();
    }
    boolean checkIfFilled(){
        if(nameJTextUpdateField.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"Name Missing","Error!",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(emailJTextUpdateField.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"Email Missing","Error!",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(dateField.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"Date Missing","Error!",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(surnameJTextUpdateField.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"Surname Missing","Error!",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public JPanel getWestUpdatePanel() {
        westUpdatePanel.setLayout(new GridLayout(9,1));
        westUpdatePanel.add(emailUpdateLabel);
        westUpdatePanel.add(nameUpdateLabel);
        westUpdatePanel.add(surnameUpdateLabel);
        westUpdatePanel.add(dateLabel);
        return westUpdatePanel;
    }

    public JPanel getEastUpdatePanel() {
        eastUpdatePanel.setLayout(new GridLayout(9,1));
        eastUpdatePanel.add(emailJTextUpdateField);
        eastUpdatePanel.add(nameJTextUpdateField);
        eastUpdatePanel.add(surnameJTextUpdateField);
        eastUpdatePanel.add(dateField);
        eastUpdatePanel.add(space);
        eastUpdatePanel.add(updateUser);
        eastUpdatePanel.add(deleteUser);
        eastUpdatePanel.add(deactivate);
        return eastUpdatePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== updateUser){
            System.out.println("about to update");
            if(checkIfFilled()){
                Users users = getUser();
                System.out.println(users);
                String result = userIO.updateUser(this.newClient,users);
                if(result.equals("true")){
                    jTextUpdateArea.append("Updated successful");
                }
            }
        }
        if(e.getSource()==deleteUser){
            System.out.println("about to delete");
            if(!emailJTextUpdateField.getText().equals("")){
                String result = userIO.deleteUser(this.newClient,emailJTextUpdateField.getText());
                jTextUpdateArea.append(result);
            }else {
                JOptionPane.showMessageDialog(null,"Could not Delete","Error Deleting",JOptionPane.ERROR_MESSAGE);
            }
        }
        if(e.getSource()==deactivate){
            System.out.println("about to deactivate");
            if(!emailJTextUpdateField.getText().equals("")){
                String result = userIO.deactivateUser(this.newClient,emailJTextUpdateField.getText());
                jTextUpdateArea.append(result);
            }else {
                JOptionPane.showMessageDialog(null,"Could not Deactivate","Error Deactivate",JOptionPane.ERROR_MESSAGE);
            }
        }
        if(e.getSource()==activate){
                    System.out.println("about to activate");
                    if(!emailJTextUpdateField.getText().equals("")){
                        String result = userIO.activateUser(this.newClient,emailJTextUpdateField.getText());
                        jTextUpdateArea.append(result);
                    }else {
                        JOptionPane.showMessageDialog(null,"Could not Activate","Error Activate",JOptionPane.ERROR_MESSAGE);
                    }
                }
    }
}
