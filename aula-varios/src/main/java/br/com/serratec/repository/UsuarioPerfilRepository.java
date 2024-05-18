package br.com.serratec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.entity.UsuarioPerfil;

public interface UsuarioPerfilRepository extends JpaRepository<UsuarioPerfil, Long> {
}
