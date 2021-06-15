package br.com.onspringboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.onspringboot.model.Usuario;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {
	
	//Método busca por pedaço de nome...
	@Query(value = "SELECT u FROM Usuario u WHERE upper(trim(u.nome)) LIKE %?1%")
	List<Usuario> buscarPorNome(String name);
	
	
	

}
