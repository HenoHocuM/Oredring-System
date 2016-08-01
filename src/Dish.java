
public class Dish {
	private String DishName;
	//private String Cuisine;
	private Double DishPrice;
	public Dish(String name, String price){
		DishName = name;
		//Cuisine = cuisine;
		DishPrice = Double.parseDouble(price);
	}
	public String getName(){
		return this.DishName;
	}
	public Double getPrice(){
		return this.DishPrice;
	}

}
