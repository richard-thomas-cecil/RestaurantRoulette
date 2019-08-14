

public class RestaurantRoulette {
	public static void main(String[] args) throws Exception {
		
		/*MainInterface startProgram = new MainInterface("Places to eat.eat");
		boolean endProgram = false;
		while(!endProgram) {
			endProgram = startProgram.mainMenu();
		}*/
		
		GraphicalInterface stuff = new GraphicalInterface("Places to eat.eat");
		
		stuff.buildWindow();
		
	}
}
