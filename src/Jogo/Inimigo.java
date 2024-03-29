package Jogo;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Inimigo {
	private Image imagem;
	private int x, y; //coordenadas do tiro
	private int largura, altura;
	private boolean visivel;// para controlar o tiro

	private static final int LARGURA_TELA = 1000; //Constante para Definir largura da tela para que a muni��o n�o ultrapasse o ponto(Importante o valor deve ser de acordo com o definido na tela!).
	private static final int VELOCIDADE = 1; //Constante para definir velicidade do tiro, as constantes em java s�o definidas em caixa alta somente como boa pr�tica de programa��o.

	public Inimigo(int x, int y){
		// o construtor recebe por parametro os valores de x e y da nave para coordenadas!
		this.x = x;
		this.y = y;
		ImageIcon inimigo = new ImageIcon("res\\inimigo.png");
		imagem = inimigo.getImage(); // passando a imagem para o atributa privado Image imagem!

		altura = imagem.getHeight(null);// essa funcao reclhe exatamente a altura da imagem.
		largura = imagem.getWidth(null);// essa funca recolhe exatamente a largura da imagem.
		
		visivel = true; // deixando a muni��o ativa;
	}
	
	public void Controle(){
	//metodo para mexer o inimigo, se a coordenada x for menor que 0 voltar� a coordenada da largura da tela, se n�o decrementar� x.
		if(x < 0){
			x = LARGURA_TELA;
		}
		else{
			x -= VELOCIDADE;
		}
		
	}
	
	
	
	// adicionar os m�t�dos Getters para saber quais s�o as coordenadas da nave e se a muni��o ser� vis�vel ou n�o.
	// no caso do atributo "visivel" ser� necess�rio o met�do Setter para definir verdadeiro ou falso!
	public boolean visivel(){
		return visivel;
	}
	public void setVisivel(boolean visivel){
		this.visivel = visivel;
	}
	public Image getImagem() {
		return imagem;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}

	//Colis�o
	//Ser� tratado com um m�todo retornando um triangulo e o formato do retangulo.
	public Rectangle getBounds(){
		return new Rectangle(x, y, largura, altura);
	}
	


}
