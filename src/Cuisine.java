import java.util.ArrayList;
import java.util.HashMap;

public class Cuisine {
	private final String CuisineName;
	HashMap <String, Dish> dishes= new HashMap<String, Dish>();
	ArrayList<String> dishes_list = new ArrayList<String>();
	public Cuisine(String name){
		this.CuisineName = name;
	}
	public String getCuisineName(){
		return this.CuisineName;
	}
	public HashMap <String, Dish> getAllDishes(){
		return dishes;
	}
	public void addDish(String name, String price){
		dishes.put(name, new Dish(name, price));
		dishes_list.add(name);
		
	}
	public ArrayList<String> getDishesList(){
		return this.dishes_list;
	}
	public Dish getDish(String name){
		return dishes.get(name);
	}

}
