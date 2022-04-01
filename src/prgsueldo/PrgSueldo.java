//nombre del paquete
package prgsueldo;
import biblioteca.LE;
public class PrgSueldo {//Declaro los arreglos
    long codigo[];
    String datostra[][];
    String sexo[];
    double sueldo[];
    int total;
    public static void main(String[] args) {
        PrgSueldo x=new PrgSueldo();
        x.menu();
    }
    public PrgSueldo(){ //Método Constructor definimos los tamaños de los arreglos
    total=0;
    codigo=new long[5];
    datostra=new String[5][2];
    sexo=new String[5];
    sueldo=new double[5];
    
}
    public void menu(){
        String texto="----MENU----\n"+
                "[1] Ingrese  datos del trabajador\n"+
                "[2] Mostrar datos ingresados\n"+
                "[3] Mostrar ala categoria que pertenecen\n"+
                "[4] Mostrar el mayor sueldo del trabajador\n"+
                "[5] Mostrar el menor sueldo del trabajador\n"+
                "[6] Ordenar datos por codigo descendente\n"+
                "[7] Ordenar los datos por nombre de manera alfabeticamente.\n"+
                "[8] Modificar datos por el codigo\n"+
                "[9] Eliminar datos \n"+
                "[10] Finalizar aplicacion \n"+
                ".................\n"+
                "selecione su operacion";
        int opc=0;
        boolean sw=false;//Deshabilitamos los botones desde el 2 hasta el 8
        do{
           opc=LE.leerInt(texto);
            switch(opc){
                case 1:
                    ingresar();
                    sw=true;
                    break;
                case 2:
                    if(sw){
                        mostrardatos();
                    }else{
                        LE.mostrarError("error ingrese datos");
                    }
                    break;
                case 3:
                    if(sw){
                        mostrarcat();
                    }else{
                        LE.mostrarError("Primero ingrese datos de los trabajadores");
                    }
                    break;
                case 4:
                    if(sw){
                        mostrarmayorsueldo();
                    }else{
                        LE.mostrarError("Primero ingrese los datos");
                    }
                    break;
                 case 5:
                    if(sw){
                        mostrarmenorsueldo();
                    }else{
                        LE.mostrarError("Primero ingrese los datos");
                    }
                    break;
                case 6:
                    if(sw){
                        ordenardatos();
                    }else{
                        LE.mostrarError("Primero ingrese los datos");
                    }
                    break;
                case 7:
                    if(sw){
                        ordenarNombres();
                    }else{
                        LE.mostrarError("Primero ingrese los datos");
                    }
                    break;
                    
                case 8:
                    if(sw){
                        modificardatos();
                    }else{
                        LE.mostrarError("Primero ingrese los datos");
                    }
                    break;
                case 9:
                    if(sw){
                        eliminardatos();
                    }else{
                        LE.mostrarError("Primero ingrese los datos");
                    }
                    break;
                case 10:
                    break;
                default:
                    LE.mostrarError("Opción no valida ....REINTENTE");
            }
        }while(opc!=10);
        }

