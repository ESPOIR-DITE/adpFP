package com.mycompany.adpfp.gui.venue;

import com.mycompany.adpfp.datas.venue.Venue;
import com.mycompany.adpfp.io.NewClient;
import com.mycompany.adpfp.io.venue.VenueIO;

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
    private NewClient newClient;
    private VenueIO venueIO = new VenueIO();

    public VenueUpdateFrame(NewClient newClient) {
        userUpdatePanel.setLayout(new BorderLayout(5,5));
        updateUser.addActionListener(this);
        deleteUser.addActionListener(this);
        jTextUpdateArea.setEditable(false);
        title.setFont(f);
        title.setForeground(btnBrown);
        this.newClient = newClient;
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

    boolean checkIfEmpty(){
        if(idJTextField.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Id Missing","MISSING ERROR",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(nameJTextField.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Name Missing","MISSING ERROR",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(locationJTextField.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Location Missing","MISSING ERROR",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(costJTextField.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Cost Missing","MISSING ERROR",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(maxNumOfPplJTextField.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Number of Guest Missing","MISSING ERROR",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(availabilityTextField.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Availability Missing","MISSING ERROR",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(dateTextField.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Date Missing","MISSING ERROR",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(typeJTextField.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Category Missing","MISSING ERROR",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    void clearField(){
        idJTextField.setText("");
        nameJTextField.setText("");
        locationJTextField.setText("");
        costJTextField.setText("");
        maxNumOfPplJTextField.setText("");
        availabilityTextField.setText("");
        dateTextField.setText("");
        typeJTextField.setText("");
    }

    boolean getAvailable(){
        if(availabilityTextField.getText().equals("")||availabilityTextField.getText().equals("true")){
            return true;
        }
        return false;
    }

    Venue getVenue(){
        return Venue.builder()
                .id(Integer.parseInt(idJTextField.getText()))
                .name(nameJTextField.getText())
                .location(locationJTextField.getText())
                .cost(Double.parseDouble(costJTextField.getText()))
                .maxNumGuest(Integer.parseInt(maxNumOfPplJTextField.getText()))
                .availability(getAvailable())
                .date(dateTextField.getText())
                .categoryId(typeJTextField.getText())
                .build();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== updateUser){
            System.out.println(idJTextField.getText());
           if(checkIfEmpty()){
               Venue venue = getVenue();
               String result = venueIO.update(this.newClient,venue);
               jTextUpdateArea.append(result);
           }
        }
        if(e.getSource()== deleteUser){
            int result = JOptionPane.showConfirmDialog(null, "Are you sure?", "WARNING", JOptionPane.YES_NO_OPTION);
            if(result==0&&checkIfEmpty()&&!idJTextField.getText().equals("")){
                String resultString = venueIO.delete(this.newClient,idJTextField.getText());
                clearField();
                jTextUpdateArea.append(resultString);
                updateJFrame.dispose();
            }else {
                JOptionPane.showMessageDialog(null,"Could not delete","Error update",JOptionPane.ERROR_MESSAGE);

            }
        }
    }
}
