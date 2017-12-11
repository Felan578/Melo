import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * Created by HE YE on 11/27/2017.
 */
public class Group_Dialogue extends Group {
    private final String prefix = "             ";

    private Text text = new Text();
    private Group Board = new Group();

    private Name n = new Name(35,248);

    Group_Dialogue(){
        makeBoard(false);
        makeText("test");
    }

    Group_Dialogue(String name){
        n.setName(name);

        makeBoard(true);
        makeText("测试\n测试");

        getChildren().add(n);
    }

    private void makeBoard(boolean b){
        ImageView imageView = new ImageView();
        Image image = new Image(Group_Dialogue.class.getResource("Graphics/Game/Dialogue/Dialogue.png").toString(),520,0,true,true,false);
        imageView.setImage(image);

        imageView.setLayoutX(10);
        imageView.setLayoutY(400-image.getHeight());

        Board.getChildren().add(imageView);

        getChildren().add(Board);

        if(b){
            makeNameBoard();
        }
    }

    private void makeText(String s){
        text.setText(prefix+s);
        text.setLayoutX(60);
        text.setLayoutY(280);
        text.setFont(Font.font(16));
        getChildren().add(text);
    }

    private void makeNameBoard(){
        ImageView imageView = new ImageView();
        Image image = new Image(Group_Dialogue.class.getResource("Graphics/Game/Dialogue/NameBoard.png").toString(),n.getWidth()+30,35,false,false,false);

        imageView.setImage(image);
        imageView.setLayoutX(20);
        imageView.setLayoutY(240);

        Board.getChildren().add(imageView);

    }

    private class Name extends Group{
        private String name;
        private double nextX=0;
        private final double spaceBetween=1;

        public double x;
        public double y;


        Name(String name,double x, double y){
            this.name=name;
            this.x=x;
            this.y=y;

            drow();
        }

        Name(double x, double y){
            this.x=x;
            this.y=y;
        }

        public void setName(String name){
            this.name=name;
            nextX=0;
            drow();
        }

        public void drow(){

            for(int i =0;i<name.length();i++){
                String s = "";
                double height = 30;

                int index =name.charAt(i);

                if(index>96){
                    s="_";
                }
                if(index>=78&&index<=90||index>=110&index<=122)
                    height=0.9*height;

                ImageView imageView = new ImageView();
                Image image = new Image(Group_Dialogue.class.getResource("Graphics/Game/Dialogue/Font/" +name.charAt(i)+s+".png").toString(),0,height,true,false,false);

                imageView.setImage(image);
                imageView.setLayoutY(y);
                imageView.setLayoutX(x+nextX+spaceBetween);

                getChildren().add(imageView);

                nextX+=(image.getWidth()+spaceBetween);
            }
        }

        public double getWidth(){
            return nextX;
        }
    }

    public void moveOutOfView(){
        TranslateTransition tt = new TranslateTransition(new Duration(400));
        tt.setFromY(0);
        tt.setToY(180);

        ParallelTransition pt = new ParallelTransition(this,tt);
        pt.play();
    }

    public void moveIntoView(){
        TranslateTransition tt = new TranslateTransition(new Duration(400));
        tt.setFromY(180);
        tt.setToY(0);

        ParallelTransition pt = new ParallelTransition(this,tt);
        pt.play();
    }
}
