/*
 Clase Palabras.
 */
package scrabble;

public class Palabras {

//Método que devuelve true si la palabra pasada por parámetro existe en el diccionario escogido. 
    public static boolean buscarEnDiccionario(Palabra a) throws Exception {

        //Declaraciones                
        boolean encontrada = false;
        String nombreArchivo = "x"; //Inicializamos con un valor la variable donde irá el nombre de archivo.

        //Dependiendo del diccionario escogido leeremos un fichero u otro. 
        switch (Partida.getDiccionario()) {

            case 1:
                nombreArchivo = "dic.es";
                break;

            case 2:
                nombreArchivo = "dic.ca";
                break;

            case 3:
                nombreArchivo = "dic.en";
                break;
        }

        //Abrimos el diccionario a leer. 
        FicherosPalabrasIn entrada = new FicherosPalabrasIn(nombreArchivo);

        while ((entrada.hayPalabras()) && (encontrada == false)) {

            Palabra pal = new Palabra();

            //leemos una palabra.
            pal = entrada.lectura();

            //Si la palabra es igual a la pasada por parámetros, modificamos la variable booleana. 
            if (Palabra.iguales(pal, a)) {

                encontrada = true;
            }

        }

        //Cierre de fichero
        entrada.cierre();

        //Devolvemos el valor booleano. 
        return encontrada;
    }

    //Devolverá "true" si la palabra se puede crear con las letras aparecidas en el sorteo. 
    public static boolean validarConLetras(Palabra a) throws Exception {

        //Declaraciones
        char[] palabra = a.toString().toCharArray();
        int[] letrasAparecidas = new int[Letra.getNumeroLetrasPanel()];
        char[] alfabeto = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        int[] apariciones = new int[alfabeto.length];
        boolean validada = true;

        //Contadores para realizar la comprobación de la palabra letra a letra. 
        int contador = 0;
        int contador2 = 0;

        //Cargamos dentro de un array las letras que han aparecido en el sorteo. 
        for (int i = 0; i < letrasAparecidas.length; i++) {

            letrasAparecidas[i] = Letra.getLetrasFinales()[i].getLetra();
        }

        //Escribimos en el array apariciones la cantidad de letras que aparecen
        for (int i = 0; i < letrasAparecidas.length; i++) {

            for (int e = 0; e < alfabeto.length; e++) {

                if (letrasAparecidas[i] == alfabeto[e]) {

                    apariciones[e]++;

                }

            }

        }

        //Si la palabra escrita es mayor que el número de letras aparecidas, devolveremos false. 
        if ((palabra.length > letrasAparecidas.length) || (palabra.length == 0)) {

            validada = false;

            //Sino haremos una comprobación letra por letra. 
        } else {

            while (contador < palabra.length) {
                while ((contador2 < letrasAparecidas.length) && (palabra[contador] != letrasAparecidas[contador2])) {

                    contador2++;
                }

                //Si el contador2 es igual al tamaño del array de letras aparecidas significa que no hay esa letra en el sorteo. 
                if (contador2 == letrasAparecidas.length) {

                    validada = false;
                    contador++;
                } else {

                    //Buscaremos cuantas veces hay la letra en el array de apariciones para comprobar que se pueda hacer. 
                    for (int i = 0; i < alfabeto.length; i++) {

                        if (palabra[contador] == alfabeto[i]) {

                            //Si en apariciones hay menos de una letra significa que no se puede usar diche letra. 
                            if (apariciones[i] < 1) {

                                validada = false;

                            } else {

                                //Sino, la contabilizamos y restamos una unidad a la letra usada.
                                apariciones[i]--;

                            }

                        }

                    }
                    //reiniciamos el contador2 e incrementamos el contador
                    contador2 = 0;
                    contador++;
                }
            }

        }

        //Devolvemos la variable booleana. 
        return validada;

    }

    //Método que devuelve la puntuación de la palabra pasada por parámetro. 
    public static int puntuacionPalabra(Palabra a) throws Exception {

        //Declaraciones
        char[] palabra = a.toString().toCharArray(); //Almacenamos dentro de un array char la palabra
        // pasada por parámetro. 
        int puntuacion = 0;
        int indicePalabra = 0;
        int indiceLetras = 0;

        while (indicePalabra < palabra.length) {

            //Bucle que busca la letra en el array de letras del fichero. 
            while ((palabra[indicePalabra] != ((char) Letra.recogerLetrasFichero()[indiceLetras].getLetra()))) {

                indiceLetras++;

            }
            //Cuando tenemos la letra, accedemos al atributo puntuación de dicha letra y lo sumamos al acumulador. 
            puntuacion = puntuacion + (Letra.recogerLetrasFichero()[indiceLetras].getValor() - '0');

            //incrementamos indice de la palabra y reseteamos el índice de letras.
            indicePalabra++;
            indiceLetras = 0;
        }

        //Devolución de la puntuación
        return puntuacion;
    }

