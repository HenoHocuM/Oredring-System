
public class Order {
	Boolean extraice = false;
	Boolean extralemon = false;
	Dish dish = null;
	Drink drink = null;
	Dessert dessert = null;
	Boolean order_executed= false;
	
	public void addIce(){
		this.extraice = true;
	}
	public Boolean issIce(){
		return this.extraice;
	}
	public void addLemon(){
		this.extralemon = true;
	} 
	public Boolean isLemon(){
		return this.extralemon;
	}
	public void orderDish(Dish dish){
		this.dish = dish;
	}
	public Dish getDish(){
		return this.dish;
	}
	public void orderDrink(Drink drink){
		this.drink = drink;
	}
	public Drink getDrink(){
		return this.drink;
	}
	public void orderDessert(Dessert dessert){
		this.dessert = dessert;
	}
	public Dessert getDessert(){
		return this.dessert;
	}
	public void executeOrder(){
		this.order_executed= true;
	}
	public double getTotal(){
		double total = 0.0d;
		if(dish!= null)
		total += dish.getPrice();
		if(drink!=null)
		total += drink.getPrice();
		if(dessert!=null)
		total += dessert.getPrice();
		return total;
	}
}