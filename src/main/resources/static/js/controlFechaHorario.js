$(document).ready(function() {

    $('#createHorarioModal').on('show.bs.modal', function(event) {
        var button = $(event.relatedTarget); // Button that triggered the modal
        var id = button.data('id'); // Extract info from data-* attributes
        var modal = $(this);
        modal.find('.modal-body #medicoId').val(id);

        // Establecer la fecha mínima para incluir el día actual y deshabilitar fechas pasadas
        var today = new Date();
        var day = ("0" + today.getDate()).slice(-2);
        var month = ("0" + (today.getMonth() + 1)).slice(-2);
        var todayFormatted = today.getFullYear() + "-" + (month) + "-" + (day);

        document.getElementById("fecha").setAttribute('min', todayFormatted);
        document.getElementById("fecha").value = todayFormatted;
    });

    // Función para deshabilitar sábados y domingos
    $('#fecha').on('input', function() {
        var inputDate = new Date(this.value);
        var day = inputDate.getUTCDay(); // 0 (Domingo) to 6 (Sábado)

        if (day === 0 || day === 6) {
            alert("Sábados y domingos no están permitidos.");
            this.value = ''; // Clear the value
        }
    });

});
