package br.com.serratec.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.entity.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, UUID>{

}
