package net.gametutorial.andengineframework;

import android.graphics.Typeface;
import android.util.Log;
import android.view.Display;
import android.widget.Toast;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.Entity;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.MoveByModifier;
import org.andengine.entity.modifier.PathModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.IOnAreaTouchListener;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.ITouchArea;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.AutoParallaxBackground;
import org.andengine.entity.scene.background.Background;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.entity.scene.background.ParallaxBackground;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder;
import org.andengine.opengl.texture.bitmap.AssetBitmapTexture;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.util.GLState;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.IGameInterface;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.adt.align.HorizontalAlign;
import org.andengine.util.adt.color.Color;
import org.andengine.util.debug.Debug;
import org.andengine.util.modifier.ease.EaseSineInOut;

import java.io.IOException;

public class GameActivity extends BaseGameActivity  implements ButtonSprite.OnClickListener,IOnSceneTouchListener {
    private int CAMERA_WIDTH1;
    private int CAMERA_HEIGHT1;
    private static final int CAMERA_WIDTH = 840;
    private static final int CAMERA_HEIGHT = 480;
    private ITexture mFaceTexture;
    private Camera camera;
    private Scene splashScene;
    private Scene mainScene,  thirdScreen, GameEndScreen;
    private static final int RECTANGLE_SIZE = 50;
  //  private BitmapTextureAtlas splashTextureAtlas;
    private ITextureRegion splashTextureRegion;
    private Sprite splash,restartButton,homeButton;
    private ITexture splashTextureAtlas;
    private ITextureRegion mFaceTextureRegion;
    Display mDisplay;
    private BuildableBitmapTextureAtlas mBuildableBitmapTextureAtlas;
    private ITextureRegion mButtonNormalTextureRegion;
    private ITextureRegion mPressedTextureRegion;
    private ITextureRegion mDisabledTextureRegion;
    private ITexture mNotesTexture,mBoxSecond,mBoxThirdTexture,mBoxForthTexture,mBoxFifthTexture,mfirstBoxTexure;
    private ITextureRegion mNotesTextureRegion,mBoxSecondRegion,mBoxthirdRegion,mBoxForthReagion,mBoxFifthRegion,mBoxFirstRegion;
    private ITexture mquiTexture,mHomeTexture,mRestrartTextrue;
    private ITextureRegion mquittextureRegion,mHomeTextrueRegin,mRestartTextureRegion;
    private ITexture mPlayerTexture;
    private TiledTextureRegion mPlayerTextureRegion;
    private TiledTextureRegion mPlayerTextureRegion1;
    private ITexture mParallaxLayerBackTexture;
    private ITexture mParallaxLayerMidTexture;
    private ITexture mParallaxLayerFrontTexture;
     VertexBufferObjectManager vertexBufferObjectManager;
    private ITextureRegion mParallaxLayerBackTextureRegion;
    private ITextureRegion mParallaxLayerMidTextureRegion;
    private ITextureRegion mParallaxLayerFrontTextureRegion;
    private Font mFont;
    int count=0;
    Text mHudText;
    Sprite mFirstBox, mSecondBox,mThirdBox,mForthBox,mFifthBox;
    AnimatedSprite player;
    private Music mMusic;
    @Override
    public void onClick(ButtonSprite pButtonSprite1, float pTouchAreaLocalX, float pTouchAreaLocalY) {
        this.toastOnUiThread("GO..GO..GO", Toast.LENGTH_LONG);
         vertexBufferObjectManager = this.getVertexBufferObjectManager();
        thirdScreen = new Scene();
        final float centerX = CAMERA_WIDTH / 12;
        final float centerY = CAMERA_HEIGHT / 18;
        player = new AnimatedSprite(centerX, centerY , this.mPlayerTextureRegion, vertexBufferObjectManager);
        player.setOffsetCenterY(0);
        player.setScale(4);
     //   player.animate(new long[]{200, 200, 200}, 3, 5, true);

        mFirstBox = new Sprite(CAMERA_WIDTH-2, 13, this.mBoxFirstRegion, this.getVertexBufferObjectManager());
        mFirstBox.setScale(1);
        thirdScreen.registerTouchArea(mFirstBox);
        thirdScreen.attachChild(mFirstBox);

         mSecondBox = new Sprite(CAMERA_WIDTH-2, 130, this.mBoxSecondRegion, this.getVertexBufferObjectManager());
        mSecondBox.setScale(1);
        thirdScreen.registerTouchArea(mSecondBox);
        thirdScreen.attachChild(mSecondBox);

        mThirdBox = new Sprite(-10, 200, this.mBoxthirdRegion, this.getVertexBufferObjectManager());
        mThirdBox.setScale(1);
        thirdScreen.registerTouchArea(mThirdBox);
        thirdScreen.attachChild(mThirdBox);

        mForthBox = new Sprite(-12, 320, this.mBoxForthReagion, this.getVertexBufferObjectManager());
        mForthBox.setScale(1);
        thirdScreen.registerTouchArea(mForthBox );
        thirdScreen.attachChild(mForthBox);

        mFifthBox = new Sprite(-12, 420, this.mBoxFifthRegion, this.getVertexBufferObjectManager());
        mFifthBox.setScale(1);
        thirdScreen.registerTouchArea( mFifthBox  );
        thirdScreen.attachChild(mFifthBox );

      //  HUD gameHUD = new HUD();
        // CREATE SCORE c
        mHudText = new Text(-12, 460, GameActivity.this.mFont, "0123456789", new TextOptions(HorizontalAlign.LEFT), vertexBufferObjectManager);
        mHudText.setText("Score:0");
        mHudText.setX((CAMERA_WIDTH - mHudText.getWidth()) / 10);
        mHudText.setVisible(true);
      //  gameHUD.attachChild(mHudText);
        //camera.setHUD(gameHUD);
        thirdScreen.attachChild(mHudText);
        thirdScreen.registerTouchArea(player);
        thirdScreen.attachChild(player);
        thirdScreen.setOnSceneTouchListener(this);

//        final Entity rectangleGroup = new Entity(CAMERA_WIDTH / 9, CAMERA_HEIGHT / 12);
//        rectangleGroup.attachChild(this.makeColoredRectangle(-RECTANGLE_SIZE / 12, -RECTANGLE_SIZE / 12, Color.WHITE));
//        mainScene2.attachChild(rectangleGroup);
        final AutoParallaxBackground autoParallaxBackground = new AutoParallaxBackground(0, 0, 0, 10);
        final Sprite parallaxLayerBackSprite = new Sprite(0, 0, this.mParallaxLayerBackTextureRegion, vertexBufferObjectManager);
        parallaxLayerBackSprite.setOffsetCenter(0, 0);
        autoParallaxBackground.attachParallaxEntity(new ParallaxBackground.ParallaxEntity(-10.0f, parallaxLayerBackSprite));

        final Sprite parallaxLayerMidSprite = new Sprite(0, CAMERA_HEIGHT - this.mParallaxLayerMidTextureRegion.getHeight() - 80, this.mParallaxLayerMidTextureRegion, vertexBufferObjectManager);
        parallaxLayerMidSprite.setOffsetCenter(0, 0);
        autoParallaxBackground.attachParallaxEntity(new ParallaxBackground.ParallaxEntity(-5.0f, parallaxLayerMidSprite));

        final PathModifier.Path path = new PathModifier.Path(2).to(CAMERA_WIDTH - 2, 40).to(-12, 40);
        final PathModifier.Path secondBoxPath = new PathModifier.Path(2).to(CAMERA_WIDTH-2, 120).to(-12, 120);
        final PathModifier.Path thirdBoxPath = new PathModifier.Path(2).to(CAMERA_WIDTH-3, 200).to(-12,200);
        final PathModifier.Path forthBoxPath = new PathModifier.Path(2).to(CAMERA_WIDTH-3, 320).to(-12,320);
        final PathModifier.Path fifthBoxPath = new PathModifier.Path(2).to(CAMERA_WIDTH-3, 420).to(-12,420);

        mFifthBox.registerEntityModifier(new LoopEntityModifier(new PathModifier(18, fifthBoxPath, null, new PathModifier.IPathModifierListener() {
            @Override
            public void onPathStarted(PathModifier pPathModifier, IEntity pEntity) {
            }

            @Override
            public void onPathWaypointStarted(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            @Override
            public void onPathWaypointFinished(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {

            }

            @Override
            public void onPathFinished(PathModifier pPathModifier, IEntity pEntity) {
                countScore();
            }
        }, EaseSineInOut.getInstance())));

        mForthBox.registerEntityModifier(new LoopEntityModifier(new PathModifier(8 ,forthBoxPath,null, new PathModifier.IPathModifierListener() {
            @Override
            public void onPathStarted(PathModifier pPathModifier, IEntity pEntity) {
            }
            @Override
            public void onPathWaypointStarted(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }
            @Override
            public void onPathWaypointFinished(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {

            }

            @Override
            public void onPathFinished(PathModifier pPathModifier, IEntity pEntity) {
                countScore();
            }
        }, EaseSineInOut.getInstance())));

        mThirdBox.registerEntityModifier(new LoopEntityModifier(new PathModifier(15 ,thirdBoxPath,null, new PathModifier.IPathModifierListener() {
            @Override
            public void onPathStarted(PathModifier pPathModifier, IEntity pEntity) {
            }
            @Override
            public void onPathWaypointStarted(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }
            @Override
            public void onPathWaypointFinished(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {

            }

            @Override
            public void onPathFinished(PathModifier pPathModifier, IEntity pEntity) {
                countScore();
            }
        }, EaseSineInOut.getInstance())));

        mSecondBox.registerEntityModifier(new LoopEntityModifier(new PathModifier(7 ,secondBoxPath,null, new PathModifier.IPathModifierListener() {
            @Override
            public void onPathStarted(PathModifier pPathModifier, IEntity pEntity) {
            }
            @Override
            public void onPathWaypointStarted(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }
            @Override
            public void onPathWaypointFinished(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {

            }

            @Override
            public void onPathFinished(PathModifier pPathModifier, IEntity pEntity) {
                countScore();
            }
        }, EaseSineInOut.getInstance())));

        mFirstBox.registerEntityModifier(new LoopEntityModifier(new PathModifier(17, path, null, new PathModifier.IPathModifierListener() {
            @Override
            public void onPathStarted(final PathModifier pPathModifier, final IEntity pEntity) {
                Debug.d("onPathStarted");
            }

            @Override
            public void onPathWaypointStarted(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
            }

            @Override
            public void onPathWaypointFinished(final PathModifier pPathModifier, final IEntity pEntity, final int pWaypointIndex) {
                Debug.d("onPathWaypointFinished: " + pWaypointIndex);
            }

            @Override
            public void onPathFinished(final PathModifier pPathModifier, final IEntity pEntity) {
                Debug.d("onPathFinished");
                countScore();
            }
        }, EaseSineInOut.getInstance())));
        thirdScreen.registerUpdateHandler(new IUpdateHandler() {

            @Override
            public void onUpdate(float pSecondsElapsed) {
                if (mFirstBox.collidesWith(player) || mSecondBox.collidesWith(player) || mThirdBox.collidesWith(player) || mForthBox.collidesWith(player) || mFifthBox.collidesWith(player)) {
                    GameEndScreen = new Scene();
                    final Text centerText = new Text(CAMERA_WIDTH / 2, 400, GameActivity.this.mFont, "GAME OVER !!!\n" + "Your Score is:" + count, new TextOptions(HorizontalAlign.CENTER), vertexBufferObjectManager);
                    homeButton = new Sprite(380, 250, GameActivity.this.mHomeTextrueRegin, GameActivity.this.getVertexBufferObjectManager()) {
                        @Override
                        public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {

                            mEngine.setScene(mainScene);

                            return true;
                        }
                    };
                    GameEndScreen.registerTouchArea(homeButton);
                    GameEndScreen.attachChild(homeButton);
                    restartButton = new Sprite(450, 250, GameActivity.this.mRestartTextureRegion, GameActivity.this.getVertexBufferObjectManager()){
                        @Override
                        public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                            thirdScreen.reset();

                            mEngine.setScene(thirdScreen);
                            return true;
                        }
                    };
                    GameEndScreen.registerTouchArea(restartButton);
                    GameEndScreen.attachChild(restartButton);
                    GameEndScreen.attachChild(centerText);
                    GameEndScreen.setBackground(new Background(Color.WHITE));
                    mEngine.setScene(GameEndScreen);

                }
            }

            @Override
            public void reset() {

            }
        });
        thirdScreen.setBackground(autoParallaxBackground);
       // mainScene2.setBackground(new Background(Color.BLUE));
        mEngine.setScene( thirdScreen);
    }

    @Override
    public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
        final Entity playerEntity = player;
        final SequenceEntityModifier modifier1 =   new SequenceEntityModifier(
                new MoveByModifier((float) 1.5, 0, 30),
                new MoveByModifier((float) 1.5, 0, -30)
        );
        playerEntity.registerEntityModifier(modifier1);
        return true;
    }
    private enum SceneType {
        SPLASH,
        MAIN,
        OPTIONS,
        WORLD_SELECTION,
        LEVEL_SELECTION,
        CONTROLLER
    }
    private SceneType currentScene = SceneType.SPLASH;
    @Override
    public EngineOptions onCreateEngineOptions() {
        mDisplay = getWindowManager().getDefaultDisplay();
        mDisplay = getWindowManager().getDefaultDisplay();

        CAMERA_HEIGHT1 = mDisplay.getHeight();
        CAMERA_WIDTH1 = mDisplay.getWidth();
        camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
        EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_SENSOR, new FillResolutionPolicy(), camera);
        engineOptions.getAudioOptions().setNeedsMusic(true);
        return engineOptions;
    }
    @Override
    public void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback) throws IOException {
        // Log.d("-------onCreateResources()---------", " ");
       BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        splashTextureAtlas = new AssetBitmapTexture(this.getTextureManager(), this.getAssets(), "gfx/splashImage.png");
        splashTextureRegion =  TextureRegionFactory.extractFromTexture(this.splashTextureAtlas);

        this.mPlayerTexture = new AssetBitmapTexture(this.getTextureManager(), this.getAssets(), "gfx/player.png", TextureOptions.BILINEAR);
        this.mPlayerTextureRegion = TextureRegionFactory.extractTiledFromTexture(this.mPlayerTexture, 3, 4);
        this.mPlayerTexture.load();

        this.mParallaxLayerBackTexture = new AssetBitmapTexture(this.getTextureManager(), this.getAssets(), "gfx/parallax_background_layer_back.png");
        this.mParallaxLayerBackTextureRegion = TextureRegionFactory.extractFromTexture(this.mParallaxLayerBackTexture);
        this.mParallaxLayerBackTexture.load();

        this.mParallaxLayerMidTexture = new AssetBitmapTexture(this.getTextureManager(), this.getAssets(), "gfx/parallax_background_layer_mid1.png");
        this.mParallaxLayerMidTextureRegion = TextureRegionFactory.extractFromTexture(this.mParallaxLayerMidTexture);
        this.mParallaxLayerMidTexture.load();

        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        this.mBuildableBitmapTextureAtlas = new BuildableBitmapTextureAtlas(this.getTextureManager(), 312, 312);
        this.mButtonNormalTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBuildableBitmapTextureAtlas, this, "play1.png");
        this.mPressedTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBuildableBitmapTextureAtlas, this, "play2.png");
        this.mDisabledTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBuildableBitmapTextureAtlas, this, "button_disabled.png");

        try {
            this.mBuildableBitmapTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(15, 14, 10));
            this.mBuildableBitmapTextureAtlas.load();
        } catch (ITextureAtlasBuilder.TextureAtlasBuilderException e) {
            Debug.e(e);
        }
        this.mHomeTexture = new AssetBitmapTexture(this.getTextureManager(), this.getAssets(), "gfx/home_button.png", TextureOptions.BILINEAR);
        this.mHomeTextrueRegin = TextureRegionFactory.extractFromTexture(this.mHomeTexture);
        this.mHomeTexture.load();

        this.mRestrartTextrue = new AssetBitmapTexture(this.getTextureManager(), this.getAssets(), "gfx/restart_game_button.png", TextureOptions.BILINEAR);
        this.mRestartTextureRegion = TextureRegionFactory.extractFromTexture(this.mRestrartTextrue);
        this.mRestrartTextrue.load();

        this.mNotesTexture = new AssetBitmapTexture(this.getTextureManager(), this.getAssets(), "gfx/sound1.png", TextureOptions.BILINEAR);
        this.mNotesTextureRegion = TextureRegionFactory.extractFromTexture(this.mNotesTexture);
        this.mNotesTexture.load();

        this.mfirstBoxTexure = new AssetBitmapTexture(this.getTextureManager(), this.getAssets(), "gfx/box.png", TextureOptions.BILINEAR);
        this.mBoxFirstRegion = TextureRegionFactory.extractFromTexture(this.mfirstBoxTexure);
        this.mfirstBoxTexure.load();

        this.mBoxSecond = new AssetBitmapTexture(this.getTextureManager(), this.getAssets(), "gfx/box2.png", TextureOptions.BILINEAR);
        this.mBoxSecondRegion = TextureRegionFactory.extractFromTexture(this.mBoxSecond);
        this.mBoxSecond.load();

        this.mBoxThirdTexture = new AssetBitmapTexture(this.getTextureManager(), this.getAssets(), "gfx/box3.png", TextureOptions.BILINEAR);
        this.mBoxthirdRegion = TextureRegionFactory.extractFromTexture(this.mBoxThirdTexture);
        this.mBoxThirdTexture.load();

        this.mBoxForthTexture = new AssetBitmapTexture(this.getTextureManager(), this.getAssets(), "gfx/box4.png", TextureOptions.BILINEAR);
        this.mBoxForthReagion = TextureRegionFactory.extractFromTexture(this.mBoxForthTexture);
        this.mBoxForthTexture.load();

        this.mBoxFifthTexture = new AssetBitmapTexture(this.getTextureManager(), this.getAssets(), "gfx/box5.png", TextureOptions.BILINEAR);
        this.mBoxFifthRegion = TextureRegionFactory.extractFromTexture(this.mBoxFifthTexture);
        this.mBoxFifthTexture.load();

        this.mquiTexture = new AssetBitmapTexture(this.getTextureManager(), this.getAssets(), "gfx/quit1.png", TextureOptions.BILINEAR);
        this.mquittextureRegion = TextureRegionFactory.extractFromTexture(this.mquiTexture);
        this.mquiTexture.load();
        MusicFactory.setAssetBasePath("mfx/");
        try {
           // this.mMusic = MusicFactory.createMusicFromAsset(this.mEngine.getMusicManager(), this, "wagner_the_ride_of_the_valkyries.ogg");
            this.mMusic = MusicFactory.createMusicFromAsset(this.mEngine.getMusicManager(), this, "my_music_game.mp3");
            this.mMusic.setLooping(true);
        } catch (final IOException e) {
            Debug.e(e);
        }
        this.mFont = FontFactory.create(this.getFontManager(), this.getTextureManager(), 1000, 1000, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 32);
        this.mFont.load();
        splashTextureAtlas.load();
        pOnCreateResourcesCallback.onCreateResourcesFinished();
    }
    @Override
    public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) throws IOException {
        initSplashScene();
        pOnCreateSceneCallback.onCreateSceneFinished(this.splashScene);
    }
    @Override
    public void onPopulateScene(Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback) throws IOException {
        //  Log.d("-------onPopulateScene()---------", " ");
        mEngine.registerUpdateHandler(new TimerHandler(3f, new ITimerCallback() {
            public void onTimePassed(final TimerHandler pTimerHandler) {
                mEngine.unregisterUpdateHandler(pTimerHandler);
                loadResources();
                loadScenes();
                splash.detachSelf();
                mEngine.setScene(mainScene);
                currentScene = SceneType.MAIN;
            }
        }));

        pOnPopulateSceneCallback.onPopulateSceneFinished();
    }
    public void loadResources() {
    }

    private void loadScenes() {
        mainScene = new Scene();

        //  mainScene.setBackground(new Background(100, 100, 100));


        final float centerX = CAMERA_WIDTH / 2;
        final float centerY = CAMERA_HEIGHT / 2;
        final float centerX1 = CAMERA_WIDTH / 2;
        final float centerY1 = (float) (CAMERA_HEIGHT / 1.5);
        final float centerX2 = CAMERA_WIDTH / 2;
        //  final float centerY2 = CAMERA_HEIGHT / 3;
        final float centerY2 = (float) (CAMERA_HEIGHT / 2.9);
        final Sprite notes = new Sprite(centerX1, centerY1, this.mNotesTextureRegion, this.getVertexBufferObjectManager());
        mainScene.registerTouchArea(notes);
        mainScene.setOnAreaTouchListener(new IOnAreaTouchListener() {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final ITouchArea pTouchArea, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
                if (pSceneTouchEvent.isActionDown()) {
                    if (GameActivity.this.mMusic.isPlaying()) {
                        GameActivity.this.mMusic.pause();
                    } else {
                        GameActivity.this.mMusic.play();
                    }
                }
                return true;
            }
        });
        final Sprite quitButton = new Sprite(centerX2, centerY2, this.mquittextureRegion, this.getVertexBufferObjectManager()){
                @Override
                public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                    System.exit(0);
                    return true;
                }
    };
        mainScene.registerTouchArea(quitButton);
        mainScene.attachChild(quitButton);
        final ButtonSprite buttonSprite = new ButtonSprite(centerX, centerY,this.mButtonNormalTextureRegion, this.mPressedTextureRegion, this.mDisabledTextureRegion, this.getVertexBufferObjectManager(), this);
        mainScene.registerTouchArea(buttonSprite);
        mainScene.attachChild(notes);
        mainScene.attachChild(buttonSprite);
        mainScene.setTouchAreaBindingOnActionDownEnabled(true);
        mainScene.setBackground(new Background(Color.BLUE));
    }
    private Rectangle makeColoredRectangle(final float pX, final float pY, final Color pColor) {
        final Rectangle coloredRect = new Rectangle(pX, pY, RECTANGLE_SIZE, RECTANGLE_SIZE, this.getVertexBufferObjectManager());
        coloredRect.setColor(pColor);
        return coloredRect;
    }
    void countScore(){

        count=count+1;
        mHudText.setText("Score:" + count);
    }
    private void initSplashScene() throws IOException {
        //  Log.d("-------initSplashScene()---------", " ");
        splashScene = new Scene();
        splash = new Sprite(0, 0, splashTextureRegion, mEngine.getVertexBufferObjectManager()) {
            @Override
            protected void preDraw(GLState pGLState, Camera pCamera) {
                super.preDraw(pGLState, pCamera);
                pGLState.enableDither();
            }

        };
        splash.setScale(1.0f);
        splash.setPosition( CAMERA_WIDTH - splash.getWidth() * 0.5f , CAMERA_HEIGHT - splash.getHeight() * 0.5f);
        splashScene.attachChild(splash);
    }

//    @Override
//    public void onBackPressed() {
//       // super.onBackPressed();
//        System.exit(0);
//    }
}