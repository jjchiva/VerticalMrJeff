import modelo.Ciudad;
import modelo.Comida;
import BBDD.Controller;
import vista.Consola;

import java.util.ArrayList;
import java.util.List;


public class Main {

    static List<Comida> pedidosUsuario, productosComida;
    static String[] opciones;
    static int seleccion;

    static String nombre;
    static int precio;
    static int id;
    static int unidades_final;
    static int raciones;

    public static void main(String[] args) {
        Controller controller = Controller.getInstance();
        Consola consola = Consola.getInstance();

        pedidosUsuario = new ArrayList<>();
        productosComida = controller.listaProductosBBDD();

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
            id = productosComida.get(seleccion - 1).getId();
            System.out.println(id);
            precio = productosComida.get(seleccion - 1).getPrecio();
            Comida pedido = new Comida(id , nombre, precio, raciones);
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


        int suma = pedidosUsuario.stream()
                .mapToInt(pedidosUsuario_-> pedidosUsuario_.getPrecio())
                .sum();

        System.out.println(suma);




        for (int i = 0; i < pedidosUsuario.size(); i++) {
            id = pedidosUsuario.get(i).getId();
            unidades_final = productosComida.get(id - 1).getUnidades() - pedidosUsuario.get(i).getUnidades();

            controller.actualizarUnidadesBD(id , unidades_final);

        }



    }
}
