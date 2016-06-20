package com.frappagames.demineur.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.TimeUtils;
import com.frappagames.demineur.Demineur;
import com.frappagames.demineur.Tools.GameScreen;
import com.frappagames.demineur.Tools.Settings;

/**
 * Play screen : Draw tiles and manage game loop
 */
public class PlayScreen extends GameScreen {
    private final Label bestTimeLbl;
    private final Label gamePlayedLbl;
    private final Label gameWinLbl;
    private final Label gameTimeLbl;
    private int difficulty;
    private int _height       = 15;
    private int _width        = 9;
    private int _tilesOppened = 0;
    private int _nbMines      = 0;
    private int _gameTime;
    private Integer _nbTiles = 0;
    private int[][] _mineGrid;
    private int[][] _deckGrid;
    private boolean _gameIsOver = false;
    private boolean _gameIsWin  = false;
    private Label _timeLbl;
    private Label _tilesLbl;
    private int   startTime;
    private Table _window;
    private Label _gameStateLbl;

    public PlayScreen(final Demineur game, final int difficulty) {
        super(game);

        this.difficulty = difficulty;

        this._mineGrid = new int[this._width][this._height];
        this._deckGrid = new int[this._width][this._height];

        BitmapFont font32 = new BitmapFont(Gdx.files.internal("xen32.fnt"), false);
        BitmapFont font60 = new BitmapFont(Gdx.files.internal("xen60.fnt"), false);

        Label.LabelStyle labelStyle32 = new Label.LabelStyle(font32, Color.BLACK);
        Label.LabelStyle labelStyle60 = new Label.LabelStyle(font60, Color.BLACK);

        switch (difficulty) {
            case 1:
                _nbMines = 10;
                break;
            case 3:
                _nbMines = 30;
                break;
            default:
                _nbMines = 20;
                break;
        }
        newGrid(_nbMines);

        this._nbTiles = (this._width * this._height) - this._nbMines;

        _gameTime = 0;

        _timeLbl = new Label("00:00", labelStyle32);
        _timeLbl.setPosition(556 + offsetX, 1198);
        _timeLbl.setWidth(100);
        _timeLbl.setAlignment(Align.center);

        _tilesLbl = new Label(Integer.valueOf(_nbTiles - _tilesOppened).toString(), labelStyle32);
        _tilesLbl.setPosition(82 + offsetX, 1198);
        _tilesLbl.setWidth(100);
        _tilesLbl.setAlignment(Align.center);

        startTime = Math.round(TimeUtils.nanoTime() / 1000000000);


        // Fenêtre de fin de jeu
        Label newGameLbl = new Label("PARTIE TERMINEE", labelStyle60);
        Label youHaveLbl = new Label("vous avez", labelStyle32);
        _gameStateLbl = new Label("", labelStyle60);

        gameTimeLbl = new Label("", labelStyle32);
        gameTimeLbl.setAlignment(Align.left);
        bestTimeLbl = new Label("", labelStyle32);
        bestTimeLbl.setAlignment(Align.left);
        gamePlayedLbl = new Label("", labelStyle32);
        gamePlayedLbl.setAlignment(Align.left);
        gameWinLbl = new Label("", labelStyle32);
        gameWinLbl.setAlignment(Align.left);

        Skin skin = new Skin();
        skin.addRegions(Demineur.atlas);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font32;
        textButtonStyle.up = skin.getDrawable("smallBtn");
        textButtonStyle.down = skin.getDrawable("smallBtnOver");

        TextButton quitBtn   = new TextButton("QUITTER", textButtonStyle);
        TextButton replayBtn = new TextButton("REJOUER", textButtonStyle);
        replayBtn.pad(15);

        Table hg = new Table();
        hg.add(quitBtn).pad(15);
        hg.add(replayBtn).pad(15);

        _window = new Table();
        _window.background(skin.getDrawable("window"));
        _window.setSize(577, 550);
        _window.setPosition(71 + offsetX, 365);

        _window.add(newGameLbl).pad(25, 10, 20, 0).row();
        _window.add(youHaveLbl).pad(8, 0, 8, 0).row();
        _window.add(_gameStateLbl).pad(8, 0, 20, 0).row();

        _window.add(gameTimeLbl).pad(8, 20, 8, 0).left().row();
        _window.add(bestTimeLbl).pad(8, 20, 8, 0).left().row();
        _window.add(gamePlayedLbl).pad(8, 20, 8, 0).left().row();
        _window.add(gameWinLbl).pad(8, 20, 8, 0).left().row();

        _window.add(hg).pad(35, 0, 15, 0).row();
        _window.setVisible(false);
        stage.addActor(_window);

        replayBtn.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new PlayScreen(game, difficulty));
            }
        });
        quitBtn.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MenuScreen(game));
            }
        });
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        if (!_gameIsOver) {
            manageInput();
        }

        game.batch.begin();
        printMineGrid();
        printDeckGrid();

        showTime();
        showTilesCount();

        game.batch.end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        _timeLbl.setPosition(556 + offsetX, 1198);
        _tilesLbl.setPosition(82 + offsetX, 1198);
    }

    private void showTime() {
        game.batch.draw(Demineur.atlas.findRegion("timeLbl"), 552 + offsetX, 1185);

        if (!_gameIsOver) {
            int currentTime = Math.round(TimeUtils.nanoTime() / 1000000000);
            _gameTime = currentTime - startTime;
        }

        _timeLbl.setText(formatTime(_gameTime));
        _timeLbl.draw(game.batch, 1);
    }

    private void showTilesCount() {
        game.batch.draw(Demineur.atlas.findRegion("minesLbl"), 18 + offsetX, 1185);
        _tilesLbl.setText(Integer.valueOf(_nbTiles - _tilesOppened).toString());
        _tilesLbl.draw(game.batch, 1);
    }

    private void manageInput() {
        // process user input
        if (!_gameIsOver && Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);

            if (touchPos.x >= 36 + offsetX && touchPos.x <= 684 - offsetX && touchPos.y >= 60 && touchPos.y <= 1140) {
                int x = (int) Math.floor((touchPos.x - 36  - offsetX) / 72);
                int y = (int) Math.floor((touchPos.y - 60) / 72);
                openTile(x, y);
            }
        }
    }

    private void updateGameStats() {
        Settings.increaseGamePlayed(difficulty);

        if (_gameIsWin) {
            Settings.increaseGameWin(difficulty);
            _gameStateLbl.setText("GAGNE");

            int previousBestTime = Settings.getBestTime(difficulty);

            if (previousBestTime == 0 || _gameTime < previousBestTime) {
                // Update best time
                Settings.setBestTime(difficulty, _gameTime);
            }
        } else {
            _gameStateLbl.setText("PERDU");
        }

        gameTimeLbl.setText("TEMPS : " + formatTime(_gameTime));

        int bestTime = Settings.getBestTime(difficulty);
        bestTimeLbl.setText("RECORD : " + formatTime(bestTime));

        int playedGames = Settings.getGamePlayed(difficulty);
        int winGames = Settings.getGameWin(difficulty);
        int ratio = (int) Math.floor((((float) winGames / (float) playedGames) * 100.0f));

        gamePlayedLbl.setText("PARTIES JOUEES : " + playedGames);
        gameWinLbl.setText("PARTIES GAGNEES : " + winGames + " (" + ratio + "%)");
        _window.setVisible(true);
    }

    private String formatTime(int time) {
        Integer minutes = ((int) Math.floor(time / 60));
        Integer seconds = time % 60;
        String result = "";

        if (minutes > 10) {
            result += String.valueOf(minutes);
        } else {
            result += "0" + String.valueOf(minutes);
        }

        result += ":";

        if (seconds > 10) {
            result += String.valueOf(seconds);
        } else {
            result += "0" + String.valueOf(seconds);
        }

        return result;
    }

    private boolean openTile(int x, int y) {
        // Check for Game Over
        if (this._mineGrid[x][y] == 1) {
            this._deckGrid[x][y] = 2;
            _gameIsOver = true;
            _gameIsWin = false;
            _openMines(x, y);
            updateGameStats();

            return false;
        }

        if (this._deckGrid[x][y] == 0) {
            this._deckGrid[x][y] = 1;
            _tilesOppened++;

            // Check for Game Win
            if (_tilesOppened == ((this._height * this._width) - _nbMines)) {
                _gameIsOver = true;
                _gameIsWin = true;
                _openMines(x, y);
                updateGameStats();

                return true;
            }

            if (countMine(x, y) == 0) {
                if (x - 1 >= 0) {
                    if (y - 1 >= 0) openTile(x - 1, y - 1);
                    openTile(x - 1, y);
                    if (y + 1 < this._height) openTile(x - 1, y + 1);
                }
                if (x + 1 < this._width) {
                    if (y - 1 >= 0) openTile(x + 1, y - 1);
                    openTile(x + 1, y);
                    if (y + 1 < this._height) openTile(x + 1, y + 1);
                }
                if (y - 1 >= 0) openTile(x, y - 1);
                if (y + 1 < this._height) openTile(x, y + 1);
            }
        }

        return true;
    }

    private void _openMines(int x, int y) {
        for (int i = 0; i < this._width; i++) {
            for (int j = 0; j < this._height; j++) {
                if (this._mineGrid[i][j] == 1 && (i != x || j != y)) {
                    this._deckGrid[i][j] = 1;
                }
            }
        }
    }

    private void newGrid(int mines) {

        int i = 0;
        int rand_x;
        int rand_y;
        this._mineGrid = new int[this._width][this._height];
        this._deckGrid = new int[this._width][this._height];

        while (i < mines) {
            rand_x = (int) Math.floor(Math.random() * this._width);
            rand_y = (int) Math.floor(Math.random() * this._height);
            if (this._mineGrid[rand_x][rand_y] == 0) {
                this._mineGrid[rand_x][rand_y] = 1;
                i++;
            }
        }
    }

    public void printMineGrid() {
        for (int i = 0; i < this._width; i++) {
            for (int j = 0; j < this._height; j++) {
                if (this._mineGrid[i][j] == 1) {
                    game.batch.draw(Demineur.atlas.findRegion("tile_bomb"), i * 72 + 36 + offsetX, j * 72 + 60);
                } else {
                    game.batch.draw(Demineur.atlas.findRegion("tile" + countMine(i, j)), i * 72 + 36 + offsetX, j * 72 + 60);
                }
            }
        }
    }

    public void printDeckGrid() {
        for (int i = 0; i < this._width; i++) {
            for (int j = 0; j < this._height; j++) {
                switch (this._deckGrid[i][j]) {
                    case 1:
                        break;
                    case 2:
                        game.batch.draw(Demineur.atlas.findRegion("tile_bomb_red"), i * 72 + 36 + offsetX, j * 72 + 60);
                        break;
                    default:
                        game.batch.draw(Demineur.atlas.findRegion("tile_btn"), i * 72 + 36 + offsetX, j * 72 + 60);
                        break;
                }
            }
        }
    }

    private int countMine(int x, int y) {
        int nb = 0;

        if (x - 1 >= 0) {
            if (y - 1 >= 0) if (this._mineGrid[x - 1][y - 1] == 1) nb++;
            if (this._mineGrid[x - 1][y] == 1) nb++;
            if (y + 1 < this._height) if (this._mineGrid[x - 1][y + 1] == 1) nb++;
        }
        if (x + 1 < this._width) {
            if (y - 1 >= 0) if (this._mineGrid[x + 1][y - 1] == 1) nb++;
            if (this._mineGrid[x + 1][y] == 1) nb++;
            if (y + 1 < this._height) if (this._mineGrid[x + 1][y + 1] == 1) nb++;
        }
        if (y - 1 >= 0) if (this._mineGrid[x][y - 1] == 1) nb++;
        if (y + 1 < this._height) if (this._mineGrid[x][y + 1] == 1) nb++;

        return nb;
    }
}
