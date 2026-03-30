import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StudentDashboard extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public StudentDashboard() {
        setTitle("Student Dashboard");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel top = new JLabel("Welcome, " + Session.userName + " | Student Dashboard");
        top.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(top, BorderLayout.NORTH);

        model = new DefaultTableModel(new Object[]{"ID", "Category", "Description", "Status"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel panel = new JPanel();
        JButton addBtn = new JButton("Submit Complaint");
        JButton refreshBtn = new JButton("Refresh");
        JButton logoutBtn = new JButton("Logout");

        panel.add(addBtn);
        panel.add(refreshBtn);
        panel.add(logoutBtn);
        add(panel, BorderLayout.SOUTH);

        addBtn.addActionListener(e -> submitComplaint());
        refreshBtn.addActionListener(e -> loadComplaints());
        logoutBtn.addActionListener(e -> {
            Session.userId = -1;
            Session.userName = "";
            Session.role = "";
            new LoginPage();
            dispose();
        });

        loadComplaints();
        setVisible(true);
    }

    private void submitComplaint() {
        String[] categories = {"Water", "Electricity", "Cleanliness", "Internet", "Other"};
        JComboBox<String> categoryBox = new JComboBox<>(categories);
        JTextArea descArea = new JTextArea(5, 20);
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);

        JPanel panel = new JPanel(new BorderLayout(5, 5));
        JPanel form = new JPanel(new GridLayout(2, 1, 5, 5));
        form.add(new JLabel("Category:"));
        form.add(categoryBox);
        panel.add(form, BorderLayout.NORTH);
        panel.add(new JScrollPane(descArea), BorderLayout.CENTER);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Submit Complaint",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            String category = (String) categoryBox.getSelectedItem();
            String desc = descArea.getText().trim();

            if (desc.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Description cannot be empty.");
                return;
            }

            ComplaintService service = new ComplaintService();
            if (service.addComplaint(Session.userId, category, desc)) {
                JOptionPane.showMessageDialog(this, "Complaint submitted successfully.");
                loadComplaints();
            } else {
                JOptionPane.showMessageDialog(this, "Could not submit complaint.");
            }
        }
    }

    private void loadComplaints() {
        model.setRowCount(0);
        ComplaintService service = new ComplaintService();
        List<Complaint> complaints = service.getComplaintsByUser(Session.userId);

        for (Complaint c : complaints) {
            model.addRow(new Object[]{
                    c.getId(),
                    c.getCategory(),
                    c.getDescription(),
                    c.getStatus()
            });
        }
    }
}