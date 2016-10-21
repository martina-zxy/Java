public class Population {
	Individual[] individuals;

	// Create a population
	public Population(int populationSize, boolean initialise) {
		individuals = new Individual[populationSize];
		// Initialise population
		if (initialise) {
			// Loop and create individuals
			for (int i = 0; i < size(); i++) {
				Individual newIndividual = new Individual();
				newIndividual.generateIndividual();
				saveIndividual(i, newIndividual);
			}
		}
	}

	public Individual getFittest() {
		Individual fittest = individuals[0];
		// Loop through individuals to find fittest
		for (int i = 0; i < size(); i++) {
			if (fittest.getFitness() >= individuals[i].getFitness()) {
				fittest = individuals[i];
			}
		}
		return fittest;
	}

	/* Public methods */
	// Get population size
	public int size() {
		return individuals.length;
	}

	// Save individual
	public void saveIndividual(int index, Individual indiv) {
		individuals[index] = indiv;
	}

	/* Getters */
	public Individual[] getIndividuals() {
		return this.individuals;
	}

	public Individual getIndividual(int index) {
		return individuals[index];
	}
	
	public void sort(){
		mergesort(0, individuals.length - 1);
	}

	private void mergesort(int low, int high) {
		// check if low is smaller then high, if not then the array is sorted
		if (low < high) {
			// Get the index of the element which is in the middle
			int middle = low + (high - low) / 2;
			// Sort the left side of the array
			mergesort(low, middle);
			// Sort the right side of the array
			mergesort(middle + 1, high);
			// Combine them both
			merge(low, middle, high);
		}
	}

	private void merge(int low, int middle, int high) {
		
		Individual[] helper = new Individual[individuals.length];
		
		// Copy both parts into the helper array
		for (int i = low; i <= high; i++) {
			helper[i] = individuals[i];
		}

		int i = low;
		int j = middle + 1;
		int k = low;
		// Copy the smallest values from either the left or the right side back
		// to the original array
		while (i <= middle && j <= high) {
			if (helper[i].getFitness() <= helper[j].getFitness()) {
				individuals[k] = helper[i];
				i++;
			} else {
				individuals[k] = helper[j];
				j++;
			}
			k++;
		}
		// Copy the rest of the left side of the array into the target array
		while (i <= middle) {
			individuals[k] = helper[i];
			k++;
			i++;
		}

	}
}
