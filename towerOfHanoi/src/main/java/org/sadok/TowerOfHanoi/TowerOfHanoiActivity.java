package org.sadok.TowerOfHanoi;



import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.widget.Toast;

import com.google.common.collect.Ordering;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.adt.io.in.IInputStreamOpener;
import org.andengine.util.debug.Debug;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static org.sadok.TowerOfHanoi.MenuActivity.checkboxTrue;
import static org.sadok.TowerOfHanoi.MenuActivity.selectedFeedBackItem;
import static org.sadok.TowerOfHanoi.MenuActivity.selectedItem;
import static org.sadok.TowerOfHanoi.MenuActivity.selectedShapeItem;

public class TowerOfHanoiActivity extends SimpleBaseGameActivity {
	private static int CAMERA_WIDTH = 1280;
	private static int CAMERA_HEIGHT = 748;
	private ITextureRegion mBackgroundTextureRegion, mTowerTextureRegion, mRing1, mRing2, mRing3, mRing4, mRing5, mRing6;
	private Sprite mTower1, mTower2, mTower3;
	private Stack mStack1, mStack2, mStack3;
	private List<Integer> poids = new ArrayList<Integer>() ;
	private Timer theChrono;
	private Report theReport;
	private boolean neutre;
	@Override
	public EngineOptions onCreateEngineOptions() {
    	final Camera camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
    	return new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), camera);
	}


	@Override
	protected void onCreateResources() {

        try {
        	// 1 - Set up bitmap textures
			System.out.println("Checkbox: "+checkboxTrue);

            ITexture backgroundTexture = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
                @Override
                public InputStream open() throws IOException {
					if(checkboxTrue) {
						return getAssets().open("gfx/background2.png");
					}else{
						return getAssets().open("gfx/background2.png");
					}
                }
            });
            ITexture towerTexture = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
                @Override
                public InputStream open() throws IOException {
                    return getAssets().open("gfx/tower5.png");
                }
            });
			ITexture ring1 = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
				@Override

				public InputStream open() throws IOException {
					InputStream ringShape1 = getAssets().open("gfx/RectangleFinal/Emotion/Mouth/rect1Mouth.png");

					if (!checkboxTrue) {
						if (selectedShapeItem.equals("Circulaire")) {
							ringShape1 = getAssets().open("gfx/RingFinal/ring1.png");
						}
						if (selectedShapeItem.equals("Pyramidale")) {
							ringShape1 = getAssets().open("gfx/PyramideFinal/Pyram1.png");
						}

					}else {
						if (selectedShapeItem.equals("Rectangulaire")) {
							ringShape1 = getAssets().open("gfx/rectFinal3d/rect1.png");
						}
						if(selectedShapeItem.equals("Pyramidale")){
							ringShape1 = getAssets().open("gfx/PyramideFinal3d/pyram1.png");
						}
						if (selectedShapeItem.equals("Circulaire")) {
							ringShape1 = getAssets().open("gfx/ringFinal3d/ring1.png");
						}

					}
					return ringShape1;
				}
			});
			ITexture ring2 = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
				@Override
				public InputStream open() throws IOException {
					InputStream ringShape2 = getAssets().open("gfx/RectangleFinal/Emotion/Mustache/rect2Mustache.png");
					if (!checkboxTrue) {
					if(selectedShapeItem.equals("Circulaire")){
						ringShape2 = getAssets().open("gfx/RingFinal/ring2.png");
					}

					if(selectedShapeItem.equals("Pyramidale")){
						ringShape2 = getAssets().open("gfx/PyramideFinal/Pyram2.png");
					}
					}else {
						if (selectedShapeItem.equals("Rectangulaire")) {
							ringShape2 = getAssets().open("gfx/rectFinal3d/rect2.png");
						}
						if(selectedShapeItem.equals("Pyramidale")){
							ringShape2 = getAssets().open("gfx/PyramideFinal3d/pyram2.png");
						}
					}
					return ringShape2;
				}
			});
            ITexture ring3 = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
                @Override
                public InputStream open() throws IOException {
					InputStream ringShape3 = getAssets().open("gfx/RectangleFinal/Emotion/Nose/rect3Nose.png");
					if (!checkboxTrue) {
						if (selectedShapeItem.equals("Circulaire")) {
							ringShape3 = getAssets().open("gfx/RingFinal/ring3.png");
						}
						if (selectedShapeItem.equals("Pyramidale")) {
							ringShape3 = getAssets().open("gfx/PyramideFinal/Pyram3.png");
						}
					}else {
						if (selectedShapeItem.equals("Rectangulaire")) {
							ringShape3 = getAssets().open("gfx/rectFinal3d/rect3.png");
						}
						if (selectedShapeItem.equals("Circulaire")) {
							ringShape3 = getAssets().open("gfx/ringFinal3d/ring3.png");
						}
						if(selectedShapeItem.equals("Pyramidale")){
							ringShape3 = getAssets().open("gfx/PyramideFinal3d/pyram3.png");
						}
					}
					return ringShape3;
                }
            });
            ITexture ring4 = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
                @Override
                public InputStream open() throws IOException {
					InputStream ringShape4 = getAssets().open("gfx/RectangleFinal/Emotion/Eyes/rect4Eyes.png");
					if (!checkboxTrue) {
						if (selectedShapeItem.equals("Circulaire")) {
							ringShape4 = getAssets().open("gfx/RingFinal/ring4.png");
						}
						if (selectedShapeItem.equals("Pyramidale")) {
							ringShape4 = getAssets().open("gfx/PyramideFinal/Pyram4.png");
						}
					} else {
							if (selectedShapeItem.equals("Circulaire")) {
								ringShape4 = getAssets().open("gfx/ringFinal3d/ring4.png");
							}
							if (selectedShapeItem.equals("Rectangulaire")) {
							ringShape4 = getAssets().open("gfx/rectFinal3d/rect4.png");
						}
							if (selectedShapeItem.equals("Pyramidale")) {
								ringShape4 = getAssets().open("gfx/PyramideFinal3d/pyram4.png");
							}
						}
					return ringShape4;
				}
            });
            ITexture ring5 = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
                @Override
                public InputStream open() throws IOException {
					InputStream ringShape5 = getAssets().open("gfx/RectangleFinal/Emotion/Eyebrow/rect5EyeBrow.png");
					if (!checkboxTrue) {
						if (selectedShapeItem.equals("Circulaire")) {
							ringShape5 = getAssets().open("gfx/RingFinal/ring5.png");
						}
						if (selectedShapeItem.equals("Pyramidale")) {
							ringShape5 = getAssets().open("gfx/PyramideFinal/Pyram5.png");
						}
					}else {
						if (selectedShapeItem.equals("Circulaire")) {
							ringShape5 = getAssets().open("gfx/ringFinal3d/ring5.png");
						}
						if (selectedShapeItem.equals("Rectangulaire")) {
							ringShape5 = getAssets().open("gfx/rectFinal3d/rect5.png");
						}
						if (selectedShapeItem.equals("Pyramidale")) {
							ringShape5 = getAssets().open("gfx/PyramideFinal3d/pyram5.png");
						}
					}
					return ringShape5;
				}
            });
			ITexture ring6 = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
				@Override
				public InputStream open() throws IOException {
					InputStream ringShape6 = getAssets().open("gfx/RectangleFinal/Emotion/Hat/rect6Hat.png");
					if (!checkboxTrue) {
						if (selectedShapeItem.equals("Circulaire")) {
							ringShape6 = getAssets().open("gfx/RingFinal/ring6.png");
						}
						if (selectedShapeItem.equals("Pyramidale")) {
							ringShape6 = getAssets().open("gfx/PyramideFinal/Pyram6.png");
						}
					}else {
						if (selectedShapeItem.equals("Circulaire")) {
							ringShape6 = getAssets().open("gfx/ringFinal3d/ring6.png");
						}
						if (selectedShapeItem.equals("Rectangulaire")) {
							ringShape6 = getAssets().open("gfx/rectFinal3d/rect6.png");
						}
						if (selectedShapeItem.equals("Pyramidale")) {
							ringShape6 = getAssets().open("gfx/PyramideFinal3d/pyram6.png");
						}
					}
					return ringShape6;
				}
			});

            // 2 - Load bitmap textures into VRAM
            backgroundTexture.load();
            towerTexture.load();
            ring1.load();
            ring2.load();
            ring3.load();
			ring4.load();
			ring5.load();
			ring6.load();

			// 3 - Set up texture regions
            this.mBackgroundTextureRegion = TextureRegionFactory.extractFromTexture(backgroundTexture);
            this.mTowerTextureRegion = TextureRegionFactory.extractFromTexture(towerTexture);
			this.mRing2 = TextureRegionFactory.extractFromTexture(ring2);
            this.mRing1 = TextureRegionFactory.extractFromTexture(ring1);
            this.mRing3 = TextureRegionFactory.extractFromTexture(ring3);
			this.mRing4 = TextureRegionFactory.extractFromTexture(ring4);
			this.mRing5 = TextureRegionFactory.extractFromTexture(ring5);
			this.mRing6 = TextureRegionFactory.extractFromTexture(ring6);


			// 4 - Create the stacks
            this.mStack1 = new Stack();
            this.mStack2 = new Stack();
            this.mStack3 = new Stack();
        } catch (IOException e) {
            Debug.e(e);
        }
	}

	@Override
	protected Scene onCreateScene() {
		// 0 - Timer créé et lancé lors de la création de l'activité, cela nous permettra de calculer la durée totale d'une partie
		this.theChrono = new Timer();
		this.theChrono.start();
		System.out.println("Time Start (ms): "+theChrono.getT_second_game_start());


		// 1 - Create new scene
		final Scene scene = new Scene();
		Sprite backgroundSprite = new Sprite(0, 0, this.mBackgroundTextureRegion, getVertexBufferObjectManager());
		scene.attachChild(backgroundSprite);
		// 2 - Add the towers
		mTower1 = new Sprite(214, 177, this.mTowerTextureRegion, getVertexBufferObjectManager());
		mTower2 = new Sprite(640, 177, this.mTowerTextureRegion, getVertexBufferObjectManager());
		mTower3 = new Sprite(1066, 177, this.mTowerTextureRegion, getVertexBufferObjectManager());
		scene.attachChild(mTower1);
		scene.attachChild(mTower2);
		scene.attachChild(mTower3);
		// 3 - Create the rings

		Ring ring1 = new Ring(6, mTower1.getX() + mTower1.getWidth()/2 - mRing1.getWidth()/2, mTower1.getY() + mTower1.getHeight() - mRing1.getHeight(), this.mRing1, getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if (((Ring) this.getmStack().peek()).getmWeight() != this.getmWeight())


				return false;

				this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, pSceneTouchEvent.getY() - this.getHeight() / 2);

				if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP) {
					//detecte une action
					if (!neutre) {
						theChrono.clickAction();
					}
					checkForCollisionsWithTowers(this);
					scene.attachChild(this);

					checkEnding(this);

				}

				return true;
			}


		};
		float DmRing2;
		if(checkboxTrue) {
			if(selectedShapeItem.equals("Pyramidale")){
				DmRing2 = mRing2.getHeight() - 5;
			}
			else{
				DmRing2 = mRing2.getHeight() - 20;
			}

		}else {
			DmRing2 = mRing2.getHeight();
		}
		Ring ring2 = new Ring(5, mTower1.getX() + mTower1.getWidth()/2 - mRing2.getWidth()/2, ring1.getY() - (DmRing2), this.mRing2, getVertexBufferObjectManager()) {
		    @Override
		    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {

		        if (((Ring) this.getmStack().peek()).getmWeight() != this.getmWeight())
		            return false;

				this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, pSceneTouchEvent.getY() - this.getHeight() / 2);
		        if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP) {
					//detecte une action
					if (!neutre) {
						theChrono.clickAction();
					}
		        	checkForCollisionsWithTowers(this);
					scene.attachChild(this);

					checkEnding(this);
				}

		        return true;
		    }
		};
		float DmRing3;
		if(checkboxTrue) {
			if(selectedShapeItem.equals("Pyramidale")){
				DmRing3 = mRing3.getHeight() - 5;
			}else {
				DmRing3 = mRing3.getHeight() - 15;
			}
		}else {
			DmRing3 = mRing3.getHeight();
		}
		Ring ring3 = new Ring(4, mTower1.getX() + mTower1.getWidth()/2 - mRing3.getWidth()/2, ring2.getY() - (DmRing3), this.mRing3, getVertexBufferObjectManager()) {

		    @Override
		    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if(getSelectedItem().equals("3") && theChrono.getT_second_game_playerTouchStart() == 0){
					//lance le départ depuis le premier touché de l'écran sur l'anneau 3 (le plus petit si la partie selcetionnée est de 3 anneaux);
					//Et que le starter n'a jamais été initialiser
					theChrono.playerTouchStart();
				}
		        if (((Ring) this.getmStack().peek()).getmWeight() != this.getmWeight())
		            return false;
		        this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, pSceneTouchEvent.getY() - this.getHeight() / 2);

		        if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP) {

					//detecte une action
					if (!neutre) {
						theChrono.clickAction();
					}
		            checkForCollisionsWithTowers(this);
					scene.attachChild(this);

					checkEnding(this);
				}

				//detecte une action

		        return true;

		    }
		};
		float DmRing4;
		if(checkboxTrue) {
			if(selectedShapeItem.equals("Pyramidale")){
				DmRing4 = mRing4.getHeight() - 5;
			}else {
				DmRing4 = mRing4.getHeight() - 10;
			}
		}else {
			DmRing4 = mRing4.getHeight();
		}
		Ring ring4 = new Ring(3, mTower1.getX() + mTower1.getWidth()/2 - mRing4.getWidth()/2, ring3.getY() - (DmRing4), this.mRing4, getVertexBufferObjectManager()) {
		    @Override
		    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if(getSelectedItem().equals("4") && theChrono.getT_second_game_playerTouchStart() == 0){
					//lance le départ depuis le premier touché de l'écran sur l'anneau 4 (le plus petit si la partie selcetionnée est de 4 anneaux);
					//Et que le starter n'a jamais été initialiser
					theChrono.playerTouchStart();
				}
		        if (((Ring) this.getmStack().peek()).getmWeight() != this.getmWeight())
		            return false;
		        this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, pSceneTouchEvent.getY() - this.getHeight() / 2);
		        if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP) {
					//detecte une action
					if (!neutre) {
						theChrono.clickAction();
					}
		        	checkForCollisionsWithTowers(this);
					scene.attachChild(this);
					checkEnding(this);
		        }
				//detecte une action

				return true;
		    }
		};
		float DmRing5;
		if(checkboxTrue) {
			if(selectedShapeItem.equals("Pyramidale")){
				DmRing5 = mRing5.getHeight() - 5;
			}else {
				DmRing5 = mRing5.getHeight() - 10;
			}
		}else {
			DmRing5 = mRing5.getHeight();
		}
		Ring ring5 = new Ring(2, mTower1.getX() + mTower1.getWidth()/2 - mRing5.getWidth()/2, ring4.getY() - (DmRing5), this.mRing5, getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if(getSelectedItem().equals("5") && theChrono.getT_second_game_playerTouchStart() == 0){
					//lance le départ depuis le premier touché de l'écran sur l'anneau 5 (le plus petit si la partie selcetionnée est de 5 anneaux);
					//Et que le starter n'a jamais été initialiser
					theChrono.playerTouchStart();
				}
				if (((Ring) this.getmStack().peek()).getmWeight() != this.getmWeight())
					return false;
				this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, pSceneTouchEvent.getY() - this.getHeight() / 2);
				if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP) {
					//detecte une action
					if (!neutre) {
						theChrono.clickAction();
					}
					checkForCollisionsWithTowers(this);
					scene.attachChild(this);
					checkEnding(this);
				}
				//detecte une action

				return true;
			}
		};
		float DmRing6;
		if(checkboxTrue) {
			if(selectedShapeItem.equals("Pyramidale")){
				DmRing6 = mRing6.getHeight() -2;
			}else {
				DmRing6 = mRing6.getHeight() - 10;
			}
		}else {
			DmRing6 = mRing6.getHeight();
		}
		final Ring ring6 = new Ring(1, mTower1.getX() + mTower1.getWidth()/2 - mRing6.getWidth()/2, ring5.getY() - (DmRing6), this.mRing6, getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if(getSelectedItem().equals("6") && theChrono.getT_second_game_playerTouchStart() == 0){
					//lance le départ depuis le premier touché de l'écran sur l'anneau 6 (le plus petit si la partie selcetionnée est de 6 anneaux);
					//Et que le starter n'a jamais été initialiser
					theChrono.playerTouchStart();
				}
				if (((Ring) this.getmStack().peek()).getmWeight() != this.getmWeight())
					return false;
				this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, pSceneTouchEvent.getY() - this.getHeight() / 2);
				if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP) {
					//detecte une action
					if (!neutre) {
						theChrono.clickAction();
					}
					checkForCollisionsWithTowers(this);
					scene.attachChild(this);
					checkEnding(this);
				}

				//detecte une action

				return true;
			}
		};

		if (selectedItem.equals("3")){

			scene.attachChild(ring1);

			this.mStack1.add(ring1);
			ring1.setmStack(mStack1);
			ring1.setmTower(mTower1);
			scene.registerTouchArea(ring1);

			scene.attachChild(ring2);

			this.mStack1.add(ring2);
			ring2.setmStack(mStack1);
			ring2.setmTower(mTower1);
			scene.registerTouchArea(ring2);

			scene.attachChild(ring3);

			this.mStack1.add(ring3);
			ring3.setmStack(mStack1);
			ring3.setmTower(mTower1);
			scene.registerTouchArea(ring3);
		}

		if (selectedItem.equals("4")){
			scene.attachChild(ring1);
			this.mStack1.add(ring1);
			ring1.setmStack(mStack1);
			ring1.setmTower(mTower1);
			scene.registerTouchArea(ring1);

			scene.attachChild(ring2);
			this.mStack1.add(ring2);
			ring2.setmStack(mStack1);
			ring2.setmTower(mTower1);
			scene.registerTouchArea(ring2);

			scene.attachChild(ring3);
			this.mStack1.add(ring3);
			ring3.setmStack(mStack1);
			ring3.setmTower(mTower1);
			scene.registerTouchArea(ring3);

			scene.attachChild(ring4);
			this.mStack1.add(ring4);
			ring4.setmStack(mStack1);
			ring4.setmTower(mTower1);
			scene.registerTouchArea(ring4);
		}
		if (selectedItem.equals("5")){
			scene.attachChild(ring1);
			this.mStack1.add(ring1);
			ring1.setmStack(mStack1);
			ring1.setmTower(mTower1);
			scene.registerTouchArea(ring1);

			scene.attachChild(ring2);
			this.mStack1.add(ring2);
			ring2.setmStack(mStack1);
			ring2.setmTower(mTower1);
			scene.registerTouchArea(ring2);

			scene.attachChild(ring3);
			this.mStack1.add(ring3);
			ring3.setmStack(mStack1);
			ring3.setmTower(mTower1);
			scene.registerTouchArea(ring3);

			scene.attachChild(ring4);
			this.mStack1.add(ring4);
			ring4.setmStack(mStack1);
			ring4.setmTower(mTower1);
			scene.registerTouchArea(ring4);

			scene.attachChild(ring5);
			this.mStack1.add(ring5);
			ring5.setmStack(mStack1);
			ring5.setmTower(mTower1);
			scene.registerTouchArea(ring5);
		}
		if (selectedItem.equals("6")){
			scene.attachChild(ring1);
			this.mStack1.add(ring1);
			ring1.setmStack(mStack1);
			ring1.setmTower(mTower1);
			scene.registerTouchArea(ring1);

			scene.attachChild(ring2);
			this.mStack1.add(ring2);
			ring2.setmStack(mStack1);
			ring2.setmTower(mTower1);
			scene.registerTouchArea(ring2);

			scene.attachChild(ring3);
			this.mStack1.add(ring3);
			ring3.setmStack(mStack1);
			ring3.setmTower(mTower1);
			scene.registerTouchArea(ring3);

			scene.attachChild(ring4);
			this.mStack1.add(ring4);
			ring4.setmStack(mStack1);
			ring4.setmTower(mTower1);
			scene.registerTouchArea(ring4);

			scene.attachChild(ring5);
			this.mStack1.add(ring5);
			ring5.setmStack(mStack1);
			ring5.setmTower(mTower1);
			scene.registerTouchArea(ring5);

			scene.attachChild(ring6);
			this.mStack1.add(ring6);
			ring6.setmStack(mStack1);
			ring6.setmTower(mTower1);
			scene.registerTouchArea(ring6);

		}

		scene.setTouchAreaBindingOnActionDownEnabled(true);

		return scene;
	}

	private void checkForCollisionsWithTowers(Ring ring) {
	    Stack stack = null;
	    Sprite tower = null;
		int indexPoids = poids.indexOf(ring.getmWeight());
	    if (ring.collidesWith(mTower1) && (mStack1.size() == 0 || ring.getmWeight() < ((Ring) mStack1.peek()).getmWeight())) {
			neutre = false;

	    	//code executer en cas de succès déclenchement chrono succès
			//a chaque succès un marqueur temporel est posé quelquesoit le feedBack, c'est la différence entre 2 marqueurs qui nous permettra
			//de connaitre le temps entre 2 succès. Cela est traité dans la classe Timer.
	    	this.theChrono.clickSuccess();
			System.out.println("Time Between 2 succés (ms/null possible au début) : "+theChrono.getTimeBetweenSuccess());

			stack = mStack1;
	        tower = mTower1;
			System.out.println("AUTORISER1");
			System.out.println("Anneaux:"+ring.getmStack());
			if (indexPoids != -1) {
				poids.remove(indexPoids);
			}

		} else if (ring.collidesWith(mTower2) && (mStack2.size() == 0 || ring.getmWeight() < ((Ring) mStack2.peek()).getmWeight())) {
			neutre = false;

	    	this.theChrono.clickSuccess();
			System.out.println("Time Between 2 succés (ms/null possible au début) : "+theChrono.getTimeBetweenSuccess());

	        stack = mStack2;
	        tower = mTower2;
			System.out.println("AUTORISER2");
			System.out.println("Anneaux:"+ring.getmStack());
			if (indexPoids != -1) {
				poids.remove(indexPoids);
			}

		} else if (ring.collidesWith(mTower3) && (mStack3.size() == 0 || ring.getmWeight() < ((Ring) mStack3.peek()).getmWeight())) {
			neutre = false;

			this.theChrono.clickSuccess();
			System.out.println("Time Between 2 succés (ms/null possible au début) : "+theChrono.getTimeBetweenSuccess());

	    	stack = mStack3;
	        tower = mTower3;
			System.out.println("AUTORISER3");
			if (indexPoids == -1) {
				poids.add(ring.getmWeight());
			}
			System.out.println("Ringooo: "+poids.toString());

			System.out.println("Autorisé? "+ Ordering.natural().reverse().isOrdered(poids));



		}

		//Cas ou l'utilisateur n'a pas le droit d'effectuer ce mouvement
	    else {
			//code executer en cas d'erreur déclenchement chrono erreur
			//a chaque erreur un marqueur temporel est posé quelquesoit le feedBack, c'est la diférence entre 2 marqueurs qui nous permettra
			//de connaitre le temps entre 2 erreurs. Cela est traité dans la classe Timer.

				System.out.println("Time Between 2 erreur (ms/null possible au début) : " + theChrono.getTimeBetweenError());

				System.out.println("INTERDIT");

				if (selectedFeedBackItem.equals("Totale")) {
					if (!ring.getmTower().collidesWith(ring)) {
						neutre = false;
						runOnUiThread(new Runnable() {
							public void run() {
								Context context = getApplicationContext();
								final Toast toast = Toast.makeText(context, "ATTENTION!\nMouvement interdit", Toast.LENGTH_LONG);
								toast.setGravity(Gravity.CENTER, 0, 0);
								toast.show();

							}
						});
						this.theChrono.clickError();

					}else {
						neutre = true;
					}
						System.out.println("TOWER " + ring.getmTower().collidesWith(ring));
					stack = ring.getmStack();
					tower = ring.getmTower();

				}
				if (selectedFeedBackItem.equals("Semi")) {
					if (!ring.getmTower().collidesWith(ring)) {
						neutre = false;
						runOnUiThread(new Runnable() {
							public void run() {
								Context context = getApplicationContext();
								final Toast toast = Toast.makeText(context, "ATTENTION!\nMouvement interdit", Toast.LENGTH_LONG);
								toast.setGravity(Gravity.CENTER, 0, 0);
								toast.show();

							}
						});
						this.theChrono.clickError();

					}else {
						neutre = true;
					}
					if (ring.collidesWith(mTower1)) {
						stack = mStack1;
						tower = mTower1;
						if (indexPoids != -1) {
							poids.remove(indexPoids);
						}

					} else if (ring.collidesWith(mTower2)) {
						stack = mStack2;
						tower = mTower2;
						if (indexPoids != -1) {
							poids.remove(indexPoids);
						}


					} else if (ring.collidesWith(mTower3)) {
						stack = mStack3;
						tower = mTower3;
						if (indexPoids == -1) {
							poids.add(ring.getmWeight());
							System.out.println("Ringooo: " + poids.toString());

							System.out.println("Autorisé? " + Ordering.natural().reverse().isOrdered(poids));
						}
					} else if (!ring.collidesWith(ring.getmTower())) {
						stack = ring.getmStack();
						tower = ring.getmTower();

					}
				}
				if (selectedFeedBackItem.equals("Sans")) {
					if (!ring.getmTower().collidesWith(ring)){
						neutre = false;
					}else {
						neutre = true;
					}
					if (ring.collidesWith(mTower1)) {
						if (indexPoids != -1) {
							poids.remove(indexPoids);
						}
						stack = mStack1;
						tower = mTower1;
					} else if (ring.collidesWith(mTower2)) {
						if (indexPoids != -1) {
							poids.remove(indexPoids);
						}
						stack = mStack2;
						tower = mTower2;
					} else if (ring.collidesWith(mTower3)) {
						if (indexPoids == -1) {
							poids.add(ring.getmWeight());
						}

						System.out.println("Ringooo: " + poids.toString());

						System.out.println("Autorisé? " + Ordering.natural().reverse().isOrdered(poids));

						stack = mStack3;
						tower = mTower3;
					} else if (!ring.collidesWith(ring.getmTower())) {
						stack = ring.getmStack();
						tower = ring.getmTower();
					}

				}

	    }

	    ring.getmStack().remove(ring);
		if(checkboxTrue) {
			if (selectedShapeItem.equals("Rectangulaire")) {

			if (stack != null && tower !=null && stack.size() == 0) {
				ring.setPosition(tower.getX() + tower.getWidth()/2 - ring.getWidth()/2, tower.getY() + tower.getHeight() - (ring.getHeight()/1.2f));
			} else if (stack != null && tower !=null && stack.size() > 0) {
				ring.setPosition(tower.getX() + tower.getWidth()/2 - ring.getWidth()/2, ((Ring) stack.peek()).getY() - (ring.getHeight()/1.2f));
			}
			}else if(selectedShapeItem.equals("Circulaire")){
				if (stack != null && tower !=null && stack.size() == 0) {
					ring.setPosition(tower.getX() + tower.getWidth()/2 - ring.getWidth()/2, tower.getY() + tower.getHeight() - (ring.getHeight()/1.3f));
				} else if (stack != null && tower !=null && stack.size() > 0) {
					ring.setPosition(tower.getX() + tower.getWidth()/2 - ring.getWidth()/2, ((Ring) stack.peek()).getY() - (ring.getHeight()/1.3f));
				}

				
			}
			else if(selectedShapeItem.equals("Pyramidale")){
				if (stack != null && tower !=null && stack.size() == 0) {
					ring.setPosition(tower.getX() + tower.getWidth()/2 - ring.getWidth()/2, tower.getY() + tower.getHeight() - (ring.getHeight()/1.06f));
				} else if (stack != null && tower !=null && stack.size() > 0) {
					ring.setPosition(tower.getX() + tower.getWidth()/2 - ring.getWidth()/2, ((Ring) stack.peek()).getY() - (ring.getHeight()/1.06f));
				}


			}
		}else {
			if (stack != null && tower !=null && stack.size() == 0) {
				ring.setPosition(tower.getX() + tower.getWidth()/2 - ring.getWidth()/2, tower.getY() + tower.getHeight() - ring.getHeight());
			} else if (stack != null && tower !=null && stack.size() > 0) {
				ring.setPosition(tower.getX() + tower.getWidth()/2 - ring.getWidth()/2, ((Ring) stack.peek()).getY() - ring.getHeight());
			}
		}


	stack.add(ring);
	    ring.setmStack(stack);
	    ring.setmTower(tower);
	}
	private void checkEnding(Ring ring){
		Stack stack = ring.getmStack();
		Context context = getApplicationContext();
		int selectedItemParsed = Integer.parseInt(selectedItem);
		int verifSelectedItem = selectedItemParsed-1;
		Boolean isOrdered = Ordering.natural().reverse().isOrdered(poids);
		if (stack.size() > verifSelectedItem && ring.getmTower() == mTower3 && isOrdered){
			System.out.println("nice");
			//timer stoppé en fin de partie
			this.theChrono.stop();

			//report édité puis affiché
			theReport = new Report(theChrono, this, context);
			theReport.afficheReport();
			theReport.addItemToSheet(context);
			finish();
			Intent i = new Intent(getBaseContext(), EndingActivity.class);
			startActivity(i);
		}
	}
	public static String getSelectedFeedBackItem() {
		return selectedFeedBackItem;
	}

	public static String getSelectedItem() {
		return selectedItem;
	}

	public static String getSelectedShapeItem() {
		return selectedShapeItem;
	}

	public static String getDimension(){
		if(checkboxTrue){
			return "3D";
		} else {
			return "2D";
		}
	}
}