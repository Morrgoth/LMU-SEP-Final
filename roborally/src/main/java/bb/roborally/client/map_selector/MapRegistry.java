package bb.roborally.client.map_selector;

import bb.roborally.client.robot_selector.Orientation;

import java.util.ArrayList;

public class MapRegistry {
    ArrayList<Map> maps = new ArrayList<>();
    private final Map dizzyHighWay = new Map("Dizzy Highway", Orientation.RIGHT);
    private final Map extraCrispy = new Map("Extra Crispy", Orientation.RIGHT);
    private final Map lostBearings = new Map("Lost Bearings", Orientation.RIGHT);
    private final Map deathTrap = new Map("Death Trap", Orientation.LEFT);
    private final Map twister = new Map("Twister", Orientation.RIGHT);

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
        if(mapName.equals("Dizzy Highway")){
            return dizzyHighWay;
        } else if (mapName.equals("Extra Crispy")) {
            return extraCrispy;
        } else if (mapName.equals("Lost Bearings")) {
            return lostBearings;
        } else if (mapName.equals("Death Trap")) {
            return deathTrap;
        } else if (mapName.equals("Twister")) {
            return twister;
        }
        return null;
    }
}
