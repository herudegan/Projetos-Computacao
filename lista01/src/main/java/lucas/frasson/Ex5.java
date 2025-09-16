package lucas.frasson;

import java.util.Scanner;

public class Ex5 {
    public static void main(String[] args) {
        System.out.println("Digite a sua idade");
        Scanner scanner = new Scanner(System.in);
        int age = scanner.nextInt();
        if(age < 12 && age >= 0){
            System.out.println("Categoria Infantil");
        } else if(age < 17 && age >= 0){
            System.out.println("Categoria Adolescente");
        } else if(age >= 18) {
            System.out.println("Categoria Adulto");
        } else {
            System.out.println("Idade inválida");
        }

        scanner.close();
    }
}
