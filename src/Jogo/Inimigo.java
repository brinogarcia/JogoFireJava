package Jogo;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Inimigo {
	private Image imagem;
	private int x, y; //coordenadas do tiro
	private int largura, altura;
	private boolean visivel;// para controlar o tiro

	private static final int LARGURA_TELA = 1000; //Constante para Definir largura da tela para que a munição não ultrapasse o ponto(Importante o valor deve ser de acordo com o definido na tela!).
	private static final int VELOCIDADE = 1; //Constante para definir velicidade do tiro, as constantes em java são definidas em caixa alta somente como boa prática de programação.

	public Inimigo(int x, int y){
		// o construtor recebe por parametro os valores de x e y da nave para coordenadas!
		this.x = x;
		this.y = y;
		ImageIcon inimigo = new ImageIcon("res\\inimigo.png");
		imagem = inimigo.getImage(); // passando a imagem para o atributa privado Image imagem!

		altura = imagem.getHeight(null);// essa funcao reclhe exatamente a altura da imagem.
		largura = imagem.getWidth(null);// essa funca recolhe exatamente a largura da imagem.
		
		visivel = true; // deixando a munição ativa;
	}
	
	public void Controle(){
	//metodo para mexer o inimigo, se a coordenada x for menor que 0 voltará a coordenada da largura da tela, se não decrementará x.
		if(x < 0){
			x = LARGURA_TELA;
		}
		else{
			x -= VELOCIDADE;
		}
		
	}
	
	
	
	// adicionar os métódos Getters para saber quais são as coordenadas da nave e se a munição será visível ou não.
	// no caso do atributo "visivel" será necessário o metódo Setter para definir verdadeiro ou falso!
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

	//Colisão
	//Será tratado com um método retornando um triangulo e o formato do retangulo.
	public Rectangle getBounds(){
		return new Rectangle(x, y, largura, altura);
	}
	


}
