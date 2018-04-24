package unam;

public class HiloTranspuesta extends Thread {

	private String nombre;
	private String arr;
	private int row;
	
	public HiloTranspuesta(String nombre, String arr, int row) {
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
				Main.at[i][row] = Main.a[row][i]; 
				System.out.println(nombre + ": " + "At[" + i + "]" + "[" + row + "] = " + Main.at[i][row]);
			}
		}
		else
		{
			for(int i = 0; i < Main.cB; i ++)
			{
				Main.bt[i][row] = Main.b[row][i];
				System.out.println(nombre + ": " + "Bt[" + i + "]" + "[" + row + "] = " + Main.bt[i][row]);
			}
		}
		
	}

}