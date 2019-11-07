package tests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConsolaTest {
    @Test
    public void probarElegirOpcion() {
        Consola c = new Consola();
        String[] opciones = new String[3];
        opciones[0] = "Opcion1";
        opciones[1] = "Opcion2";
        opciones[2] = "Opcion3";
        int opcion = c.elegirOpcion(opciones);
        assertTrue(opcion < opciones.length && opcion > 0);
    }
}