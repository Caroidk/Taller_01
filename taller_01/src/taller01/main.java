package taller01;

import java.util.Random;
import java.util.Scanner;


import java.io.*;

public class main {

	public static void main(String[] args) throws IOException{
		
		String[][] extraterrestres = new String[100][8];
		String[][] humanos = new String[100][9];
		
		int cantExt = leerArchivoExt(extraterrestres);
		int cantHum = leerArchivoHum(humanos);
		
		traducirExt(extraterrestres, cantExt);
		traducirHum(humanos, cantHum);
		
		Menu(humanos, extraterrestres, cantHum, cantExt);
		
		//Verificar si la edad de los extraterrestres, altura y peso se guardan en metros y kgs
		//Verificar si la id es con random o ingresa desde pantalla, además de cómo se modifica (random o manual)
	}

	public static int leerArchivoExt(String[][] ext) throws IOException{
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
		return i;
	}
	
	public static int leerArchivoHum(String[][] hum) throws IOException{
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
		return i;
	}
	
	public static void traducirExt(String[][] ext, int cantExt) {
		cantExt = getCantExt(ext);
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
	
	public static void traducirHum(String[][] hum, int cantHum) {
		cantHum = getCantHum(hum);
		for(int i=0; i<cantHum; i++) {
			String[] datos = convertirDatos(Double.parseDouble(hum[i][5]), Double.parseDouble(hum[i][6]), Double.parseDouble(hum[i][7]), 2);
			hum[i][6] = datos[0];
			hum[i][7] = datos[1];
		}
	}
	
	public static String traducir(String dato) {
		String aux = "";
		for(int j=0; j<dato.length(); j++) {
			String palabra = dato.toUpperCase();
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
				default:
					chars[j] = palabra.charAt(j);
			}
			aux += chars[j];
		}
		return aux;
	}
	
	public static String[] convertirDatos(double edad, double altura, double peso, int op) {
		String[] datos = new String[3];
		if(op==1) {
			datos[0] = Double.toString(edad/2);
			datos[1] = Double.toString(altura/100);
			datos[2] = Double.toString(peso/1000);
		}if(op==2) {
			datos[0] = Double.toString(altura/100);
			datos[1] = Double.toString(peso/1000);
		}
		
		//Para convertirlos al reves
		
		if(op == 3) {
			datos[0] = Double.toString(edad * 2);
			datos[1] = Double.toString(altura * 100);
			datos[2] = Double.toString(peso * 1000);
		}
		return datos;
	}
	
	//1 
	public static void ingresarExt(String[][] ext, int cantExt) {
		cantExt = getCantExt(ext);
		System.out.println("-- INGRESAR EXTRATERRESTRE --");
		Scanner leerExt = new Scanner(System.in);
		System.out.print("- Ingrese especie: ");
		String especie = leerExt.nextLine();
		System.out.print("- Ingrese nombre: ");
		String nombre = leerExt.nextLine();
		Random ran = new Random();
		int id = ran.nextInt(10000000)+99999999;
		int i=0;
		while(i<cantExt) {
			if(ext[i][2].equals(Integer.toString(id))) {
				id = ran.nextInt(10000000)+99999999;
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
	
	public static void desplegarExt(String[][] ext, int cantExt) {
		cantExt = getCantExt(ext);
		for(int i=0; i<ext.length; i++) {
			if(ext[i][0]!=null) {
				System.out.println("Extraterrestre "+(i+1)+": "+"[Especie: "+ext[i][0]+", Nombre: "+ext[i][1]+", Identificación universal: "+ext[i][2]+", Planeta de origen: "+ext[i][3]+", Edad: "+ext[i][4]+", Altura: "+ext[i][5]+", Peso: "+ext[i][6]+", Tipo: "+ext[i][7]+"]");
			}
		}
	}
	
	public static void desplegarHum(String[][] hum, int cantHum) {
		for(int i=0; i<hum.length; i++) {
			if(hum[i][0]!=null) {
				System.out.println("Humano "+(i+1)+": "+"[Nacionalidad: "+hum[i][0]+", Nombre: "+hum[i][1]+", Identificación: "+hum[i][2]+", Región: "+hum[i][3]+", Ciudad: "+hum[i][4]+", Edad: "+hum[i][5]+", Altura: "+hum[i][6]+", Peso: "+hum[i][7]+", Planetas de trabajo: "+hum[i][8]+"]");
			}
		}
	}
	
	//2
	public static void modificarExt(String[][] ext, int cantExt) {
		cantExt = getCantExt(ext);
		System.out.println("-- MODIFICAR EXTRATERRESTRE --");
		desplegarExt(ext, cantExt);
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
					ext[num][0] = traducir(leerExt1.nextLine());
					break;
				case 2:
					System.out.print("- Ingrese nombre: ");
					ext[num][1] = traducir(leerExt1.nextLine());
					break;
				case 3:
					System.out.print("- Ingrese identificación: ");
					ext[num][2] = leerExt1.nextLine();
					break;
				case 4:
					System.out.print("- Ingrese planeta: ");
					ext[num][3] = traducir(leerExt1.nextLine());
					break;
				case 5:
					System.out.print("- Ingrese edad: ");
					int edad = Integer.parseInt(leerExt1.nextLine());
					ext[num][4] = Double.toString(edad/2);
					break;
				case 6:
					System.out.print("- Ingrese altura (en centímetros): ");
					int altura = Integer.parseInt(leerExt1.nextLine());
					ext[num][5] = Double.toString(altura/100);
					break;
				case 7:
					System.out.print("- Ingrese peso (en gramos): ");
					int peso = Integer.parseInt(leerExt1.nextLine());
					ext[num][6] = Double.toString(peso/1000);
					break;
				case 8:
					System.out.print("- Ingrese tipo (Invertebrado: I, Vertebrado: V, Flexible: F): ");
					String tipo = leerExt1.nextLine().toUpperCase();
					while(!tipo.equals("V") && !tipo.equals("I") && !tipo.equals("F")) {
						System.out.print("Tipo ingresado no es válido. Ingrese nuevamente: ");
						tipo = leerExt1.nextLine().toUpperCase();
					}
					ext[num][7] = tipo;
					break;
			}
		}
		System.out.println("Datos guardados exitosamente.");
		System.out.println("-----------------------------------------");
	}
	
	//3
	public static void ingresarHum(String[][] hum, int cantHum) {
		cantHum = getCantHum(hum);
		System.out.println("-- INGRESAR HUMANO --");
		Scanner leerHum = new Scanner(System.in);
		System.out.print("- Ingrese nacionalidad: ");
		String nacion = leerHum.nextLine();
		System.out.print("- Ingrese nombre: ");
		String nombre = leerHum.nextLine();
		Random ran = new Random();
		int id = ran.nextInt(10000000)+99999999;
		int i=0;
		while(i<cantHum) {
			if(hum[i][2].equals(Integer.toString(id))) {
				id = ran.nextInt(10000000)+99999999;
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
		cantHum++;
		System.out.println("Humano ingresado exitosamente.");
		System.out.println("-----------------------------------------");
	}
	
	//4
	//Falta modificar humano
	
	//5
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
	
	//6
	public static void eliminarExt(String[][] ext, int cantExt) {
		cantExt = getCantExt(ext);
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
			cantExt--;
		}
		System.out.println("-----------------------------------------");
	}
	
	//7
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
			cantHum--;
			System.out.println(cantHum);
		}
		System.out.println("-----------------------------------------");
	}
	
