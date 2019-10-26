package br.com.dasp.examgeneratorfront.interceptor;

import br.com.dasp.examgeneratorfront.annotation.ExceptionHandler;
import br.com.dasp.examgeneratorfront.custom.CustomObjectMapper;
import br.com.dasp.examgeneratorfront.persistence.model.support.ErrorDetail;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.IOException;
import java.io.Serializable;

@Interceptor
@ExceptionHandler
public class ExceptionInterceptor implements Serializable {
    private final ExternalContext externalContext;

    @Inject
    public ExceptionInterceptor(ExternalContext externalContext) {
        this.externalContext = externalContext;
    }

    @AroundInvoke
    public Object invoke(InvocationContext context) throws IOException {
        Object result = null;
        try {
            result = context.proceed();
        } catch (Exception e) {
            if (e instanceof HttpClientErrorException) {
                HttpStatusCodeException httpStatusCodeException = (HttpStatusCodeException) e;
                ErrorDetail errorDetail = new CustomObjectMapper().readValue(httpStatusCodeException.getResponseBodyAsString(), ErrorDetail.class);
                addMessage(errorDetail.getMessage(), true);
            }else{
                e.printStackTrace();
            }
        }
        return result;
    }

    private void addMessage(String msg, boolean keepMessages) {

    final FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
    externalContext.getFlash().setKeepMessages(keepMessages);
    externalContext.getFlash().setRedirect(true);
    FacesContext.getCurrentInstance().addMessage(null, facesMessage);
}
}
