package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class geradorSenhas {

    public static void main(String[] args) {
        generateSenha gerador = new generateSenha();
        String senha = gerador.gerarSenha(8);
        System.out.println("Senha gerada: " + senha);
    }
}

class generateSenha {

    private List<String> caracteres;
    private Random random;

    public generateSenha() {
        this.caracteres = new ArrayList<>();
        this.random = new Random();
        inicializarCaracteres();
    }

    private void inicializarCaracteres() {
        for (char c = 'A'; c <= 'Z'; c++) {
            caracteres.add(String.valueOf(c));
        }
        for (char c = 'a'; c <= 'z'; c++) {
            caracteres.add(String.valueOf(c));
        }
        for (char c = '0'; c <= '9'; c++) {
            caracteres.add(String.valueOf(c));
        }
    }

    public String gerarSenha(int tamanho) {
        StringBuilder senha = new StringBuilder();

        for (int i = 0; i < tamanho; i++) {
            int indice = random.nextInt(caracteres.size());
            senha.append(caracteres.get(indice));
        }

        return senha.toString();
    }
}
