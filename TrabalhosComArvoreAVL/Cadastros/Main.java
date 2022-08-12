import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//Gustavo Lavina e Rafael Facchin
public class Main {
	public static void main(String[] args) {
		Cadastros c = new Cadastros();
		System.out.println("Bem-vindo(a)!");
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			menu:while (true) {
				imprimeMenu();
				
				String opcao = br.readLine();
				
				switch (opcao) {
					case "1":
						System.out.print("Digite o nome do arquivo (por exemplo: teste.csv): ");
						String nomeArquivo = br.readLine();
						c.leArquivo(nomeArquivo);
						break;
						
					case "2":
						System.out.print("Digite o CPF a ser consultado: ");
						long cpf;
						
						while (true) {
							try {
								String strCpf = br.readLine();
								cpf = Long.parseLong(strCpf);		
								if (strCpf.length() != 11) throw new Exception();
								break;
							} catch (Exception e) {
								System.out.print("CPF inválido! Digite novamente: ");
							}
						}
						
						c.buscaCPF(cpf);
						break;
						
					case "3":
						System.out.print("Digite a String a ser consultada: ");
						String nome = br.readLine();
						c.buscaNome(nome);
						break;
						
					case "4":
						System.out.print("Digite a data inicial no formato DD/MM/AAAA: ");
						String dataInicial = br.readLine();
						System.out.print("Digite a data final no formato DD/MM/AAAA: ");
						String dataFinal = br.readLine();
						c.buscaData(dataInicial, dataFinal);
						break;
						
					case "0":
						System.out.println("Finalizando a aplicação...");
						break menu;
						
					default:
						System.out.println("Opção incorreta!");
						continue menu; // Repete o loop sem imprimir a mensagem de voltar.
				}
				
				System.out.println("Aperte ENTER para voltar ao menu");
				br.readLine();
			}
		} catch (IOException e) {
			System.out.println("Excecao de IO! Fim da execucao!");
		}
	}
	
	public static void imprimeMenu() {
		System.out.println("Digite:\n"
				+ "1 - Para fazer a leitura de um arquivo com cadastros.\n"
				+ "2 - Para fazer uma consulta por CPF.\n"
				+ "3 - Para fazer uma consulta por nome.\n"
				+ "4 - Para fazer uma consulta por data.\n"
				+ "0 - Para sair.\n");
	}
}
