package com.teste.service;

import java.util.List;
import java.util.Optional;

import com.teste.controller.dto.filtro.EnderecoFiltro;
import com.teste.entity.Endereco;

public interface EnderecoService {

	Endereco saveEndereco(Endereco endereco);

	List<Endereco> findAllEndereco(EnderecoFiltro enderecoFiltro);

	Optional<Endereco> findEnderecoById(Long id);

	void deleteEnderecoById(Long id);

}