package clinica.medica.ApiRest.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class medicoController {
    @PostMapping
    public void registrarMedico(@RequestBody String dataJSON){
        System.out.println("Request llega correctamente");
        System.out.println(dataJSON);
    }
}
