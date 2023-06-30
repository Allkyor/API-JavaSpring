package br.edu.ally.contato.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.edu.ally.contato.entity.Contato;
import br.edu.ally.contato.repository.ContatoRepository;

@RestController
public class ContatoController {
	@Autowired
	private ContatoRepository contatoRepository;
	
	@PostMapping("/contato")
	public String saveContato(@RequestBody Contato contato) {
		contatoRepository.save(contato);
		return "Contato salvo com sucesso!";
	}
	@GetMapping("/contato")
	public List<Contato> getContatos(){
		return contatoRepository.findAll();
	}
	
	@PutMapping("/contato/{id}")
	public String updateContato(@PathVariable("id") Long id, @RequestBody Contato contatoUpdate) {
	    Optional<Contato> optionalContato = contatoRepository.findById(id);

	    if (optionalContato.isPresent()) {
	        Contato contato = optionalContato.get();

	        if (contatoUpdate.getNome() != null) {
	            contato.setNome(contatoUpdate.getNome());
	        }

	        if (contatoUpdate.getEmail() != null) {
	            contato.setEmail(contatoUpdate.getEmail());
	        }

	        contatoRepository.save(contato);
	        return "Contato atualizado com sucesso!";
	    } else {
	        return "ERRO 404: Contato não encontrado";
	    }
	}
	
	@DeleteMapping("/contato/{id}")
	public ResponseEntity<String> deleteContato(@PathVariable Long id) {
        Optional<Contato> optionalContato = contatoRepository.findById(id);
        if (optionalContato.isPresent()) {
            contatoRepository.deleteById(id);
            return ResponseEntity.ok("Contato excluído com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}



