package taller01;

import java.util.Scanner;
import java.io.*;

public class main {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub

		Scanner leer = new Scanner(System.in);
		String[][] extraterrestres = new String[100][8];
		String[][] humanos = new String[100][9];
		
		leerArchivoExt(extraterrestres);
		leerArchivoHum(humanos);
		
		
	}

	public static void leerArchivoExt(String[][] ext) throws IOException{
		BufferedReader arch = new BufferedReader(new FileReader("x.txt"));
		String linea;
		int i=0;
		while((linea=arch.readLine())!=null) {
			String[] partes = linea.split(",");
			ext[i][0] = partes[0];
			ext[i][1] = partes[1];
			ext[i][2] = partes[2];
			ext[i][3] = partes[3];
			ext[i][4] = partes[4];
			ext[i][5] = partes[5];
			ext[i][6] = partes[6];
			ext[i][7] = partes[7];
			i++;
		}
		arch.close();
	}
	
	public static void leerArchivoHum(String[][] hum) throws IOException{
		BufferedReader arch = new BufferedReader(new FileReader("h.txt"));
		String linea;
		int i=0;
		while((linea=arch.readLine())!=null) {
			String[] partes = linea.split(",");
			hum[i][0] = partes[0];
			hum[i][1] = partes[1];
			hum[i][2] = partes[2];
			hum[i][3] = partes[3];
			hum[i][4] = partes[4];
			hum[i][5] = partes[5];
			hum[i][6] = partes[6];
			hum[i][7] = partes[7];
			hum[i][8] = partes[8];
			i++;
		}
		arch.close();
	}
	
}
