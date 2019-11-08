import modelo.Ciudad;
import modelo.Comida;
import BBDD.Controller;
import vista.Consola;

import java.util.ArrayList;
import java.util.List;


public class Main {

    static List<Comida> pedidosUsuario, productosComida;
    static int id , unidades_final;

    public static void mostrarCostePedido(){

        for (int i = 0; i < pedidosUsuario.size() ; i++) {
            System.out.println(pedidosUsuario.get(i) + " x" +pedidosUsuario.get(i).getUnidades() + " " + (pedidosUsuario.get(i).getPrecio()*pedidosUsuario.get(i).getUnidades()) + "€");
        }
        System.out.println();

        int precio_final = pedidosUsuario.stream()
                .mapToInt(pedidosUsuario_-> pedidosUsuario_.getPrecio() * pedidosUsuario_.getUnidades())
                .sum();
        System.out.println("El coste de su pedido es de " + precio_final+"€");
        System.out.println("Gracias por su compra.");
    }


    public static void main(String[] args) {

        Comida comida = new Comida();
        Controller controller = Controller.getInstance();
        Consola consola = Consola.getInstance();

        pedidosUsuario = new ArrayList<>();
        productosComida = controller.listaProductosBBDD();
        pedidosUsuario = consola.elegirPedido(productosComida);
        mostrarCostePedido();
        comida.actualizarUnidadesBD(pedidosUsuario,productosComida);


    }
}
