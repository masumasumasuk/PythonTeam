//ver2_1210_by Hwang

package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class CalendarGUI extends JFrame {

    public CalendarGUI() {
        setTitle("December 2023 Reservation System");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createCalendarPanel();
    }

    private void createCalendarPanel() {
        JLabel monthLabel = new JLabel("December 2023", SwingConstants.CENTER);
        monthLabel.setFont(new Font("Serif", Font.BOLD, 20));
        add(monthLabel, BorderLayout.NORTH);

        JPanel calendarPanel = new JPanel(new GridLayout(0, 7));
        int year = 2023;
        int month = Calendar.DECEMBER;

        String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (String day : days) {
            JLabel dayLabel = new JLabel(day, SwingConstants.CENTER);
            if ("Sun".equals(day)) {
                dayLabel.setForeground(Color.RED);
            } else if ("Sat".equals(day)) {
                dayLabel.setForeground(Color.BLUE);
            }
            calendarPanel.add(dayLabel);
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 1);

        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        for (int i = 1; i < firstDayOfWeek; i++) {
            calendarPanel.add(new JLabel(""));
        }

        for (int day = 1; day <= maxDay; day++) {
            JButton dayButton = new JButton(String.valueOf(day));
            dayButton.addActionListener(new DayButtonListener(year, month, day));
            calendar.set(Calendar.DAY_OF_MONTH, day);

            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                dayButton.setForeground(Color.RED);
            } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                dayButton.setForeground(Color.BLUE);
            } else if (day == 25) {
                dayButton.setForeground(Color.RED);
            }

            calendarPanel.add(dayButton);
        }

        add(calendarPanel, BorderLayout.CENTER);
    }

    private static class DayButtonListener implements ActionListener {
        private final int year;
        private final int month;
        private final int day;

        public DayButtonListener(int year, int month, int day) {
            this.year = year;
            this.month = month;
            this.day = day;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Calendar selectedDate = Calendar.getInstance();
            selectedDate.set(year, month, day);
            int dayOfWeek = selectedDate.get(Calendar.DAY_OF_WEEK);

            if (dayOfWeek == Calendar.MONDAY) {
                JOptionPane.showMessageDialog(null, "Closed on Monday.");
            } else {
                TheaterReservationGUI theaterReservation = new TheaterReservationGUI(selectedDate);
                theaterReservation.setVisible(true);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalendarGUI frame = new CalendarGUI();
            frame.setVisible(true);
        });
    }
}
