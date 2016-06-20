package com.frappagames.demineur.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.frappagames.demineur.Demineur;
import com.frappagames.demineur.Tools.GameScreen;

/**
 * Main menu of the game
 * Created by jmoreau on 11/01/16.
 */
public class MenuScreen extends GameScreen
{
    public MenuScreen(final Demineur game)
    {
        super(game);

        BitmapFont font  = new BitmapFont(Gdx.files.internal("xen60.fnt"), false);
        Label newGameLbl = new Label("NOUVELLE PARTIE", new Label.LabelStyle(font, Color.BLACK));

        Skin skin = new Skin();
        skin.addRegions(Demineur.atlas);

        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = skin.getDrawable("btn");
        textButtonStyle.down = skin.getDrawable("btnOver");

        TextButton easyBtn = new TextButton("FACILE", textButtonStyle);
        TextButton normalBtn = new TextButton("NORMAL", textButtonStyle);
        TextButton hardBtn = new TextButton("DIFFICILE", textButtonStyle);
        TextButton exitBtn = new TextButton("QUITTER", textButtonStyle);

        table.pad(70, 0, 0, 0).row();
        table.add(newGameLbl).pad(40, 0, 40, 0).row();
        table.add(easyBtn).pad(15, 0, 15, 0).row();
        table.add(normalBtn).pad(15, 0, 15, 0).row();
        table.add(hardBtn).pad(15, 0, 15, 0).row();
        table.add(exitBtn).pad(180, 0, 0, 0).row();

        easyBtn.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new PlayScreen(game, 1));
            }
        });
        normalBtn.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new PlayScreen(game, 2));
            }
        });
        hardBtn.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new PlayScreen(game, 3));
            }
        });
        exitBtn.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
