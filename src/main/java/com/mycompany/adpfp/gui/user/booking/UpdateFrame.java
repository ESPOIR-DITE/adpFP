package com.mycompany.adpfp.gui.user.booking;

import com.mycompany.adpfp.datas.Booking;
import com.mycompany.adpfp.datas.customer.Customer;
import com.mycompany.adpfp.io.NewClient;
import com.mycompany.adpfp.io.booking.BookingIO;
import com.mycompany.adpfp.io.customer.CustomerIO;

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
    private JLabel customerEmailLabel = new JLabel("Customer Email");
    private JLabel userEmailLabel = new JLabel("User Email");
    private JLabel venueIdLabel = new JLabel("Venue Id");
    private JLabel dateLabel = new JLabel("Date");
    private JLabel descriptionLabel = new JLabel("Description");
    private JLabel idLabel = new JLabel("Id");

    private JTextField customerEmailField = new JTextField(20);
    private JTextField userEmailField = new JTextField(20);
    private JTextField venueIdField = new JTextField(20);
    private JTextField dateField = new JTextField(20);
    private JTextField descriptionField = new JTextField(20);
    private JTextField idField = new JTextField(20);
    private JButton updateUser = new JButton("Update");
    private JButton deleteUser = new JButton("Delete");
    private Color btnBrown = new Color(81,43,40);
    Font f = new Font("Verdana",Font.BOLD,20);

    private BookingIO bookingIO =new BookingIO();
    private NewClient newClient;
    public UpdateFrame(NewClient newClient) {
        this.newClient=newClient;
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
                mode.getModel().getValueAt(rowSelected,4).toString()
                );
        return userUpdatePanel;
    }
    private void setUpdateVenueData(String id, String customerEmail, String venueId, String date, String description){
        customerEmailField.setText(customerEmail);
        venueIdField.setText(venueId);
        dateField.setText(date);
        descriptionField.setText(description);
        idField.setText(id);
        jTextUpdateArea.setText("READY TO UPDATE !!");
    }
    void clearCustomerField(){
        customerEmailField.setText("");
        userEmailField.setText("");
        idField.setText("");
        venueIdField.setText("");
        descriptionField.setText("");
        dateField.setText("");
    }
    Booking getBooking(){
       return Booking.builder()
                .id(Integer.parseInt(idField.getText()))
                .customerEmail(customerEmailField.getText())
                .date(dateField.getText())
                .venueId(venueIdField.getText())
                .description(descriptionField.getText())
                .userEmail(userEmailField.getText()).build();
    }
    boolean checkIfField(){
        if(customerEmailField.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"Customer Missing","Error!",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(idField.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"Id Missing","Error!",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(dateField.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"Date Missing","Error!",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(descriptionField.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"Period Missing","Error!",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(venueIdField.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"Venue Missing","Error!",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public JPanel getWestUpdatePanel() {
        westUpdatePanel.setLayout(new GridLayout(15,1));
        westUpdatePanel.add(idLabel);
        westUpdatePanel.add(customerEmailLabel);
        westUpdatePanel.add(venueIdLabel);
        westUpdatePanel.add(dateLabel);
        westUpdatePanel.add(descriptionLabel);
        return westUpdatePanel;
    }

    public JPanel getEastUpdatePanel() {
        eastUpdatePanel.setLayout(new GridLayout(15,1));
        eastUpdatePanel.add(idField);
        eastUpdatePanel.add(customerEmailField);
        eastUpdatePanel.add(venueIdField);
        eastUpdatePanel.add(dateField);
        eastUpdatePanel.add(descriptionField);
        eastUpdatePanel.add(space);
        eastUpdatePanel.add(updateUser);
        eastUpdatePanel.add(deleteUser);
        return eastUpdatePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== updateUser){
            System.out.println("about to update");
            if(checkIfField()){
                Booking booking = getBooking();
                clearCustomerField();
                String result = bookingIO.updateCustomer(this.newClient,booking);
                if(result.equals("true")){
                    jTextUpdateArea.append(result);
                }
            }
        }

    }
}
