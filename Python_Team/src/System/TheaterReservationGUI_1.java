package System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TheaterReservationGUI_1 extends JFrame {
    private JComboBox<String> hallComboBox;
    private JPanel showPanel;
    private ShowCollection<Show> showCollection;
    private static int dayInfo;
    
    public static int getDayInfo() {
    	return dayInfo;
    }

    public TheaterReservationGUI_1(Calendar selectedDate) {
        setTitle("Theater Reservation System");
        setSize(600, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        initializeShowCollection(selectedDate);

        String[] halls = {"Hall 1"};
        hallComboBox = new JComboBox<>(halls);
        hallComboBox.addActionListener(this::hallComboBoxActionPerformed);

        JPanel selectionPanel = new JPanel();
        selectionPanel.add(new JLabel("Select Hall:"));
        selectionPanel.add(hallComboBox);
        add(selectionPanel, BorderLayout.NORTH);

        showPanel = new JPanel();
        showPanel.setLayout(new BoxLayout(showPanel, BoxLayout.Y_AXIS));
        showPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JScrollPane scrollPane = new JScrollPane(showPanel);
        add(scrollPane, BorderLayout.CENTER);

        updateShowPanel(halls[0]);
    }

    private void initializeShowCollection(Calendar selectedDate) {
        showCollection = new ShowCollection<>();
        int dayOfWeek = selectedDate.get(Calendar.DAY_OF_WEEK);

        // On weekends, show both performances
        if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
        	
            showCollection.addShow(new Show("Phantom of the Opera", "1-3 PM"));
            showCollection.addShow(new Show("Phantom of the Opera", "4-6 PM"));
        } else {
            // On weekdays, only one performance
            showCollection.addShow(new Show("Phantom of the Opera", "1-3 PM"));
        }
    }

    private void hallComboBoxActionPerformed(ActionEvent e) {
        String selectedHall = (String) hallComboBox.getSelectedItem();
        updateShowPanel(selectedHall);
    }

    private void updateShowPanel(String hall) {
		showPanel.removeAll();

		for (Show show : showCollection.getShows()) {
			JButton showButton = new JButton(show.toString());
			showButton.addActionListener(e -> {
			    int result = JOptionPane.showConfirmDialog(this,
			            "Selected performance: " + hall + ", " + show.getTime() + ", " + show.getName(),
			            "Confirmation", JOptionPane.OK_CANCEL_OPTION);
			    
			    if(show.getTime().equals("4-6 PM")) {
			    	dayInfo = 1;
			    } else dayInfo = 0;
			    

			    if (result == JOptionPane.OK_OPTION) {
			    	dispose();
			    }
			});

			// Set the maximum size of the button to be 3 times wider and 2 times taller
			Dimension preferredSize = showButton.getPreferredSize();
			showButton.setMaximumSize(new Dimension(preferredSize.width * 3, // Width is 3 times the preferred width
					preferredSize.height * 2 // Height is 2 times the preferred height
			));
			showButton.setAlignmentX(Component.CENTER_ALIGNMENT);

			// Add spacing between buttons
			if (showPanel.getComponentCount() > 0) {
				showPanel.add(Box.createRigidArea(new Dimension(0, 10)));
			}

			showPanel.add(showButton);
		}

        showPanel.revalidate();
        showPanel.repaint();
    }
}