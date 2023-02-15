package com.teste.controller.dto.filtro;

import java.util.List;

import com.teste.entity.Endereco;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaFiltro {

	private Long id;

	private String nome;

	private String dataNascimento;

	private List<Endereco> endereco;

}
