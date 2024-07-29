$(document).ready(function(){

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
				$('#comboboxCircuito').html(s);
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
				for(var i = 0; i < result.length; i++) {
					s += '<option value="' + result[i].id + '">' + result[i].nombre + '</option>';
				}
				$('#comboboxSubcircuito').html(s);
			}
		});
	});
});