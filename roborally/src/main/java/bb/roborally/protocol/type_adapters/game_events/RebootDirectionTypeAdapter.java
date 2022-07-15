package bb.roborally.protocol.type_adapters.game_events;

import bb.roborally.protocol.game_events.RebootDirection;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class RebootDirectionTypeAdapter extends TypeAdapter<RebootDirection> {
    @Override
    public void write(JsonWriter jsonWriter, RebootDirection rebootDirection) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("direction").value(rebootDirection.getDirection());
        jsonWriter.endObject();
    }

    @Override
    public RebootDirection read(JsonReader jsonReader) throws IOException {
        RebootDirection rebootDirection = new RebootDirection();
        jsonReader.beginObject();
        while(jsonReader.hasNext()){
            String name = jsonReader.nextName();
            if(name.equals("direction")){
                rebootDirection.setDirection(jsonReader.nextString());
            }
        }
        jsonReader.endObject();
        return rebootDirection;
    }
}
