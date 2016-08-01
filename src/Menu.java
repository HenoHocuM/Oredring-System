import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.commons.cli.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Menu {
	private Map<String, Cuisine> cuisinesList = new TreeMap<String, Cuisine>();
	private ArrayList <String> cuisines_menu = new ArrayList<String>();
	private Map<String, Dessert> dessertList = new TreeMap<String, Dessert>();
	private ArrayList <String> desserts_menu = new ArrayList<String>();
	private Map<String, Drink> drinkList = new TreeMap<String, Drink>();
	private ArrayList <String> drinks_menu = new ArrayList<String>();
	public Boolean isLoaded = false;
	private Scanner scanner = new Scanner(System.in); 
	private Order order = new Order();
	//String current_quisine = null;
	public void parseMenu(String filename){
		try{
			File file = new File(filename);

			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
		                             .newDocumentBuilder();

			Document doc = dBuilder.parse(file);
			NodeList cuisines = doc.getElementsByTagName("Cuisine");
			for (int i = 0; i< cuisines.getLength(); i++){
				Node cuisineitem = cuisines.item(i);
				String cuisineName = cuisineitem.getAttributes().getNamedItem("name").getNodeValue();
				cuisines_menu.add(cuisineName);
				Cuisine cuisine = new Cuisine(cuisineName);
				 NodeList dishes = cuisineitem.getChildNodes();
				 for (int j = 0; j< dishes.getLength(); j++){
					 Node dish = dishes.item(j);
			         if (dish.getNodeType() == Node.ELEMENT_NODE) {
			            Element elementDish = (Element) dish;
						String DishName = elementDish.getElementsByTagName("name").item(0).getTextContent();
						String DishPrise = elementDish.getElementsByTagName("price").item(0).getTextContent();
						cuisine.addDish(DishName, DishPrise);
					 }
			         
				 }
				 cuisinesList.put(cuisineName,cuisine);
			}
			NodeList desserts = doc.getElementsByTagName("Dessert");
			for (int i = 0; i< desserts.getLength(); i++){
				Node dessertitem = (Node) desserts.item(i);
				 if (dessertitem.getNodeType() == Node.ELEMENT_NODE) {
					 Element elementDessert = (Element) dessertitem;
					 String dessertName = elementDessert.getElementsByTagName("name").item(0).getTextContent();
					 desserts_menu.add(dessertName);
					 String dessertPrise = elementDessert.getElementsByTagName("price").item(0).getTextContent();
					 dessertList.put(dessertName, new Dessert(dessertName,dessertPrise));
				 }	
			}
			NodeList drinks = doc.getElementsByTagName("Drink");
			for (int i = 0; i< drinks.getLength(); i++){
				Node drinksitem = (Node) drinks.item(i);
				 if (drinksitem.getNodeType() == Node.ELEMENT_NODE) {
					 Element elementDrinks = (Element) drinksitem;
					 String drinksName = elementDrinks.getElementsByTagName("name").item(0).getTextContent();
					 String drinksPrise = elementDrinks.getElementsByTagName("price").item(0).getTextContent();
					 drinks_menu.add(drinksName);
					 drinkList.put(drinksName, new Drink(drinksName,drinksPrise));
				 }	
			}
			this.isLoaded = true;
		}
		catch(Exception e){
			System.out.println(e.getMessage()); 	
		}
	}
	public void print_initial_menu(){
		String[] main_menu= {"0: Lunch","1: Drinks"};
		System.out.println("Total: "+this.order.getTotal());
		System.out.println("\t Main Menu");
		for(String menu_item:main_menu){
			System.out.println(menu_item);
		}
		int arg = scanner.nextInt();
		if (arg== 0){
			print_cuisines_menu();	
		}
		else if (arg == 1){
			order_drinks_menu();
			print_initial_menu();
		}
		else {
			print_initial_menu();
		}
		
	}
	public void print_cuisines_menu(){
		System.out.println("Total: "+this.order.getTotal());
		System.out.println("\tCusines");
		for(int i = 0; i< cuisines_menu.size(); i++){
			System.out.println(i+": "+cuisines_menu.get(i));
		}
		System.out.println(cuisines_menu.size()+": Order Dessert");
		int var = scanner.nextInt();
		if (var ==cuisines_menu.size()){
			order_desserts_menu();
		}
		if(var<cuisines_menu.size()){
			String cuisineName = cuisines_menu.get(var);
			Cuisine cuis = cuisinesList.get(cuisineName);
			orderDish(cuis);
		}
		print_initial_menu();
	}
	public void orderDish(Cuisine cuisine){
		ArrayList<String> dishes_menu = cuisine.getDishesList();
		System.out.println("\tDishes");
		for(int i = 0; i< dishes_menu.size(); i++){
			System.out.println(i+": "+dishes_menu.get(i));
		}
		System.out.println("\tOreder a dish:");
		int arg = scanner.nextInt();
		String dish_name = dishes_menu.get(arg);
		Dish selcetdDish = cuisine.getDish(dish_name);
		order.orderDish(selcetdDish);
	}
	public void order_drinks_menu(){
		System.out.println("\tDrinks");
		for(int i = 0; i< drinks_menu.size(); i++){
			System.out.println(i+": "+drinks_menu.get(i));
		}
		System.out.println("\tOreder a drink:");
		int arg = scanner.nextInt();
		String drink_name = drinks_menu.get(arg);
		Drink selcetdDrink = drinkList.get(drink_name);
		order.orderDrink(selcetdDrink);
		System.out.println("Order more ice?");
		String is_ice = scanner.next();
		if (is_ice == "y"||is_ice == "Y"){
			order.addIce();
		}
		System.out.println("Order additional lemon?");
		String is_lemon = scanner.next();
		if (is_lemon == "y"|is_lemon == "Y"){
			order.addLemon();
		}
	}
	public void order_desserts_menu(){
		System.out.println("\tDesserts");
		for(int i = 0; i< desserts_menu.size(); i++){
			System.out.println(i+": "+desserts_menu.get(i));
		}
		System.out.println("\tOrder a dessert:");
		int arg = scanner.nextInt();
		String dessert_name = desserts_menu.get(arg);
		Dessert selcetdDessert = dessertList.get(dessert_name);
		order.orderDessert(selcetdDessert);
	}
	public void executeOrder(){
		order.executeOrder();
		this.order = new Order();
	}
	public static void main(String argcs[]) throws IOException{
		Menu menu = new Menu();
		menu.parseMenu("menu.xml");
		menu.print_initial_menu();
	} 

}
