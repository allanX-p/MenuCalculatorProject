
import java.util.Scanner;
//Food Type which contains name & calories pair. Also stores how much times food has been selected;
class Food_Type{
	public String name;
	public int cal;
	public int selected;
	public Food_Type(String s, int c) {
		name = s;
		cal = c;
		selected = 0;
		
	}
}

public class calculator {

	//Auxiliary function which performs input validation
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
	
	
	//Displays menu while allowing user to input quantity of food eaten (handles input validation and keeps track of foods selected). Later calculates amount of calories.
	public static int getCal(Food_Type[] foods) { 
		int cal_sum = 0;
		Scanner scanner = new Scanner(System.in);
		//Infinite while loop to allow the user to continue selecting items
		while(true) {
			
			System.out.println("Select from this list of food what you ate (1-6)");
			for(int i = 0; i < foods.length; i ++) {
				System.out.println(Integer.toString(i + 1) + ". " +   foods[i].name);
			}
			System.out.print("Enter selection number (1-6): ");
			int sel = getNextNum(scanner);	
			while(sel < 1 || sel > 6) {
				System.out.println("Invalid Selection. Make sure to enter number from 1-6: ");
				sel = getNextNum(scanner);
			}
			System.out.println("Enter quantity of selection you have eaten: ");
			int q = getNextNum(scanner);
			//Updates calorie sum and selection info
			cal_sum += foods[sel - 1].cal * q;
			foods[sel - 1].selected += q;
			
			System.out.print("Do you eat more? (Enter 1 if yes, 0 if no): ");
			if(getNextNum(scanner) != 1) {
				break;
			}
			//Line Break
			System.out.println();
		}
		scanner.close();
		return cal_sum;
	}
	
	
	public static void main(String[] args) {
		 
		//Initializes all the foods
		 Food_Type[] foods = new Food_Type[6];
		 foods[0] = new Food_Type("hamburger", 300);
		 foods[1] = new Food_Type("cheeseburger", 350);
		 foods[2] = new Food_Type("French fries", 250);
		 foods[3] = new Food_Type("onion rings", 300);
		 foods[4] = new Food_Type("soda", 160);
		 foods[5] = new Food_Type("milkshake", 650);
		 int cals = getCal(foods);
		 //Clears Screen By Printing Multiple Lines
		 for(int i = 0; i < 100; i ++) {
			 System.out.println();
		 }
		 
		 System.out.println("Foods selected: ");
		 //Displays User Info on Selection
		 for(int i = 0; i < foods.length; i ++) {
			 if(foods[i].selected > 0) {
				 System.out.println("You ate " + foods[i].name + " " + foods[i].selected + " times" );
				 System.out.println("You gained " + Integer.toString(foods[i].cal * foods[i].selected) + " calories in total from the " + foods[i].name);
			 }
			 
		 }
		 //Displays total calories and running time
		 System.out.println();
		 System.out.println("Total Calories Eaten: " + Integer.toString(cals));
		 //Constants for jogging and running
		 double jog = 398.0;
		 double run = 557.0;
		 //Rounds the hours requried to burn off calories
		 double time_jog = Math.round(cals/jog * 100);
		 time_jog /= 100;
		 double time_run = Math.round(cals/run * 100);
		 time_run /= 100;
		 
		 System.out.println("Time required to burn off calories jogging: " + Double.toString(time_jog) + " hours.");
		 System.out.println("Time required to burn off calories running: " + Double.toString(time_run) + " hours.");
		 
	}

}
