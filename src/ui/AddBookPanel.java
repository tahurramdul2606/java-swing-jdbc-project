package ui;

import java.awt.Color;
import java.sql.*;
import java.awt.*;

import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import database.DatabaseUtil;

public class AddBookPanel extends JPanel {

	JTextField title, author, category, qty, shelf;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBookPanel frame = new AddBookPanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AddBookPanel() {

		setLayout(null);
		setBackground(Color.WHITE);

		JLabel heading = new JLabel("ADD BOOK");
		heading.setFont(new Font("Arial", Font.BOLD, 28));
		heading.setBounds(320, 20, 200, 30);
		add(heading);

		addLabel("Title", 100);
		title = addField(100);

		addLabel("Author", 150);
		author = addField(150);

		addLabel("Category", 200);
		category = addField(200);

		addLabel("Quantity", 250);
		qty = addField(250);

		addLabel("Shelf No", 300);
		shelf = addField(300);

		JButton save = new JButton("Save Book");
		save.setBounds(350, 370, 150, 35);

		add(save);

		save.addActionListener(e -> saveBook());
		setVisible(true);
	}

	private void addLabel(String text, int y) {

		JLabel l = new JLabel(text);
		l.setBounds(250, y, 120, 25);
		add(l);
	}

	private JTextField addField(int y) {

		JTextField f = new JTextField();
		f.setBounds(350, y, 200, 25);
		add(f);
		return f;
	}

	void saveBook() {

		try {
			Connection con = DatabaseUtil.getConnection();
			String sql = "INSERT INTO books(title,author,category,quantity,available_quantity,shelf_number) VALUES(?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			int q = Integer.parseInt(qty.getText());

			ps.setString(1, title.getText());
			ps.setString(2, author.getText());
			ps.setString(3, category.getText());
			ps.setInt(4, q);
			ps.setInt(5, q);
			ps.setString(6, shelf.getText());

			ps.executeUpdate();

			JOptionPane.showMessageDialog(this, "Book Added Successfully!");

			title.setText("");
			author.setText("");
			category.setText("");
			qty.setText("");
			shelf.setText("");

		} catch (Exception e) {

			e.printStackTrace();

			JOptionPane.showMessageDialog(this, "Error adding book!");
		}
	}

}
