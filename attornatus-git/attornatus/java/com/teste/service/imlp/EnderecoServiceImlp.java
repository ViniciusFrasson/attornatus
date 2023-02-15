package com.teste.service.imlp;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.teste.controller.dto.filtro.EnderecoFiltro;
import com.teste.entity.Endereco;
import com.teste.entity.Pessoa;
import com.teste.repository.EnderecoRepository;
import com.teste.repository.PessoaRepository;
import com.teste.service.EnderecoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnderecoServiceImlp implements EnderecoService {

	private final EnderecoRepository enderecoRepository;
	private final PessoaRepository pessoaRepository;

	@Override
	public Endereco saveEndereco(Endereco endereco) {
		if (endereco.getEnderecoPrincipal().equalsIgnoreCase("S")) {
			Optional<Pessoa> pessoa = pessoaRepository.findById(endereco.getPessoa_id());
			for (int i = 0; i < pessoa.get().getEndereco().size(); i++) {
				if(endereco.getEnderecoPrincipal().equals(pessoa.get().getEndereco().get(i).getEnderecoPrincipal())) {
					endereco.setEnderecoPrincipal("N");
				}
			}
		}
		return enderecoRepository.save(endereco);
	}

	@Override
	public List<Endereco> findAllEndereco(EnderecoFiltro enderecoFiltro) {
		Endereco endereco = Endereco.builder().id(enderecoFiltro.getId()).logradouro(enderecoFiltro.getLogradouro())
				.cep(enderecoFiltro.getCep()).numero(enderecoFiltro.getNumero()).cidade(enderecoFiltro.getCidade())
				.enderecoPrincipal(enderecoFiltro.getEnderecoPrincipal()).pessoa_id(enderecoFiltro.getPessoa_id())
				.build();

		ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

		Example<Endereco> example = Example.of(endereco, exampleMatcher);

		return enderecoRepository.findAll(example);
	}

	@Override
	public Optional<Endereco> findEnderecoById(Long id) {
		return enderecoRepository.findById(id);
	}

	@Override
	public void deleteEnderecoById(Long id) {
		enderecoRepository.deleteById(id);

	}

}
