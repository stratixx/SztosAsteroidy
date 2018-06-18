package asteroidymodyfikacja;


public class boost extends Ship{
	static Point[] boost = {new Point(0,0), new Point(10,10), new Point (0,20),
		new Point(-12,15),new Point(-10,12),new Point (-20,10),
		new Point(-10,8), new Point(-12,5)};
	
	static Point pos = new Point(Ship.pos.x-16,Ship.pos.y);
	public boost(){
		super();
		this.ship = boost;
	}
	
}
