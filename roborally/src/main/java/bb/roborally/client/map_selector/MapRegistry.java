package bb.roborally.client.map_selector;

import java.util.ArrayList;

public class MapRegistry {
    ArrayList<Map> maps = new ArrayList<>();
    private final Map dizzyHighWay = new Map("DizzyHighWay");
    private final Map extraCrispy = new Map("ExtraCrispy");
    private final Map lostBearings = new Map("LostBearings");
    private final Map deathTrap = new Map("DeathTrap");
    private final Map twister = new Map("Twister");

    public MapRegistry() {
        initializeMaps();
    }

    private void initializeMaps(){
        maps.add(dizzyHighWay);
        maps.add(extraCrispy);
        maps.add(lostBearings);
        maps.add(deathTrap);
        maps.add(twister);
    }

    public Map getMapByName(String mapName){
        if(mapName.equals("DizzyHighWay")){
            return dizzyHighWay;
        } else if (mapName.equals("ExtraCrispy")) {
            return extraCrispy;
        } else if (mapName.equals("LostBearings")) {
            return lostBearings;
        } else if (mapName.equals("DeathTrap")) {
            return deathTrap;
        } else if (mapName.equals("Twister")) {
            return twister;
        }
        return null;
    }
}
