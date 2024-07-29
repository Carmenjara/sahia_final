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
                },
                empty: false
            }
        ],
        //para usar los botones   
        responsive: "true",
        select: {
            style: 'single'
        },
        rowId: 'staffId',
        dom: 'frtilp',
    });

    var table = $('#example').DataTable();

    $('#btn-cerrar-popup').click(function () {
        var oAll = new Array();
        var i = 0;
        $('#example tbody tr.selected').each(function () {

            var pos = table.row(this).index();
            var row = table.row(pos).data();
            oAll.push(row);
            
            var idpersonal = oAll[i][0];
            var nombres = oAll[i][2];
            var apellidos = oAll[i][3];
            var rango = oAll[i][4];
            
            var placa = document.getElementById("placa_ficha").value;
            var circuito = document.getElementById("circuito").value;
            var distrito = document.getElementById("distrito").value;
           
            var kilometraje = document.getElementById("km").value;
            //console.log(kilometraje);

            if (rango == "POLICIA") {
                rango = "POLI" ;
            } else if (rango == "CABO SEGUNDO") {
                rango = "CBOS" ;
            } else if (rango == "CABO PRIMERO") {
                rango = "CBOP" ;
            } else if (rango == "SARGENTO SEGUNDO") {
                rango = "SGOS" ;
            } else if (rango == "SARGENTO PRIMERO") {
                rango = "SGOP" ;
            } else if (rango == "SUBOFICIAL SEGUNDO") {
                rango = "SBOS" ;
            } else if (rango == "SUBOFICIAL PRIMERO") {
                rango = "SBOP" ;
            } else if (rango == "SUBOFICIAL MAYOR") {
                rango = "SBOM" ;
            } else if (rango == "SUBTENIENTE") {
                rango = "SBTE" ;
            } else if (rango == "TENIENTE") {
                rango = "TNTE" ;
                
            }
            
            var idvehiculo = document.getElementById("idvehiculo").value;
            $("#idvehiculo1").val(idvehiculo);
            
            $("#idpersonal").val(idpersonal);
            $("#km1").val(kilometraje);
            $("#responsable").val(rango + ' - ' + nombres + ' ' + apellidos);
            $("#prueba").val(rango + ' - ' + nombres + ' ' + apellidos);
            $("#detalle").val('SÍRVASE USTED REALIZAR LOS SIGUIENTES TRABAJOS PARA EL PATRULLERO EN REFERENCIA. ' + rango + '. ' + nombres + ' ' + apellidos);
            $("#asunto").val('ORDEN DE TRABAJO DEL VEHÍCULO POLICIAL DE PLACAS ' + placa + ' CON KILOMETRAJE ' + kilometraje + ' PERTENECIENTE AL DISTRITO ' + distrito + ' UPC ' + circuito + ' DE LA SUBZONA LOJA N11.');
            
        });
    });

    $('#btn-abrir-popup1').click(function () {
        
        var kilometraje = document.getElementById("km").value;
        $("#kmactual").val(kilometraje);
        
    });
    
    $('#btn-cerrar-popup1').click(function () {
    
    	var placa = document.getElementById("placa_ficha").value;
    	var kilometraje = document.getElementById("km").value;
        var kmactual = document.getElementById("kmactual").value;
        var circuito = document.getElementById("circuito").value;
        var distrito = document.getElementById("distrito").value;
        
        console.log("Kilometraje Ficha" + kilometraje);
        console.log("kilometraje nuevo" + kmactual);
        
        $("#km1").val(kmactual);
        
        if (kmactual < kilometraje) {
            alert("El Kilometraje INGRESADO debe ser mayor al ACTUAL") ;
        } else if (kmactual >= kilometraje){
        	$("#asunto").val('ORDEN DE TRABAJO DEL VEHÍCULO POLICIAL DE PLACAS ' + placa + ' CON KILOMETRAJE ' + kmactual + ' PERTENECIENTE AL DISTRITO ' + distrito + ' UPC ' + circuito + ' DE LA SUBZONA LOJA N11.');
            $("#km").val(kmactual);
        }
        
        
        
    });
    




});
