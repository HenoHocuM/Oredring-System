
public class Dessert {
	private String Name;
	private Double Price;
	
	public Dessert(String name, String price){
		this.Name = name;
		this.Price = Double.parseDouble(price);
	}
	public String getName(){
		return this.Name;
	}
	public Double getPrice(){
		return this.Price;
	}
}
