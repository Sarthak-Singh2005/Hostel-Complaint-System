public class Complaint {
    private int id;
    private int userId;
    private String studentName;
    private String category;
    private String description;
    private String status;

    public Complaint(int id, int userId, String studentName, String category, String description, String status) {
        this.id = id;
        this.userId = userId;
        this.studentName = studentName;
        this.category = category;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }
}