import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Costo {

int[][] cost_matrix = null;
	
	public Costo(String archivo, int ciudades) {
		
		this.cost_matrix = new int[ciudades][ciudades];
		
		BufferedReader brBackground;
		String[] lineBackground;
		String line = "";
		
		try 
		{
			brBackground = new BufferedReader(new FileReader( archivo ));
			line = brBackground.readLine();
			
			int fila = 0;
			
			while(line != null) //TODO
			{
				lineBackground = line.split(" ");
				
				for(int i = 0; i < ciudades; i++)
				{
					cost_matrix[fila][i] = Integer.parseInt( lineBackground[i] );
				}
					
				fila++;
				line = brBackground.readLine();
			}
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public int calcularCosto(int[] solucion){
		int costo = 0;
		for(int i = 0 ; i < solucion.length-1; i++){
			costo += cost_matrix[solucion[i]][solucion[i+1]];
		}
		costo += cost_matrix[solucion.length - 1][0];
		return costo;
	}
	
}
