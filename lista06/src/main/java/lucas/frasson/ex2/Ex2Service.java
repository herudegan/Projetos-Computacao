package lucas.frasson.ex2;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class Ex2Service {
    public Jogador jogador(String time, String posicao) {
        List<String> nome = List.of("Lucas","Vitor","Vinicius","Ramon");
        List<String> sobrenome = List.of("Frasson", "Tinelli", "Santos", "Rosa");
        Random random = new Random();

        return new Jogador(nome.get(random.nextInt(nome.size())),
                                sobrenome.get(random.nextInt(sobrenome.size())),
                                random.nextInt(16, 27),
                                time, posicao);
    }
}
