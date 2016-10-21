
public class FitnessCalc {
	static byte[] solution = new byte[64];

    /* Public methods */
    // Set a candidate solution as a byte array
    public static void setSolution(byte[] newSolution) {
        solution = newSolution;
    }

    // To make it easier we can use this method to set our candidate solution
    // with string of 0s and 1s
    static void setSolution(String newSolution) {
        solution = new byte[newSolution.length()];
        // Loop through each character of our string and save it in our byte
        // array
        for (int i = 0; i < newSolution.length(); i++) {
            String character = newSolution.substring(i, i + 1);
            if (character.contains("0") || character.contains("1")) {
                solution[i] = Byte.parseByte(character);
            } else {
                solution[i] = 0;
            }
        }
    }

  //Evaluation
  	public static int getFitness(Individual individual) {
//  		int[] order = {1,4,0,2,3};
  		int[] order = individual.getGenes();
  		
//  		for (int i = 0; i< order.length; i++) {
//  			System.out.print(order[i] + " ");
//  		}
  		int[][] data = Helper.originSol;
  		int sol[][] = new int[data.length][data[0].length];
//  		System.out.println();
  		
//  		for(int i = 0; i<order.length ; i++ ) {
//			for(int j = 0; j<10; j++) {
//				System.out.print(data[order[i]][j] + " ");
//			}
//			System.out.println();
//		}
  		
  		for (int i = 0; i < order.length; i++) {
//  			System.out.println(order[i]);
  			for(int j = 0; j <10; j++) {
  				if (i == 0 && j == 0) {
  					sol[i][j] = data[order[i]][0];
  				}
  				else if (i == 0) {
  					sol[i][j] = sol[i][j-1] + data[order[i]][j];
  				}
  				else if (j == 0) {
  					sol[i][j] = sol[i-1][j] + data[order[i]][j];
  				}
  				else {
  					sol[i][j] = Math.max(sol[i][j-1], sol[i-1][j]) + data[order[i]][j];
  				}
  			}
  		}
  		
//  		for(int i = 0; i<sol.length ; i++ ) {
//			for(int j = 0; j<sol[0].length; j++) {
//				System.out.print(sol[i][j] + " ");
//			}
//			System.out.println();
//		}

  		return sol[data.length-1][9];
  	}
  	
    // Calculate inidividuals fittness by comparing it to our candidate solution
    static int getFitness2(Individual individual) {
        int fitness = 0;
        int[] order = individual.getGenes();
        // Loop through our individuals genes and compare them to our cadidates
        for (int i = 0; i < order.length; i++) {
            if (individual.getGene(i) == solution[i]) {
                fitness++;
            }
        }
        return fitness;
    }	
    
    
    
    static int getFitness3(Individual individual) {
        int fitness = 0;
        // Loop through our individuals genes and compare them to our cadidates
        for (int i = 0; i < individual.size() && i < solution.length; i++) {
            if (individual.getGene(i) == solution[i]) {
                fitness++;
            }
        }
        return fitness;
    }	
    
    // Get optimum fitness
    static int getMaxFitness() {
        int maxFitness = solution.length;
        return maxFitness;
    }
}
