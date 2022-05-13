package br.com.alura.spring.data.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.specification.SpecificationFuncionario;

@Service
public class RelatorioFuncionarioDinamico {

	private final FuncionarioRepository funcionarioRepository;
	
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public RelatorioFuncionarioDinamico(FuncionarioRepository funcionarioRepository) {
		
		this.funcionarioRepository = funcionarioRepository;
		
	}
	
	public void inicial(Scanner scanner) {
		
		System.out.println("Digite o nome");
		String nomeString = scanner.next();
		
		if (nomeString.equalsIgnoreCase("NULL")) {
			nomeString = null;
		}

		System.out.println("Digite o CPF");
		String cpf = scanner.next();

		if (cpf.equalsIgnoreCase("NULL")) {
			cpf = null;
		}

		System.out.println("Digite o Salario");
		Double salario = scanner.nextDouble();

		if (salario == 0) {
			salario = null;
		}

		System.out.println("Digite o Data de Contratação");
		String data = scanner.next();

		LocalDate dataContratacao;
		
		if (data.equalsIgnoreCase("NULL")) {
			dataContratacao = null;
		} else {
			dataContratacao = LocalDate.parse(data,formatter);
		}


		List<Funcionario> funcionarios =funcionarioRepository.findAll(Specification.where(SpecificationFuncionario.nome(nomeString))
				.or(SpecificationFuncionario.cpf(cpf))
				.or(SpecificationFuncionario.salario(salario))
				.or(SpecificationFuncionario.dataContratacao(dataContratacao))
				);
		funcionarios.forEach(System.out::println);
	}
	
	
}
