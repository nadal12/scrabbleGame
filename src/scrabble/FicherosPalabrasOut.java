/*
 * CLASE FicheroPalabrasOut: AGLUTINA LAS DECLARACIONES Y FUNCIONALIDADES PARA
 * LLEVAR A CABO LA GESTIÓN DEL FICHERO DE PRODUCTOS EN MODALIDAD DE SALIDA
 */
package scrabble;



import java.io.*;

public class FicherosPalabrasOut {
    private BufferedWriter objetoOut =null;
    private static final char ESPACIO=' ';

    
   public FicherosPalabrasOut(String nom) throws Exception {
       objetoOut = new BufferedWriter(new FileWriter(nom));
    }
    
    public void escritura(Palabra pal) throws Exception {
        objetoOut.write(pal.toString());
    }
    
    public void saltoLinea() throws Exception {
        objetoOut.newLine();
    }
    
    public void espacio() throws Exception {
        objetoOut.write(ESPACIO);
    }
    
    public void cierre() throws Exception {
        objetoOut.close();
    }
}
