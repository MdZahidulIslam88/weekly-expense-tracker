import java.awt.*;
import java.util.*;
import javax.swing.*;

/* Weekly Expense Tracker
 * Features:
 *Select Day & Category (Dropdown)
 *Enter Amount & Description
 *Add Expense
 *Calculate Total Weekly Expense
 *View Expenses by Category
 *Error handling included */

public class ExpenseTracker {
    // ---------Store data-------
    static ArrayList<String> days = new ArrayList<>();
    static ArrayList<String> categories = new ArrayList<>();
    static ArrayList<Double> amounts = new ArrayList<>();
    static ArrayList<String> descriptions = new ArrayList<>();
    public static void main(String[] args) {
        // -------- FRAME --------
        JFrame frame = new JFrame("Weekly Expense Tracker");
        frame.setSize(450, 500);
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ----- INPUT --------
        String[] dayList = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
        JComboBox<String> dayBox = new JComboBox<>(dayList);

        String[] catList = {"Groceries","Eating Out","Petrol","Taxi","Bills","Rent","Others"};
        JComboBox<String> categoryBox = new JComboBox<>(catList);

        JTextField amountField = new JTextField(10);
         JTextField descField = new JTextField(10);
        // -------- BUTTONS -------
        JButton addBtn = new JButton("Add");
        JButton totalBtn = new JButton("Total");
            JButton viewBtn = new JButton("View");
        JTextArea output = new JTextArea(10, 30);
        output.setEditable(false);
        // -------- ADD COMPONENTS ------
        frame.add(new JLabel("Day:"));
        frame.add(dayBox);
        frame.add(new JLabel("Category:"));
        frame.add(categoryBox);
            frame.add(new JLabel("Amount:"));
        frame.add(amountField);
            frame.add(new JLabel("Description:"));
        frame.add(descField);
        frame.add(addBtn);
        frame.add(totalBtn);
            frame.add(viewBtn);
        frame.add(new JScrollPane(output));
        // -------- ADD FUNCTION ----------
        addBtn.addActionListener(e -> {
            try {
                double amt = Double.parseDouble(amountField.getText());
                if (amt <= 0) {
                    JOptionPane.showMessageDialog(frame, "Enter valid amount!");
                    return;
                }
                // ------ Store data -------
                days.add(dayBox.getSelectedItem().toString());
            categories.add(categoryBox.getSelectedItem().toString());
                amounts.add(amt);
                descriptions.add(descField.getText());
                // ------- Success Message -----
             JOptionPane.showMessageDialog(
                        frame,
                        categoryBox.getSelectedItem() + " added successfully!"
             );
            output.setText("Added: " 
                        + categoryBox.getSelectedItem() 
                        + " - $" + amt);
             amountField.setText("");
                descField.setText("");
            } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Invalid input!");
            }
     });
        // ----- TOTAL FUNCTION ------
        totalBtn.addActionListener(e -> {
         double sum = 0;
            for (double a : amounts) {
                sum += a;
            }
    output.setText("Total Weekly Expense = $" + sum);
    });

        // ----- CATEGORY VIEW -----
    viewBtn.addActionListener(e -> {
            HashMap<String, Double> map = new HashMap<>();
        for (int i = 0; i < categories.size(); i++) {
                String c = categories.get(i);
                double a = amounts.get(i);
                map.put(c, map.getOrDefault(c, 0.0) + a);
         }
         String result = "Expenses by Category:\n\n";
            for (String c : map.keySet()) {
                result += c + " = $" + map.get(c) + "\n";
         }
     output.setText(result);
    });
    frame.setVisible(true);
    }
}
