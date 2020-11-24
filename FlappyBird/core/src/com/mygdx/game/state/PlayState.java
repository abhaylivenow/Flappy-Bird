package com.mygdx.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.FlappyBirdGdxGame;

import sprites.Bird;
import sprites.Tube;

public class PlayState extends state {
    private static final int Tube_Spacing = 125;
    private static final int Tube_Count = 4;
    private static final int GROUND_Y_OFFSET = -50;
    private Bird bird;
    private Texture Ground;
    private Vector2 groundPos1 , groundPos2;
    private Array<Tube> tubes;
    private Texture bg;


    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50,300);
        bg = new Texture("bg.png");
        Ground = new Texture("ground.png");
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth/2,GROUND_Y_OFFSET);
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth/2)+Ground.getWidth(),GROUND_Y_OFFSET);
        tubes = new Array<Tube>();
        cam.setToOrtho(false, FlappyBirdGdxGame.WIDTH/2,FlappyBirdGdxGame.HEIGHT/2);

        for(int i = 1;i<=Tube_Count;i++){
            tubes.add(new Tube(i*(Tube_Spacing+ Tube.TUBE_WIDTH)));
        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            bird.jump();
        }
    }

    @Override
    public void update(Float dt) {
        handleInput();
        bird.update(dt);
        updateGround();
        cam.position.x = bird.getPosition().x+80;
        for(Tube tube : tubes){
            if(cam.position.x - (cam.viewportWidth/2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()){
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH+Tube_Spacing)*Tube_Count));
            }
            if(tube.collide(bird.getBounds())) gsm.set(new PlayState(gsm));
        }
        if(bird.getPosition().y <= Ground.getHeight()){
            gsm.set(new PlayState(gsm));
        }
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg,cam.position.x-(cam.viewportWidth/2),0);
        sb.draw(bird.getBird(),bird.getPosition().x,bird.getPosition().y);
        for(Tube tube : tubes){
            sb.draw(tube.getTopTube(),tube.getPosTopTube().x,tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(),tube.getPosBotTube().x,tube.getPosBotTube().y);
        }
        sb.draw(Ground,groundPos1.x,groundPos1.y);
        sb.draw(Ground,groundPos2.x,groundPos2.y);
        sb.end();
    }

    private void updateGround(){
        if(cam.position.x - (cam.viewportWidth/2) > groundPos1.x + Ground.getWidth()){
            groundPos1.add(Ground.getWidth()*2,0);
        }
        if(cam.position.x - (cam.viewportWidth/2) > groundPos2.x + Ground.getWidth()){
            groundPos2.add(Ground.getWidth()*2,0);
        }
    }

    @Override
    public void dispose() {
        bird.dispose();
        bg.dispose();
        Ground.dispose();
    }
}
