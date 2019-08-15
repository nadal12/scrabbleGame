/*
CLASE Palabra (14.12.2017)
 */
package scrabble;


public class Palabra {
    //DECLARACIONES
    private static final int MAXIMO=20; //número máximo de caracteres que puede tener una palabra
    private static final char FINAL_SECUENCIA='.';     
    private static final char SALTO_LINEA='\n';
    private static final char CR='\r';
    private static final char ESPACIO=' ';
    private static char caracter=ESPACIO;
    private char [] caracteres=new char[MAXIMO];
    private int numCaracteres;
    
    //MÉTODOS
    //CONSTRUCTORES
    public Palabra() {
        numCaracteres=0;
    }
    
    public Palabra(String palabra) {
        caracteres=palabra.toCharArray();
        numCaracteres=palabra.length();
    }
    
    //OTROS MÉTODOS
    //lectura de una palabra desde el teclado a partir de una secuencia de caracteres introducida
    //por teclado y acabada en '.' (FINAL_SECUENCIA).
   
    public void lectura() {
        numCaracteres=0;
        while ((caracter!=SALTO_LINEA)&&(caracter!=CR)&&(caracter!=FINAL_SECUENCIA)
                &&(caracter!=ESPACIO)&&(!caracterSeparacion(caracter))) {
            // almacenamiento en la componente correspondiente del array caracteres
            //del caracter leido de la palabra
            caracteres[numCaracteres]=caracter;
            // incrementamos numCaracteres para contabilizar el caracter de la 
            // palabra almacenado en el array caracteres
            numCaracteres++;
            // lectura del siguiente caracter de la secuencia de entrada
            caracter=LT.readCharacterSequence();
        } 
    }
    
    // buscar  palabra en la secuencia de entrada
    private static void buscarPalabra() {
        while ((caracter==ESPACIO)||(caracterSeparacion(caracter))) {
            caracter=LT.readCharacterSequence();
        }
    }
    
    //verifica si quedan palabras por leer en la secuencia de entrada
    public static boolean hayPalabras() {
        buscarPalabra();
        return ((caracter!=FINAL_SECUENCIA)&&(caracter!=SALTO_LINEA)&&
                (caracter!=CR));
    }
    
    // conversión de una plabra a String para poderla utilizar como parámetro en
    // un método de visualización en pantalla (System.out.printl y System.out.println).
 
    public String toString() {
        String resultado="";
        for (int indice=0; indice < numCaracteres; indice++) {
            resultado=resultado+caracteres[indice];
        }
        return resultado;
    }
    
 
    //devuelve el número de caracteres de la palabra
    public int numeroDeCaracteres() {
        return numCaracteres;
    }
    
    //copiar una palabra en otra dadas por parametro
    public static void copiar(Palabra a, Palabra b) {
        b.numCaracteres=a.numCaracteres;
        for (int indice=0;indice<a.numCaracteres;indice++) {
            b.caracteres[indice]=a.caracteres[indice];
        }
    }
    
    //verifica si dos palabras son iguales
    public static boolean iguales(Palabra pal1, Palabra pal2) {
        
        if (pal1.numCaracteres==pal2.numCaracteres) {
            int indice=0;
            while ((pal1.caracteres[indice]==pal2.caracteres[indice])&&(indice<pal1.numCaracteres-1)) {
                indice++;
            }
            return (pal1.caracteres[indice]==pal2.caracteres[indice]);
        }
        else {
            return false;
        }
    }
           
    private static boolean caracterSeparacion(char car) {
        return ((car==':')||(car==',')||(car==';')||(car=='-'));
    }
    
     //obtiene el número de caracteres diferentes de una palabra
    public int numeroCaracteresDiferentes() {
        //DECLARACIONES
        final char [] ALFABETO="abcdefghijklmnopqrstuvwxyz".toCharArray();
        boolean [] apariciones=new boolean[ALFABETO.length];
        int caracteresDiferentes=0;
        
        //ACCIONES
        //inicialización array apariciones a false
        for (int indice=0;indice<apariciones.length;indice++) {
            apariciones[indice]=false;
        }
        //verificación caracteres de la palabra
        for (int indice=0; indice<numCaracteres;indice++) {
            //buscar caracter de la palabra en el aray ALFABETO
            int posicion=0;//posicion donde estará ubicado el correspondiente caracter
            while (ALFABETO[posicion]!=caracteres[indice]) {
                posicion++;
            }
            //actualizar array apariciones
            apariciones[posicion]=true;  
        }
        //contar el número de componentes con el valor true del array apariciones
        //dicho valor se corresponderá con el número de caracteres diferentes
        //que conforman la palabra objeto de este método
        int resultado=0;
        for (int indice=0;indice<apariciones.length;indice++) {
            if (apariciones[indice]) {
                resultado++;
            }
        }
        //decolver el valor de resultado
        return resultado;  
    }
}
