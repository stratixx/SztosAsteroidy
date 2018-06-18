package asteroidymodyfikacja;
/**
*KLASA: Point
*OPIS: Klasa używana do przechowywania i otrzymywania dostępu do zmiennych
*/

class Point implements Cloneable {
  double x,y;
  public Point(double inX, double inY) { x = inX; y = inY; }
  
  public Point clone() {
	  return new Point(x, y);
  }
}

