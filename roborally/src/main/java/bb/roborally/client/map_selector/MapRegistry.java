package bb.roborally.client.map_selector;

import bb.roborally.client.robot_selector.Orientation;

import java.util.ArrayList;

public class MapRegistry {
    ArrayList<Map> maps = new ArrayList<>();
    private final Map dizzyHighWay = new Map("DizzyHighway", Orientation.RIGHT);
    private final Map extraCrispy = new Map("ExtraCrispy", Orientation.RIGHT);
    private final Map lostBearings = new Map("LostBearings", Orientation.LEFT);
    private final Map deathTrap = new Map("DeathTrap", Orientation.RIGHT);
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
        if(mapName.equals("DizzyHighway")){
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
