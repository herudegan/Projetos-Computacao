package lucas.frasson.ex4;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private Integer id;
    private String nome;
    private List<Musica>  musicas = new ArrayList<>();

    public Playlist(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public void addMusic(Musica musica){
        musicas.add(musica);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }
}
