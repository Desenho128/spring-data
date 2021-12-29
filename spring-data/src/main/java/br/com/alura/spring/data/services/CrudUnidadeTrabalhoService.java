package br.com.alura.spring.data.services;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.UnidadeTrabalho;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;

@Service
public class CrudUnidadeTrabalhoService {
	
	private boolean system = true;
	private final UnidadeTrabalhoRepository repository;
	
	public CrudUnidadeTrabalhoService(UnidadeTrabalhoRepository repository) {
		this.repository = repository;
	}
	
	public void inicial(Scanner scanner) {
		
		while (system) {
			System.out.println("Qual a ação de Unidade Trabalho voce deseja");
			System.out.println("0 - Sair");
			System.out.println("1 - Salvar");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Listar");
			System.out.println("4 - Apagar");
			
			int action = scanner.nextInt();
			switch (action) {
			case 1:
				salvar(scanner);
				break;
			case 2:
				atualizar(scanner);
				break;
			case 3:
				listar();
				break;
			case 4:
				apagar(scanner);
				break;

			default:
				system = false;
				break;
			}
		}
		
	}

	private void apagar(Scanner scanner) {
		
		System.out.println("digite o id da unidade que queira apagar");
		int id = scanner.nextInt();
		repository.deleteById(id);
		System.out.println("Unidade apagado com sucesso.");

		
	}

	private void listar() {
		
		System.out.println("Listando Unidades");
		Iterable<UnidadeTrabalho> unidades = repository.findAll();
		unidades.forEach(unidade -> System.out.println(unidade));		
		
	}

	private void atualizar(Scanner scanner) {
		
		System.out.println("Digite o Id da unidade de trabalho que deseja alterar");
		int id = scanner.nextInt();
		System.out.println("Digite a Descricao");
		String descricao = scanner.next();
		System.out.println("Digite a endereço");
		String endereco = scanner.next();
		UnidadeTrabalho trabalho = new UnidadeTrabalho();
		trabalho.setId(id);
		trabalho.setDescricao(descricao);
		trabalho.setEndereco(endereco);
		repository.save(trabalho);
		System.out.println("Unidade de trabalho Alterada com Sucesso");
		
		
		
	}

	private void salvar(Scanner scanner) {

		System.out.println("Digite a descrição da unidade de trabalho");
		String descricao = scanner.next();
		System.out.println("digite o Endereço da unidade de trabalho ");
		String endereco = scanner.next();
		UnidadeTrabalho trabalho = new UnidadeTrabalho();
		trabalho.setDescricao(descricao);
		trabalho.setEndereco(endereco);
		repository.save(trabalho);
		System.out.println("Unidade de trabalho Salvo com Sucesso");
		
	}

	
	
}
