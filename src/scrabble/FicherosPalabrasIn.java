/*
 * CLASE FicheroPalabrasIn: AGLUTINA LAS DECLARACIONES Y FUNCIONALIDADES PARA
 * LLEVAR A CABO LA GESTIÓN DE FICHERO A NIVEL DE PALABRAS EN MODALIDAD DE ENTRADA
 */
package scrabble;

import java.io.*;

public class FicherosPalabrasIn {
    private BufferedReader objetoIn =null;
    private int entrada=' ';
    private static final int FINAL_FICHERO=-1;
    private static final char ESPACIO=' ';
    private static final char SALTO_LINEA='\n';
    private static final char CR='\r';

    
   public FicherosPalabrasIn(String nom) throws Exception {
                objetoIn = new BufferedReader(new FileReader(nom));
    }
    
    public Palabra lectura() throws Exception {
        String palString="";
        int indice=0;
        while ((entrada !=FINAL_FICHERO)&&(entrada!=ESPACIO) &&
                (entrada!='.')&&(entrada!=SALTO_LINEA)&&(entrada!=CR)&&
                (!caracterSeparacion((char) entrada))) {
                palString=palString+(char)entrada;
                indice++;             
                entrada= objetoIn.read();
        }
        Palabra pal=new Palabra(palString);
        return pal;
    }
     
    public boolean hayPalabras() throws Exception {
        buscarPalabra();
        return (entrada!=FINAL_FICHERO);
    }
    public void buscarPalabra() throws Exception {
            while ((entrada==ESPACIO)||(entrada==CR)||
                   (entrada==SALTO_LINEA)||(caracterSeparacion((char) entrada))) {
                entrada= objetoIn.read();
            }
    }   
    
        
    public boolean caracterSeparacion(char car) {
        return ((car=='.')||(car==':')||(car==',')||(car==';')||(car=='-'));
    }
    public void cierre() throws Exception {
        objetoIn.close();
    }
    
}

