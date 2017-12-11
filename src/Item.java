/**
 * Created by HE YE on 11/29/2017.
 */
public enum Item {
    Table(2,2,0,-25,105,80,0,false,false),
    Tile(1,1,-0.5,-0.5,40,40,0,true,true);

    public final int occupiedX;
    public final int occupiedY;
    public final double imageHeight;
    public final double imageWidth;
    public final int rotation;
    public final double correctionX;
    public final double correctionY;
    public final boolean chooseRegion;
    public final boolean ground;

    private final String [] all = {"Table","Tile"};

    private int x;
    private int y;

    Item(int occupiedX, int occupiedY, double correctionX, double correctionY, double imageHeight, double imageWidth, int rotation, boolean chooseRegion, boolean ground){
        this.occupiedX = occupiedX;
        this.occupiedY = occupiedY;
        this.correctionX = correctionX;
        this.correctionY = correctionY;
        this.imageHeight = imageHeight;
        this.imageWidth = imageWidth;
        this.rotation = rotation;
        this.chooseRegion = chooseRegion;
        this.ground = ground;
    }

    public void setX(int x){
        this.x=x;
    }

    public void setY(int y) {
        this.y=y;
    }
}
