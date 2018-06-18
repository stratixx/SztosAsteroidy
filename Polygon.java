package asteroidymodyfikacja;

import java.awt.Color;
import java.awt.Graphics;

/**
*KLASA: Polygon
*OPIS: Wielokąt to zbiór punktów w przestrzeni zdefiniowana przez zestaw
*takich punkty, przesunięcie (offset) i rotację (obrót). Przesunięcie to
*odległość między punktem początkowym a środkiem wielokąta.
*Rotacja jest mierzona w skli 0-360.
*"Polygonami" są asteroidy oraz statki
*/

class Polygon {
  protected Point[] shape;  
  public  Point position;   // przesunięcie
  public double rotation; //zero stopni oznacza skierowanie obiektu w prawo
  
  public Polygon(Point[] inShape, Point inPosition, double inRotation) {
    shape = inShape;
    position = inPosition;
    rotation = inRotation;
    
    //Znajdowanie najbardziej wysuniętego na lewo punktu wielokąta
    Point origin = shape[0].clone();
    for (Point p : shape) {
      if (p.x < origin.x) origin.x = p.x;
      if (p.y < origin.y) origin.y = p.y;
    }
    
    // Wszystkie inne punkty są ustawiane względem odnalezionego powyżej punktu
    for (Point p : shape) {
      p.x -= origin.x;
      p.y -= origin.y;
    }
  }
  
  /**
   * Metoda umożliwiająca ustawienie rotacji oraz pozyji wielokąta
   * Zwraca wektor punktów potrzebny do implementacji powyższych zjawisk
   */
  public Point[] getPoints() {
    Point center = findCenter();
    Point[] points = new Point[shape.length];
    for (int i = 0; i < shape.length; i++) {

      Point p = shape[i];
      double x = ((p.x-center.x) * Math.cos(Math.toRadians(rotation)))
               - ((p.y-center.y) * Math.sin(Math.toRadians(rotation)))
               + center.x/2 + position.x;
      double y = ((p.x-center.x) * Math.sin(Math.toRadians(rotation)))
               + ((p.y-center.y) * Math.cos(Math.toRadians(rotation)))
               + center.y/2 + position.y;
      points[i] = new Point(x,y);
    }
    return points;
  }
  
  
  public boolean contains(Point point) {
    Point[] points = getPoints();
    double crossingNumber = 0;
    for (int i = 0, j = 1; i < shape.length; i++, j=(j+1)%shape.length) {
      if ((((points[i].x < point.x) && (point.x <= points[j].x)) ||
           ((points[j].x < point.x) && (point.x <= points[i].x))) &&
          (point.y > points[i].y + (points[j].y-points[i].y)/
           (points[j].x - points[i].x) * (point.x - points[i].x))) {
        crossingNumber++;
      }
    }
    return crossingNumber%2 == 1;
  }
  
  public void rotate(int degrees) {rotation = (rotation+degrees)%360;}
  
  
  
 
  private double findArea() {
    double sum = 0;
    for (int i = 0, j = 1; i < shape.length; i++, j=(j+1)%shape.length) {
      sum += shape[i].x*shape[j].y-shape[j].x*shape[i].y;
    }
    return Math.abs(sum/2);
  }
  
  
  private Point findCenter() {
    Point sum = new Point(0,0);
    for (int i = 0, j = 1; i < shape.length; i++, j=(j+1)%shape.length) {
      sum.x += (shape[i].x + shape[j].x)
               * (shape[i].x * shape[j].y - shape[j].x * shape[i].y);
      sum.y += (shape[i].y + shape[j].y)
               * (shape[i].x * shape[j].y - shape[j].x * shape[i].y);
    }
    double area = findArea();
    return new Point(Math.abs(sum.x/(6*area)),Math.abs(sum.y/(6*area)));
  }
  
  
  /**
   * Metoda umożliwiająca narysowanie wielokąta
   * @param brush 
   */
  public void paint(Graphics brush){
	  int[] xPoint = new int[shape.length];
	  int[] yPoint = new int[shape.length];
	  int segments = shape.length;
	  Point[] points = getPoints();
	  for(int i = 0; i < shape.length; i++){ 
		 xPoint[i] =  (int) points[i].x;
		 yPoint[i] = (int) points[i].y;
	  }
	  brush.drawPolygon(xPoint,yPoint,segments);
  }
  
  /**
   * Określa czy doszło do zderzenia dwóch wielokątów; metoda przydatna przy implementacji zderzeń w klasie Asteroids()
   * @param other - drugi obiekt typu Polygon
   * @return true jeśli dwa obiekty typu polygon miały ze sobą kontakt
   */
  public  boolean intersection(Polygon other){
	  Point[] array = other.getPoints();
	  int yes = 0;
	  //innocent until proven guilty
	  boolean output = false;
	  for(int i = 0; i < array.length; i++){
		  if(this.contains(array[i])){
			  yes++;
                          
                }
	  }
	  if(yes > 0){
		  output = true;
	  }
	  return output;
	 
  
      
  }
      
  }
  
 
  
