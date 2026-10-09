package ui;

import java.awt.EventQueue;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.JButton;
import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import database.DatabaseUtil;

public class AdminDashboard extends JFrame implements ActionListener {

	JPanel sidePanel, topPanel, mainPanel;

	JLabel titleLabel, welcomeLabel;

	JButton addBookBtn, manageBookBtn, searchBookBtn, issueBookBtn, returnBookBtn, fineBtn, usersBtn, studentsBtn,
			logoutBtn, editStudentBtn;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton ja;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminDashboard frame = new AdminDashboard();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AdminDashboard() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = screenSize.width;
		int height = screenSize.height;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		setTitle("Admin Dashboard - Library Management System");
//		setSize(1100, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(false);
		setLocationRelativeTo(null);

		// Top panel
		topPanel = new JPanel();
		topPanel.setBounds(0, 0, width, 70);
		topPanel.setBackground(new Color(25, 118, 210));
		topPanel.setLayout(null);
		getContentPane().add(topPanel);

		titleLabel = new JLabel("LIBRARY MANAGEMENT SYSTEM - ADMIN");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setBounds(250, 20, 600, 30);
		topPanel.add(titleLabel);

		// Side Panel
		sidePanel = new JPanel();
		sidePanel.setBounds(0, 70, 250, height - 70);
		sidePanel.setBackground(new Color(33, 33, 33));
		sidePanel.setLayout(null);
		getContentPane().add(sidePanel);

		// Btns
		addBookBtn = createButton("Add Book", 30);
		manageBookBtn = createButton("Manage Books", 90);
		searchBookBtn = createButton("Search Books", 150);
		issueBookBtn = createButton("Issue Book", 210);
		returnBookBtn = createButton("View Issued Books", 270);
		fineBtn = createButton("Fine Management", 330);
		studentsBtn = createButton("Students Data", 390);
		editStudentBtn = createButton("Edit Student", 450);
		logoutBtn = createButton("Logout", 520);

		sidePanel.add(addBookBtn);
		sidePanel.add(manageBookBtn);
		sidePanel.add(searchBookBtn);
		sidePanel.add(issueBookBtn);
		sidePanel.add(returnBookBtn);
		sidePanel.add(fineBtn);
		sidePanel.add(studentsBtn);
		sidePanel.add(editStudentBtn);
		sidePanel.add(logoutBtn);

//        addBookBtn = new JButton("Add Book");
//        addBookBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
//        addBookBtn.setBounds(30, 30, 190, 40);
//        addBookBtn.setBackground(new Color(66, 66, 66));
//        addBookBtn.setForeground(new Color(255, 255, 255));
//        addBookBtn.setFocusPainted(false);
//        addBookBtn.addActionListener(this);
//        sidePanel.add(addBookBtn);

		// main
		mainPanel = new JPanel();
		mainPanel.setBounds(250, 70, width - 250, height - 70);
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setLayout(null);
		add(mainPanel);

		welcomeLabel = new JLabel("WELCOME ADMIN");
		welcomeLabel.setFont(new Font("Arial", Font.BOLD, 36));
		welcomeLabel.setBounds(250, 200, 400, 50);
		mainPanel.add(welcomeLabel);

