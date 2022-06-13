package bb.roborally.data.messages.type_adapters;

import bb.roborally.data.messages.gameplay.SetStartingPoint;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * @author Veronika Heckel
 */
public class SetStartingPointTypeAdapter extends TypeAdapter<SetStartingPoint> {

    @Override
    public void write(JsonWriter jsonWriter, SetStartingPoint setStartingPoint) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("x").value(setStartingPoint.getX());
        jsonWriter.name("y").value(setStartingPoint.getY());
        jsonWriter.endObject();
    }

    @Override
    public SetStartingPoint read(JsonReader jsonReader) throws IOException {
        SetStartingPoint setStartingPoint = new SetStartingPoint();
        jsonReader.beginObject();
        String name;
        while (jsonReader.hasNext()){
            name = jsonReader.nextName();
            if(name.equals("x")){
                setStartingPoint.setX(jsonReader.nextInt());
            }
            if (name.equals("y")){
                setStartingPoint.setY(jsonReader.nextInt());
            }
        }
        jsonReader.endObject();
        return setStartingPoint;
    }
}
