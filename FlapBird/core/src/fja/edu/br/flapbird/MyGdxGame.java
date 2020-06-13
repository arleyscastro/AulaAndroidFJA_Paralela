package fja.edu.br.flapbird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture[] passaros;
	private Texture fundo;
	private float variacao = 0;
	private float deltaTime;
	private float velocidadeDeQueda=0;
	private float posicaoInicialVertical=0;

	private int estadoDoJogo=0;  //0-> jogo não iniciado; 1->Jogo iniciado; 2->Game Over
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		passaros = new Texture[3];
		fundo = new Texture("fundo.png");
		//passaro = new Texture("passaro1.png");
		passaros[0] = new Texture("passaro1.png");
		passaros[1] = new Texture("passaro2.png");
		passaros[2] = new Texture("passaro3.png");
		posicaoInicialVertical = Gdx.graphics.getHeight() / 2;
	}

	@Override
	public void render () {
		deltaTime = Gdx.graphics.getDeltaTime();
		variacao += deltaTime * 20;
		if(variacao > 2)
			variacao = 0;

		if(Gdx.input.justTouched()){
			estadoDoJogo = 1;
		}

		if(estadoDoJogo == 1){
			velocidadeDeQueda++;
			posicaoInicialVertical = posicaoInicialVertical - velocidadeDeQueda;
			if(Gdx.input.justTouched()){
				velocidadeDeQueda = -15;
			}
		}


		//Similar a um quadro em branco para ser desenhado
		batch.begin();

		batch.draw(fundo,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		batch.draw(passaros[(int)variacao], 150, posicaoInicialVertical);

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		passaros[0].dispose();
		passaros[1].dispose();
		passaros[2].dispose();
		fundo.dispose();
	}
}

