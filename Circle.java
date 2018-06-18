/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidymodyfikacja;
//martapalka
import java.awt.Graphics;

/**
 * KLASA: Circle
 * OPIS: Klasa po której dziedziczy klasa Bullet
 */
public class Circle {
	
	public Point position;
	public int radius;
	
	public Circle(Point pos, int r){
		position = pos;
		radius = r;
		
	}
	
	
	/**
         * Metoda umożliwiająca malowanie obiektu
         * @param brush typu Garphics
         */
	public void paint(Graphics brush){
		brush.drawOval((int)position.x, (int)position.y, radius, radius);
		
	}
}