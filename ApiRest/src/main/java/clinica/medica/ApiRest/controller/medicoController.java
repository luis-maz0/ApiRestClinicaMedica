package clinica.medica.ApiRest.controller;

import clinica.medica.ApiRest.medico.DatosRegistroMedico;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class medicoController {
    @PostMapping
    public void registrarMedico(@RequestBody DatosRegistroMedico dataRegistroMedico){
        System.out.println("Request llega correctamente");
        System.out.println(dataRegistroMedico);
    }
}
