package asteroidymodyfikacja;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NameWindow extends JPanel {
    private static final String YOUNG_RESPONSE = "Hi youngster!";
    private static final String ADULT_RESPONSE = "Hello mature!";
    private static final String INVALID_AGE = "Invalid age!";

    private static final int MIN_AGE = 0;
    private static final int MAX_AGE = 100;

    private static JTextField nameField;
    private static JLabel res;

    NameWindow() {
        setLayout(new BorderLayout());

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());
        JLabel label = new JLabel("Enter your name");
        northPanel.add(label);

        nameField = new JTextField(15);
        northPanel.add(nameField);
        add(northPanel, BorderLayout.NORTH);


        JPanel centerPanel = new JPanel();  
        JButton btn = new JButton("Hello again");
        btn.addActionListener(new BtnListener());
        centerPanel.add(btn);

        res = new JLabel();
        res.setVisible(false);
        centerPanel.add(res);

        add(centerPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Test");
        frame.add(new NameWindow());
        frame.setVisible(true);
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static class BtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String content = nameField.getText();
            int age = -1;
            try{
                age = Integer.parseInt(content);
                if(isValid(age)) {
                    res.setText(age < 18 ? YOUNG_RESPONSE : ADULT_RESPONSE);
                } else {
                    res.setText(INVALID_AGE);
                }
                if(!res.isVisible())
                    res.setVisible(true);
            } catch(NumberFormatException ex) {
                res.setText("Wrong input");
            }
        }

        private boolean isValid(int age) {
            return age > MIN_AGE && age < MAX_AGE;
        }
    }
}