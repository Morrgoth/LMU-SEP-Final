package bb.roborally.protocol.type_adapters.map;


import bb.roborally.protocol.map.tiles.*;
import bb.roborally.protocol.Orientation;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;


/**
 * @author Muqiu Wang
 * @author Veronika Heckel
 * @author tolgaengin
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
            jsonWriter.name("count").value(((CheckPoint) tile).getCount());
        }else if(tile instanceof EnergySpace){
            jsonWriter.name("type").value(tile.getType());
            jsonWriter.name("isOnBoard").value(tile.getIsOnBoard());
            jsonWriter.name("orientations");
            jsonWriter.beginArray();
            for(Orientation o: tile.getOrientations())
                jsonWriter.value(o.toString());
            jsonWriter.endArray();
            jsonWriter.name("count").value(((EnergySpace) tile).getCount());
        }else if(tile instanceof Gear){
            jsonWriter.name("type").value(tile.getType());
            jsonWriter.name("isOnBoard").value(tile.getIsOnBoard());
            jsonWriter.name("orientations");
            jsonWriter.beginArray();
            for(Orientation o: tile.getOrientations())
                jsonWriter.value(o.toString());
            jsonWriter.endArray();
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
        String name;
        String type = null;
        String isOnBoard = null;
        ArrayList<Orientation> orientations = new ArrayList<>();
        ArrayList<Integer> registers = new ArrayList<>();
        int speed = 0;
        int count = 0;
        while (jsonReader.hasNext()) {
            name = jsonReader.nextName();
            if (name.equals("type")) {
                type = jsonReader.nextString();
            } else if (name.equals("isOnBoard")) {
                isOnBoard = jsonReader.nextString();
            } else if (name.equals("orientations")) {
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    orientations.add(Orientation.toOrientation(jsonReader.nextString()));
                }
                jsonReader.endArray();
            } else if (name.equals("registers")) {
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    registers.add(jsonReader.nextInt());
                }
                jsonReader.endArray();
            } else if (name.equals("speed")) {
                speed = jsonReader.nextInt();
            } else if (name.equals("count")) {
                count = jsonReader.nextInt();
            }
        }
        jsonReader.endObject();
        if (type != null) {
            if (type.equals("Empty")) {
                Empty empty = new Empty();
                empty.setIsOnBoard(isOnBoard);
                tile = empty;

            } else if (type.equals("StartPoint")) {
                StartPoint startPoint = new StartPoint();
                startPoint.setIsOnBoard(isOnBoard);
                tile = startPoint;
            } else if (type.equals("ConveyorBelt")) {
                ConveyorBelt conveyorBelt = new ConveyorBelt();
                conveyorBelt.setSpeed(speed);
                conveyorBelt.setIsOnBoard(isOnBoard);
                conveyorBelt.setOrientations(orientations);
                tile = conveyorBelt;
            } else if (type.equals("PushPanel")) {
                PushPanel pushPanel = new PushPanel();
                pushPanel.setIsOnBoard(isOnBoard);
                pushPanel.setOrientations(orientations);
                pushPanel.setRegisters(registers);
                tile = pushPanel;
            } else if (type.equals("Laser")) {
                Laser laser = new Laser();
                laser.setIsOnBoard(isOnBoard);
                laser.setCount(count);
                laser.setOrientations(orientations);
                tile = laser;
            } else if (type.equals("CheckPoint")) {
                CheckPoint checkPoint = new CheckPoint();
                checkPoint.setIsOnBoard(isOnBoard);
                checkPoint.setCount(count);
                tile = checkPoint;
            } else if (type.equals("EnergySpace")) {
                EnergySpace energySpace = new EnergySpace();
                energySpace.setIsOnBoard(isOnBoard);
                energySpace.setOrientations(orientations);
                energySpace.setCount(count);
                tile = energySpace;
            } else if (type.equals("Gear")) {
                Gear gear = new Gear();
                gear.setIsOnBoard(isOnBoard);
                gear.setOrientations(orientations);
                tile = gear;
            } else if (type.equals("Antenna")) {
                Antenna antenna = new Antenna();
                antenna.setIsOnBoard(isOnBoard);
                antenna.setOrientations(orientations);
                tile = antenna;
            } else if (type.equals("Wall")) {
                Wall wall = new Wall();
                wall.setIsOnBoard(isOnBoard);
                wall.setOrientations(orientations);
                tile = wall;
            } else if (type.equals("RestartPoint")) {
                RestartPoint restartPoint = new RestartPoint();
                restartPoint.setIsOnBoard(isOnBoard);
                restartPoint.setOrientations(orientations);
                tile = restartPoint;
            } else if (type.equals("Pit")) {
                Pit pit = new Pit();
                pit.setIsOnBoard(isOnBoard);
                tile = pit;
            }
        }
        return tile;
    }

}
