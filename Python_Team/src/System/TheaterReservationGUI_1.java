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
    private static String gradeInfo;
    
    public static int getDayInfo() {
    	return dayInfo;
    }
    
    public static String getGradeInfo() {
    	return gradeInfo;
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
        if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY || Reservation_InquiryGUI.getDay() == 25) {
        	
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
			    	
			    	inputGrade();
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
        dispose();
    }
    
    private void inputGrade() {
    	boolean a = true;
    	while(a) {

			try {
				gradeInfo = JOptionPane.showInputDialog("V, S, A, B 중 조회 할 좌석 등급을 입력해주세요 : ");
				invalidInput(gradeInfo);
				
				Cheak_reservation c = new Cheak_reservation();
				
		    	int day = Reservation_InquiryGUI.getDay();
				int dayInfo = TheaterReservationGUI.getDayInfo();
				int date = c.getConvertDay(dayInfo, day);
				
				reservationInquiry(gradeInfo, date);
				
				a = false;
				dispose();
				
			} catch (IllegalArgumentException e) {
				JOptionPane.showMessageDialog(null, "잘못 입력하였습니다");
			}
		}
	}
    
    private void reservationInquiry(String g, int date) {
    	Save saves = new Save();
		int day = Reservation_InquiryGUI.getDay();
    	if (g.equals("V")) {
			System.out.println("************ "+ day +"일 V석 좌석 현황입니다. ************");
			for (int i = 0; i < 30; i++) {
				System.out.println((i + 1) + "번 좌석의 예매자 이름 : " + saves.get_save(date, i)[1] + ", 예약 번호는 : "
						+ saves.get_save(date, i)[2]);
			}
		} else if (g.equals("S")) {
			System.out.println("************ "+ day +"일 S석 좌석 현황입니다. ************");
			for (int i = 30; i < 60; i++) {
				System.out.println((i - 29) + "번 좌석의 예매자 이름 : " + saves.get_save(date, i)[1] + ", 예약 번호는 : "
						+ saves.get_save(date, i)[2]);
			}
		} else if (g.equals("A")) {
			System.out.println("************ "+ day +"일 A석 좌석 현황입니다. ************");
			for (int i = 60; i < 90; i++) {
				System.out.println((i - 59) + "번 좌석의 예매자 이름 : " + saves.get_save(date, i)[1] + ", 예약 번호는 : "
						+ saves.get_save(date, i)[2]);
			}
		} else if (g.equals("B")) {
			System.out.println("************ "+ day +"일 B석 좌석 현황입니다. ************");
			for (int i = 90; i < 120; i++) {
				System.out.println((i - 89) + "번 좌석의 예매자 이름 : " + saves.get_save(date, i)[1] + ", 예약 번호는 : "
						+ saves.get_save(date, i)[2]);
			}
		}
    }

	private static void invalidInput(String in) {
		if (!(in.equals("V") || in.equals("S") || in.equals("A") || in.equals("B"))) {
			throw new IllegalArgumentException();
		}
	}
}