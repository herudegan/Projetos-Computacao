package lucas.frasson.ex0;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Lista06Controller {
    @GetMapping("/")
    public String olaMundo(@RequestParam String nome) {
        return("Olá "+ nome + "!");
    }
}
