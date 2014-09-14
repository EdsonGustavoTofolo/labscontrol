/**
 * Created by EdsonGustavo on 14/09/2014.
 */
/**
 * Fecha a dialog passada por parâmetro caso não haja erros na validação
 * @param xhr
 * @param status
 * @param args
 * @param dialogWidget - passar PF('nome_da_dialog_widget')
 * @param dialogId - passar o id da dialog
 */
function complete(xhr, status, args, dialogWidget, dialogId) {
    if (args.validationFailed || args.KEEP_DIALOG_OPENED) {
        jQuery('#'+dialogId).effect("bounce", {times : 4, distance : 20}, 100);
    } else {
        dialogWidget.hide();
    }
}
