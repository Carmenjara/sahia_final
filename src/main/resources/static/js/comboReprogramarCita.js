$(document).ready(function() {
	var medicoId = $('#medicoId').val();

	if (medicoId != 0) {
		// Obtener las fechas disponibles del médico
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

	// Cuando se seleccione una fecha, obtener los horarios disponibles
	$('#fecha').change(function() {
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