package com.mycompany.adpfp.gui.user.customer;

import com.mycompany.adpfp.datas.customer.Customer;
import com.mycompany.adpfp.datas.factory.UserCredentialFactory;
import com.mycompany.adpfp.datas.user.UserCredentials;
import com.mycompany.adpfp.io.NewClient;
import com.mycompany.adpfp.io.customer.CustomerIO;
import com.mycompany.adpfp.io.user.UserCredentialIO;

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
    private JLabel title = new JLabel("UPDATE VENUE",JLabel.CENTER);
    private JLabel nameUpdateLabel = new JLabel("Name");
    private JLabel emailUpdateLabel = new JLabel("Email");
    private JLabel surnameUpdateLabel = new JLabel("Surname");
    private JLabel dateLabel = new JLabel("Date");

    private JTextField nameJTextUpdateField = new JTextField(20);
    private JTextField emailJTextUpdateField = new JTextField(20);
    private JTextField surnameJTextUpdateField = new JTextField(20);
    private JTextField dateField = new JTextField(20);
    private JButton updateUser = new JButton("Update");
    private JButton deleteUser = new JButton("Delete");
    private Color btnBrown = new Color(81,43,40);
    Font f = new Font("Verdana",Font.BOLD,20);

    private CustomerIO customerIO = new CustomerIO();
    private UserCredentialIO userCredentialIO= new UserCredentialIO();
    private NewClient newClient;

    public UpdateFrame(NewClient newClient) {
        this.newClient=newClient;
        userUpdatePanel.setLayout(new BorderLayout(5,5));
        updateUser.addActionListener(this);
        deleteUser.addActionListener(this);
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
        dateField.setText(date);
        surnameJTextUpdateField.setText(surname);
        jTextUpdateArea.setText("READY TO UPDATE !!");
    }
    void clearCustomerField(){
        nameJTextUpdateField.setText("");
        emailJTextUpdateField.setText("");
        dateField.setText("");
        surnameJTextUpdateField.setText("");
    }
    Customer getCustomer(){
       return Customer.builder()
                .date(dateField.getText())
                .name(nameJTextUpdateField.getText())
                .surname(surnameJTextUpdateField.getText())
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
        return eastUpdatePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== updateUser){
            System.out.println("about to update");
            if(checkIfFilled()){
                Customer customer = getCustomer();
                System.out.println(customer);
                String result = customerIO.updateCustomer(this.newClient,customer);
                if(result.equals("true")){
                    clearCustomerField();
                }else {
                    JOptionPane.showMessageDialog(null,"Could not update Customer","Update Error!",JOptionPane.ERROR_MESSAGE);
                }
                jTextUpdateArea.setText("Updated successful");
            }else {
                JOptionPane.showMessageDialog(null,"Could not update Customer","Update Error!",JOptionPane.ERROR_MESSAGE);
                jTextUpdateArea.setText("Updated successful");
            }
        }
        if(e.getSource() == deleteUser){
            if(!emailJTextUpdateField.getText().equals("")){
                String result = customerIO.deleteCustomer(this.newClient,emailJTextUpdateField.getText());
                System.out.println("result: "+result);
                if(result.equals("You have successfully deleted customer")){
                    clearCustomerField();
                    jTextUpdateArea.setText("Updated delete successful");
                }
                else {
                    JOptionPane.showMessageDialog(null,"Could not delete Customer","Update Error!",JOptionPane.ERROR_MESSAGE);
                    jTextUpdateArea.setText("Updated failed");
                }
            }
            else {
                JOptionPane.showMessageDialog(null,"Could not delete Customer","Update Error!",JOptionPane.ERROR_MESSAGE);
                jTextUpdateArea.setText("Delete failed");
            }
        }
    }
}
