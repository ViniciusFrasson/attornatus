package com.teste.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.teste.controller.dto.filtro.EnderecoFiltro;
import com.teste.entity.Endereco;
import com.teste.service.imlp.EnderecoServiceImlp;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

	@Autowired
	private EnderecoServiceImlp enderecoServiceImlp;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Endereco saveEndereco(@RequestBody Endereco endereco) {
		return enderecoServiceImlp.saveEndereco(endereco);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Endereco> getAllEndereco(EnderecoFiltro enderecoFiltro) {
		return enderecoServiceImlp.findAllEndereco(enderecoFiltro);
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Endereco getEnderecoById(@PathVariable Long id) {
		return enderecoServiceImlp.findEnderecoById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereco não encontrada."));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteEndereco(@PathVariable Long id) {
		enderecoServiceImlp.findEnderecoById(id).map(endereco -> {
			enderecoServiceImlp.deleteEnderecoById(endereco.getId());
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereco não encontrada."));
	}

	@PostMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarEndereco(@PathVariable Long id, Endereco endereco) {
		enderecoServiceImlp.findEnderecoById(id).map(enderecoBase -> {
			enderecoServiceImlp.saveEndereco(endereco);
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereco não encontrada."));

	}

}