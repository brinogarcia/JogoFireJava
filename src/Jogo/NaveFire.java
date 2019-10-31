package Jogo;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
public class NaveFire {
	private int x, y; // coordenadas
	private int dx, dy;
	private int altura, largura; // altura e largura criados para manipulação da munição.
	private boolean visivel;
	
	private Image imagem;
	
	private List<Municao> municoes; // Será necessário implementar uma lista para adicionarmos a munição, assim será simples controlarmos a munição por lista.
	
	//construtor
	public NaveFire(){
		     
		ImageIcon nave = new ImageIcon("res\\nave.png");
		imagem = nave.getImage();
		
		altura = imagem.getHeight(null);// essa funcao reclhe exatamente a altura da imagem.
		largura = imagem.getWidth(null);// essa funca recolhe exatamente a largura da imagem.
		
		
		//implementando a lista, note que há uma função Array, porem trata-se de lista não de vetor.
		municoes = new ArrayList<Municao>();//Será necessário método para pegar a lista!
		
		this.x = 10;
		this.y = 10;
	}
	
	
	public void controle(){
		//System.out.println(x + " , " + y);// Código somente para visualizar as coordenadas
		
		x += dx; // 1 e 396
		y += dy; // -76 e 300
		
		if(x <1){
			x = 1;
		}
		if(x >396){
			x = 396;
		}
		if(y <-76){
			y = -76;
		}
		if(y >300){
			y = 300;
		}
		
		
	}
	
	
	
	
	public List<Municao> getMunicoes() {
		return municoes;
	}


	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public Image getImagem(){
		return imagem;
	}
		
	public boolean isVisivel() {
		return visivel;
	}


	public void setVisivel(boolean visivel) {
		this.visivel = visivel;
	}


	public void atira(){
		// metodo para adicionar municao a lista de municoes da nave.
		this.municoes.add(new Municao(x + largura , y + altura/2));
	}
	
	//Colisão
		//Será tratado com um método retornando um triangulo e o formato do retangulo.
		public Rectangle getBounds(){
			return new Rectangle(x, y, largura, altura);
		}
	

	public void keyPressed(KeyEvent tecla){
		
		int codigo = tecla.getKeyCode();
	
		if(codigo == KeyEvent.VK_SPACE){
			atira();// chamar o metodo da municao.
		}
		
		if(codigo == KeyEvent.VK_UP){
			dy = -1;	
		}
		if(codigo == KeyEvent.VK_DOWN){
			dy = 1;	
		}
		if(codigo == KeyEvent.VK_LEFT){
			dx = -1;	
		}
		if(codigo == KeyEvent.VK_RIGHT){
			dx = 1;	
		}
			
	}

	public void keyReleased(KeyEvent tecla){
		int codigo = tecla.getKeyCode();
		if(codigo == KeyEvent.VK_UP){
			dy = -0;	
		}
		if(codigo == KeyEvent.VK_DOWN){
			dy = 0;	
		}
		if(codigo == KeyEvent.VK_LEFT){
			dx = -0;	
		}
		if(codigo == KeyEvent.VK_RIGHT){
			dx = 0;	
		}
		
	}
}
