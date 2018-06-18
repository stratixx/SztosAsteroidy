package asteroidymodyfikacja;

/**
 * KLASA: fastMode 
 * OPIS:  Statek jest zastępowany obiektem typu FastMode przy wciśnięciu spacji podczas gry
 * Klasa rozszerza klasę Ship, czyli zachwoanie obiektu tego typu jest po za kształtem identyczne 
 */
public class fastMode extends Ship{
	static Point[] fm = {new Point(0,0), new Point(10,10), new Point (0,20),
		new Point(-12,15),new Point(-10,12),new Point (-20,10),
		new Point(-10,8), new Point(-12,5)};
	
	static Point pos = new Point(Ship.pos.x-16,Ship.pos.y);
	public fastMode(){
		super();
		this.ship = fm;
	}
	
}
