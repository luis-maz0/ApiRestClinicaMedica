package clinica.medica.ApiRest.medico;

public record DatosListadoMedico(
        Long id,
        String nombre,
        String especialidad,
        String email
) {
    public DatosListadoMedico(Medico medico) {
        this(medico.getId(),medico.getNombre(), medico.getEspecialidad().toString(),medico.getEmail());
    }
}