    public void ingresar(){
        int opc;
        int pos;
        long cod;
        String sex="";
        do{
            if(codigo.length==total){//Si el código se llena por consecuente se va aumentando
                aumentar();
            }
            cod=LE.leerLong("Ingrese codigo");
            pos=buscar(cod);
            if(pos==-1){
            codigo[total]=cod;
            datostra[total][0]=LE.leerString("ingrese el nombre del trabajador");
            datostra[total][1]=LE.leerString("Ingrese apellido");
            sex =LE.leerString("Ingrese el sexo: F si es femenino y M si es masculino");
                switch(sex){// Restringimos en el sexo que solo pueden poner F o M 
                    case "F":
                        sexo[total]="Femenino";
                        break;
                    case "M":
                        sexo[total]="Masculino";
                        break;
                    default:
                        LE.mostrarError("Opcion invalida!");
                        break;
                }
            sueldo[total]=LE.leerDouble("Ingrese sueldo del trabajador");
            total++;
        }else{
            LE.mostrarInformacion("Codigo ya existe");
            
        }
        opc=LE.mostrarPreguntaOpcion2("¿Desea continuar?");
    }while (opc==0);
    
}
public void aumentar(){//creamos vectores más grandes
        long cod[]=new long[codigo.length+5];
        String datostr[][]=new String[codigo.length+5][2];
        String sex[]=new String[codigo.length+5];
        double sue[]= new double[codigo.length+5];
        for (int i = 0; i < total; i++) {//Realizamos una copia de los datos
            cod[i]=codigo[i];
            datostr[i]=datostra[i];
            sex[i]=sexo[i];
            sue[i]=sueldo[i];
        }//Destruimos los vectores originales y renombramos los nuevos vectores
        codigo=cod;
        datostra=datostr;
        sexo=sex;
        sueldo=sue;
}
public int buscar(long codig){
        for (int i = 0; i < total; i++) {
            if(codigo[i]==codig){
                return i;
            }
        }
        return -1;
}
public void mostrardatos(){
     String dat="";//Creamos la variable para acumular la siguiente información
        for (int i = 0; i < total; i++) {
            dat+=(i+1)+". "+ "Su codigo es: "+codigo[i]+"\n "+
                    "su nombre es: "+ datostra[i][0]+"\n "+
                    "Su apellido es: "+datostra[i][1]+"\n "+
                    "Su sexo es: "+sexo[i]+"\n "+
                    "Su sueldo es:"+sueldo[i]+"\n";
        }
        LE.mostrarInformacion(dat);
}
public void mostrarcat(){//Desarrollamos la logica para ver de acuerdo al sueldo a que clase pertenece
    String dat="";
    String cat="";
    for (int i = 0; i < total; i++) {
        if(sueldo[i]<=930){
            cat="clase baja";
        }
         
        if(sueldo[i]>930 && sueldo[i]<=2400){
            cat="clase media";
        }
         
        if(sueldo[i]>=2400){
            cat="clase alta";
        }
        dat+=(i+1)+".-"+"la categoria del trabajador de nombre: "+
                datostra[i][0]+" "+datostra[i][1]+"es:"+cat+"\n";
    }
    LE.mostrarInformacion(dat);
}
public void mostrarmayorsueldo(){
    double mayor=sueldo[0];
    for (int i = 0; i < total; i++) {
        if(mayor<sueldo[i]){
            mayor=sueldo[i];
        }
        
    }
    LE.mostrarInformacion("El mayor sueldo ingresado es: "+mayor);
}
public void mostrarmenorsueldo(){
    double menor=sueldo[0];
    for (int i = 0; i < total; i++) {
        if(menor>sueldo[i]){
            menor=sueldo[i];
        }
        
    }
    LE.mostrarInformacion("El menor sueldo ingresado es: "+menor);
}


public void ordenardatos(){
     double sue;
     String datost;
     long cod;
     String sex;
    for (int i = 0; i < total-1; i++) {
        for (int j = i+1; j < total; j++) {
            if(codigo[i]<codigo[j]){
                cod=codigo[j];
                codigo[j]=codigo[i];
                codigo[i]=cod;
                datost=datostra[j][0];
                datostra[j][0]=datostra[i][0];
                datostra[i][0]=datost;
                datost=datostra[j][1];
                datostra[j][1]=datostra[i][1];
                datostra[i][1]=datost;
                sex=sexo[j];
                sexo[j]=sexo[i];
                sexo[i]=sex;
                sue=sueldo[j];
                sueldo[j]=sueldo[i];
                sueldo[i]=sue;
            }
        }
    }
}
public void modificardatos(){
    long codig;
    long cod=LE.leerLong("Ingrese el codigo del trabajador cuyos datos desea modificar");
    int pos1;
    int pos=buscar(cod);
    if(pos==-1){
        LE.mostrarInformacion("El codigo no existe..VERIFIQUE");
    }else{
        int rpta=LE.mostrarPreguntaOpcion2("Desea modificar los datos del trabajador cuyos datos son: \n" +
                datostra[pos][0]+"\n"+
                datostra[pos][1]+"\n"+
                sexo[pos]+"\n"+
                sueldo[pos]+"?");
        if(rpta==0){//Empieza la modificación
            codig=LE.leerLong("Ingrese el nuevo codigo");
            pos1=buscar(codig);
            if(pos1==-1){
                codigo[pos]=codig;
                datostra[pos][0]=LE.leerString("Ingrese nuevo nombre");
                datostra[pos][1]=LE.leerString("Ingrese nuevo apellido");
                sexo[pos]=LE.leerString("Ingrese nuevo sexo del trabajador");
                sueldo[pos]=LE.leerDouble("Ingrese el nuevo sueldo del trabajdor");
            }else{
                LE.mostrarError("Nuevo codigo ya existe");
            }
        }
    }
}
public void ordenarNombres(){
    double sue;
    String datost;
    long cod;
    String sex;
    for (int i = 0; i < total-1; i++) {//Declaramos la estructura de repetición 
        for (int j = i+1; j < total; j++) {
            if(datostra[i][0].compareToIgnoreCase(datostra[j][0])>0){
                cod=codigo[j];
                codigo[j]=codigo[i];
                codigo[i]=cod;
                datost=datostra[j][0];
                datostra[j][0]=datostra[i][0];
                datostra[i][0]=datost;
                datost=datostra[j][1];
                datostra[j][1]=datostra[i][1];
                datostra[i][1]=datost;
                sex=sexo[j];
                sexo[j]=sexo[i];
                sexo[i]=sex;
                sue=sueldo[j];
                sueldo[j]=sueldo[i];
                sueldo[i]=sue;
            }
            
        }
        
    }
}
public void eliminardatos(){
    long cod=LE.leerLong("Ingrese codigo del trabajador cuyos datos deseas eliminar");
    int pos=buscar(cod);
    if(pos==-1){
        LE.mostrarError("El codigo no existe.... VERIFICAR");
    }else{
        int rpta=LE.mostrarPreguntaOpcion2("Esta seguro que desea eliminar los datos del trabajador "+datostra[pos][0]+"?");
        if(rpta==0){
            for (int i = pos; i < total; i++) {
                codigo[i]=codigo[i+1];
                datostra[i][0]=datostra[i+1][0];
                datostra[i][1]=datostra[i+1][1];
                sexo[i]=sexo[i+1];
                sueldo[i]=sueldo[i+1];
            }
            total--;
        }
    }
}
}

