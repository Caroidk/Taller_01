package taller01;

import java.util.*;
import java.util.Scanner;

import java.io.*;

public class main {

	public static void main(String[] args) throws IOException{
		
		String[][] extraterrestres = new String[100][8];
		String[][] humanos = new String[100][9];
		
		leerArchivoExt(extraterrestres);
		leerArchivoHum(humanos);
		
		traducirExt(extraterrestres);
		traducirHum(humanos);
		
		int op = Menu(humanos, extraterrestres);
		
		if(op==12) {
			System.out.println(" -- SISTEMA CERRADO -- ");
			guardarDatos(extraterrestres,humanos);
		}
		
	}

	/***
	 * Reads and saves the information inside "x.txt".
	 * @param ext
	 * @throws IOException
	 */
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
	/***
	 * Reads and saves the information inside "h.txt".
	 * @param hum
	 * @throws IOException
	 */
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
	/***
	 * Translates the aliens information, converts the age to human age and converts the weight and height of humans to kilograms and meters respectively.
	 * @param ext
	 */
	public static void traducirExt(String[][] ext) {
		int cantExt = getCantExt(ext);
		for(int i=0; i<cantExt; i++) {
			ext[i][0] = traducir(ext[i][0]);
			ext[i][1] = traducir(ext[i][1]);
			ext[i][3] = traducir(ext[i][3]);
			String[] datos = convertirDatos(Double.parseDouble(ext[i][4]), Double.parseDouble(ext[i][5]), Double.parseDouble(ext[i][6]), 1);
			ext[i][4] = datos[0];
			ext[i][5] = datos[1];
			ext[i][6] = datos[2];
		}
	}
	
	/***
	 * Converts the weight and height of humans to kilograms and meters respectively.
	 * @param hum
	 */
	public static void traducirHum(String[][] hum) {
		int cantHum = getCantHum(hum);
		for(int i=0; i<cantHum; i++) {
			String[] datos = convertirDatos(Double.parseDouble(hum[i][5]), Double.parseDouble(hum[i][6]), Double.parseDouble(hum[i][7]), 2);
			hum[i][6] = datos[0];
			hum[i][7] = datos[1];
		}
	}
	/***
	 * Translates every single letter to human language.
	 * @param dato
	 * @return
	 */
	public static String traducir(String dato) {
		String aux = "";
		for(int j=0; j<dato.length(); j++) {
			String palabra = dato;
			char[] chars = new char[palabra.length()];
			switch(palabra.charAt(j)) {
				case 'A':
					chars[j] = 'E';
					break;
				case 'E':
					chars[j] = 'A';
					break;
				case 'I':
					chars[j] = 'O';
					break;
				case 'O':
					chars[j] = 'I';
					break;
				case 'a':
					chars[j] = 'e';
					break;
				case 'e':
					chars[j] = 'a';
					break;
				case 'i':
					chars[j] = 'o';
					break;
				case 'o':
					chars[j] = 'i';
					break;
				default:
					chars[j] = palabra.charAt(j);
			}
			aux += chars[j];
		}
		return aux;
	}
	/***
	 * Converts the entered parameters into kilograms, meters and human age, depending on the entered option.
	 * @param edad
	 * @param altura
	 * @param peso
	 * @param op
	 * @return
	 */
	public static String[] convertirDatos(double edad, double altura, double peso, int op) {
		String[] datos = new String[3];
		if(op==1) {
			datos[0] = Double.toString(edad/2);
			datos[1] = Double.toString(altura/100);
			datos[2] = Double.toString(peso/1000);
		}if(op==2) {
			datos[0] = Double.toString(altura/100);
			datos[1] = Double.toString(peso/1000);
		}else if(op==3) {
			datos[0] = Double.toString(edad*2);
			datos[1] = Double.toString(altura*100);
			datos[2] = Double.toString(peso*1000);
		}else if(op==4) {
			datos[0] = Double.toString(altura*100);
			datos[1] = Double.toString(peso*1000);
		}
		
		//Para convertirlos al reves
		
		if(op == 3) {
			datos[0] = Double.toString(edad * 2);
			datos[1] = Double.toString(altura * 100);
			datos[2] = Double.toString(peso * 1000);
		}
		return datos;
	}
	
