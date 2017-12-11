import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashSet;

/**
 * Created by HE YE on 11/25/2017.
 */
public class Player extends ImageView {
    private final int SQUARE_SIZE;
    private final double Height = 80;
    private double Width;

    public double x;
    public double y;
    int mapHeight;
    int mapWidth;

    public boolean blocked [] = {false,false,false,false};

    private Image[] D = new Image[4];
    private Image[] L = new Image[4];
    private Image[] R = new Image[4];
    private Image[] B = new Image[4];

    public int direction = 0;
    private HashSet<Pos> notallowed;

    Player(HashSet<Pos> notallowed,int SQUARE_SIZE,int mapWidth,int mapHeight){
        Image image = new Image(Player.class.getResource("Graphics/character/D/Down3.png").toString(),0,Height,true,false,false);
        setImage(image);

        this.notallowed = notallowed;
        this.SQUARE_SIZE = SQUARE_SIZE;
        this.mapWidth=mapWidth;
        this.mapHeight=mapHeight;

        Width = image.getWidth();

        x=220-Width/2;
        y=300;

        setLayoutX(x);
        setLayoutY(y);

        getImages();
    }

    public void getImages(){
        for(int i=0;i<4;i++)
            D[i]= new Image(Player.class.getResource("Graphics/character/D/Down"+i+".png").toString(),0,Height,true,false,false);
        for(int i=0;i<4;i++)
            R[i]= new Image(Player.class.getResource("Graphics/character/R/Right"+i+".png").toString(),0,Height,true,false,false);
        for(int i=0;i<4;i++)
            L[i]= new Image(Player.class.getResource("Graphics/character/L/Left"+i+".png").toString(),0,Height,true,false,false);
        for(int i=0;i<4;i++)
            B[i]= new Image(Player.class.getResource("Graphics/character/B/Back"+i+".png").toString(),0,Height,true,false,false);
    }

    public void moveDown(int time,double speed) {
        setImage(D[time]);
        direction = 0;
        if (Pos.cant(0, x, y, notallowed)|| y+78>=mapHeight){
            blocked[0] = true;
        }
        if(!blocked[0]){
            y+=speed;
            setLayoutY(y);
            blocked[2]=false;
        }
    }

    public void moveBack(int time,double speed){
        setImage(B[time]);
        direction = 2;
        if(Pos.cant(2,x,y,notallowed)|| y+48<=0){
            blocked[2]=true;
        }
        if(!blocked[2]){
            y-=speed;
            setLayoutY(y);
            blocked[0]=false;
        }

    }

    public void moveLeft(int time,double speed){
        setImage(L[time]);
        direction = 1;
        if(Pos.cant(1,x,y,notallowed) || x-1<=0)
            blocked[1]=true;
        if(!blocked[1]){
            x-=speed;
            setLayoutX(x);
            blocked[3]=false;
        }
    }

    public void moveRight(int time,double speed){
        setImage(R[time]);
        direction = 3;
        if(Pos.cant(3,x,y,notallowed) || x+38>=mapWidth)
            blocked[3]=true;
        if(!blocked[3]){
            x+=speed;
            setLayoutX(x);
            blocked[1]=false;
        }
    }

    public void move(int direction,int time,double speed){
        time=time/5;
        switch (direction){
            case 0:
                moveDown(time,speed);
                break;
            case 1:
                moveLeft(time,speed);
                break;
            case 2:
                moveBack(time,speed);
                break;
            case 3:
                moveRight(time,speed);
                break;
        }
        setFalse();
    }

    public void stop(){
        switch (direction){
            case 0:
                setImage(D[3]);
                break;
            case 1:
                setImage(L[3]);
                break;
            case 2:
                setImage(B[3]);
                break;
            case 3:
                setImage(R[3]);
                break;
        }
    }

    private void setFalse(){
        for(int i=0;i<4;i++)
            blocked[i]=false;
    }

}
