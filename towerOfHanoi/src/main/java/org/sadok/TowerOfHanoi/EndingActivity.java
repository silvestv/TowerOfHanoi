package org.sadok.TowerOfHanoi;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.Texture;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.adt.io.in.IInputStreamOpener;
import org.andengine.util.debug.Debug;

import java.io.IOException;
import java.io.InputStream;



public class EndingActivity extends SimpleBaseGameActivity {

    private static int CAMERA_WIDTH = 1280;
    private static int CAMERA_HEIGHT = 748;
    private ITextureRegion mBackgroundTextureRegion, newGameButton;

    @Override
    public EngineOptions onCreateEngineOptions() {
        final Camera camera = new Camera(0, 100, CAMERA_WIDTH, CAMERA_HEIGHT);
        return new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), camera);
    }
    protected void onCreateResources() {

        try {
            // 1 - Set up bitmap textures

            ITexture backgroundTexture = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
                @Override
                public InputStream open() throws IOException {

                        return getAssets().open("gfx/parfait.png");

                }
            });
            ITexture newGame = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
                @Override
                public InputStream open() throws IOException {
                    return getAssets().open("gfx/endRing2.png");
                }
            });

            backgroundTexture.load();
            newGame.load();

            // 3 - Set up texture regions
            this.mBackgroundTextureRegion = TextureRegionFactory.extractFromTexture(backgroundTexture);
            this.newGameButton = TextureRegionFactory.extractFromTexture(newGame);
        } catch (IOException e) {
            Debug.e(e);
        }
    }


    @Override
    protected Scene onCreateScene() {
        final Scene scene = new Scene();
        Sprite backgroundSprite = new Sprite(0, 0, this.mBackgroundTextureRegion, getVertexBufferObjectManager());
        scene.attachChild(backgroundSprite);

        Ring newGameRing = new Ring(6, 480, 650,this.newGameButton, getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP) {
                    System.out.println("Ok");
                    Intent i = new Intent(getBaseContext(), MenuActivity.class);
                    startActivity(i);
                }
return true;
            }
        };
        scene.attachChild(newGameRing);
        scene.registerTouchArea(newGameRing);
        scene.setTouchAreaBindingOnActionDownEnabled(true);
        return scene;
    }

}
