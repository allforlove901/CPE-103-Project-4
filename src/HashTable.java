/**
 * Brett Nelson bnelso26@calpoly.edu
 * 3/5/16
 * Project 4
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

//Class to implement a Hash Table with open addressing and quadratic probing
public class HashTable {

	//class to use for saving objects to the table
	private class HashEntry{
		
		//Object to hold element of HashEntry
		public Object element;
		//boolean to show if an entry is active or not
		public boolean isActive;
		
		//constructor to create HashEntry
		public HashEntry(Object element){
			this.element = element;
			isActive = true;
		}
	}
	
	//array to hold objects in the table
	private HashEntry[] tableArr;
	//integer to hold number of elements in the table
	private int numOccupied = 0;
	
	//constructor to set size of table to next prime number
	public HashTable(int collectionSize){
		tableArr = new HashEntry[getNextPrime(collectionSize)];
	}
	
	//private method to get next prime number
	private int getNextPrime(int n){
		while(!isPrime(n)){
			n++;
		}
		return n;
	}
	
	//checks whether an integer is prime or not
	private boolean isPrime(int n){
	    if (n%2 == 0) 
	    	return false;
	    for(int i=3; i*i<=n; i+=2) {
	        if(n%i == 0)
	            return false;
	    }
	    return true;
	}
	
	@SuppressWarnings("rawtypes")
	//class to implement java's iterator interface
	private class Iter implements Iterator{
		
		//integer to keep index of iterator position
		public int cursor;
		
		//constructor to create iterator
		public Iter(){
			cursor = 0;
			while(cursor < tableArr.length && 
			(tableArr[cursor] == null || !tableArr[cursor].isActive)){
				cursor++;
			}
		}
		
		//checks to see if iterator has next element
		public boolean hasNext(){
			if(cursor < tableArr.length){
				return true;
			}
			return false;
		}
		
		//iterates to next element and returns previous element
		public Object next(){
			if(!hasNext()){
				throw new NoSuchElementException();
			}
			
			Object item = tableArr[cursor].element;
			
			cursor++;
			while(cursor < tableArr.length && 
			(tableArr[cursor] == null || !tableArr[cursor].isActive)){
				cursor++;
			}
			
			return item;
		}
		
		//remove method does nothing
		public void remove(){
			//do nothing
		}
	}
	
	//method to insert item into table
	public void insert(Object item){
		int index = getIndex(item);
		
		if(tableArr[index] == null){
			tableArr[index] = new HashEntry(item);
			numOccupied++;
			
			if(numOccupied >= tableArr.length/2){
				rehash();
			}
		}else if(!tableArr[index].isActive){
			tableArr[index].isActive = true;
			
			if(numOccupied >= tableArr.length/2){
				rehash();
			}
		}
	}
	
	//private method to get hash value of an object
	private int hash(Object m){
		return Math.abs(m.hashCode()) % tableArr.length;
	}
	
	//method to get index of object's actual position in table
	private int getIndex(Object m){
		int hash = hash(m);
		int index = hash;
		int i = 0;
		
		while(tableArr[index] != null && !m.equals(tableArr[index].element)){
			i++;
			index = (hash + (i*i)) % tableArr.length;
		}
		
		return index;
	}
	
	//private method to resize the table
	private void rehash(){
		HashEntry[] store = tableArr;
		tableArr = new HashEntry[getNextPrime(2*store.length)];
		numOccupied = 0;
		
		for(int i=0; i<store.length; i++){
			if(store[i] != null && store[i].isActive)
			{
				int index = getIndex(store[i].element);
				tableArr[index] = store[i];
				numOccupied++;
			}
		}
	}
	
	//method to delete item from table
	public void delete(Object item){
		int index = getIndex(item);
		if(tableArr[index] != null && tableArr[index].isActive){
			tableArr[index].isActive = false;
		}
	}
	
	//method to find specific item in table
	public Object find(Object item){
		int index = getIndex(item);
		if(tableArr[index] != null && tableArr[index].isActive == true)
	    {
	       return tableArr[index].element;
	    }
	    return null;
	}
	
	//method to return number of elements in a collection
	public int elementCount(){
		int num = 0;
	      for(int i=0; i<tableArr.length; i++)
	      {
	         if(tableArr[i] != null && tableArr[i].isActive)
	         {
	            num++;
	         }
	      }
	      return num;
	}
	
	//method to check if the collection is empty
	public boolean isEmpty(){
		for(int i = 0; i<tableArr.length; i++){
			if(tableArr[i] != null)
				if(tableArr[i].isActive)
					return false;
		}
		return true;
		//if(numOccupied == 0)
	      //{
	        // return true;
	      //}
	      //return false;
	}
	
	//method to make the table empty
	public void makeEmpty(){
		numOccupied = 0;
		tableArr = new HashEntry[tableArr.length];
	}
	
	//method to print the table content
	public void printTable(){
		for(int i=0; i<tableArr.length; i++){
			System.out.print("[" + i + "]: ");
			if(tableArr[i] != null){
				System.out.print(tableArr[i].element + ", ");
				if(tableArr[i].isActive)
					System.out.print("active");
				else
					System.out.print("inactive");
			}
			else{
				System.out.print("empty");
			}
			System.out.print("\r");
		}
	}
	
	//method to create iterator for collection
	public Iterator iterator(){
		return new Iter();
	}
}
