
public class Costo {

	public Costo() {
		  
	}
	
	int[][] cost_matrix = {
			{0, 10, 17, 15},
			{20, 0, 19, 18},
			{50, 44, 0, 25},
			{45, 40, 20, 0}
			};
	
	public int calcularCosto(int[] solucion){
		int costo = 0;
		for(int i = 0 ; i < solucion.length-1; i++){
			costo += cost_matrix[solucion[i]][solucion[i+1]];
		}
		costo += cost_matrix[solucion.length - 1][0];
		return costo;
	}
	
}
