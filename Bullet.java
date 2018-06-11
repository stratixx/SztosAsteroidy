/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidymodyfikacja;



public class Bullet extends Circle {
	static Point pos = Ship.pos;
	boolean shoot = false;
	int counter = 50;
	static double rot;
	Point[] sqr = { new Point(0, 0), new Point(0, 5), new Point(5, 5),
			new Point(5, 0) };
	Polygon square;

	public Bullet() {
		super(pos, 5);
		square = new Polygon(sqr, position, rot);

	}

	public static Bullet[] bullets(int n) {
		Bullet[] bullets = new Bullet[n];
		for (int i = 0; i < n; i++) {
			bullets[i] = new Bullet();
		}
		return bullets;
	}

	public void move(Ship s) {
		counter++;
		if (counter > 50) {
			if (s.shift) {
				shoot = true;
				counter = 0;
			}
		}
		if (shoot == true) {
			this.position = new Point(this.position.x
					+ (10 * Math.cos(Math.toRadians(rot))), this.position.y
					+ (10 * Math.sin(Math.toRadians(rot))));
			if (counter > 50) {
				shoot = false;
			}
			else if(this.position.x > Asteroids.w || this.position.x < 0 || this.position.y > Asteroids.h || this.position.y < 0){
				shoot = false;
				counter = 50;
			}
		} else {
			this.position = s.position;
			this.rot = s.rotation;

		}
		square.position = this.position;
		square.rotation = this.rot;

	}

}