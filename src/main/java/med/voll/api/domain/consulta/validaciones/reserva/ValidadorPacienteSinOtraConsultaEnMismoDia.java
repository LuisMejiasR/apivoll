package med.voll.api.domain.consulta.validaciones.reserva;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DatosReservaConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteSinOtraConsultaEnMismoDia implements ValidadorDeConsultas {

    @Autowired
    private ConsultaRepository repository;

    @Override
    public void validar(DatosReservaConsulta datos) {
        var primerHorario = datos.fecha().withHour(7);
        var ultimoHorario = datos.fecha().withHour(18);
        var pacienteTieneOtraConsultaEnElDia = repository.existsByPacienteIdAndFechaBetween(
                datos.idPaciente(),
                primerHorario,
                ultimoHorario);
        if (pacienteTieneOtraConsultaEnElDia) {
            throw new ValidacionException("Paciente ya tiene una cita para este día");
        }
    }
}