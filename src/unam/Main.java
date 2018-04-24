package unam;


import java.util.Scanner;

public class Main {


	public static Integer rA, rB, cA, cB; // Renglones y columnas de ambas matrices
	public static int[][] a; // Hacemos los arreglos globales para poder llamarlos desde los hilos
	public static int[][] b;
	 
	public static void main(String[] args) {

		Boolean error; // manejo de error
		do
		{
			error = IngresoDatos();
		
			if (!error && cA != rB){

				System.out.println("Datos no válidos (no es posible realizar la operación...) \n\n");
				error = true;
			}
		
		}while(error == true);
		System.out.println("A("+ rA + "x" + cA  + ") y B(" + rB + "x" + cB + ")");
		
		 a = new int [rA][cA];
		 b = new int [rB][cB];
		 
		 int ia = 0, ib = 0, colA = 0, colB = 0, i = 1;
		do{
			if (!(ia >= rA))
			{
				new HiloLlenadoArreglo("Hilo" + i, "A", ia).start();// Llena A
				ia++;
				i++;
				colA++;
			}
			if(!(ib >= rB))
			{
				new HiloLlenadoArreglo("Hilo" + i, "B", ib).start();// Llena B
				ib++;
				i++;
				colB ++;
			}
		}while (ia < rA | ib < rB);
	}
	
	public static Boolean IngresoDatos(){
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		try{
		System.out.println("Número de renglones de la matriz A: (Enteros > 0)\n");
		rA = Integer.parseInt(sc.nextLine());
		System.out.println("Número de columnas de la matriz A: (Enteros > 0)\n");
		cA = Integer.parseInt(sc.nextLine());
		System.out.println("Número de renglones de la matriz B: (Enteros > 0)\n");
		rB = Integer.parseInt(sc.nextLine());
		System.out.println("Número de columnas de la matriz B: (Enteros > 0)\n");
		cB = Integer.parseInt(sc.nextLine());
		}
		catch(Exception e){
			
			System.out.println("Datos no válidos (!) \n\n");
			return true; // Hubo error
			
		}
		if(rA <= 0 | rB <= 0 | cA <= 0 | cB <= 0)
		{
			System.out.println("Datos no válidos (Ningun valor puede ser <= 0) \n\n");
			return true; // Hubo error
		}
		return false; // No hubo error
	}

}
