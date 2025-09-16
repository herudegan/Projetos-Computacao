package lucas.frasson;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Ex7 {
    public static double calcularIMC(double peso, double altura){
        return peso/(altura*altura);
    }

    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("#.00");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite seu peso: ");
        double peso = scanner.nextDouble();

        System.out.println("Digite seu altura: ");
        double altura = scanner.nextDouble();

        double imc = calcularIMC(peso, altura);
        String imc_format = df.format(imc);
        System.out.println("IMC: "+imc_format);

        scanner.close();
    }
}
