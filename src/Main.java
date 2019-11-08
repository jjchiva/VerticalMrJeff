import modelo.Ciudad;
import modelo.Comida;
import BBDD.Controller;
import vista.Consola;

import java.util.ArrayList;
import java.util.List;


public class Main {

    static Consola consola;
    static Controller controller;
    static List<Comida> pedidosUsuario, productosComida;
    static List<Ciudad> listaCiudad;
    static Ciudad ciudadSeleccionada;
    static int id , unidades_final;

    public static void mostrarCostePedido() {

        for (int i = 0; i < pedidosUsuario.size() ; i++) {
            Comida pedido = pedidosUsuario.get(i);
            System.out.println(" - " + pedido.getProducto() + " x" + pedido.getUnidades() + " " + (pedido.getPrecio() * pedido.getUnidades()) + "€");
        }
        System.out.println();

        int precio_final = pedidosUsuario.stream()
                .mapToInt(pedidosUsuario_-> pedidosUsuario_.getPrecio() * pedidosUsuario_.getUnidades())
                .sum();
        System.out.println("El coste total de su pedido es de " + precio_final + "€\n");
    }

    public static void mostrarYElegirCiudad() {
        listaCiudad = controller.ciudadesBBDD();
        ciudadSeleccionada = consola.elegirCiudad(listaCiudad);
    }

    public static void main(String[] args) {
        controller = Controller.getInstance();
        consola = Consola.getInstance();

        pedidosUsuario = new ArrayList<>();
        productosComida = controller.listaProductosBBDD();
        pedidosUsuario = consola.elegirPedido(productosComida);

        mostrarYElegirCiudad();
        System.out.println("\nDetalle: ");
        mostrarCostePedido();

        boolean confirmado = consola.confirmarPedido(pedidosUsuario, ciudadSeleccionada);
        if(confirmado) {
            Comida.actualizarUnidadesBD(pedidosUsuario, productosComida);
            System.out.println("Hecho. ¡Gracias por su compra!");
            System.out.println("Enviaremos tu pedido a " + ciudadSeleccionada.getCiudad() + " en aproximadamente " + ciudadSeleccionada.getTiempo() + " minutos");
        }
        else
            System.out.println("¡Transacción anulada!");
    }
}
