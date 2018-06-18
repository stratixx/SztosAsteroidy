package asteroidymodyfikacja;

/*
CLASS: Game
DESCRIPTION: A painted canvas in its own window, updated every tenth second.
USAGE: Extended by Asteroids.
NOTE: You don't need to understand the details here, no fiddling neccessary.
Original code by Dan Leyzberg and Art Simon
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
  
        // width and height scale
        protected double scaleW = 1.0;
        protected double scaleH = 1.0;
  
	public Game(String name, int inWidth, int inHeight) {
            width = inWidth;
            height = inHeight;
	  
            // Frame can be read as 'window' here.
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
            
            Timer timer = new Timer(1000/30, this);
            timer.setInitialDelay(0);
            timer.start();
            
            buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            buffGraphics = new GraphicsScallable(buffer.createGraphics(),scaleW,scaleH);
        }
  
  // 'paint' will be called every tenth of a second that the game is on.
	abstract public void paintAll(Graphics brush);
  Boolean paintAllow = false;
  // 'update' paints to a buffer then to the screen, then waits a tenth of
  // a second before repeating itself, assuming the game is on. This is done
  // to avoid a choppy painting experience if repainted in pieces.
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
        /*
        Frame frame = (Frame)e.getComponent();
        Rectangle fBounds = frame.getBounds();         
        Insets fInsets = frame.getInsets();
        
                
        scaleW = ((100000*fBounds.width-fInsets.left-fInsets.right+width/2)/width)/100000.0;
        scaleH = ((100000*fBounds.height-frame.getInsets().top-frame.getInsets().bottom+height/2)/height)/100000.0;
        //repaint();
        */
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