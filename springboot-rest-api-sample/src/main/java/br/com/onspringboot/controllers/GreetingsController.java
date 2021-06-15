package br.com.onspringboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.onspringboot.model.Usuario;
import br.com.onspringboot.repository.UserRepository;
 

 
@RestController
public class GreetingsController {
    
	
	@Autowired //Injetando as dependecias
	private UserRepository userReporsitory;
	
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "Hello " + name + "!";
    }
    
    
    
    @RequestMapping(value = "/olamundo/{nome}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String metedoQueRetornaOlaMundo(@PathVariable String nome) {
    	
    	
    	Usuario u = new Usuario();
    	u.setNome(nome);
    	userReporsitory.save(u);
    	
    	return"Ola pessoal" + nome;
    }
    
    //Primeiro método de Api - Buscar todos
    @GetMapping(value="listatodos")
    @ResponseBody // retorna os dados para o corpo da resposta
    public ResponseEntity<List<Usuario>> listaUsuario(){//retorna uma lista de usuários.
    	List<Usuario> usuarios = userReporsitory.findAll();//executa a consulta
    	
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);//consulta tud do banco e retonar em json
    	
    }
    
    
    @PostMapping(value="salvar") //Mapeia a URL
    @ResponseBody //descrição da resposta
    public ResponseEntity<Usuario> Salvar(@RequestBody Usuario usuario){//recebe os dados para salvar
    	Usuario user =	userReporsitory.save(usuario);
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    	
    }
    
    @DeleteMapping(value="delete") //Mapeia a URL
    @ResponseBody //descrição da resposta
    public ResponseEntity<String> Salvar(@RequestParam Long idLong){//recebe os dados para salvar
     userReporsitory.deleteById(idLong);
    	
    	return new ResponseEntity<String>("User deletado com sucesso!", HttpStatus.OK);
    	
    }

    @GetMapping(value="buscaruserid") //Mapeia a URL
    @ResponseBody //descrição da resposta
    public ResponseEntity<Usuario> buscaruserid(@RequestParam(name="idLong") Long idLong){//recebe os dados para consulta
    	Usuario usuario = userReporsitory.findById(idLong).get();
    	
    	return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    	
    }
    
    
    @PutMapping(value="atualizar") //Mapeia a URL
    @ResponseBody //descrição da resposta
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario){//recebe os dados para salvar
    	
    	if(usuario.getId() == null) {
    		return new ResponseEntity<String>("Id não foi informado para atualização.", HttpStatus.OK);
    	}
    	
    	Usuario user =	userReporsitory.saveAndFlush(usuario);
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    	
    }
    
    @GetMapping(value="buscarnome") //Mapeia a URL
    @ResponseBody //descrição da resposta
    public ResponseEntity<List<Usuario>> buscarnome(  @RequestParam(name="name") String name){//recebe os dados para consulta
    	List<Usuario> usuario = userReporsitory.buscarPorNome(name.trim().toUpperCase());
    	
    	return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
    	
    }
    
    
    
    
    
}
