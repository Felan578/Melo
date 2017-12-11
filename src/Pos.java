import java.util.HashSet;

/**
 * Created by HE YE on 12/2/2017.
 */
public class Pos {
        public int x;
        public int y;

        Pos(int x, int y){
            this.x=x;
            this.y=y;
        }

        public static HashSet<Pos> remove(int x, int y,HashSet<Pos> target){
            Pos p = null;
            for(Pos i:target){
                if(i.x==x&&i.y==y){
                    p=i;
                }
            }
            target.remove(p);
            return target;
        }

        public static boolean cant(int direction,double x, double y,HashSet<Pos> target){
            switch (direction){
                case 0:
                    for(Pos p:target){
                        if(contain(p,x,y+78) || contain(p,x+36,y+78)) {
                            return true;
                        }
                    }
                    break;
                case 1:
                    for(Pos p:target){
                        if(contain(p,x-1,y+50) || contain(p,x-1,y+74))
                            return true;
                    }
                    break;
                case 2:
                    for(Pos p:target){
                        if(contain(p,x,y+48) || contain(p,x+37,y+48))
                            return true;
                    }
                    break;
                case 3:
                    for(Pos p:target){
                        if(contain(p,x+38,y+50) || contain(p,x+38,y+74))
                            return true;
                    }
                    break;
            }
            return false;
        }

        private static boolean contain(Pos p,double x,double y){
            return (int)Math.floor(x/40)==p.x && (int)Math.floor(y/40)==p.y;
        }

        public static void print(HashSet<Pos> h){
            for(Pos p:h){
                if(p.x==5&&p.y==5)
                    System.out.println(1);
            }
        }
}
