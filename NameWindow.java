package asteroidymodyfikacja;
 
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
 
public class NameWindow extends Frame implements ActionListener {
    //private static final String C = "THANKS FOR THE GAME! BYE!";
    private static JTextField nameField;
    //private static JLabel res;
    int width;
    int height;
    private JButton exitButton;
    
    NameWindow(int width, int height) {
        this.width=width;
        this.height=height;
        nameField = new JTextField(20);
        JPanel lowerPanel = new JPanel();
        exitButton = new JButton("OK");
        lowerPanel.setLayout(new FlowLayout());
        //lowerPanel.add(nameField);
        JLabel label = new JLabel("Enter your name");
        lowerPanel.add(label);
        //Frame frame = new Frame();
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(width, height));
        add(lowerPanel);
        add(exitButton);
        add(nameField);
        setVisible(true);
        setResizable(true);
        addWindowListener(new WindowAdapter() { 
      public void windowClosing(WindowEvent e) {//System.exit(0);
        Frame frame = (Frame) e.getSource();
        frame.dispose();
      } 
    });
        pack();
       exitButton.addActionListener(this); 
       nameField.addActionListener(this);
 
        
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
        String content = nameField.getText();
        Object source = e.getSource();
        if(source == exitButton){
             //this = (NameWindow) e.getSource();
             dispose();
        }
        else if(source == nameField){
            //dispose();
            
        }
    }
 
   
}

    
