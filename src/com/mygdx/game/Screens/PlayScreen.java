package com.mygdx.game.Screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.*;
import com.mygdx.game.MyGdxGame;
import com.badlogic.gdx.ApplicationAdapter;

import java.awt.*;

public class PlayScreen implements Screen{
    private MyGdxGame game;
    private Texture skyTex,roadTex,backTex,exTex,h1Tex,h3Tex,player;
    protected Sprite  objBarrol,objTire,objBlock1,objBlock2,objPad1,objPad2
                        ,objStone1,objStone2,objspike01,objPad04,objPad03
                        ,objcutter,objBarrol2;
    private Sprite test1,test2,animation_test;
    protected Rectangle rec1,rec2,Player_anime_test_rec,objBarrol_rec,objTire_rec
                         ,objBlock2_rec,objPad1_rec,objPad2_rec,
                        objStone1_rec,objStone2_rec,objspike01_rec,objPad04_rec
                        ,objPad03_rec,objcutter_rec ,objBarrol2_rec;

    private TextureAtlas A_Atlas;
    private Animation AnimE_1_A,AnimE_1_R,Player_1_R,Player_1_D,Player_1_J;
    private float elapsedTime = 0,clock = 0;
    private OrthographicCamera camera;
    private Viewport viewport;
    int  sourceX = 0;
    float cx,cy;
    int player_x = 50,player_y = 25,incre = 0;
    Integer PlayerFreame = 0;
    boolean isover[]=new boolean[20];

    public PlayScreen(MyGdxGame game){

        this.game = game;
        for(int i=0;i<20;i++)
        {
            isover[i]=false;
        }
        //**//Texture Declaration
        skyTex = new Texture("Bright//Sky.png");
        h3Tex = new Texture("Bright//houses3.png");
        backTex = new Texture("Bright//back.png");
        h1Tex = new Texture("Bright//houses1.png");
        exTex = new Texture("Bright//ex1.png");
        roadTex = new Texture("Bright//road&lamps.png");

        //**//Sprite Declaration
        objBarrol = new Sprite(new Texture("Object//Barrel_1.png"));
        objBarrol2 = new Sprite(new Texture("Object//Barrel_2.png"));
        objTire = new Sprite(new Texture("Object//3Tire.png"));
        animation_test=new Sprite(new Texture("Player_collision_test.png"));
        objBlock1 =new Sprite( new Texture("Object//Block.png"));
        objBlock2 = new Sprite(new Texture("Object//Block03.png"));
        objPad1 = new Sprite(new Texture("Object//Pad01.png"));
        objPad2 = new Sprite(new Texture("Object//Pad02.png"));
        objPad03= new Sprite(new Texture("Object//Pad03.png"));
        objPad04= new Sprite(new Texture("Object//Pad04.png"));
        objStone1 = new Sprite(new Texture("Object//Stone01.png"));
        objspike01 = new Sprite(new Texture("Object//Spikes1.png"));
        objcutter=new Sprite(new Texture("Object//cut1.png"));

        ////Atlas Decl
        A_Atlas = new TextureAtlas(Gdx.files.internal("Enemy_1_A//Enemy_1_A.atlas"));
        AnimE_1_A = new Animation(5/1f,A_Atlas.getRegions());
        A_Atlas = new TextureAtlas(Gdx.files.internal("Enemy_1_R//Enemy_1_R.atlas"));
        AnimE_1_R = new Animation(5/1f,A_Atlas.getRegions());
        A_Atlas = new TextureAtlas(Gdx.files.internal("PlayerRun//Player_R.atlas"));
        Player_1_R = new Animation(5/1f,A_Atlas.getRegions());
        A_Atlas = new TextureAtlas(Gdx.files.internal("PlayerDead//Player_D.atlas"));
        Player_1_D = new Animation(10/1f,A_Atlas.getRegions());
        A_Atlas = new TextureAtlas(Gdx.files.internal("PlayerJump//Player_J.atlas"));
        Player_1_J = new Animation(25/1f,A_Atlas.getRegions());
        Player_1_J.setFrameDuration(15f);

        ////Texture wrapping/looping
        skyTex.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        h3Tex.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        backTex.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        h1Tex.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        exTex.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        roadTex.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        objBarrol_rec = new Rectangle(objBarrol.getX(),objBarrol.getY(),objBarrol.getWidth(),objBarrol.getHeight());
        Player_anime_test_rec = new Rectangle(animation_test.getX(),animation_test.getY(),animation_test.getWidth()
                                                ,animation_test.getHeight());


        ////camera setup
        camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        camera.translate(camera.viewportWidth/2,camera.viewportHeight/2);
        //gameport = new FillViewport(100,400,gamecam);
    }

    @Override
    public void show() {

    }
    //// pseudo_player
    int player_rec_x=player_x;
    int player_rec_y=player_y;

