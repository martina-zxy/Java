
// Individual is a series of unit execution, unit no is 0 - 199
public class Individual {
	
	static int defaultUnitNo = 200;
    private int[] genes = new int[defaultUnitNo];
    
    // Cache
    private int fitness = 0;

    // Create a random individual
    public void generateIndividual() {
    	for (int a = 0; a < defaultUnitNo; a++){
    		genes[a] = a;
		}
    	genes = Helper.randomArray(genes);
    }

    /* Getters and setters */
    // Use this if you want to create individuals with different gene lengths
    public static void setDefaultGeneLength(int length) {
        defaultUnitNo = length;
    }
    
    public int getGene(int index) {
        return genes[index];
    }
    
    public int[] getGenes() {
        return genes;
    }

    public void setGene(int index, int value) {
        genes[index] = value;
        fitness = 0;
    }

    /* Public methods */
    public int size() {
        return genes.length;
    }

    public int getFitness() {
        if (fitness == 0) {
            fitness = FitnessCalc.getFitness(this);
        }
        return fitness;
    }
    
    public boolean containsGene(int gene) {
    	for (int i = 0; i < genes.length; i++) {
    		if (genes[i] == gene) {
    			return true;
    		}
    	}
    	return false;
    }

    @Override
    public String toString() {
        String geneString = "";
        for (int i = 0; i < size(); i++) {
            geneString += " " + getGene(i);
        }
        return geneString;
    }
}
