package lucas.frasson.rpg;

import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Character Jogador = new Character("Lucas", 20, 5);
        List<Character> inimigos = new java.util.ArrayList<>(List.of());
        Character Ogro = new Character("Ogro", 25, 7);
        Character Goblin = new Character("Goblin", 10, 3);
        Character Lobo = new Character("Lobo", 15, 2);
        inimigos.add(Ogro);
        inimigos.add(Goblin);
        inimigos.add(Lobo);

        Jogador.atacar(inimigos.get((int)(Math.random() * 3)));
        while(inimigos.stream().anyMatch(inimigo -> inimigo.health > 0) && Jogador.health > 0) {
            for (Character inimigo : inimigos) {
                if (inimigo.health > 0) {
                    inimigo.atacar(Jogador);
                    System.out.println(inimigo.name + " atacou " + Jogador.name + "!");
                    System.out.println(Jogador.name + " tem " + Jogador.health + " de saúde.");
                }
            }

            if (Jogador.health > 0) {
                Random rand = new Random();
                Character inimigoAlvo = inimigos.get(rand.nextInt(inimigos.size()));
                if (inimigoAlvo.health > 0) {
                    Jogador.atacar(inimigoAlvo);
                    System.out.println(Jogador.name + " atacou " + inimigoAlvo.name + "!");
                    System.out.println(inimigoAlvo.name + " tem " + inimigoAlvo.health + " de saúde.");
                }
            }

            System.out.println("\nStatus após rodada:");
            System.out.println(Jogador.name + " tem " + Jogador.health + " de saúde.");
            for (Character inimigo : inimigos) {
                System.out.println(inimigo.name + " tem " + inimigo.health + " de saúde.");
            }
            System.out.println("-----------------------------");
        }

        if (Jogador.health <= 0) {
            System.out.println(Jogador.name + " foi derrotado!");
        } else {
            System.out.println("Todos os inimigos foram derrotados!");
        }
    }
}