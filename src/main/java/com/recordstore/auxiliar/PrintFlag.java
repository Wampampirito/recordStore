package com.recordstore.auxiliar;
/**
 * Clase utilitaria que proporciona metodos para imprimir mensajes dentro de un rectangulo con bordes.
 * El metodo principal es {@link #flag(String)}, que muestra el mensaje dentro de un rectangulo con bordes 
 * y maneja el caso en el que el mensaje es demasiado largo para caber en una sola linea.
 * 
 * <p>Esta clase es util para mostrar mensajes de manera destacada en la consola, especialmente cuando 
 * se necesita destacar mensajes importantes o de formato especial.</p>
 * 
 * <p><strong>Características:</strong></p>
 * <ul>
 *   <li>Calcula automaticamente el ancho del rectangulo segun la longitud del mensaje.</li>
 *   <li>Asegura que el mensaje no se desborde, dividiendolo en varias lineas si es necesario.</li>
 *   <li>Permite personalizar el margen y el borde del rectangulo.</li>
 * </ul>
 * 
 * <p><strong>Ejemplo de uso:</strong></p>
 * <pre>
 * printMsg.flag("Este es un mensaje importante");
 * </pre>
 */
public class printMsg {

    /**
     * Imprime un mensaje dentro de un rectangulo con bordes. Si el mensaje es demasiado largo, 
     * se divide en varias lineas para que quepa dentro del rectangulo.
     * 
     * @param mensaje El mensaje a imprimir dentro del rectangulo.
     * @throws IllegalArgumentException Si el mensaje es nulo.
     */
    public static void flag(String mensaje) {
        // Calcular el ancho del rectangulo
        int ancho = Math.min(mensaje.length() + 10, 100); // Ancho = length + 10, maximo 100
        int margen = 5; // Minimo 5 espacios de margen

        // Asegurarse de que el ancho sea suficiente para los margenes
        if (ancho < margen * 2 + 2) { // 2 bordes + 2 espacios minimos
            ancho = margen * 2 + 2;
        }

        // Crear los bordes
        String lineaSuperior = "╔" + repeatChar('═', ancho ) + "╗"; // Borde superior
        String lineaInferior = "╚" + repeatChar('═', ancho ) + "╝"; // Borde inferior
        String bordeLateral = "║"; // Borde lateral

        System.out.println(lineaSuperior); // Imprime borde superior

        // Calcular el espacio disponible para el texto (ancho - bordes - margenes)
        int espacioTexto = ancho - margen * 2;

        // Si el mensaje cabe en una linea, imprimelo sin dividir
        if (mensaje.length() <= espacioTexto) {
            String linea = bordeLateral + " ".repeat(margen) + mensaje + " ".repeat(espacioTexto - mensaje.length()) + " ".repeat(margen) + bordeLateral;
            System.out.println(linea);
        } else {
            // Dividir el mensaje en lineas que no excedan el espacio disponible
            for (String lineaMensaje : dividirMensaje(mensaje, espacioTexto)) {
                String linea = bordeLateral + " ".repeat(margen) + lineaMensaje + " ".repeat(espacioTexto - lineaMensaje.length()) + " ".repeat(margen) + bordeLateral;
                System.out.println(linea);
            }
        }

        System.out.println(lineaInferior); // Imprime borde inferior
    }

    /**
     * Divide el mensaje en lineas que no excedan un ancho maximo.
     * 
     * @param mensaje El mensaje a dividir.
     * @param anchoMaximo El ancho maximo de cada linea.
     * @return Un arreglo de cadenas de texto que representan las lineas divididas.
     */
    private static String[] dividirMensaje(String mensaje, int anchoMaximo) {
        return mensaje.split("(?<=\\G.{" + anchoMaximo + "})");
    }

    /**
     * Repite un caracter especificado un numero dado de veces.
     * 
     * @param c El caracter a repetir.
     * @param n El numero de veces que se debe repetir el caracter.
     * @return Una cadena con el caracter repetido.
     */
    private static String repeatChar(char c, int n) {
        return new String(new char[n]).replace('\0', c);
    }

    /**
     * Metodo principal que realiza pruebas de la clase {@link printMsg}.
     * Imprime un mensaje largo y uno corto dentro de un rectangulo con bordes.
     * 
     * @param args Los argumentos de linea de comandos (no utilizados en este caso).
     */
    public static void main(String[] args) {
        String textoLargo = "Este es un mensaje de prueba muy largo que debe dividirse en varias lineas para que quepa dentro del rectangulo.";
        String textoCorto = "Este es un mensaje corto.";

        // Prueba con un mensaje largo
        flag(textoLargo);

        // Prueba con un mensaje corto
        flag(textoCorto);
    }
}
