package test;

import org.junit.jupiter.api.Test;
import vista.Consola;

import static org.junit.jupiter.api.Assertions.*;

class ConsolaTest {

    @Test
    void elegirOpcion() {
        Consola consola = Consola.getInstance();
        int numOpciones = 3;
        String[] opciones = new String[numOpciones];
        for (int i = 0; i < numOpciones; i++) {
            opciones[i] = "Opción " + (i + 1);
        }

        int seleccion = consola.elegirOpcion(opciones);
        assertTrue(seleccion > 0 && seleccion < numOpciones);
    }
}