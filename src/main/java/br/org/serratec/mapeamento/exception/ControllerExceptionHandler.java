package br.org.serratec.mapeamento.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//anotacao q informa q a classe trata o erro dos controllers
@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		//getBindingResult vem da exception e contém as informações dos erros gerados na requisicao
		List<String> erros = new ArrayList<>();
		for(FieldError fe: ex.getBindingResult().getFieldErrors()) {
			//adiciona o campo junto da descricao da mensagem de erro na lista erros
			erros.add(fe.getField() + " : " + fe.getDefaultMessage());
		}
		
		ErroResposta erroResposta = new ErroResposta(status.value(), "Existem campos inválidos, confira o preenchimento", LocalDateTime.now(), erros);
		
		//pega os dados do método para lançar no body da exception
		return super.handleExceptionInternal(ex, erroResposta, headers, status, request);
	}
}
