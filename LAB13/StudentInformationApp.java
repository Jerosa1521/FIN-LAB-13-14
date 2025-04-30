import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentInformationApp {
    private JFrame frame;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField middleInitialTextField;
    private JTextField courseTextField;
    private JTextField yearTextField;

    public StudentInformationApp() {
        frame = new JFrame("Student Information App");
        frame.setLayout(new GridLayout(0, 2));
        
        frame.add(new JLabel("First Name:"));
        firstNameTextField = new JTextField();
        frame.add(firstNameTextField);
        
        frame.add(new JLabel("Last Name:"));
        lastNameTextField = new JTextField();
        frame.add(lastNameTextField);
        
        frame.add(new JLabel("Middle Initial:"));
        middleInitialTextField = new JTextField();
        frame.add(middleInitialTextField);
        
        frame.add(new JLabel("Course:"));
        courseTextField = new JTextField();
        frame.add(courseTextField);
        
        frame.add(new JLabel("Year:"));
        yearTextField = new JTextField();
        frame.add(yearTextField);
        
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameTextField.getText();
                String lastName = lastNameTextField.getText();
                String middleInitial = middleInitialTextField.getText();
                String course = courseTextField.getText();
                String year = yearTextField.getText();
                
                JFrame displayFrame = new JFrame("Submitted Information");
                displayFrame.setLayout(new FlowLayout());
                displayFrame.setSize(300, 200);
                displayFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                
                JLabel displayLabel = new JLabel("Name: " + firstName + " " + middleInitial + ". " + lastName);
                JLabel courseLabel = new JLabel("Course: " + course);
                JLabel yearLabel = new JLabel("Year: " + year);
                
                displayFrame.add(displayLabel);
                displayFrame.add(courseLabel);
                displayFrame.add(yearLabel);
                
                displayFrame.setVisible(true);
            }
        });
        frame.add(submitButton);
        
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                firstNameTextField.setText("");
                lastNameTextField.setText("");
                middleInitialTextField.setText("");
                courseTextField.setText("");
                yearTextField.setText("");
            }
        });
        frame.add(clearButton);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StudentInformationApp();
            }
        });
    }
}
