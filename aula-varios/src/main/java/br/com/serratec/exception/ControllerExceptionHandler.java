package br.com.serratec.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		List<String> erros = new ArrayList<>();
		for (FieldError f : ex.getBindingResult().getFieldErrors()) {
			erros.add(f.getField() + ":" + f.getDefaultMessage());
		}

		ErroResposta er = new ErroResposta(status.value(), "Existem campos inválidos", LocalDateTime.now(), erros);
		return super.handleExceptionInternal(ex, er, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		ErroResposta er = new ErroResposta(status.value(), "Campos inválidos foram inseridos, favor verificar",
				LocalDateTime.now(), null);
		return super.handleExceptionInternal(ex, er, headers, status, request);
	}

	@ExceptionHandler(EmailException.class) // excecção de E-mail
	protected ResponseEntity<Object> handleEmailException(EmailException e) {
		List<String> erros = new ArrayList<>();
		erros.add(e.getMessage());
		HttpStatus http = HttpStatus.BAD_REQUEST;
		ErroResposta er = new ErroResposta(http.value(), "Existem Erros", LocalDateTime.now(), erros);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(er);
	}

	@ExceptionHandler(ConfirmaSenhaException.class) // excecção de E-mail
	protected ResponseEntity<Object> handleConfirmaSenhaException(ConfirmaSenhaException e) {
		List<String> erros = new ArrayList<>();
		erros.add(e.getMessage());
		HttpStatus http = HttpStatus.BAD_REQUEST;
		ErroResposta er = new ErroResposta(http.value(), "Existem Erros", LocalDateTime.now(), erros);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(er);
	}

	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {

		List<String> erros = new ArrayList<>();
		erros.add(ex.getMessage());

		ErroResposta erroResposta = new ErroResposta(HttpStatus.NOT_FOUND.value(), "Recurso não encontrado",
				LocalDateTime.now(), erros);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erroResposta);
	}
}
