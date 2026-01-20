package gerenciador_crud;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

public class codigoJava {

	static final int estoque = 100;
	static int totalProdutos = 0;
	static int id;
	public static final String RED = "\u001B[31m";
	static final String GREEN = "\u001B[32m";
	public static final String RESET = "\u001B[0m";

	static String[] nome = new String[estoque];
	static String[] categoria = new String[estoque];
	static String[] preco = new String[estoque];
	static String[] quantidade = new String[estoque];

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		String escolha;

		do {
			System.out.println(" ============ MENU ==============");
			System.out.println("| 1. Criar produto               |");
			System.out.println("| 2. Listar produto              |");
			System.out.println("| 3. Editar produto              |");
			System.out.println("| 4. Excluir produto             |");
			System.out.println("| 0. Sair do sistema             |");
			System.out.println(" ================================");
			System.out.print("Escolha uma opção: ");

			escolha = sc.nextLine();

			if (!escolha.matches("^[0-4]$")) {
				System.out.println(RED + "❌ Entrada inválida!" + RESET);
				continue;
			}

			// Sistema chama a funcão escolhida pelo usuário
			switch (escolha) {
			case "1" -> criarProduto();
			case "2" -> listarProduto();
			case "3" -> editarProduto();
			case "4" -> excluirProduto();
			case "0" -> System.err.println("\nEncerrando programa...\n");
			}

		} while (!escolha.equals("0"));
	}

	// ---------- CRIAR PRODUTO ---------------
	public static void criarProduto() {
		if (totalProdutos < estoque) {

			// repete até que as infos sejam validas
			// o looping só para quando a condição for falsa.
			do {
				System.out.print("Digite o nome do produto: ");
				nome[totalProdutos] = sc.nextLine();
			} while (!validarTexto(nome[totalProdutos]));

			do {
				System.out.print("Digite a categoria do produto: ");
				categoria[totalProdutos] = sc.nextLine();
			} while (!validarTexto(categoria[totalProdutos]));

			do {
				System.out.print("Digite a quantidade do produto: ");
				quantidade[totalProdutos] = sc.next();
			} while (!validarInteiro(quantidade[totalProdutos]));

			do {
				System.out.print("Digite o preço do produto: ");
				preco[totalProdutos] = sc.next();
			} while (!validarPreco(preco[totalProdutos]));

			sc.nextLine(); // alimenta o scanner

			totalProdutos++;
			System.out.println(GREEN + "\n✅ Produto criado com sucesso!\n" + RESET);
			historicoTxt(totalProdutos - 1, "✍️ CRIADO  "); // salva no txt o último produto criado
		} else {
			System.out.println(RED + "\n❌ Limite máximo de produtos atingido!\n" + RESET);
		}
	}

	// --------- LISTAR PRODUTO --------------
	public static void listarProduto() {
		System.out.println("\n========================== LISTAGEM DE PRODUTOS ======================");
		if (totalProdutos == 0) {
			System.out.println(RED + "Nenhum produto cadastrado!\n" + RESET);
		} else {
			for (int id = 0; id < totalProdutos; id++) {
				System.out.printf("| ID: %d | Nome: %s | Categoria: %s | Quantidade: %s | Preço: R$ %s | %n", id + 1,
						nome[id], categoria[id], quantidade[id], preco[id]);
				System.out.print("------------------------------------------" + "-------------------------------\n");
			}

			System.out.println();
		}

	}

	// ---------- EDITAR PRODUTOS ---------------
	public static void editarProduto() {
		if (totalProdutos == 0) {
			System.out.println(RED + "Nenhum produto cadastrado!\n" + RESET);
			return;
		} else {
			listarProduto();
		}

		String entrada;
		do {
			System.out.print("Digite o ID do produto que deseja editar: ");
			entrada = sc.nextLine();
		} while (!validarInteiro(entrada));

		id = Integer.parseInt(entrada) - 1;

		if (id >= 0 && id < totalProdutos) {
			String opcao;
			do {
				System.out.println("\n------ MENU DE EDIÇÃO ------");
				System.out.println("| 1. Nome                    |");
				System.out.println("| 2. Categoria               |");
				System.out.println("| 3. Quantidade              |");
				System.out.println("| 4. Preço                   |");
				System.out.println("| 5. Editar tudo             |");
				System.out.println("| 0. Voltar ao menu          |");
				System.out.println(" ---------------------------- ");
				System.out.println("o que deseja editar? ");
				opcao = sc.nextLine();

				if (!opcao.matches("^[0-5]$")) {
					System.out.println(RED + "❌ Entrada inválida!" + RESET);
					continue;
				}

				switch (opcao) {
				case "1":
					do {
						System.out.print("Novo nome: ");
						nome[id] = sc.nextLine();
					} while (!validarTexto(nome[id]));
					System.out.println(GREEN + "\n✅ Nome atualizado com sucesso!\n" + RESET);
					historicoTxt(id, "✏️ EDITADO ");
					break;

				case "2":
					do {
						System.out.print("Nova categoria: ");
						categoria[id] = sc.nextLine();
					} while (!validarTexto(categoria[id]));
					System.out.println(GREEN + "\n✅ Categoria atualizada com sucesso!\n" + RESET);
					historicoTxt(id, "✏️ EDITADO ");
					break;

				case "3":
					do {
						System.out.print("Nova quantidade: ");
						quantidade[id] = sc.nextLine();
					} while (!validarInteiro(quantidade[id]));
					System.out.println(GREEN + "\n✅ Quantidade atualizada com sucesso!\n" + RESET);
					historicoTxt(id, "✏️ EDITADO ");
					break;

				case "4":
					do {
						System.out.print("Novo preço: ");
						preco[id] = sc.nextLine();
					} while (!validarPreco(preco[id]));
					System.out.println(GREEN + "\n✅ Preço atualizado com sucesso!\n" + RESET);
					historicoTxt(id, "✏️ EDITADO ");
					break;

				case "5":
					do {
						System.out.print("Novo nome: ");
						nome[id] = sc.nextLine();
					} while (!validarTexto(nome[id]));

					do {
						System.out.print("Nova categoria: ");
						categoria[id] = sc.nextLine();
					} while (!validarTexto(categoria[id]));

					do {
						System.out.print("Nova quantidade: ");
						quantidade[id] = sc.nextLine();
					} while (!validarInteiro(quantidade[id]));

					do {
						System.out.print("Novo preço: ");
						preco[id] = sc.nextLine();
					} while (!validarPreco(preco[id]));
					System.out.println(GREEN + "\n✅ Produto editado com sucesso!\n" + RESET);
					historicoTxt(id, "✏️ EDITADO ");
					break;

				case "0":
					System.out.println("\n↩️ Voltando ao menu...\n");
					break;
				}

			} while (!opcao.equals("0"));

		} else {
			System.out.println(RED + "\n❌ Produto não encontrado!\n" + RESET);
		}
	}

	// ----------------- EXCLUIR PRODUTOS -------------------
	public static void excluirProduto() {
		if (totalProdutos == 0) {
			System.out.println(RED + "Nenhum produto cadastrado!\n" + RESET);
			return;
		} else {
			listarProduto();
		}

		String entrada;
		do {
			System.out.print("Digite o ID do produto que deseja excluir: ");
			entrada = sc.nextLine();
		} while (!validarInteiro(entrada));

		id = Integer.parseInt(entrada)- 1;

		if (id >= 0 && id < totalProdutos) {
			for (int i = id; i < totalProdutos - 1; i++) {
				nome[i] = nome[i + 1];
				categoria[i] = categoria[i + 1];
				quantidade[i] = quantidade[i + 1];
				preco[i] = preco[i + 1];
			}
			totalProdutos--;
			System.out.println(GREEN + "\n✅ Produto removido com sucesso!\n" + RESET);
			historicoTxt(id, "🚮 EXCLUIDO");
		} else {
			System.out.println(RED + "❌ Produto não encontrado!" + RESET);
		}
	}

	// VALIDAÇÃO DE TEXTO
	public static boolean validarTexto(String info) {
		if (!info.matches("^[a-zA-ZÀ-ÿ\\s]+$") || info.matches(".*(.)\\1{2,}.*")) {
			System.out.println(RED + "❌ Informação inválida! Use apenas letras e espaços." + RESET);
			return false;
		}
		return true;
	}

	// VALIDAÇÃO DE QUANTIDADE
	public static boolean validarInteiro(String valor) {
		if (!valor.matches("^[1-9][0-9]*$")) {
			System.out.println(RED + "❌ Valor inválido! Use apenas números inteiros." + RESET);
			return false;
		}
		return true;
	}

	// VALIDAÇÃO DE PREÇO
	public static boolean validarPreco(String valor) {
		if (!valor.matches("^[1-9][0-9]*(,[0-9]{1,2}|\\.[0-9]{1,2})?$")) {
			System.out.println(RED + "❌ Preço inválido! Use apenas números e vírgula ou ponto." + RESET);
			return false;
		}
		return true;
	}

	// ARMAZENA EM TEXTO
	public static void historicoTxt(int id, String acao) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("historico.txt", true))) {

			bw.write(acao + " | ID: " + (id + 1) + " | Nome: " + nome[id] + " | Categoria: " + categoria[id]
					+ " | Quantidade: " + quantidade[id] + " | Preço: R$ " + preco[id]);
			bw.newLine();

		} catch (Exception e) {
			System.out.println(RED + "Erro ao salvar histórico: " + e.getMessage() + RESET);
		}
	}

}
