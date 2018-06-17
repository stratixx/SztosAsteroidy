package asteroidymodyfikacja;

/*
 CLASS: Asteroids
 DESCRIPTION: Extending Game, Asteroids is all in the paint method.
 NOTE: This class is the metaphorical "main method" of your program,
 it is your control center.
 Original code by Dan Leyzberg and Art Simon
 */
/**Game Features
 * 1.Speed change when space bar is pressed
 * 2.Score increases faster when space bar is pressed
 * 3.Added levels, levels increase amount of asteroids displayed. 
 * Levels change when score reaches certain number
 * 4.reset game after game over.
 * 5.added high score... might add txt file to keep old scores 
 * 6. when increased speed changes to show that its going faster. (thrust shows instead of regular ship)
 * 7. added immunity after you lose a live. (only 3 seconds)
 * 8. added pause button with key p.
 * 9. added stars to the background
 * 10. added bullets. get 1000 points for small asteroid shot.
 * 11. added bullets. get 500 points for big asteroid shot.
 * 12. added way to cheat. press p before reaching 2500 points and then press 1,2,3,4 at the same time. 
 * This make your score 500000 therefore activating impossible mode. 
 * All game features coded by Josue Rojas
 */

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.Timer;

class Asteroids extends Game{
	// added boolean pause to pause game along with delay for pause
	static boolean pause = false;
	// delay is used for pause, start of game and the ending
	static int delay = 100;
	 static int start = 1;
	// the immunity is to avoid being hit when you lose a live. it starts at 300
	// so you get no immunity at the start
	static int immunity = 300;
	static int score = 0;
	// change this later to txt file to save previous games
	static int topScore = 0;
	Ship ship = new Ship();
	// number of lives
	int live = 3;
	// vector of asteroids, starts with a number of asteroids indicated in text file 
	Vector asV = Asteroid.astV(getInnitialAsteroidsNumber());
        ReadFile rf = new ReadFile();
        boolean alreadyExecuted = false;
        boolean alreadyCalled =false;
        int helpful=0; //used when changing levels in paint method
        boolean end=false;
        
        
        
        
	// array of stars
	//Star[] stars = Star.starArray(100);
	Random random = new Random();
	int level = 1;
        int time=0;
	//score value
	int scoreIn = 0;
        int scoreInsmall=0;
        //
                
        Bullet[] bullets = Bullet.bullets(3);
	static int bullet = 0;
	static int bulletDelay = 0;

	JFrame frame;

	//width and height
	static int w = getWindowWidthorHeight("width");
	static int h = getWindowWidthorHeight("height");

	// use for txt file
	boolean statChange = false;
	int astDestroyed = 0;
	int shipDestroyed = 0;
        String name;

	public Asteroids() {
		super("Asteroids", w, h);
		//this.setFocusable(true);
		this.requestFocus();
		this.addKeyListener(ship);
                
	}
        
        static public int getWindowWidthorHeight(String s){
            int x = 0;
            ReadFile r = new ReadFile();
            r.openFile();
            r.readFile();
            if(s=="width")
            {
               x= r.width;
            }
            else if(s=="height"){
                x= r.height;
            }
            return x;
                
           
        }
        
         public int getInnitialAsteroidsNumber(){
            ReadFile r = new ReadFile();
            r.openFile();
            r.readFile();
            int a=r.GetAstNumber(1);
            return a;
         }
        
        
        
        public Asteroid getSmallAsteroid(Point pos){
            Asteroid asteroid = new Asteroid(pos);
            asteroid.changeShape();
            asteroid.hit=true;
            return asteroid;
        }
        
       /* public void readFile(int i, int level, String name){
            ReadFile r = new ReadFile();
            r.openFile();
            r.readFile();
            switch(i){
                case 1:
                    r.getTotalGameTime();
                break;
                case 2:
                    r.GetAstNumber(level);
                    break;
                case 3:
                    r.GetObjectlVelocity(level, name);
                    break;
                case 4:
                    r.getPlayTime(level);
                    break;
                default: 
                    System.out.println("Error connected with reading file");
            
        }
        }*/
        

