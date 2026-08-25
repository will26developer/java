package excepciones;

import excepciones.excepcion_class.DivisionPorCeroExcepcion;
import excepciones.excepcion_class.FormatoInvalidoExcepcion;

public class Calculadora {

    public double dividir(int numerador, int divisor) throws DivisionPorCeroExcepcion {
        if (divisor == 0) {
            throw new DivisionPorCeroExcepcion("No se puede dividir por cero");
        }
        return numerador / divisor;
    }

    public double dividir(String numerador, String divisor) throws DivisionPorCeroExcepcion, FormatoInvalidoExcepcion {
        int num1 = 0;
        int num2 = 0;
        try {
            num1 = Integer.parseInt(numerador);
            num2 = Integer.parseInt(divisor);
        } catch (NumberFormatException e) {
            throw new FormatoInvalidoExcepcion("El formato de los datos es incorrecto");
        }
        return this.dividir(num1,num2);
    }
}
