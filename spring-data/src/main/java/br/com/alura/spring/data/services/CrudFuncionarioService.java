package br.com.alura.spring.data.services;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.repository.FuncionarioRepository;

@Service
public class CrudFuncionarioService {
	
	private boolean system = true;
	private final FuncionarioRepository repository;

	public CrudFuncionarioService(FuncionarioRepository repository) {
		this.repository = repository;
	}
	
	public void inicial(Scanner scanner) {
		
		while (system) {
			System.out.println("Qual a ação de Funcionario voce deseja");
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

}
