package lucas.frasson.ex4;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class Ex4Controller {

    private final Map<UUID, Filme> filmes = new LinkedHashMap<>();
    private final Map<UUID, List<Ingresso>> ingressosPorFilme = new LinkedHashMap<>();

    public Ex4Controller() {
        // Inicializa com alguns filmes de exemplo
        Filme f1 = new Filme(UUID.randomUUID(), "Matrix", "Ficção", 3, 0);
        Filme f2 = new Filme(UUID.randomUUID(), "O Senhor dos Anéis", "Aventura", 2, 0);
        filmes.put(f1.getId(), f1);
        filmes.put(f2.getId(), f2);
        ingressosPorFilme.put(f1.getId(), new ArrayList<>());
        ingressosPorFilme.put(f2.getId(), new ArrayList<>());
    }

    // CRUD de filmes
    @GetMapping("/filmes")
    public Collection<Filme> listarFilmes() {
        return filmes.values();
    }

    public static class NovoFilmeRequest {
        public String titulo;
        public String genero;
        public Integer capacidade;
    }

    @PostMapping("/filmes")
    public ResponseEntity<?> criarFilme(@RequestBody NovoFilmeRequest req) {
        if (req == null || req.titulo == null || req.titulo.isBlank() || req.genero == null || req.genero.isBlank() || req.capacidade == null || req.capacidade < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dados inválidos");
        }
        UUID id = UUID.randomUUID();
        Filme f = new Filme(id, req.titulo.trim(), req.genero.trim(), req.capacidade, 0);
        filmes.put(id, f);
        ingressosPorFilme.put(id, new ArrayList<>());
        return ResponseEntity.status(HttpStatus.CREATED).body(f);
    }

    public static class AtualizaFilmeRequest {
        public String titulo;
        public String genero;
        public Integer capacidade;
    }

    @PatchMapping("/filmes/{id}")
    public ResponseEntity<?> atualizarFilme(@PathVariable UUID id, @RequestBody AtualizaFilmeRequest req) {
        Filme f = filmes.get(id);
        if (f == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Filme não encontrado");
        if (req.titulo != null && !req.titulo.isBlank()) f.setTitulo(req.titulo.trim());
        if (req.genero != null && !req.genero.isBlank()) f.setGenero(req.genero.trim());
        if (req.capacidade != null) {
            if (req.capacidade < f.getAssentosOcupados()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Capacidade não pode ser menor que assentos ocupados");
            }
            f.setCapacidade(req.capacidade);
        }
        return ResponseEntity.ok(f);
    }

    @DeleteMapping("/filmes/{id}")
    public ResponseEntity<?> removerFilme(@PathVariable UUID id) {
        Filme removed = filmes.remove(id);
        ingressosPorFilme.remove(id);
        if (removed == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Filme não encontrado");
        return ResponseEntity.noContent().build();
    }

    // Ingressos
    public static class NovoIngressoRequest {
        public Integer assentoNumero; // opcional: se não vier, alocar próximo
    }

    @PostMapping("/filmes/{id}/ingressos")
    public ResponseEntity<?> comprarIngresso(@PathVariable UUID id, @RequestBody(required = false) NovoIngressoRequest req) {
        Filme f = filmes.get(id);
        if (f == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Filme não encontrado");
        if (f.getAssentosOcupados() >= f.getCapacidade()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Capacidade esgotada");
        }
        List<Ingresso> lista = ingressosPorFilme.computeIfAbsent(id, k -> new ArrayList<>());
        int assento;
        if (req != null && req.assentoNumero != null) {
            assento = req.assentoNumero;
            // Verifica se já está ocupado
            boolean ocupado = lista.stream().anyMatch(i -> i.getAssentoNumero() == assento);
            if (ocupado || assento < 1 || assento > f.getCapacidade()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Assento inválido ou já ocupado");
            }
        } else {
            // aloca próximo livre (1..capacidade)
            assento = 1;
            Set<Integer> ocupados = new HashSet<>();
            for (Ingresso i : lista) ocupados.add(i.getAssentoNumero());
            while (ocupados.contains(assento) && assento <= f.getCapacidade()) assento++;
            if (assento > f.getCapacidade()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Capacidade esgotada");
            }
        }
        UUID ingressoId = UUID.randomUUID();
        Ingresso ingresso = new Ingresso(ingressoId, id, assento);
        lista.add(ingresso);
        f.setAssentosOcupados(f.getAssentosOcupados() + 1);
        return ResponseEntity.status(HttpStatus.CREATED).body(ingresso);
    }

    @DeleteMapping("/filmes/{id}/ingressos/{ingressoId}")
    public ResponseEntity<?> devolverIngresso(@PathVariable UUID id, @PathVariable UUID ingressoId) {
        Filme f = filmes.get(id);
        if (f == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Filme não encontrado");
        List<Ingresso> lista = ingressosPorFilme.get(id);
        if (lista == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ingresso não encontrado");
        boolean removed = lista.removeIf(i -> i.getIngressoId().equals(ingressoId));
        if (!removed) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ingresso não encontrado");
        f.setAssentosOcupados(Math.max(0, f.getAssentosOcupados() - 1));
        return ResponseEntity.noContent().build();
    }
}
