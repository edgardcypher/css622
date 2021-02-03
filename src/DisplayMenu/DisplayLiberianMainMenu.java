package DisplayMenu;

public class DisplayLiberianMainMenu extends Menu {
	
	public  void displayMenu(String username) {
		System.out.println("\nLiberian Main Menu");
		System.out.println("====================");
		System.out.println("id:"+ getMemberId()+" username: "+username);
		System.out.println("\n1- Add a book");
		System.out.println("2- update a book");
		System.out.println("3- Remove a book");
		System.out.println("4- Issue a book");
		System.out.println("5- Check overdue issue book");
		System.out.println("6- add an account");
		System.out.println("7- remove an account");
		System.out.println("8- Logout");
		System.out.print("\nYour choice:");
	}
}
