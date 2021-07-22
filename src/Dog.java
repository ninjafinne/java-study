public final class Dog {
	public final String name;
	public final String breed;
	public final int age_years;
	public final int weight_kg;
		
	public static class Builder {
		private String name;
		private String breed;
		private int age_years = -1;
		private int weight_kg = -1;
		
		public Builder withName(String name) {
			this.name = name;
			return this;
		}
		
		public Builder withBreed(String breed) {
			this.breed = breed;
			return this;
		}
		
		public Builder withAgeYears(int age_years) {
			if (age_years < 0)
				throw new IllegalArgumentException("Age must be zero or more: " + Integer.toString(age_years));
			this.age_years = age_years;
			return this;
		}
		
		public Builder withWeightKg(int weight_kg) {
			if (weight_kg < 0)
				throw new IllegalArgumentException("Weight must be zero or more: " + Integer.toString(weight_kg));
			this.weight_kg = weight_kg;
			return this;
		}
		
		public Dog build() {
			Dog dog = new Dog(this.name, this.breed, this.age_years, this.weight_kg);
			if (dog.name == null)
				throw new IllegalStateException("Field name uninitialized");
			if (dog.breed == null)
				throw new IllegalStateException("Field breed uninitialized");
			if (dog.age_years == -1)
				throw new IllegalStateException("Field age_years uninitialized");
			if (dog.weight_kg == -1)
				throw new IllegalStateException("Field weight_kg uninitialized");
			return dog;
		}
	}
	
	private Dog(String _name, String _breed, int _age_years, int _weight_kg) {
		name = _name;
		breed = _breed;
		age_years = _age_years;
		weight_kg = _weight_kg;
	}
	
	public String toString() {
		return name + " (" + breed + ", " + age_years + " years, " + weight_kg + " kg)";
	}
}
