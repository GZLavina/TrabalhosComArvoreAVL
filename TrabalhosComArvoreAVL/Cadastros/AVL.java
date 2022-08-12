import java.util.ArrayList;

//Integrantes da dupla: Gustavo Lavina e Rafael Facchin
//Todos os métodos para o trabalho anterior que não seriam utilizados neste, foram removidos.

public class AVL {
	private Nodo raiz;
		
	public AVL() {
		this.raiz = null;
	}
	
	// metodo que calcula o fator de balanceamento de um nodo
	public int fatorBalanceamento(Nodo n) {
		if (n == null)
			return 0;
		return alturaSemNull(n.getEsquerda()) - alturaSemNull(n.getDireita());
	}
	
	// metodo para tratar os casos onde nao eh folha
	public int alturaSemNull(Nodo n) {
		if (n == null)
			return -1;
		else
			return n.getAltura();
	}
	
	public Nodo rotacaoEsquerda(Nodo n) {
		Nodo direita = n.getDireita();
		Nodo aux = direita.getEsquerda();
		direita.setEsquerda(n);
		n.setDireita(aux);
		
		// recalcula a altura da sub-arvore do nodo que foi para a esquerda
		if (alturaSemNull(n.getEsquerda()) >= alturaSemNull(n.getDireita()))
			n.setAltura(alturaSemNull(n.getEsquerda()) + 1);
		else
			n.setAltura(alturaSemNull(n.getDireita()) + 1);
		
		// recalcula a altura da sub-arvore do nodo que subiu
		if (alturaSemNull(direita.getEsquerda()) >= alturaSemNull(direita.getDireita()))
			direita.setAltura(alturaSemNull(direita.getEsquerda()) + 1);
		else
			direita.setAltura(alturaSemNull(direita.getDireita()) + 1);
		
		return direita;
	}
	
	public Nodo rotacaoDireita(Nodo n) {
		Nodo esquerda = n.getEsquerda();
		Nodo aux = esquerda.getDireita();
		esquerda.setDireita(n);
		n.setEsquerda(aux);
		
		if (alturaSemNull(n.getEsquerda()) >= alturaSemNull(n.getDireita()))
			n.setAltura(alturaSemNull(n.getEsquerda()) + 1);
		else
			n.setAltura(alturaSemNull(n.getDireita()) + 1);
		
		if (alturaSemNull(esquerda.getEsquerda()) >= alturaSemNull(esquerda.getDireita()))
			esquerda.setAltura(alturaSemNull(esquerda.getEsquerda()) + 1);
		else
			esquerda.setAltura(alturaSemNull(esquerda.getDireita()) + 1);
		
		return esquerda;
	}
	
	// metodo para balancear uma subarvore
	public Nodo balanceia(Nodo n, int fator) {
		if (fator > 1) {
			// verifica se necessita rotacao dupla a direita
			if (fatorBalanceamento(n.getEsquerda()) < 0){
				n.setEsquerda(rotacaoEsquerda(n.getEsquerda()));	
			}
			
			n = rotacaoDireita(n);
			
		} else if (fator < -1) {
			// verifica se necessita rotacao dupla a esquerda
			if (fatorBalanceamento(n.getDireita()) > 0){
				n.setDireita(rotacaoDireita(n.getDireita()));	
			}
			
			n = rotacaoEsquerda(n);
		}
		
		return n;
	}
	
	// metodo "front-end" para inserir cpf
	public void insercao(long num, int reg) {
		this.raiz = insere(num, reg, this.raiz);
	}
	
	// metodo recursivo de insercao
	public Nodo insere(long num, int reg, Nodo n) {
		if (n == null) {
			return new Nodo(num, reg);
		} if (num > n.getNum()) {
			n.setDireita(insere(num, reg, n.getDireita()));
		} else if (num < n.getNum()) {
			n.setEsquerda(insere(num, reg, n.getEsquerda()));
		} else {
			// insere novo registro se a chave ja estava na arvore
			n.insereReg(reg);
			return n;
		}
		
		// atualiza altura do nodo sendo tratado
		// dessa forma, vai atualizar todos os nodos no caminho do que esta sendo inserido
		if (alturaSemNull(n.getEsquerda()) >= alturaSemNull(n.getDireita()))
			n.setAltura(alturaSemNull(n.getEsquerda()) + 1);
		else
			n.setAltura(alturaSemNull(n.getDireita()) + 1);
		
		// verifica o balanceamento 
		return balanceia(n, fatorBalanceamento(n));
	}
	
	// para inserir nome e data
	public void insercao(String str, int reg) {
		this.raiz = insere(str, reg, this.raiz);
	}
	
