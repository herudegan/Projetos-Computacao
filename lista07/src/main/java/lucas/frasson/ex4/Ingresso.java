package lucas.frasson.ex4;

import java.util.UUID;

public class Ingresso {
    private UUID ingressoId;
    private UUID filmeId;
    private int assentoNumero;

    public Ingresso(UUID ingressoId, UUID filmeId, int assentoNumero) {
        this.ingressoId = ingressoId;
        this.filmeId = filmeId;
        this.assentoNumero = assentoNumero;
    }

    public UUID getIngressoId() {
        return ingressoId;
    }

    public UUID getFilmeId() {
        return filmeId;
    }

    public int getAssentoNumero() {
        return assentoNumero;
    }
}
