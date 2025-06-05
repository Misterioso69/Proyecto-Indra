package Funciones;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import Clases.Categoria;
import Clases.Evento;
import Clases.Organizador;
import Clases.Usuario;

public class Menu {
	private static Scanner sc = new Scanner(System.in);
	private static ArrayList<Usuario> listaUsu = new ArrayList<Usuario>();
	private static ArrayList<Organizador> listaOrga = new ArrayList<Organizador>();
	private static ArrayList<Evento> listaEven = new ArrayList<Evento>();
	private static Map<Evento, ArrayList<Usuario>> incriscion = new HashMap<Evento, ArrayList<Usuario>>();

	public static void menu() {
		System.out.println("1.Crear Usario\n2.Crear Organizador\n3.Crear Evento\n4.Inscribirse a un Evento\n5.Listar Todo\n6.Borra dato\n7.Salir");
		System.out.println("----------------------------");
		System.out.print("Elige una opcion:");
		int a = sc.nextInt();
		switch (a) {
		case 1:
			crear_Usuario();
			break;
		case 2:
			crear_Organizador();
			break;
		case 3:
			crear_Evento();
			break;
		case 4:
			incribirse();
			break;
		case 5:
			listar_eventos();
			break;
		case 6:
			delete();
			break;
		case 7:
			salir();
			break;
		default:
			System.out.println("\nOpcion no valida\n ");
			menu();
		}
	}

	public static Usuario crear_Usuario() {
		sc.nextLine();
		System.out.println("Escribe tu nombre");
		String nombre = sc.nextLine();
		System.out.println("Escribe tu correo");
		String correo = sc.nextLine();
		System.out.println("Escibe la contraseña");
		String contra = sc.nextLine();
		Usuario a = new Usuario(nombre, correo, contra);
		listaUsu.add(a);
		System.out.println("\nCreacion completada\n");
		menu();
		return a;
	}

	public static Organizador crear_Organizador() {
		sc.nextLine();
		System.out.println("Escribe tu nombre");
		String nombre = sc.nextLine();
		System.out.println("Escribe tu correo");
		String correo = sc.nextLine();
		Organizador a = new Organizador(nombre, correo);
		listaOrga.add(a);
		System.out.println("\nCreacion completada\n");
		menu();
		return a;
	}

	public static void crear_Evento() {
		sc.nextLine();
		System.out.println("Escibe el nombre");
		String nombre = sc.nextLine();
		System.out.println("Escibe la fecha");
		String fec = sc.nextLine();
		System.out.println("Escribe la duracion en minutos");
		int duracion = sc.nextInt();
		sc.nextLine();
		System.out.println("Escribe el lugar");
		String lugar = sc.nextLine();
		System.out.println("Escribe el nombre del Organizadoe que lo crea");
		String orga = sc.nextLine();
		Organizador or = null;
		boolean existeOr = false;
		Iterator<Organizador> i = listaOrga.iterator();
		while (i.hasNext()) {
			Organizador a = i.next();
			if (a.getNombre().equals(orga)) {
				existeOr = true;
				or = a;
				break;
			}
		}
		if (!existeOr) {
			System.out.println("\nEl nombre del Organizador no existe, Error\n");
			menu();
		}
		System.out.println("Escribe la categoria ");
		String cate = sc.nextLine();
		try {
			Categoria cat = Categoria.valueOf(cate);
			Evento eve = new Evento(nombre, fec, duracion, lugar, or, cat);
			listaEven.add(eve);
			System.out.println("\nCreacion completada\n");
		} catch (IllegalArgumentException e) {
			System.out.println("\nLa categoria no existe, Error\n");
		}
		menu();
	}

