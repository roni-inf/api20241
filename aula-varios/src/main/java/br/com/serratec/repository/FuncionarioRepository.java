package br.com.serratec.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.serratec.entity.Funcionario;



public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
/*	
	@Query(value="SELECT * FROM FUNCIONARIO WHERE salario BETWEEN :valorInicial AND :valorFinal"
			, nativeQuery = true)
	public Page<Funcionario> faixaSalarial(Double valorInicial,
			Double valorFinal, Pageable pageable);

	@Query(value = "SELECT * FROM funcionario WHERE nome ILIKE %:paramNome%", nativeQuery = true)
	public Page<Funcionario> buscarPorNome(String paramNome, Pageable pageable);
*/		
	
	public Page<Funcionario> findBySalarioBetween(Double valorInicial,
			Double valorFinal, Pageable pageable);
	
	public Page<Funcionario> findByNomeContainingIgnoreCase(String paramNome, Pageable pageable);
	
}
