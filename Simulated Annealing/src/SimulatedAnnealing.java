import java.util.Random;

public class SimulatedAnnealing {

	int temperaturaInicial = 10000;
	double temperaturaFinal = 0.00001;
	double tasaEnfriamiento = 0.9999;
	int numCiudades = 4;
	
	Swap swap = new Swap();
	Costo costo = new Costo();
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimulatedAnnealing simulated = new SimulatedAnnealing();
		for(int i = 0; i < 30; i++){
			System.out.println("Experiment " + (i + 1));
			simulated.Simulated();
			System.out.println();
		}
		
	}

	public void Simulated(){
		
		int[]solucionActual = new int[numCiudades];
		solucionActual = generarSolucion(solucionActual);
		int distanciaActual = costo.calcularCosto(solucionActual);
		
		while (temperaturaInicial > temperaturaFinal) {
			Random rnd = new Random();
			int[]solucion = swap.swap(solucionActual);
			int distancia = costo.calcularCosto(solucion);
			int diferenciaDistancia = distancia - distanciaActual;
			
			if ((diferenciaDistancia < 0) || (distancia > 0 && Math.exp(-distancia / temperaturaInicial) > rnd.nextDouble())) {
				
				for (int i = 0; i < solucion.length; i++) {
					solucionActual[i] = solucion[i];
				}
				
				distanciaActual = distancia;
				
			}
			
			temperaturaInicial *= tasaEnfriamiento;
		}
		
		String solucion = mostrarsolucion(solucionActual);
		System.out.println("Mejor Solución: " + solucion);
		System.out.println("Distancia de la solución: " + distanciaActual + "\nTemperatura Actual: " + temperaturaInicial);
		
	}
	
	public int [] generarSolucion(int [] solucion){
		
		Random rnd = new Random();
		int temp = 0;
		
		for (int i = 0; i < numCiudades; i++) {
			solucion[i] = i;
		}
		
		for (int i = 0; i < (solucion.length - 1); i++) {
			int random1 = rnd.nextInt(numCiudades);
			int random2 = rnd.nextInt(numCiudades);
			temp = solucion[random1]; 
			solucion[random1] = solucion[random2];
			solucion[random2] = temp;		
		}
		
		return solucion;
	}
	
	public String mostrarsolucion(int[] solucion) {
		
		String temp = "";
		for (int i = 0; i < solucion.length; i++) {
			temp += solucion[i] + " ";
		}
		return temp;
	
	}
	
}
