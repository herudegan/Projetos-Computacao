package lucas.frasson.rpg;

public class Character {
    public String name;
    public Integer health;
    public Integer attack;

    public Character(String name, Integer health, Integer attack) {
        this.name = name;
        this.health = health;
        this.attack = attack;
    }

    public void receberDano(int dano) {
        this.health = health - dano;
        if (this.health <= 0){
            this.health = 0; // Apenas para não aparecer com um valor negativo ao printar o objeto
            System.out.println("Esse personagem morreu");
        } else {
            System.out.println("Esse personagem tomou " + dano + " de dano, deixando ele com " + this.health + " de vida.");
        }
    }
    public void atacar(Character alvo) {
        if (alvo.health <= 0){
            alvo.health = 0;
            System.out.println("Impossível atacar o alvo, ele já está morto.");
        } else {
            alvo.receberDano(this.attack);
        }
    }
}

