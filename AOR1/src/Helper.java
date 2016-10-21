import java.util.Random;


public class Helper {
	public static int[][] originSol = new int[200][10];
	
	public static int[] randomArray(int[] a) {
		int n = a.length;
		Random random = new Random();
		random.nextInt();
		
		for (int i = 0; i < n; i++) {
			int change = i + random.nextInt(n - i);
			//swap
			int helper = a[i];
			a[i] = a[change];
			a[change] = helper;
		}
		
		return a;
	}	
	
	
}
