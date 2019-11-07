import BBDD.Comida;
import BBDD.Controller;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Controller controller =Controller.getInstance();
        int tiempo_mins = 0;
        ArrayList<Comida> productos_comida;



        tiempo_mins = controller.ciudadesBBDD("Madrid");
        productos_comida = controller.listaProductosBBDD();


        for (int i = 0; i < productos_comida.size(); i++) {
            System.out.println(productos_comida.get(i));

        }



        if (tiempo_mins == 0){
            System.out.println("Lo sentimos, por ahora no tenemos servicio en esa ciudad");
        }else{
            System.out.println(tiempo_mins);
            System.out.println(productos_comida);
        }



        controller.actulizaBBDD(1,300);



    }
}
