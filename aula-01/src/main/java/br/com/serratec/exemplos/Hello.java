package br.com.serratec.exemplos;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {
	
	@RequestMapping("/aulas")
	public String exemplo() {
		return "Hello World !";
	}


	@RequestMapping(value="/cursos")
	public String exemplo2() {
		return "cursos diversos";
	}

}