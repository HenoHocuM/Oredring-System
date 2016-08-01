
public class Drink {
	private String DrinkName;
	private String DrinkType;
	private Double DrinkPrice;
	
	public Drink(String name, String price){
		this.DrinkName = name;
		//this.DrinkType = type;
		this.DrinkPrice = Double.parseDouble(price);		
	}

	public String getName() {
		return DrinkName;
	}

	public String getType() {
		return DrinkType;
	}

	public Double getPrice() {
		return DrinkPrice;
	}
	

}