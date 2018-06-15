/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidymodyfikacja;

import java.awt.Canvas;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

public class MainWindow extends Canvas implements ActionListener{
        protected boolean on = true;
         int width;
         int height;
	private JButton play;
	private JButton highScore;
	private JButton theEnd;
       
        
        
	public MainWindow(int width, int height) {
               this.width=width;
               this.height=height;
               
                play = new JButton("Play Asteroids");
		highScore = new JButton("High Score");
		theEnd = new JButton("Exit");
                Frame frame = new Frame();
                frame.setLayout(new FlowLayout());
                frame.setPreferredSize(new Dimension(width, height));
                frame.add(play);
                frame.add(highScore);
                frame.add(theEnd);
                frame.setVisible(true);
                frame.setResizable(true);
                frame.addWindowListener(new WindowAdapter() { 
      public void windowClosing(WindowEvent e) {System.exit(0);} 
    });
		frame.pack();
		play.addActionListener(this); //sluchaczem jest panel wiec this
		highScore.addActionListener(this);
		theEnd.addActionListener(this);
		
}
	@Override

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source==play)
			//wywołujue się okienko gry
		{
			
			Asteroids a = new Asteroids();
		        a.repaint();
			
			
			
			
		}
			
			else if(source==highScore) 
				//uruchamia się okienko high score
			{
                           ReadFile rf = new ReadFile();
			   ResultsWindow rw = new ResultsWindow(this.width,this.height);
			}
				
				
				
			else if(source==theEnd)
				//gra sie zamyka (wszystkie okienka)
			{
                            System.exit(0);
                        }
                   
		
	}

    

    

    
}
