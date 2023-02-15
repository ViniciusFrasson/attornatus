package com.teste.service;

import java.util.List;
import java.util.Optional;

import com.teste.controller.dto.filtro.PessoaFiltro;
import com.teste.entity.Pessoa;

public interface PessoaService {

	Pessoa savePessoa(Pessoa pessoa);

	List<Pessoa> findAllPessoa(PessoaFiltro pessoaFiltro);

	Optional<Pessoa> findPessoaById(Long id);

	void deletePessoaById(Long id);
	

}