		setVisible(true);
	}

	private JButton createButton(String text, int y) {
		JButton btn = new JButton(text);
		btn.setBounds(30, y, 190, 40);
		btn.setFont(new Font("Arial", Font.BOLD, 14));
		btn.setBackground(new Color(66, 66, 66));
		btn.setForeground(Color.WHITE);
		btn.setFocusPainted(false);
		btn.addActionListener(this);
		return btn;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addBookBtn) {
			AddBookPanel p = new AddBookPanel();
			p.setBounds(0, 0, 850, 630);

			mainPanel.removeAll();
			mainPanel.add(p);
			mainPanel.revalidate();
			mainPanel.repaint();
		}

		if (e.getSource() == manageBookBtn) {
			showBooksData();
		}

		if (e.getSource() == searchBookBtn) {
			showSearchBooksPanel();
		}

		if (e.getSource() == issueBookBtn) {
			mainPanel.removeAll();

			IssueBookPanel p = new IssueBookPanel();
			p.setBounds(0, 0, 850, 630);
			mainPanel.add(p);
			mainPanel.revalidate();
			mainPanel.repaint();
		}

		if (e.getSource() == returnBookBtn) {
			showIssuedBooks();
		}

		if (e.getSource() == fineBtn) {
			showFineManagement();
		}

		if (e.getSource() == usersBtn) {
			JOptionPane.showMessageDialog(this, "Open Users Page");
		}

		if (e.getSource() == studentsBtn) {
			showStudentsData();
		}
		if (e.getSource() == editStudentBtn) {
			showEditStudentPanel();
		}

		if (e.getSource() == logoutBtn) {
			int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout",
					JOptionPane.YES_NO_OPTION);

			if (confirm == JOptionPane.YES_OPTION) {
				dispose();
				new LoginPage();
			}
		}

	}

	private void showEditStudentPanel() {

		mainPanel.removeAll();
		mainPanel.setLayout(null);
		
		JLabel heading = new JLabel("Edit Student Data");
		heading.setFont(new Font("Arial", Font.BOLD, 22));
		heading.setBounds(300, 20, 300, 30);
		mainPanel.add(heading);

		JLabel idLabel = new JLabel("Student ID:");
		idLabel.setBounds(200, 80, 100, 25);
		mainPanel.add(idLabel);

		JTextField idField = new JTextField();
		idField.setBounds(320, 80, 200, 25);
		mainPanel.add(idField);

		JLabel fineLabel = new JLabel("Fine:");
		fineLabel.setBounds(200, 130, 100, 25);
		mainPanel.add(fineLabel);

		JTextField fineField = new JTextField();
		fineField.setBounds(320, 130, 200, 25);
		mainPanel.add(fineField);

		JLabel takenLabel = new JLabel("Books Taken:");
		takenLabel.setBounds(200, 180, 100, 25);
		mainPanel.add(takenLabel);

		JTextField takenField = new JTextField();
		takenField.setBounds(320, 180, 200, 25);
		mainPanel.add(takenField);

		JLabel dueLabel = new JLabel("Books Due:");
		dueLabel.setBounds(200, 230, 100, 25);
		mainPanel.add(dueLabel);

		JTextField dueField = new JTextField();
		dueField.setBounds(320, 230, 200, 25);
		mainPanel.add(dueField);

		JButton loadBtn = new JButton("Load Student");
		loadBtn.setBounds(550, 80, 150, 25);
		mainPanel.add(loadBtn);

		JButton updateBtn = new JButton("Update");
		updateBtn.setBounds(320, 300, 150, 30);
		mainPanel.add(updateBtn);

		loadBtn.addActionListener(e -> {

			try {
				Connection con = DatabaseUtil.getConnection();
				PreparedStatement ps = con
						.prepareStatement("SELECT fine, books_taken, books_due FROM users WHERE id=?");
				ps.setInt(1, Integer.parseInt(idField.getText()));
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {

					fineField.setText(String.valueOf(rs.getDouble("fine")));
					takenField.setText(String.valueOf(rs.getInt("books_taken")));
					dueField.setText(String.valueOf(rs.getInt("books_due")));
				} else {
					JOptionPane.showMessageDialog(null, "Student not found!");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});

		updateBtn.addActionListener(e -> {
			try {
				Connection con = DatabaseUtil.getConnection();
				PreparedStatement ps = con
						.prepareStatement("UPDATE users SET fine=?, books_taken=?, books_due=? WHERE id=?");
				ps.setDouble(1, Double.parseDouble(fineField.getText()));
				ps.setInt(2, Integer.parseInt(takenField.getText()));
				ps.setInt(3, Integer.parseInt(dueField.getText()));
				ps.setInt(4, Integer.parseInt(idField.getText()));
				int rows = ps.executeUpdate();

				if (rows > 0) {
					JOptionPane.showMessageDialog(null, "Student Updated Successfully!");
				}
				
				idField.setText("");
				fineField.setText("");
				takenField.setText("");
				dueField.setText("");
				

			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error updating student!");
			}
		});
		mainPanel.revalidate();
		mainPanel.repaint();
	}

	private void showFineManagement() {

		mainPanel.removeAll();
		mainPanel.setLayout(new BorderLayout());

		JPanel topPanel = new JPanel();

		JLabel heading = new JLabel("Students With Pending Fine");
		heading.setFont(new Font("Arial", Font.BOLD, 22));

		topPanel.add(heading);

		mainPanel.add(topPanel, BorderLayout.NORTH);
		String[] columns = { "ID", "Name", "Username", "Phone", "Books Due", "Fine Amount" };
		DefaultTableModel model = new DefaultTableModel(columns, 0);
		JTable table = new JTable(model);

		try {
			Connection con = DatabaseUtil.getConnection();
			String query = "SELECT id, name, username, phone, books_due, fine "
					+ "FROM users WHERE role='USER' AND fine > 0";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
			model.addRow(new Object[] { rs.getInt("id"), rs.getString("name"), rs.getString("username"),
						rs.getString("phone"), rs.getInt("books_due"), rs.getDouble("fine") });
			}
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error loading fine data!");
		}

		JScrollPane sp = new JScrollPane(table);
		mainPanel.add(sp, BorderLayout.CENTER);
		mainPanel.revalidate();
		mainPanel.repaint();
	}

	private void showSearchBooksPanel() {
		mainPanel.removeAll();
		mainPanel.setLayout(new BorderLayout());

		JPanel top = new JPanel();
		top.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));

		JTextField titleField = new JTextField(15);
		JTextField categoryField = new JTextField(15);

		JButton searchBtn = new JButton("Search");

		top.add(new JLabel("Title:"));
		top.add(titleField);

		top.add(new JLabel("Category:"));
		top.add(categoryField);

		top.add(searchBtn);

		mainPanel.add(top, BorderLayout.NORTH);

		String[] columns = { "Book ID", "Title", "Author", "Category", "Quantity", "Available", "Shelf" };

		DefaultTableModel model = new DefaultTableModel(columns, 0);
		JTable table = new JTable(model);

		JScrollPane sp = new JScrollPane(table);
		mainPanel.add(sp, BorderLayout.CENTER);

		searchBtn.addActionListener(e -> {

			model.setRowCount(0);

			try {
				Connection con = DatabaseUtil.getConnection();
				String query = "SELECT * FROM books WHERE title LIKE ? AND category LIKE ?";
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1, "%" + titleField.getText() + "%");
				ps.setString(2, "%" + categoryField.getText() + "%");
				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					model.addRow(new Object[] { rs.getInt("book_id"), rs.getString("title"), rs.getString("author"),
							rs.getString("category"), rs.getInt("quantity"), rs.getInt("available_quantity"),
							rs.getString("shelf_number") });
				}

			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this, "Error searching books!");
			}
		});

		mainPanel.revalidate();
		mainPanel.repaint();
	}

	private void showIssuedBooks() {

		mainPanel.removeAll();

		String[] columns = { "Issue ID", "User ID", "Book ID", "Issue Date", "Return Date", "Status" };
		DefaultTableModel model = new DefaultTableModel(columns, 0);
		JTable table = new JTable(model);

		try {
			Connection con = DatabaseUtil.getConnection();
			String query = "SELECT * FROM issued_books";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				model.addRow(new Object[] { rs.getInt("issue_id"), rs.getInt("user_id"), rs.getInt("book_id"),
						rs.getTimestamp("issue_date"), rs.getDate("return_date"), rs.getString("status") });
			}
			con.close();

		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error loading issued books!");
		}

		JScrollPane sp = new JScrollPane(table);
		sp.setPreferredSize(new Dimension(mainPanel.getWidth(), mainPanel.getHeight()));
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(sp, BorderLayout.CENTER);
		mainPanel.revalidate();
		mainPanel.repaint();
	}

	private void showBooksData() {
		mainPanel.removeAll();

		String[] columns = { "Book ID", "Title", "Author", "Category", "Qty", "Available", "Shelf" };
		DefaultTableModel model = new DefaultTableModel(columns, 0);
		JTable table = new JTable(model);

		try {
			Connection con = DatabaseUtil.getConnection();
			String query = "SELECT * FROM books";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				model.addRow(new Object[] { rs.getInt("book_id"), rs.getString("title"), rs.getString("author"),
						rs.getString("category"), rs.getInt("quantity"), rs.getInt("available_quantity"),
						rs.getString("shelf_number") });
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error loading books!");
		}

		JScrollPane sp = new JScrollPane(table);
		sp.setPreferredSize(new Dimension(mainPanel.getWidth(), mainPanel.getHeight()));
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(sp, BorderLayout.CENTER);
		mainPanel.revalidate();
		mainPanel.repaint();

	}

	private void showStudentsData() {

    mainPanel.removeAll();
    mainPanel.setLayout(new BorderLayout());

    String[] columns = {
            "ID",
            "Name",
            "Email",
            "Username",
            "Phone",
            "Department",
            "Enroll No",
            "Books Taken",
            "Books Due",
            "Fine"
    };

    DefaultTableModel model = new DefaultTableModel(columns, 0);

    JTable table = new JTable(model);

    table.setRowHeight(25);
    
    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

    table.getColumnModel().getColumn(0).setPreferredWidth(50);
    table.getColumnModel().getColumn(1).setPreferredWidth(150);
    table.getColumnModel().getColumn(2).setPreferredWidth(200);
    table.getColumnModel().getColumn(3).setPreferredWidth(120);
    table.getColumnModel().getColumn(4).setPreferredWidth(120);
    table.getColumnModel().getColumn(5).setPreferredWidth(150);
    table.getColumnModel().getColumn(6).setPreferredWidth(120);
    table.getColumnModel().getColumn(7).setPreferredWidth(100);
    table.getColumnModel().getColumn(8).setPreferredWidth(100);
    table.getColumnModel().getColumn(9).setPreferredWidth(80);
    
    try {

        Connection con = DatabaseUtil.getConnection();

        String query =
                "SELECT id, name, email, username, phone, department, enrollment_no, books_taken, books_due, fine " +
                "FROM users WHERE role='USER'";

        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {

            model.addRow(new Object[] {

                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("username"),
                    rs.getString("phone"),
                    rs.getString("department"),
                    rs.getString("enrollment_no"),
                    rs.getInt("books_taken"),
                    rs.getInt("books_due"),
                    rs.getDouble("fine")
            });
        }

        con.close();

    } catch (Exception e) {

        e.printStackTrace();

        JOptionPane.showMessageDialog(this,
                "Error loading students data!");
    }

    JScrollPane sp = new JScrollPane(table);

    // BOTH scrollbars enabled
    sp.setVerticalScrollBarPolicy(
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    sp.setHorizontalScrollBarPolicy(
            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

    mainPanel.add(sp, BorderLayout.CENTER);

    mainPanel.revalidate();
    mainPanel.repaint();
}
}
