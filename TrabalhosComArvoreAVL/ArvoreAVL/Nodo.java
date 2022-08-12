
public class Nodo {
	private int num;
	private int altura;
	private Nodo esquerda;
	private Nodo direita;
	
	public Nodo(int num) {
		this.num = num;
		this.altura = 0;
		this.esquerda = null;
		this.direita = null;
	}

	
	public void setNum(int num) {
		this.num = num;
	}

	public int getNum() {
		return num;
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
	
	
	
//	public int fatorBalanceamento() {
//		if (true) {
//			
//		}
//	}
}
