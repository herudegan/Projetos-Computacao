package lucas.frasson.ex1;

public class Jogador {
    private String nome;
    private String posicao;
    private Integer idade;

    public Jogador(String nome, String posicao, Integer idade) {
        this.nome = nome;
        this.posicao = posicao;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }
}
