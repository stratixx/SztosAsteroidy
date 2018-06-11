/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidymodyfikacja;

/**
 *Klasa która odpowiada za pobieranie danych z pliku konfiguracyjnego
 */


import java.io.BufferedReader;
import java.awt.Color;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;

public class Scaner {
	protected int height;
	protected int width;
	protected int x;
	protected int y;
	protected double rotVelAst;
	protected double moveAngle;
	protected double faceAngle;
	protected double velX;
	protected double velY;
	protected ArrayList <String> list = null;
	
/**
 * 	Konstruktor klasy Scaner: ustawia docelowa sciezke do pliku konfiguracyjnego;
 *  pozwala ustawic dogodne parametry jak naprzyklad polozenie obiektu
 */
	Scaner(String filePath){
		Path path = FileSystems.getDefault().getPath(filePath);
		try(BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
			String line;
			String arr[];
			while((line=reader.readLine())!=null) {
				
				if(line.charAt(0)!='#') {
					arr=line.split(" ");
					switch(arr[0]) {
					case "HEIGHT":
						height = Integer.parseInt(arr[1]);
						break;
					case "WIDTH":
						width = Integer.parseInt(arr[1]);
						break;
					case "X":
						x= Integer.parseInt(arr[1]);
						break;
					case "Y":
						y= Integer.parseInt(arr[1]);
						break;
					case "ROTVELAST":
						rotVelAst = Double.parseDouble(arr[1]);
                                                break;
					case "MOVEANGLE":
						moveAngle = Double.parseDouble(arr[1]);
						break;
					case "FACEANGLE":
						faceAngle =Double.parseDouble(arr[1]);
					    break;
					case "VELX":
						velX= Double.parseDouble(arr[1]);
						break;
					case "VELY":
						velY= Double.parseDouble(arr[1]);
						break;
					case "LIST":
						list = new ArrayList<String>();
						for(int i=0; i<10;i++)
						{
							line = reader.readLine();
							list.add(line);
						}
						break;
					default:
						System.out.println("Nieznany znak w pliku konfiguracyjnym");
							
						
						
					}
				}
			}
		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
	}
	

}
