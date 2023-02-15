package com.teste.service.imlp;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.teste.controller.dto.filtro.PessoaFiltro;
import com.teste.entity.Pessoa;
import com.teste.repository.PessoaRepository;
import com.teste.service.PessoaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PessoaServiceImpl implements PessoaService {

	private final PessoaRepository pessoaRepository;

	@Override
	public Pessoa savePessoa(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}

	@Override
	public Optional<Pessoa> findPessoaById(Long id) {
		return pessoaRepository.findById(id);
	}

	@Override
	public void deletePessoaById(Long id) {
		pessoaRepository.deleteById(id);
	}

	@Override
	public List<Pessoa> findAllPessoa(PessoaFiltro pessoaFiltro) {
		Pessoa pessoa = Pessoa.builder()
				.id(pessoaFiltro.getId())
				.nome(pessoaFiltro.getNome())
				.dataNascimento(pessoaFiltro.getDataNascimento())
				.endereco(pessoaFiltro.getEndereco())
				.build();

		ExampleMatcher exampleMatcher = ExampleMatcher.matching()
				.withIgnoreNullValues()
				.withIgnoreCase()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

		Example<Pessoa> example = Example.of(pessoa, exampleMatcher);

		return pessoaRepository.findAll(example);
	}
}
