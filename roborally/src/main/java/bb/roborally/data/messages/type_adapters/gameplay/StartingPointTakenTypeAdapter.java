package bb.roborally.data.messages.type_adapters.gameplay;

import bb.roborally.data.messages.gameplay.SetStartingPoint;
import bb.roborally.data.messages.gameplay.StartingPointTaken;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * @author Veronika Heckel
 */
public class StartingPointTakenTypeAdapter extends TypeAdapter<StartingPointTaken> {

    @Override
    public void write(JsonWriter jsonWriter, StartingPointTaken startingPointTaken) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("x").value(startingPointTaken.getX());
        jsonWriter.name("y").value(startingPointTaken.getY());
        jsonWriter.name("clientID").value(startingPointTaken.getClientID());
        jsonWriter.endObject();
    }

    @Override
    public StartingPointTaken read(JsonReader jsonReader) throws IOException {
        StartingPointTaken startingPointTaken = new StartingPointTaken();
        jsonReader.beginObject();
        String name;
        while(jsonReader.hasNext()){
            name = jsonReader.nextName();
            if(name.equals("x")){
                startingPointTaken.setX(jsonReader.nextInt());
            }
            if(name.equals("y")){
                startingPointTaken.setY(jsonReader.nextInt());
            }
            if(name.equals("clientID")){
                startingPointTaken.setClientID(jsonReader.nextInt());
            }
        }
        jsonReader.endObject();
        return startingPointTaken;
    }
}
