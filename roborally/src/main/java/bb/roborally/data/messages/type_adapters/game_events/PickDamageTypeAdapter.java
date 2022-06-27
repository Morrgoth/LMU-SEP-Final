package bb.roborally.data.messages.type_adapters.game_events;

import bb.roborally.data.messages.game_events.PickDamage;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class PickDamageTypeAdapter extends TypeAdapter<PickDamage> {
    @Override
    public void write(JsonWriter jsonWriter, PickDamage pickDamage) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("count").value(pickDamage.getCount());
        jsonWriter.name("availablePiles").value(pickDamage.getAvailablePiles());
        jsonWriter.endObject();
    }

    @Override
    public PickDamage read(JsonReader jsonReader) throws IOException {
        PickDamage pickDamage = new PickDamage();
        jsonReader.beginObject();
        String name;
        while (jsonReader.hasNext()){
            name = jsonReader.nextName();
            if (name.equals("count")){
                pickDamage.setCount(jsonReader.nextInt());
            }
            if (name.equals("availablePiles")){
                pickDamage.setAvailablePiles(jsonReader.nextString());
            }
        }
        jsonReader.endObject();
        return pickDamage;
    }
}