	/***
	 * Creates a new alien.
	 * @param ext
	 * @param cantExt
	 */
	public static void ingresarExt(String[][] ext, int cantExt) {
		System.out.println("-- INGRESAR EXTRATERRESTRE --");
		Scanner leerExt = new Scanner(System.in);
		System.out.print("- Ingrese especie: ");
		String especie = leerExt.nextLine();
		System.out.print("- Ingrese nombre: ");
		String nombre = leerExt.nextLine();
		int id = (int) (Math.random() * ((99999999 + 1) - 10000000)) + 10000000;
		int i=0;
		while(i<cantExt) {
			if(ext[i][2].equals(Integer.toString(id))) {
				id = (int) (Math.random() * ((99999999 + 1) - 10000000)) + 10000000;
				i = 0;
			}
			i++;
		}
		System.out.print("- Ingrese planeta de origen: ");
		String planeta = leerExt.nextLine();
		System.out.print("- Ingrese edad: ");
		String edad = leerExt.nextLine();
		System.out.print("- Ingrese altura (en centímetros): ");
		String altura = leerExt.nextLine();
		System.out.print("- Ingrese peso (en gramos): ");
		String peso = leerExt.nextLine();
		System.out.print("- Ingrese tipo de extraterrestre (Invertebrado: I, Vertebrado: V, Flexible: F): ");
		String tipo = leerExt.nextLine().toUpperCase();
		while(!tipo.equals("V") && !tipo.equals("I") && !tipo.equals("F")) {
			System.out.print("Tipo ingresado no es válido. Ingrese nuevamente: ");
			tipo = leerExt.nextLine().toUpperCase();
		}
		String[] datos = convertirDatos(Double.parseDouble(edad), Double.parseDouble(altura), Double.parseDouble(peso), 1);
		ext[cantExt][0] = traducir(especie);
		ext[cantExt][1] = traducir(nombre);
		ext[cantExt][2] = Integer.toString(id);
		ext[cantExt][3] = traducir(planeta);
		ext[cantExt][4] = datos[0];
		ext[cantExt][5] = datos[1];
		ext[cantExt][6] = datos[2];
		ext[cantExt][7] = tipo;
		System.out.println("Extraterrestre ingresado exitosamente.");
		System.out.println(ext[cantExt][0]+" - "+ext[cantExt][2]);
		cantExt++;
		System.out.println("-----------------------------------------");
	}
	/***
	 * Shows all the current aliens in the database.
	 * @param ext
	 * @param cantExt
	 */
	public static void desplegarExt(String[][] ext, int cantExt) {
		for(int i=0; i<ext.length; i++) {
			if(ext[i][0]!=null) {
				System.out.println("Extraterrestre "+(i+1)+": "+"[Especie: "+ext[i][0]+", Nombre: "+ext[i][1]+", Identificación universal: "+ext[i][2]+", Planeta de origen: "+ext[i][3]+", Edad: "+ext[i][4]+", Altura: "+ext[i][5]+", Peso: "+ext[i][6]+", Tipo: "+ext[i][7]+"]");
			}
		}
	}
	/***
	 * Shows all the current humans in the database.
	 * @param hum
	 * @param cantHum
	 */
	public static void desplegarHum(String[][] hum, int cantHum) {
		for(int i=0; i<hum.length; i++) {
			if(hum[i][0]!=null) {
				System.out.println("Humano "+(i+1)+": "+"[Nacionalidad: "+hum[i][0]+", Nombre: "+hum[i][1]+", Identificación: "+hum[i][2]+", Región: "+hum[i][3]+", Ciudad: "+hum[i][4]+", Edad: "+hum[i][5]+", Altura: "+hum[i][6]+", Peso: "+hum[i][7]+", Planetas de trabajo: "+hum[i][8]+"]");
			}
		}
	}
	
