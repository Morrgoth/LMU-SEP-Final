package bb.roborally.data.messages.type_adapters.map;


import bb.roborally.data.messages.game_events.Energy;
import bb.roborally.game.Orientation;
import bb.roborally.game.tiles.*;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;


/**
 * @author Muqiu Wang
 * @author Veronika Heckel
 */
public class TileTypeAdapter extends TypeAdapter<Tile> {
    @Override
    public void write(JsonWriter jsonWriter, Tile tile) throws IOException {
        jsonWriter.beginObject();
        if(tile instanceof ConveyorBelt){
            jsonWriter.name("type").value(tile.getType());
            jsonWriter.name("isOnBoard").value(tile.getIsOnBoard());
            jsonWriter.name("speed").value(((ConveyorBelt) tile).getSpeed());
            jsonWriter.name("orientations");
            jsonWriter.beginArray();
            for(Orientation o: tile.getOrientations())
                jsonWriter.value(o.toString());
            jsonWriter.endArray();
        }else if(tile instanceof PushPanel){
            jsonWriter.name("type").value(tile.getType());
            jsonWriter.name("isOnBoard").value(tile.getIsOnBoard());
            jsonWriter.name("orientations");
            jsonWriter.beginArray();
            for(Orientation o: tile.getOrientations())
                jsonWriter.value(o.toString());
            jsonWriter.endArray();
            jsonWriter.name("registers");
            jsonWriter.beginArray();
            for(Integer r: ((PushPanel) tile).getRegisters())
                jsonWriter.value(r);
            jsonWriter.endArray();
        }else if(tile instanceof Laser){
            jsonWriter.name("type").value(tile.getType());
            jsonWriter.name("isOnBoard").value(tile.getIsOnBoard());
            jsonWriter.name("orientations");
            jsonWriter.beginArray();
            for(Orientation o: tile.getOrientations())
                jsonWriter.value(o.toString());
            jsonWriter.endArray();
            jsonWriter.name("count").value(((Laser) tile).getCount());
        }else if(tile instanceof CheckPoint){
            jsonWriter.name("type").value(tile.getType());
            jsonWriter.name("isOnBoard").value(tile.getIsOnBoard());
            jsonWriter.name("orientations");
            jsonWriter.beginArray();
            for(Orientation o: tile.getOrientations())
                jsonWriter.value(o.toString());
            jsonWriter.endArray();
            jsonWriter.name("number").value(((CheckPoint) tile).getNumber());
        }else if(tile instanceof EnergySpace){
            jsonWriter.name("type").value(tile.getType());
            jsonWriter.name("isOnBoard").value(tile.getIsOnBoard());
            jsonWriter.name("orientations");
            jsonWriter.beginArray();
            for(Orientation o: tile.getOrientations())
                jsonWriter.value(o.toString());
            jsonWriter.endArray();
            jsonWriter.name("remainedEnergyCube").value(((EnergySpace) tile).getRemainedEnergyCube());
        }else if(tile instanceof Gear){
            jsonWriter.name("type").value(tile.getType());
            jsonWriter.name("isOnBoard").value(tile.getIsOnBoard());
            jsonWriter.name("direction").value(((Gear) tile).getDirection());
        }else if(tile instanceof Antenna || tile instanceof Wall || tile instanceof RestartPoint){
            jsonWriter.name("type").value(tile.getType());
            jsonWriter.name("isOnBoard").value(tile.getIsOnBoard());
            jsonWriter.name("orientations");
            jsonWriter.beginArray();
            for(Orientation o: tile.getOrientations())
                jsonWriter.value(o.toString());
            jsonWriter.endArray();
        }else if(tile instanceof Pit || tile instanceof Empty
                || tile instanceof StartPoint){
            jsonWriter.name("type").value(tile.getType());
            jsonWriter.name("isOnBoard").value(tile.getIsOnBoard());
        }
        jsonWriter.endObject();
    }

