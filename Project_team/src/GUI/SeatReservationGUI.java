package GUI;
// ver1_12.01
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class SeatReservationGUI extends JFrame {

    private final Map<String, JButton[]> seatMap; // 좌석을 저장하는 맵
    private final String[] seatTypes = {"V", "S", "A", "B"};
    private final int seatsPerType = 30; // 각 타입별 좌석 수

    public SeatReservationGUI() {
        setTitle("좌석 예약 시스템");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        private final int number;

        public SeatButtonListener(String type, int number) {
            this.type = type;
            this.number = number;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            if (button.getBackground().equals(Color.GREEN)) {
                button.setBackground(Color.RED);
                JOptionPane.showMessageDialog(SeatReservationGUI.this, 
                        "Selected Seat: " + type + " " + number);
            } else {
                button.setBackground(Color.GREEN);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SeatReservationGUI frame = new SeatReservationGUI();
            frame.setVisible(true);
        });
    }
}
