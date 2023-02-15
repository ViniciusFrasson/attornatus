package com.teste.controller.dto.filtro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoFiltro {

	private Long id;

	private String logradouro;

	private String cep;

	private Integer numero;

	private String cidade;

	private String enderecoPrincipal;

	private Long pessoa_id;

}
