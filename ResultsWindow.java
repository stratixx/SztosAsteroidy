package asteroidymodyfikacja;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import javax.swing.JLabel;

/**
 * KLASA:ResultsWindow
 * OPIS: Okienko z pięcioma najlpeszymi wynikami
 * 
 */
public class ResultsWindow extends Frame{
  //  protected JTextArea textArea;
    private final static String newline =System.lineSeparator();
    
    public ResultsWindow(int width, int height) throws FileNotFoundException{
        super("Top 5 scores");
       
        String [] topFive = ReadFile.read();
        
	JLabel label = new JLabel();
        label.setText(topFive[0]+newline+topFive[1]+newline+topFive[2]+newline+topFive[3]+newline+topFive[4]+newline);
        //JLabel label1 = new JLabel();
        //label1.setText(topFive[0]+newline+topFive[1]+newline+topFive[2]+newline+topFive[3]+newline+topFive[4]+newline);
        
        setLayout(new FlowLayout());
	setPreferredSize(new Dimension(width, height));
        add(label);
        //add(label1);
        
	
	
    
	
	addWindowListener(new WindowAdapter() {
		@Override
		public void windowClosing(WindowEvent e) {
               
                Frame frame = (Frame) e.getSource();
                frame.dispose();
   }

  });
	pack();
	setVisible(true);
    }

    
}
