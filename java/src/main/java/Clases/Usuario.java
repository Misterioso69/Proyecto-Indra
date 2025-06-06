package Clases;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import Funciones.Menu;

public class Usuario {
	private String nombre;
	private String correo;
	private String contraseña;
	public Usuario(String nombre, String correo, String contraseña) {
		super();
		this.nombre = nombre;
		this.correo = correo;
		this.contraseña = contraseña;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	@Override
	public String toString() {
		return " [nombre=" + nombre + ", correo=" + correo + ", contraseña=" + contraseña + "]";
	}
	
	public static Usuario crear_Usuario(ArrayList<Usuario>listaUsu) {
		Scanner sc = new Scanner(System.in);
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
		Menu.menu();
		return a;
	}
	
	public static void incribirse(ArrayList<Usuario>listaUsu, ArrayList<Evento>listaEven , Map<Evento, ArrayList<Usuario>> incriscion) {
		Scanner sc = new Scanner(System.in);
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
			Menu.menu();
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
			Menu.menu();
			return;
		}
		ArrayList<Usuario> lus = incriscion.getOrDefault(eve, new ArrayList<>());
		if (lus.contains(usu)) {
			System.out.println("\nEste usuario ya está inscrito en el evento\n");
			Menu.menu();
			return;
		}

		lus.add(usu);
		incriscion.put(eve, lus);

		System.out.println("\nIncriscion completada\n");
		Menu.menu();
	}
	
	public static void borrarUsuario (ArrayList<Usuario>listaUsu) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Usuarios:\n");
		Iterator<Usuario> us = listaUsu.iterator();
		while (us.hasNext()) {
			Usuario a = us.next();
			System.out.print(a);
		}
		sc.nextLine();
		System.out.println("\n¿Que Usuario quires borrar?");
		String nom = sc.nextLine();
		boolean esta = false;
		Usuario u = null;
		Iterator<Usuario> usu = listaUsu.iterator();
		while (usu.hasNext()) {
			Usuario a = usu.next();
			if (a.getNombre().equals(nom)) {
				esta = true;
				u = a;
			}
		}
		if (esta) {
			listaUsu.remove(u);
			System.out.println("Usuario borrado\n");
			Menu.menu();
		} else {
			System.out.println("El usuario no existe\n");
			Menu.menu();
		}
	}
	
	public static void borrarInscripcion ( Map<Evento, ArrayList<Usuario>> incriscion) {
		Scanner sc = new Scanner(System.in);
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

			if (aa.getNombre().equals(eventonombre)) {
				Iterator<Usuario> iteratorusu = uu.iterator();
				while (iteratorusu.hasNext()) {
					Usuario usuario = iteratorusu.next();
					if (usuario.getNombre().equals(usuarionombre)) {
						estausuario = true;
						iteratorusu.remove();
						break;
					}
				}
			}
			if (estausuario)
				break;
		}

		if (estausuario) {
			System.out.println("Usuario borrado\n");
			Menu.menu();
		} else {
			System.out.println("Usuario no encontrado en el evento\n");
			Menu.menu();
		}
	}
}
