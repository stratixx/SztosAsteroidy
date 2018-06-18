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
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;



public class ReadFile {
	  int height;
	  int width;
	protected  int x;
	protected  int y;
        private Scanner sc;
        List list;
/**
 * 	Konstruktor klasy Scaner: ustawia docelowa sciezke do pliku konfiguracyjnego;
 *  pozwala ustawic dogodne parametry jak naprzyklad polozenie obiektu
 */
        public void openFile(){
            try{
               
                
                sc= new Scanner (new File("config.txt"));
               
               
                
    }
            catch(Exception e){
                System.out.println("could not open file");
        }
        }
        
        
        
        public void readFile(){
            list = new ArrayList();
            while (sc.hasNext()){
                
                int i = 0;
                double d = 0.0;
			if (sc.hasNext("#")) {
				sc.nextLine();
                                continue;
			}
                        
                        String s = sc.next();
                        
                        if(sc.hasNextInt())
                        {
                          i = sc.nextInt();
                          list.add(i);
                        }
                        
                        if(sc.hasNextDouble())
                        {
                         d= sc.nextDouble();
                         list.add(d);
                        }
                        if(sc.hasNextDouble())
                        {
                         d= sc.nextDouble();
                         list.add(d);
                        }
                        if(sc.hasNextInt())
                        {
                          i = sc.nextInt();
                          list.add(i);
                        }
                        
                        
                        switch(s){
                        case "HEIGHT":
                        height=i ;
                       // System.out.println(height);
                        break;
                        case "WIDTH":
                        width = i; 
                       // System.out.println(width);
                        break;
                        case "X":
                            x= i;
                            //System.out.println(x);
                            break;
                        case "Y":
                            y=i;
                            //System.out.println(y);
                            break;
                        case "LEVELONE":
                            //level[0]=1;
                            break;
                            
                        case "LEVELTWO":
                            //level[1]=2;
                            break;
                        case "LEVELTHREE":
                            //level[2]=3;
                            break;
                        case "LEVELFOUR":
                           // level[3]=4;
                            break;
                        case "LEVELFIVE":
                           // level[4]=5;
                            break;
                        default:
			    throw new IllegalArgumentException("Unknown sign in configuration file");
                        }
                      }
             
             int astvel=(int) list.get(23);
             
                    sc.close();
                    }
        
       public static void write(String[] input) throws IOException{
		String stat = "results.txt";
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
		String stat = "results.txt";
		FileReader read = new FileReader(stat);
		String[] lines = new String[5];
		
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
			//score[i-1] =  Integer.parseInt(stat[i].substring(3));
                        score[i-1] =  Integer.parseInt(stat[i-1]);
			//temp[i-1] =   Integer.parseInt(stat[i].substring(3));
                        temp[i-1] =   Integer.parseInt(stat[i-1]);
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
			stat[i-1] = i + ". "  + score[i-1];
		}
        
			
		
			
		
		write(stat);
		
	}
        
        
           
        
        
       
    
        
        
        
        public int GetAstNumber(int level){
            int astNumber;
             switch(level){
                 case 1:
                     astNumber=(int) list.get(4);
                     break;
                 case 2:
                     astNumber=(int) list.get(8);
                     break;
                 case 3:
                     astNumber=(int) list.get(12);
                     break;
                 case 4:
                     astNumber=(int) list.get(16);
                     break;
                 case 5:
                     astNumber=(int) list.get(20);
                     break;
                 default:
                     astNumber=4;
                     throw new IllegalArgumentException("Wrong level number entered");
                     
             }
             return astNumber;
            
            
        }
        
        public double GetObjectlVelocity(int level, String objectName){
            double astVel, shipVel;
            if(objectName.equals("asteroid"))
            {
             switch(level){
                 case 1:
                     astVel=(double) list.get(5);
                     break;
                 case 2:
                      astVel=(double) list.get(9);
                     break;
                 case 3:
                      astVel=(double) list.get(13);
                     break;
                 case 4:
                      astVel=(double) list.get(17);
                     break;
                 case 5:
                      astVel=(double) list.get(21);
                     break;
                 default:
                     astVel=0.5;
                    throw new IllegalArgumentException("Wrong level number");
                     
             }
             return astVel;
            }
            else if(objectName.equals("ship")){
                switch(level){
                 case 1:
                     shipVel=(double) list.get(6);
                     break;
                 case 2:
                      shipVel=(double) list.get(10);
                     break;
                 case 3:
                      shipVel=(double) list.get(14);
                     break;
                 case 4:
                      shipVel=(double) list.get(18);
                     break;
                 case 5:
                      shipVel=(double) list.get(22);
                     break;
                 default:
                     shipVel=0.5;
                     throw new IllegalArgumentException("Wrong level number");
                     
             }
                return shipVel;
            }
            else 
                throw new IllegalArgumentException("Wrong object name entered");
              
            
            
        }
        
        public int getPlayTime(int level){
            int time;
             switch(level){
                 case 1:
                     time=(int) list.get(7);
                     break;
                 case 2:
                    time=(int) list.get(11);
                     break;
                 case 3:
                     time=(int) list.get(15);
                     break;
                 case 4:
                     time=(int) list.get(19);
                     break;
                 case 5:
                     time=(int) list.get(23);
                     break;
                 default:
                     time=3;
                     throw new IllegalArgumentException("Wrong level number entered");
                     
             }
             return time;
            
            
        }
        
        public int getTotalGameTime(){
            int totTime=(int)list.get(7)+(int)list.get(11)+(int)list.get(15)+(int)list.get(19)+(int)list.get(23);
            return totTime;
        }
        
        
        
        }
        
        
            

    