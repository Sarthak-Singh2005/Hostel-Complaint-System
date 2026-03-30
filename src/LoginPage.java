import javax.swing.*;
import java.awt.*;

public class LoginPage extends JFrame {

    JTextField email;
    JPasswordField password;

    public LoginPage() {
        setTitle("Login");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel l1 = new JLabel("Email:");
        l1.setBounds(30, 30, 100, 25);
        add(l1);

        email = new JTextField();
        email.setBounds(120, 30, 170, 25);
        add(email);

        JLabel l2 = new JLabel("Password:");
        l2.setBounds(30, 70, 100, 25);
        add(l2);

        password = new JPasswordField();
        password.setBounds(120, 70, 170, 25);
        add(password);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(120, 115, 80, 30);
        add(loginBtn);

        JButton registerBtn = new JButton("Register");
        registerBtn.setBounds(210, 115, 80, 30);
        add(registerBtn);

        loginBtn.addActionListener(e -> login());

        registerBtn.addActionListener(e -> {
            new RegisterPage();
            dispose();
        });

        setVisible(true);
    }

    void login() {
        String em = email.getText().trim();
        String pw = new String(password.getPassword());

        UserService us = new UserService();
        User user = us.login(em, pw);

        if (user == null) {
            JOptionPane.showMessageDialog(this, "Invalid Login");
            return;
        }

        Session.userId = user.getId();
        Session.userName = user.getName();
        Session.role = user.getRole();

        JOptionPane.showMessageDialog(this, "Welcome, " + user.getName() + "!");

        if ("admin".equalsIgnoreCase(user.getRole())) {
            new AdminDashboard();
        } else {
            new StudentDashboard();
        }

        dispose();
    }
}