	public Nodo insere(String nome, int reg, Nodo n) {
		if (n == null) {
			return new Nodo(nome, reg);
		} if (nome.compareTo(n.getStr()) > 0) {
			n.setDireita(insere(nome, reg, n.getDireita()));
		} else if (nome.compareTo(n.getStr()) < 0) {
			n.setEsquerda(insere(nome, reg, n.getEsquerda()));
		} else {
			n.insereReg(reg);
			return n;
		} 
		if (alturaSemNull(n.getEsquerda()) >= alturaSemNull(n.getDireita()))
			n.setAltura(alturaSemNull(n.getEsquerda()) + 1);
		else
			n.setAltura(alturaSemNull(n.getDireita()) + 1);
 
		return balanceia(n, fatorBalanceamento(n));
	}
	
	public ArrayList<Integer> buscaNome(String min, String max) {
		ArrayList<Integer> regs = buscaNomePorIntervalo(this.raiz, min, max, new ArrayList<Integer>());
		if (regs.isEmpty())
			return null;
		else
			return regs;
	}
	
	public ArrayList<Integer> buscaNomePorIntervalo(Nodo n, String min, String max, ArrayList<Integer> regs){
		if (n == null)
			return regs;
		
		String str = n.getStr();
		// Se a string for maior ou igual a min e menor que max
		// pega os registros do nodo
		if (str.compareTo(min) >= 0 && str.compareTo(max) < 0) {
			regs.addAll(n.getRegs());
			//faz a mesma coisa para os dois filhos
			//como um unico array esta sendo usado, 
			//nao precisa acoplhar o retorno das chamadas recursivas
			buscaNomePorIntervalo(n.getDireita(), min, max, regs);
			buscaNomePorIntervalo(n.getEsquerda(), min, max, regs);
			return regs;
		} 
		// se for menor que o min, testa na direita
		else if (str.compareTo(min) < 0) {
			return buscaNomePorIntervalo(n.getDireita(), min, max, regs);
		} 
		// se for maior que o max, testa na esquerda
		else {
			return buscaNomePorIntervalo(n.getEsquerda(), min, max, regs);
		}
	} 
	
	public ArrayList<Integer> buscaData(String min, String max) {
		ArrayList<Integer> regs = buscaDataPorIntervalo(this.raiz, min, max, new ArrayList<Integer>());
		if (regs.isEmpty())
			return null;
		else
			return regs;
	}
	
	public ArrayList<Integer> buscaDataPorIntervalo(Nodo n, String min, String max, ArrayList<Integer> regs){
		if (n == null)
			return regs;
		
		String str = n.getStr();
		// Se a string for maior ou igual a min e menor que max
		// pega os registros do nodo
		if (str.compareTo(min) >= 0 && str.compareTo(max) <= 0) {
			regs.addAll(n.getRegs());
			//faz a mesma coisa para os dois filhos
			//como um unico array esta sendo usado, 
			//nao precisa acoplhar o retorno das chamadas recursivas
			buscaDataPorIntervalo(n.getDireita(), min, max, regs);
			buscaDataPorIntervalo(n.getEsquerda(), min, max, regs);
			return regs;
		} 
		// se for menor que o min, testa na direita
		else if (str.compareTo(min) < 0) {
			return buscaDataPorIntervalo(n.getDireita(), min, max, regs);
		} 
		// se for maior que o max, testa na esquerda
		else {
			return buscaDataPorIntervalo(n.getEsquerda(), min, max, regs);
		}
	}
	
	public int buscaCPF(long num) {
		if (this.raiz != null) {
			Nodo n = buscaSimples(num, this.raiz);
			if (n != null)
				return n.getRegs().get(0);
			else
				return -1;
		} else {
			return -1;
		}
	}
	
	public Nodo buscaSimples(long num, Nodo n) {
		if (n == null) {
			return null;
		} if (num > n.getNum()) {
			return buscaSimples(num, n.getDireita());
		} else if (num < n.getNum()) {
			return buscaSimples(num, n.getEsquerda());
		} else {
			return n;
		}
	}
	
	//metodo recursivo para mostrar a arvore usando indentacao
	public void mostraArvore(Nodo n, int i) {
		if (n != null) {
			// essa linha eh para construir a indentacao com tamanhos maiores a medida que a profundidade aumenta
			System.out.print(new String(new char[i*3]).replace("\0", " "));		    
			System.out.println(n.getNum());
					
			// incrementando o i a cada nivel
			mostraArvore(n.getEsquerda(), i+1);
			mostraArvore(n.getDireita(), i+1);
		}
	}

	public void mostraArvore() {
		if (this.raiz == null) {
			System.out.println("A arvore esta vazia.");
			System.out.println("\n=========================================================================================================\n");
			return;
		}
		
		int i = 1;
		
		Nodo n = this.raiz;
		
		System.out.println("-x-x- Mostra Arvore -x-x-\n");
		
		if (n != null) {
		  	    
			System.out.println(n.getNum());
					
			mostraArvore(n.getEsquerda(), i);
			mostraArvore(n.getDireita(), i);
			   
			System.out.println();
			System.out.println("\n=========================================================================================================\n");
			
		}
	}
}
