package bb.roborally.data.messages.type_adapters;

import bb.roborally.data.messages.gameplay.ActivePhase;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * @author Veronika Heckel
 */
public class ActivePhaseTypeAdapter extends TypeAdapter<ActivePhase> {
    @Override
    public void write(JsonWriter jsonWriter, ActivePhase activePhase) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("phase").value(activePhase.getPhase());
        jsonWriter.endObject();
    }

    @Override
    public ActivePhase read(JsonReader jsonReader) throws IOException {
        ActivePhase activePhase = new ActivePhase();
        jsonReader.beginObject();
        String name;
        while (jsonReader.hasNext()){
            name = jsonReader.nextName();
            if(name.equals("phase")){
                activePhase.setPhase(jsonReader.nextInt());
            }
        }
        jsonReader.endObject();
        return activePhase;
    }
}
