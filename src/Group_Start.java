import javafx.scene.Group;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Created by HE YE on 11/26/2017.
 */
public class Group_Start extends Group {
    Text title = new Text("MELOMELO");
    private Group arrow = new Group();
    private Group choices = new Group();
    private Load load = null;

    private int arrowIndex = 0;
    private Text[] ts = new Text[3];

    Group_Start(){
        makeBackGround();
        makeTitle();
        makeArrow();
        makeChoices();
    }

    private void makeTitle(){
        title.setFont(Font.font("Verdana",20));
        title.setLayoutX(100);
        title.setLayoutY(100);
        getChildren().add(title);
    }

    private void makeChoices(){
        ts[0] = new Text("新的游戏");
        ts[0].setFont(Font.font("Verdana",20));
        ts[0].setLayoutX(100);
        ts[0].setLayoutY(180);

        ts[1] = new Text("读取存档");
        ts[1].setFont(Font.font("Verdana",20));
        ts[1].setLayoutX(100);
        ts[1].setLayoutY(230);

        ts[2] = new Text("退出游戏");
        ts[2].setFont(Font.font("Verdana",20));
        ts[2].setLayoutX(100);
        ts[2].setLayoutY(280);

        choices.getChildren().addAll(ts);
        getChildren().add(choices);
    }

    private void makeBackGround(){
        ImageView imageView = new ImageView();
        Image image = new Image(Group_Start.class.getResource("Graphics/StartScene/wallPaper.png").toString(),540,0,true,false,true);
        imageView.setImage(image);

        imageView.setLayoutX(0);
        imageView.setLayoutY(0);

        getChildren().add(imageView);
        imageView.toBack();
    }

    private void makeArrow(){
        ImageView imageView = new ImageView();
        Image image = new Image(Group_Start.class.getResource("Graphics/StartScene/sword.png").toString(),30,50,false,false,false);
        imageView.setImage(image);
        imageView.setLayoutX(105-image.getHeight());
        imageView.setLayoutY(148);
        imageView.setRotate(90);

        BoxBlur boxBlur = new BoxBlur();
        boxBlur.setWidth(10);
        boxBlur.setHeight(3);
        boxBlur.setIterations(3);

        Ellipse ellipse = new Ellipse(45,14);
        ellipse.setCenterX(139);
        ellipse.setCenterY(173);
        ellipse.setFill(Color.WHITE);
        ellipse.setOpacity(0.5);
        ellipse.setEffect(boxBlur);

        arrow.getChildren().addAll(imageView,ellipse);
        getChildren().add(arrow);
    }

    public void arrowDown(){
        if(arrowIndex!=2){
            arrow.setLayoutY(arrow.getLayoutY()+50);
            arrowIndex++;
        }
    }

    public void arrowUp(){
        if(arrowIndex!=0){
            arrow.setLayoutY(arrow.getLayoutY()-50);
            arrowIndex--;
        }
    }

    public void loadArrowUp(){
        load.moveUp();
    }

    public void loadArrowDown(){
        load.moveDown();
    }

    public void load(){
        title.setOpacity(0);
        choices.setOpacity(0);
        arrow.setOpacity(0);

        load = new Load();

        getChildren().add(load);
    }

    public void loadBack(){
        getChildren().remove(load);
        load = null;

        title.setOpacity(1);
        choices.setOpacity(1);
        arrow.setOpacity(1);
    }

    public void choose(){
        load.choose();
    }

    public int makeChoice(){
        return arrowIndex;
    }

    public void reset(){
        arrowIndex=0;
        arrow.setLayoutY(0);
    }

    private class Load extends Group{
        private int arrowIndex = 0;
        private String [] states = new String [3];

        private Group arrow = new Group();
        private Group info = new Group();

        Load(){
            makeBoards();
            makeArrow();
            makeInfo();
        }

        private void makeInfo(){
            states = TxtReader.saveReader("save");
            Text [] t = new Text[3];

            for(int i=0;i<3;i++){
                if(states[i].length()==1){
                    t[i] = new Text("空");
                }else{
                    t[i]= new Text(states[i]);
                }

                t[i].setFont(Font.font("Verdana",18));
                t[i].setFill(Color.WHITE);
                t[i].setLayoutX(175-states[i].length()*4.5);
                t[i].setLayoutY(115+90*i);

                info.getChildren().add(t[i]);
            }
            getChildren().add(info);
        }

        public void choose(){
                System.out.println("加载存档"+arrowIndex);
        }

        private void makeBoards(){
            for(int i = 0;i<3;i++){
                ImageView imageView = new ImageView();
                Image image = new Image(Group_Start.class.getResource("Graphics/StartScene/LoadFile.png").toString(),160,60,false,false,false);

                imageView.setImage(image);
                imageView.setLayoutX(100);
                imageView.setLayoutY(80+90*i);

                getChildren().add(imageView);
            }
        }

        private void makeArrow(){
            ImageView imageView = new ImageView();
            Image image = new Image(Group_Start.class.getResource("Graphics/StartScene/sword.png").toString(),30,50,false,false,false);
            imageView.setImage(image);
            imageView.setLayoutX(105-image.getHeight());
            imageView.setLayoutY(85);
            imageView.setRotate(90);

            ImageView chose = new ImageView();
            Image c = new Image(Group_Start.class.getResource("Graphics/StartScene/LoadFileChose.png").toString(),156,56,false,false,false);
            chose.setImage(c);
            chose.setLayoutX(102.5);
            chose.setLayoutY(82.5);
            chose.setOpacity(0.5);

            arrow.getChildren().addAll(imageView,chose);

            getChildren().add(arrow);
        }

        public void moveDown(){
            if(arrowIndex!=2){
                arrowIndex++;
                arrow.setLayoutY(arrow.getLayoutY()+90);
            }
        }

        public void moveUp(){
            if(arrowIndex!=0){
                arrowIndex--;
                arrow.setLayoutY(arrow.getLayoutY()-90);
            }
        }
    }
}
