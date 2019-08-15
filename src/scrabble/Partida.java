/*
Clase Partida.
 */
package scrabble;

public class Partida {

    //Declaraciones
    private static String nombreJugador;
    private static int puntuacionMaxima; //Puntuación objetivo.
    private static int numeroDiccionario; //Diccionario elegido. 
    private static int turnoInicial;
    private static final String[] IDIOMA = {" ", "Castellano", "Catalán", "Inglés"};

    //Método constructor.
    public Partida() {

        nombreJugador = "";
        puntuacionMaxima = 0;
        numeroDiccionario = -1;
        turnoInicial = -1;

    }

    //Método que recogerá toda la configuración que escoja el usuario. 
    public static void configuracionInicial() {

        System.out.println("            Configuraciones iniciales\n");

        System.out.print("Introduce el nombre del jugador: ");
        nombreJugador = LT.readLine();
        System.out.println();
        System.out.print("Introduce la puntuación máxima de la partida (Mínimo 1): ");

        //Lectura de la puntuación objetivo. 
        puntuacionMaxima = LT.readInt();
        System.out.println();
        System.out.println("¿Qué diccionario de palabras quieres usar? (1-3).");
        System.out.println("            [1] Castellano.");
        System.out.println("            [2] Catalán.");
        System.out.println("            [3] Inglés.");
        System.out.print("Respuesta: ");

        //Lectura del diccionario escogido. 
        numeroDiccionario = LT.readInt();

        //Salto de línea. 
        System.out.println();

    }

    //Visualización de un breve resumen de la configuración elegida. 
    public static void resumenConfiguracionInicial() {
        System.out.println("---------------------Resumen---------------------\n");
        System.out.print("El nombre del jugador es: " + nombreJugador + "\n");
        System.out.println("La partida se jugará a " + puntuacionMaxima + " puntos.");
        System.out.print("El idioma del diccionario seleccionado es: " + getIdioma() + ".\n");
    }

    //Devuelve el nombre del diccionario elegido. 
    private static String getIdioma() {

        return IDIOMA[numeroDiccionario];

    }

    //Sorteo del turno inicial aleatoriamente. Si es = 0 empieza el jugador, si es 
    // = 1 empieza el ordenador. 
    public static int sorteoTurnoInicial() {

        //Se sorteará un valor de entre 0 y 1 y se almacenará
        turnoInicial = (int) (Math.random() * 2);

        if (turnoInicial == 0) {

            System.out.print("Empezará jugando " + nombreJugador + "\n");
            System.out.println("-------------------------------------------------\n");

        } else {

            System.out.print("Empezará jugando el ordenador.\n");
            System.out.println("-------------------------------------------------\n");
        }

        return turnoInicial;
    }

    //Devuelve el valor del atributo de puntuaciónMaxima
    public static int getPuntuacionMaxima() {

        return puntuacionMaxima;

    }

    //Devuelve el valor del turnoInicial sorteado. 
    public static int getTurnoInicial() {

        return turnoInicial;

    }

    //Devuelve el nombre del jugador. 
    public static String getNombreJugador() {

        return nombreJugador;
    }
    //Devuelve el número de diccionario. 

    public static int getDiccionario() {

        return numeroDiccionario;

    }

}
