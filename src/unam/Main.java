package unam;


import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {


	public static Integer rA, rB, cA, cB; // Renglones y columnas de ambas matrices
	public static int[][] a; // Hacemos los arreglos globales para poder llamarlos desde los hilos
	public static int[][] b;
	
	public static int[][] at;//transpuestas
	public static int[][] bt;

	public static int[][] c; // el arreglo del resultado final

	 
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

		 at = new int [cA][rA];
		 bt = new int [cB][rB];
		 
		 int ia = 0, ib = 0, i = 0;
		 HiloLlenadoArreglo[] t = new HiloLlenadoArreglo[rA*cB];
		do{
			if (!(ia >= rA))
			{
//				new HiloLlenadoArreglo("Hilo" + i, "A", ia).start();// Llena A
//				new HiloLlenadoArreglo("Hilo" + i, "A", ia).run();
				t[i] = new HiloLlenadoArreglo("Hilo" + (i+1), "A", ia);
				t[i].start();
				ia++;
				i++;
			}
			if(!(ib >= rB))
			{
//				new HiloLlenadoArreglo("Hilo" + i, "B", ib).run();
//				new HiloLlenadoArreglo("Hilo" + i, "B", ib).start();// Llena B
				t[i] = new HiloLlenadoArreglo("Hilo" + (i+1), "B", ib);
				t[i].start();
				ib++;
				i++;
			}
		}while (ia < rA | ib < rB);
		
		for(int w = 0; w < i; w++){
			try {
				t[w].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int s = 0;
		 HiloTranspuesta[] t2 = new HiloTranspuesta[rA*cB];
		//AQUI EMPIEZA LA TRANSPOSICION!!!!!!!!!!!!!!!vvvvvvv
		for(ia = 0; ia < rA; ia++) {
			
			t2[ia] = new HiloTranspuesta("Hilo" + i, "A", ia); // Transpone A
			t2[ia].start();
//			new HiloTranspuesta("Hilo" + i, "A", ia).run();
			i++;
			s++;
			
		}
		for(ib = 0; ib < rB; ib++) {
			
//			new HiloTranspuesta("Hilo" + i, "B", ib).start(); // Transpone B
			t2[ib + rA] = new HiloTranspuesta("Hilo" + i, "B", ib); 
			t2[ib + rA].start();
			i++;
			s++;
			
		}
		for(int w = 0; w < s; w++){
			try {
				t2[w].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//AQUI EMPIEZA LA MULTIPLICACION (HAY QUE MODIFICAR rA rB cA y cB a los transpuestos)
		

		c = new int[cA][rB];
	int[] x = new int[rA];// los arreglos a mandar para el producto x= datos de cada fila de A

	for(int i1 = 0; i1< cA; i1++){
		for(int j= 0; j<rA; j++){ // los datos de la fila x de a
			x[j] = at[i1][j];
		}
			new HiloProducto("HiloProd " + i1 ,x,bt,i1).run();
		}
	
		new HiloImprimir("A: ",at,cA,rA).start();
		new HiloImprimir("B: ",bt,cB,rB).start();
		new HiloImprimir("C: ",c,cA, rB).start();
	}
	
	
	
	//--
	
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
