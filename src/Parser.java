import java.util.Scanner;

public final class Parser {
	enum Error {
		OK,
		EMPTY,
		INVALID,
		NEGATIVE_VALUE,
	}
	
	enum Field {
		NAME,
		BREED,
		AGE_YEARS,
		WEIGHT_KG,
	}
	
	private Field current = Field.NAME;
	private boolean done = false;
	private Dog.Builder builder = new Dog.Builder();
			
	public Field getField() {
		return current;
	}
	
	public boolean done() {
		return done;
	}
	
	public Dog build() {
		if (!done)
			throw new IllegalStateException("Parsing not finished\n");
		return builder.build();
	}
	
	/* 
	 * Parse next field, does nothing if parsing already done.
	 */
	public Error parseNext(String in) {
		if (!done) {
			String str = in.trim();
			if (str.isEmpty())
				return Error.EMPTY;
			
			switch (current) {
			case NAME:
				builder.withName(str);
				current = Field.BREED;
				break;
			case BREED:
				builder.withBreed(str);
				current = Field.AGE_YEARS;
				break;
			case AGE_YEARS:
				if (!isInt(str))
					return Error.INVALID;
				int age = Integer.parseInt(str);
				if (age < 0)
					return Error.NEGATIVE_VALUE;
				builder.withAgeYears(age);
				current = Field.WEIGHT_KG;
				break;
			case WEIGHT_KG:
				if (!isInt(str))
					return Error.INVALID;
				int kg = Integer.parseInt(str);
				if (kg < 0)
					return Error.NEGATIVE_VALUE;
				builder.withWeightKg(kg);
				done = true;
				break;
			}
		}
		return Error.OK;
	}
	
	private static boolean isInt(String str) {
		Scanner s = new Scanner(str);
		if (!s.hasNextInt(10))
			return false;
		s.nextInt(10);
		return !s.hasNext();
	}
}
