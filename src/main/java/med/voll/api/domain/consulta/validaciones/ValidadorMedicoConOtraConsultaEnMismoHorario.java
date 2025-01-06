package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DatosReservaConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoConOtraConsultaEnMismoHorario implements ValidadorDeConsultas {

    @Autowired
    private ConsultaRepository repository;

    @Override
    public void validar(DatosReservaConsulta datos) {
        var medicoTieneOtraConsultaEnElMismoHorario = repository.existSByMedicoIdAndFecha(
                datos.idMedico(),
                datos.fecha());
        if (medicoTieneOtraConsultaEnElMismoHorario) {
            throw new ValidacionException("Medico ya tiene otra consulta para este horario");
        }
    }
}
