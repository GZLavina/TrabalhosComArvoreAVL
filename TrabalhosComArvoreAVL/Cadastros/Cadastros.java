import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

// Gustavo Lavina e Rafael Facchin
public class Cadastros {
	private ArrayList<Pessoa> cadastros;
	private AVL cpf;
	private AVL nome;
	private AVL data;
	private int seqRegistros;
	
	public Cadastros() {
		this.cadastros = new ArrayList<Pessoa>();
		this.cpf = new AVL();
		this.nome = new AVL();
		this.data = new AVL();
		this.seqRegistros = 0;
	}
	
	public void leArquivo(String arquivo) {
		try (BufferedReader in = new BufferedReader(new FileReader(arquivo))){
			
			int linhasErradas = 0;
			String linha;
			while ((linha = in.readLine()) != null) {
				String[] campos = divideEVerifica(linha);
				if (campos != null) {
					long cpfP = Long.parseLong(campos[0]);
					this.cadastros.add(new Pessoa(cpfP, Long.parseLong(campos[1]), campos[2], campos[3], campos[4]));
					this.cpf.insercao(cpfP, this.seqRegistros);
					this.nome.insercao(campos[2].toUpperCase(), this.seqRegistros);
					this.data.insercao(converteData(campos[3]), this.seqRegistros);
					this.seqRegistros++;
				} else {
					linhasErradas++;
				}
			}
			
			System.out.println("Arquivo " + arquivo + " lido! " + linhasErradas + " linhas erradas encontradas!");
			
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado!");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Arquivo mal formatado!");
		} catch (Exception e) {
			System.out.println("Erro.");
			e.printStackTrace();
		}
	}
	
	public String[] divideEVerifica(String linha) {
		String[] campos = linha.split(";");
		try {
			
			if (campos[0].length() != 11 || campos[1].length() > 10 ||
					campos[2].split(" ").length < 2 || converteData(campos[3]) == null || 
					campos[4].isEmpty()) {
				return null;
			}
			
		} catch (Exception e) {
			return null;
		}
		
		return campos;
	}
	
	// verifica e transforma em AAAA/MM/DD para facilitar a comparacao
	public String converteData(String data) {	
		String[] arr = data.split("/");
		if (arr[0].length() == 2 && arr[1].length() == 2 && arr[2].length() == 4)
			return arr[2] + "/" + arr[1] + "/" + arr[0];
		else
			return null;
	}
	
	public void buscaCPF(long cpf) {
		int reg = this.cpf.buscaCPF(cpf);
		if (reg != -1)
			System.out.println("Registro encontrado:\n" + cadastros.get(reg));
		else
			System.out.println("Não há registro desse CPF!");
	}
	
	public void buscaData(String min, String max) {
		min = converteData(min);
		max = converteData(max);
		ArrayList<Integer> regs = null; 
		if (min != null && max != null)
				regs = this.data.buscaData(min, max);
		if (regs == null) {
			System.out.println("Nenhum registro encontrado neste intervalo!");
			return;
		}
		
		System.out.println(regs.size() + " registro(s) encontrado(s) neste intervalo:");
		for (Integer i : regs) {
			System.out.println(this.cadastros.get(i));			
		}
	}
	
	// obtem a string limite para o algoritmo de busca,
	// aumentando o valor original do ultimo caracter em 1.
	public void buscaNome(String str) {
		str = str.toUpperCase();
		String limite = str.substring(0, str.length()-1) + ((char) (str.charAt(str.length()-1) + 1));
		ArrayList<Integer> regs = this.nome.buscaNome(str, limite);
		if (regs == null) {
			System.out.println("Nenhum registro encontrado iniciando com esta string!");
			return;
		}
		
		System.out.println(regs.size() + " registro(s) encontrado(s) iniciando com esta string:");
		for (Integer i : regs) {
			System.out.println(this.cadastros.get(i));			
		}
	}
}
