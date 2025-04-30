import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class LibraryManagementSystem extends JFrame {
    private Map<String, Boolean> books;
    private JTextField bookTitleTextField;
    private JButton addBookButton;
    private JButton checkoutBookButton;
    private JTextArea bookListTextArea;

    public LibraryManagementSystem() {
        setTitle("Library Management System");
        setLayout(new BorderLayout());
        books = new HashMap<>();

        JPanel inputPanel = new JPanel(new FlowLayout());
        bookTitleTextField = new JTextField(20);
        inputPanel.add(new JLabel("Book Title:"));
        inputPanel.add(bookTitleTextField);
        addBookButton = new JButton("Add Book");
        addBookButton.addActionListener(e -> {
            String title = bookTitleTextField.getText().trim();
            if (!title.isEmpty()) {
                books.put(title, true);
                updateBookList();
                bookTitleTextField.setText("");
            }
        });
        inputPanel.add(addBookButton);
        checkoutBookButton = new JButton("Checkout Book");
        checkoutBookButton.addActionListener(e -> {
            String title = bookTitleTextField.getText().trim();
            if (!title.isEmpty() && books.containsKey(title) && books.get(title)) {
                books.put(title, false);
                updateBookList();
                bookTitleTextField.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Book not available or does not exist.");
            }
        });
        inputPanel.add(checkoutBookButton);
        add(inputPanel, BorderLayout.NORTH);

        bookListTextArea = new JTextArea(20, 40);
        bookListTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(bookListTextArea);
        add(scrollPane, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void updateBookList() {
        bookListTextArea.setText("");
        for (Map.Entry<String, Boolean> entry : books.entrySet()) {
            String status = entry.getValue() ? "Available" : "Checked Out";
            bookListTextArea.append(entry.getKey() + " - " + status + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LibraryManagementSystem::new);
    }
}
