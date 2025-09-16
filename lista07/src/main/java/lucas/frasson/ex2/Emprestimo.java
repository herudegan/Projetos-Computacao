package lucas.frasson.ex2;

import java.time.LocalDateTime;
import java.util.UUID;

public class Emprestimo {
    private UUID emprestimoId;
    private UUID livroId;
    private String usuarioId;
    private LocalDateTime dataEmprestimo;

    public Emprestimo(UUID emprestimoId, UUID livroId, String usuarioId, LocalDateTime dataEmprestimo) {
        this.emprestimoId = emprestimoId;
        this.livroId = livroId;
        this.usuarioId = usuarioId;
        this.dataEmprestimo = dataEmprestimo;
    }

    public UUID getEmprestimoId() {
        return emprestimoId;
    }

    public UUID getLivroId() {
        return livroId;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public LocalDateTime getDataEmprestimo() {
        return dataEmprestimo;
    }
}