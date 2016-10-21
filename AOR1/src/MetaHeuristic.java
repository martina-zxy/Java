import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class MetaHeuristic {
	public static int[][] intProblem = new int[200][10];
	
	public static void main(String[] args) throws FileNotFoundException {
		BufferedReader in = new BufferedReader(new FileReader("instance_makespan.txt"));
		String line ; 
		
		
		int i = 0;
		try {
			while((line = in.readLine()) != null)
			{
				String[] split = line.split("\t");
				for (int idx = 0; idx < split.length; idx++) {
					intProblem[i][idx] = Integer.parseInt(split[idx]);
				}
				i++;
			}
			in.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		int[] input = new int[200];
		for(int seq = 0; seq < 200; seq++) {
			input[0] = seq;
		}
		System.out.println(getMakespan(input));
		
	}
	
	public void ga(int[][] intProblem)
	{
		
	}
	
	public static int getMakespan(int[] order) {
		int[][] result = new int[200][10];
		for(int x = 0; x < 200; x++) {
			for(int y = 0; y <10; y++) {
				
				if (x == 0 && y == 0) {
					result[x][y] = intProblem[0][0];
				}
				else if (x == 0) {
					result[x][y] = result[x][y-1] + intProblem[x][y];
				}
				else if (y == 0) {
					result[x][y] = result[x-1][y] + intProblem[x][y];
				}
				else {
					result[x][y] = Math.max(result[x][y-1], result[x-1][y]) + intProblem[x][y];
				}
			}
		}
		
		return result[199][9];
	}
}
