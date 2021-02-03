package DisplayMenu;

public class DisplayMemberMainMenu extends Menu {
	
	public  void  displayMenu(String username) {
		System.out.println("\nMember Main Menu");
		System.out.println("==================");
		System.out.println("id: "+ getMemberId()+" username: "+username);
		System.out.println("\n1- View Account information");
		System.out.println("2- Update Account");
		System.out.println("3- Checkout Book");
		System.out.println("4- Search catalog");
		System.out.println("5- Cancel membership");
		System.out.println("6- Logout");
		System.out.print("\nYour choice:");
	}

}
