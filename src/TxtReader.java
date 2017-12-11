import java.io.*;

/**
 * Created by HE YE on 11/25/2017.
 */
public class TxtReader {
        public static String mapReader(String mapName){
            InputStream is=TxtReader.class.getResourceAsStream("/Maps/"+mapName+".txt");
            StringBuilder result = new StringBuilder();
            try{
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String s = null;
                while((s = br.readLine())!=null){
                    result.append(s);
                }
                br.close();
            }catch(Exception e){
                e.printStackTrace();
            }
            return result.toString();
        }

        public static String[] readTxt(String mapName){
            return mapReader(mapName).split(";");
        }

        public static String[] saveReader(String saveName){
            InputStream is=TxtReader.class.getResourceAsStream("/Save/"+saveName+".txt");
            StringBuilder result = new StringBuilder();
            try{
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String s = null;
                while((s = br.readLine())!=null){
                    result.append(s);
                }
                br.close();
            }catch(Exception e){
                e.printStackTrace();
            }
            return result.toString().split("\\.");
        }
}
