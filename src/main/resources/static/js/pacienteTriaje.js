$(document).ready(function() {
	// Buscar paciente por cédula
	$('#buscarPacienteTriaje').click(function(event) {
		// Prevenir el submit del formulario
		event.preventDefault();

		var cedula = $('#cedula').val();
		console.log("Buscando paciente con cédula:", cedula); // Agregar este console.log
		$.ajax({
			url: '/triaje/buscar_paciente',
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

	$(document).ready(function() {
		$('#guardarTriaje').click(function(event) {
			// Evita el envío del formulario si hay campos vacíos
			var esValido = true;
			var mensaje = "";

			
			var frecuenciaCardiaca = $('#frecuenciaCardiaca').val();
			var frecuenciaRespiratoria = $('#frecuenciaRespiratoria').val();
			var nivelSaturacionOxigeno = $('#nivelSaturacionOxigeno').val();
			var presionArterial = $('#presionArterial').val();
			var temperaturaCorporal = $('#temperaturaCorporal').val();

		
			if (!isInteger(frecuenciaCardiaca)) {
				mensaje += "La frecuencia cardiaca debe ser un valor entero.\n";
				esValido = false;
			}
			if (!isInteger(frecuenciaRespiratoria)) {
				mensaje += "La frecuencia respiratoria debe ser un valor entero.\n";
				esValido = false;
			}
			if (!isInteger(nivelSaturacionOxigeno)) {
				mensaje += "El nivel de saturación de oxígeno debe ser un valor entero.\n";
				esValido = false;
			}
			if (!isInteger(presionArterial)) {
				mensaje += "La presión arterial debe ser un valor entero.\n";
				esValido = false;
			}
			if (!isInteger(temperaturaCorporal)) {
				mensaje += "La temperatura corporal debe ser un valor entero.\n";
				esValido = false;
			}

			// Para controlar datos correctos antes de guardar
			if (!esValido) {
				alert(mensaje);
				event.preventDefault();
			}
		});

		// Función para verificar si una cadena es un número entero
		function isInteger(value) {
			return /^\d+$/.test(value);
		}
	});

});
