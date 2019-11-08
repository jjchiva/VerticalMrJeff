package vista;

import modelo.Comida;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Consola {
    private static Consola instance;
    private Scanner sc;
    static String[] opciones;
    static int seleccion;
    static String nombre;

    static int precio;
    static int id;
    static int unidades_final;
    static int raciones;

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

    public ArrayList<Comida> elegirPedido(List<Comida> productosComida){

        ArrayList<Comida> pedidosUsuario = new ArrayList<>();

        do {
            int numOpciones = productosComida.size();
            opciones = new String[numOpciones];
            for (int i = 0; i < numOpciones; i++) {
                opciones[i] = productosComida.get(i).getProducto() + " " +  productosComida.get(i).getPrecio()  + "€";
            }

            System.out.println("Elige uno de los productos: ");
            seleccion = this.elegirOpcion(opciones);

            nombre = opciones[seleccion - 1];

            System.out.println("Cuántas raciones quieres?");
            raciones = this.pedirNumero();
            id = productosComida.get(seleccion - 1).getId();
            System.out.println(id);
            precio = productosComida.get(seleccion - 1).getPrecio();
            Comida pedido = new Comida(id , nombre, precio, raciones);
            pedidosUsuario.add(pedido);

            System.out.println("Añadido! Quieres algo más?");

            opciones = new String[2];
            opciones[0] = "Sí";
            opciones[1] = "No";
            seleccion = this.elegirOpcion(opciones);
        } while (seleccion == 1);

        return pedidosUsuario;
    }
}