    //Este método funciona igual que el anterior pero se le pasa por parámetro otro tipo de dato. (char). 
    public static int puntuacionPalabraCPU(char[] palabra) throws Exception {

        //Declaraciones
        int puntuacion = 0;
        int indicePalabra = 0;
        int indiceLetras = 0;

        while (indicePalabra < palabra.length) {
            //Bucle que busca la letra en el array de letras del fichero. 
            while ((palabra[indicePalabra] != ((char) Letra.recogerLetrasFichero()[indiceLetras].getLetra()))) {

                indiceLetras++;

            }
            //Cuando tenemos la letra, accedemos al atributo puntuación de dicha letra y lo sumamos al acumulador. 
            puntuacion = puntuacion + (Letra.recogerLetrasFichero()[indiceLetras].getValor() - '0');

            //incrementamos indice de la palabra y reseteamos el índice de letras.
            indicePalabra++;
            indiceLetras = 0;
        }
        //Devolución de la puntuación
        return puntuacion;
    }

    //Método que mediante un algoritmo devuelve una palabra pensada por el ordenador
    //ya validada con el panel de letras sorteadas y el diccionario.
    public static char[] palabraCPU() throws Exception {

        //Declaraciones
        char[] palabra = new char[Letra.getNumeroLetrasPanel()];
        int[] letrasAparecidas = new int[Letra.getNumeroLetrasPanel()];
        char[] alfabeto = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        int[] apariciones = new int[alfabeto.length];
        Palabra pal = new Palabra();
        boolean caracteres = false;
        boolean sePuede = false;
        String nombreArchivo = "X";

        //Cargamos las letras que han aparecido en el sorteo dentro de un array
        for (int i = 0; i < letrasAparecidas.length; i++) {
            letrasAparecidas[i] = Letra.getLetrasFinales()[i].getLetra();
        }

        //Dependiendo del diccionario escogido leeremos un fichero u otro. 
        switch (Partida.getDiccionario()) {

            case 1:
                nombreArchivo = "dic.es";
                break;

            case 2:
                nombreArchivo = "dic.ca";
                break;

            case 3:
                nombreArchivo = "dic.en";
                break;
        }

        //Abrimos el fichero correspondiente    
        FicherosPalabrasIn entrada = new FicherosPalabrasIn(nombreArchivo);

        boolean laTengo = false;

        while ((entrada.hayPalabras()) && !(laTengo)) {

            //Reiniciamos a 0 el array de apariciones
            for (int i = 0; i < apariciones.length; i++) {

                apariciones[i] = 0;

            }

            //leemos la primera palabra. 
            pal = entrada.lectura();

            //Mientras haya palabras y el número de carácteres de la palabra sea menor que 6, seguimos leyendo palabras. 
            /**
             * **Aqui podemos establecer un "nivel de dificultad" de la partida
             * modificando el número de carácteres mínimos que debe tener la
             * palabra pensada por el ordenador. A más carácteres mínimos, más
             * dificultad. Tiene que ser proporcional con el número de letras
             * aleatorias aparecidas.***
             */
            while ((entrada.hayPalabras()) && (pal.numeroDeCaracteres()) < 6) {

                pal = entrada.lectura();

            }

            //Apuntar el numero de apariciones de las letras aparecidas en el sorteo.
            for (int i = 0; i < letrasAparecidas.length; i++) {

                for (int e = 0; e < alfabeto.length; e++) {

                    if (letrasAparecidas[i] == alfabeto[e]) {

                        apariciones[e]++;

                    }

                }
            }

            laTengo = true;

            //Pasamos la palabra en un array de char. 
            palabra = pal.toString().toCharArray();

            int indice = 0;

            //Bucle que comprueba que dicha palabra se pueda hacer con el panel de letras aleatorias. 
            for (int i = 0; i < palabra.length; i++) {

                while ((indice < alfabeto.length)) {

                    if (palabra[i] == alfabeto[indice]) {

                        if ((apariciones[indice]) == 0) {

                            laTengo = false;

                        } else {

                            //Contabilizamos la letra. 
                            apariciones[indice]--;
                        }

                    }
                    //Incrementamos indice. 
                    indice++;
                }
                //Reseteamos la variable índice. 
                indice = 0;
            }

        }

        //Si no quedan más palabras.. 
        if (!(entrada.hayPalabras())) {

            //Ponemos un 0 al inicio de la palabra para indicar que no es correcta.
            //Si no se hace esto se queda con la última leída y la indica como válida.                         
            palabra[0] = '0';

        }

        //Cerramos el fichero.
        entrada.cierre();

        //Devolvemos la palabra encontrada        
        return palabra;

    }

}
