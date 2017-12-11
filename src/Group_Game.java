import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


/**
 * Created by HE YE on 11/24/2017.
 */
public class Group_Game extends Group {
    private final int SQUARE_SIZE = 40;

    private Group Ground = new Group();
    private Group Items1 = new Group();
    private Group Items2 = new Group();

    private String [] ground;
    private String [] items;
    private String [] transfers;

    private double mapX;
    private double mapY;
    private int width;
    private int height;

    private HashSet<Pos> notAllowed = new HashSet<>();
    private HashMap<Pos,Boolean> transferPoints = new HashMap<>();

    public Player Melo;


    public void makeMap(String mapName){
        Map map = new Map(mapName);
        ground = map.ground;
        items = map.items;
        transfers = map.transfers;
        width = SQUARE_SIZE*map.width;
        height = SQUARE_SIZE*map.height;
    }

    public void makeGround(){
        for(String g: ground){
            try {
                String[] gd = g.split(",");
                ImageView imageView = new ImageView(new Image(Group_Game.class.getResource("Graphics/Inner/" + gd[0] + ".png").toString(), 40, 40, false, false, false));
                imageView.setLayoutX(SQUARE_SIZE*Integer.valueOf(gd[1]));
                imageView.setLayoutY(SQUARE_SIZE*Integer.valueOf(gd[2]));
                Ground.getChildren().add(imageView);
            }catch (Exception e){}
        }
    }

    public void makeItems(){
        for(String g: items){
            try {
                String[] id = g.split(",");
                int x =Integer.valueOf(id[1]);
                int y =Integer.valueOf(id[2]);
                fxItem f = new fxItem(new Image(Group_Game.class.getResource("Graphics/Inner/" + id[0] + ".png").toString(), Double.valueOf(id[5]), Double.valueOf(id[6]), false, false, false),x,y);
                f.setLayoutX(SQUARE_SIZE*x+Double.valueOf(id[7]));
                f.setLayoutY(SQUARE_SIZE*y+Double.valueOf(id[8]));
                Items1.getChildren().add(f);
                for(int i=0;i<Integer.valueOf(id[3]);i++){
                    for(int j=0;j<Integer.valueOf(id[4]);j++){
                        notAllowed.add(new Pos(x+i,y+j));
                    }
                }
            }catch (Exception e){}
        }
    }

    public void makeTransfers(){
        for(String s: transfers){
            String[] info = s.split(",");
            int x = Integer.valueOf(info[1]);
            int y =Integer.valueOf(info[2]);
            Pos p = new Pos(x,y);
            transferPoints.put(p,true);
        }
    }

    public void checkItems(){
        int y = (int)Math.floor((Melo.y+70)/40);
        ArrayList<fxItem> fl1 = new ArrayList<>();
        ArrayList<fxItem> fl2 = new ArrayList<>();
        for(Node i: Items1.getChildren()){
            try{
                fxItem fi = (fxItem)i;
                if(y<fi.y){
                    fl1.add(fi);
                }
            }catch (Exception e){}
        }
        for(Node i: Items2.getChildren()){
            try{
                fxItem fi = (fxItem)i;
                if(y>fi.y)
                    fl2.add(fi);
            }catch (Exception e){}
        }
        for(fxItem i:fl1){
            Items1.getChildren().remove(i);
            Items2.getChildren().add(i);
        }
        for(fxItem i:fl2){
            Items2.getChildren().remove(i);
            Items1.getChildren().add(i);
        }
    }


    Group_Game(){
        makeMap("Inner_map");
        makeGround();
        makeItems();
        makeTransfers();
        Melo = new Player(notAllowed,SQUARE_SIZE,width,height);

        getChildren().addAll(Ground,Items1,Melo,Items2);
        checkItems();
    }

    public void mapMove(double speed){
        switch (Melo.direction){
            case 0:
                if(!(Melo.y+mapY<=160 || mapY+height<=400 || Melo.blocked[0])){
                    mapY-=speed;
                    setLayoutY(mapY);
                }
                break;
            case 1:
                if(!(Melo.x+mapX>=240 || mapX>=0 || Melo.blocked[1])){
                    mapX+=speed;
                    setLayoutX(mapX);
                }
                break;

            case 2:
                if(!(Melo.y+mapY>=160 || mapY>=0 || Melo.blocked[2])){
                    mapY+=speed;
                    setLayoutY(mapY);
                }
                break;
            case 3:
                if(!(Melo.x+mapX<=240 || mapX+width<=540 || Melo.blocked[3])){
                    mapX-=speed;
                    setLayoutX(mapX);
                }
                break;
        }
    }

    private class fxItem extends ImageView{
        public int x;
        public int y;
        fxItem(Image image,int x, int y){
            this.x=x;
            this.y=y;
            setImage(image);
        }
    }
}
