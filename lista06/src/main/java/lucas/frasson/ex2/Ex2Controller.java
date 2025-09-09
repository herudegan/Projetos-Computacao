package lucas.frasson.ex2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jogador")
public class Ex2Controller {

    private final Ex2Service service;

    public Ex2Controller(Ex2Service service) {
        this.service = service;
    }

    @GetMapping("/{time}/{posicao}")
    public Jogador buscarJogador(@PathVariable String time, @PathVariable String posicao) {
        return service.jogador(time, posicao);
    }
}
