import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		Dog.Builder b = new Dog.Builder(); 
		System.out.println("Name?\n");
		b.withName(s.nextLine());
		System.out.println("Breed?\n");
		b.withBreed(s.nextLine());
		System.out.println("Age?\n");
		String tmpAge = s.nextLine();
		b.withAgeYears(Integer.parseUnsignedInt(tmpAge));
		System.out.println("Weight?\n");
		String tmpWeight = s.nextLine();
		b.withWeightKg(Integer.parseUnsignedInt(tmpWeight));
		Dog dog = b.build();
		System.out.println("name: " + dog.name +": breed: " + dog.breed + ": age: " + Integer.toString(dog.age_years) + ": weight: "
								+ Integer.toString(dog.weight_kg));
	}
}