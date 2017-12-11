import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Arrays;

/**
 * Created by HE YE on 11/26/2017.
 */
public class Main extends javafx.application.Application {
    private final int SCREEN_WIDTH = 540;
    private final int SCREEN_HEIGHT = 400;

    private final double speed=2.5;
    private final String[] DIRECTION_CONTROLLER ={"DOWN","UP","LEFT","RIGHT"};

    private String currentKey = null;
    private int direction;
    private int count = 0;

    Group_Game game = null;

    int currentScene = 0;

    private Group_Start startScene = new Group_Start();

    private void counting(){
        count=(count+1)%20;
    }

    private int toDirection(){
        if(currentKey=="UP")
            return 2;
        else if(currentKey=="DOWN")
            return 0;
        else if(currentKey=="LEFT")
            return 1;
        else
            return 3;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("MeloMelo!");

        Group_Menu sm = new Group_Menu();

        Scene scene = new Scene(startScene,SCREEN_WIDTH,SCREEN_HEIGHT,Color.color(0.2706, 0.1333, 0));

        Timeline tlControl = new Timeline(new KeyFrame(new Duration(20), ae->{
            game.Melo.move(direction,count,speed);
            game.mapMove(speed);
            game.checkItems();
            counting();
        }));

        tlControl.setCycleCount(Animation.INDEFINITE);

        scene.setOnKeyPressed(e->{
            switch(currentScene){
                case 0:
                    if(e.getCode()== KeyCode.DOWN)
                        startScene.arrowDown();
                    else if(e.getCode()==KeyCode.UP)
                        startScene.arrowUp();
                    else if(e.getCode()==KeyCode.ENTER){
                        switch (startScene.makeChoice()){
                            case 0:
                                game = new Group_Game();
                                scene.setRoot(game);
                                currentScene=3;
                                startScene.reset();
                                break;
                            case 1:
                                startScene.load();
                                currentScene=1;
                                break;
                            case 2:
                                primaryStage.close();
                                break;
                        }
                    }
                    break;

                case 1:
                    if(e.getCode()== KeyCode.DOWN)
                        startScene.loadArrowDown();
                    else if(e.getCode()==KeyCode.UP)
                        startScene.loadArrowUp();
                    else if(e.getCode()==KeyCode.ESCAPE) {
                        startScene.loadBack();
                        currentScene=0;
                    }
                    else if(e.getCode()==KeyCode.ENTER){
                        startScene.choose();
                    }
                    break;

                case 3:
                    if(e.getCode()==KeyCode.ESCAPE){
                        game.Melo.stop();
                        tlControl.stop();
                        game.getChildren().add(sm);
                        currentScene=31;
                    }else if( Arrays.asList(DIRECTION_CONTROLLER).contains(e.getCode().toString())) {
                        currentKey=e.getCode().toString();
                        direction = toDirection();
                        tlControl.play();
                    }
                    break;

                case 31:
                    if(e.getCode()==KeyCode.DOWN)
                        sm.arrowDown();
                    else if(e.getCode()==KeyCode.UP)
                        sm.arrowUp();
                    else if(e.getCode()==KeyCode.ENTER){
                        switch (sm.choice()){
                            case 0:
                                game.getChildren().remove(sm);
                                sm.reset();
                                currentScene=3;
                                break;
                            case 1:                      //TODO  保存存档
                                sm.save();
                                currentScene = 311;
                                break;
                            case 2:                      //TODO  退出问询
                                scene.setRoot(startScene);
                                tlControl.stop();
                                currentScene=0;
                                sm.reset();
                                break;
                        }
                    }else if(e.getCode()==KeyCode.ESCAPE){
                        game.getChildren().remove(sm);
                        sm.reset();
                        currentScene=3;
                    }
                    break;

                case 311:
                    if(e.getCode()==KeyCode.DOWN)
                        sm.saveArrowDown();
                    else if(e.getCode()==KeyCode.UP)
                        sm.saveArrowUp();
                    else if(e.getCode()==KeyCode.ESCAPE) {
                        sm.saveClose();
                        currentScene=31;
                    }
                    break;
            }
        });

        scene.setOnKeyReleased(e ->{
            if(currentScene==3&&currentKey==e.getCode().toString()) {
                currentKey=null;
                    game.Melo.stop();
                    tlControl.stop();
                    count = 0;
            }
        });

        scene.setOnMouseClicked(e->{
            System.out.println(e.getSceneX()+" "+e.getSceneY());
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
