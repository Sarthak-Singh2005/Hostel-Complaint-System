import javax.swing.*;

public class RegisterPage extends JFrame {

    JTextField name, email;
    JPasswordField password;

    public RegisterPage() {
        setTitle("Register");
        setSize(350, 280);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel l1 = new JLabel("Name:");
        l1.setBounds(30, 30, 100, 25);
        add(l1);

        name = new JTextField();
        name.setBounds(120, 30, 170, 25);
        add(name);

        JLabel l2 = new JLabel("Email:");
        l2.setBounds(30, 70, 100, 25);
        add(l2);

        email = new JTextField();
        email.setBounds(120, 70, 170, 25);
        add(email);

        JLabel l3 = new JLabel("Password:");
        l3.setBounds(30, 110, 100, 25);
        add(l3);

        password = new JPasswordField();
        password.setBounds(120, 110, 170, 25);
        add(password);

        JButton registerBtn = new JButton("Register");
        registerBtn.setBounds(120, 155, 90, 30);
        add(registerBtn);

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(215, 155, 75, 30);
        add(backBtn);

        registerBtn.addActionListener(e -> registerUser());

        backBtn.addActionListener(e -> {
            new LoginPage();
            dispose();
        });

        setVisible(true);
    }

    void registerUser() {
        String n = name.getText().trim();
        String e = email.getText().trim();
        String p = new String(password.getPassword());

        if (n.isEmpty() || e.isEmpty() || p.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.");
            return;
        }

        UserService us = new UserService();

        if (us.register(n, e, p)) {
            JOptionPane.showMessageDialog(this, "Registration Successful!");
            new LoginPage();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Registration failed.");
        }
    }
}