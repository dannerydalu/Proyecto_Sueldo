
package biblioteca;
import java.io.*;

/*
 * Clase que facilita el trabajo con archivos de texto. Un archivo texto 
 * esta conformado por registros y los registros estan organizados en campos. 
 * Esta clase maneja archivos con registros de longitud variable.
 *
 * @version		1.10 - 20 Nov. 2005.
 * @author		Ing. Juan José Flores Cueto.
 * 
 * 
 * Para cualquier sugerencia o mejora de esta clase puede comunicarse a la 
 * siguiente dirección de correo: jflores@usmp.edu.pe.
 * 
 * El uso de esta clase es de caracter académico y es libre. El autor no se 
 * responsabiliza por su uso en aplicaciones comerciales.  
 * 
 */
public class ArchivoRegistroVariable {
    private RandomAccessFile archivo;
    private String campos[];
    private String registro;
    /* La constante marca establece el caracter de separación entre los datos
     * almacenados en el archivo de texto.
     */
    private final String marca = "%";

    public ArchivoRegistroVariable() {
    	archivo = null;
    	campos = null;
    	registro = "";
    }

    /**
     * Método utilizado para abrir un archivo texto.
     * @param ubicacion : Especifica el directorio donde se ubica el archivo texto.
     * @param nombre    : Especifica el nombre del archivo texto.
     * @param modoAcceso: Especifica el modo en que se abrira el archivo texto (w, r, rw).
     *      
     */
    public void abrirArchivo(String ubicacion, String nombre, String modoAcceso) {
        try {
            archivo = new RandomAccessFile(ubicacion + nombre, modoAcceso);
        } catch(IOException e) {
            System.out.println("Error al abrir archivo");
            System.exit(1);
        }
    }

    /**
     * Método utilizado para grabar datos en un archivo de texto.
     * @param campos[]: Contiene los datos de tipo String que se grabarán en el archivo texto.
     *      
     */    
    public void grabarDatos(String campos[]) {
    	setCampos(campos);
    	uneCampos();
    	posicionaPunteroFinal();
    	grabarRegistro();
    }
    private void setCampos(String campos[]) {
    	this.campos = campos;
    }
    private void uneCampos() {
        registro = "";
        for(int i = 0; i < campos.length; i++) {
            registro = registro + campos[i].trim() + marca;
        }
    }
    private void posicionaPunteroFinal() {
        try {
            archivo.seek((int)(archivo.length()));
        } catch(IOException e) {
            System.out.println("Error en posicionar puntero al final del archivo");
        }    	
    }    
    private void grabarRegistro() {
        try {
            archivo.writeBytes(registro);
        } catch(IOException e) {
            System.out.println("Error al grabar en el archivo");
        }
    }

    /**
     * Método utilizado para leer los datos de un archivo de texto.
     * @param  numRegistro: Especifica el número del registro cuyos datos se leerán del archivo texto.
     * @return un String[]: Contiene los datos de tipo TEXTO que se han recuperado del archivo texto.
     *      
     */    
    public String[] leerDatos(int numRegistro) {
    	posicionaPunteroInicio();
    	leerRegistro();
    	int posicion = posicionaPunteroRegistro(numRegistro);
    	separaCampos(posicion);
    	return campos;
    }
    private void posicionaPunteroInicio() {
        try {
            archivo.seek(0);
        } catch(IOException e) {
            System.out.println("Error en posicionar puntero al inicio del archivo");
        }    	
    }    
    private void leerRegistro() {
    	try{
        	byte bytes[]=new byte[(int)(archivo.length())];
    		archivo.readFully(bytes);
    		registro = new String(bytes); 		
        } catch(IOException e) {
            System.out.println("Error al leer del archivo");
        }
    }
    private int posicionaPunteroRegistro(int numRegistro) {
        int regActual = 0, posInicial = 0, encontrado=0;
    	while (regActual < numRegistro) {
        	posInicial = registro.indexOf(marca, posInicial) + 1;
        	encontrado++;        	
        	if (encontrado == campos.length) {
        		regActual++;
        		encontrado = 0;
        	}
        }
    	return posInicial;
    }
    private void separaCampos(int posInicial) {
        int posFinal;
      	for(int i = 0; i < campos.length; i++) {
      		posFinal = registro.indexOf(marca, posInicial);
      		campos[i] = registro.substring(posInicial,posFinal);
      		posInicial = posFinal + 1;
       	}
    }
    
    /**
     * Método utilizado para calcular el total de registros almacenados 
     * en un archivo de texto.
     * @return Un int:  Especifica el total de registros que tiene un archivo texto.
     * 
     */    
    public int totalRegistros() {
    	posicionaPunteroInicio();
    	leerRegistro();
    	return cuentaRegistros();
    }
    private int cuentaRegistros() {
        int totalMarcas = 0, posInicial = 0;
    	while (posInicial < registro.length()) {
        	posInicial = registro.indexOf(marca, posInicial) + 1;
        	totalMarcas++;        	
        }    	
    	return totalMarcas/campos.length;
    }    

    /**
     * Método utilizado para verificar el final del archivo texto.
     * @return Un boolean:  true si el puntero esta en el final del archivo texto.
     * 
     */
    public boolean eof() {
        boolean rpta = true;
        try {
            rpta = archivo.getFilePointer() == archivo.length();
        } catch(IOException e) {
            System.out.println("Error en fin de archivo");
        }
        return rpta;
    }

    /**
     * Método utilizado para cerrar un archivo texto.
     *      
     */
    public void cerrarArchivo() {
        try {
            archivo.close();
        } catch(IOException e) {
            System.out.print("Error al cerrar archivo");
            System.exit(1);
        }
    } 
}
