package lucas.frasson.ex3;

import java.time.LocalDateTime;
import java.util.UUID;

public class Tweet {
    private UUID tweetId;
    private String mensagem;
    private boolean editado;
    private LocalDateTime dataCriacao;

    public Tweet(UUID tweetId, String mensagem, boolean editado, LocalDateTime dataCriacao) {
        this.tweetId = tweetId;
        this.mensagem = mensagem;
        this.editado = editado;
        this.dataCriacao = dataCriacao;
    }

    public UUID getTweetId() {
        return tweetId;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public boolean isEditado() {
        return editado;
    }

    public void setEditado(boolean editado) {
        this.editado = editado;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
}
