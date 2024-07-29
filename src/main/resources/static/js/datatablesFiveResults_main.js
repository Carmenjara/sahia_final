$(document).ready(function () {
	$('#example').DataTable({
        language: {
            "lengthMenu": "Mostrar _MENU_ registros",
            "zeroRecords": "No se encontraron resultados",
            "info": "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
            "infoEmpty": "Mostrando registros del 0 al 0 de un total de 0 registros",
            "infoFiltered": "(filtrado de un total de _MAX_ registros)",
            "sSearch": "Buscar:",
            "oPaginate": {
                "sFirst": "Primero",
                "sLast": "Último",
                "sNext": "Siguiente",
                "sPrevious": "Anterior"
            },
            "sProcessing": "Procesando...",
        },
        "lengthMenu": [[5, 10, 20, -1], [5, 10, 20, "Todo"]],
        columnDefs: [
            {
                targets: 0,
                checkboxes: {
                    seletRow: "true"
                }
            }
        ],
        //para usar los botones   
        responsive: "true",
        select: {
            style: 'single'
        },
        rowId: 'staffId',
        dom: 'frtilp'
    });

    var table = $('#example').DataTable();

    $('#comboboxDistrito').on('change', function(){
		var distritoId = $(this).val();
		$.ajax({
			type: 'GET',
			url: '/demo/loadCircuitosByDistrito/' + distritoId,
			success: function(result) {
				var result = JSON.parse(result);
				var s = '';
				var option='<option value="0">---</option>';
				for(var i = 0; i < result.length; i++) {
					s += '<option value="' + result[i].id + '">' + result[i].nombre + '</option>';
				}
				$('#comboboxCircuito').html(option+s);
			}
		});
	});


	$('#comboboxCircuito').on('change', function(){
		var circuitoId = $(this).val();
		$.ajax({
			type: 'GET',
			url: '/demo/loadSubcircuitosByCircuito/' + circuitoId,
			success: function(result) {
				var result = JSON.parse(result);
				var s = '';
				var option='<option value="0">---</option>';
				var n = 8;
				var l = "seleccione"; 
				for(var i = 0; i < result.length; i++) {
					//s += '<option value="'+ n + '">'+ l +'</option>';'<option value="' + result[i].id + '">' + result[i].nombre + '</option>';
					s += '<option value="' + result[i].id + '">' + result[i].nombre + '</option>';
				}
				$('#comboboxSubcircuito').html(option+s);
			}
		});
	});
	
  $('#comboboxSubcircuito').on('change', function(){
        var oAll = new Array ();
        var oAll1 = new Array ();
        var i = 0;
        var text = "";
        $('#example tbody tr.selected').each(function () {
            var cod = document.getElementById("comboboxSubcircuito").value;
            var pos = table.row(this).index();
            var row = table.row(pos).data();
            oAll.push(row);
            console.log('Id Policia: '+ oAll[i][0] + ' Id Subcircuito: ' + cod);
            $("#id_personal").val(oAll[i][0]);
            $("#id_sc").val(cod);

            i++;
        });
    });

});
