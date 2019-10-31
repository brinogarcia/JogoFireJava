package Jogo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Fase extends JPanel implements ActionListener {
	private Image fundo;
	private NaveFire nave;
	private Timer timer;

	private boolean gameOn;

	private List<Inimigo> inimigos;

	private int[][] coordenadas = { { 2380, 29 }, { 2600, 59 }, { 1380, 89 },
			{ 780, 109 }, { 580, 139 }, { 880, 239 }, { 790, 259 },
			{ 760, 50 }, { 790, 150 }, { 1980, 209 }, { 560, 45 }, { 510, 70 },
			{ 930, 159 }, { 590, 80 }, { 530, 60 }, { 940, 59 }, { 990, 30 },
			{ 920, 200 }, { 900, 259 }, { 660, 50 }, { 540, 90 }, { 810, 220 },
			{ 860, 20 }, { 740, 180 }, { 820, 128 }, { 490, 170 }, { 700, 30 },
			{ 920, 300 }, { 856, 328 }, { 456, 320 } };

	public Fase() {

		setFocusable(true);
		setDoubleBuffered(true);
		addKeyListener(new TecladoAdapter());
		ImageIcon espaco = new ImageIcon("res\\Espaco.GIF");
		fundo = espaco.getImage();

		nave = new NaveFire();

		gameOn = true;

		chamarInimigos();

		timer = new Timer(5, this);
		timer.start();

	}

	public void chamarInimigos() {
		// metodo para chamar inimigos
		inimigos = new ArrayList<Inimigo>();
		for (int i = 0; i < coordenadas.length; i++) {
			inimigos.add(new Inimigo(coordenadas[i][0], coordenadas[i][1]));
		}
	}

	public void paint(Graphics g) {
		// método para printar imagens na tela!
		Graphics2D graficos = (Graphics2D) g;
		graficos.drawImage(fundo, 0, 0, null);

		if (gameOn) {

			graficos.drawImage(nave.getImagem(), nave.getX(), nave.getY(), this);

			// codigo para printar todas as municoes adicionadas no metodo atira
			// da classe NaveFire
			List<Municao> municoes = nave.getMunicoes();
			for (int i = 0; i < municoes.size(); i++) {
				Municao m = (Municao) municoes.get(i);
				graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
			}
			for (int i = 0; i < inimigos.size(); i++) {
				Inimigo in = inimigos.get(i);
				graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
			}
			// Colocando uma frase dizendo o número de inimigos
			graficos.setColor(Color.YELLOW);
			graficos.drawString("Inimigos: " + inimigos.size(), 5, 15);
			
			
		}
		else{
			ImageIcon gameOver = new ImageIcon("res\\game_over.png");
			graficos.drawImage(gameOver.getImage(), 0, 0, null);
		}
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// testar se o jogo está ativo!
		if (inimigos.size() == 0) {
			gameOn = false;
			// caso tenha acabado com os inimigos finalizar o jogo
		}
		// Mover os inimigos

		for (int i = 0; i < inimigos.size(); i++) {

			Inimigo in = inimigos.get(i);

			if (in.visivel()) {
				in.Controle();
			} else {
				inimigos.remove(i);
			}

		}

		// Mover as municoes
		List<Municao> municoes = nave.getMunicoes();
		for (int i = 0; i < municoes.size(); i++) {

			Municao m = (Municao) municoes.get(i);

			if (m.visivel()) {
				m.Controle();
			} else {
				municoes.remove(i);
			}

		}

		nave.controle();
		checarColisoes();
		repaint();
	}

	public void checarColisoes() {
		Rectangle formaNaveFire = nave.getBounds();
		Rectangle formaInimigo;
		Rectangle formaMunicao;

		// testando se a nave será colidida
		for (int i = 0; i < inimigos.size(); i++) {
			Inimigo tempInimigo = inimigos.get(i);
			formaInimigo = tempInimigo.getBounds();// encrementando o valor da
													// lista de coordenadas no
													// inimigo
			// testando se um retangulo irá interssectar outro retangulo para
			// isso usar a funcao intersects que retorna exatamente um retangulo
			if (formaNaveFire.intersects(formaInimigo)) {
				nave.setVisivel(false);
				tempInimigo.setVisivel(false);
				gameOn = false;
			}

		}

		// Criar uma lista que receba os elementos da nave que no caso seria as
		// munições
		List<Municao> municoes = nave.getMunicoes();
		// Feito isso testar uma munião com todos os inimigos
		for (int i = 0; i < municoes.size(); i++) {
			Municao tempMunicao = municoes.get(i);
			formaMunicao = tempMunicao.getBounds();
			// agora que temos a forma da municao faremos o teste desta municao
			// para cada inimigo
			for (int j = 0; j < inimigos.size(); j++) {
				Inimigo tempInimigo = inimigos.get(j);
				formaInimigo = tempInimigo.getBounds();

				if (formaMunicao.intersects(formaInimigo)) {
					tempInimigo.setVisivel(false);
					tempMunicao.setVisivel(false);
				}

			}
		}

	}

	private class TecladoAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			nave.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			nave.keyReleased(e);
		}

	}

}
