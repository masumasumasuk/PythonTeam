package System;
// ver1_12.01
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class SeatReservationGUI_Double extends JFrame {
	
	private final Map<String, JButton[]> seatMap; // 좌석을 저장하는 맵
    private final String[] seatTypes = {"V", "S", "A", "B"};
    private final int seatsPerType = 30; // 각 타입별 좌석 수
    
    private int selectedSeatsCount = 0;
    private final JButton[] selectedSeats = new JButton[2];
    private static int num;
    
    static void setNum(int n) {
    	num = n;
    }
    
    public SeatReservationGUI_Double() {
        setTitle("좌석 예약 시스템");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout()); // Change layout to BorderLayout

        seatMap = new HashMap<>();

        JPanel seatPanel = new JPanel();
        seatPanel.setLayout(new GridLayout(seatTypes.length, 1)); // Use GridLayout for seat rows

        for (String type : seatTypes) {
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Center the seats
            JButton[] seats = new JButton[seatsPerType];
            for (int i = 0; i < seatsPerType; i++) {
                seats[i] = new JButton(type + " " + (i + 1));
                seats[i].setBackground(Color.GREEN);
                seats[i].addActionListener(new SeatButtonListener(type, i + 1));
                panel.add(seats[i]);
            }
            seatMap.put(type, seats);
            seatPanel.add(panel);
        }

        JLabel stageLabel = new JLabel("STAGE", SwingConstants.CENTER); // Add stage label at the top
        stageLabel.setFont(new Font("Arial", Font.BOLD, 24));
        stageLabel.setOpaque(true);
        stageLabel.setBackground(Color.BLUE);
        stageLabel.setForeground(Color.WHITE);
        stageLabel.setPreferredSize(new Dimension(0, 50)); // Set the preferred height of the stage label

        add(stageLabel, BorderLayout.NORTH); // Add the stage label to the top
        add(seatPanel, BorderLayout.CENTER); // Add the seat panel to the center
    }

	private class SeatButtonListener implements ActionListener {
		private final String type;
		private int number;
		private static int number_1, number_2;

		public SeatButtonListener(String type, int number) {
			this.type = type;
			this.number = number;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Cheak_reservation c = new Cheak_reservation();
			JButton button = (JButton) e.getSource();
			String grade = this.type;

			if (num == 2) {
				if (selectedSeatsCount == 0) {
					selectedSeats[0] = button;
					selectedSeatsCount++;
					button.setBackground(Color.RED);
					number_1 = number;
				} else if (selectedSeatsCount == 1) {
					selectedSeats[1] = button;
					selectedSeatsCount++;
					button.setBackground(Color.RED);
					number_2 = number;

					ReservationInfo_Double();
				}
			} else if (num == 1) {
				if (button.getBackground().equals(Color.GREEN)) {
					button.setBackground(Color.RED);
				} else {
					button.setBackground(Color.GREEN);
				}
				ReservationInfo_Single();

			}
		}

		private void ReservationInfo_Double() {
			Cheak_reservation c = new Cheak_reservation();
			int day = CalendarGUI.getDay();
			int dayInfo = TheaterReservationGUI.getDayInfo();
			int ConvertDay = c.getConvertDay(dayInfo, day);
            
            if(c.reservation_Cheak(ConvertDay, type, number_1)&&c.reservation_Cheak(ConvertDay, type, number_2)) {
            	String name = JOptionPane.showInputDialog("예매자의 성명을 입력하세요");
                String phoneNum = JOptionPane.showInputDialog("예매자의 전화번호를 입력하세요");
                String password = JOptionPane.showInputDialog("예매자의 비밀번호를 입력하세요");
            handleReservation(ConvertDay, name, phoneNum, type, number_1, password);
            handleReservation(ConvertDay, name, phoneNum, type, number_2, password);
            }else {
            	JOptionPane.showMessageDialog(null,"이미 예약된 좌석입니다.");
            	dispose();
            }
        }
		
		private void ReservationInfo_Single() {
	    	Cheak_reservation c = new Cheak_reservation();
			int day = CalendarGUI.getDay();
			int dayInfo = TheaterReservationGUI.getDayInfo();
			int ConvertDay = c.getConvertDay(dayInfo, day);
			if(c.reservation_Cheak(ConvertDay, type, number)){
			String name = JOptionPane.showInputDialog("예매자의 성명을 입력하세요");
            String phoneNum = JOptionPane.showInputDialog("예매자의 전화번호를 입력하세요");
            String password = JOptionPane.showInputDialog("예매자의 비밀번호를 입력하세요");
            
            handleReservation(ConvertDay, name, phoneNum, type, number, password);
			} else {
            	JOptionPane.showMessageDialog(null,"이미 예약된 좌석입니다.");
            	dispose();
            }
        }
        
    }

    private void handleReservation(int date, String name, String phoneNum, String grade, int sn, String password) {
    	Reservation_system s = new Reservation_system();
        Save saves = new Save();
    	Cheak_reservation c = new Cheak_reservation();
    	String i = String.valueOf(sn);

		switch (grade) {
		case "S":
			sn += 30;
			break;
		case "A":
			sn += 60;
			break;
		case "B":
			sn += 90;
			break;
		default:
			sn = sn;
			break;
		}

		s.set_TopNum(grade, i);

		s.set_BottomNum();

		sn -= 1;
		i = c.d_grade(sn);

		String[] info = { i, name, s.get_Num(), phoneNum, password };
		
		saves.add_save(date, sn, info);

		JOptionPane.showMessageDialog(null, "예약이 완료 되었습니다." + "\n" + name + "님의 좌석은" + "12월 " + CalendarGUI.getDay() + "일 " +
		i +"("+c.d_num(i, sn)+") 석 입니다." + "\n" + "예매번호는 : " + s.get_Num() + "입니다.");
		
		
    }

}