	/***
	 * Modifies any parameter of a certain alien.
	 * @param ext
	 * @param cantExt
	 */
	public static void modificarExt(String[][] ext, int cantExt) {
		System.out.println("-- MODIFICAR EXTRATERRESTRE --");
		desplegarExt(ext,cantExt);
		Scanner leerExt1 = new Scanner(System.in);
		System.out.print("- Ingrese número de extraterrestre a modificar: ");
		int num = Integer.parseInt(leerExt1.nextLine());
		while(num<1 || num>cantExt) {
			System.out.print("Número de extraterrestre no válido. Ingrese nuevamente: ");
			num = Integer.parseInt(leerExt1.nextLine());
		}
		int op = 0;
		while(op!=9) {
			System.out.print("¿Qué dato desea modificar?\n1) Especie\n2) Nombre\n3) Identificación\n4) Planeta de origen\n5) Edad\n6) Altura\n7) Peso\n8) Tipo\n9) Guardar y salir\nSu opción: ");
			op = Integer.parseInt(leerExt1.nextLine());
			while(op<1 || op>9) {
				System.out.print("Opción no válida. Ingrese nuevamente: ");
				op = Integer.parseInt(leerExt1.nextLine());
			}
			switch(op) {
				case 1:
					System.out.print("- Ingrese especie: ");
					ext[num-1][0] = traducir(leerExt1.nextLine());
					break;
				case 2:
					System.out.print("- Ingrese nombre: ");
					ext[num-1][1] = traducir(leerExt1.nextLine());
					break;
				case 3:
					System.out.print("- Ingrese identificación: ");
					ext[num-1][2] = leerExt1.nextLine();
					break;
				case 4:
					System.out.print("- Ingrese planeta: ");
					ext[num-1][3] = traducir(leerExt1.nextLine());
					break;
				case 5:
					System.out.print("- Ingrese edad: ");
					double edad = Integer.parseInt(leerExt1.nextLine());
					ext[num-1][4] = Double.toString(edad/2);
					break;
				case 6:
					System.out.print("- Ingrese altura (en centímetros): ");
					double altura = Integer.parseInt(leerExt1.nextLine());
					ext[num-1][5] = Double.toString(altura/100);
					break;
				case 7:
					System.out.print("- Ingrese peso (en gramos): ");
					double peso = Integer.parseInt(leerExt1.nextLine());
					ext[num-1][6] = Double.toString(peso/1000);
					break;
				case 8:
					System.out.print("- Ingrese tipo (Invertebrado: I, Vertebrado: V, Flexible: F): ");
					String tipo = leerExt1.nextLine().toUpperCase();
					while(!tipo.equals("V") && !tipo.equals("I") && !tipo.equals("F")) {
						System.out.print("Tipo ingresado no es válido. Ingrese nuevamente: ");
						tipo = leerExt1.nextLine().toUpperCase();
					}
					ext[num-1][7] = tipo;
					break;
			}
		}
		System.out.println("Datos guardados exitosamente.");
		System.out.println("-----------------------------------------");
	}
	/***
	 * Creates a new human.
	 * @param hum
	 * @param cantHum
	 */
	public static void ingresarHum(String[][] hum, int cantHum) {
		cantHum = getCantHum(hum);
		System.out.println("-- INGRESAR HUMANO --");
		Scanner leerHum = new Scanner(System.in);
		System.out.print("- Ingrese nacionalidad: ");
		String nacion = leerHum.nextLine();
		System.out.print("- Ingrese nombre: ");
		String nombre = leerHum.nextLine();
		int id = (int) (Math.random() * ((99999999 + 1) - 10000000)) + 10000000;
		int i=0;
		while(i<cantHum) {
			if(hum[i][2].equals(Integer.toString(id))) {
				id = (int) (Math.random() * ((99999999 + 1) - 10000000)) + 10000000;
				i = 0;
			}
			i++;
		}
		System.out.print("- Ingrese región: ");
		String region = leerHum.nextLine();
		System.out.print("- Ingrese ciudad: ");
		String ciudad = leerHum.nextLine();
		System.out.print("- Ingrese edad: ");
		String edad = leerHum.nextLine();
		System.out.print("- Ingrese altura: ");
		String altura = leerHum.nextLine();
		System.out.print("- Ingrese peso: ");
		String peso = leerHum.nextLine();
		System.out.print("- Ingrese planetas de trabajo (formato PLANETA1/PLANETA2/ETC): ");
		String trabajo = leerHum.nextLine().toUpperCase();
		String[] datos = convertirDatos(Double.parseDouble(edad), Double.parseDouble(altura), Double.parseDouble(peso), 2);
		hum[cantHum][0] = nacion;
		hum[cantHum][1] = nombre;
		hum[cantHum][2] = Integer.toString(id);
		hum[cantHum][3] = region;
		hum[cantHum][4] = ciudad;
		hum[cantHum][5] = String.valueOf(edad);
		hum[cantHum][6] = datos[0];
		hum[cantHum][7] = datos[1];
		hum[cantHum][8] = trabajo;
		System.out.println("Humano ingresado exitosamente.");
		System.out.println("-----------------------------------------");
	}
	/***
	 * Modifies any parameter of a certain human.
	 * @param hum
	 * @param cantHum
	 */
	public static void modificarHum(String[][] hum, int cantHum) {
		System.out.println("-- MODIFICAR EXTRATERRESTRE --");
		desplegarHum(hum,cantHum);
		Scanner leerExt1 = new Scanner(System.in);
		System.out.print("- Ingrese número de humano a modificar: ");
		int num = Integer.parseInt(leerExt1.nextLine());
		while(num<1 || num>cantHum) {
			System.out.print("Número de humano no válido. Ingrese nuevamente: ");
			num = Integer.parseInt(leerExt1.nextLine());
		}
		int op = 0;
		while(op!=10) {
			System.out.print("¿Qué dato desea modificar?\n1) Nacionalidad\n2) Nombre\n3) Identificación\n4) Región\n5) Ciudad\n6) Edad\n7) Altura\n8) Peso\n9) Planetas de trabajo\n10) Guardar y salir\nSu opción: ");
			op = Integer.parseInt(leerExt1.nextLine());
			while(op<1 || op>10) {
				System.out.print("Opción no válida. Ingrese nuevamente: ");
				op = Integer.parseInt(leerExt1.nextLine());
			}
			switch(op) {
				case 1:
					System.out.print("- Ingrese nacionalidad: ");
					hum[num-1][0] = leerExt1.nextLine();
					break;
				case 2:
					System.out.print("- Ingrese nombre: ");
					hum[num-1][1] = leerExt1.nextLine();
					break;
				case 3:
					System.out.print("- Ingrese identificación: ");
					hum[num-1][2] = leerExt1.nextLine();
					break;
				case 4:
					System.out.print("- Ingrese región: ");
					hum[num-1][3] = leerExt1.nextLine();
					break;
				case 5:
					System.out.print("- Ingrese ciudad: ");
					hum[num-1][4] = leerExt1.nextLine();
					break;
				case 6:
					System.out.print("- Ingrese edad: ");
					hum[num-1][5] = leerExt1.nextLine();
					break;
				case 7:
					System.out.print("- Ingrese altura (en centímetros): ");
					double altura = Double.parseDouble(leerExt1.nextLine());
					hum[num-1][6] = Double.toString(altura/100);
					break;
				case 8:
					System.out.print("- Ingrese peso (en gramos): ");
					double peso = Double.parseDouble(leerExt1.nextLine());
					hum[num-1][7] = Double.toString(peso/1000);
					break;
				case 9:
					System.out.print("- Ingrese planetas de trabajo (formato PLANETA1/PLANETA2/ETC): ");
					hum[num-1][8] = leerExt1.nextLine().toUpperCase();
					break;
			}
		}
		System.out.println("Datos guardados exitosamente.");
		System.out.println("-----------------------------------------");
	}
	/***
	 * Finds and shows all the current humans in the database who matches the entered nationality.
	 * @param hum
	 * @param cantHum
	 */
	public static void desplegarNacionalidad(String[][] hum, int cantHum) {
		cantHum = getCantHum(hum);
		System.out.println("-- MOSTRAR POR NACIONALIDAD --");
		Scanner leerNac = new Scanner(System.in);
		System.out.print("- Ingrese nacionalidad: ");
		String nacion = leerNac.nextLine();
		int i;
		for(i=0; i<cantHum; i++) {
			if(hum[i][0].equals(nacion)) {
				System.out.println("[Nacionalidad: "+hum[i][0]+", Nombre: "+hum[i][1]+", Identificación: "+hum[i][2]+", Región: "+hum[i][3]+", Ciudad: "+hum[i][4]+", Edad: "+hum[i][5]+", Altura: "+hum[i][6]+", Peso: "+hum[i][7]+", Planetas de trabajo: "+hum[i][8]+"]");
			}
		}
		if(i==cantHum) {
			System.out.println("No hay humanos que coincidan con la nacionalidad ingresada.");
		}
		System.out.println("-----------------------------------------");
	}
	/***
	 * Eliminates a certain alien from the database.
	 * @param ext
	 * @param cantExt
	 */
	public static void eliminarExt(String[][] ext, int cantExt) {
		System.out.println("-- ELIMINAR EXTRATERRESTRE --");
		Scanner deleteExt = new Scanner(System.in);
		System.out.print("- Ingrese número de identificación universal: ");
		String id = deleteExt.nextLine();
		int i;
		for(i=0; i<cantExt; i++){
			if(ext[i][2].equals(id)) {
				break;
			}
		}
		if(i==cantExt) {
			System.out.println("No hay extraterrestres que coincidan con la identificación ingresada.");
		}else {
			for(int j=i; j<cantExt;j++) {
				ext[j] = ext[j+1];
			}
			System.out.println("Extraterrestre eliminado exitosamente.");
		}
		System.out.println("-----------------------------------------");
	}
	/***
	 * Eliminates a certain human from the database.
	 * @param hum
	 * @param cantHum
	 */
	public static void eliminarHum(String[][] hum, int cantHum) {
		cantHum = getCantHum(hum);
		System.out.println("-- ELIMINAR HUMANO --");
		Scanner deleteHum = new Scanner(System.in);
		System.out.print("- Ingrese número de identificación: ");
		String id = deleteHum.nextLine();
		System.out.println(cantHum);
		int i;
		for(i=0; i<cantHum; i++){
			if(hum[i][2].equals(id)) {
				break;
			}
		}
		if(i==cantHum) {
			System.out.println("No hay humanos que coincidan con la identificación ingresada.");
		}else {
			for(int j=i; j<cantHum;j++) {
				hum[j] = hum[j+1];
			}
			System.out.println("Humano eliminado exitosamente.");
		}
		System.out.println("-----------------------------------------");
	}
	/***
	 * Looks for an alien who match the entered identification.
	 * @param ext
	 * @param cantExt
	 */
	public static void buscarExt(String[][] ext, int cantExt) {
		cantExt = getCantExt(ext);
		System.out.println("-- BUSCAR POR IDENTIFICACIÓN UNIVERSAL --");
		Scanner buscarExt = new Scanner(System.in);
		System.out.print("- Ingrese número de indentificación universal: ");
		String id = buscarExt.nextLine();
		int i;
		for(i=0; i<cantExt; i++) {
			if(ext[i][2].equals(id)) {
				break;
			}
		}
		if(i==cantExt) {
			System.out.println("No hay extraterrestres que coincidan con la identificación ingresada.");
		}else {
			System.out.println("[Especie: "+ext[i][0]+", Nombre: "+ext[i][1]+", Identificación universal: "+ext[i][2]+", Planeta de origen: "+ext[i][3]+", Edad: "+ext[i][4]+", Altura: "+ext[i][5]+", Peso: "+ext[i][6]+", Tipo: "+ext[i][7]+"]");
		}
		System.out.println("-----------------------------------------");
	}
	/***
	 * Finds and shows all the current humans in the database who works in the entered planet and the aliens who lives there.
	 * @param ext
	 * @param hum
	 * @param cantExt
	 */
	public static void mostrarPlaneta(String [][] ext, String [][] hum, int cantExt) {
		cantExt = getCantExt(ext);
		System.out.println("-- MOSTRAR POR PLANETA --");
		Scanner scan = new Scanner(System.in);
		System.out.println("-Ingrese nombre del planeta a consultar: ");
		String planeta = scan.nextLine().toUpperCase();
		
		double cantExtPorPlaneta = extraterrestresPorPlaneta(planeta, ext);
		
		double porcentaje = (cantExtPorPlaneta/cantExt) * 100;
		
		System.out.println("-Porcentaje de Extraterrestres en " + planeta.toUpperCase() +": " + porcentaje +" %");
		System.out.println("-------------------------------");
		
		System.out.println("-Humanos que trabajan en el planeta " + planeta.toUpperCase() + ": " + humanosTrabajadoresEnPlaneta(planeta, hum));
		
		System.out.println("-------------------------------");
	}
	/***
	 * Looks for all the current aliens in the database who lives in the entered planet.
	 * @param planeta
	 * @param ext
	 * @return
	 */
	public static int extraterrestresPorPlaneta(String planeta,String [][] ext) {
		planeta = planeta.toUpperCase();
		System.out.println("-------------------------------");
		System.out.println("Extraterrestres del planeta: ");
		int cont = 0;
		for(int fila = 0 ; fila < ext.length ; fila++) {
			if(ext[fila][0] != null) {
				
				if(planeta.equals(ext[fila][3])) {
					printFila(ext,fila);
					cont++;
				}
			}
		}
		System.out.println("-------------------------------");
		return cont;
	}
	/***
	 * Looks for all the current humans of the database who works in the entered planet.
	 * @param planeta
	 * @param hum
	 * @return
	 */
	public static int humanosTrabajadoresEnPlaneta(String planeta, String [][] hum) {
		int cont = 0;
		String [] planetas;
		for(int fila = 0; fila < hum.length ; fila++) {
			if(hum[fila][0] != null) {
				planetas = hum[fila][8].split("/");
				for(int i = 0 ; i < planetas.length ; i++ )
					if(planeta.toUpperCase().equals(planetas[i])) {
						cont++;
					}
			}
			else {
				break;
			}
		}
		return cont;
	}
	/***
	 * Finds and shows all the humans who matches with the entered nationality and the percentage with respect to all the humans in the database.
	 * @param hum
	 * @param cantHum
	 */
	public static void desplegarPorNacionalidad(String[][] hum, int cantHum) {
		cantHum = getCantHum(hum);
		System.out.println("-- MOSTRAR POR NACIONALIDAD --");
		Scanner leerNacion = new Scanner(System.in);
		System.out.print("- Ingrese nacionalidad: ");
		String nacion = leerNacion.nextLine();
		int cont = 0;
		int i;
		for(i=0; i<cantHum; i++) {
			if(hum[i][0].equals(nacion)) {
				System.out.println("[Nacionalidad: "+hum[i][0]+", Nombre: "+hum[i][1]+", Identificación: "+hum[i][2]+", Región: "+hum[i][3]+", Ciudad: "+hum[i][4]+", Edad: "+hum[i][5]+", Altura: "+hum[i][6]+", Peso: "+hum[i][7]+", Planetas de trabajo: "+hum[i][8]+"]");
				cont++;
			}
		}
		if(cont==0) {
			System.out.println("No hay humanos que coincidan con la nacionalidad ingresada.");
		}else {
			System.out.println("- Porcentaje de humanos con la nacionalidad "+nacion+": "+(int)((cont*100)/cantHum)+"%");
		}
		System.out.println("-----------------------------------------");
	}
	/***
	 * Counts how many aliens in the database are according to their type.
	 * @param ext
	 * @param cantExt
	 * @param cant
	 */
	public static void contarTipos(String[][] ext, int cantExt, int[] cant) {
		for(int i=0; i<cantExt; i++) {
			switch(ext[i][7]) {
				case "I":
					cant[0]++;
					break;
				case "V":
					cant[1]++;
					break;
				case "F": 
					cant[2]++;
					break;
			}
		}
	}
	/***
	 * Shows the count of the aliens according to their type.
	 * @param ext
	 * @param cantExt
	 */
	public static void desplegarTipoExt(String[][] ext, int cantExt) {
		System.out.println("-- MOSTRAR EXTRATERRESTRES SEGÚN TIPO --");
		int[] cantTipos = new int[3];
		contarTipos(ext, cantExt, cantTipos);
		
		int promI = (int)((cantTipos[0]*100)/cantExt);
		System.out.println("  _________________");
		System.out.print(" | I |");
		if(cantTipos[0]<10) {
			System.out.print("   "+cantTipos[0]+" |");
		}else if(cantTipos[0]>10 && cantTipos[0]<100) {
			System.out.print("  "+cantTipos[0]+" |");
		}else {
			System.out.print(" "+cantTipos[0]+" |");
		}
		if(promI<10) {
			System.out.print("    "+promI+"% |");
		}else if(promI>10 && promI<100) {
			System.out.print("   "+promI+"% |");
		}else {
			System.out.print("  "+promI+"% |");
		}
		System.out.println();
		System.out.println("  -----------------");
		int promV = (int)((cantTipos[1]*100)/cantExt);
		System.out.print(" | V |");
		if(cantTipos[1]<10) {
			System.out.print("   "+cantTipos[1]+" |");
		}else if(cantTipos[1]>10 && cantTipos[1]<100) {
			System.out.print("  "+cantTipos[1]+" |");
		}else {
			System.out.print(" "+cantTipos[1]+" |");
		}
		if(promV<10) {
			System.out.print("    "+promV+"% |");
		}else if(promV>10 && promV<100) {
			System.out.print("   "+promV+"% |");
		}else {
			System.out.print("  "+promV+"% |");
		}
		System.out.println();
		System.out.println("  -----------------");
		int promF = (int)((cantTipos[2]*100)/cantExt);
		System.out.print(" | F |");
		if(cantTipos[2]<10) {
			System.out.print("   "+cantTipos[2]+" |");
		}else if(cantTipos[2]>10 && cantTipos[2]<100) {
			System.out.print("  "+cantTipos[2]+" |");
		}else {
			System.out.print(" "+cantTipos[2]+" |");
		}
		if(promF<10) {
			System.out.print("    "+promF+"% |");
		}else if(promF>10 && promF<100) {
			System.out.print("   "+promF+"% |");
		}else {
			System.out.print("  "+promF+"% |");
		}
		System.out.println();
		System.out.println("  —————————————————");
		System.out.println("-----------------------------------------");
	}
	/***
	 * Saves all the new information into their corresponding files.
	 * @param ext
	 * @param hum
	 * @throws IOException
	 */
	public static void guardarDatos(String[][] ext, String[][] hum) throws IOException{
		BufferedWriter archX = new BufferedWriter(new FileWriter("x.txt"));
		int cantExt = getCantExt(ext);
		for(int i=0; i<cantExt; i++) {
			String[] datos = convertirDatos(Double.parseDouble(ext[i][4]),Double.parseDouble(ext[i][5]),Double.parseDouble(ext[i][6]),3);
			String linea = traducir(ext[i][0])+","+traducir(ext[i][1])+","+ext[i][2]+","+traducir(ext[i][3])+","+(int)Double.parseDouble(datos[0])+","+(int)Double.parseDouble(datos[1])+","+(int)Double.parseDouble(datos[2])+","+ext[i][7];
			if(i!=cantExt-1) {
				linea += "\n";
			}
			archX.write(linea);
		}
		archX.close();
		
		BufferedWriter archH = new BufferedWriter(new FileWriter("h.txt"));
		int cantHum = getCantExt(hum);
		for(int i=0; i<cantHum; i++) {
			String[] datos = convertirDatos(Double.parseDouble(hum[i][5]),Double.parseDouble(hum[i][6]),Double.parseDouble(hum[i][7]),4);
			String linea = hum[i][0]+","+hum[i][1]+","+hum[i][2]+","+hum[i][3]+","+hum[i][4]+","+hum[i][5]+","+(int)Double.parseDouble(datos[0])+","+(int)Double.parseDouble(datos[1])+","+hum[i][8];
			if(i!=cantHum-1) {
				linea += "\n";
			}
			archH.write(linea);
		}
		archH.close();
	}
	/***
	 * Executes the main menu.
	 * @param hum
	 * @param ext
	 * @return the entered option
	 */
	public static int Menu(String[][] hum, String[][] ext){
		int op = 0;
		while(op!=12) {
			System.out.println("*********** MENÚ ***********");
			int cantExt = getCantExt(ext);
			int cantHum = getCantHum(hum);
			Scanner leer = new Scanner(System.in);
			System.out.println("1) Ingresar extraterrestre\n2) Modificar un extraterrestre\n3) Ingresar humano\n4) Modificar un humano\n5) Mostrar por nacionalidad\n6) Eliminar extraterrestre\n7) Eliminar humano\n8) Buscar por identificación universal\n9) Mostrar por planeta\n10) Mostrar por nacionalidad y porcentaje\n11) Mostrar cantidad de extraterrestres según tipo\n12) Salir");
			System.out.print("Su opción: ");
			op = Integer.parseInt(leer.nextLine());
			if(op==12) {
				System.out.println("Saliendo del sistema...");
			}
			while(op<1 || op>12) {
				System.out.print("Opción ingresada no es válida. Ingrese nuevamente: ");
				op = Integer.parseInt(leer.nextLine());
			}
			switch(op) {
				case 1: 
					ingresarExt(ext, cantExt);
					break;
				case 2:
					modificarExt(ext, cantExt);
					break;
				case 3:
					ingresarHum(hum, cantHum);
					break;
				case 4:
					modificarHum(hum, cantHum);
					break;
				case 5:
					desplegarNacionalidad(hum, cantHum);
					break;
				case 6:
					eliminarExt(ext, cantExt);
					break;
				case 7:
					eliminarHum(hum, cantHum);
					break;
				case 8:
					buscarExt(ext, cantExt);
					break;
				case 9:
					mostrarPlaneta(ext,hum,cantExt);
					break;
				case 10:
					desplegarPorNacionalidad(hum, cantHum);
					break;
				case 11:
					desplegarTipoExt(ext, cantExt);
					break;
			}
		}
		return op;
	}
	
