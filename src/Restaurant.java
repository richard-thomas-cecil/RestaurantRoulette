
public class Restaurant {

	private String restaurantName;
	
	Restaurant(String name){
		this.restaurantName = name;
	}
	
	public void setName(String newName) {
		restaurantName = newName;
	}
	
	public String getName() {
		return restaurantName;
	}
	
}
