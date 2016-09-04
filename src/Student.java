/**
 * Brett Nelson bnelso26@calpoly.edu
 * 3/5/16
 * Project 4
 */

//class to implement a student object
public class Student {

	//holds id number of student
	private Long id;
	//holds last name of student
	private String lastName;
	
	//constructor to create student object
	public Student(long id, String lastName){
		this.id = id;
		this.lastName = lastName;
	}
	
	//method to determine if one student equals another
	public boolean equals(Object other){
		if(other == null)
			return false;
		if(other.getClass() != this.getClass())
			return false;
		Student s = (Student) other;
		return this.id.equals(s.id);
	}
	
	//method to provide String form for Student object
	public String toString(){
		return "{ id: " + this.id + ", name: " + this.lastName + " }";
	}
	
	//method to return the hashCode for a student object
	public int hashCode(){
		return id.hashCode();
	}
}
