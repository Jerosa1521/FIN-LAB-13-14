import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class InventoryManagementSystem extends JFrame {
    private Map<String, Integer> inventory;

    private JTextField itemNameTextField;
    private JTextField quantityTextField;
    private JButton addItemButton;
    private JButton updateQuantityButton;
    private JTextArea inventoryListTextArea;

    public InventoryManagementSystem() {
        setTitle("Inventory Management System");
        setLayout(new BorderLayout());

        inventory = new HashMap<>();

        JPanel inputPanel = new JPanel(new FlowLayout());
        itemNameTextField = new JTextField(20);
        inputPanel.add(new JLabel("Item Name:"));
        inputPanel.add(itemNameTextField);
        quantityTextField = new JTextField(5);
        inputPanel.add(new JLabel("Quantity:"));
        inputPanel.add(quantityTextField);
        addItemButton = new JButton("Add Item");
        addItemButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String itemName = itemNameTextField.getText().trim();
                int quantity = Integer.parseInt(quantityTextField.getText().trim());
                if (!itemName.isEmpty()) {
                    inventory.put(itemName, quantity);
                    updateInventoryList();
                    itemNameTextField.setText("");
                    quantityTextField.setText("");
                }
            }
        });
        inputPanel.add(addItemButton);
        updateQuantityButton = new JButton("Update Quantity");
        updateQuantityButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String itemName = itemNameTextField.getText().trim();
                int quantity = Integer.parseInt(quantityTextField.getText().trim());
                if (!itemName.isEmpty() && inventory.containsKey(itemName)) {
                    inventory.put(itemName, quantity);
                    updateInventoryList();
                    itemNameTextField.setText("");
                    quantityTextField.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Item not found.");
                }
            }
        });
        inputPanel.add(updateQuantityButton);
        add(inputPanel, BorderLayout.NORTH);

        inventoryListTextArea = new JTextArea(20, 40);
        inventoryListTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(inventoryListTextArea);
        add(scrollPane, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void updateInventoryList() {
        inventoryListTextArea.setText("");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            inventoryListTextArea.append(entry.getKey() + " - Quantity: " + entry.getValue() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new InventoryManagementSystem();
            }
        });
    }
}
