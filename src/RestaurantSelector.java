import java.util.ArrayList;

public class RestaurantSelector {

	private ArrayList<Restaurant> restaurantChoices;
	private FileOperationManager choicesFile;
	
	RestaurantSelector(String fileName) throws Exception{
		this.choicesFile = new FileOperationManager(fileName);
		this.restaurantChoices = new ArrayList<Restaurant>();
	}
	
	public void initialize() throws Exception {
		String nextRestaurant;
		
		if(!choicesFile.doesExist()) {
			choicesFile.createFile();
		}
		
		choicesFile.openFileRead();
		
		while((nextRestaurant = choicesFile.readFile()) != null) {
			restaurantChoices.add(new Restaurant(nextRestaurant));
		}
		
		choicesFile.closeFileRead();
	}
	
	private void buildChoicesFile() throws Exception {
		choicesFile.openFileWrite();
		
		for(int i=0; i < restaurantChoices.size(); i++) {
			choicesFile.writeFile(restaurantChoices.get(i).getName());
		}
		
		choicesFile.closeFileWrite();
	}
	
	public void listRestaurant() {
		for(int i=0;i < restaurantChoices.size(); i++)
			System.out.println(restaurantChoices.get(i).getName());
	}
	
	public int getRestaurantNumber() {
		return restaurantChoices.size();
	}
	
	public String getSelectedRestaurant(int selection) {
		return restaurantChoices.get(selection).getName();
	}
	
	public void addRestaurant(String newRestaurant) throws Exception {
		choicesFile.openFileWrite();
		
		restaurantChoices.add(new Restaurant(newRestaurant));
		choicesFile.writeFile(newRestaurant);
		
		choicesFile.closeFileWrite();
	}
	
	public boolean removeRestaurant(String oldRestaurant) throws Exception {
		int i = 0;
		
		while(i < restaurantChoices.size() && !oldRestaurant.equals(restaurantChoices.get(i).getName())){
			//System.out.println(!oldRestaurant.equals(restaurantChoices.get(i).getName()));
			i++;
		} 
		
		if(i>=restaurantChoices.size()) {
			System.out.println("Restaurant Not in List");
			return false;
		}
		else {
			restaurantChoices.remove(i);
			choicesFile.overWriteFile();
			buildChoicesFile();
			return true;
		}
			
	}
}
