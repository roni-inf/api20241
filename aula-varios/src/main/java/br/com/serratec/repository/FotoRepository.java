package br.com.serratec.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.entity.Foto;
import br.com.serratec.entity.Funcionario;




public interface FotoRepository extends JpaRepository<Foto, Long> {
	public Optional<Foto> findByFuncionario(Funcionario funcionario);
	
}
