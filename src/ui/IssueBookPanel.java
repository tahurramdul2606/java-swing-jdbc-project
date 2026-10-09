package ui;

import java.awt.*;
import java.awt.EventQueue;
import java.sql.*;

import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import database.DatabaseUtil;

public class IssueBookPanel extends JPanel {

	JTextField userIdField, bookIdField;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IssueBookPanel frame = new IssueBookPanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public IssueBookPanel() {

		setLayout(null);
		setBackground(Color.WHITE);

		JLabel heading = new JLabel("ISSUE BOOK");
		heading.setFont(new Font("Arial", Font.BOLD, 28));
		heading.setBounds(320, 20, 200, 30);
		add(heading);

		addLabel("User ID", 120);
		userIdField = addField(120);

		addLabel("Book ID", 180);
		bookIdField = addField(180);

		JButton issueBtn = new JButton("Issue Book");
		issueBtn.setBounds(350, 260, 150, 35);
		add(issueBtn);

		issueBtn.addActionListener(e -> issueBook());
		setVisible(true);
	}

	private void issueBook() {
		try {
			Connection con = DatabaseUtil.getConnection();
			int userId = Integer.parseInt(userIdField.getText());
			int bookId = Integer.parseInt(bookIdField.getText());

			PreparedStatement check = con.prepareStatement("SELECT available_quantity FROM books WHERE book_id=?");
			check.setInt(1, bookId);
			ResultSet rs = check.executeQuery();

			if (rs.next()) {
				int available = rs.getInt(1);
				if (available <= 0) {
					JOptionPane.showMessageDialog(this, "Book Not Available!");
					return ;
				}

				PreparedStatement updateBook = con.prepareStatement(
						"UPDATE books SET available_quantity = available_quantity - 1 WHERE book_id=?");

				updateBook.setInt(1, bookId);
				updateBook.executeUpdate();

				PreparedStatement updateUser = con.prepareStatement(
						"UPDATE users SET books_taken = books_taken + 1, books_due = books_due + 1 WHERE id=?");

				updateUser.setInt(1, userId);

				updateUser.executeUpdate();

				PreparedStatement insert = con.prepareStatement(
						"INSERT INTO issued_books(user_id,book_id,return_date,status) VALUES(?,?,DATE_ADD(CURDATE(), INTERVAL 7 DAY),'ISSUED')");

				insert.setInt(1, userId);
				insert.setInt(2, bookId);

				insert.executeUpdate();

				JOptionPane.showMessageDialog(this, "Book Issued Successfully!");

				userIdField.setText("");
				bookIdField.setText("");
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error issuing book!");
		}
	}

	

	private JTextField addField(int y) {
		JTextField f = new JTextField();
		f.setBounds(350, y, 200, 25);
		add(f);
		return f;
	}

	private void addLabel(String text, int y) {
		JLabel l = new JLabel(text);
		l.setBounds(250, y, 100, 25);
		add(l);
	}

}
