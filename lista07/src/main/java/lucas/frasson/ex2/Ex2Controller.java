package lucas.frasson.ex2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
public class Ex2Controller {

    private final List<Livro> disponiveis = new ArrayList<>();
    private final Map<UUID, Emprestimo> emprestimos = new LinkedHashMap<>();
    private final Map<UUID, Livro> catalogo = new LinkedHashMap<>();

    public Ex2Controller() {
        Livro l1 = new Livro(UUID.randomUUID(), "Clean Code", "Robert C. Martin");
        Livro l2 = new Livro(UUID.randomUUID(), "Domain-Driven Design", "Eric Evans");
        Livro l3 = new Livro(UUID.randomUUID(), "Effective Java", "Joshua Bloch");
        for (Livro l : List.of(l1, l2, l3)) {
            catalogo.put(l.getId(), l);
            disponiveis.add(l);
        }
    }

    @GetMapping("/livros")
    public List<Livro> listarLivros() {
        return disponiveis;
    }

    @GetMapping("/emprestados")
    public Collection<Emprestimo> listarEmprestimos() {
        return emprestimos.values();
    }

    public static class NovoEmprestimoRequest {
        public UUID livroId;
        public String usuarioId;
    }

    @PostMapping("/emprestados")
    public ResponseEntity<?> criarEmprestimo(@RequestBody NovoEmprestimoRequest req) {
        if (req == null || req.livroId == null || req.usuarioId == null || req.usuarioId.isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dados inválidos");
        }
        // Verifica se o livro está disponível
        Optional<Livro> livroOpt = disponiveis.stream().filter(l -> l.getId().equals(req.livroId)).findFirst();
        if (livroOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não disponível");
        }
        Livro livro = livroOpt.get();
        // Remove dos disponíveis e cria empréstimo
        disponiveis.remove(livro);
        UUID emprestimoId = UUID.randomUUID();
        Emprestimo emp = new Emprestimo(emprestimoId, livro.getId(), req.usuarioId, LocalDateTime.now());
        emprestimos.put(emprestimoId, emp);
        return ResponseEntity.status(HttpStatus.CREATED).body(emp);
    }

    @DeleteMapping("/emprestados/{emprestimoId}")
    public ResponseEntity<?> devolver(@PathVariable UUID emprestimoId) {
        Emprestimo emp = emprestimos.remove(emprestimoId);
        if (emp == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empréstimo não encontrado");
        }
        Livro original = catalogo.get(emp.getLivroId());
        if (original != null) {
            disponiveis.add(original);
        } else {
            disponiveis.add(new Livro(emp.getLivroId(), "Desconhecido", "Desconhecido"));
        }
        return ResponseEntity.noContent().build();
    }
}
