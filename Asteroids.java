package asteroidymodyfikacja;
//martapalka
/**
 *KLASA: Asteroids
 *OPIS: Dziedziczy po abstrakcyjnej klasie Game 
 *UWAGA: W tej klasie znajduje się metoda main
 *Instrukcja obsługi:
 * 1.Rozszerzenie w postaci zmiany prędkości na szybszą przy naciśnięciu spacji
 * 2.Kiedy gracz porusza się szybciej punkty też odpowiednio rosną szybciej
 * 3.Poziom zmienia się po upłynięciu czasu określonego w pliku konfiguracyjnym config.txt
 * 4.Grę można wygrać "wytrzymując" do końca czasu określonego w pliku konfiguracyjnym
 * 5.Wraz ze zmianą poziomu dochodzi do zwiększenia prędkości statku i asteroid
 * 6.Co poziom dodawane są nowe asteroidy
 * 7.Po utracie życia graczowi przysługuje okres w którym nie może zostać trafiony przez asteroidy
 * 8.Naciskając przycisk p na klawiaturze, gracz uruchamia pauzę
 * 9.Za strącenie dużej asteroidy graczowi przysługuje 500 punktów
 * 10.Za strącenie małej asteroidy graczowi przysługuje 1000 punktów
 * 11.Gracz ma 3 życia
 * 12.Niektóre asteroidy rozpadają się na dwie mniejsze
 */

import java.awt.*;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;
import javax.swing.JFrame;

class Asteroids extends Game{
	// do pauzowania gry
	static boolean pause = false;
	// do synchronizacji gry przy rozpoczynaniu, pauzowaniu i kończeniu
	static int delay = 100;
	static int start = 1;
        static int immunity = 100;
	static int score = 0;
	static int topScore = 0;
	Ship ship = new Ship();
	int live = 3;
	// wektor asteroid inicjuję liczbą wskazaną w pliku konfiguracyjnym
	Vector asV = Asteroid.astV(getInnitialAsteroidsNumber());
        ReadFile rf = new ReadFile();
        boolean alreadyExecuted = false;
        boolean alreadyCalled =false;
        int helpful=0; //pomoc podczas zmiany poziomów
        boolean end=false;
        
        
        
        

	Random random = new Random();
	int level = 1;
        int time=0;
	//punkty za dużą asteroidę
	int scoreIn = 0;
        //punkty za małą asteroidę
        int scoreInsmall=0;
        
                
        Bullet[] bullets = Bullet.bullets(3);
	static int bullet = 0;
	static int bulletDelay = 0;

	JFrame frame;

	//wysokość i szerokość
	static int w = getWindowWidthorHeight("width");
	static int h = getWindowWidthorHeight("height");

	//zmienne używane podczas operacji na plikach
        int howManyGamesAlready=1;
	boolean statChange = false;
        String name;
        private String[] results = new String[5];

	public Asteroids() {
		super("Asteroids", w, h);
		this.requestFocus();
		this.addKeyListener(ship);
                
	}
        /**
         * Metoda pozwalająca na pobranie wysokości i szerokości okna z pliku konfiguracyjnego
         * @param s parametr mówiący, czy prosimy o wysokość czy szerokość
         * @return zwraca wysokość i szerokość okna
         */
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
        /**
         * Metoda pozwalająca na pobranie początkowej ilości asteroid z pliku konfiguracyjnego
         * @return 
         */
        public int getInnitialAsteroidsNumber(){
            ReadFile r = new ReadFile();
            r.openFile();
            r.readFile();
            int a=r.GetAstNumber(1);
            return a;
         }
        
        
        /**
         * Metoda która tworzy mniejszą asteroidę
         * @param pos 
         * @return zwraca asteroidę
         */
        public Asteroid getSmallAsteroid(Point pos){
            Asteroid asteroid = new Asteroid(pos);
            asteroid.changeShape();
            asteroid.hit=true;
            return asteroid;
        }
        