	public void paintAll(Graphics brush) {                
		delay++;
		brush.setColor(Color.black);
		brush.fillRect(0, 0, w+250, h+250);
                
                if(!alreadyExecuted) {
                rf.openFile();
                rf.readFile();
                alreadyExecuted = true;
                }
                
		
		
			// this sets pause on or off
			if (delay > 30) {
				if (ship.pKey) {
					if (pause) {
						pause = false;
						delay = 0;
					} else {
						pause = true;
						delay = 0;
					}
				}

			}
			// at pause
			if (pause) {
				// pause screen
				brush.setColor(Color.white);
				brush.drawString("GAME PAUSE", 0, 11);
				brush.drawString("Up Key - Move Forward", 0, 31);
				brush.drawString("Left Key - Rotate Left", 0, 51);
				brush.drawString("Right Key - Rotate Right", 0, 71);
				brush.drawString("Space Key w/ Up Key - Activate Thrust", 0, 91);
				brush.drawString("P Key - Pause/Resume Game", 0, 131);
				brush.drawString("HIGH SCORE: " + topScore, 360, 290);
				brush.drawString("Current Score:" + score, 350, 270);
				if (ship.shift) {
					// secret message
					brush.drawString("Hold Shift Key for a stronger bullet", 0, 111);
				} else {
					brush.drawString("Shift Key - Shoot", 0, 111);
				}
				// cheat code
				if (ship.one && ship.two && ship.three && ship.four && score < 2500) {
					JOptionPane.showMessageDialog(frame, "ENTERING IMPOSSIBLE MODE", "WARNING",
							JOptionPane.WARNING_MESSAGE);
					score += 500000;
					asV = Asteroid.astV(1000);
					// avoid errors
					ship.one = false;
					ship.two = false;
					ship.three = false;
					ship.four = false;

				}

			}
                        
                        else if(end){
                            brush.setColor(Color.white);
                            brush.setFont(new Font("Dialog", Font.BOLD, 24));
		            brush.drawString("CONGRATULATIONS", 360, 100);
		            brush.drawString("YOU", 360, 120);
		            brush.drawString("WON!", 330, 140);
                            brush.drawString("SCORE:" + score, 330, 160);
                            brush.drawString("Press W button to enter your name:", 330, 180);
                            //here is a place for a name window to type gamers name 
			    brush.setFont(new Font("Dialog", Font.PLAIN, 16));
                            
                            if(ship.wKey){
                                
                            NameWindow nw = new NameWindow(400,404);
                                /*if(!alreadyCalled){
                                 JFrame frame = new JFrame("Test");
                                 frame.add(new NameWindow());
                                 frame.setVisible(true);
                                 frame.setSize(300, 300);
                                 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                 alreadyCalled =true;
                            }*/
                            }
                            
                        }

			else {
				brush.setColor(Color.white);
				// as long as you have lives, keep on playing
				if (live > 0) {
					// counter is used to see if its been reset, or you loose a
					// live, to avoid being hit when you lose a live
					immunity++;
					// since it is dangerous traveling super fast, i increased
					// the points.
                                        //time moves faster with  space pressed on
                                        

					bullets[0].move(ship, level);
					if (bullets[0].shoot == true) {
						brush.setColor(Color.blue);
						bullets[0].paint(brush);
						brush.setColor(Color.black);
						bullets[0].square.paint(brush);
						brush.setColor(Color.white);
						brush.drawString("Bullets: 0", 10, 80);
					} else {
						brush.drawString("Bullets: 1", 10, 80);
					}

					// score is increases when using space bar
					if (ship.space) {
						score += 5;
                                                time+=2;
						if (ship.upKey) {
							if (immunity < 300) {
								brush.drawString("Immunity:" + (300 - immunity), 10, 120);
								ship.move(level);
								if (immunity % 10 != 0) {
									ship.thrust.paint(brush);
								}
							} else {
								ship.move(level);
								ship.thrust.paint(brush);
							}
						} else {
							if (immunity < 300) {
								brush.drawString("Immunity:" + (300 - immunity), 10, 120);
								ship.move(level);
								// flicker when you have immunity
								if (immunity % 10 != 0) {
									ship.paint(brush);
								}
							} else {
								ship.move(level);
								ship.paint(brush);
							}
						}
					} else {
						if (immunity < 300) {
							brush.drawString("Immunity:" + (300 - immunity), 10, 120);
							ship.move(level);
							if (immunity % 10 != 0) {
								ship.paint(brush);
							}
						} else {
							ship.move(level);
							ship.paint(brush);
						}
						score++;
                                                time++;
					}
					brush.drawString("Score:" + score, 10, 20);
					brush.drawString("Lives " + live, 10, 40);
                                        brush.drawString("Time "+time, 10, 100);
					
					//loop for drawing the asteroids
					for (int i = 0; i < asV.size(); i++) {
						brush.setColor(Color.pink);
						((Asteroid) asV.elementAt(i)).paint(brush);
						((Asteroid) asV.elementAt(i)).move(level);
						// crash with ship : asteroid is destroyed
						if (ship.intersection(((Asteroid) asV.elementAt(i))) && immunity > 300 ) {
                                                        
                                                        
							live--;
							immunity = 0;
							ship.reset();
							((Asteroid) asV.elementAt(i)).reset();
                                                       // ((Asteroid) asV.elementAt(i)).changeShape();
                                                        //asV.add(i)
                                                        //asV.(i);
							astDestroyed++;
							shipDestroyed++;
							// delay for end screen
							if (live == 0) {
								delay = 0;
							}
							// break or it will cause problems in the next line
							break;
						}
                                      
						// crash with bullet : big asteroid breaks into two smaller asteroids
						if (((Asteroid) asV.elementAt(i)).intersection(bullets[bullet].square)
								&& bullets[bullet].shoot == true && ((Asteroid) asV.elementAt(i)).hit==false) {
                                                        ((Asteroid) asV.elementAt(i)).hit=true;
                                                        ((Asteroid) asV.elementAt(i)).changeShape();
                                                        asV.add(i+1, getSmallAsteroid(((Asteroid)asV.elementAt(i)).position));
                                                        score += scoreIn;
                                                        bullets[bullet].counter = bullets[bullet].getCounterLimit(level); //Its better to set smaller counter or a counter depeding on the level
                                                        
							//((Asteroid) asV.elementAt(i)).reset();
                                                        }
                                                //second crash with bullet : small asteroid disappears
                                               if (((Asteroid) asV.elementAt(i)).intersection(bullets[bullet].square) && bullets[bullet].shoot == true && ((Asteroid) asV.elementAt(i)).hit==true) {
							((Asteroid) asV.elementAt(i)).reset();
							score += scoreInsmall;
							astDestroyed++;
							bullets[bullet].counter = bullets[bullet].getCounterLimit(level);
						}
					}
			
					//the next few lines of code is for handling score, crashes and asteroid adding
					//set the score
					brush.setColor(Color.white);
					if (time < rf.getTotalGameTime()) {
						brush.drawString("Level: " + level, 10, 60);
						scoreIn = 500;
                                                scoreInsmall=1000;
						//add another asteroid
                                                //level change
						//if (level < ((int) (score / 1000) + 1)) {
                                                if((time-helpful)>rf.getPlayTime(level)){
                                                  
                                                   //helpful trick to solve my problem during level change
                                                    helpful+=rf.getPlayTime(level);
					           // add the number of asteroids indicated in config.txt 
                                                   level+=1;
                                                    
                                                        int astToAdd=rf.GetAstNumber(level)-rf.GetAstNumber(level-1);
                                                        for(int i=0; i<astToAdd; i++) {
							asV.addElement(new Asteroid(Asteroid.pos()));
                                                       }
                                                        
						}
					} else {
						//too many asteroids so none are added here
						//brush.drawString("Congratulations! You won!", 10, 60);
                                                alreadyExecuted=false;
					        end=true;
                                                
					}


					//level = ((score / 1000) + 1);

				} else {
					brush.setFont(new Font("Dialog", Font.BOLD, 24));
					brush.drawString("GAME", 360, 100);
					brush.drawString("OVER", 360, 120);
					brush.drawString("Score:" + score, 330, 140);
                                        brush.drawString("Press W button to enter your name:", 330, 180);
					brush.setFont(new Font("Dialog", Font.PLAIN, 16));
                                       
                           
                            
                                        if(ship.wKey && delay>30 && !alreadyCalled){
                                       
                                         NameWindow nw = new NameWindow(400,404);
                                         alreadyCalled=true;
                                        /*JFrame frame = new JFrame("Enter your name");
                                        frame.add(new NameWindow());
                                        frame.setVisible(true);
                                        frame.setSize(rf.height, rf.width);
                                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                        Timer timer = new Timer(3000, new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                        frame.setVisible(false);
                                        frame.dispose();
                                        }
                                        });
                                        timer.setRepeats(false);
                                        timer.start();

                                        frame.setVisible(true); // if modal, application will pause here

                                        System.out.println("Dialog closed");

                                        alreadyCalled =true;
                                        
                                        */}
					int posX = 180;
					try {
						// to avoid it being written over and over statChange
						// must change to true until it is reset
						if (!statChange) {
							File.highscore(score);
							File.destroyed(astDestroyed, shipDestroyed);
							statChange = true;

						}
						String[] stat = File.read();
						for (int i = 0; i < stat.length; i++) {
							brush.drawString(stat[i], 300, posX);
							posX += 20;
						}
					} catch (IOException e) {
						System.err.println("File error");
					}
					brush.drawString("Press any key to start again", 300, posX + 20);

					// reset game
					if ((ship.otherKey || ship.upKey || ship.downKey || ship.leftKey || ship.rightKey || ship.space
							|| ship.shift || ship.one || ship.two || ship.three || ship.four || ship.wKey) && delay > 150) {
						immunity = 300;
						live = 3;
						score = 0;
                                                time=0;
                                                level=1;
						start = 0;
						astDestroyed = 0;
						shipDestroyed = 0;
						bullets[bullet].counter = bullets[bullet].getCounterLimit(level);
						statChange = false;
						ship.reset();
						asV = Asteroid.astV(getInnitialAsteroidsNumber());
						delay = 0;
                                                alreadyExecuted = false;
                                                alreadyCalled =false;
                                                helpful=0;
                                                end=false;

					}
				}

			}
		
	
        
        }

	public static void main(String[] args) {
                ReadFile rf= new ReadFile();
                rf.openFile();
                rf.readFile();
		MainWindow a = new MainWindow(rf.height,rf.width);
                 
               
		

	}

}