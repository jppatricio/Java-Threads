package unam;

import java.util.Random;

public class HiloLlenadoArreglo extends Thread{
	
	private String nombre;
	private String arr;
	private int row;
	
	 public HiloLlenadoArreglo(String nombre, String arr, int row) {
		 this.nombre = nombre;
		 this.arr = arr;
		 this.row = row;
	}
	
	 @Override
	public void run() {
		if(arr == "A")
		{
			
			for(int i = 0; i < Main.cA; i ++)
			{
				System.out.println(Main.a.toString());
				Main.a[row][i] =  new Random().nextInt(11); //(Va del 0 al 10 excluye al 11)
				System.out.println(nombre + ": " + "A[" + row + "]" + "[" + i + "] = " + Main.a[row][i]);
			}
		}
		else
		{
			for(int i = 0; i < Main.cA; i ++)
			{
			Main.b[row][i] =  new Random().nextInt(11); //(Va del 0 al 10 excluye al 11)

			System.out.println(nombre + ": " + "B[" + row + "]" + "[" + i + "] = " + Main.b[row][i]);
			}
		}
    }
	
}
