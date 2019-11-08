package vista;

import java.text.NumberFormat;
import java.util.InputMismatchException;
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
        try {
            do {
                mostrarOpciones(opciones);
                System.out.print("> ");

                opc = sc.nextInt();
                sc.nextLine();
            } while (opc < 1 || opc > opciones.length);
            System.out.println("");
        } catch(InputMismatchException e) { System.out.println("Número no válido"); }
        return opc;
    }

    public int pedirNumero() {
        boolean esNumero = true;
        int sel = -1;
        do {
            try {
                System.out.print("> ");
                sel = sc.nextInt();
                sc.nextLine();
                esNumero = true;
            } catch(InputMismatchException e) {
                System.out.println("Número no válido");
                esNumero = false;
            }
        } while (!esNumero || sel < 0);
        System.out.println("");
        return sel;
    }

    public void mostrarOpciones(String[] opciones) {
        int i = 1;
        for (String o : opciones) {
            System.out.println("[" + (i++) + "] " + o );
        }
    }
}
