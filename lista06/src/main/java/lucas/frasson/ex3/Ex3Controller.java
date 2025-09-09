package lucas.frasson.ex3;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estudantes")
public class Ex3Controller {

    @GetMapping("/{codigo}/{nome}")
    public Estudante buscarEstudante(@PathVariable Integer codigo, @PathVariable String nome) {

    }
}
