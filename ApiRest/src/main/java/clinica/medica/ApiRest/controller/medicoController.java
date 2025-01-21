package clinica.medica.ApiRest.controller;

import clinica.medica.ApiRest.medico.DatosListadoMedico;
import clinica.medica.ApiRest.medico.DatosRegistroMedico;
import clinica.medica.ApiRest.medico.Medico;
import clinica.medica.ApiRest.medico.MedicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class medicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    public void registrarMedico(@RequestBody @Valid DatosRegistroMedico dataRegistroMedico){
        System.out.println("Request llega correctamente");
        System.out.println(dataRegistroMedico);
        medicoRepository.save( new Medico(dataRegistroMedico) );
    }
    @GetMapping()
    public Page<DatosListadoMedico> listadoMedicos(Pageable paginacion){
        return medicoRepository.findAll(paginacion).map( medico -> new DatosListadoMedico(medico));
    }
}
