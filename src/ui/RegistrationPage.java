package ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import database.DatabaseUtil;
import java.awt.Font;
import java.sql.*;

public class RegistrationPage extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	JLabel titleLabel, nameLabel, emailLabel, usernameLabel, passwordLabel, confirmPasswordLabel, phoneLabel,
			departmentLabel, enrollmentLabel;

	JTextField nameField, emailField, usernameField, phoneField, enrollmentField;

	JPasswordField passwordField, confirmPasswordField;

	JComboBox<String> departmentBox;

	JButton registerButton, backButton;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrationPage frame = new RegistrationPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public RegistrationPage() {
		setBackground(new Color(240, 240, 240));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 1122, 701);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		setTitle("Library Management System - Registration");
//		setSize(550, 700);
//		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(false);
		getContentPane().setLayout(null);

		titleLabel = new JLabel("Student Registration");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
		titleLabel.setBounds(600, 40, 400, 40);
		getContentPane().add(titleLabel);

		nameLabel = new JLabel("Full Name:");
		nameLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		nameLabel.setBounds(450, 120, 150, 30);
		getContentPane().add(nameLabel);
		nameField = new JTextField();
		nameField.setFont(new Font("Tahoma", Font.BOLD, 12));
		nameField.setBounds(650, 120, 280, 35);
		getContentPane().add(nameField);

		emailLabel = new JLabel("Email:");
		emailLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		emailLabel.setBounds(450, 170, 150, 30);
		getContentPane().add(emailLabel);
		emailField = new JTextField();
		emailField.setFont(new Font("Tahoma", Font.BOLD, 12));
		emailField.setBounds(650, 170, 280, 35);
		getContentPane().add(emailField);

		usernameLabel = new JLabel("Username:");
		usernameLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		usernameLabel.setBounds(450, 220, 150, 30);
		getContentPane().add(usernameLabel);
		usernameField = new JTextField();
		usernameField.setFont(new Font("Tahoma", Font.BOLD, 12));
		usernameField.setBounds(650, 220, 280, 35);
		getContentPane().add(usernameField);

		passwordLabel = new JLabel("Password:");
		passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		passwordLabel.setBounds(450, 270, 150, 30);
		getContentPane().add(passwordLabel);
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		passwordField.setBounds(650, 270, 280, 35);
		getContentPane().add(passwordField);

		confirmPasswordLabel = new JLabel("Confirm Password:");
		confirmPasswordLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		confirmPasswordLabel.setBounds(450, 320, 170, 30);
		getContentPane().add(confirmPasswordLabel);
		confirmPasswordField = new JPasswordField();
		confirmPasswordField.setFont(new Font("Tahoma", Font.BOLD, 12));
		confirmPasswordField.setBounds(650, 320, 280, 35);
		getContentPane().add(confirmPasswordField);

		phoneLabel = new JLabel("Phone Number:");
		phoneLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		phoneLabel.setBounds(450, 370, 150, 30);
		getContentPane().add(phoneLabel);
		phoneField = new JTextField();
		phoneField.setFont(new Font("Tahoma", Font.BOLD, 12));
		phoneField.setBounds(650, 370, 280, 35);
		getContentPane().add(phoneField);

		departmentLabel = new JLabel("Department:");
		departmentLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		departmentLabel.setBounds(450, 420, 150, 30);
		getContentPane().add(departmentLabel);
		String[] departments = { "CSE", "IT", "MECHANICAL", "ELECTRICAL", "CIVIL", "ECE", "MBA", "BCA", "MCA" };
		departmentBox = new JComboBox<>(departments);
		departmentBox.setFont(new Font("Tahoma", Font.BOLD, 12));
		departmentBox.setBounds(650, 420, 280, 35);
		getContentPane().add(departmentBox);

		enrollmentLabel = new JLabel("Enrollment No:");
		enrollmentLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		enrollmentLabel.setBounds(450, 470, 150, 30);
		getContentPane().add(enrollmentLabel);
		enrollmentField = new JTextField();
		enrollmentField.setFont(new Font("Tahoma", Font.BOLD, 12));
		enrollmentField.setBounds(650, 470, 280, 35);
		getContentPane().add(enrollmentField);

		registerButton = new JButton("Register");
		registerButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		registerButton.setBounds(520, 570, 160, 45);
		registerButton.addActionListener(this);
		getContentPane().add(registerButton);

		backButton = new JButton("Back to Login");
		backButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		backButton.setBounds(730, 570, 180, 45);
		backButton.addActionListener(this);
		getContentPane().add(backButton);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == registerButton) {
			registerUser();
		}

		if (e.getSource() == backButton) {
			dispose();
			new LoginPage();
		}

	}

	private void registerUser() {
		String name = nameField.getText();
		String email = emailField.getText();
		String username = usernameField.getText();
		String password = String.valueOf(passwordField.getPassword());
		String confirmPassword = String.valueOf(confirmPasswordField.getPassword());
		String phone = phoneField.getText();
		String department = departmentBox.getSelectedItem().toString();
		String enrollment = enrollmentField.getText();

		if (name.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()
				|| phone.isEmpty() || enrollment.isEmpty()) {
			JOptionPane.showMessageDialog(this, "All fields are required!");
			return;
		}
		
		if (!email.contains("@") || !email.contains(".")) {
			JOptionPane.showMessageDialog(this, "Invalid Email Format!");
			return;
		}

		
		if (!password.equals(confirmPassword)) {
			JOptionPane.showMessageDialog(this, "Passwords do not match!");
			return;
		}

		if (enrollment.length() != 4) {
			JOptionPane.showMessageDialog(this, "Enrollment number must be 4 digits!");
			return;
		}

		try {

			Connection con = DatabaseUtil.getConnection();

			PreparedStatement checkPs = con.prepareStatement("SELECT * FROM users WHERE username=?");
			checkPs.setString(1, username);
			ResultSet rs = checkPs.executeQuery();
			if (rs.next()) {
				JOptionPane.showMessageDialog(this, "Username already exists!");
				return;
			}

			String insertQuery = "INSERT INTO users(name,email,username,password,role,phone,department,enrollment_no,fine,books_taken,books_due) VALUES(?,?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement ps = con.prepareStatement(insertQuery);
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, username);
			ps.setString(4, password);
			ps.setString(5, "USER");
			ps.setString(6, phone);
			ps.setString(7, department);
			ps.setString(8, enrollment);
			ps.setDouble(9, 0.0);
			ps.setInt(10, 0);
			ps.setInt(11, 0);

			int rows = ps.executeUpdate();

			if (rows > 0) {
				JOptionPane.showMessageDialog(this, "Registration Successful!");
				clearFields();
				dispose();
				new LoginPage();
			}

			rs.close();
			checkPs.close();
			ps.close();
			con.close();

		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, "Database Error!");
		}

	}

	private void clearFields() {
		nameField.setText("");
		emailField.setText("");
		usernameField.setText("");
		passwordField.setText("");
		confirmPasswordField.setText("");
		phoneField.setText("");
		enrollmentField.setText("");
	}

}
