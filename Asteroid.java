package asteroidymodyfikacja;



import java.util.Random;
import java.util.Vector;

public class Asteroid extends Polygon{
        
	Random random = new Random();
        double speed =0;
	boolean hit;
	
	public Asteroid(Point pos) {
		
		super(astShape(), pos, setRot(pos));
                hit=false;
                
	}
        
        public double getAsteroidVelocity(int level){
            return 0;
        }
        
      
	public void reset(){
		//need to update this 
		this.position = Asteroid.pos();
		this.rotation = setRot(this.position);
		this.shape = astShape();
                
	}
        
        public void changeShape(){
            this.shape=smallAstShape();
        }
        
        
	
	/**
	 * Method astShap: creates random shape for asteroid.
	 * After all in space I don't think all asteroids are the same.
	 * Might update this method to create bigger asteroids
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
	 *method puts asteroids in random positons. outside the canvas. 
	 *returns Point
	 */
	public static  Point pos(){
		Random random = new Random();
		int x = 0;
		int y = 0;
		//this is to decide which side the asteroid will appear. pseudo-random
		int pos = random.nextInt(4);
		//west
		if(pos == 0){
			x = 0;
			y = random.nextInt(Asteroids.h + 1);
		}
		//north
		else if(pos == 1){
			x = random.nextInt(Asteroids.w + 1);
			y = 0;
		}
		//east
		else if(pos == 2){
			x = Asteroids.w;
			y = random.nextInt(Asteroids.h + 1);
		}
		//
		else if(pos == 3){
			x = random.nextInt(Asteroids.w + 1);
			y = Asteroids.h;
		}
		Point p = new Point(x,y);
		return p;
	}
	
	/**
	 *method: rotation is set depending on the position. 
	 *this is to avoid asteroids at the start not showing up, or going outside at the starts. 
	 *better said is when they start they go towards around the center.
	 */
	public static  double setRot(Point position){
		Random random = new Random();
		//the canvas gets divided into 4 parts
		//west side
		if(position.x < (Asteroids.w/2)){
			//north west
			if(position.y < (Asteroids.h/2)){
				return random.nextInt(61-30) + 30;
			}
			//south west
			else{
				return random.nextInt(331-300) + 300;
			}
		}
		//east side
		else{
			//north east
			if(position.y < (Asteroids.h/2)){
				return random.nextInt(151-120) + 120;
			}
			//south east
			else{
				return random.nextInt(241-210) + 210;
			}
		}
	}

	/**
	 * asT creates a vector with n size of Asteroid objects
	 * @param n number of elements
	 * @return vector of Asteroid
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
	 * Method move: just simply makes the asteroid(s) move around the canvas
	 * also if the get out bound they will appear on the opposite side
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