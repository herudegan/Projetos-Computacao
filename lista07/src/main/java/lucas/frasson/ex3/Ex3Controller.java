package lucas.frasson.ex3;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
public class Ex3Controller {

    private final List<Usuario> usuarios = new ArrayList<>();
    private final Map<UUID, List<Tweet>> tweetsPorUsuario = new LinkedHashMap<>();

    public Ex3Controller() {
        // Inicializa alguns usuários
        Usuario u1 = new Usuario(UUID.randomUUID(), "Ana", "ana@example.com");
        Usuario u2 = new Usuario(UUID.randomUUID(), "Bruno", "bruno@example.com");
        Usuario u3 = new Usuario(UUID.randomUUID(), "Carla", "carla@example.com");
        usuarios.addAll(List.of(u1, u2, u3));
        for (Usuario u : usuarios) {
            tweetsPorUsuario.put(u.getId(), new ArrayList<>());
        }
    }

    @GetMapping("/usuarios")
    public List<Usuario> listarUsuarios() {
        return usuarios;
    }

    @GetMapping("/usuarios/{id}/tweets")
    public ResponseEntity<?> listarTweets(@PathVariable UUID id) {
        List<Tweet> lista = tweetsPorUsuario.get(id);
        if (lista == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        return ResponseEntity.ok(lista);
        
    }

    public static class NovoTweetRequest {
        public String mensagem;
    }

    @PostMapping("/usuarios/{id}/tweets")
    public ResponseEntity<?> criarTweet(@PathVariable UUID id, @RequestBody NovoTweetRequest req) {
        if (req == null || req.mensagem == null || req.mensagem.isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mensagem inválida");
        }
        List<Tweet> lista = tweetsPorUsuario.get(id);
        if (lista == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        Tweet t = new Tweet(UUID.randomUUID(), req.mensagem.trim(), false, LocalDateTime.now());
        lista.add(t);
        return ResponseEntity.status(HttpStatus.CREATED).body(t);
    }

    public static class AtualizaTweetRequest {
        public String mensagem;
    }

    @PatchMapping("/usuarios/{id}/tweets/{tweetId}")
    public ResponseEntity<?> atualizarTweet(@PathVariable UUID id, @PathVariable UUID tweetId, @RequestBody AtualizaTweetRequest req) {
        if (req == null || req.mensagem == null || req.mensagem.isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mensagem inválida");
        }
        List<Tweet> lista = tweetsPorUsuario.get(id);
        if (lista == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        Optional<Tweet> opt = lista.stream().filter(t -> t.getTweetId().equals(tweetId)).findFirst();
        if (opt.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tweet não encontrado");
        Tweet t = opt.get();
        t.setMensagem(req.mensagem.trim());
        t.setEditado(true);
        return ResponseEntity.ok(t);
    }

    @DeleteMapping("/usuarios/{id}/tweets/{tweetId}")
    public ResponseEntity<?> removerTweet(@PathVariable UUID id, @PathVariable UUID tweetId) {
        List<Tweet> lista = tweetsPorUsuario.get(id);
        if (lista == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        boolean removed = lista.removeIf(t -> t.getTweetId().equals(tweetId));
        if (!removed) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tweet não encontrado");
        return ResponseEntity.noContent().build();
    }
}
