/**
 * Created by HE YE on 11/25/2017.
 */
public class Map {
    public int width = 0;
    public int height = 0;
    public String [] ground,items,transfers;

    Map(String mapName){
        String [] s = TxtReader.readTxt(mapName);

        try {
            this.width = Integer.parseInt(s[0]);
            this.height = Integer.parseInt(s[1]);
            this.ground = s[2].substring(1).split("@");
            this.items = s[3].substring(1).split("@");
            this.transfers = s[4].substring(1).split("@");
        }catch (Exception e){}
    }
}
