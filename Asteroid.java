package asteroidymodyfikacja;
//martapalka


import java.util.Random;
import java.util.Vector;

/**
 *KLASA: Asteroid
 *OPIS: Dziedziczy po klasie będącej bazą dla każdego obiektu gry (oprócz pocisku) Polygon
 * 1.Położenie początkowe asteroidy ma charakter losowy (metoda rand())
 * 2.Dostępne są asteroida mała i duża 
 * 3.Prędkość asteroidy pobierana jest z pliku konfiguracyjnego
 */

public class Asteroid extends Polygon{
        
	Random random = new Random();
        double speed =0;
	boolean hit;
	
        /**
         * Konstruktor Asteroidy 
         * Defaultowo zmienna typu boolean mówiąca czy asteroida została trafiona pociskiem jest ustawiona na false
         * @param pos - pozycja 
         */
	public Asteroid(Point pos) {
		
		super(astShape(), pos, setRot(pos));
                hit=false;
                
	}
        
        
      /**
       * Metoda, która umożliwia zresetwanie asteroidy (Po zderzeniu ze statkiem lub z pociskiem)
       */
	public void reset(){
		//need to update this 
		this.position = Asteroid.pos();
		this.rotation = setRot(this.position);
		this.shape = astShape();
                
	}
        
        /**
         * Metoda używana do zmiany kształtu asteroidy (w moim kodzie uzyskujemy małą asteroidę )
         */
        public void changeShape(){
            this.shape=smallAstShape();
        }
        
        
	
	/**
	 * astShap: tu tworzony jest kształt dużej asteroidy (ma on charakter losowy)
	 */
	public static Point[] astShape(){
		Random random = new Random();
		Point[] randAsteroid = {
				new Point(0,0),
				new Point(random.nextInt(11-5)+5, -(random.nextInt(16-10)+10)),
				new Point(random.nextInt(21-15)+15, -(random.nextInt(5))),
				new Point(random.nextInt(31-25)+25, (random.nextInt(5-(-10)-10))),
				new Point(random.nextInt(21-15)+15, (random.nextInt(21-15)+15)),
				new Point(random.nextInt(0-(-5))-5, (random.nextInt(15-10)+10))
		};
		return randAsteroid;
	}
        /**
	 * smallAstShap: tu tworzony jest kształt małej asteroidy (ma on charakter losowy)
	 */
         public static Point[] smallAstShape(){
		Random random = new Random();
		Point[] randAsteroid = {
				new Point(0,0),
				new Point(random.nextInt(11-5)+1, -(random.nextInt(16-10)+6)),
				new Point(random.nextInt(21-15)+9, -(random.nextInt(5))),
				new Point(random.nextInt(31-25)+21, (random.nextInt(5-(-10)-10))),
				new Point(random.nextInt(21-15)+11, (random.nextInt(21-15)+15)),
				new Point(random.nextInt(0-(-5))-1, (random.nextInt(15-10)+10))
		};
		return randAsteroid;
	}
        
       
	
	/**
	 *Asteroidy są umieszczane w randomowych pozycjach- zazwyczaj poza płótnem gry
	 *returns Point
	 */
	public static  Point pos(){
		Random random = new Random();
		int x = 0;
		int y = 0;
		
		int pos = random.nextInt(4);
		//lewo
		if(pos == 0){
			x = 0;
			y = random.nextInt(Asteroids.h + 1);
		}
		//góra
		else if(pos == 1){
			x = random.nextInt(Asteroids.w + 1);
			y = 0;
		}
		//prawo
		else if(pos == 2){
			x = Asteroids.w;
			y = random.nextInt(Asteroids.h + 1);
		}
		//dół
		else if(pos == 3){
			x = random.nextInt(Asteroids.w + 1);
			y = Asteroids.h;
		}
		Point p = new Point(x,y);
		return p;
	}
	
	/**
	 *method: rotację ustawiamy w zależności od pozycji asteroidy
	 *w ten sposób unikamy sytuacji, że asteroida się nie pojawi albo odrazu skieruje się po za płótno gry
	 */
	public static  double setRot(Point position){
		Random random = new Random();
		
		//prawa część płótna
		if(position.x < (Asteroids.w/2)){
			//góra
			if(position.y < (Asteroids.h/2)){
				return random.nextInt(63-29) + 30;
			}
			//dół
			else{
				return random.nextInt(329-300) + 300;
			}
		}
		//lewa część płótna
		else{
			//góra
			if(position.y < (Asteroids.h/2)){
				return random.nextInt(151-120) + 120;
			}
			//dół
			else{
				return random.nextInt(241-210) + 210;
			}
		}
	}

	/**
	 * asT tworzy wektor asteroid potrzebny w metodzie paint w klasie Asteroids()
	 * @param n liczba asteroid w wektorze
	 * @return wektor złożony z asteroid
	 */
	public static Vector astV(int n){
		Vector v = new Vector();
		for(int i = 0; i < n; i++){
			Asteroid ast = new Asteroid(pos());
			v.add(ast);
		}
		return v;
	}
	
	/**
	 * move sprawia, że asteroida zaczyna się poruszać z prędkością zależną od poziomu 
         * jeśli asteroida wyleci po za ekran, pojawi się symetrycznie z drugiej strony
	 */
	public void move(int level){
		double prevPosX = position.x;
		double prevPosY = position.y;
                
                ReadFile rf = new ReadFile();
                rf.openFile();
                rf.readFile();
                
                speed=(rf.GetObjectlVelocity(level, "asteroid")*(random.nextInt(3) + 1));

		position = new Point(position.x + (speed*Math.cos(Math.toRadians(rotation))),
				position.y + (speed* Math.sin(Math.toRadians(rotation))));
		if(position.x > Asteroids.w && prevPosX < position.x){
			position = new Point(-10,position.y);
		}
		else if(position.x < 0 && prevPosX > position.x){
			position = new Point(Asteroids.w + 10,position.y);
		}
		if(position.y > Asteroids.h && prevPosY < position.y){
			position = new Point(position.x, -10);
		}
		else if(position.y < 0 && prevPosY > position.y){
			position = new Point(position.x, Asteroids.h + 10);
		}
	}

}