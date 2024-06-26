package br.com.serratec.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.model.Aluno;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

	private static List<Aluno> alunos= new ArrayList();
	
	//Bloco de inicialização estático
	static {
		alunos.add(new Aluno(1L,"Joaquim","j@gmail.com",23));
		alunos.add(new Aluno(2L,"Ana","a@gmail.com",34));
		alunos.add(new Aluno(3L,"Bruno","b@gmail.com",42));
		alunos.add(new Aluno(4L,"Carla","c@gmail.com",21));
	}
	
	@GetMapping
	public List<Aluno> listar(){
		return alunos;
	}
	
	//Inserir um aluno novo
	
	@PostMapping
	public Aluno inserir(@RequestBody Aluno aluno) {
		 alunos.add(aluno);
		 return aluno;
	}
	
	
}
