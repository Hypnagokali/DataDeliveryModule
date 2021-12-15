package de.inmed.DropzoneFileUpload.adapter.web.errorHandler;

import de.inmed.DropzoneFileUpload.application.in.CreateOrLoadDataDeliveryUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ErrorController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CreateOrLoadDataDeliveryUseCase.NoSuchDataDeliveryException.class)
    public void handleNoDataDeliveryException(HttpServletResponse response) {
    }

}
