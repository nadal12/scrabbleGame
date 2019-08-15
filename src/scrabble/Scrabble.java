/*
Juego Scrabble.
Autor: Nadal Llabrés Belmar.
Fecha de creación: 12/12/2017.
 */
package scrabble;

public class Scrabble {
    
    //Declaraciones
    
        static int puntuacionJugador = 0;
        static int puntuacionCPU = 0;

    public static void main(String[] argumentos) throws Exception {

//********************DECLARACIONES***********************

        String palabraIntroducida; //String dónde se almacenará la palabra introducida por el usuario.
        String teclaInicio; //String para apretar la tecla inicio y empezar el juego. 
        int turno;   //Si el valor es 0 juega el humano, si es 1 el ordenador. 
        char [] palabraCPU=new char[Letra.getNumeroLetrasPanel()]; //Array donde se almacenará la palabra de la CPU.
        
//***************MENÚ Y CONFIGURACIÓN INICIAL*************

        //Configuración inicial del juego.
        cabeceraMenuInicial();
        
        //Llamada al método que irá almacenando toda la configuración de la partida. 
        Partida.configuracionInicial();
        
        //Resumen de la configuración establecida. 
        Partida.resumenConfiguracionInicial();
        
        //Llamada al método que elegirá aleatoriamente quién empieza la partida. 
        Partida.sorteoTurnoInicial();
        System.out.print("Presiona enter para empezar la partida.");
        teclaInicio = LT.readLine();
        System.out.println();
        
        //Almacenamos el turno sorteado en la variable.
        turno=Partida.getTurnoInicial();
        
//***************PARTIDA Y TURNOS********************

        //Bucle dónde se ejecutará la partida hasta que alguien llegue a la puntuación máxima. 
        while ((puntuacionJugador < Partida.getPuntuacionMaxima()) && (puntuacionCPU < Partida.getPuntuacionMaxima())) {

            if (turno % 2 == 0) {
                //TURNO DEL HUMANO

                System.out.print("Turno de " + Partida.getNombreJugador() + "\n");

                //Método que imprime las letras para jugar y su puntuación
                panelLetras();

                //Salto de línea.
                System.out.println();
                System.out.print("Palabra: ");
                palabraIntroducida = LT.readLine();

                //Creación de un objeto palabra
                Palabra pal = new Palabra(palabraIntroducida);
                
                //Si el método que valida la palabra con las letras aparecidas devuelve "true" 
                //entraremos dentro de la condición para comprobar que esté en el diccionario. 
                //De no ser así ya no comprobaremos nada en el diccionario (ahorro de ciclos de CPU).
                if (Palabras.validarConLetras(pal)) {

                    //Si la palabra se encuentra en el diccionario seleccionado entraremos dentro de la condición.
                    //De no ser así el mensaje que aparecerá será "palabra incorrecta".
                    if (Palabras.buscarEnDiccionario(pal)) {

                        //Mensaje de palabra correcta. 
                        System.out.println("¡Palabra correcta!\n");
                        
                        //A través de un método se calcula la puntuación de dicha palabra y se muestra en pantalla. 
                        System.out.print("¡"+Palabras.puntuacionPalabra(pal) + " puntos!\n");
                        
                        //La puntuación se suma al acumulador correspondiente. 
                        puntuacionJugador = puntuacionJugador + Palabras.puntuacionPalabra(pal);
                        
                       //Antes de proceder con el siguiente turno, mostramos un resumen de las puntuaciones actuales.  
                       resumenPuntuacion();
                        
                    } else {

                        //Mensaje de palabra incorrecta porque no ha sido encontrada en el diccionario. 
                        System.out.println("Palabra incorrecta\n");

                    }

                } else {
                    
                    //Mensaje de palabra incorrecta porque no ha sido validada con las letras sorteadas. 
                    System.out.println("Palabra incorrecta\n");

                }
                
                //Incrementamos la variable turno para seguir con el siguiente turno. 
                turno++;

            } else {

                //TURNO DEL ORDENADOR
                
                System.out.print("Turno del ordenador" + "\n");
                
                //Llamada al método para mostrar en pantalla las letras sorteadas y su puntuación.
                panelLetras();
                System.out.println();
                System.out.print("Palabra: ");
                
                //El método palabraCPU() nos devuelve la palabra escogida por el ordenador a través de un algoritmo.
                //La almacenamos dentro del array palabraCPU
                //La palabra devuelta ya ha sido validada con el diccionario y con el panel. 
                palabraCPU=Palabras.palabraCPU();
                
                //Si la primera componente del array no es un '0' significa que ha encontrado una palabra.
                //Si la primera componente empieza por una letra significa que tiene una palabra válida. 
                if (palabraCPU[0]!='0') {
                    
                //Visualización por pantalla de la palabra hallada a travé de una iteración para recorrer
                //todas las componentes del array. 
                for (int i=0;i<palabraCPU.length;i++) {
                System.out.print(palabraCPU[i]);           
                }
                
                //Salto de línea
                System.out.println();
                
                //Como la palabra ha sido verificada por el método palabraCPU(), se visualiza el mensaje 
                //de palabra correcta. 
                System.out.print("¡Palabra correcta!\n");
                
                //Visualización de la puntuación obtenida. 
                System.out.print("¡"+Palabras.puntuacionPalabraCPU(palabraCPU)+" puntos!\n");
                
                //Suma de la puntuación en el acumulador correspondiente.
                puntuacionCPU=puntuacionCPU+Palabras.puntuacionPalabraCPU(palabraCPU);
                
                }else{
                
                    //Si la primera componente es '0' significa que no ha encontrado ninguna palabra
                    //que pueda hacer con las letras sorteadas. 
                    
                    //Visualización del mensaje. 
                    System.out.println("No se me ocurre ninguna...");
                
                }
                
                //Visualización del resumen de puntos. 
                 resumenPuntuacion();
                
                 //Salto de línea
                System.out.println();
                
                //Incremento de la variable turno antes de pasar al siguiente turno. 
                turno++;
            }

        }
        
        //Cuando llegamos aqui es porque alguno de los jugadores ha igualado o superado
        //la puntuación establecida al inicio. 
        
        //Comparación de puntuaciones
        
        //Si no son iguales significa que hay un claro ganador, sino hay un empate. 
        if (puntuacionJugador!=puntuacionCPU) {
        
        //Si la del jugador es mayor, visualizamos que ha ganado el jugador. 
        //De no ser así, visualizamos que ha ganado el ordenador. 
        if (puntuacionJugador > puntuacionCPU) {

            System.out.println("¡HA GANADO " + Partida.getNombreJugador()+"!");
            
            //Llamada al método de despedida. 
            mensajeDespedida();
        } else {

            System.out.println("¡HA GANADO EL ORDENADOR!");
            //Llamada al método de despedida. 
            mensajeDespedida();
        }
        }else{
        
            System.out.println("¡EMPATE DE PUNTOS!");
         //Llamada al método de despedida. 
            mensajeDespedida();
        }
        
    }

