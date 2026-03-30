import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AdminDashboard extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public AdminDashboard() {
        setTitle("Admin Dashboard");
        setSize(950, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel top = new JLabel("Welcome, " + Session.userName + " | Admin Dashboard");
        top.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(top, BorderLayout.NORTH);

        model = new DefaultTableModel(new Object[]{"ID", "Student", "Category", "Description", "Status"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel panel = new JPanel();
        JButton refreshBtn = new JButton("Refresh");
        JButton updateBtn = new JButton("Update Status");
        JButton logoutBtn = new JButton("Logout");

        panel.add(refreshBtn);
        panel.add(updateBtn);
        panel.add(logoutBtn);
        add(panel, BorderLayout.SOUTH);

        refreshBtn.addActionListener(e -> loadComplaints());
        updateBtn.addActionListener(e -> updateSelectedComplaint());
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

    private void loadComplaints() {
        model.setRowCount(0);
        ComplaintService service = new ComplaintService();
        List<Complaint> complaints = service.getAllComplaints();

        for (Complaint c : complaints) {
            model.addRow(new Object[]{
                    c.getId(),
                    c.getStudentName(),
                    c.getCategory(),
                    c.getDescription(),
                    c.getStatus()
            });
        }
    }

    private void updateSelectedComplaint() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a complaint first.");
            return;
        }

        int complaintId = (int) model.getValueAt(row, 0);

        String[] statuses = {"Pending", "In Progress", "Resolved"};
        String status = (String) JOptionPane.showInputDialog(
                this,
                "Choose new status:",
                "Update Status",
                JOptionPane.QUESTION_MESSAGE,
                null,
                statuses,
                statuses[0]
        );

        if (status == null) return;

        ComplaintService service = new ComplaintService();
        if (service.updateStatus(complaintId, status)) {
            JOptionPane.showMessageDialog(this, "Status updated.");
            loadComplaints();
        } else {
            JOptionPane.showMessageDialog(this, "Update failed.");
        }
    }
}