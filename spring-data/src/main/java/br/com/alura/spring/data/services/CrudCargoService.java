package br.com.alura.spring.data.services;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;

@Service
public class CrudCargoService {

	private boolean system = true;
	private final CargoRepository cargoRepository;

	public CrudCargoService(CargoRepository cargoRepository) {
		this.cargoRepository = cargoRepository;
	}
	
	public void inicial(Scanner scanner) {
		
		while (system) {
			System.out.println("Qual a ação de cargo voce deseja");
			System.out.println("0 - Sair");
			System.out.println("1 - Salvar");
			System.out.println("2 - Atualizar");
1			System.out.println("3 - Listar");
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

		System.out.println("digite o id do cargo que queira apagar");
		int id = scanner.nextInt();
		cargoRepository.deleteById(id);
		System.out.println("Cargo apagado com sucesso.");
	}

	private void listar() {

		System.out.println("Listando Cargos");
		Iterable<Cargo> cargos = cargoRepository.findAll();
		cargos.forEach(cargo -> System.out.println(cargo));
		
	}

	private void salvar(Scanner scanner) {
		
		System.out.println("Descrição do cargo");
		String descricao = scanner.next();
		Cargo cargo = new Cargo();
		cargo.setDescricao(descricao);
		cargoRepository.save(cargo);
		System.out.println("Cargo Salvo com Sucesso");
		
	}
	
	private void atualizar(Scanner scanner) {
		
		System.out.println("Qual o Id que deseja Atualizar");
		int id = scanner.nextInt();
		System.out.println("Qual a nova Descrição");
		String descricao = scanner.next();
		
		Cargo cargo = new Cargo();
		cargo.setId(id);
		cargo.setDescricao(descricao);
		cargoRepository.save(cargo);
		System.out.println("Cargo atualizado com sucesso");
		
	}
	
	
}
