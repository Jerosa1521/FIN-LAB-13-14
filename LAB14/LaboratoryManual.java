import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class LaboratoryManual {

    private JTextField nameField;
    private JTextField emailField;
    private JTextArea displayArea;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LaboratoryManual().createAndShowGUI();
        });
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Laboratory Manual");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel titleLabel = new JLabel("Database Management System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        titleLabel.setBounds(50, 10, 300, 30);
        frame.add(titleLabel);

        // User Details Panel
        JPanel userPanel = new JPanel(null);
        userPanel.setBorder(BorderFactory.createTitledBorder("User Details"));
        userPanel.setBounds(40, 50, 300, 100);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(10, 20, 80, 25);
        userPanel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(90, 20, 180, 25);
        userPanel.add(nameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 55, 80, 25);
        userPanel.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(90, 55, 180, 25);
        userPanel.add(emailField);

        frame.add(userPanel);

        // Operations Panel
        JPanel buttonPanel = new JPanel(null);
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Operations"));
        buttonPanel.setBounds(40, 160, 300, 190);

        JButton addButton = new JButton("Add User");
        addButton.setBounds(90, 20, 120, 25);
        buttonPanel.add(addButton);

        JButton showButton = new JButton("Show Records");
        showButton.setBounds(90, 50, 120, 25);
        buttonPanel.add(showButton);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(90, 80, 120, 25);
        buttonPanel.add(searchButton);

        JButton editButton = new JButton("Edit");
        editButton.setBounds(90, 110, 120, 25);
        buttonPanel.add(editButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(90, 140, 120, 25);
        buttonPanel.add(deleteButton);

        frame.add(buttonPanel);

        // Display Area
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBounds(40, 360, 300, 90);
        frame.add(scrollPane);

        // Button Actions
        addButton.addActionListener(e -> addUser());
        showButton.addActionListener(e -> showRecords());
        searchButton.addActionListener(e -> searchRecord());
        editButton.addActionListener(e -> editRecord());
        deleteButton.addActionListener(e -> deleteRecord());

        frame.setResizable(false);
        frame.setVisible(true);
    }

    private Connection connect() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/myoop";
        String user = "root";
        String password = "";
        return DriverManager.getConnection(url, user, password);
    }

    private void addUser() {
        try (Connection con = connect()) {
            String sql = "INSERT INTO users (name, email) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nameField.getText());
            ps.setString(2, emailField.getText());
            ps.executeUpdate();
            displayArea.setText("User added successfully!");
        } catch (Exception e) {
            displayArea.setText("Error: " + e.getMessage());
        }
    }

    private void showRecords() {
        try (Connection con = connect()) {
            String sql = "SELECT * FROM users";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                sb.append("ID: ").append(rs.getInt("id"))
                        .append(" | Name: ").append(rs.getString("name"))
                        .append(" | Email: ").append(rs.getString("email"))
                        .append("\n");
            }
            displayArea.setText(sb.toString());
        } catch (Exception e) {
            displayArea.setText("Error: " + e.getMessage());
        }
    }

    private void searchRecord() {
        try (Connection con = connect()) {
            String sql = "SELECT * FROM users WHERE name = ? OR email = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nameField.getText());
            ps.setString(2, emailField.getText());
            ResultSet rs = ps.executeQuery();
            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                sb.append("ID: ").append(rs.getInt("id"))
                        .append(" | Name: ").append(rs.getString("name"))
                        .append(" | Email: ").append(rs.getString("email"))
                        .append("\n");
            }
            displayArea.setText(sb.length() > 0 ? sb.toString() : "No record found.");
        } catch (Exception e) {
            displayArea.setText("Error: " + e.getMessage());
        }
    }

    private void editRecord() {
        try (Connection con = connect()) {
            String sql = "UPDATE users SET name = ?, email = ? WHERE email = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nameField.getText());
            ps.setString(2, emailField.getText());
            ps.setString(3, emailField.getText());
            int rows = ps.executeUpdate();
            displayArea.setText(rows > 0 ? "User updated successfully!" : "No user found.");
        } catch (Exception e) {
            displayArea.setText("Error: " + e.getMessage());
        }
    }

    private void deleteRecord() {
        try (Connection con = connect()) {
            String sql = "DELETE FROM users WHERE email = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, emailField.getText());
            int rows = ps.executeUpdate();
            displayArea.setText(rows > 0 ? "User deleted successfully!" : "No user found.");
        } catch (Exception e) {
            displayArea.setText("Error: " + e.getMessage());
        }
    }
}