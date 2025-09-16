package lucas.frasson;

import java.util.Scanner;

public class Ex8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite seu nome: ");
        String nome = sc.nextLine();
        String nome_s = nome.replaceAll(" ", "");

        int number = nome_s.length();
        boolean silva = nome.toLowerCase().contains("silva");
        nome = nome.toUpperCase();

        System.out.println("Numero de caracteres: "+number);
        System.out.println("Maiusculas: "+nome);
        System.out.println("Contem 'Silva': "+silva);
    }
}