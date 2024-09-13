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
});
