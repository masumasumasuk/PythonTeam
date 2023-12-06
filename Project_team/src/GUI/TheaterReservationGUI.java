package GUI;
//ver1_12.03
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Show {
    private String name;
    private String time;

    public Show(String name, String time) {
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return time + " - " + name;
    }
}

class ShowCollection<T extends Show> {
    private List<T> shows;

    public ShowCollection() {
        shows = new ArrayList<>();
    }

    public void addShow(T show) {
        shows.add(show);
    }

    public List<T> getShows() {
        return shows;
    }
}

public class TheaterReservationGUI extends JFrame {
    private JComboBox<String> hallComboBox;
    private JPanel showPanel;
    private ShowCollection<Show> showCollection;

    public TheaterReservationGUI() {
        setTitle("Theater Reservation System");
        setSize(600, 200); // Adjust the window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initializeShowCollection();

        String[] halls = {"Hall 1", "Hall 2", "Hall 3"};
        hallComboBox = new JComboBox<>(halls);
        hallComboBox.addActionListener(this::hallComboBoxActionPerformed);

        JPanel selectionPanel = new JPanel();
        selectionPanel.add(new JLabel("Select Hall:"));
        selectionPanel.add(hallComboBox);
        add(selectionPanel, BorderLayout.NORTH);

        showPanel = new JPanel();
        showPanel.setLayout(new BoxLayout(showPanel, BoxLayout.Y_AXIS));
        showPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add a transparent inset at the top to lower the position
        showPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        JScrollPane scrollPane = new JScrollPane(showPanel);
        add(scrollPane, BorderLayout.CENTER);

        updateShowPanel(halls[0]);
    }

    private void initializeShowCollection() {
        showCollection = new ShowCollection<>();
        showCollection.addShow(new Show("Les Misérables", "1-3 PM"));
        showCollection.addShow(new Show("Phantom of the Opera", "4-6 PM"));
        showCollection.addShow(new Show("Hamlet", "7-9 PM"));
    }

    private void hallComboBoxActionPerformed(ActionEvent e) {
        String selectedHall = (String) hallComboBox.getSelectedItem();
        updateShowPanel(selectedHall);
    }

    private void updateShowPanel(String hall) {
        showPanel.removeAll();

        for (Show show : showCollection.getShows()) {
            JButton showButton = new JButton(show.toString());
            showButton.addActionListener(e -> JOptionPane.showMessageDialog(this,
                    "Selected performance: " + hall + ", " + show.getTime() + ", " + show.getName()));

            Dimension preferredSize = showButton.getPreferredSize();
            showButton.setMaximumSize(new Dimension(
                    (int) (preferredSize.width * 1.8), // Width is 1.8 times
                    preferredSize.height)); // Height remains the same

            showButton.setAlignmentX(Component.CENTER_ALIGNMENT);

            if (showPanel.getComponentCount() > 0) {
                showPanel.add(Box.createRigidArea(new Dimension(
                        0, (int) (preferredSize.height * 0.2)))); // Vertical spacing is 20% of button's height
            }
            
            showPanel.add(showButton);
        }

        showPanel.revalidate();
        showPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TheaterReservationGUI frame = new TheaterReservationGUI();
            frame.setVisible(true);
        });
    }
}
