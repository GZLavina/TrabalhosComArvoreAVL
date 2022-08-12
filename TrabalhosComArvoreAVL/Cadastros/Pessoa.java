
// Gustavo Lavina e Rafael Facchin
public class Pessoa {
	private long cpf;
	private long rg;
	private String nome;
	private String data;
	private String cidade;
	
	public Pessoa(long cpf, long rg, String nome, String data, String cidade) {
		this.cpf = cpf;
		this.rg = rg;
		this.nome = nome;
		this.data = data;
		this.cidade = cidade;
	}

	public long getCPF() {
		return cpf;
	}

	public void setCPF(long cpf) {
		this.cpf = cpf;
	}

	public long getRG() {
		return rg;
	}

	public void setRG(long rg) {
		this.rg = rg;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public String toString() {
        return String.format("Nome: %s; CPF: %011d; RG: %d; Data de nascimento: %s; Cidade natal: %s", 
                this.nome, this.cpf, this.rg, this.data, this.cidade);
    }
}
