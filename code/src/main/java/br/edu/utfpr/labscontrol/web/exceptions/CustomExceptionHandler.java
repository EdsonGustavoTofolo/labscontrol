package br.edu.utfpr.labscontrol.web.exceptions;

import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by EdsonGustavo on 07/03/2015.
 */
public class CustomExceptionHandler extends ExceptionHandlerWrapper {
    private ExceptionHandler wrapped;

    final FacesContext facesContext = FacesContext.getCurrentInstance();

    final Map requestMap = facesContext.getExternalContext().getRequestMap();

    final NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();

    public CustomExceptionHandler(ExceptionHandler ex) {
        this.wrapped = ex;
    }

    @Override
    public ExceptionHandler getWrapped() {
        return this.wrapped;
    }

    @Override
    public void handle() throws FacesException {
        final Iterator iterator = getUnhandledExceptionQueuedEvents().iterator();

        while (iterator.hasNext()) {
            ExceptionQueuedEvent event = (ExceptionQueuedEvent) iterator.next();
            ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();

            // Recupera a exceção do contexto
            Throwable exception = context.getException();

            // Tratar exceção
            try {
//                Aqui você poderia por exemploinstanciar as classes StringWriter e PrintWriter
//                StringWriter stringWriter = new StringWriter();
//                PrintWriter printWriter = new PrintWriter(stringWriter);
//                exception.printStackTrace(printWriter);
//                Por fim você pode converter a pilha de exceções em uma String
//                String message = stringWriter.toString();
//                Aqui você poderia enviar um email com a StackTrace
//                em anexo para a equipe de desenvolvimento
//                e depois imprimir a stacktrace no log
//                exception.printStackTrace();
                requestMap.put("exceptionMessage", exception.getMessage());

//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage (FacesMessage.SEVERITY_ERROR, "O sistema se recuperou de um erro inesperado.", ""));

                // Tranquiliza o usuário para que ele continue usando o sistema
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage (FacesMessage.SEVERITY_INFO, "Você pode continuar usando o sistema normalmente!", ""));

                // Seta a navegação para uma página padrão.
//                navigationHandler.handleNavigation(facesContext, null, "/restrict/home.faces");

                // Renderiza a pagina de erro e exibe as mensagens
                facesContext.renderResponse();
            } finally {
                iterator.remove();
            }

        }
        getWrapped().handle();
    }
}
