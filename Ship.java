package asteroidymodyfikacja;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 * KLASA:Ship
 * OPIS:Klasa która odpowiada za statek. Statek reaguje na przyciski:
 * 1.Shift-strzał
 * 2.P-pauza
 * 3.spacja-przyspieszenie
 * 4.góra-do przodu
 * 5.lewo-obrót w lewo
 * 6.prawo-obrót w prawo
 * 7.Dowolny przycisk- nowa gra
 * 
 */
public class Ship extends Polygon implements KeyListener {
	
	static double speed = 0;
	//keys
	boolean shift = false;
	boolean pKey = false;
	boolean space = false;
	boolean upKey = false;
	boolean leftKey = false;
	boolean rightKey = false;
	boolean otherKey = false;
        boolean wKey = false;
        //kształt statku bez wciśniętej spacji- normalna prędkość
	static Point[] ship = { new Point(0, 0), new Point(10, 10),
			new Point(0, 20), new Point(20, 10) };
	//kształt statku w nadświetlnej
	static Point[] boost = { new Point(0, 0), new Point(20, 10),
			new Point(0, 20), new Point(-12, 15), new Point(-10, 12),
			new Point(-20, 10), new Point(-10, 8), new Point(-12, 5) };
	static int rot = 0;
       
	static Point pos = new Point( getShipPosition("x"), getShipPosition("y"));
	
        /**
         * Metoda pozwalająca na pobranie początkowej pozycji statku przy jego malowaniu (z pliku config.txt)
         * @param xy
         * @return 
         */
        static public int getShipPosition(String xy){
            
            ReadFile r = new ReadFile();
            r.openFile();
            r.readFile();
            if(xy.equals("x")){
                return r.x;
            }
            else if(xy.equals("y")){
                return r.y;
            }
            else throw new IllegalArgumentException("Unknown sign in getShipPosition method");
            
        }
        
	
	
        //kształt statu w nadświetlnej
	Polygon thrust = new Polygon(boost, new Point(pos.x -15,pos.y), rot);

        
	public Ship() {
		super(ship, pos, rot);

	}
	/**
	 * Przywraca statek (niezależnie od kształtu) do początkowej pozycji
	 */
	public void reset() {
		
		this.rotation = 0;
		this.position = new Point(getShipPosition("x"), getShipPosition("y"));
		this.thrust = new Polygon(boost, new Point(pos.x -15,pos.y), rot);
	}

	
	/**
         * Pozwala na identyfikację przycisku z klawiatury (za pomocą id przycisku) i oppowiednią reakcję
         * @param e 
         */
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
		case 80:
			pKey = true;
			
			break;
                case 87:
                        wKey= true;
                        break;
		default:
			otherKey = true;

		}

	}
        /**
         * Pozwala na identyfikację przycisku z klawiatury (za pomocą id przycisku) i oppowiednią reakcję
         * @param e 
         */
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
		
		case 80:
			pKey = false;
			break;
                case 87:
                        wKey= false;
                        break;
		default:
			otherKey = false;

		}
	}

	public void keyTyped(KeyEvent e) {

	}
	/**
	 * Metoda move: pozwala na sterowanie statkiem 
	 * Gdy statek wyleci po za granicę, jest namalowywany po drugiej stronie
         * Prędkość statku jest pobierana z pliku config.txt
	 */
	public void move(int level) {
                ReadFile rf = new ReadFile();
                rf.openFile();
                rf.readFile();
                
		
		double prevPosX = position.x;
		double prevPosY = position.y;
		double speed = 0;
		if (upKey) {
			//prędkość się zwiększa z wciśniętą spacją
			if (space) {
				speed = 5*rf.GetObjectlVelocity(level, "ship");
			} else {
				speed = 2*rf.GetObjectlVelocity(level, "ship");
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