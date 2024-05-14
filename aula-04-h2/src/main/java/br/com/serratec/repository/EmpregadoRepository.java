package br.com.serratec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.entity.Empregado;

public interface EmpregadoRepository extends JpaRepository<Empregado, Long>{

}