	public static void incribirse() {
		sc.nextLine();
		System.out.println("Escibe el evento que te quieres inscribir");
		String nomeve = sc.nextLine();
		boolean esta = false;
		Evento eve = null;
		Iterator<Evento> i = listaEven.iterator();
		while (i.hasNext()) {
			Evento a = i.next();
			if (nomeve.equals(a.getNombre())) {
				esta = true;
				eve = a;
				break;
			}
		}
		if (!esta) {
			System.out.println("\nEl nombre de el evento no existe, Error\n");
			menu();
			return;
		}
		System.out.println("Escribe tu nombre de Usuario");
		String nomusu = sc.nextLine();
		Usuario usu = null;
		boolean estausu = false;
		Iterator<Usuario> it = listaUsu.iterator();
		while (it.hasNext()) {
			Usuario a = it.next();
			if (nomusu.equals(a.getNombre())) {
				estausu = true;
				usu = a;
				break;
			}
		}
		if (!estausu) {
			System.out.println("\nEl nombre no existe, Error\n");
			menu();
			return;
		}
		ArrayList<Usuario> lus = incriscion.getOrDefault(eve, new ArrayList<>());
		if (lus.contains(usu)) {
			System.out.println("\nEste usuario ya está inscrito en el evento\n");
			menu();
			return;
		}

		lus.add(usu);
		incriscion.put(eve, lus);

		System.out.println("\nIncriscion completada\n");
		menu();
	}

	public static void listar_eventos() {
		System.out.print("\nUsuarios:");
		Iterator<Usuario> us = listaUsu.iterator();
		while (us.hasNext()) {
			Usuario a = us.next();
			System.out.print(a);
		}
		System.out.println();
		System.out.print("Organizadores: ");
		Iterator<Organizador> or = listaOrga.iterator();
		while (or.hasNext()) {
			Organizador a = or.next();
			System.out.print("[ nombre= " + a.getNombre() + ", correo=" + a.getCorreo() + "] ");
		}
		System.out.println();
		System.out.print("Eventos: ");
		Iterator<Evento> ev = listaEven.iterator();
		while (ev.hasNext()) {
			Evento a = ev.next();
			System.out.print(a);
		}
		System.out.println();
		System.out.print("Inscripcion: ");
		Iterator<Map.Entry<Evento, ArrayList<Usuario>>> in = incriscion.entrySet().iterator();
		while (in.hasNext()) {
			Map.Entry<Evento, ArrayList<Usuario>> map = in.next();
			Evento e = map.getKey();
			ArrayList<Usuario> uu = map.getValue();
			System.out.print("Eveneto: " + e + " Usuarios: " + uu);
		}
		System.out.println();
		System.out.println();
		menu();
	}

