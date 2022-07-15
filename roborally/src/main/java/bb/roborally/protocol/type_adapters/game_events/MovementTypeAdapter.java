package bb.roborally.protocol.type_adapters.game_events;

import bb.roborally.protocol.game_events.Movement;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class MovementTypeAdapter extends TypeAdapter<Movement> {
    @Override
    public void write(JsonWriter jsonWriter, Movement movement) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("clientID").value(movement.getClientID());
        jsonWriter.name("x").value(movement.getX());
        jsonWriter.name("y").value(movement.getY());
        jsonWriter.endObject();
    }

    @Override
    public Movement read(JsonReader jsonReader) throws IOException {
        Movement movement = new Movement();
        jsonReader.beginObject();
        while(jsonReader.hasNext()){
            String name = jsonReader.nextName();
            if(name.equals("clientID")){
                movement.setClientID(jsonReader.nextInt());
            }
            if(name.equals("x")){
                movement.setX(jsonReader.nextInt());
            }
            if(name.equals("y")){
                movement.setY(jsonReader.nextInt());
            }
        }
        jsonReader.endObject();
        return movement;
    }
}
