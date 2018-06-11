/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidymodyfikacja;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JTextArea;

/**
 *
 * @author martapalka
 */
public class ResultsWindow extends Frame{
    protected JTextArea textArea;
    private final static String newline ="\n";
    
    public ResultsWindow(int width, int height){
        textArea = new JTextArea(width-5,height-5);
	textArea.setEditable(false);
        textArea.setCaretPosition(textArea.getDocument().getLength());

	setLayout(new FlowLayout());
	setPreferredSize(new Dimension(width, height));
	add(textArea);
	
    
	
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
