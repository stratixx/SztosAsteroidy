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
 * 10. added bullets. get 1000 points for each asteroid shot.
 * 11. added way to cheat. press p before reaching 2500 points and then press 1,2,3,4 at the same time. 
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

class Asteroids extends Game {
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
	// vector of asteroids, starts with 5
	Vector asV = Asteroid.astV(5);
        
	// array of stars
	//Star[] stars = Star.starArray(100);
	Random random = new Random();
	int level = 0;
	//score value
	int scoreIn = 0;
        

	Bullet[] bullets = Bullet.bullets(3);
	static int bullet = 0;
	static int bulletDelay = 0;

	JFrame frame;

	//width and height
	static int w = 800;
	static int h = 600;
        // width and height scale
        static double scaleW = 1;
        static double scaleH = 1;

	// use for txt file
	boolean statChange = false;
	int astDestroyed = 0;
	int shipDestroyed = 0;

	public Asteroids() {
		super("Asteroids v2.1", w, h);
		this.setFocusable(true);
		this.requestFocus();
		this.addKeyListener(ship);
	}

	public void paint(Graphics brush) {
                brush = new GraphicsScallable(brush, scaleW, scaleH);
                
		delay++;
		brush.setColor(Color.black);
		brush.fillRect(0, 0, w, h);
		
		
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

			else {
				brush.setColor(Color.white);
				// as long as you have lives, keep on playing
				if (live > 0) {
					// counter is used to see if its been reset, or you loose a
					// live, to avoid being hit when you lose a live
					immunity++;
					// since it is dangerous traveling super fast, i increased
					// the points.

					bullets[0].move(ship);
					if (bullets[0].shoot == true) {
						brush.setColor(Color.red);
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
						if (ship.upKey) {
							if (immunity < 300) {
								brush.drawString("Immunity:" + (300 - immunity), 10, 100);
								ship.move();
								if (immunity % 10 != 0) {
									ship.thrust.paint(brush);
								}
							} else {
								ship.move();
								ship.thrust.paint(brush);
							}
						} else {
							if (immunity < 300) {
								brush.drawString("Immunity:" + (300 - immunity), 10, 100);
								ship.move();
								// flicker when you have immunity
								if (immunity % 10 != 0) {
									ship.paint(brush);
								}
							} else {
								ship.move();
								ship.paint(brush);
							}
						}
					} else {
						if (immunity < 300) {
							brush.drawString("Immunity:" + (300 - immunity), 10, 100);
							ship.move();
							if (immunity % 10 != 0) {
								ship.paint(brush);
							}
						} else {
							ship.move();
							ship.paint(brush);
						}
						score++;
					}
					brush.drawString("Score:" + score, 10, 20);
					brush.drawString("Lives " + live, 10, 40);
					
					//loop for drawing the asteroids
					for (int i = 0; i < asV.size(); i++) {
						brush.setColor(Color.pink);
						((Asteroid) asV.elementAt(i)).paint(brush);
						((Asteroid) asV.elementAt(i)).move();
						// crash with ship
						if (ship.intersection(((Asteroid) asV.elementAt(i))) && immunity > 300 ) {
                                                        
                                                        
							live--;
							immunity = 0;
							ship.reset();
							//((Asteroid) asV.elementAt(i)).reset();
                                                        ((Asteroid) asV.elementAt(i)).changeShape();
                                                        ((Asteroid) asV.elementAt(i)).hit=true;
                                                    
                                                   
                                                        live--;
                                                        immunity=0;
                                                        ship.reset();
                                                        ((Asteroid) asV.elementAt(i)).reset();
                                                    
                                                        
                                                        
                                                        
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
                                                //second srash with a ship
                                                if (ship.intersection(((Asteroid) asV.elementAt(i))) && immunity > 300) {
                                                        
                                                        
							live--;
							immunity = 0;
							ship.reset();
							//((Asteroid) asV.elementAt(i)).reset();
                                                        ((Asteroid) asV.elementAt(i)).changeShape();
                                                        ((Asteroid) asV.elementAt(i)).hit=true;
                                                    
                                                   
                                                        live--;
                                                        immunity=0;
                                                        ship.reset();
                                                        ((Asteroid) asV.elementAt(i)).reset();
                                                    
                                                        
                                                        
                                                        
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
						// crash with bullet
						if (((Asteroid) asV.elementAt(i)).intersection(bullets[bullet].square)
								&& bullets[bullet].shoot == true) {
							((Asteroid) asV.elementAt(i)).reset();
							score += scoreIn;
							astDestroyed++;
							bullets[bullet].counter = 50;
						}
                                                //second crash with bullet
                                                if (((Asteroid) asV.elementAt(i)).intersection(bullets[bullet].square)
								&& bullets[bullet].shoot == true) {
							((Asteroid) asV.elementAt(i)).reset();
							score += scoreIn;
							astDestroyed++;
							bullets[bullet].counter = 50;
						}
					}
			
					//the next few lines of code is for handling score, crashes and asteroid adding
					//set the score
					brush.setColor(Color.white);
					if (score < 500000) {
						brush.drawString("Level: " + level, 10, 60);
						scoreIn = 1000;
						//add another asteroid
						if (level < ((int) (score / 1000) + 1)) {
							// add new asteroid
							asV.addElement(new Asteroid(Asteroid.pos()));
						}
					} else {
						//too many asteroids so none are added here
						brush.drawString("Level: Impossible", 10, 60);
						scoreIn = 2000;
					}


					level = ((score / 1000) + 1);

				} else {
					brush.setFont(new Font("Dialog", Font.BOLD, 24));
					brush.drawString("GAME", 360, 100);
					brush.drawString("OVER", 360, 120);
					brush.drawString("Score:" + score, 330, 140);
					brush.setFont(new Font("Dialog", Font.PLAIN, 16));
					int posX = 180;
					try {
						// to avoid it being written over and over statChange
						// must change to true until it is reset
						if (statChange == false) {
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
							|| ship.shift || ship.one || ship.two || ship.three || ship.four) && delay > 150) {
						immunity = 300;
						live = 3;
						score = 0;
						start = 0;
						astDestroyed = 0;
						shipDestroyed = 0;
						bullets[bullet].counter = 50;
						statChange = false;
						ship.reset();
						asV = Asteroid.astV(5);
						delay = 0;

					}
				}

			}
		
	}

	public static void main(String[] args) {
		MainWindow a = new MainWindow(700,400);
               
		

	}
}