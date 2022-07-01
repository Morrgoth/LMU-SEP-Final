package bb.roborally.data.messages.type_adapters.map;


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
        }else if(tile instanceof BoardLaser){
            jsonWriter.name("type").value(tile.getType());
            jsonWriter.name("isOnBoard").value(tile.getIsOnBoard());
            jsonWriter.name("orientations");
            jsonWriter.beginArray();
            for(Orientation o: tile.getOrientations())
                jsonWriter.value(o.toString());
            jsonWriter.endArray();
            jsonWriter.name("count").value(((BoardLaser) tile).getCount());
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
            jsonWriter.name("remainedEnergyCube").value(((EnergySpace) tile).getRemainedEnergyCube());
        }else if(tile instanceof Gear){
            jsonWriter.name("type").value(tile.getType());
            jsonWriter.name("isOnBoard").value(tile.getIsOnBoard());
            jsonWriter.name("direction").value(((Gear) tile).getDirection());
        }else if(tile instanceof Antenna || tile instanceof Wall || tile instanceof RebootPoint){
            jsonWriter.name("type").value(tile.getType());
            jsonWriter.name("isOnBoard").value(tile.getIsOnBoard());
            jsonWriter.name("orientations");
            jsonWriter.beginArray();
            for(Orientation o: tile.getOrientations())
                jsonWriter.value(o.toString());
            jsonWriter.endArray();
        }else if(tile instanceof BlackHole || tile instanceof Floor
                || tile instanceof StartPoint){
            jsonWriter.name("type").value(tile.getType());
            jsonWriter.name("isOnBoard").value(tile.getIsOnBoard());
        }
        jsonWriter.endObject();
    }

    @Override
    public Tile read(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        Tile tile = new Tile();
        ArrayList<Orientation> orientations = new ArrayList<>();
        if("type".equals(jsonReader.nextName())){
            tile.setType(jsonReader.nextString());
            if(tile.getType().equals("ConveyorBelt")){
                tile.setIsOnBoard(jsonReader.nextString());
                ((ConveyorBelt) tile) .setSpeed(jsonReader.nextInt());
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    orientations.add(Orientation.valueOf(jsonReader.nextString()));
                }
                tile.setOrientations(orientations);
                jsonReader.endArray();
            }else if(tile.getType().equals("PushPanel")){
                tile.setIsOnBoard(jsonReader.nextString());
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    orientations.add(Orientation.valueOf(jsonReader.nextString()));
                }
                tile.setOrientations(orientations);
                jsonReader.endArray();
                jsonReader.beginArray();
                ArrayList<Integer> registers = new ArrayList<>();
                while (jsonReader.hasNext()){
                    registers.add(jsonReader.nextInt());
                }
                ((PushPanel) tile).setRegisters(registers);
                jsonReader.endArray();
            }else if(tile.getType().equals("Laser")){
                tile.setIsOnBoard(jsonReader.nextString());
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    orientations.add(Orientation.valueOf(jsonReader.nextString()));
                }
                tile.setOrientations(orientations);
                jsonReader.endArray();
                ((BoardLaser) tile).setCount(jsonReader.nextInt());
            }else if(tile.getType().equals("CheckPoint")){
                tile.setIsOnBoard(jsonReader.nextString());
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    orientations.add(Orientation.valueOf(jsonReader.nextString()));
                }
                tile.setOrientations(orientations);
                jsonReader.endArray();
                ((CheckPoint) tile).setNumber(jsonReader.nextInt());
            }else if(tile.getType().equals("EnergySpace")){
                tile.setIsOnBoard(jsonReader.nextString());
                ((EnergySpace) tile).setRemainedEnergyCube(jsonReader.nextInt());
            }else if(tile.getType().equals("Gear")){
                tile.setIsOnBoard(jsonReader.nextString());
                ((Gear) tile).setDirection(jsonReader.nextString());
            }else if (tile.getType().equals("Antenna") || tile.getType().equals("Wall") || tile.getType().equals("RebootPoint")){
                tile.setIsOnBoard(jsonReader.nextString());
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    orientations.add(Orientation.valueOf(jsonReader.nextString()));
                }
                tile.setOrientations(orientations);
                jsonReader.endArray();
            }else if(tile.getType().equals("BlackHole") || tile.getType().equals("Floor") || tile.getType().equals("StartPoint")){
                tile.setIsOnBoard(jsonReader.nextString());
            }
        }
        jsonReader.endObject();
        return tile;
    }
}