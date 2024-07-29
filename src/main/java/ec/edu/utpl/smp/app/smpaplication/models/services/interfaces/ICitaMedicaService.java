package ec.edu.utpl.smp.app.smpaplication.models.services.interfaces;

import java.util.List;

import ec.edu.utpl.smp.app.smpaplication.models.entities.CitaMedica;

public interface ICitaMedicaService {

	public Iterable<CitaMedica> getAllCitas();

	public void save(CitaMedica citaMedica);

	public CitaMedica get(Integer id);

	public void delete(Integer id);

	public List<CitaMedica> getCitasPorMedicoDiarias(int medicoId);

	public List<CitaMedica> getCitasPorMedicoSemanales(int medicoId);

	public List<CitaMedica> getCitasPorMedicoMensuales(int medicoId);

	public List<CitaMedica> getCitasPorPaciente(int pacienteId);

	public int numeroCitasPorDia(int pacienteId);

}