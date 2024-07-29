$(document).ready(function() {
	// Buscar paciente por cédula
	$('#buscarPaciente').click(function() {
		var cedula = $('#cedula').val();
		console.log("Buscando paciente con cédula:", cedula); // Agregar este console.log
		$.ajax({
			url: '/citamedica/buscar_paciente',
			type: 'get',
			data: { cedula: cedula },
			success: function(response) {
				console.log("Respuesta del servidor:", response); // Agregar este console.log
				if (response.paciente) {
					$('#pacienteInfo').show();
					$('#pacienteInfo span').text(response.paciente.persona.nombres + ' ' + response.paciente.persona.apellidos);
					$('input[name="paciente.id"]').val(response.paciente.id);
					$('#errorPaciente').hide();
				} else {
					$('#pacienteInfo').hide();
					$('#errorPaciente').show().text(response.error);
				}
			},
			error: function(xhr, status, error) {
				console.error("Error en la solicitud AJAX:", error); // Agregar este console.error
			}
		});
	});

	// Actualizar lista de médicos al seleccionar especialidad
	$('#especialidad').change(function() {
		var especialidadId = $(this).val();
		if (especialidadId != 0) {
			$.ajax({
				url: '/citamedica/medicosPorEspecialidad',
				data: { especialidadId: especialidadId },
				success: function(data) {
					var medicoSelect = $('#medico');
					medicoSelect.empty();
					medicoSelect.append('<option value="0">Seleccione un médico</option>');
					$.each(data, function(index, medico) {
						medicoSelect.append('<option value="' + medico.id + '">' + medico.persona.nombres + ' ' + medico.persona.apellidos + '</option>');
					});
				}
			});
		} else {
			$('#medico').empty();
			$('#medico').append('<option value="0">Seleccione un médico</option>');
		}
	});

	// Cuando se seleccione un médico, obtener las fechas disponibles
	$('#medico').change(function() {
		var medicoId = $(this).val();
		if (medicoId != 0) {
			$.ajax({
				url: '/citamedica/fechasPorMedico',
				type: 'get',
				data: { estado: 1, medicoId: medicoId },
				success: function(response) {
					var fechaSelect = $('#fecha');
					fechaSelect.empty();
					fechaSelect.append('<option value="0">Seleccione una fecha</option>');
					response.forEach(function(fecha) {
						fechaSelect.append('<option value="' + fecha + '">' + fecha + '</option>');
					});
				},
				error: function(xhr, status, error) {
					console.error("Error en la solicitud AJAX para fechas:", error);
				}
			});
		}
	});

	// Cuando se seleccione una fecha, obtener los horarios disponibles
	$('#fecha').change(function() {
		var medicoId = $('#medico').val();
		var fecha = $(this).val();
		if (fecha != 0 && medicoId != 0) {
			$.ajax({
				url: '/citamedica/horariosDisponibles',
				type: 'get',
				data: { medicoId: medicoId, fecha: fecha },
				success: function(response) {
					var horaSelect = $('#hora');
					horaSelect.empty();
					horaSelect.append('<option value="0">Seleccione una hora</option>');
					response.forEach(function(horario) {
						horaSelect.append('<option value="' + horario.id + '">' + horario.hora + '</option>');
					});

					// Actualizar el campo oculto horarioId cuando se seleccione una hora
					horaSelect.change(function() {
						var selectedId = $(this).val();
						$('#horarioId').val(selectedId);
					});
				},
				error: function(xhr, status, error) {
					console.error("Error en la solicitud AJAX para horarios:", error);
				}
			});
		}
	});
});
