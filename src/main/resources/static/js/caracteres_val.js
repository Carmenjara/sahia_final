function validarIdentificacion(event) {
	const pattern = /^[a-zA-Z0-9]*$/;
	if (!pattern.test(event.target.value)) {
		event.target.setCustomValidity("La identificación solo puede contener letras y números.");
	} else {
		event.target.setCustomValidity("");
	}
}

function validarNomAp(event) {
	const pattern = /^[a-zA-ZñÑáéíóúÁÉÍÓÚ]+$/;
	if (!pattern.test(event.target.value)) {
		event.target.setCustomValidity("Solo permite letras, incluida la ñ.");
	} else {
		event.target.setCustomValidity("");
	}
}