package com.mycompany.adpfp.gui.venue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Venue extends JFrame implements ActionListener {
    private JPanel createVenuePanel = new JPanel();
    private JPanel westPanel = new JPanel();
    private JPanel eastPanel = new JPanel();
    private JLabel space = new JLabel("");
    private JLabel title = new JLabel("Create Venue");
    private JLabel nameLabel = new JLabel("Name");
    private JLabel locationLabel = new JLabel("Location");
    private JLabel typeLabel = new JLabel("Type");
    private JLabel costLabel = new JLabel("Cost");
    private JLabel MaxNumbOfPplLabel = new JLabel("Max Num Of Ppl");

    private JTextField nameJTextField = new JTextField(10);
    private JTextField locationJTextField = new JTextField(10);
    private JTextField typeJTextField = new JTextField(10);
    private JTextField costJTextField = new JTextField(10);
    private JTextField maxNumOfPplJTextField = new JTextField(10);

    private JButton createVenue = new JButton("Create");

    private JFrame jFrame = new JFrame();

    public Venue() throws HeadlessException {
        createVenuePanel.setLayout(new BorderLayout(5,5));
        createVenue.addActionListener(this);
        createVenuePanel.add(title,BorderLayout.NORTH);
        createVenuePanel.add(getWestPanel(),BorderLayout.WEST);
        createVenuePanel.add(getEastPanel(),BorderLayout.CENTER);
        createVenuePanel.add(getJFrame(),BorderLayout.SOUTH);
    }
    public JTable getJFrame(){
        JTable jTable = null;
        //jFrame.setTitle("Venues");

        String [] columnNames = {"Name","Location","Type","Cost","Max Num Of Ppl"};
        String [][] data = {
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"},
                {"test","congo","commune","10","100"}
        };
        jTable = new JTable(data,columnNames);
        jTable.setBounds(12,15,200,200);
        JScrollPane sp = new JScrollPane(jTable);
//        jFrame.add(sp);
//        jFrame.setSize(500, 200);
//        jFrame.setVisible(true);

        return jTable;
    }

    public JPanel getCreateVenuePanel() {
        return createVenuePanel;
    }

    public JPanel getWestPanel() {
        westPanel.setLayout(new GridLayout(15,1));
        westPanel.add(nameLabel);
        westPanel.add(locationLabel);
        westPanel.add(typeLabel);
        westPanel.add(costLabel);
        westPanel.add(MaxNumbOfPplLabel);
        return westPanel;
    }

    public JPanel getEastPanel() {
        eastPanel.setLayout(new GridLayout(15,1));
        eastPanel.add(nameJTextField);
        eastPanel.add(locationJTextField);
        eastPanel.add(typeJTextField);
        eastPanel.add(costJTextField);
        eastPanel.add(maxNumOfPplJTextField);
        eastPanel.add(space);
        eastPanel.add(createVenue);
        return eastPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==createVenue){
            System.out.println("voila");
        }
    }
}
