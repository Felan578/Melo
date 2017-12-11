import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Created by HE YE on 11/26/2017.
 */
public class Group_Menu extends Group {
    private int currentIndex = 0;

    private Text[] choices = new Text[3];
    private  Rectangle r = new Rectangle(240,280, Color.PURPLE);
    private Group arrow = new Group();
    private Save s = new Save();

    Group_Menu(){
        makeBoard();
        makeArrow();
        makeChoices();
    }

    private void makeChoices(){

        choices[0] = new Text("返回游戏");
        choices[0].setFont(Font.font("Verdana",20));
        choices[0].setLayoutX(225);
        choices[0].setLayoutY(130);

        choices[1] = new Text("保存游戏");
        choices[1].setFont(Font.font("Verdana",20));
        choices[1].setLayoutX(225);
        choices[1].setLayoutY(180);

        choices[2] = new Text("返回菜单");
        choices[2].setFont(Font.font("Verdana",20));
        choices[2].setLayoutX(225);
        choices[2].setLayoutY(230);

        getChildren().addAll(choices);
    }

    private void makeBoard(){
        r.setLayoutX(150);
        r.setLayoutY(50);
        r.setOpacity(0.8);

        getChildren().add(r);
    }

    private void makeArrow(){
        BoxBlur boxBlur = new BoxBlur();
        boxBlur.setWidth(10);
        boxBlur.setHeight(3);
        boxBlur.setIterations(3);

        Ellipse ellipse = new Ellipse(45,14);
        ellipse.setCenterX(265);
        ellipse.setCenterY(122);
        ellipse.setFill(Color.LIGHTBLUE);
        ellipse.setOpacity(0.9);
        ellipse.setEffect(boxBlur);

        arrow.getChildren().add(ellipse);
        getChildren().add(arrow);
    }

    public void arrowDown(){
        if(currentIndex!=2) {
            arrow.setLayoutY(arrow.getLayoutY() + 50);
            currentIndex++;
        }
    }

    public void arrowUp(){
        if(currentIndex!=0){
            arrow.setLayoutY(arrow.getLayoutY()-50);
            currentIndex--;
        }
    }

    public void saveArrowDown(){
        s.arrowDown();
    }

    public void saveArrowUp(){
        s.arrowUp();
    }

    public void saveClose(){
        getChildren().remove(s);
        for(Node i:getChildren()){
            i.setOpacity(1);
        }

        r.setOpacity(0.8);

        s.reset();
    }

    public int choice(){
        return currentIndex;
    }

    public void reset(){
        currentIndex=0;
        arrow.setLayoutY(0);
    }

    public void save(){
        for(Node i:getChildren()){
            i.setOpacity(0);
        }
        getChildren().add(s);
    }

    private class Save extends Group{
        private int arrowIndex = 0;
        private String [] states = new String [3];

        private ImageView arrow = new ImageView();
        private Group info = new Group();

        Save(){
            makeBoard();
            makeChoices();
            makeArrow();
            makeInfo();
        }

        private void makeBoard(){
            Rectangle r = new Rectangle(240,340, Color.PURPLE);
            r.setLayoutX(150);
            r.setLayoutY(20);
            r.setOpacity(0.8);

            getChildren().add(r);
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
                t[i].setLayoutX(265-states[i].length()*4.5);
                t[i].setLayoutY(85+110*i);

                info.getChildren().add(t[i]);
            }
            getChildren().add(info);
        }

        private void makeChoices(){
            for(int i =0;i<3;i++) {
                ImageView imageView = new ImageView();
                Image image = new Image(Group_Menu.class.getResource("Graphics/StartScene/LoadFile.png").toString(),160,60,false,false,false);
                imageView.setImage(image);
                imageView.setLayoutX(190);
                imageView.setLayoutY(50+110*i);

                getChildren().add(imageView);
            }
        }

        private void makeArrow(){
            Image image = new Image(Group_Menu.class.getResource("Graphics/StartScene/LoadFileChose.png").toString(),156,56,false,false,false);
            arrow.setImage(image);
            arrow.setLayoutX(192.5);
            arrow.setLayoutY(52.5);

            getChildren().add(arrow);
        }

        public void arrowDown(){
            if(arrowIndex!=2){
                arrowIndex++;
                arrow.setLayoutY(arrow.getLayoutY()+110);
            }
        }

        public void arrowUp(){
            if(arrowIndex!=0){
                arrowIndex--;
                arrow.setLayoutY(arrow.getLayoutY()-110);
            }
        }

        public void reset(){
            arrowIndex=0;
            arrow.setLayoutY(52.5);
        }
    }
}
