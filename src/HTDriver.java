/**
 * Brett Nelson bnelso26@calpoly.edu
 * 3/5/16
 * Project 4
 */

import java.util.*;
import java.io.*;
import java.util.NoSuchElementException;

//class to implement driver to test functionality of HashTable class
public class HTDriver {

	//main method
	public static void main(String args[]){
		
		//create new scanner
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter input file name:");
		String input = sc.nextLine();
		
		try{
			System.out.println("works1");
			File file = new File(input);
			Scanner reader = new Scanner(file);
			
			int collectionSize = Integer.parseInt(reader.nextLine());
			HashTable hashTable = new HashTable(collectionSize);
			
			while(reader.hasNext()){
				String[] line = reader.nextLine().split(" ");
				if(line.length == 2 && Integer.parseInt(line[0]) > 0){
					try{
						long id = Integer.parseInt(line[0]);
						//System.out.println(id + " " + line[1]);
						hashTable.insert(new Student(id, line[1]));
					}catch(Exception e){
						//does nothing
					}
				}
			}
			
			boolean quit = false;
			System.out.println("Menu of operations:");
			System.out.println("  a - add the element");
			System.out.println("  d - delete the element");
			System.out.println("  f - find and retrieve the element");
			System.out.println("  n - get the number of elements in the collection");
			System.out.println("  e - check if the collection is empty");
			System.out.println("  k - make the hash table empty");
			System.out.println("  p - print the content of the hash table");
			System.out.println("  o - output the elements of the collection");
			System.out.println("  q - Quit the program");
			
			while(!quit){
	    		System.out.println("Choose an option");
	    		String response =  sc.nextLine();
	    		
	    		switch(response){
	    		
	    		case"a":
	    			System.out.println("Enter a Student ID and Last Name:");
	    			if(sc.hasNext()){
	    				String[] line = sc.nextLine().split(" ");
	    				if(line.length == 2){
	    					try{
	    						int id = Integer.parseInt(line[0]);
	    						Student s = new Student(id, line[1]);
	    						hashTable.insert(s);
	                            System.out.println(s.toString() + " added");
	    					}catch(Exception e){
	    						System.out.println("Error: Incorrect input");
	    					}
	    				}else{
    						System.out.println("Error: Incorrect input");
	    				}
	    			}
	    			break;
	    			
	    		case"d":
	    			System.out.println("Enter a Student ID");
	    			if(sc.hasNext()){
	    				String[] line = sc.nextLine().split(" ");
	    				if(line.length == 1){
	    					try{
	    						int id = Integer.parseInt(line[0]);
	    						Student s = new Student(id, "");
	    						Student store = (Student) hashTable.find(s);
	    						hashTable.delete(s);
	    						
	    						if(store != null)
	    							System.out.println(store.toString() + " deleted");
	    						else
	    							System.out.println("ID not found");
	    					}catch(Exception e){
	    						System.out.println("Error: Incorrect input");
	    					}
	    				}else{
    						System.out.println("Error: Incorrect input");
	    				}
	    			}
	    			break;
	    			
	    		case"f":
	    			System.out.println("Enter a Student ID");
	    			if(sc.hasNext()){
	    				String[] line = sc.nextLine().split(" ");
	    				if(line.length == 1){
	    					try{
	    						int id = Integer.parseInt(line[0]);
	    						//System.out.println(line[0]);
	    						Student s = new Student(id, "");
	    						Student store = (Student)hashTable.find(s);
	    						if(store != null)
		                            System.out.println(store.toString() + " found");
	    						else
		                            System.out.println("Id not found");
	    					}catch(Exception e){
	    						System.out.println("Error: Incorrect input1");
	    					}
	    				}else{
    						System.out.println("Error: Incorrect input2");
	    				}
	    			}
	    			break;
	    			
	    		case"n":
	    			System.out.println(hashTable.elementCount() + " elements in collection");
	    			break;
	    			
	    		case"e":
	    			if(hashTable.isEmpty())
	    				System.out.println("Empty");
	    			else
	    				System.out.println("Not Empty");
	    			break;
	    			
	    		case"k":
	    			hashTable.makeEmpty();
	    			System.out.println("Table emptied");
	    			break;
	    			
	    		case"p":
	    			hashTable.printTable();
	    			break;
	    			
	    		case"o":
	    			Iterator iter = hashTable.iterator();
	    			while(iter.hasNext())
	    				System.out.println(iter.next());
	    			break;
	    			
	    		case"q":
	    			System.out.println("quitting");
	    			quit = true;
	    			break;
	    			
	    		default:
	    			System.out.println("invalid choice");
	    			break;
	    		}
			}
		}catch(Exception e){
			System.out.println("File not found");
		}
	}
}
