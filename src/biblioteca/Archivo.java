

package biblioteca;
import java.io.*;

public class Archivo
{

    public Archivo(int nro, int a[])
    {
        nroCampos = nro;
        campo = new String[nro];
        longCampo = a;
        longReg = calculaLongReg();
        b = new byte[longReg];
    }

    public void abrirArchivo(String ubi, String nomb, String mod)
    {
        try
        {
            file = new RandomAccessFile(ubi + nomb, mod);
        }
        catch(IOException _ex)
        {
            System.out.println("Error al abrir archivo");
        }
    }

    public void apertura()
    {
        int n = totalRegs();
        apuntar(n + 1);
    }

    public void apuntar(int p)
    {
        try
        {
            file.seek((p - 1) * longReg);
        }
        catch(IOException _ex)
        {
            System.out.println("Error en el puntero");
        }
    }

    public int calculaLongReg()
    {
        int lon = 0;
        for(int i = 0; i < nroCampos; i++)
            lon += longCampo[i];

        return lon;
    }

    public void cerrarArchivo()
    {
        try
        {
            file.close();
        }
        catch(IOException _ex)
        {
            System.out.print("Error al cerrar archivo");
            System.exit(1);
        }
    }

    public String completaEspacios(String dato, int lonCamp)
    {
        int falta = lonCamp - dato.length();
        for(int i = 1; i <= falta; i++)
            dato = dato + " ";

        return dato;
    }

    public boolean eof()
    {
        boolean rpta = true;
        try
        {
            rpta = file.getFilePointer() == file.length();
        }
        catch(IOException _ex)
        {
            System.out.println("Error en m\351todo EOF");
        }
        return rpta;
    }

    public void grabaReg()
    {
        try
        {
            file.writeBytes(registro);
        }
        catch(IOException _ex)
        {
            System.out.println("Error al grabar");
        }
    }

    public String leeReg()
    {
        try
        {
            file.readFully(b);
            registro = new String(b);
        }
        catch(EOFException _ex)
        {
            registro = "Fin";
        }
        catch(IOException _ex)
        {
            System.out.println("Error al leer");
        }
        return registro;
    }

    public void separaCampos()
    {
        int col = 0;
        for(int i = 0; i < nroCampos; i++)
        {
            campo[i] = registro.substring(col, col + longCampo[i]);
            col += longCampo[i];
        }

    }

    public int totalRegs()
    {
        try
        {
            return (int)(file.length() / (long)longReg);
        }
        catch(IOException _ex)
        {
            System.out.println("Error al contar registros");
        }
        return 0;
    }

    public void uneCampos()
    {
        registro = "";
        for(int i = 0; i < nroCampos; i++)
        {
            campo[i] = completaEspacios(campo[i], longCampo[i]);
            registro = registro + campo[i];
        }

    }

    RandomAccessFile file;
    byte b[];
    public String registro;
    public String campo[];
    int nroCampos;
    int longReg;
    int longCampo[];
}
