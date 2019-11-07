import modelo.Ciudad;
import modelo.Comida;
import BBDD.Controller;
import vista.Consola;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Main {

    static List<Comida> pedidosUsuario, productosComida;
    static String[] opciones;
    static int seleccion;

    static String nombre;
    static int precio;
    static int raciones;

    public static void main(String[] args) {
        Controller controller = Controller.getInstance();
        Consola consola = Consola.getInstance();

        pedidosUsuario = new ArrayList<>();
        productosComida = controller.listaProductosBBDD();
        productosComida.sort(Comparator.comparing(Comida::getProducto));

        do {
            int numOpciones = productosComida.size();
            opciones = new String[numOpciones];
            for (int i = 0; i < numOpciones; i++) {
                opciones[i] = productosComida.get(i).getProducto();
            }

            System.out.println("Elige uno de los productos: ");
            seleccion = consola.elegirOpcion(opciones);

            nombre = opciones[seleccion - 1];

            System.out.println("Cuántas raciones quieres?");
            raciones = consola.pedirNumero();
            precio = productosComida.get(seleccion - 1).getPrecio();
            Comida pedido = new Comida(nombre, precio, raciones);
            pedidosUsuario.add(pedido);

            System.out.println("Añadido! Quieres algo más?");

            opciones = new String[2];
            opciones[0] = "Sí";
            opciones[1] = "No";
            seleccion = consola.elegirOpcion(opciones);
        } while (seleccion == 1);

        pedidosUsuario.stream().forEach(c -> System.out.print(c + ", "));



        int tiempo_mins = 0;
        List<Ciudad> listaCiudad;


        listaCiudad = controller.ciudadesBBDD();


        for (int i = 0; i < productosComida.size(); i++) {
            System.out.println(productosComida.get(i));

        }

        controller.actulizaBBDD(1, 300);
    }
}
