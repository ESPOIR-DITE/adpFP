package com.mycompany.adpfp.gui.venue;

import com.mycompany.adpfp.datas.venue.Venue;
import com.mycompany.adpfp.io.NewClient;
import com.mycompany.adpfp.io.venue.VenueIO;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Date;

public class VenueGui extends JFrame implements ActionListener {
    private JPanel createVenuePanel = new JPanel();
    private JPanel westPanel = new JPanel();
    private JPanel eastPanel = new JPanel();
    private JLabel space = new JLabel("");
    private JLabel title = new JLabel("Create Venue",JLabel.CENTER);
    private JLabel nameLabel = new JLabel("Name");
    private JLabel locationLabel = new JLabel("Location");
    private JLabel typeLabel = new JLabel("Type");
    private JLabel costLabel = new JLabel("Cost");
    private JLabel MaxNumbOfPplLabel = new JLabel("Max Num Of Ppl");

    private JTextField nameJTextField = new JTextField(20);
    private JTextField locationJTextField = new JTextField(20);
    private JTextField typeJTextField = new JTextField(20);
    private JTextField costJTextField = new JTextField(20);
    private JTextField maxNumOfPplJTextField = new JTextField(20);
    private JButton createVenue = new JButton("Create Venue");
    private JButton viewVenue = new JButton("View Venues");
    JTable table = new JTable();

    JFrame jFrame = new JFrame();
    JFrame updateFrame = new JFrame();

    private JTextArea jTextArea = new JTextArea(4,50);

    private Color btnBrown = new Color(81,43,40);
    private Color btnBrownSelected = new Color(166,123,119);
    Border blackline = BorderFactory.createLineBorder(Color.black);
    Font f = new Font("Verdana",Font.BOLD,20);
    private NewClient newClient;
    private VenueIO venueIO = new VenueIO();
    public VenueGui(NewClient newClient) throws HeadlessException {
        this.newClient = newClient;
        createVenuePanel.setLayout(new BorderLayout(10,10));
        createVenue.addActionListener(this);
        jTextArea.setBorder(blackline);
        viewVenue.addActionListener(this);
        createVenue.setBackground(btnBrown);
        createVenue.setForeground(Color.WHITE);
        viewVenue.setBackground(btnBrown);
        viewVenue.setForeground(Color.WHITE);

        title.setFont(f);
        title.setForeground(btnBrown);
        createVenuePanel.add(title,BorderLayout.NORTH);
        createVenuePanel.add(getWestPanel(),BorderLayout.WEST);
        createVenuePanel.add(getEastPanel(),BorderLayout.CENTER);
        createVenuePanel.add(jTextArea,BorderLayout.SOUTH);
    }

    public JFrame getTableJFrame(){
        jFrame.setTitle("Venues");

        String [] columnNames = {"Name","Location","Type","Cost","Max Num Of Ppl"};
        String [][] data = {
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"}
        };

        table = new JTable(data,columnNames);
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
       VenueUpdateFrame updateJF = new VenueUpdateFrame(this.newClient);
        updateFrame.add(updateJF.getUpdateVenue(rowSelected,mode));
        updateFrame.setLocationRelativeTo(null);
        updateFrame.setVisible(true);
        updateFrame.setSize(500,400);
    }

    public JPanel getCreateVenuePanel() {
        return createVenuePanel;
    }

    public JPanel getWestPanel() {
        westPanel.setLayout(new GridLayout(9,1,5,5));
        westPanel.add(nameLabel);
        westPanel.add(locationLabel);
        westPanel.add(typeLabel);
        westPanel.add(costLabel);
        westPanel.add(MaxNumbOfPplLabel);
        return westPanel;
    }

    public JPanel getEastPanel() {
        eastPanel.setLayout(new GridLayout(9,1,5,5));
        eastPanel.add(nameJTextField);
        eastPanel.add(locationJTextField);
        eastPanel.add(typeJTextField);
        eastPanel.add(costJTextField);
        eastPanel.add(maxNumOfPplJTextField);
        eastPanel.add(space);
        eastPanel.add(createVenue);
        eastPanel.add(space);
        eastPanel.add(viewVenue);
        return eastPanel;
    }

    private Venue getVenue(){
        try{
            String name = nameJTextField.getText();
            String location = locationJTextField.getText();
            String type = typeJTextField.getText();
            double cost = Double.parseDouble(costJTextField.getText());
            int maxNumOfPpl = Integer.parseInt(maxNumOfPplJTextField.getText());
            boolean availability = true;
            String date = new Date().toString();

            return Venue.builder()
                    .name(name)
                    .location(location)
                    .categoryId(type)
                    .cost(cost)
                    .date(date)
                    .availability(availability)
                    .description("test")
                    .maxNumGuest(maxNumOfPpl)
                    .build();

        }catch (NullPointerException nullPointerException){
            clearFields();
            JOptionPane.showMessageDialog(this,"Error");
        }catch (NumberFormatException numberFormatException){
            clearFields();
            JOptionPane.showMessageDialog(this,"Error");
        }

        return null;
    }

    void clearFields(){
        nameJTextField.setText("");
        locationJTextField.setText("");
        typeJTextField.setText("");
        costJTextField.setText("");
        maxNumOfPplJTextField.setText("");
    }

    boolean checkIfEmpty(){
        if (nameJTextField.getText().equals("")) {
            JOptionPane.showMessageDialog(this,"Name missing");
            return false;
        }
        if (locationJTextField.getText().equals("")) {
            JOptionPane.showMessageDialog(this,"Location missing");
            return false;
        }
        if (typeJTextField.getText().equals("")) {
            JOptionPane.showMessageDialog(this,"Type missing");
            return false;
        }
        if (costJTextField.getText().equals("")) {
            JOptionPane.showMessageDialog(this,"Cost missing");
            return false;
        }
        if (maxNumOfPplJTextField.getText().equals("")) {
            JOptionPane.showMessageDialog(this,"Num of Guests missing");
            return false;
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==createVenue){
            System.out.println("voila");
            if(checkIfEmpty()){
                System.out.println(venueIO.createVenue(this.newClient,getVenue()));
            }
        }
        if(e.getSource() == viewVenue){
            System.out.println("view clicked");
            TableGui tableGui  = new TableGui();
            tableGui.getTableJFrame(venueIO.readVenues(this.newClient),this.newClient);
            //getTableJFrame();
        }
    }
}
