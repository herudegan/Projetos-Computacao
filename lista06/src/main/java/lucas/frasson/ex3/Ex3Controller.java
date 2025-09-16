package lucas.frasson.ex3;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/estudantes")
public class Ex3Controller {
    List<Estudante> estudantes = new ArrayList<>();

    @GetMapping
    public List<Estudante> getAllEstudantes() {
        return estudantes;
    }

    @GetMapping("/{codigo}")
    public Estudante getEstudante(@PathVariable Integer codigo) {
        return estudantes.stream().filter(estudante -> codigo.equals(estudante.getCodigo())).findFirst().orElse(null);
    }

    @PostMapping
    public Estudante buscarEstudante(@RequestBody Estudante estudante) {
        estudantes.add(estudante);
        return estudante;
    }
}
