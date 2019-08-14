import java.util.Scanner;
import java.util.Random;

public class MainInterface {

	private Scanner keyboardInput;
	private RestaurantSelector restaurants;
	
	MainInterface(String fileName) throws Exception{
		this.restaurants = new RestaurantSelector(fileName);
		this.keyboardInput = new Scanner(System.in);	
		restaurants.initialize();
	}
	
	public boolean mainMenu() throws Exception {			
		
		System.out.println("Choose an option from below:");
		System.out.println("1) Choose Restaurant");
		System.out.println("2) Add new Restaurant");
		System.out.println("3) Remove Restaurant");
		System.out.println("4) Show Available Restaurants");
		System.out.println("5) Exit Program");
		
		int option = keyboardInput.nextInt();
		
		boolean endProgram = selectionManager(option);
		
		return endProgram;
		
	}
	
	private boolean selectionManager(int selection) throws Exception {
		switch(selection) {
		case 1:
			makeSelection();
			return false;
		case 2:
			addRestaurant();
			return false;
		case 3:
			removeRestaurant();
			return false;
		case 4:
			showCurrentOptions();
			return false;
		case 5:
			System.out.println("Exiting");
			return true;
		default:
			System.out.println("Not a valid option");
			return false;
			
		}
	}
	
	private void makeSelection() {
		Random randomSelection = new Random();
		int selection = randomSelection.nextInt(restaurants.getRestaurantNumber());
		System.out.println("You should eat at " + restaurants.getSelectedRestaurant(selection));
	}
	
	private void addRestaurant() throws Exception {
		System.out.println("Enter New Restaurant Name: ");
		String newRestaurant = keyboardInput.next();
		restaurants.addRestaurant(newRestaurant);
	}
	
	private void removeRestaurant() throws Exception {
		System.out.println("Enter Restaurant to Remove: ");
		String oldRestaurant = keyboardInput.next();
		restaurants.removeRestaurant(oldRestaurant);
		keyboardInput.nextLine();
	}
	
	private void showCurrentOptions() {
		restaurants.listRestaurant();
	}
	
}
