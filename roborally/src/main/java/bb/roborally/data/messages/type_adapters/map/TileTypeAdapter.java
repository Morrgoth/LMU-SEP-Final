package bb.roborally.data.messages.type_adapters.map;

import bb.roborally.game.tiles.ConveyorBelt;
import bb.roborally.game.tiles.Tile;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;

public class TileTypeAdapter extends TypeAdapter<Tile> {
    @Override
    public void write(JsonWriter jsonWriter, Tile tile) throws IOException {

    }

    @Override
    public Tile read(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        Tile tile = new Tile();
        ArrayList<String> orientations = new ArrayList<>();
        if("type".equals(jsonReader.nextName())){
            tile.setType(jsonReader.nextString());
            if(tile.getType().equals("ConveyorBelt")){
                tile.setIsOnBoard(jsonReader.nextString());
                ((ConveyorBelt) tile) .setSpeed(jsonReader.nextInt());
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    orientations.add(jsonReader.nextString());
                }
                tile.setOrientations(orientations);
                jsonReader.endArray();
            }else if (tile.getType().equals("Wall")){
                tile.setIsOnBoard(jsonReader.nextString());
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    orientations.add(jsonReader.nextString());
                }
                tile.setOrientations(orientations);
                jsonReader.endArray();
            }else if(tile.getType().equals("Antenna")){
                tile.setIsOnBoard(jsonReader.nextString());
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    orientations.add(jsonReader.nextString());
                }
                tile.setOrientations(orientations);
                jsonReader.endArray();
            }else if(tile.getType().equals("BlackHole")){
                tile.setIsOnBoard(jsonReader.nextString());
            }else if(tile.getType().equals("Checkpoint")){
                tile.setIsOnBoard(jsonReader.nextString());
            }else if(tile.getType().equals("EnergySpace")){
                tile.setIsOnBoard(jsonReader.nextString());
            }else if(tile.getType().equals("Floor")){
                tile.setIsOnBoard(jsonReader.nextString());
            }else if(tile.getType().equals("Gear")){
                tile.setIsOnBoard(jsonReader.nextString());
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    orientations.add(jsonReader.nextString());
                }
                tile.setOrientations(orientations);
                jsonReader.endArray();
            }else if(tile.getType().equals("Laser")){
                tile.setIsOnBoard(jsonReader.nextString());
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    orientations.add(jsonReader.nextString());
                }
                tile.setOrientations(orientations);
                jsonReader.endArray();
            }else if(tile.getType().equals("PushPanel")){
                tile.setIsOnBoard(jsonReader.nextString());
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    orientations.add(jsonReader.nextString());
                }
                tile.setOrientations(orientations);
                jsonReader.endArray();
            }else if(tile.getType().equals("RebootPoint")){
                tile.setIsOnBoard(jsonReader.nextString());
            }else if(tile.getType().equals("StartPoint")){
                tile.setIsOnBoard(jsonReader.nextString());
            }
        }
        jsonReader.endObject();
        return tile;
    }
}
