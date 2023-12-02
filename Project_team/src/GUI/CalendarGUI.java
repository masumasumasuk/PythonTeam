// ver1_12.01

package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class CalendarGUI extends JFrame {

    public CalendarGUI() {
        setTitle("2023년 12월 예약 시스템");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createCalendarPanel();
    }

    private void createCalendarPanel() {
        // 상단에 년월 표시
        JLabel monthLabel = new JLabel("2023년 12월", SwingConstants.CENTER);
        monthLabel.setFont(new Font("Serif", Font.BOLD, 20));
        add(monthLabel, BorderLayout.NORTH);

        JPanel calendarPanel = new JPanel(new GridLayout(0, 7)); // 7일 주
        int year = 2023;
        int month = Calendar.DECEMBER;

        // 달력에 요일 헤더 추가
        String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (String day : days) {
            calendarPanel.add(new JLabel(day, SwingConstants.CENTER));
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 1);

        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        // 1일 전까지 빈 공간으로 채우기
        for (int i = 1; i < firstDayOfWeek; i++) {
            calendarPanel.add(new JLabel(""));
        }

        // 날짜 버튼 추가
        for (int day = 1; day <= maxDay; day++) {
            JButton dayButton = new JButton(String.valueOf(day));
            dayButton.addActionListener(new DayButtonListener(year, month, day));
            calendarPanel.add(dayButton);
        }

        add(calendarPanel, BorderLayout.CENTER);
    }

    // 날짜 버튼 클릭 시 이벤트 처리
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
            // TODO: 예약 관련 처리
            System.out.printf("Selected Date: %d-%d-%d\n", year, month + 1, day);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalendarGUI frame = new CalendarGUI();
            frame.setVisible(true);
        });
    }
}
