package bb.roborally.protocol.type_adapters.game_events;

import bb.roborally.protocol.game_events.CheckPointReached;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class CheckPointReachedTypeAdapter extends TypeAdapter<CheckPointReached> {
    @Override
    public void write(JsonWriter jsonWriter, CheckPointReached checkPointReached) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("clientID").value(checkPointReached.getClientID());
        jsonWriter.name("number").value(checkPointReached.getNumber());
        jsonWriter.endObject();
    }

    @Override
    public CheckPointReached read(JsonReader jsonReader) throws IOException {
        CheckPointReached checkPointReached = new CheckPointReached();
        jsonReader.beginObject();
        while(jsonReader.hasNext()){
            String name = jsonReader.nextName();
            if(name.equals("clientID")){
                checkPointReached.setClientID(jsonReader.nextInt());
            }
            if(name.equals("number")){
                checkPointReached.setNumber(jsonReader.nextInt());
            }
        }
        jsonReader.endObject();
        return checkPointReached;
    }
}