	public static void delete() {
		System.out.println("\n¿Que quieres borrar: \n1.Usuario\n2.Organizador\n3.Eventos\n4.Inscripcion");
		int caso = sc.nextInt();
		switch (caso) {
		case 1:
			System.out.print("Usuarios:\n");
			Iterator<Usuario> us = listaUsu.iterator();
			while (us.hasNext()) {
				Usuario a = us.next();
				System.out.print(a);
			}
			sc.nextLine();
			System.out.println("\n¿Que Usuario quires borrar?");
			String nom = sc.nextLine();
			boolean esta =false;
			Usuario u = null;
			Iterator<Usuario>usu = listaUsu.iterator();
			while (usu.hasNext()) {
				Usuario a = usu.next();
				if(a.getNombre().equals(nom)) {
					esta=true;
					u=a;
				}
			}
			if(esta) {
				listaUsu.remove(u);
				System.out.println("Usuario borrado\n");
				menu();
			}else {
				System.out.println("El usuario no existe\n");
				menu();
			}
			break;
		case 2:
			System.out.print("Organizador:\n");
			Iterator<Organizador> or = listaOrga.iterator();
			while (or.hasNext()) {
				Organizador a = or.next();
				System.out.print(a);
			}
			sc.nextLine();
			System.out.println("\n¿Que Organizador quires borrar?");
			String nomb = sc.nextLine();
			boolean estao =false;
			Organizador o = null;
			Iterator<Organizador>org = listaOrga.iterator();
			while (org.hasNext()) {
				Organizador a = org.next();
				if(a.getNombre().equals(nomb)) {
					estao=true;
					o=a;
				}
			}
			if(estao) {
				listaOrga.remove(o);
				System.out.println("Organizador borrado\n");
				menu();
			}else {
				System.out.println("El organizador no existe\n");
				menu();
			}
			break;
		case 3:
			System.out.print("Evento:\n");
			Iterator<Evento> ev = listaEven.iterator();
			while (ev.hasNext()) {
				Evento a = ev.next();
				System.out.print(a);
			}
			sc.nextLine();
			System.out.println("\n¿Que Evento quires borrar?");
			String nombr = sc.nextLine();
			boolean estae =false;
			Evento e = null;
			Iterator<Evento>eve= listaEven.iterator();
			while (eve.hasNext()) {
				Evento a = eve.next();
				if(a.getNombre().equals(nombr)) {
					estae=true;
					e=a;
				}
			}
			if(estae) {
				listaEven.remove(e);
				System.out.println("Evento borrado\n");
				menu();
			}else {
				System.out.println("El organizador no existe\n");
				menu();
			}
			break;
		case 4:
			sc.nextLine();
			System.out.print("Inscripcion:\n");
			Iterator<Map.Entry<Evento, ArrayList<Usuario>>> in = incriscion.entrySet().iterator();
			while (in.hasNext()) {
			    Map.Entry<Evento, ArrayList<Usuario>> map = in.next();
			    Evento aa = map.getKey();
			    ArrayList<Usuario> uu = map.getValue();
			    System.out.print("Evento: " + aa + " Usuarios: " + uu);
			}

			System.out.println("\nDe que evento te quieres quitar, diga el nombre");
			String eventonombre = sc.nextLine();
			System.out.println("Dime el nombre de usuario");
			String usuarionombre = sc.nextLine();

			boolean estausuario = false;
			Iterator<Map.Entry<Evento, ArrayList<Usuario>>> iterator = incriscion.entrySet().iterator();

			while (iterator.hasNext()) {
			    Map.Entry<Evento, ArrayList<Usuario>> map = iterator.next();
			    Evento aa = map.getKey();
			    ArrayList<Usuario> uu = map.getValue();
			    
			    if(aa.getNombre().equals(eventonombre)) {
			        Iterator<Usuario> iteratorusu = uu.iterator();
			        while(iteratorusu.hasNext()) {
			            Usuario usuario = iteratorusu.next();
			            if(usuario.getNombre().equals(usuarionombre)) {
			                estausuario = true;
			                iteratorusu.remove(); 
			                break;
			            }
			        }
			    }
			    if(estausuario) break;
			}

			if(estausuario) {
			    System.out.println("Usuario borrado\n");
			    menu();
			} else {
			    System.out.println("Usuario no encontrado en el evento\n");
			    menu();
			}
			break;
		default:
			sc.nextLine();
			System.out.println("Opcion no valida\nDeseas volver al menu");
			String opcio = sc.nextLine();
			if (opcio.toLowerCase().equals("si")) {
				System.out.println();
				menu();
			} else {
				System.out.println();
				delete();
			}
		}
	}

