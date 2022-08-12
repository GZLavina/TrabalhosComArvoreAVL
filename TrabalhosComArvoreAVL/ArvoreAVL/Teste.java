import java.io.*;

public class Teste {
	
	public static void main(String[] args) {
		Arvore arvore = new Arvore();
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			
			System.out.println("Bem-vindo(a)!\nPara iniciar, use o comando \"i (valor)\" para inserir um valor na arvore!");
			String comando = br.readLine();
			
			while (!comando.startsWith("s")) {
				try {
					String[] arrayStr = comando.split(" ");
					switch (arrayStr[0].charAt(0)) {
						case 'i':
							arvore.insercao(Integer.parseInt(arrayStr[1]));
							System.out.println("Insira outro comando!\nComandos reconhecidos: i (valor) para insercao, b (valor) para busca, r (valor) para remocao, s para sair\n");
							comando = br.readLine();
							break;
						case 'b':
							arvore.busca(Integer.parseInt(arrayStr[1]));
							System.out.println("Insira outro comando!\nComandos reconhecidos: i (valor) para insercao, b (valor) para busca, r (valor) para remocao, s para sair\n");
							comando = br.readLine();
							break;
						case 'r':
							arvore.remocao(Integer.parseInt(arrayStr[1]));
							System.out.println("Insira outro comando!\nComandos reconhecidos: i (valor) para insercao, b (valor) para busca, r (valor) para remocao, s para sair\n");
							comando = br.readLine();
							break;
						default:
							System.out.println("Comando invalido! Insira outro comando!\nComandos reconhecidos: i (valor) para insercao, b (valor) para busca, r (valor) para remocao, s para sair\n");
							comando = br.readLine();
					}
					
				} catch (Exception e) {
					
					System.out.println("Comando invalido! Insira outro comando!\nComandos reconhecidos: i (valor) para insercao, b (valor) para busca, r (valor) para remocao, s para sair\n");
					comando = br.readLine();
					
				}
			}
			
			System.out.println("Programa encerrado!");
			
		} catch (IOException e) {
			
			System.out.println("Excecao de IO! Fim da execucao!");
			
		}
	}
}