package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.DatosReservaConsulta;

import java.time.DayOfWeek;

public class ValidadoresFueraHorarioConsultas implements ValidadorDeConsultas{
    public void validar(DatosReservaConsulta datos) {
        var fechaConsulta = datos.fecha();
        var domingo = fechaConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var horarioAntesDeAperturaClinica = fechaConsulta.getHour() < 7;
        var horarioDespuesDeCierreClinica = fechaConsulta.getHour() > 18;

        if (domingo || horarioDespuesDeCierreClinica || horarioAntesDeAperturaClinica) {
            throw new ValidacionException("Horario seleccionado fuera del horario de atendimiento de la clinica");
        }
    }
}
