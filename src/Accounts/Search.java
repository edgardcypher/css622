package Accounts;

import java.util.ArrayList;
import java.util.List;

/*generic class with a generic method which to search an object from a list
 * by using compareTo method  */
public class Search<T,S> {
	
	public <T,S> Search(){}
	
	/*this function is a generic function which helps to search an Account by it associated name 
	 * or a book by author name*/
	public <T extends Comparable<S>> List<T> searchByName(List<T> t, S s) {
		// precondition: there should be a list of Accounts or Books
		// postcondition: list of books or accounts which match the searched name
		List<T> foundList = new ArrayList<T>();
		for (T t2 : t) {
			if(t2.compareTo(s) == 1) {
				foundList.add(t2);
			}
		}
		return foundList;
	}
}
