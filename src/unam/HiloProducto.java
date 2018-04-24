package unam;


public class HiloProducto extends Thread{

	public String nombre;
	public int[] x;
	public int[][] y;
	public int fila;
	
	public HiloProducto(String nombre, int[] x, int[][] y, int fila) {
		this.nombre = nombre;
		this.x = x;
		this.y = y;
		this.fila = fila;
	}
	
	@Override
	public void run(){
//		System.out.println("X:" + x.length + " Y: " + y.length); Prueba de que siempre son iguales
		for(int i = 0; i < Main.rB; i++)
		{
			int resultParcial = 0;
			
			for(int j = 0; j < x.length; j++){
				resultParcial = resultParcial + (x[j]*y[j][i]);
			}
			
			Main.c[fila][i] = resultParcial;
			
			System.out.println(nombre + ": " + "C[" + fila + "]" + "[" + i + "] = " + resultParcial);
		}
	}
}
