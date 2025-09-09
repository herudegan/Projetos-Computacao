package lucas.frasson.ex1;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Ex1Controller {
    @GetMapping("/")
    public String ex1(@RequestParam String clima, @RequestParam String estilo) {
        if(clima.equals("Quente") && estilo.equals("Tropical")){
            return "Havaí";
        }
        else if(clima.equals("Frio") && estilo.equals("Neve")){
            return "Russia";
        }
        else if(clima.equals("Quente") && estilo.equals("Deserto")){
            return "Jerusalém";
        }
        else if(clima.equals("Frio") && estilo.equals("Úmido")){
            return "Canadá";
        }
        else{
            return "Seu clima ou estilo não está no banco de dados";
        }
    }
}
