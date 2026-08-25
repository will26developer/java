package excepciones;

import excepciones.excepcion_class.DivisionPorCeroExcepcion;
import excepciones.excepcion_class.FormatoInvalidoExcepcion;

import java.util.Scanner;

public class EjemploExcepciones {
    public static void main(String[] args) throws DivisionPorCeroExcepcion, FormatoInvalidoExcepcion {
        Calculadora calculadora = new Calculadora();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese un dividendo: ");
        String num1 = scanner.nextLine();
        System.out.println("Ingrese un divisor: ");
        String num2 = scanner.nextLine();

        calculadora.dividir(num1,num2);

        scanner.close();
    }
}