	public static void salir() {
		BufferedWriter bwus = null;
		BufferedWriter bwor = null;
		BufferedWriter bwev = null;
		BufferedWriter bwin = null;
		try {
			bwus = new BufferedWriter(new FileWriter("usuario.txt"));
			Iterator<Usuario> i = listaUsu.iterator();
			while (i.hasNext()) {
				Usuario a = i.next();
				bwus.write(a.getNombre() + "," + a.getCorreo() + "," + a.getContraseña());
				bwus.newLine();
			}
			bwor = new BufferedWriter(new FileWriter("organizador.txt"));
			Iterator<Organizador> it = listaOrga.iterator();
			while (it.hasNext()) {
				Organizador a = it.next();
				bwor.write(a.getNombre() + "," + a.getCorreo());
				bwor.newLine();
			}
			bwev = new BufferedWriter(new FileWriter("evento.txt"));
			Iterator<Evento> ite = listaEven.iterator();
			while (ite.hasNext()) {
				Evento a = ite.next();
				bwev.write(a.getNombre() + "," + a.getFecha() + "," + String.valueOf(a.getDuracion_min()) + ","
						+ a.getLugar() + "," + a.getOr().toString() + "," + a.getCa());
				bwev.newLine();
			}
			bwin = new BufferedWriter(new FileWriter("incripcion.txt"));
			Iterator<Map.Entry<Evento, ArrayList<Usuario>>> iterator = incriscion.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<Evento, ArrayList<Usuario>> map = iterator.next();
				Evento a = map.getKey();
				ArrayList<Usuario> b = map.getValue();
				bwin.write(a.getNombre() + "," + a.getFecha() + "," + String.valueOf(a.getDuracion_min()) + ","
						+ a.getLugar() + "," + a.getOr().toString() + "," + a.getCa() + "[");
				Iterator<Usuario> bb = b.iterator();
				while (bb.hasNext()) {
					Usuario u = bb.next();
					bwin.write(u.getNombre() + "," + u.getCorreo() + "," + u.getContraseña() + "-");
				}
				bwin.newLine();
			}

			System.out.println("\nEscritura finalizada\nCierre del programa");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				bwus.close();
				bwev.close();
				bwor.close();
				bwin.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void cargar() {
		BufferedReader brus = null;
		BufferedWriter crear = null;
		BufferedReader bror = null;
		BufferedReader brev = null;
		BufferedReader brin = null;
		try {
			crear = new BufferedWriter(new FileWriter("usuario.txt", true));
			brus = new BufferedReader(new FileReader("usuario.txt"));
			String linea;
			while ((linea = brus.readLine()) != null) {
				String[] datos = linea.split(",");
				Usuario u = new Usuario(datos[0], datos[1], datos[2]);
				listaUsu.add(u);
			}

			crear = new BufferedWriter(new FileWriter("organizador.txt", true));
			bror = new BufferedReader(new FileReader("organizador.txt"));
			while ((linea = bror.readLine()) != null) {
				String[] datos = linea.split(",");
				Organizador o = new Organizador(datos[0], datos[1]);
				listaOrga.add(o);
			}

			crear = new BufferedWriter(new FileWriter("evento.txt", true));
			brev = new BufferedReader(new FileReader("evento.txt"));
			while ((linea = brev.readLine()) != null) {
				String[] datos = linea.split(",");
				String nombre = datos[0];
				String fecha = datos[1];
				int duracion = Integer.parseInt(datos[2]);
				String lugar = datos[3];
				String organizadorInfo = datos[4];
				String cate = datos[5];

				String[] orgData = organizadorInfo.split("/");
				Organizador orga = new Organizador(orgData[0], orgData[1]);

				Categoria cat = Categoria.valueOf(cate);
				Evento e = new Evento(nombre, fecha, duracion, lugar, orga, cat);
				listaEven.add(e);
			}

			crear = new BufferedWriter(new FileWriter("incripcion.txt", true));
			brin = new BufferedReader(new FileReader("incripcion.txt"));
			while ((linea = brin.readLine()) != null) {
				String[] datos = linea.split("\\[");

				String[] daeve = datos[0].split(",");
				String nombre = daeve[0];
				String fecha = daeve[1];
				int duracion = Integer.parseInt(daeve[2]);
				String lugar = daeve[3];
				String organizadorInfo = daeve[4];
				String cate = daeve[5];
				String[] orgData = organizadorInfo.split("/");
				Organizador orga = new Organizador(orgData[0], orgData[1]);
				Categoria cat = Categoria.valueOf(cate);
				Evento e = new Evento(nombre, fecha, duracion, lugar, orga, cat);

				String[] dausu = datos[1].split("-");
				ArrayList<Usuario> si = new ArrayList<Usuario>();
				for (int i = 0; i < dausu.length; i++) {
					String[] usuusu = dausu[i].split(",");
					Usuario susu = new Usuario(usuusu[0], usuusu[1], usuusu[2]);
					si.add(susu);
				}
				incriscion.put(e, si);
			}

			System.out.println("Lectura finalizada\n");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				crear.close();
				brus.close();
				bror.close();
				brev.close();
				brin.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
