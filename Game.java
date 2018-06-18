package asteroidymodyfikacja;
//martapalka
/*
CLASS: Game
OPIS: Płótno w okienku gry 
Klasa abstrakcyjna, którą rozszerza klasa Asteroids
W tej klasie dochodzi do odświerzania grafiki, a także tu zaimplementowane jest zachowanie okna z płótnem(canvasem) przy rozciąganiu
*/
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.Timer;

abstract class Game extends Canvas implements ComponentListener, ActionListener{
  protected boolean on = true;
  protected int width, height;
  protected BufferedImage buffer;
  protected Graphics2D buffGraphics;  
  protected Frame frame;
  
       //skala w dwóch wymiarach okienka
        protected double scaleW = 1.0;
        protected double scaleH = 1.0;
  
	public Game(String name, int inWidth, int inHeight) {
            width = inWidth;
            height = inHeight;
            frame = new Frame(name);
            frame.add(this);
            frame.setSize(width,height);
            frame.setVisible(true);
            frame.setResizable(true);
            frame.setFocusable(true);
            frame.requestFocus();
            frame.addComponentListener(this);
            frame.addWindowListener(new WindowAdapter() 
            {
                public void windowClosing(WindowEvent e) {
                Frame frame = (Frame) e.getSource();
                frame.dispose();} 
            });
            
            //30 Hz
            Timer timer = new Timer(1000/30, this);
            timer.setInitialDelay(0);
            timer.start();
            
            buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            buffGraphics = new GraphicsScallable(buffer.createGraphics(),scaleW,scaleH);
        }
  
  
	abstract public void paintAll(Graphics brush);
  Boolean paintAllow = false;
  
  /**
   * DZIAŁANIE:Update maluje do bufora, potem na ekran; następnie czeka 1/10 sekundy zanim się powtórzy, oczywiście o ile gra jest włączona
   * @param brush 
   */
  
    @Override
    public void update(Graphics brush) 
    {      
        Graphics2D gS = (Graphics2D)brush;

        paintAll(buffGraphics);  
        gS.drawImage(buffer,0,0,frame);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        repaint();
    }
  

    @Override
    public void componentResized(ComponentEvent e) {   
        double w = e.getComponent().getWidth();
        double h = e.getComponent().getHeight();
        scaleW = w/width;
        scaleH = h/height;
        buffer = new BufferedImage((int)w,(int)h, BufferedImage.TYPE_INT_ARGB);
        buffGraphics = new GraphicsScallable(buffer.createGraphics(),scaleW,scaleH);
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void componentShown(ComponentEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  

}