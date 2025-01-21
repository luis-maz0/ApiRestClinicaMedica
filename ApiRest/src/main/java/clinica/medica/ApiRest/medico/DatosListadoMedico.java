package clinica.medica.ApiRest.medico;

public record DatosListadoMedico(
        String nombre,
        String especialidad,
        String email
) {
    public DatosListadoMedico(Medico medico) {
        this(medico.getNombre(), medico.getEspecialidad().toString(),medico.getEmail());
    }
}
