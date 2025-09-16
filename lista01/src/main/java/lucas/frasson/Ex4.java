package lucas.frasson;

import java.util.Scanner;

public class Ex4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o valor da sua compra: ");
        double value = scanner.nextDouble();

        if (value > 100){
            value = value*0.9;
            System.out.println("Valor final com desconto: " + value);
        } else {
            System.out.println("Valor final sem desconto: " + value);
        }

        scanner.close();
    }
}
