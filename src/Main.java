import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		final String input = "Kalle\nGolden\n5\n10\n";
		Scanner s = new Scanner(input);
		//Scanner s = new Scanner(System.in);
		Parser parser = new Parser();
		while (!parser.done()) {
			switch (parser.getField()) {
			case NAME:
				System.out.println("Name?\n");
				break;
			case BREED:
				System.out.println("Breed?\n");
				break;
			case AGE_YEARS:
				System.out.println("Age?\n");
				break;
			case WEIGHT_KG:
				System.out.println("Weight?\n");
				break;
			}
			
			String str = s.nextLine();
			Parser.Error r = parser.parseNext(str);
			if (r != Parser.Error.OK) {
				System.out.println("Error: " + r.name());
			}			
		}
		Dog dog = parser.build();
		System.out.println("name: " + dog.name +": breed: " + dog.breed + ": age: " + Integer.toString(dog.age_years) + ": weight: "
								+ Integer.toString(dog.weight_kg));
	}
}