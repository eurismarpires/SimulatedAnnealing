import java.util.Random;

public class Swap {
	
	public int[]swap(int[] solucion){
			
			Random rnd = new Random();
			int[] sol = new int[solucion.length];
			int random1 = rnd.nextInt(solucion.length);
			int random2 = rnd.nextInt(solucion.length);
			int temp = 0;
			
			while(random1 == random2){
				random2 = rnd.nextInt(solucion.length);
			}
			
		    sol = solucion.clone();
			
			temp = sol[random1];
			sol[random1] = sol[random2];
			sol[random2] = temp;
		
			return sol;
		}

}
