/***************************************************************************
FUNCIONALIDAD: funcionalidades para llevar a cabo la lectura de datos desde 
el teclado
 ***************************************************************************/
package scrabble;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LT {

    private static String secuencia="";
    private static int indice = 0;

    private static char[] readln() {
        String res = "";
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(System.in, "ISO-8859-1"));
            res = br.readLine();
        } catch (IOException e) {
        }
        return res.toCharArray();
    }

    // Lectura secuencia de caracteres finalizada con el caracter '.'
    public static char readCharacterSequence() {
        int elemento = 0;
        if (indice == 0) {
            secuencia = readLine();
        }
        elemento = indice;
        indice = ((indice==(secuencia.length()-1))||
                (secuencia.charAt(indice) == '.'))? 0 : (indice + 1);
        return (secuencia.charAt(elemento));

    }

    // Lectura de n�mero entero.
    public static Integer readInt() {
        Integer res = 0;
        try {
            char[] pal = readln();
            res = Integer.parseInt(new String(pal));
        } catch (NumberFormatException e) {
            res = null;
        }
        return res;
    }

    // Lectura n�mero real.
    public static Double readReal() {
        Double res = 0.0;
        try {
            char[] pal = readln();
            res = Double.parseDouble(new String(pal));
        } catch (NumberFormatException e) {
            res = null;
        }
        return res;
    }

    // Lectura linea de caracteres.
    public static String readLine() {
        String res;
        char[] pal = readln();
        res = new String(pal);
        return res;
    }

    // Lectura de caracter.
    public static Character readCharacter() {
        Character res;
        char[] pal = readln();
        String s = new String(pal);
        if (s.length() == 0) {
            res = null;
        } else {
            res = new Character(s.charAt(0));
        }
        return res;
    }

}