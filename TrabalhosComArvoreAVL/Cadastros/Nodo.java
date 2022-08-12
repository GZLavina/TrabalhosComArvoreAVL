import java.util.ArrayList;

//Gustavo Lavina e Rafael Facchin
public class Nodo {
	private long num;
	private String str;
	private int altura;
	//lista de endereços no cadastro
	private ArrayList<Integer> regs;
	private Nodo esquerda;
	private Nodo direita;
	
	public Nodo(long num, int reg) {
		this.num = num;
		this.str = null;
		this.altura = 0;
		this.regs = new ArrayList<Integer>();
		this.regs.add(reg);
		this.esquerda = null;
		this.direita = null;
	}
	
	public Nodo(String nome, int reg) {
		this.str = nome;
		this.altura = 0;
		this.regs = new ArrayList<Integer>();
		this.regs.add(reg);
		this.esquerda = null;
		this.direita = null;
	}

	public void insereReg(int reg) {
		this.regs.add(reg);
	}
	
	public void setNum(long num) {
		this.num = num;
	}

	public long getNum() {
		return num;
	}
	
	public void setStr(String str) {
		this.str = str;
	}
	
	public String getStr() {
		return str;
	}
	
	public ArrayList<Integer> getRegs() {
		return regs;
	}
	
	public void setAltura(int alt) {
		this.altura = alt;
	}

	public int getAltura() {
		return altura;
	}
	
	public Nodo getEsquerda() {
		return esquerda;
	}

	public void setEsquerda(Nodo esquerda) {
		this.esquerda = esquerda;
	}

	public Nodo getDireita() {
		return direita;
	}

	public void setDireita(Nodo direita) {
		this.direita = direita;
	}
}
