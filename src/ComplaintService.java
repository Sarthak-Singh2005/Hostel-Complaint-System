import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ComplaintService {

    public boolean addComplaint(int userId, String category, String description) {
        String sql = "INSERT INTO complaints(user_id, category, description, status) VALUES (?, ?, ?, 'Pending')";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setString(2, category);
            ps.setString(3, description);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Complaint> getComplaintsByUser(int userId) {
        List<Complaint> list = new ArrayList<>();
        String sql = "SELECT id, user_id, category, description, status FROM complaints WHERE user_id = ? ORDER BY id DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Complaint(
                            rs.getInt("id"),
                            rs.getInt("user_id"),
                            "",
                            rs.getString("category"),
                            rs.getString("description"),
                            rs.getString("status")
                    ));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Complaint> getAllComplaints() {
        List<Complaint> list = new ArrayList<>();
        String sql = """
                SELECT c.id, c.user_id, u.name AS student_name, c.category, c.description, c.status
                FROM complaints c
                JOIN users u ON c.user_id = u.id
                ORDER BY c.id DESC
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new Complaint(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("student_name"),
                        rs.getString("category"),
                        rs.getString("description"),
                        rs.getString("status")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean updateStatus(int complaintId, String status) {
        String sql = "UPDATE complaints SET status = ? WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, complaintId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}