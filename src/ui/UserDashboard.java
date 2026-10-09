package ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.DatabaseUtil;

import java.sql.*;

public class UserDashboard extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	JLabel titleLabel;

	JLabel nameLabel, emailLabel, usernameLabel, phoneLabel, departmentLabel, enrollmentLabel, fineLabel,
			booksTakenLabel, booksDueLabel;

	JLabel nameValue, emailValue, usernameValue, phoneValue, departmentValue, enrollmentValue, fineValue,
			booksTakenValue, booksDueValue;

	JButton payFineButton, logoutButton;

	String currentUsername;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserDashboard frame = new UserDashboard("testuser");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public UserDashboard(String username) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.currentUsername = username;

		initializeUI();

		loadUserData();

		setVisible(true);
	}

	private void loadUserData() {
		try {
			Connection con = DatabaseUtil.getConnection();
			String query = "SELECT * FROM users WHERE username=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, currentUsername);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				nameValue.setText(rs.getString("name"));
				emailValue.setText(rs.getString("email"));
				usernameValue.setText(rs.getString("username"));
				phoneValue.setText(rs.getString("phone"));
				departmentValue.setText(rs.getString("department"));
				enrollmentValue.setText(rs.getString("enrollment_no"));
				fineValue.setText("₹ " + rs.getDouble("fine"));
				booksTakenValue.setText(String.valueOf(rs.getInt("books_taken")));
				booksDueValue.setText(String.valueOf(rs.getInt("books_due")));
			}
			rs.close();
			ps.close();
			con.close();

		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error Loading User Data");
		}

	}

	private void initializeUI() {
		setTitle("Library Management System - User Dashboard");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setExtendedState(JFrame.MAXIMIZED_BOTH);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(245, 245, 245));

		setContentPane(contentPane);

		titleLabel = new JLabel("USER DASHBOARD");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
		titleLabel.setBounds(580, 40, 400, 40);
		contentPane.add(titleLabel);

		Font labelFont = new Font("Tahoma", Font.BOLD, 16);

		nameLabel = createLabel("Full Name:", 450, 140);
		emailLabel = createLabel("Email:", 450, 190);
		usernameLabel = createLabel("Username:", 450, 240);
		phoneLabel = createLabel("Phone:", 450, 290);
		departmentLabel = createLabel("Department:", 450, 340);
		enrollmentLabel = createLabel("Enrollment No:", 450, 390);
		fineLabel = createLabel("Current Fine:", 450, 440);
		booksTakenLabel = createLabel("Books Taken:", 450, 490);
		booksDueLabel = createLabel("Books Due:", 450, 540);

		nameValue = createValueLabel(700, 140);
		emailValue = createValueLabel(700, 190);
		usernameValue = createValueLabel(700, 240);
		phoneValue = createValueLabel(700, 290);
		departmentValue = createValueLabel(700, 340);
		enrollmentValue = createValueLabel(700, 390);
		fineValue = createValueLabel(700, 440);
		booksTakenValue = createValueLabel(700, 490);
		booksDueValue = createValueLabel(700, 540);

		payFineButton = new JButton("Pay Fine");
		payFineButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		payFineButton.setBounds(540, 650, 170, 45);
		payFineButton.addActionListener(this);
		contentPane.add(payFineButton);

		logoutButton = new JButton("Logout");
		logoutButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		logoutButton.setBounds(760, 650, 170, 45);
		logoutButton.addActionListener(this);
		contentPane.add(logoutButton);

		setVisible(true);
	}

	private JLabel createLabel(String text, int x, int y) {
		JLabel label = new JLabel(text);
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		label.setBounds(x, y, 220, 30);
		contentPane.add(label);

		return label;
	}

	private JLabel createValueLabel(int x, int y) {
		JLabel label = new JLabel();
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label.setBounds(x, y, 350, 30);
		contentPane.add(label);

		return label;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == payFineButton) {
			
			showPaymentDialog();
			
//			try {
//				Connection con = DatabaseUtil.getConnection();
//				String updateQuery = "UPDATE users SET fine=0 WHERE username=?";
//				PreparedStatement ps = con.prepareStatement(updateQuery);
//				ps.setString(1, currentUsername);
//				int rows = ps.executeUpdate();
//
//				if (rows > 0) {
//					JOptionPane.showMessageDialog(this, "Fine Paid Successfully!");
//					fineValue.setText("₹ 0.0");
//				}
//				ps.close();
//				con.close();
//
//			} catch (Exception ex) {
//				ex.printStackTrace();
//				JOptionPane.showMessageDialog(this, "Payment Failed!");
//			}
		}

		if (e.getSource() == logoutButton) {
			dispose();
			new LoginPage();
		}
	}

	private void showPaymentDialog() {
		JDialog dialog = new JDialog(this, "Pay Fine", true);
		dialog.setSize(400, 450);
		dialog.setLayout(null);
		dialog.setLocationRelativeTo(this);

		JLabel title = new JLabel("PAYMENT DETAILS");
		title.setFont(new Font("Arial", Font.BOLD, 18));
		title.setBounds(110, 20, 250, 30);
		dialog.add(title);

		JLabel upiLabel = new JLabel("UPI ID: library@upi");
		upiLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		upiLabel.setBounds(120, 70, 250, 30);
		dialog.add(upiLabel);

		JLabel amountLabel = new JLabel("Amount: " + fineValue.getText());
		amountLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		amountLabel.setBounds(120, 110, 250, 30);
		dialog.add(amountLabel);

//		ImageIcon qrIcon = new ImageIcon("/pay.png");
		java.net.URL imageURL =
		        getClass().getResource("/images/pay.png");
		if (imageURL == null) {
		    JOptionPane.showMessageDialog(this,
		            "Image Not Found!");
		    return;
		}
		
		ImageIcon qrIcon = new ImageIcon(imageURL);
		Image scaledImage =
		        qrIcon.getImage().getScaledInstance(
		                200,
		                200,
		                Image.SCALE_SMOOTH
		        );

		JLabel qrLabel = new JLabel(new ImageIcon(scaledImage));
		qrLabel.setBounds(100, 150, 200, 200);
		dialog.add(qrLabel);

		JButton payButton = new JButton("Done");
		payButton.setBounds(120, 370, 150, 30);
		payButton.addActionListener(e -> {

			JOptionPane.showMessageDialog(dialog, "Payment Recorded Successfully!");

			dialog.dispose();

			clearFineAfterPayment();
		});

		dialog.add(payButton);

		dialog.setVisible(true);
		
	}

	private void clearFineAfterPayment() {
		try {

			Connection con = DatabaseUtil.getConnection();
			PreparedStatement ps = con.prepareStatement("UPDATE users SET fine=0 WHERE username=?");
			ps.setString(1, currentUsername);
			ps.executeUpdate();
			fineValue.setText("₹ 0.0");

			ps.close();
			con.close();

		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error updating fine!");
		}
		
	}

}
