package DisplayMenu;

public class DisplayLiberianMainMenu extends Menu {
	
	public  void displayMenu(String username) {
		System.out.println("\nLiberian Main Menu");
		System.out.println("====================");
		System.out.println("id:"+ getMemberId()+" username: "+username);
		System.out.println("\n1- Add a book");
		System.out.println("2- List all books");
		System.out.println("3- Issue a book");
		System.out.println("4- Check all issued books");
		System.out.println("5- add an account");
		System.out.println("6- List all accounts");
		System.out.println("7- delete an account");
		System.out.println("8- search an account by name or username");
		System.out.println("9- search a book by author name");
		System.out.println("10- Logout");
		System.out.print("\nYour choice:");
	}
}
