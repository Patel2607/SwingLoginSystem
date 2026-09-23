import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SwingLoginSystem extends JFrame implements ActionListener {

    private JLabel titleLabel, userLabel, passLabel, messageLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, clearButton, exitButton, showPasswordButton;

    private int attempts = 0;

    
    private final String correctUsername = "admin";
    private final String correctPassword = "12345";

    public SwingLoginSystem() {

        setTitle("Smart Login System");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(245, 248, 252));

        
        titleLabel = new JLabel("SMART LOGIN SYSTEM");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(85, 25, 300, 40);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel);

        
        userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Arial", Font.BOLD, 15));
        userLabel.setBounds(55, 90, 100, 30);
        mainPanel.add(userLabel);

        
        usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.PLAIN, 15));
        usernameField.setBounds(160, 90, 220, 30);
        mainPanel.add(usernameField);

        
        passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Arial", Font.BOLD, 15));
        passLabel.setBounds(55, 135, 100, 30);
        mainPanel.add(passLabel);

        
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 15));
        passwordField.setBounds(160, 135, 165, 30);
        mainPanel.add(passwordField);

        
        showPasswordButton = new JButton("Show");
        showPasswordButton.setBounds(330, 135, 70, 30);
        showPasswordButton.setFocusPainted(false);
        showPasswordButton.addActionListener(this);
        mainPanel.add(showPasswordButton);

        
        loginButton = new JButton("LOGIN");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBounds(55, 195, 100, 35);
        loginButton.setFocusPainted(false);
        loginButton.addActionListener(this);
        mainPanel.add(loginButton);

        
        clearButton = new JButton("CLEAR");
        clearButton.setFont(new Font("Arial", Font.BOLD, 14));
        clearButton.setBounds(170, 195, 100, 35);
        clearButton.setFocusPainted(false);
        clearButton.addActionListener(this);
        mainPanel.add(clearButton);

        
        exitButton = new JButton("EXIT");
        exitButton.setFont(new Font("Arial", Font.BOLD, 14));
        exitButton.setBounds(285, 195, 100, 35);
        exitButton.setFocusPainted(false);
        exitButton.addActionListener(this);
        mainPanel.add(exitButton);

        
        messageLabel = new JLabel("Enter your login details");
        messageLabel.setFont(new Font("Arial", Font.ITALIC, 13));
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setBounds(50, 250, 350, 30);
        mainPanel.add(messageLabel);

        add(mainPanel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        
        if (e.getSource() == loginButton) {

            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {

                messageLabel.setText("Please enter username and password.");
                messageLabel.setForeground(Color.RED);

            } else if (username.equals(correctUsername)
                    && password.equals(correctPassword)) {

                messageLabel.setText("Login Successful!");
                messageLabel.setForeground(new Color(0, 128, 0));

                JOptionPane.showMessageDialog(
                        this,
                        "Welcome, " + username + "!",
                        "Login Successful",
                        JOptionPane.INFORMATION_MESSAGE
                );

                openWelcomeWindow();

            } else {

                attempts++;

                if (attempts >= 3) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Maximum login attempts exceeded!",
                            "Access Denied",
                            JOptionPane.ERROR_MESSAGE
                    );

                    loginButton.setEnabled(false);
                    messageLabel.setText("Login disabled.");
                    messageLabel.setForeground(Color.RED);

                } else {

                    messageLabel.setText(
                            "Invalid username or password. Attempts left: "
                                    + (3 - attempts)
                    );

                    messageLabel.setForeground(Color.RED);
                }
            }
        }

        
        else if (e.getSource() == clearButton) {

            usernameField.setText("");
            passwordField.setText("");

            messageLabel.setText("Enter your login details");
            messageLabel.setForeground(Color.BLACK);
        }

        else if (e.getSource() == exitButton) {

            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to exit?",
                    "Exit",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
        else if (e.getSource() == showPasswordButton) {

            if (showPasswordButton.getText().equals("Show")) {

                passwordField.setEchoChar((char) 0);
                showPasswordButton.setText("Hide");

            } else {

                passwordField.setEchoChar('•');
                showPasswordButton.setText("Show");
            }
        }
    }

    private void openWelcomeWindow() {

        JFrame welcomeFrame = new JFrame("Welcome");
        welcomeFrame.setSize(400, 250);
        welcomeFrame.setLocationRelativeTo(this);
        welcomeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        welcomeFrame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(245, 248, 252));

        JLabel welcomeLabel = new JLabel(
                "<html><center>Welcome to Smart Login System<br>"
                        + "<br>Login completed successfully!</center></html>"
        );

        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add(welcomeLabel, BorderLayout.CENTER);

        welcomeFrame.add(panel);
        welcomeFrame.setVisible(true);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new SwingLoginSystem();
        });
    }
}

//username : admin
//passord : 12345