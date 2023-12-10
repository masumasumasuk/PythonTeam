package System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReservationPerson_GUI extends JFrame {

    private int selectedPersonCount = 0;

    public ReservationPerson_GUI() {
        setTitle("예약 인원 선택");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(1, 2));

        JLabel label = new JLabel("예약할 인원 수");
        panel.add(label);

        ButtonGroup group = new ButtonGroup();
        JRadioButton onePersonButton = new JRadioButton("1명");
        JRadioButton twoPersonsButton = new JRadioButton("2명");

        group.add(onePersonButton);
        group.add(twoPersonsButton);

        panel.add(onePersonButton);
        panel.add(twoPersonsButton);

        JButton confirmButton = new JButton("확인");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (onePersonButton.isSelected()) {
                    selectedPersonCount = 1;
                } else if (twoPersonsButton.isSelected()) {
                    selectedPersonCount = 2;
                }
                
                SwingUtilities.invokeLater(() -> {
                	int n = selectedPersonCount;
                	SeatReservationGUI_Double.setNum(n);
	                SeatReservationGUI_Double frame = new SeatReservationGUI_Double();
	                frame.setVisible(true);
	        });

                dispose();
            }
        });

        add(panel, BorderLayout.CENTER);
        add(confirmButton, BorderLayout.SOUTH);
    }

    public int getSelectedPersonCount() {
        return selectedPersonCount;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ReservationPerson_GUI frame = new ReservationPerson_GUI();
            frame.setVisible(true);
        });
    }
}
