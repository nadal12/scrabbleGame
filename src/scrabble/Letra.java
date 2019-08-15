/*
Clase Letra.
 */
package scrabble;

import java.io.FileReader;

public class Letra {

    //Declaraciones
    //Atributos
    private int letra;
    private int puntuacionLetra;
    private final static int FINAL_FICHERO = -1;
    private final static int SEPARADOR_LETRAS = 58; //Separador de letras dentro del fichero ":".
    private final static int CR = 13; //Card return
    private final static int NUMERO_LETRAS_PANEL = 14; //Número de letras que serán sorteadas [9-17].
    private static Letra[] letrasFinales = new Letra[NUMERO_LETRAS_PANEL]; //Almacenará las letras sorteadas

//Método constructor
    public Letra() {

        int letra = -1;
        int puntuacionLetra = 0;

    }

//Devuelve el número de líneas que tiene el fichero de letras. 
    private static int contarLineasFichero() throws Exception {

        int contador = 0;
        int codigo;
        int saltoLinea = '\n';

        FileReader lecturaFichero = new FileReader("lletres.txt");
        while ((codigo = lecturaFichero.read()) != FINAL_FICHERO) {
            if (codigo == saltoLinea) {
                contador++;
            }
        }
        lecturaFichero.close();

        //Volvemos a aumentar el contador porque se supone que la última linea no tiene un salto de linea
        contador++;
        return contador;
    }

//Devuelve un array de objetos Letra con todas las letras y su puntuación.
    public static Letra[] recogerLetrasFichero() throws Exception {
        //Declaraciones
        Letra[] letras = new Letra[contarLineasFichero()];
        FileReader lecturaFichero = new FileReader("lletres.txt");
        int indice = 0;
        int codigo;

        //Realizamos la lectura del primer byte.
        codigo = lecturaFichero.read();

        //Mientras indice sea menor que el tamaño del array "letras" (líneas del fichero). 
        while (indice < letras.length) {

            //Instanciamos la posición del array
            letras[indice] = new Letra();

            while (codigo == SEPARADOR_LETRAS) {

                codigo = lecturaFichero.read();

            }

            //Si el byte leído no es igual al CardReturn entraremos en la condicional. 
            if (codigo != CR) {

                //Metemos el valor del byte dentro del array de letras.         
                letras[indice].letra = codigo;

                //Leemos 2 bytes más
                codigo = lecturaFichero.read();
                codigo = lecturaFichero.read();

                //El siguiente byte será la puntuación de la letra. 
                letras[indice].puntuacionLetra = codigo;
                indice++;
            }

            //Leemos 3 bytes más.
            codigo = lecturaFichero.read();
            codigo = lecturaFichero.read();
            codigo = lecturaFichero.read();
        }

        //Cierre del fichero.
        lecturaFichero.close();

        //Devolución del array de Letras. 
        return letras;
    }

//Método que sortea las letras y las almacena dentro de un array. 
    public static void getLetrasRandom() throws Exception {

        //Declaraciones
//Contador que llegará hasta el número de letras del panel definido en la constante inicial. 
        int contador = 0;
        int letraAleatoria;
        Letra[] letrasRandom = new Letra[NUMERO_LETRAS_PANEL];

        while (contador < NUMERO_LETRAS_PANEL) {

            //Generar un valor aleatorio de entre 0 y el tamaño del array de letras del fichero. 
            letraAleatoria = (int) (Math.random() * recogerLetrasFichero().length);

            //Instanciación de la componente del array
            letrasRandom[contador] = new Letra();

            //Almacenamos la letra sorteada dentro del atributo letra del array de letrasRandom. 
            letrasRandom[contador].letra = recogerLetrasFichero()[letraAleatoria].letra;

            //A continuación almacenamos su valor en la misma componente pero en el atributo valor. 
            letrasRandom[contador].puntuacionLetra = recogerLetrasFichero()[letraAleatoria].puntuacionLetra;

            //Incrementamos el contador.
            contador++;
        }

        //Una vez tengamos todas las letras sorteadas las almacenamos dentro de otro array
        //para mantenerlas. 
        for (int i = 0; i < letrasRandom.length; i++) {

            //Instanciamos la componente.
            letrasFinales[i] = new Letra();

            //almacenamos cada componente.
            letrasFinales[i] = letrasRandom[i];

        }

    }

//Devuelve el valor del atributo letra.
    public int getLetra() {

        return letra;

    }

//Devuelve el valor del atributo puntuacionLetra.
    public int getValor() {

        return puntuacionLetra;

    }

//Devuelve el número de letras que se mostrarán en el panel del sorteo.
    public static int getNumeroLetrasPanel() {

        return NUMERO_LETRAS_PANEL;

    }

//Devuelve el array de letras que han sido sorteadas. 
    public static Letra[] getLetrasFinales() {

        return letrasFinales;

    }

}
