import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class Main {
	public final static class Commands {
		
			/* returns true to exit program */
		public boolean operate(Scanner s) {
			System.out.println("> ");
			String str = s.nextLine();
			boolean exit = false;
			if (cmds.containsKey(str)) {
				exit = cmds.get(str).run(s, dogs);
			} else {
				System.out.println("ERROR: Unknown command, see \"help\" for available commands\n");
			}
			return exit;
		}

		private static interface CommandInterface {
			/* return true to exit program */
			public boolean run(Scanner s, Map<String, Dog> dogs);
		}
		
		private Map<String, Dog> dogs = new HashMap<String, Dog>();
		private Map<String, CommandInterface> cmds = Map.ofEntries(
				java.util.Map.entry("h", new printHelp()),
				java.util.Map.entry("help", new printHelp()),
				java.util.Map.entry("q", new quit()),
				java.util.Map.entry("quit", new quit()),
				java.util.Map.entry("dog register", new registerDog())
			);
	
		private static String getKey(Dog dog) {
			return dog.name.toLowerCase();
		}
		
		private class printHelp implements CommandInterface {
			public boolean run(Scanner s, Map<String, Dog> dogs) {
				System.out.println(
						"Help:\n"
						+ " h,help (this message)\n"
						+ " q, quit (quit program)\n"
						+ " dog register (interactively register dog)\n"
						);	
				return false;
			}
		}
		
		private class quit implements CommandInterface {
			public boolean run(Scanner s, Map<String, Dog> dogs) {
				return true;
			}
		}
		
		private class registerDog implements CommandInterface {
			public boolean run(Scanner s, Map<String, Dog> dogs) {
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
				String key = getKey(dog);
				if (dogs.containsKey(key)) {
					System.out.println(" Error: Already exists\n");
				} else {
					dogs.put(key, dog);
				}
				
				System.out.println(dog.toString());

				return false;
			}
		}
	}
	
	public static void main(String[] args) {
		final String input = "dog register\nKalle\nGolden\n5\n10\nq\n";
		Scanner s = new Scanner(input);
		//Scanner s = new Scanner(System.in);
		Commands cmd = new Commands();
		while (true) {
			boolean exit = cmd.operate(s);
			if (exit)
				break;
		}

	}
}