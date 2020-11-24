package sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tube {
    public static final int TUBE_WIDTH = 52;
    private static final int FLUCTUATION = 130;
    private static final int TUBE_GAP = 100;
    private static final int LAST_OPENING = 130;
    private Rectangle boundTop, boundBot;
    private Random rand;
    private Texture topTube,bottomTube;
    private Vector2 posTopTube,posBotTube;

    public Tube(float x){
        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");
        rand = new Random();
        posTopTube = new Vector2(x,rand.nextInt(FLUCTUATION)+TUBE_GAP+LAST_OPENING);
        posBotTube = new Vector2(x,posTopTube.y-TUBE_GAP-bottomTube.getHeight());
        boundTop = new Rectangle(posTopTube.x,posTopTube.y,topTube.getWidth(),topTube.getHeight());
        boundBot = new Rectangle(posBotTube.x,posBotTube.y,bottomTube.getWidth(),bottomTube.getHeight());
    }

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBotTube() {
        return posBotTube;
    }
    public void reposition(float x){
        posTopTube.set(x,rand.nextInt(FLUCTUATION)+TUBE_GAP+LAST_OPENING);
        posBotTube.set(x,posTopTube.y-TUBE_GAP-bottomTube.getHeight());
        boundTop.setPosition(posTopTube.x,posTopTube.y);
        boundBot.setPosition(posBotTube.x,posBotTube.y);
    }
    public boolean collide(Rectangle player){
        return player.overlaps(boundTop) || player.overlaps(boundBot);
    }
}