    @Override
    public Tile read(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        Tile tile = null;
        ArrayList<Orientation> orientations = new ArrayList<>();
        while(jsonReader.hasNext()){
            String name = jsonReader.nextName();
            if(name.equals("type")){
                String type = jsonReader.nextString();
                if(type.equals("ConveyorBelt")){
                    ConveyorBelt conveyorBelt = new ConveyorBelt();
                    while(jsonReader.hasNext()){
                        String name1 = jsonReader.nextName();
                        if(name1.equals("isOnBoard")){
                            conveyorBelt.setIsOnBoard(jsonReader.nextString());
                        }else if(name1.equals("speed")){
                            conveyorBelt.setSpeed(jsonReader.nextInt());
                        }else if(name1.equals("orientations")){
                            jsonReader.beginArray();
                            while (jsonReader.hasNext()) {
                                orientations.add(Orientation.toOrientation(jsonReader.nextString()));
                            }
                            conveyorBelt.setOrientations(orientations);
                            jsonReader.endArray();
                        }
                    }
                    tile = conveyorBelt;
                }else if(type.equals("PushPanel")){
                    PushPanel pushPanel = new PushPanel();
                    while(jsonReader.hasNext()){
                        String name2 = jsonReader.nextName();
                        if(name2.equals("isOnBoard")){
                            pushPanel.setIsOnBoard(jsonReader.nextString());
                        }else if(name2.equals("orientations")){
                            jsonReader.beginArray();
                            while (jsonReader.hasNext()) {
                                orientations.add(Orientation.toOrientation(jsonReader.nextString()));
                            }
                            pushPanel.setOrientations(orientations);
                            jsonReader.endArray();
                        }else if(name2.equals("registers")){
                            jsonReader.beginArray();
                            ArrayList<Integer> registers = new ArrayList<>();
                            while (jsonReader.hasNext()){
                                registers.add(jsonReader.nextInt());
                            }
                            pushPanel.setRegisters(registers);
                            jsonReader.endArray();
                        }
                    }
                    tile = pushPanel;
                }else if(type.equals("Laser")){
                    Laser laser = new Laser();
                    while(jsonReader.hasNext()){
                        String name3 = jsonReader.nextName();
                        if(name3.equals("isOnBoard")){
                            laser.setIsOnBoard(jsonReader.nextString());
                        }else if(name3.equals("orientations")){
                            jsonReader.beginArray();
                            while (jsonReader.hasNext()) {
                                orientations.add(Orientation.toOrientation(jsonReader.nextString()));
                            }
                            laser.setOrientations(orientations);
                            jsonReader.endArray();
                        }else if(name3.equals("count")){
                            laser.setCount(jsonReader.nextInt());
                        }
                    }
                    tile = laser;
                }else if(type.equals("CheckPoint")){
                    CheckPoint checkPoint = new CheckPoint();
                    while(jsonReader.hasNext()){
                        String name4 = jsonReader.nextName();
                        if(name4.equals("isOnBoard")){
                            checkPoint.setIsOnBoard(jsonReader.nextString());
                        }else if(name4.equals("orientations")){
                            jsonReader.beginArray();
                            while (jsonReader.hasNext()) {
                                orientations.add(Orientation.toOrientation(jsonReader.nextString()));
                            }
                            checkPoint.setOrientations(orientations);
                            jsonReader.endArray();
                        }else if(name4.equals("number")){
                            checkPoint.setNumber(jsonReader.nextInt());
                        }
                    }
                    tile = checkPoint;
                }else if(type.equals("EnergySpace")){
                    EnergySpace energySpace = new EnergySpace();
                    while(jsonReader.hasNext()){
                        String name5 = jsonReader.nextName();
                        if(name5.equals("isOnBoard")){
                            energySpace.setIsOnBoard(jsonReader.nextString());
                        }else if(name5.equals("orientations")){
                            jsonReader.beginArray();
                            while (jsonReader.hasNext()) {
                                orientations.add(Orientation.toOrientation(jsonReader.nextString()));
                            }
                            energySpace.setOrientations(orientations);
                            jsonReader.endArray();
                        }else if(name5.equals("remainedEnergyCube")){
                            energySpace.setRemainedEnergyCube(jsonReader.nextInt());
                        }
                    }
                    tile = energySpace;
                }else if(type.equals("Gear")){
                    Gear gear = new Gear();
                    while(jsonReader.hasNext()){
                        String name6 = jsonReader.nextName();
                        if(name6.equals("isOnBoard")){
                            gear.setIsOnBoard(jsonReader.nextString());
                        }else if(name6.equals("direction")){
                            gear.setDirection(jsonReader.nextString());
                        }
                    }
                    tile = gear;
                }else if (type.equals("Antenna")){
                    Antenna antenna = new Antenna();
                    while(jsonReader.hasNext()){
                        String name7 = jsonReader.nextName();
                        if(name7.equals("isOnBoard")){
                            antenna.setIsOnBoard(jsonReader.nextString());
                        }else if(name7.equals("orientations")){
                            jsonReader.beginArray();
                            while (jsonReader.hasNext()) {
                                orientations.add(Orientation.toOrientation(jsonReader.nextString()));
                            }
                            antenna.setOrientations(orientations);
                            jsonReader.endArray();
                        }
                    }
                    tile = antenna;
                }else if(type.equals("Wall")){
                    Wall wall = new Wall();
                    while(jsonReader.hasNext()){
                        String name7 = jsonReader.nextName();
                        if(name7.equals("isOnBoard")){
                            wall.setIsOnBoard(jsonReader.nextString());
                        }else if(name7.equals("orientations")){
                            jsonReader.beginArray();
                            while (jsonReader.hasNext()) {
                                orientations.add(Orientation.toOrientation(jsonReader.nextString()));
                            }
                            wall.setOrientations(orientations);
                            jsonReader.endArray();
                        }
                    }
                    tile = wall;
                }else if(type.equals("RestartPoint")){
                    RestartPoint restartPoint = new RestartPoint();
                    while(jsonReader.hasNext()){
                        String name7 = jsonReader.nextName();
                        if(name7.equals("isOnBoard")){
                            restartPoint.setIsOnBoard(jsonReader.nextString());
                        }else if(name7.equals("orientations")){
                            jsonReader.beginArray();
                            while (jsonReader.hasNext()) {
                                orientations.add(Orientation.toOrientation(jsonReader.nextString()));
                            }
                            restartPoint.setOrientations(orientations);
                            jsonReader.endArray();
                        }
                    }
                    tile = restartPoint;
                }else if(type.equals("Pit")){
                    Pit pit = new Pit();
                    while(jsonReader.hasNext()){
                        if(jsonReader.nextName().equals("isOnBoard")){
                            pit.setIsOnBoard(jsonReader.nextString());
                        }
                    }
                    tile = pit;
                }else if(type.equals("Empty")){
                    Empty empty = new Empty();
                    while(jsonReader.hasNext()){
                        if(jsonReader.nextName().equals("isOnBoard")){
                            empty.setIsOnBoard(jsonReader.nextString());
                        }
                    }
                    tile = empty;
                }else if(type.equals("StartPoint")){
                    StartPoint startPoint = new StartPoint();
                    while(jsonReader.hasNext()){
                        if(jsonReader.nextName().equals("isOnBoard")){
                            startPoint.setIsOnBoard(jsonReader.nextString());
                        }
                    }
                    tile = startPoint;
                }
            }
        }
        jsonReader.endObject();
        return tile;
    }

}
