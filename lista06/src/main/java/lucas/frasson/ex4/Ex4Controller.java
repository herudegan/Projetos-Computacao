package lucas.frasson.ex4;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Ex4Controller {
    List<Playlist> playlists = new ArrayList<>();
    List<Musica> musicas = new ArrayList<>();

    @GetMapping("/playlists")
    public List<Playlist> getAllPlaylists(){
        return playlists;
    }
    @GetMapping("/musics")
    public List<Musica> getAllMusicas(){
        return musicas;
    }

    @PostMapping("/playlists")
    public Playlist addPlaylists(@RequestBody Playlist playlist){
        playlists.add(playlist);
        return playlist;
    }
    @PutMapping("/playlists/{id}")
    public void updatePlaylists(@PathVariable Integer id, @RequestBody Musica json){
        for(Playlist playlist : playlists){
            if(playlist.getId().equals(id)){
                System.out.println(json.getId());
            }
        }
    }
    @PostMapping("/musics")
    public Musica addMusicas(@RequestBody Musica musica){
        musicas.add(musica);
        return musica;
    }
}
