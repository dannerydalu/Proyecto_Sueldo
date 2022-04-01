package biblioteca;

/*
 * @(#)Lectura.java	1.2 09 Agosto 2003
 *
 * Copyright 2003 by USMP,
 * Av. Pascal Pringles 1250, Sta. Patricia, La Molina, Lima, Per�.
 * Todos los derechos reservados.
 * 
 * Este software es informaci�n confidencial y propietaria de la
 * USMP.
 */

import java.io.*;
/**
 * La clase <code>Lectura</code> permite capturar valores por teclado desde una
 * Ventana de DOS o consola si utiliza un IDE (Entorno de Desarrollo Integrado).
 *
 * @version		1.2 09 Agosto 2003
 * @author		Gisella Guzm�n Mariluz
 */
public class Lectura {
/**
 * Retorna el caracter capturado desde el teclado. Si no se ingres� un
 * caracter, solicita el ingreso nuevamente.
 *
 * @return un caracter ingresado por teclado.
 */
public static char leerChar() {
	char[] c = leerString().toCharArray();
	if (c.length == 1)
		return c[0];
	else {
		System.out.print("ERROR...\nIngrese un caracter: ");
		return leerChar();
	}
}
/**
 * Retorna un valor double capturado desde el teclado. Si no ingres� un
 * valor num�rico, solicita el ingreso nuevamente.
 *
 * @return el valor real ingresado por teclado.
 */
public static double leerDouble() {
	try {
		return Double.valueOf(leerString()).doubleValue();
	} catch (NumberFormatException e) {
		System.out.print("ERROR...\nIngrese un n�mero real: ");
		return leerDouble();
	}
}
/**
 * Retorna un valor float capturado desde el teclado. Si no ingres� un
 * valor num�rico, solicita el ingreso nuevamente.
 *
 * @return el valor real ingresado por teclado.
 */
public static float leerFloat() {
	try {
		return Float.valueOf(leerString()).floatValue();
	} catch (NumberFormatException e) {
		System.out.print("ERROR...\nIngrese un n�mero real: ");
		return leerFloat();
	}
}
/**
 * Retorna un valor int capturado desde el teclado. Si no ingres� un
 * valor num�rico, solicita el ingreso nuevamente.
 *
 * @return el valor entero ingresado por teclado.
 */
public static int leerInt() {
	try {
		return Integer.parseInt(leerString());
	} catch (NumberFormatException e) {
		System.out.print("ERROR...\nIngrese un n�mero entero: ");
		return leerInt();
	}
}
/**
 * Retorna un valor long capturado desde el teclado. Si no ingres� un
 * valor num�rico, solicita el ingreso nuevamente.
 *
 * @return el valor entero ingresado por teclado.
 */
public static long leerLong() {
	try {
		return Long.valueOf(leerString()).longValue();
	} catch (NumberFormatException e) {
		System.out.print("ERROR...\nIngrese un n�mero entero: ");
		return leerLong();
	}
}
/**
 * Retorna un valor short capturado desde el teclado. Si no ingres� un
 * valor num�rico, solicita el ingreso nuevamente.
 *
 * @return el valor entero ingresado por teclado.
 */
public static short leerShort() {
	try {
		return Short.valueOf(leerString()).shortValue();
	} catch (NumberFormatException e) {
		System.out.print("ERROR...\nIngrese un n�mero entero: ");
		return leerShort();
	}
}
/**
 * Retorna una secuencia de caracteres capturada desde el teclado.
 *
 * @return la cadena ingresada por teclado.
 */
public static String leerString() {
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	String cadena = "";
	try {
		cadena = in.readLine();
	} catch (IOException e) {
		System.out.println(e);
	}
	return cadena;
}
}

