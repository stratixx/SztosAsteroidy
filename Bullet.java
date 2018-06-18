package asteroidymodyfikacja;


/**
 * KLASA: Bullet
 * OPIS: Odpowiedzialna jest za tworzenie pocisku
 * 1.Obiekt pocisku jest sprzężony ze statkiem (ich pozycje są jednakowe jeśli pocisk nie został wystrzelony)
 * 2.Zmienna boolean shoot określa stan pocisku 
 * 3.Zmienna ounter pozwala sterować zasięgiem i opóźnieniem pocisku- ulega zmniejszeniu w zależności od poziomu, gdyż jest coraz więcej asteroid
 */
public class Bullet extends Circle {
	static Point pos = Ship.pos;
	boolean shoot = false;
	int counter = 10;
        int counterLimit=0;
	static double rot;
	Point[] sqr = { new Point(0, 0), new Point(0, 5), new Point(5, 5),
			new Point(5, 0) };
	Polygon square;
        
        /**
         * Konstruktor klasy Bullet 
         * Zostaje utworzony nowy wielokąt (kwadrat) o określonej pozycji i rotacji
         */
        public Bullet() {
		super(pos, 5);
		square = new Polygon(sqr, position, rot);

	}
        /**
         * Metoda pozwalająca na zmianę pola counter w zależności od poziomu
         * @param level 
         */
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
        /**
         * 
         * @param level
         * @return Zwraca ograniczenie countera dla danego poziomu
         */
        public int getCounterLimit(int level){
            return counterLimit;
        }
        /**
         * Tworzona jest tablica pocisków
         * @param n ile pocisków ma zostać utworzonych
         * @return zwraca tablicę pocisków
         */
	public static Bullet[] bullets(int n) {
		Bullet[] bullets = new Bullet[n];
		for (int i = 0; i < n; i++) {
			bullets[i] = new Bullet();
		}
		return bullets;
	}
        /**
         * Metoda umożliwiająca ruch pocisku (jeśli shoot=0 to wraz ze statkiem jeśli natomiast wynosi 1, to odzielnie)
         * @param s-statek
         * @param level 
         */
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