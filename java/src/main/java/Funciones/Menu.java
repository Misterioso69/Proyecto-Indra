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
		System.out.println("1.Usario\n2.Organizador\n3.Listar Todo\n4.Salir");
		System.out.println("----------------------------");
		System.out.print("Elige una opcion:");
		int a = sc.nextInt();
		switch (a) {
		case 1:
			System.out.println(
					"\n1.Crear Usuario\n2.Inscribirse a un evento\n3.Borrar Usuario\n4.Borrar Inscripcion\n5.Volver Menu");
			System.out.println("----------------------------");
			System.out.print("Elige una opcion:");
			int opcionusu = sc.nextInt();
			switch (opcionusu) {
			case 1:
				Usuario.crear_Usuario(listaUsu);
			case 2:
				Usuario.incribirse(listaUsu, listaEven, incriscion);
			case 3:
				Usuario.borrarUsuario(listaUsu);
			case 4:
				Usuario.borrarInscripcion(incriscion);
			case 5:
				System.out.println();
				menu();
			default:
				System.out.println("\nOpcion no valida\n ");
				menu();
			}
			break;
		case 2:
			System.out.println(
					"\n1.Crear Organizador\n2.Crear Evento\n3.Borrar Organizador\n4.Borrar Evento\n5.Volver Menu");
			System.out.println("----------------------------");
			System.out.print("Elige una opcion:");
			int opcionor = sc.nextInt();
			switch (opcionor) {
			case 1:
				Organizador.crear_Organizador(listaOrga);
			case 2:
				Organizador.crear_Evento(listaOrga, listaEven);
			case 3:
				Organizador.borrarOrganizador(listaOrga);
			case 4:
				Organizador.borrarEvento(listaEven);
			case 5:
				System.out.println();
				menu();
			default:
				System.out.println("\nOpcion no valida\n ");
				menu();
			}
			break;
		case 3:
			listar_todo();
			break;
		case 4:
			salir();
			break;
		default:
			System.out.println("\nOpcion no valida\n ");
			menu();
		}
	}

	public static void listar_todo() {
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