    //MÉTODOS
    
    //Cabecera que se muestra al ejecutar el programa. 
    public static void cabeceraMenuInicial() {

        System.out.println("*************************************************");
        System.out.println("                 JUEGO SCRABBLE");
        System.out.println("*************************************************\n");

    }
    
 //A través de este método se visualizarán las letras sorteadas con su puntuación.    
 public static void panelLetras() throws Exception {
     //Llamada al método que escogerá las letras aleatoriamente y las almacenará en 
     //un array que esta como atributo en la clase Letra. 
                Letra.getLetrasRandom();
                
                //Recorremos el array de las letras hasta que i sea igual al número
                //de letras sortadas que nos devuelve el método getNumeroLetrasPanel().
                for (int i =0; i<Letra.getNumeroLetrasPanel();i++) {
                
                    //Visualizamos cada componente del array donde se han almacenado las letras sorteadas. 
                    //a través del método getLetra(), accedemos al atributo letra. 
                    System.out.print((char)Letra.getLetrasFinales()[i].getLetra()+"   ");
                
                }
                //Salto de línea
                System.out.println();
                for (int i=0;i<Letra.getNumeroLetrasPanel();i++){
                
                System.out.print(Letra.getLetrasFinales()[i].getValor()-'0'+"   ");
                
                }
 
 }
 
 //Método que mostrará por pantalla el estado actual de las puntuaciones. 
 public static void resumenPuntuacion() {
 
     //Visualización de las puntuaciones actuales de cada jugador. 
     System.out.println("-------------------------------------------");
                 System.out.println("Puntuación de "+Partida.getNombreJugador()+": " + puntuacionJugador + "\n");
                 System.out.println("Puntuación del ordenador: "+puntuacionCPU);
                 System.out.println("-------------------------------------------");
 
 }
 
 //Visualización de un mensaje de despedida para el jugador. 
 public static void mensajeDespedida() {
 
     //Visualización del mensaje
     System.out.println("Juego finalizado. ¡Hasta pronto!");
     
 
 }
}
