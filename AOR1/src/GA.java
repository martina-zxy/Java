import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;;

public class GA{
	
	public static int[][] originSol = new int[200][10];
	public static int[][] tempSol = new int [200][10];
	public static int[][][] population = new int [500][200][10];
	
	public static void main(String[] args) throws FileNotFoundException {
		
		long startTime = System.currentTimeMillis();
		//Read file;
		BufferedReader in = new BufferedReader(new FileReader("instance_makespan.txt"));
		String line ; 
		
		int l = 0;
		try {
			while((line = in.readLine()) != null)
			{
				String[] splitedLine = line.split("\t");
				for (int num = 0; num < splitedLine.length; num++) {
					Helper.originSol[l][num] = Integer.parseInt(splitedLine[num]);
				}
				l++;
			}
			in.close();
		} catch (IOException e) { e.printStackTrace(); }
		
		Population myPop = new Population(100, true);
		myPop.sort();
//		for (Individual ind : myPop.getIndividuals()) {
//			System.out.println(ind.getFitness());
//		}
		// Create an initial population
        
        // Evolve our population until we reach an optimum solution
        int generationCount = 0;
        for (int i = 0; i < 30000; i++) {
        	myPop = Algorithm.evolvePopulation(myPop);
        	System.out.println(myPop.getFittest().getFitness());
        }
        
        Individual fittest = myPop.getFittest();
        System.out.println(fittest.toString());
        
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time : " + (endTime - startTime));
//        while (myPop.getFittest().getFitness() > FitnessCalc.getMaxFitness()) {
//            generationCount++;
//            System.out.println("Generation: " + generationCount + " Fittest: " + myPop.getFittest().getFitness());
//            myPop = Algorithm.evolvePopulation(myPop);
//        }
//        System.out.println("Solution found!");
//        System.out.println("Generation: " + generationCount);
//        System.out.println("Genes:");
        
//        System.out.println("Fitness :" + fittest.getFitness());
//        
//        
//        
//        int generationCount = 0;
//        while (myPop.getFittest().getFitness() < FitnessCalc.getMaxFitness()) {
//            generationCount++;
//            System.out.println("Generation: " + generationCount + " Fittest: " + myPop.getFittest().getFitness());
////            pop = Algorithm.evolvePopulation(pop);
//        }
        
//		 tempSol = originSol;
//		
		 //Step 1: Initialization: Generate population randomly (500 individuals)
//		 int[] index = new int[5];
//		 for (int a = 0; a < 5; a++){
//			 index[a] = a;
//		 }
//		 for (int i = 0; i< index.length; i++) {
//			 System.out.print(index[i] + " ");
//		 }
//		 Helper.randomArray(index);
//		 for (int i = 0; i< index.length; i++) {
//			 System.out.print(index[i] + " ");
//		 }
//		 int[] newIndex = new int [200];
//		 for (int a = 0; a < 200; a++){
//			 index[a] = a;
//		 }
//		 for (int k = 0; k < 500; k++){
//			 randomArray(index);
//			 //for (int r = 0; r < 500; r++) {System.out.println(newIndex[r]);}
//			 for (int i = 0; i < 200; i++){
//				 for (int j = 0; j < 10; j++){
//					 population[k][i][j] = tempSol[index[i]][j];
//				 }
//			 }
//		 }
		 /*for (int i=0; i<200; i++){
			 for (int j=0; j<10; j++){
				 System.out.print(population[0][i][j]+" ");
			 }
			 System.out.println();
		 }*/
		 
//		 
//		 //Step 2: Evaluation: Calculate the Makespan and save into int[500]
//		 //Step 3: Find the optimal solution: Compare with the previous best solution: if more than 5 times-> done
//		 //Step 4: Selection: Choose the best 250 individuals
//		 //Step 5: Crossover: Generate 2nd generation until 500 individuals in total
//		 //Step 6: Evaluation
//		 //Step 7: Find the optimal solution
//		 //Step 8: Selection
//		 //Step 9: Mutation: Generate 3rd generation
//		 //Step 10: Repeat from step 2
//		 
//
//		 
//		int[] input = new int[200];
//		for(int seq = 0; seq < 200; seq++) {
//			input[0] = seq;
//		}
//		System.out.println(getMakespan(input));
//		
	}
	
	//Generate random permutation of an array
	
	public void createChildren(int[][] ini)
	{
		
	}
	
	public void mutation(){
		
		
	}
	
	public void crossOver(){
		
		
	}
	
	//Evaluation
	public static int getMakespan(int[] order) { 
		int[][] sol = new int[200][10];
		for(int i = 0; i < 200; i++) {
			for(int j = 0; j <10; j++) {
				if (i == 0 && j == 0) {
					sol[i][j] = tempSol[0][0];
				}
				else if (i == 0) {
					sol[i][j] = sol[i][j-1] + tempSol[i][j];
				}
				else if (j == 0) {
					sol[i][j] = sol[i-1][j] + tempSol[i][j];
				}
				else {
					sol[i][j] = Math.max(sol[i][j-1], sol[i-1][j]) + tempSol[i][j];
				}
			}
		}
		
		return sol[199][9];
	}
}

