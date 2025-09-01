package org.example;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class geradorPokemon {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Quantos Pokémons deseja gerar? ");
        int quantidade = scanner.nextInt();

        generatePokemon gerador = new generatePokemon();

        for (int i = 0; i < quantidade; i++) {
            Pokemon pokemon = gerador.gerarPokemon();
            System.out.println(pokemon);
        }

        scanner.close();
    }
}

class Pokemon {
    private String nome;
    private String tipo;
    private int nivel;

    public Pokemon(String nome, String tipo, int nivel) {
        this.nome = nome;
        this.tipo = tipo;
        this.nivel = nivel;
    }

    @Override
    public String toString() {
        return nome + " é um Pokémon do tipo " + tipo + " de nível " + nivel + ".";
    }
}

class generatePokemon {
    private List<String> nomes;
    private List<String> tipos;
    private Random random;

    public generatePokemon() {
        nomes = List.of("Pikachu", "Charmander", "Bulbasaur", "Squirtle", "Eevee", "Snorlax", "Jigglypuff", "Gengar", "Machop", "Pidgey");
        tipos = List.of("Elétrico", "Fogo", "Água", "Planta", "Voador", "Normal", "Fantasma", "Lutador", "Psíquico", "Terrestre");
        random = new Random();
    }

    public Pokemon gerarPokemon() {
        String nome = nomes.get(random.nextInt(nomes.size()));
        String tipo = tipos.get(random.nextInt(tipos.size()));
        int nivel = 1 + random.nextInt(100);
        return new Pokemon(nome, tipo, nivel);
    }
}
