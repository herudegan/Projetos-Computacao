package lucas.frasson;

public class Ex6 {
    public static void main(String[] args) {
        double[] notas = {7, 8, 6, 9, 10};
        double soma = 0.0;

        System.out.println("Notas:");
        for(int i = 0; i < notas.length; i++){
            System.out.println(notas[i] + " ");
            soma += notas[i];
        }

        double media = soma/notas.length;
        System.out.println("Media: "+media);
    }
}