	//Extra
	/***
	 * Prints a certain array.
	 * @param matriz
	 */
	public static void printMatriz(String [][] matriz) {

		for(int f = 0 ; f < matriz.length ; f++) {
			if(matriz[f][0] != null) {
				for(int i = 0; i < matriz[0].length ; i++) {
					System.out.print("["+matriz[f][i]+"] ");
				}
				System.out.println("\n");
			}
			else {
				break;
			}
		}
	}
	/***
	 * Prints the rows of a certain array.
	 * @param matriz
	 * @param fila
	 */
	public static void printFila(String[][] matriz, int fila) {
		
		for(int i = 0; i < matriz[0].length ; i++) {
			System.out.print("["+matriz[fila][i]+"] ");
		}
		System.out.println("\n");
		
	}
	/***
	 * Counts all the current aliens in the database.
	 * @param ext
	 * @return the number of aliens
	 */
	public static int getCantExt(String [][] ext) {
		int cont = 0;
		for(int fila = 0 ; fila < ext.length ; fila++) {
			if(ext[fila][0] != null) {	
				cont++;
			}
			else {
				break;
			}
		}
		return cont;
	}
	/***
	 * Counts all the current humans in the database.
	 * @param hum
	 * @return the number of humans
	 */
	public static int getCantHum(String [][] hum) {
		int cont = 0;
		for(int fila = 0 ; fila < hum.length ; fila++) {
			if(hum[fila][0] != null) {	
				cont++;
			}
			else {
				break;
			}
		}
		return cont;
	}
}
