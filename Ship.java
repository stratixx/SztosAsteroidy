package asteroidymodyfikacja;

//import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Ship extends Polygon implements KeyListener {
	//int delay = 0;
	static int speed = 0;
	//keys
	boolean shift = false;
	boolean pKey = false;
	boolean space = false;
	boolean upKey = false;
	boolean downKey = false;
	boolean leftKey = false;
	boolean rightKey = false;
	boolean otherKey = false;
	boolean one = false;
	boolean two = false;
	boolean three = false;
	boolean four = false;
	static Point[] ship = { new Point(0, 0), new Point(10, 10),
			new Point(0, 20), new Point(20, 10) };
	//shape for the thrust, or when the user presses 'space'
	static Point[] boost = { new Point(0, 0), new Point(20, 10),
			new Point(0, 20), new Point(-12, 15), new Point(-10, 12),
			new Point(-20, 10), new Point(-10, 8), new Point(-12, 5) };
	static int rot = 0;
        ReadFile r = new ReadFile();
	static Point pos = new Point( getShipXPosition(), getShipYPosition());
	
        
        static public int getShipXPosition(){
            int x;
            ReadFile r = new ReadFile();
            r.openFile();
            r.readFile();
            x=r.x;
            return x;
        }
       
        static public int getShipYPosition(){
            int y;
            ReadFile r = new ReadFile();
            r.openFile();
            r.readFile();
            y=r.y;
            return y;
        }
	
	//thrust image
	//non static because it is always unique for every ship
	Polygon thrust = new Polygon(boost, new Point(pos.x -15,pos.y), rot);


	public Ship() {
		super(ship, pos, rot);

	}
	/**
	 * Method reset: resets the ship and thrust to original position and rotation
	 */
	public void reset() {
		// default values
		this.rotation = 0;
		this.position = new Point(getShipXPosition(), getShipYPosition());
		this.thrust = new Polygon(boost, new Point(pos.x -15,pos.y), rot);
	}

	/**
	 *Method live: creates and returns array of Ship. representing lives in the game
	 *not needed anymore with code updated
	 */
	public static Ship[] lives(int n) {
		// create empty array for ships
		Ship[] output = new Ship[n];
		for (int i = 0; i < n; i++) {
			output[i] = new Ship();
		}
		return output;
	}
	
	public void keyPressed(KeyEvent e) {
		int id = e.getKeyCode();
		switch (id) {
		case 16:
			shift = true;
			break;
		case 32:
			space = true;
			break;
		case 37:
			leftKey = true;
			break;
		case 38:
			upKey = true;
			break;
		case 39:
			rightKey = true;
			break;
		// might use the downKey for something maybe rockets
		case 40:
			downKey = true;
			break;
		case 49:
			one = true;
			break;
		case 50:
			two = true;
			break;
		case 51:
			three = true;
			break;
		case 52:
			four = true;
			break;
		case 80:
			pKey = true;
			//delay++;
			break;
		default:
			otherKey = true;

		}

	}

	public void keyReleased(KeyEvent e) {
		int id = e.getKeyCode();
		switch (id) {
		case 16:
			shift = false;
			break;
		case 32:
			space = false;
			break;
		case 37:
			leftKey = false;
			break;
		case 38:
			upKey = false;
			break;
		case 39:
			rightKey = false;
			break;
		case 40:
			downKey = false;
			break;
		case 49:
			one = false;
			break;
		case 50:
			two = false;
			break;
		case 51:
			three = false;
			break;
		case 52:
			four = false;
			break;
		case 80:
			pKey = false;
			break;
		default:
			otherKey = false;

		}
	}

	public void keyTyped(KeyEvent e) {

	}
	/**
	 * Method move: simply moves the ship when keys are pressed
	 * Also allows ship to appear on opposite side when its out off bound
	 */
	public void move() {
		// added to track which direction it is going
		double prevPosX = position.x;
		double prevPosY = position.y;
		int speed = 0;
		if (upKey) {
			// speed of the ship changes when space is pressed
			if (space) {
				speed = 5;
			} else {
				speed = 2;
			}
			position = new Point(position.x
					+ (speed * Math.cos(Math.toRadians(rotation))), position.y
					+ (speed * Math.sin(Math.toRadians(rotation))));
			thrust.position = position;
							
			if (position.x > Asteroids.w && prevPosX < position.x) {
				position = new Point(-10, position.y);
			} else if (position.x < 0 && prevPosX > position.x) {
				position = new Point(Asteroids.w + 10, position.y);
			}
			if (position.y > Asteroids.h && prevPosY < position.y) {
				position = new Point(position.x, -10);
			} else if (position.y < 0 && prevPosY > position.y) {
				position = new Point(position.x, Asteroids.h + 10);
			}
			
		}
		
		if (leftKey) {
			rotation = rotation - 5;
			thrust.rotation = rotation;
		}
		if (rightKey) {
			rotation = rotation + 5;
			thrust.rotation = rotation;
			
		}
	}

}