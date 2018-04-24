package unam;

public class HiloImprimir extends Thread{
	
	int[][] arr;
	int x, y;
	String nombre;

	public HiloImprimir(String nombre, int[][] arr, int x, int y) {
		this.arr = arr;
		this.x = x;
		this.y = y;
		this.nombre = nombre;
	}
	
	@Override
	public void run(){
		nombre += "[";
		for(int i = 0; i < x; i++){
			nombre += "[";
			for(int j = 0; j < y; j++){

				nombre += arr[i][j];
				if(j+1 != y){
					nombre += ",";
				}
			}
			nombre += "]";
			if(i+1 != x){
				nombre += ",";
			}
		}

		nombre += "]";
		System.out.println(nombre);
	}
}
