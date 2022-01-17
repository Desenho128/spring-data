package br.com.alura.spring.data.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;

@Service
public class RelatoriosService {

	private boolean system = true;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/mm/yyyy");
	private FuncionarioRepository funcionarioRepository;	
	
	public RelatoriosService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}

	public void inicial(Scanner scanner) {
		
		while (system) {
			System.out.println("Qual ação voce deseja");
			System.out.println("0 - Sair");
			System.out.println("1 - Busca Funcionario nome");
			System.out.println("2 - Busca Funcionario nome salario e data de Contratação");
			System.out.println("3 - Busca Funcionario data de Contratação");
			
			int action = scanner.nextInt();
			switch (action) {
			case 1:
				buscaFuncionarioNome(scanner);
				break;
			case 2:
				buscaFuncionarioNomeSalarioMaiorData(scanner);
				break;
			case 3:
				buscaFuncionarioDataContratacao(scanner);
				break;
			default:
				system = false;
				break;
			}
		}

	}
	
	private void buscaFuncionarioNome(Scanner scanner) {
		System.out.println("Qual nome deseja pesquisar ?");
		String nome = scanner.next();
		List<Funcionario> list = funcionarioRepository.findByNome(nome);
		list.forEach(System.out::println);
	}
	
	private void buscaFuncionarioNomeSalarioMaiorData(Scanner scanner) {
		System.out.println("Qual nome deseja pesquisar ?");
		String nome = scanner.next();
		System.out.println("Qual data contratação deseja pesquisar ?");
		String data = scanner.next();
		LocalDate localDate = LocalDate.parse(data,formatter);
		
		System.out.println("Qual salario deseja pesquisar ?");
		Double salario = scanner.nextDouble();
		
		List<Funcionario> list = funcionarioRepository.findNomeSalarioMaiorDataContratacao(nome, salario, localDate);
		
		list.forEach(System.out::println);

	
	}
	private void buscaFuncionarioDataContratacao(Scanner scanner) {
		
		System.out.println("Qual data contratação deseja pesquisar ?");
		String data = scanner.next();
		LocalDate localDate = LocalDate.parse(data,formatter);
		List<Funcionario> list = funcionarioRepository.findDataContratacaoMaior(localDate);
		list.forEach(System.out::println);

		
	}
	
	
	
}
