package clinica.medica.ApiRest.controller;

import clinica.medica.ApiRest.domain.medico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaMedico> registrarMedico(@RequestBody @Valid DatosRegistroMedico dataRegistroMedico,
                                          UriComponentsBuilder uriComponentsBuilder){
        System.out.println("Request llega correctamente");
        System.out.println(dataRegistroMedico);
        Medico medico =  medicoRepository.save( new Medico(dataRegistroMedico) );
        //Retornar 204 creado
        //Retornar URL para encontrar el medico creado: https://localhost:8080/medicos/xx
        DatosRespuestaMedico datosRespuestaMedico = new DatosRespuestaMedico(medico.getId(),
                medico.getNombre(),
                medico.getEspecialidad().toString());
        //Obtener URL del elemento creado
        URI url = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaMedico);
    }
    @GetMapping()
    public ResponseEntity<Page<DatosListadoMedico>>  listadoMedicos(@PageableDefault(size=2) Pageable paginacion){
        return ResponseEntity.ok(medicoRepository.findAll(paginacion).map( medico -> new DatosListadoMedico(medico)));
    }
    @PutMapping()
    @Transactional
    //Luego de la actualizacion,la transacción termina y hace un commit en la base de datos(usamos jpa puro).
    public ResponseEntity<DatosRespuestaMedico> actualizarMedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico){
        Medico medico = medicoRepository.getReferenceById(datosActualizarMedico.id());
        medico.actualizarDatos(datosActualizarMedico);
        //Retorna DTO
        return ResponseEntity.ok(new DatosRespuestaMedico(
                medico.getId(),
                medico.getNombre(),
                medico.getEspecialidad().toString())
        );
    }
    /*
    //***ELIMINACIÓN FISICA***
    //Se utiliza path variable
    @DeleteMapping("/{id}")
    @Transactional
    public void eliminarMedico(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        medicoRepository.delete(medico);
    }
    */
    //***ELIMINACION LOGICA***
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaMedico> eliminarMedico(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        medico.estaInactivo();
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/activos")
    public Page<DatosListadoMedico> listadoMedicosActivos(Pageable paginacion){
        return medicoRepository.findByActivoTrue(paginacion).map(medico -> new DatosListadoMedico(medico));
    }
    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaMedico> retornaDatosMedicos(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        var datosMedico = new DatosRespuestaMedico(medico.getId(),
                medico.getNombre(),
                medico.getEspecialidad().toString());
        return ResponseEntity.ok(datosMedico);
    }
}
