

public class Arvore {
	private Nodo raiz;
	
	public Arvore(int numRaiz) {
		this.raiz = new Nodo(numRaiz);
	}
	
	public Arvore() {
		this.raiz = null;
	}
	
	// metodo que calcula o fator de balanceamento de um nodo
	public int fatorBalanceamento(Nodo n) {
		if (n == null)
			return 0;
		return alturaSemNull(n.getEsquerda()) - alturaSemNull(n.getDireita());
	}
	
	// metodo para tratar os casos onde n eh folha
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
	
	// metodo que chamado na classe de teste que chama o recursivo
	public void insercao(int num) {
		System.out.println("Inserindo valor " + num + " na arvore...");
		this.raiz = insere(num, this.raiz);
		mostraArvore();
	}
	
	// metodo recursivo de insercao
	public Nodo insere(int num, Nodo n) {
		if (n == null) {
			System.out.println("Valor " + num + " inserido!");
			return new Nodo(num);
		} if (num > n.getNum()) {
			n.setDireita(insere(num, n.getDireita()));
		} else if (num < n.getNum()) {
			n.setEsquerda(insere(num, n.getEsquerda()));
		} else {
			System.out.println("Valor " + num + " ja estava na arvore!");
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
	
	// metodo chamado na classe de teste
	// chama o metodo recursivo
	public void busca(int num) {
		if (this.raiz != null) {
			System.out.println("Buscando valor " + num + "...");
			Nodo n = busca(num, this.raiz, "Caminho percorrido: ");
			if (n != null)
				System.out.println("Valor " + num + " encontrado!");
			else
				System.out.println("Valor " + num + " nao encontrado!");
			mostraArvore();
		} else {
			System.out.println("A arvore esta vazia! Busca nao realizada.");
			System.out.println("\n=========================================================================================================\n");
		}
	}
	
	// concatena a string com o caminho percorrido
	public Nodo busca(int num, Nodo n, String caminho) {
		if (n == null) {
			System.out.println(caminho);
			return null;
		} if (num > n.getNum()) {
			caminho += n.getNum() + " ";
			return busca(num, n.getDireita(), caminho);
		} else if (num < n.getNum()) {
			caminho += n.getNum() + " ";
			return busca(num, n.getEsquerda(), caminho);
		} else {
			caminho += n.getNum() + " ";
			System.out.println(caminho);
			return n;
		}
	}
	
	//metodo chamado na classe de teste
	// chama o metodo recursivo de remocao
	public void remocao(int num) {
		if (this.raiz != null) {
			System.out.println("Excluindo nodo com valor " + num);
			this.raiz = remove(num, this.raiz, true);
			mostraArvore();
		} else {
			System.out.println("A arvore esta vazia! Remocao nao realizada.");
			System.out.println("\n=========================================================================================================\n");
		}
	}
	
	//recebe um boolean para diferenciar os casos de remocao secundaria dos casos de remocao exigida pelo usuario
	public Nodo remove(int num, Nodo n, boolean numDoUsuario) {
		if (n == null) {
			System.out.println("Valor " + num + " nao esta na arvore!");
			return n;
		} if (num > n.getNum()) {
			n.setDireita(remove(num, n.getDireita(), numDoUsuario));
		} else if (num < n.getNum()) {
			n.setEsquerda(remove(num, n.getEsquerda(), numDoUsuario));
		} else {
			if (numDoUsuario) {
				n = exclui(n);
				System.out.println("Valor " + num + " excluido!\n");
			} else {
				n = exclui(n);
			}
		}
		
		if (n == null) {
			return n;
		}
		
		// atualiza altura do nodo sendo tratado
		// vai atualizar todos os nodos no caminho do que esta sendo removido
		if (alturaSemNull(n.getEsquerda()) >= alturaSemNull(n.getDireita()))
			n.setAltura(alturaSemNull(n.getEsquerda()) + 1);
		else
			n.setAltura(alturaSemNull(n.getDireita()) + 1);
		
		return balanceia(n, fatorBalanceamento(n));
	}
	
	// metodo para realizar a exclusao por copia
	public Nodo exclui(Nodo n) {
		
		// verifica se o nodo sendo excluido tem mais de um filho
		// neste caso, pega o maior valor na subarvore da esquerda e exclui o nodo com aquele valor
		if (n.getEsquerda() != null && n.getDireita() != null) {
			Nodo maiorDaEsquerda = achaMaior(n.getEsquerda());
			n.setNum(maiorDaEsquerda.getNum());
			n.setEsquerda(remove(n.getNum(), n.getEsquerda(), false));
		} else {
			if (n.getEsquerda() != null) {
				n = n.getEsquerda();
			} else {
				n = n.getDireita();
			}
		}
		
		return n;
	}
	
	//metodo para encontrar o maior valor da sub-arvore da esquerda do nodo sendo excluido
	public Nodo achaMaior(Nodo n) {
		if (n.getDireita() != null) {
			return achaMaior(n.getDireita());
		} else {
			return n;
		}
	}
	
	// metodos para percorrer a arvore nas diferentes formas
	public String ElementosPreOrdem(Nodo n, String str) {
		if (n == null) {
			return str;
		}
		
		str += n.getNum() + " ";
		str = ElementosPreOrdem(n.getEsquerda(), str);
		str = ElementosPreOrdem(n.getDireita(), str);
		
		return str;
	}
	
	public String ElementosEmOrdem(Nodo n, String str) {
		if (n == null) {
			return str;
		}
		
		str = ElementosEmOrdem(n.getEsquerda(), str);
		str += n.getNum() + " ";
		str = ElementosEmOrdem(n.getDireita(), str);
		
		return str;
	}
	
	public String ElementosPosOrdem(Nodo n, String str) {
		if (n == null) {
			return str;
		}
		
		str = ElementosPosOrdem(n.getEsquerda(), str);
		str = ElementosPosOrdem(n.getDireita(), str);
		str += n.getNum() + " ";
		
		return str;
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

	// metodo chamado na classe de teste
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
			System.out.println(ElementosPreOrdem(n, "Trajetoria da arvore em pre-ordem: "));
			System.out.println(ElementosEmOrdem(n, "Trajetoria da arvore em-ordem: "));
			System.out.println(ElementosPosOrdem(n, "Trajetoria da arvore em pos-ordem: "));
			System.out.println("\n=========================================================================================================\n");
			
		}
	}
}