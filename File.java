/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidymodyfikacja;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class File {
	
	public static void write(String[] input) throws IOException{
		String stat = "stat.txt";
		FileWriter writer = new FileWriter(stat);
		
		try{
			BufferedWriter buff = new BufferedWriter(writer);
			
			for(int i = 0; i < input.length; i++){
				buff.write(input[i]);
				buff.newLine();
			}
			
			buff.flush();
			buff.close();
			
		}
		catch (IOException e){
			System.err.println("Error");
		}
	}
	
	public static String[] read() throws FileNotFoundException {
		String stat = "stat.txt";
		FileReader read = new FileReader(stat);
		String[] lines = new String[12];
		
		try{
			BufferedReader buff = new BufferedReader(read);
			
			for(int i = 0; i < lines.length; i++){
				lines[i] = buff.readLine();
			}	
			
			buff.close();
			
		}
		catch (IOException e){
			System.err.println("Error");
		}
		return lines;
	}
	
	public static void highscore(int n) throws IOException{
		//read file first
		String[] stat = read();
		int pos = 0;
		//create an array of the score 
		int[] score = new int[5];
		int[] temp =  new int[score.length + 1];
		boolean replace = false;	
		for(int i = 1; i < 6; i++){
			score[i-1] =  Integer.parseInt(stat[i].substring(3));
			temp[i-1] =   Integer.parseInt(stat[i].substring(3));
		}
		for(int i = 0; i < score.length; i++){
			if(n > score[i] && !replace){
				score[i] = n;
				pos = i;
				replace = true;;
			}
		}

		if(replace){
			for(int i = pos + 1; i < score.length; i++){
				score[i] = temp[i-1];
			}
		}
		
		for(int i = 1; i < 6; i++){
			stat[i] = i + ". "  + score[i-1];
		}
			
		
			
		
		write(stat);
		
	}
	
	public static void destroyed(int asteroid, int ship) throws IOException{
		String[] stat = read();
		String line7 = "Total Asteroids Destroyed: ";
		String line8 = "Total Ships Destroyed: ";
		String line10 = "Total Asteroids Destroyed this round: ";
		String line11 = "Total Ships Destroyed this round: ";
	
		stat[7] = "Total Asteroids Destroyed: " + (Integer.parseInt(stat[7].substring(line7.length())) + asteroid);
		stat[8] = "Total Ships Destroyed: " + (Integer.parseInt(stat[8].substring(line8.length())) + ship);
		stat[10] = "Total Asteroids Destroyed this round: " + asteroid;
		stat[11] = "Total Ships Destroyed this round: " + ship;
		write(stat);
	}

    File(String configtxt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
	

}