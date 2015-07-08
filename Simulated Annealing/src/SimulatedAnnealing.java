import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SimulatedAnnealing {

	int temperaturaInicial = 10000;
	double temperaturaFinal = 0.00001;
	double tasaEnfriamiento = 0.9999;
	static int numCiudades;
	
	Swap swap = new Swap();
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int ciudades = Integer.parseInt( args[0] );
		String archivo = args[1];
		String salida = args[2];
		numCiudades = ciudades;
		SimulatedAnnealing simulated = new SimulatedAnnealing();
		for(int i = 0; i < 30; i++){
			System.out.println("Experiment " + (i + 1));
			System.gc();
			simulated.Simulated(ciudades, archivo, salida);
		}
		
	}

	public void Simulated(int ciudades, String archivo, String salida){
		
		Costo costo = new Costo(archivo, ciudades);
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
		
		String solucionAct = mostrarsolucion(solucionActual);
		
		//File output creation
    	File o = new File(salida);
    	
    	//Create output file
    	FileWriter fw;
        if (!o.exists()) 
        {
            try 
            {
                o.createNewFile();
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(SimulatedAnnealing.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("\n\nFile " + o);
                System.exit(0);
            }
        }
        BufferedWriter bw = null;
        
        try 
    	{
    		fw = new FileWriter(o.getAbsoluteFile(), true);
    		bw = new BufferedWriter(fw);
    		
    		bw.write( "\n\nMejor Solución: \t" + solucionAct );
    		bw.write( "\nCosto de la Solución: \t" + distanciaActual );
        	
    		
			bw.flush();
		} 
    	catch (IOException e) 
    	{
			e.printStackTrace();
		}
        
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
