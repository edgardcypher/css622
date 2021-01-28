package DisplayMenu;

public class DisplayLiberianMainMenu extends Menu {
	
	public  void displayMenu() {
		System.out.println("\nLiberian Main Menu");
		System.out.println("====================");
		System.out.println("id:"+ getMemberId());
		System.out.println("\n1- Add a book");
		System.out.println("2- Remove a book");
		System.out.println("3- Manage a membership account");
		System.out.println("4- Issue a book");
		System.out.println("5- Check overdue issue book");
		System.out.println("6- Logout");
	}
}
