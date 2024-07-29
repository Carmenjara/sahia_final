$(document).ready(function() {
	
		/*Edición Vinculacion Cantón - Distrito */
    	$('#comboboxProvincia').on('change',function() {
    		var provinciaId = $(this).val();
    		$.ajax({
    			type : 'GET',
    			url : '/descentralizacion/loadCantonesbyProvincia/'+ provinciaId,
    			success : function(result) {
    				var result = JSON.parse(result);
    				var s = '';
    				for (var i = 0; i < result.length; i++) {
    					s += '<option value="' + result[i].id + '">'+ result[i].nombre + '</option>';
    				}
    				$('#comboboxCanton').html(s);
    				/*$('#comboboxCanton').html(s).igCombo({
    		            width: 360,
    		            textKey: "Name",
    		            valueKey: "Id",
    		            multiSelection: {
    		                enabled: true,
    		                showCheckboxes: true
    		            },
    		            dropDownOrientation: "top"
    		        });*/
    			}
    		});
    	});
    	
    	$('#comboboxCanton').on('change', function(){
            var cod = document.getElementById("comboboxCanton").value;
            $("#idCant").val(cod);
            
        });
	
    	
    	/*Vinculacion Cantón - Distrito */
    	$('#comboboxProvinciaVin').on('change',function() {
    		var provinciaId = $(this).val();
    		$.ajax({
    			type : 'GET',
    			url : '/descentralizacion/loadCantonesbyProvincia/'+ provinciaId,
    			success : function(result) {
    				var result = JSON.parse(result);
    				var s = '';
    				for (var i = 0; i < result.length; i++) {
    					s += '<option value="' + result[i].id + '">'+ result[i].nombre + '</option>';
    				}
    				$('#comboboxCantonVin').html(s);
    				/*$('#comboboxCanton').html(s).igCombo({
    		            width: 360,
    		            textKey: "Name",
    		            valueKey: "Id",
    		            multiSelection: {
    		                enabled: true,
    		                showCheckboxes: true
    		            },
    		            dropDownOrientation: "top"
    		        });*/
    			}
    		});
    	});
    	
    	$('#comboboxCantonVin').on('change', function(){
            var cod = document.getElementById("comboboxCantonVin").value;
            var idDis= document.getElementById("iddistrito").value;
            $("#idDistr").val(idDis);
            $("#idCant").val(cod);
            
        });
    	
    	$('#comboboxRoles').on('change', function(){
            var idUsuario = document.getElementById("id").value;
            $("#idUsuario").val(idUsuario);
            
        });
    	
    	
    	
    	
		$('#submit').click(function(){
			var select = document.getElementById("comboboxCanton").value;
	        var idDis= document.getElementById("distritoId").value;
	        
		        $("#idDistr").val(idDis);
		        $("#idCant").val(select);
	    });
		
		/*$('#submitP').click(function(){
			var selectP = document.getElementById("comboboxParroquia").value;
	        var idCir= document.getElementById("circuitoId").value;
	        
		        $("#idCir").val(idCir);
		        $("#idParr").val(selectP);
	    });*/
		
		/* Edicion Parroquia - Circuito */
		$('#comboboxParroquia').on('change', function(){
			var selectP = document.getElementById("comboboxParroquia").value;
            $("#idParr").val(selectP);
            
        });
		
		/* Vinculacion Parroquia - Circuito */
		$('#comboboxParroquiaVin').on('change', function(){
			var selectP = document.getElementById("comboboxParroquiaVin").value;
            $("#idParr").val(selectP);

            var idCir= document.getElementById("idcircuito").value;
            $("#idCir").val(idCir);
            
        });
});