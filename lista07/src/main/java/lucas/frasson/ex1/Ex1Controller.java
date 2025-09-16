package lucas.frasson.ex1;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping({"/time", ""})
public class Ex1Controller {
    private List<Jogador> jogadores = new ArrayList<>();

    public Ex1Controller() {
        for (int i = 0; i < 11; i++) {
            jogadores.add(new Jogador("Titular " + i, "Posição " + i, 20 + i));
        }
        for (int i = 11; i < 16; i++) {
            jogadores.add(new Jogador("Reserva " + i, "Posição " + i, 20 + i));
        }
    }

    @GetMapping({"/principal"})
    public List<Jogador> getTitulares() {
        return jogadores.subList(0, 11);
    }
    @GetMapping({"/reserva", "/reservas"})
    public List<Jogador> getReservas() {
        return jogadores.subList(11, jogadores.size());
    }

    // PUT /jogador/{posicao}?reserva=12
    @PutMapping({"/jogadores/{posicao}", "/jogador/{posicao}"})
    public String substituirJogador(@PathVariable int posicao, @RequestParam int reserva){
        if (posicao < 0 || posicao >= 11) {
            return "Posição inválida! Só é possível substituir titulares (0 a 10).";
        }
        if (reserva < 11 || reserva >= jogadores.size()) {
            return "Jogador reserva inválido!";
        }

        Jogador titular = jogadores.get(posicao);
        Jogador reservaJogador = jogadores.get(reserva);

        jogadores.set(posicao, reservaJogador);
        jogadores.set(reserva, titular);

        return "Substituição feita: " + titular.getNome() + " saiu, " +
                reservaJogador.getNome() + " entrou.";
    }
}
