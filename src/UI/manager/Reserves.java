package UI.manager;

import java.util.LinkedHashMap;

public class Reserves  extends  AdminLayout{
    public Reserves(int width, int height, String frameName) {
        super(width, height, frameName, true);

         selectQuery("reservation",new LinkedHashMap<>());


    }
}