	//8
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
	
	//9
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

	//10
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
	
	public static void contarTipos(String[][] ext, int cantExt, int[] cant) {
		cantExt = getCantExt(ext);
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
	
	//11
	public static void desplegarTipoExt(String[][] ext, int cantExt) {
		cantExt = getCantExt(ext);
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
	
	public static void Menu(String[][] hum, String[][] ext, int cantHum, int cantExt) throws IOException {
		cantHum = getCantHum(hum);
		cantExt = getCantExt(ext);
		int op = 0;
		while(op!=12) {
			System.out.println("*********** MENÚ ***********");
			Scanner leer = new Scanner(System.in);
			System.out.println("1) Ingresar extraterrestre\n2) Modificar un extraterrestre\n3) Ingresar humano\n4) Modificar un humano\n5) Mostrar por nacionalidad\n6) Eliminar extraterrestre\n7) Eliminar humano\n8) Buscar por identificación universal\n9) Mostrar por planeta\n10) Mostrar por nacionalidad y porcentaje\n11) Mostrar cantidad de extraterrestres según tipo\n12) Salir");
			System.out.print("Su opción: ");
			op = Integer.parseInt(leer.nextLine());
			if(op==12) {
				System.out.println("Saliendo del sistema...");
				guardarExtraterrestres(ext);
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
					desplegarHum(hum, cantHum);
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
	}
	
	//Extras
	
	public static void guardarExtraterrestres(String [][] ext) throws IOException {
		
		FileWriter escribirTxt = new FileWriter("x.txt");
		
		for(int i = 0 ; i < getCantExt(ext) ; i++) {
			String [] datosTraducidos = convertirDatos(Double.parseDouble(ext[i][4]), Double.parseDouble(ext[i][5]), Double.parseDouble(ext[i][6]), 3);
			String linea = traducir(ext[i][0]) + "," + traducir(ext[i][1]) + "," + ext[i][2] + "," + traducir(ext[i][3]) + "," + datosTraducidos[0] + "," + datosTraducidos[1] + "," + datosTraducidos[2] + "," + ext[i][7];
			
			if(i!=0) {
				escribirTxt.write("\n");
			}
			
			escribirTxt.write(linea);
		}
		escribirTxt.close();
	}
	
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
	
	public static void printFila(String[][] matriz, int fila) {
		
		for(int i = 0; i < matriz[0].length ; i++) {
			System.out.print("["+matriz[fila][i]+"] ");
		}
		System.out.println("\n");
		
	}
	
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
