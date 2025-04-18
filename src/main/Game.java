package main;

import entities.Player;
import levels.LevelManager;

import java.awt.*;

public class Game implements Runnable {

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
    private Player player;
    private LevelManager levelManager;

    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 2.0f;
    public final static int TILES_IN_WIDTH = 32;
    public final static int TILES_IN_HEIGHT = 32;
    public final static int TILES_SIZE = (int)(TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;


    public Game() {
        initiaClasses();

        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();

        startGameLoop();
    }

    private void initiaClasses() {
        levelManager = new LevelManager(this);
        player = new Player(200,200,(int) (32*SCALE),(int)(32*SCALE));
        player.loadLvlData(LevelManager.getCurrentLevel().getLevelData());
    }

    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }


    private void update() {
        levelManager.update();
        player.update();

    }

    public void render(Graphics g) {
        levelManager.draw(g);
        player.render(g);

    }

    @Override
    public void run() {

        double timePerFrame = 1000000000.0/FPS_SET;
        double timePerUpdate = 1000000000.0/UPS_SET;
        long now = System.nanoTime();
        long previousTime = System.nanoTime();



        int frames = 0;
        int updates = 0;
        long lastcheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;


        while (true) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1){
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1){
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastcheck >= 1000) {
                lastcheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    public void windowFocusLost(){
        player.resetDirBoolean();
    }


    public Player getPlayer() {
        return player;
    }
}