        /**
         * Główna metoda gry: to w niej rysowana jest plansza 
         * @param brush typu Graphics
         */
        public void paintAll(Graphics brush) {                
		delay++;
		brush.setColor(Color.black);
		brush.fillRect(0, 0, w+250, h+250);
                
                if(!alreadyExecuted) {
                rf.openFile();
                rf.readFile();
                alreadyExecuted = true;
                }
                
		
		
			//włączamy lub wyłączamy pauzę
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
			
			if (pause) {
                            
                            
				// ekran pauzy
				brush.setColor(Color.white);
				brush.drawString("GAME PAUSE", 0, (int)(rf.width/55));
				brush.drawString("Up Key - Move Forward", 0, (int)(rf.width/3)+(int)(rf.width/55));
				brush.drawString("Left Key - Rotate Left", 0, (int)(rf.width/3)+2*(int)(rf.width/55));
				brush.drawString("Right Key - Rotate Right", 0, (int)(rf.width/3)+3*(int)(rf.width/55));
				brush.drawString("Space Key w/ Up Key - Activate Thrust", 0, (int)(rf.width/3)+4*(int)(rf.width/55));
				brush.drawString("P Key - Pause/Resume Game", 0, (int)(rf.width/3)+5*(int)(rf.width/55));
				brush.drawString("HIGH SCORE: " + topScore, (int)(rf.height)/2, (int)(rf.width/3));
				brush.drawString("Current Score:" + score, ((int)(rf.height)/2)-(int)(rf.height/55), (int)(rf.width/3)+(int)(rf.width/55));
				

			}
                        
                        else if(end){
                            brush.setColor(Color.white);
                            brush.setFont(new Font("Dialog", Font.BOLD, (int)(rf.height)/24)); 
                            brush.drawString("YOU", (int)(rf.height)/3, (int)(rf.width/3)); 
                            brush.drawString("WON!", (int)(rf.height)/3, (int)(rf.width/3)+(int)(rf.width/30)); 
                            brush.drawString("SCORE:" + score, (int)(rf.height)/3, (int)(rf.width/3)+2*(int)(rf.width/30)); 
                            brush.setFont(new Font("Dialog", Font.PLAIN, 16)); 
                            
                            
                            try {
						
						if (!statChange) {
                                                        
                                                        
                                                        if(howManyGamesAlready<=5){
                                                            results[howManyGamesAlready-1]=Integer.toString(score);
                                                            if(howManyGamesAlready==5){
                                                            rf.write(results);
                                                            rf.highscore(score);
                                                            }
                                                            }
                                                        results[howManyGamesAlready-1]=Integer.toString(score);
                                                        
							
                                                            
                                                            
                                                            
                                                            statChange = true;

						}
						
						
					} catch (IOException e) {
						System.err.println("File error");
					}
					brush.drawString("Press any key to start again",  (int)(rf.height)/3,  (int)(rf.width/3)+3*(int)(rf.width/30));

					// reset
					if ((ship.otherKey || ship.upKey  || ship.leftKey || ship.rightKey || ship.space
							|| ship.shift) && delay > 50) {
						immunity = 100;
						live = 3;
						score = 0;
                                                time=0;
                                                level=1;
						start = 0;
						bullets[bullet].counter = bullets[bullet].getCounterLimit(level);
						statChange = false;
						ship.reset();
						asV = Asteroid.astV(getInnitialAsteroidsNumber());
						delay = 0;
                                                alreadyExecuted = false;
                                                //alreadyCalled =false;
                                                helpful=0;
                                                end=false;
                                                howManyGamesAlready++;

					}
				}

			
		
	
        

                            
                        

			else {
				brush.setColor(Color.white);
				
				if (live > 0) {
					
					immunity++;
					
                                        

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

					
					if (ship.space) {
                                                //punkty rosą szybciej kiedy podróżujemy z wciśniętą spacją
						score += 5;
                                                //czas płynie szybciej
                                                time+=2;
						if (ship.upKey) {
							if (immunity < 100) {
								brush.drawString("Protection:" + (300 - immunity), 10, 120);
								ship.move(level);
								if (immunity % 10 != 0) {
									ship.thrust.paint(brush);
								}
							} else {
								ship.move(level);
								ship.thrust.paint(brush);
							}
						} else {
							if (immunity < 100) {
								brush.drawString("Protection:" + (100 - immunity), 10, 120);
								ship.move(level);
								
								if (immunity % 10 != 0) {
									ship.paint(brush);
								}
							} else {
								ship.move(level);
								ship.paint(brush);
							}
						}
					} else {
						if (immunity < 100) {
							brush.drawString("Protection:" + (100 - immunity), 10, 120);
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
					
					//tu rysujemy asteroiidy
					for (int i = 0; i < asV.size(); i++) {
						brush.setColor(Color.pink);
						((Asteroid) asV.elementAt(i)).paint(brush);
						((Asteroid) asV.elementAt(i)).move(level);
						// przypadek stłuczki asteroidy i statku: tracimy życie
						if (ship.intersection(((Asteroid) asV.elementAt(i))) && immunity > 100 ) {
                                                        
                                                        
							live--;
							immunity = 0;
							ship.reset();
							((Asteroid) asV.elementAt(i)).reset();
						
							// delay for end screen
							if (live == 0) {
								delay = 0;
							}
							
							break;
						}
                                      
						// stłuczka asteroidy z pociskiem : asteroida roztrzaskuje się na dwie mniejsze
						if (((Asteroid) asV.elementAt(i)).intersection(bullets[bullet].square)
								&& bullets[bullet].shoot == true && ((Asteroid) asV.elementAt(i)).hit==false) {
                                                        ((Asteroid) asV.elementAt(i)).hit=true;
                                                        ((Asteroid) asV.elementAt(i)).changeShape();
                                                        asV.add(i+1, getSmallAsteroid(((Asteroid)asV.elementAt(i)).position));
                                                        score += scoreIn;
                                                        bullets[bullet].counter = bullets[bullet].getCounterLimit(level);
                                                        break;
                                                        
							
                                                        }
                                                //stłuczka pocisku z małą asteroidą: asteroida znika
                                               if (((Asteroid) asV.elementAt(i)).intersection(bullets[bullet].square) && bullets[bullet].shoot == true && ((Asteroid) asV.elementAt(i)).hit==true) {
							((Asteroid) asV.elementAt(i)).reset();
							score += scoreInsmall;
							bullets[bullet].counter = bullets[bullet].getCounterLimit(level);
                                                        
						}
					}
			
					
					brush.setColor(Color.white);
					if (time < rf.getTotalGameTime()) {
						brush.drawString("Level: " + level, 10, 60);
						scoreIn = 500;
                                                scoreInsmall=1000;
                                                if((time-helpful)>rf.getPlayTime(level)){
                                                  
                                                   
                                                    helpful+=rf.getPlayTime(level);
					           
                                                   level+=1;
                                                        //dodajemy ilość asteroid określoną w pliku konfiguracyjnym
                                                        int astToAdd=rf.GetAstNumber(level)-rf.GetAstNumber(level-1);
                                                        for(int i=0; i<astToAdd; i++) {
							asV.addElement(new Asteroid(Asteroid.pos()));
                                                       }
                                                        
						}
					} else {
						//gra wygrana-czas się skończył
                                                alreadyExecuted=false;
					        end=true;
                                                
					}


					
                                      

				} else {
					
          brush.setFont(new Font("Dialog", Font.BOLD, (int)(rf.height)/24)); 
          brush.drawString("GAME", (int)(rf.height)/3, (int)(rf.width/3)); 
          brush.drawString("OVER", (int)(rf.height)/3, (int)(rf.width/3)+(int)(rf.width/30)); 
          brush.drawString("Score:" + score, (int)(rf.height)/3, (int)(rf.width/3)+2*(int)(rf.width/30)); 
          brush.setFont(new Font("Dialog", Font.PLAIN, (int)(rf.height)/37));  
                                       
                           
                            
                                        
                                        
                                        
					
					try {
						
						if (!statChange) {
                                                       
                                                        
                                                        if(howManyGamesAlready<=5){
                                                            results[howManyGamesAlready-1]=Integer.toString(score);
                                                            if(howManyGamesAlready==5){
                                                            rf.write(results);
                                                            rf.highscore(score);
                                                            }
                                                            }
                                                        
							
                                                            
                                                            
                                                            
                                                            statChange = true;

						}
						
						
					} catch (IOException e) {
						System.err.println("File error");
					}
					brush.drawString("Press any key to start again",  (int)(rf.height)/3,  (int)(rf.width/3)+3*(int)(rf.width/30));

					// reset 
					if ((ship.otherKey || ship.upKey  || ship.leftKey || ship.rightKey || ship.space
							|| ship.shift) && delay > 50) {
						immunity = 100;
						live = 3;
						score = 0;
                                                time=0;
                                                level=1;
						start = 0;
						bullets[bullet].counter = bullets[bullet].getCounterLimit(level);
						statChange = false;
						ship.reset();
						asV = Asteroid.astV(getInnitialAsteroidsNumber());
						delay = 0;
                                                alreadyExecuted = false;
                                                //alreadyCalled =false;
                                                helpful=0;
                                                end=false;
                                                howManyGamesAlready++;

					}
				}

			}
		
	
        
        }
        
        /**
         * KLASA: main
         * OPIS: Tworzymy w niej główne okienko aplikacji (MainWindow)
         * 
         *  
         */

	public static void main(String[] args) {
                ReadFile rf= new ReadFile();
                rf.openFile();
                rf.readFile();
		MainWindow a = new MainWindow(rf.height,rf.width);
                
                
                
                
                
}

}