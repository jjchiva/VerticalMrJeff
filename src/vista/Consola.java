package vista;

import modelo.Ciudad;
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

    public int elegirOpcion(String mensaje, String[] opciones) {
        int opc = -1;
        boolean err = false;
        do {
            System.out.println(mensaje);
            mostrarOpciones(opciones);
            opc = pedirNumero(mensaje, opciones);
            err = (opc < 0 || opc > opciones.length);
            if(err)
                System.out.println("No válido. Elige una de las " + opciones.length + " opciones");
        } while(err);
        return opc;
    }

    public int pedirNumero(String mensaje, String[] opciones) {
        boolean esNumero = true;
        int sel = -1;
        do {
            try {
                if(!esNumero && opciones.length > 0) {
                    System.out.println(mensaje);
                    mostrarOpciones(opciones);
                }
                System.out.print("> ");
                sel = sc.nextInt();
                sc.nextLine();
                esNumero = true;
            } catch(InputMismatchException | NumberFormatException e) {
                System.out.println("Número no válido");
                esNumero = false;
                sc.nextLine();
            }
        } while (!esNumero || sel < 0);
        System.out.println("");
        return sel;
    }

    public int pedirNumero() {
        return this.pedirNumero("", new String[0]);
    }

    public void mostrarOpciones(String[] opciones) {
        int i = 1;
        for (String o : opciones) {
            System.out.println("[" + (i++) + "] " + o );
        }
    }

    public List<Comida> elegirPedido(List<Comida> productosComida) {

        List<Comida> pedidosUsuario = new ArrayList<>();

        do {
            int numOpciones = productosComida.size();
            opciones = new String[numOpciones];
            for (int i = 0; i < numOpciones; i++) {
                opciones[i] = productosComida.get(i).getProducto() + " " +  productosComida.get(i).getPrecio()  + "€";
            }

            seleccion = this.elegirOpcion("Elige uno de los productos: ", opciones);
            Comida comidaSeleccionada = productosComida.get(seleccion - 1);
            nombre = opciones[seleccion - 1];

            boolean excedidoRaciones = false;
            do {
                System.out.println("¿Cuántas raciones quieres?");
                raciones = this.pedirNumero();
                excedidoRaciones = (raciones > comidaSeleccionada.getUnidades());
                if(excedidoRaciones)
                    System.out.println("No nos quedan tantas raciones! (disponibles: " + comidaSeleccionada.getUnidades() + ")");
            } while(excedidoRaciones);

            id = comidaSeleccionada.getId();
            precio = comidaSeleccionada.getPrecio();

            Comida pedido = new Comida(id , nombre, precio, raciones);
            pedidosUsuario.add(pedido);

            System.out.print("¡Añadido!");

            opciones = new String[2];
            opciones[0] = "Sí";
            opciones[1] = "No";
            seleccion = this.elegirOpcion("¿Quieres algo más?", opciones);
        } while (seleccion == 1);

        return pedidosUsuario;
    }

    public Ciudad elegirCiudad(List<Ciudad> listaCiudades) {
        Ciudad ciudadSeleccionada = null;

        int numOpciones = listaCiudades.size();
        opciones = new String[numOpciones];
        for (int i = 0; i < numOpciones; i++) {
            opciones[i] = listaCiudades.get(i).getCiudad();
        }
        seleccion = this.elegirOpcion("A qué ciudad quieres que te llevemos la comida?", opciones);
        ciudadSeleccionada = listaCiudades.get(seleccion - 1);

        return ciudadSeleccionada;
    }

    public boolean confirmarPedido(List<Comida> pedidos, Ciudad ciudadSeleccionada) {
        opciones = new String[2];
        opciones[0] = "Sí";
        opciones[1] = "No";
        int opc = this.elegirOpcion("¿Confirmar y pagar pedido?", opciones);
        if(opc == 1)
            return true;
        return false;
    }
}
