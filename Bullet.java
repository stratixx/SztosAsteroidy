/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidymodyfikacja;



public class Bullet extends Circle {
	static Point pos = Ship.pos;
	boolean shoot = false;
	int counter = 10;
        int counterLimit=0;
	static double rot;
	Point[] sqr = { new Point(0, 0), new Point(0, 5), new Point(5, 5),
			new Point(5, 0) };
	Polygon square;

	public Bullet() {
		super(pos, 5);
		square = new Polygon(sqr, position, rot);

	}
        
        public void setCounterLimit(int level){
            
            switch(level){
                case 1: 
                    counterLimit=50;
                    break;
                case 2:
                    counterLimit=40;
                    break;
                case 3:
                    counterLimit=30;
                    break;
                case 4:
                    counterLimit=20;
                    break;
                case 5:
                    counterLimit=10;
                    break;
                default:
                    counterLimit =50;
                    
            }
        }
        
        public int getCounterLimit(int level){
            return counterLimit;
        }

	public static Bullet[] bullets(int n) {
		Bullet[] bullets = new Bullet[n];
		for (int i = 0; i < n; i++) {
			bullets[i] = new Bullet();
		}
		return bullets;
	}

	public void move(Ship s , int level) {
                setCounterLimit(level);
		counter++;
		if (counter > getCounterLimit(level)) {
			if (s.shift) {
				shoot = true;
				counter = 0;
			}
		}
		if (shoot == true) {
			this.position = new Point(this.position.x
					+ (10 * Math.cos(Math.toRadians(rot))), this.position.y
					+ (10 * Math.sin(Math.toRadians(rot))));
			if (counter > getCounterLimit(level)) {
				shoot = false;
			}
			else if(this.position.x > Asteroids.w || this.position.x < 0 || this.position.y > Asteroids.h || this.position.y < 0){
				shoot = false;
				counter = getCounterLimit(level);
			}
		} else {
			this.position = s.position;
			this.rot = s.rotation;

		}
		square.position = this.position;
		square.rotation = this.rot;

	}

}