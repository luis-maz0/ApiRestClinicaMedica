package clinica.medica.ApiRest.controller;

import clinica.medica.ApiRest.medico.*;
import jakarta.persistence.Transient;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public Page<DatosListadoMedico> listadoMedicos(@PageableDefault(size=2) Pageable paginacion){
        return medicoRepository.findAll(paginacion).map( medico -> new DatosListadoMedico(medico));
    }
    @PutMapping()
    @Transactional
    //Luego de la actualizacion,la transacción termina y hace un commit en la base de datos(usamos jpa puro).
    public void actualizarMedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico){
        Medico medico = medicoRepository.getReferenceById(datosActualizarMedico.id());
        medico.actualizarDatos(datosActualizarMedico);
    }
    //Se utiliza path variable
    @DeleteMapping("/{id}")
    @Transactional
    public void eliminarMedico(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        medicoRepository.delete(medico);
    }
}
