package vista;

import java.util.Scanner;

public class Consola {
    private static Consola instance;
    private Scanner sc;

    private Consola() {
        sc = new Scanner(System.in);
    }

    public static Consola getInstance() {
        if(instance == null)
            instance = new Consola();
        return instance;
    }

    public int elegirOpcion(String[] opciones) {
        int opc = -1;
        do {
            mostrarOpciones(opciones);
            System.out.print("> ");
            opc = sc.nextInt();
            sc.nextLine();
        } while(opc < 1 || opc > opciones.length);
            System.out.println("");
        return opc;
    }

    public void mostrarOpciones(String[] opciones) {
        int i = 1;
        for (String o : opciones) {
            System.out.println("[" + i + "] " + o );
        }
    }
}
