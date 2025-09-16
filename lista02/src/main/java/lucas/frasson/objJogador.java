package org.example;
import java.util.List;
import java.util.Random;

public class geradorJogador {

    public static void main(String[] args) {
        generateJogador gerador = new generateJogador();
        Jogador jogador = gerador.gerarJogador();
        System.out.println(jogador);
    }
}

class Jogador {
    private String nome;
    private String sobrenome;
    private int idade;
    private String posicao;
    private String time;

    public Jogador(String nome, String sobrenome, int idade, String posicao, String time) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.idade = idade;
        this.posicao = posicao;
        this.time = time;
    }

    @Override
    public String toString() {
        return nome + " " + sobrenome + " é um futebolista brasileiro de " + idade + " anos que atua como " + posicao +
                ". Atualmente defende o " + time + ".";
    }
}

class generateJogador {
    private List<String> nomes;
    private List<String> sobrenomes;
    private List<String> posicoes;
    private List<String> times;
    private Random random;

    public generateJogador() {
        nomes = List.of("Carlos", "Rafael", "João", "Lucas", "André", "Pedro", "Tiago", "Felipe");
        sobrenomes = List.of("Silva", "Santos", "Oliveira", "Souza", "Pereira", "Lima", "Costa", "Ramos");
        posicoes = List.of("goleiro", "zagueiro", "lateral", "volante", "meia", "atacante");
        times = List.of("Flamengo", "Palmeiras", "Corinthians", "São Paulo", "Grêmio", "Internacional", "Cruzeiro", "Atlético Mineiro");
        random = new Random();
    }

    public Jogador gerarJogador() {
        String nome = nomes.get(random.nextInt(nomes.size()));
        String sobrenome = sobrenomes.get(random.nextInt(sobrenomes.size()));
        String posicao = posicoes.get(random.nextInt(posicoes.size()));
        String time = times.get(random.nextInt(times.size()));
        int idade = 18 + random.nextInt(23);

        return new Jogador(nome, sobrenome, idade, posicao, time);
    }
}
