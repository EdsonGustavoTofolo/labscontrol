/**
 * 
 * CONFIGURA��O AJAX
 * 
 */
$.ajaxSetup ({
	type: 		"POST",
    cache: 		false,
    async: 		false,
    dataType:	"json"
});

var window_focus = true;

/**
 * 
 * CARREGA FUN��ES INICIAS
 * 
 */
$(document).ready(function(){
/*
	$(window).focus(function() {
	    window_focus = true;
	}).blur(function() {
		window_focus = false;
	});
	
	// ENTER INPUT
	$("#formRegistrar input").keyup(function(e) {
		if(e.keyCode == 13) {
			enviarRegistro();
		}
	});
	
	// MASCARA
	mascara();
	
	notificacoes();
	
	alertify.set({ labels: {
	    ok     : "Sim",
	    cancel : decodeURI("N%C3%A3o")
	} });
	
	$('#idGridAll').click(function () {    
	     $('input:checkbox').prop('checked', this.checked);    
	});
	
	$('form:first *:input[type!=hidden]:first').focus();
	
	$('textarea').parent().css('clear','both');
	$('textarea').parent().css('width','auto');
	
	$('.help').popover();*/
});
	
/**
 *
 * SELECIONA MENUS ACESSADOS
 *
 */
function selecionaMenu(menuTopo, subMenuTopo){
	$("#"+subMenuTopo).addClass('subMenuItemSelect');
	$("#"+menuTopo).addClass('menuItemSelect');
}

function notificacoes(){
	$.ajax({
		url: 		"/sistema/mensagem/notificacoes",
		async: 		false,
		success:	function(data){
			if(data.totalMensagem == '0'){
 				$('#countNotificacao').html('');
 			}else{
 				$('#countNotificacao').html(data.totalMensagem);
 			}
 			
 			$('#menuMsg').html(data.htmlMensagem);
		} 
	});
}

/**
*
* MASCARAS
*
*/
function mascara(){
	$(".cep").unmask();
	$(".cep").mask('99999-999');
	
	$(".ano").unmask();
	$(".ano").mask('9999');
	
	$(".telefone").unmask();
	$(".telefone").mask('(99) 9999-9999?9');
	
	$(".hora").unmask();
	$(".hora").mask('99:99:99');
	
	$(".cpf").unmask();
	$(".cpf").mask('999.999.999-99');
	
	$(".cnpj").unmask();
	$(".cnpj").mask('99.999.999/9999-99');
	
	$(".valor").unmask();
	$('.valor').maskMoney();
	
	$(".numero").unmask();
	$(".numero").maskMoney({thousands:'', decimal:'', precision: 0});
}

function alertas(){
	$.ajax({
		url: '/sistema/geral/echo-alert',
		success: function(data){
			if(data.mensagem != '' && data.mensagem != null){
				if(data.tipo == 'erro'){
					alertify.error(data.mensagem);
				}else if(data.tipo == 'sucesso'){
					alertify.success(data.mensagem);
				}else{
					alertify.log(data.mensagem);
				}
			}
		}
	});
}