    @Override
    public void render(float delta) {
        handleInput();
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        game.batch.begin();
        sourceX += 1;

        //**//psuedo rec_decl
       objBarrol_rec=objBarrol.getBoundingRectangle();
        objBarrol2_rec=objBarrol2.getBoundingRectangle();
        objTire_rec=objTire.getBoundingRectangle();
        objBlock2_rec=objBlock2.getBoundingRectangle();
        objPad1_rec=objPad1.getBoundingRectangle();
        objPad2_rec=objPad2.getBoundingRectangle();
        objPad03_rec=objPad03.getBoundingRectangle();
        objPad04_rec=objPad04.getBoundingRectangle();
        objspike01_rec=objspike01.getBoundingRectangle();
        objcutter_rec=objcutter.getBoundingRectangle();
       objStone1_rec=objStone1.getBoundingRectangle();

        Player_anime_test_rec=animation_test.getBoundingRectangle();


        cx = camera.position.x = elapsedTime += Gdx.graphics.getDeltaTime()*100;

        ////backgroun draw

        game.batch.draw(skyTex,0,0,sourceX ,0,850,500);
        game.batch.draw(h3Tex,0,0,(int)(sourceX*1.5),0,850,500);
        game.batch.draw(backTex,0,0,(int)(sourceX*1.5),0,850,500);
        game.batch.draw(h1Tex,0,0,sourceX*2,0,850,500);
        game.batch.draw(exTex,0,0,sourceX*2,0,850,500);
        game.batch.draw(roadTex,0,0,(int)(sourceX*3),0,850,500);

        ////enenmy draww

        //game.batch.draw((TextureRegion) AnimE_1_A.getKeyFrame(elapsedTime,true ),0,25);
        //game.batch.draw((TextureRegion) AnimE_1_R.getKeyFrame(elapsedTime,true ),0,10);

        ////player animation

        if(PlayerFreame == 0 && clock == 0)
        {
            game.batch.draw((TextureRegion) Player_1_R.getKeyFrame(elapsedTime,true ),player_x,player_y);
        }

        if(PlayerFreame == 1)
        {
         //   System.out.println(clock);
            clock += Gdx.graphics.getDeltaTime()*100;
            game.batch.draw((TextureRegion) Player_1_J.getKeyFrame(clock,true ),(int)(player_x+incre*1.2),player_y);

            //**//pseudo player animation

           if(clock>=20 && clock<=100){
               player_rec_y=150;
            }
           else
           {
               player_rec_y=25;
           }
           if(clock>=80 && clock<=130)
           {
               player_rec_x=100;
           }
           else
               player_rec_x=50;


            while(clock >= 130)
            {
                camera.position.x +=incre;
                PlayerFreame = 0;
                clock = 0;
                incre = 0;
            }
            incre++;
        }
        //game.batch.draw((TextureRegion) Player_1_D.getKeyFrame(elapsedTime,true ),300,25);

        //**//pseudo player rec setposition

     //   Player_anime_test_rec.setPosition((float)player_rec_x,player_rec_y);
        animation_test.setPosition((float) player_rec_x,player_rec_y);
        animation_test.draw(game.batch);


        //**//COllision


        isover[0] = Player_anime_test_rec.overlaps(objBarrol_rec);
        //isover[1] = Player_anime_test_rec.overlaps(objBarrol2_rec);
        isover[2] = Player_anime_test_rec.overlaps(objTire_rec);
      /* isover[3] = Player_anime_test_rec.overlaps(objStone1_rec);
        isover[5] = Player_anime_test_rec.overlaps(objPad1_rec);
        isover[6] = Player_anime_test_rec.overlaps(objPad04_rec);
        isover[7] = Player_anime_test_rec.overlaps(objBlock2_rec);
        isover[11] = Player_anime_test_rec.overlaps(objPad03_rec);
        isover[12] = Player_anime_test_rec.overlaps(objPad2_rec);
        isover[13] = Player_anime_test_rec.overlaps(objcutter_rec);
        isover[14] = Player_anime_test_rec.overlaps(objspike01_rec);*/
        /*for(int i=0;i<7;i++)
        {
            if(isover[i])
                System.out.println("isover["+i+"]");
        }*/

      if(isover[0]||isover[2])
        {
           System.out.println("Overlapping");
        }
        else
        {
            System.out.println("Not");
        }

        //**// obstacle draw
        //game.batch.draw(objBarrol,(int)((cx-1150)*-3),25);
        objBarrol.setPosition((cx-1100)*-3,25);
        objBarrol.draw(game.batch);
        //game.batch.draw(objTire,(int)((cx-1600)*-3),-30);
        objTire.setPosition((cx-1600)*-3,25);
        objTire.draw(game.batch);
        game.batch.draw(objPad1,(int)((cx-2100)*-3),25);
        game.batch.draw(objPad2,(int)((cx-2600)*-3),20);
        game.batch.draw(objPad04,(int)((cx-3400)*-3),20);
        game.batch.draw(objPad03,(int)((cx-4200)*-3),20);
        game.batch.draw(objBlock2,(int)((cx-4750)*-3),25);
        game.batch.draw(objStone1,(int)((cx-5300)*-3),25);
        game.batch.draw(objPad04,(int)((cx-6200)*-3),20);
        game.batch.draw(objcutter,(int)((cx-6800)*-3),20);
        game.batch.draw(objspike01,(int)((cx-7500)*-3),20);
        game.batch.draw(objStone1,(int)((cx-7500)*-3),10);
        game.batch.draw(objBarrol2,(int)((cx-8100)*-3),20);
        game.batch.end();

    }

    private void handleInput()
    {
        if(Gdx.input.isKeyJustPressed  (Input.Keys.SPACE))
        {
            PlayerFreame = 1;
            clock = 0;
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        game.batch.dispose();
        A_Atlas.dispose();
    }
}
