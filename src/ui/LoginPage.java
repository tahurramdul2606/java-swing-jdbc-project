package ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.DatabaseUtil;

import java.awt.Font;

public class LoginPage extends JFrame implements ActionListener {
	JLabel titleLabel, usernameLabel, passwordLabel;

	JTextField usernameField;

	JPasswordField passwordField;

	JButton loginButton, registerButton;

	JRadioButton userRadio, adminRadio;

	ButtonGroup group;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 1122, 701);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		setTitle("Library Management System - Login");
		
//		setSize(500, 450);
//		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(false); 
		
		getContentPane().setLayout(null);

		titleLabel = new JLabel("Library Login");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 19));
		titleLabel.setBounds(600, 80, 300, 40);
		getContentPane().add(titleLabel);

		usernameLabel = new JLabel("Username:");
		usernameLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		usernameLabel.setBounds(500, 200, 120, 30);
		getContentPane().add(usernameLabel);
		usernameField = new JTextField();
		usernameField.setFont(new Font("Tahoma", Font.BOLD, 12));
		usernameField.setBounds(650, 200, 250, 30);
		getContentPane().add(usernameField);

		passwordLabel = new JLabel("Password:");
		passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		passwordLabel.setBounds(500, 260, 120, 30);
		getContentPane().add(passwordLabel);
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		passwordField.setBounds(650, 260, 250, 30);
		getContentPane().add(passwordField);

		userRadio = new JRadioButton("Login as User");
		userRadio.setFont(new Font("Tahoma", Font.BOLD, 12));
		userRadio.setBounds(550, 320, 150, 30);
		userRadio.setSelected(true);
		getContentPane().add(userRadio);

		adminRadio = new JRadioButton("Login as Admin");
		adminRadio.setFont(new Font("Tahoma", Font.BOLD, 12));
		adminRadio.setBounds(720, 320, 170, 30);
		getContentPane().add(adminRadio);

		group = new ButtonGroup();
		group.add(userRadio);
		group.add(adminRadio);

		loginButton = new JButton("Login");
		loginButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		loginButton.setBounds(540, 400, 140, 40);
		loginButton.addActionListener(this);

		getContentPane().add(loginButton);

		registerButton = new JButton("Register New Student");
		registerButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		registerButton.setBounds(720, 400, 220, 40);
		registerButton.addActionListener(this);
		getContentPane().add(registerButton);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == loginButton) {
			loginUser();
		}

		if (e.getSource() == registerButton) {
			dispose();

			new RegistrationPage();
		}
	}

	private void loginUser() {

    String username = usernameField.getText();
    String password =
            String.valueOf(passwordField.getPassword());

    if (username.isEmpty() || password.isEmpty()) {
        JOptionPane.showMessageDialog(this,
                "Please fill all fields!");

        return;
    }

    String selectedRole = "";

    if (userRadio.isSelected()) {
        selectedRole = "USER";
    }

    if (adminRadio.isSelected()) {
        selectedRole = "ADMIN";
    }

    try {

        Connection con = DatabaseUtil.getConnection();

        String query =
                "SELECT * FROM users WHERE username=? AND password=? AND role=?";

        PreparedStatement ps =
                con.prepareStatement(query);
        ps.setString(1, username);
        ps.setString(2, password);
        ps.setString(3, selectedRole);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            JOptionPane.showMessageDialog(this,
                    "Login Successful!");
            dispose();

            // admin
            if (selectedRole.equals("ADMIN")) {
                JOptionPane.showMessageDialog(this,
                        "Opening Admin Dashboard");

                new AdminDashboard();

            } else {
                JOptionPane.showMessageDialog(this,
                        "Opening User Dashboard");
                new UserDashboard(username);
            }

        } else {
            JOptionPane.showMessageDialog(this,
                    "Invalid Username or Password!");
        }

    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this,
                "Database Error!");
    }
}}
