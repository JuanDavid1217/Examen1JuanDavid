package org.uv.examen1juandaviddelgadomunoz;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Examen1JuanDavidDelgadoMunozApplicationTests {

	@Test
    void contextLoads() {
        // Lógica de prueba
        boolean resultado = myMethod();

        // Afirmación
        assertTrue(resultado);
    }

    public boolean myMethod() {
        // Lógica del método que estás probando
        return true; // El resultado esperado es verdadero (true)
    }

}
