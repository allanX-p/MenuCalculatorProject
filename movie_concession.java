

import java.util.Scanner;


//Item type which contains name - pair cost along with the amount of times item was selected.
class Item_Type{
	public String name;
	public int cost;
	public int selected;
	public int total;
	public Item_Type(String s, int c) {
		name = s;
		cost = c;
		selected = 0;
		total = 0;
	}
}

public class movie_concession {

	//Auxiliary function to get the next integer while performing simple input validation.
	public static int getNextNum(Scanner scanner) {
		int result;
		while(!scanner.hasNextInt()) {
			System.out.println("Invalid Selection. Please Input An Integer: \n");
			scanner.next();
		}
		result = scanner.nextInt();
		//Clears buffer
		scanner.nextLine();
		return result;
		
	}
	//Auxiliary Function to get double from input stream
	public static double getNextDouble(Scanner scanner) {
		double result;
		while(!scanner.hasNextDouble()) {
			System.out.println("Invalid Selection. Please Input A Double (e.g 4.5): \n");
			scanner.next();
		}
		result = scanner.nextDouble();
		//Clears buffer
		scanner.nextLine();
		return result;
		
	}
	//Auxiliary function to convert cents to string dollar notation
	public static String centToDollar(int c) {
		String result = "$" + Integer.toString(Math.floorDiv(c, 100)) + ".";
		if(c % 100 < 10 && c % 100 > 0) {
			result += "0";
			result += Integer.toString(c % 100);
		}else {
			result += Integer.toString(c % 100);
		}
		
		if(c % 100 == 0) {result += '0';}
		return result;
	}
	//Pseudo creates a new window by just printing new lines
	public static void newWindow() {
		for(int i = 0; i < 100; i ++) {
			System.out.println();
		}
		
	}
	
	//385 -> $3.85
	public static int main_menu(Scanner scanner) { //Displays outer menu and allows user to select option
		newWindow();
		System.out.println("Main Menu");
		System.out.println("1. Concessions");
		System.out.println("2. Checkout");
		System.out.println("3. Manager");
		System.out.print("Select option number (1-3): ");
		int opt = getNextNum(scanner);
		while(opt < 1 || opt > 3) {
			System.out.print("Invalid Selection. Make sure to input integer from 1 - 3: ");
			opt = getNextNum(scanner);
		}
		return opt;
	}
	
	public static void concession_menu(Item_Type[] items, Scanner scanner) { //Concessions Menu
		newWindow();
		//Displaying Menu and prices
	
			System.out.println("Concessions Menu: ");
			for (int i = 0; i < items.length; i ++) {
				System.out.println(Integer.toString(i + 1) + "." + items[i].name + ": " + centToDollar(items[i].cost));
			}
			System.out.println(Integer.toString(items.length + 1) + "." + "Back to Main Menu");
			//Allows user to continue selecting items
			while(true) {
				System.out.println();
				System.out.print("Enter selection option: ");
				int sel;
				sel = getNextNum(scanner);
				while(sel < 1 || sel > 6) {
					System.out.print("Invalid Selection Option (Enter integer from 1 - 6): ");
					sel = getNextNum(scanner);
				}
				
				if(sel == 6) {
					break;
				}
				int count;
				System.out.print("Enter quantity of item you want: ");
				count = getNextNum(scanner);
				while(count < 0) {
					System.out.print("Invalid Selection. Only enter positive integers: ");
					count = getNextNum(scanner);
				}
				items[sel - 1].selected += count;
				items[sel - 1].total += count;
				//Distinction line break
				
			}
			
		
		
		
	}
	
	
	
	public static void check_out(Item_Type[] items, Scanner scanner) { //Checkout menu
		newWindow();
		int total_cost = 0;
		//Displaying user info
		System.out.println("Items Selected: ");
		for(int i = 0; i < items.length; i ++) {
			if(items[i].selected > 0) {
				//Calculates cost of item(s) with sales tax applied (7%)
				int cost_with_tax = (int) (items[i].cost * items[i].selected * 1.07);
				System.out.println(cost_with_tax);
				System.out.println(items[i].name + " selected " + Integer.toString(items[i].selected) + " times costing " + centToDollar(cost_with_tax));
				total_cost += cost_with_tax;
			}
			
		}
		System.out.println("Total Cost: " + centToDollar(total_cost));
		while(true) {
			//Offers user options in the check_out menu
			System.out.println("Options: ");
			System.out.println("1. Pay");
			System.out.println("2. Back to Main Menu");
			System.out.print("Enter selection option (1-2): ");
			int sel;
			sel = getNextNum(scanner);
			while(sel < 1 || sel > 2) {
				System.out.print("Invalid Selection. Enter integer from 1-2: ");
				sel = getNextNum(scanner);
			}
			if(sel == 2) {
				break;
			}
			System.out.print("Enter amount of money you are paying with: $");
			double pay = getNextDouble(scanner);
			int pay_cent = (int) (pay * 100);
			if(pay_cent < total_cost) {
				System.out.println("Not enough money");
			}else {
				int change = pay_cent - total_cost;
				System.out.println("You Got " + centToDollar(change) + " dollars back");
				//Clears user selection (doesn't change total)
				for(int i = 0; i < items.length; i ++) {
					items[i].selected = 0;
				}
				System.out.println("Press Enter: ");
				scanner.nextLine();
				break;
			}
			
		}
		
		
	}
	
	public static void manager(Scanner scanner, Item_Type[] items) { //Manager menu
		newWindow();
		System.out.println("Manager Options: ");
		while(true) {
			System.out.println("1. Access Concession Data ");
			System.out.println("2. Return To Main Menu ");
			System.out.print("Enter selection option (1-2): ");
			int sel;
			sel = getNextNum(scanner);
			while(sel < 1 || sel > 2) {
				System.out.print("Invalid Selection. Enter integer from 1-2: ");
				sel = getNextNum(scanner);
			}
			if(sel == 2) {
				break;
			}
			//Checks for password
			System.out.print("Enter password: ");
			int pass;
			pass = getNextNum(scanner);
			if(pass == 7777) {
				int total_cost = 0;
				for(int i = 0; i < items.length; i ++) {
					//Exclude currently selected items since they are not currently purchased.
					int net_sold = items[i].total - items[i].selected;
					System.out.println("Item " + items[i].name + " was purchased " + net_sold + " times.");
					total_cost += (int) (net_sold * items[i].cost * 1.07);
				}
				System.out.println("Gross Sales: " + centToDollar(total_cost));
			}else {
				System.out.println("Incorrect Password!");
				
			}
		}
		
	}
	
	public static void main(String[] args) {
		Item_Type[] items = new Item_Type[5];
		//Adding items
		items[0] = new Item_Type("Small Popcorn", 300);
		items[1] = new Item_Type("Medium Popcorn", 400);
		items[2] = new Item_Type("Large Popcorn", 500);
		items[3] = new Item_Type("Soda", 200);
		items[4] = new Item_Type("Candy", 150);
		
	
		Scanner scanner = new Scanner(System.in);
		//Main Loop where user can interact with system
		while(true) {
			int opt = main_menu(scanner);
			//Goes to respective menu
			switch(opt) {
			
			case 1:
				concession_menu(items, scanner);	
				break;
			case 2:
				check_out(items, scanner);
				break;
			case 3:
				manager(scanner, items);
			}
		}
		
		

	}

}
