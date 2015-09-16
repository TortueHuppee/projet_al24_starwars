function loadModal()
{
	PF('changeAddress').show();
	$(".ui-widget-overlay").click(function()
	{
		PF('changeAddress').hide();
	});	
}

function loadModal2()
{
	PF('addAddress').show();
	$(".ui-widget-overlay").click(function()
	{
		PF('addAddress').hide();
	});	
}

jQuery(document).ready(function($){
	$('.switch :checkbox').change(function(e){
		var message = $(this).prop('checked') ? $(this).val() : 'off';
		});
});