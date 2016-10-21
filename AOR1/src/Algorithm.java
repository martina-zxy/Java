
public class Algorithm {
	/* GA parameters */
    private static final double uniformRate = 0.5;
    private static final double mutationRate = 0.03;
    private static final int tournamentSize = 5;
    private static final boolean elitism = false;
    
 // Evolve a population
    public static Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(pop.size(), false);

        // Keep our best individual
        if (elitism) {
            newPopulation.saveIndividual(0, pop.getFittest());
        }

        // Crossover population
        int elitismOffset;
        if (elitism) {
            elitismOffset = 1;
        } else {
            elitismOffset = 0;
        }
        
        // Loop over the population size and create new individuals with
        // crossover
        for (int i = elitismOffset; i < pop.size(); i++) {
            Individual indiv1 = tournamentSelection(pop);
            Individual indiv2 = tournamentSelection(pop);
            Individual newIndiv = crossover(indiv1, indiv2);
            newPopulation.saveIndividual(i, newIndiv);
        }

        // Mutate population
        for (int i = elitismOffset; i < newPopulation.size(); i++) {
            mutate(newPopulation.getIndividual(i));
        }

        return newPopulation;
    }
    
    // Crossover individuals
    private static Individual crossover(Individual parent1, Individual parent2) {
        Individual child = new Individual();
        for (int i = 0; i < child.getGenes().length; i++) {
    		child.setGene(i, -1);
    	}
        // Loop through genes
//        for (int i = 0; i < indiv1.size(); i++) {
//            // Crossover
//            if (Math.random() <= uniformRate) {
//                newSol.setGene(i, indiv1.getGene(i));
//            } else {
//                newSol.setGene(i, indiv2.getGene(i));
//            }
//        }
        
        int startPos = (int) (Math.random() * parent1.size());
        int endPos = (int) (Math.random() * parent1.size());
        
     // Loop and add the sub tour from parent1 to our child
        for (int i = 0; i < child.size(); i++) {
            // If our start position is less than the end position
            if (startPos < endPos && i > startPos && i < endPos) {
                child.setGene(i, parent1.getGene(i));
            } // If our start position is larger
            else if (startPos > endPos && i > endPos && i < startPos) {
                child.setGene(i, parent1.getGene(i));
            }
        }
        
     // Loop through parent2's city tour
        for (int i = 0; i < parent2.size(); i++) {
            // If child doesn't have the city add it
            if (!child.containsGene(parent2.getGene(i))) {
                // Loop to find a spare position in the child's tour
                for (int ii = 0; ii < child.size(); ii++) {
                    // Spare position found, add city
                    if (child.getGene(ii) == -1) {
                        child.setGene(ii, parent2.getGene(i));
                        break;
                    }
                }
            }
        }
        return child;
    }
    
    // Mutate an individual
    public static void mutate(Individual indiv) {
        // Loop through genes
//    	for (int i = 0; i < indiv.getGenes().length; i++) {
//        	System.out.print(indiv.getGene(i) + " ");
//        }
    	
        if (Math.random() <= mutationRate) {
            // Create random gene
        	
            int switch1 =  (int) Math.floor(Math.random() * indiv.getGenes().length);
            int switch2 =  (int) Math.floor(Math.random() * indiv.getGenes().length);
//            System.out.println("Switch 1:" +switch1);
//            System.out.println("Switch 2:" + switch2);
            if (switch1 != switch2) {
            	int[] genes = indiv.getGenes();
            	swap(switch1, switch2, genes);
            }
        }
    }
    
    // Select individuals for crossover
    private static Individual tournamentSelection(Population pop) {
        // Create a tournament population
        Population tournament = new Population(tournamentSize, false);
        // For each place in the tournament get a random individual
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournament.saveIndividual(i, pop.getIndividual(randomId));
        }
        // Get the fittest
        Individual fittest = tournament.getFittest();
        return fittest;
    }
    
    static void swap(int i, int j, int[] arr) {
    	  int t = arr[i];
    	  arr[i] = arr[j];
    	  arr[j] = t;
    	}
}
