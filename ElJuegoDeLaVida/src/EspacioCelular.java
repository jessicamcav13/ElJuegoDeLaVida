

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;



public class EspacioCelular {
	
	//Declaramos las variables de instancia de la clase. Para ello necesitaremos principalmente 
	//un array,filas,columnas y definir el tamaño de la celda. filSel y colSel son variables 
	//que utilizaremos para la clase Direccion "intentar" movernos por las celdas de la cuadricula
	private Celula [][] poblacion;
	private int filas;
	private int columnas;
	
	private int filSel =0;
	private int colSel =0;
	
	String hexadecimal="882FF5";
	
	int entero=Integer.parseInt(hexadecimal,16);
	
	Color color=new Color(entero);

	private static int tamañoCelda=10;
			
	
	//Constructor de la clase. Rellenamos el array con las filas y las columnas. 
	//Necesitamos crear dos for que recorreran las filas y las columnas y 
	//que cuando se cree la matriz, 
	//las celulas aparezcan muertas por defecto.
	public EspacioCelular(int filas, int columnas) {
		poblacion = new Celula[this.filas=filas][this.columnas=columnas];
		for (int i=0;i<filas;i++){
			for(int j=0;j<columnas;j++){
			
			poblacion[i][j]=Celula.MUERTA;
			}
		}
	}
	
	/*public int[][] get(){
		int[][] aux=new int[filas][columnas];
		for (int i=0;i<filas;i++)
			aux[i]=poblacion[i].clone();
		return aux;
	}*/
	
	//Necesitamos un metodo paint para colorear las celdas.
	public void paint(Graphics g, Color c){
		g.setColor(c);
	for(int i=0;i<filas;i++){
		for(int j=0;j<columnas; j++){
			
	//Cuando la celula esté viva, se coloreara los rectángulos en azul
	if(poblacion[i][j]==Celula.VIVA){
		
		g.setColor(color);
		
	
		g.fillRect( j*tamañoCelda,i*tamañoCelda,tamañoCelda,tamañoCelda );
	}
}
	}
		
		//Dibujar la cuadricula
		
		for(int i=0;i<filas;i++){
		
			g.setColor(c);
			
			
			g.drawLine(i, i * tamañoCelda, filas * tamañoCelda, i * tamañoCelda);
			g.drawLine(i * tamañoCelda, 0, i * tamañoCelda, columnas
					* tamañoCelda);

			g.drawLine(0, i * tamañoCelda, columnas * tamañoCelda, i
					* tamañoCelda);
			g.drawLine(i * tamañoCelda, 0, i * tamañoCelda, filas * tamañoCelda);		
	}
		//Esto lo usaremos para seleccionar la celda que queremos pintar
		g.setColor(Color.GREEN);
		
		g.drawRect((colSel/tamañoCelda)*2, (filSel/tamañoCelda)*2, tamañoCelda, tamañoCelda);
		
		
		
		
		
	}	
		
	public void setViva(int corx,int cory){
		
		poblacion[cory/tamañoCelda][corx/tamañoCelda]=Celula.VIVA;
		
	}
	//Metodo para crear celulas aleatorias
	public void setCelulasAleatorias(){
		
		  int cont = 0;
		  Random r = new Random();
		  for (int i = 0; i < filas; i++) {
		   for (int j = 0; j < columnas; j++) {
		    if (cont >= 2000)
		     break;
//Si la celula esta muerta, que la pase a embrion
		    if (r.nextInt(3)==0 && poblacion[i][j] == Celula.MUERTA) {
		     poblacion[i][j] = Celula.EMBRION;
		     cont++;
		     
		
		    }

		   }
		    

		  }
		  System.out.println("El numero de celulas creadas es " + cont);
		  
		  // y si la celula esta en fase embrionaria, que la pase a viva
		  
		  for (int i = 0; i < filas; i++) {
		   for (int j = 0; j < columnas; j++) {
		    if (poblacion[i][j] == Celula.EMBRION)
		     poblacion[i][j] = Celula.VIVA;
		   }
		  }
		 }
	
	//Método para limpiar el panel. Hace que todas las celulas pasen muertas
	public void setLimpiar(){
		
		for(int i=0;i<filas;i++){
		for (int j=0;j<columnas;j++){
			poblacion[i][j]=Celula.MUERTA;
		}
		}
//		filSel =0;
//		colSel=0;
	}
	
	
//Metodo para pasar a una siguiente generacion celular. 
//Este método cambiará el estado de las celulas cumpliendo una serie de normas.
	public void sgteGeneracion() {

		 int cont;
		 //hacemos que el for recorra las filas y las columnas
		for (int f=0; f<filas; f++){
			for (int c=0; c<columnas; c++) {
						cont=0;
			//Necesitamos dos for que localicen la posicion de las celulas en torno a un centro que será otra celula. 
			//X será las filas e Y serán las columnas
		for(int x=f-1;x<=f+1;x++){
		for(int y=c-1;y<=c+1;y++){
		
			try{
				//Si la celula no es la central, y si esta viva o esta moribunda, que la cuente
				if((x!=f || y!=c) && (poblacion[x][y]==Celula.VIVA || poblacion[x][y]==Celula.MORIBUNDA)){
			
				cont++;
			}
				//Todo el marco exterior de la cuadricula estará muerto
			}catch(IndexOutOfBoundsException e){}
		}
		
		
		}//Con este if haremos que si una celula esta rodeada de 1 o menos de una o de 4 o mas de 4 células,  
		//en la siguiente generacion estará moribunda
			if((cont<=1 || cont>=4) && poblacion[f][c]==Celula.VIVA){
				poblacion[f][c]=Celula.MORIBUNDA;
			}
		//Si la celula esta rodeada de 3 y está muerta pasará a estar en embrion en la siguiente generacion	
			if(cont==3 && poblacion[f][c]==Celula.MUERTA){
				poblacion[f][c]=Celula.EMBRION;
			}
					
						
			}

}
		for (int f=0; f<filas; f++){
			for (int c=0; c<columnas; c++) {
				//Cuando una celula este moribunda, en la siguiente generacion morirá
		if(poblacion[f][c]==Celula.MORIBUNDA){
			poblacion[f][c]=Celula.MUERTA;
		}
		//si la celula esta en estado embrion, en la siguiente generacion vivirá
		if(poblacion[f][c]==Celula.EMBRION){
			
			poblacion[f][c]=Celula.VIVA;
			
		}
		
}
		}}
	
	//Metodo para moverse de celda en celda en la cuadricula
	public void Direccion(int direccion){
		if(direccion == 0){
	
			filSel = filSel-tamañoCelda;
			System.out.println("arriba");
		}
		if(direccion == 1){
			colSel = colSel + tamañoCelda;
			System.out.println("derecha");
		}
		if(direccion == 2){
			filSel = filSel+tamañoCelda;
			System.out.println("abajo");
		}
		if(direccion == 3){
			colSel = colSel - tamañoCelda;
			System.out.println("Izquierda");
		}
		
		}
	
	//Con este metodo haremos que cuando pulsemos espacio(aqui no se define esto) la celula 
	//que seleccionemos si esta viva, pase a muerta y viceversa
	public void setEspacioEstado(){
		if(poblacion[colSel][filSel] == Celula.VIVA){
			poblacion[colSel][filSel] = Celula.MUERTA;
			System.out.println(filSel);
			System.out.println(colSel);
		}else
			poblacion[colSel][filSel] = Celula.VIVA;
		System.out.println(filSel);
		System.out.println(colSel);
	}
		
	}
