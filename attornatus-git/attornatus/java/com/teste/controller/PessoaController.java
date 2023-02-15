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

import com.teste.controller.dto.filtro.PessoaFiltro;
import com.teste.entity.Pessoa;
import com.teste.service.imlp.PessoaServiceImpl;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

	@Autowired
	private PessoaServiceImpl pessoaServiceImpl;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Pessoa savePessoa(@RequestBody Pessoa pessoa) {
		return pessoaServiceImpl.savePessoa(pessoa);
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Pessoa getPessoaById(@PathVariable Long id) {
		return pessoaServiceImpl.findPessoaById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada."));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePessoa(@PathVariable Long id) {
		pessoaServiceImpl.findPessoaById(id).map(pessoa -> {
			pessoaServiceImpl.deletePessoaById(pessoa.getId());
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada."));
	}

	@PostMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarPessoa(@PathVariable Long id, Pessoa pessoa) {
		pessoaServiceImpl.findPessoaById(id).map(pessoaBase -> {
			pessoaServiceImpl.savePessoa(pessoa);
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada."));

	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Pessoa> getPessoas(PessoaFiltro pessoaFiltro) {
		return pessoaServiceImpl.findAllPessoa(pessoaFiltro);
	}

}
