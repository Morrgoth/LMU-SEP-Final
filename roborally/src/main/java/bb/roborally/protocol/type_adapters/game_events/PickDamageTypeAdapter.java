package bb.roborally.protocol.type_adapters.game_events;

import bb.roborally.protocol.game_events.PickDamage;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;

public class PickDamageTypeAdapter extends TypeAdapter<PickDamage> {
    @Override
    public void write(JsonWriter jsonWriter, PickDamage pickDamage) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("count").value(pickDamage.getCount());
        jsonWriter.name("availablePiles");
        jsonWriter.beginArray();
        for (String card: pickDamage.getAvailablePiles()) {
            jsonWriter.value(card);
        }
        jsonWriter.endArray();
        jsonWriter.endObject();
    }

    @Override
    public PickDamage read(JsonReader jsonReader) throws IOException {
        PickDamage pickDamage = new PickDamage();
        jsonReader.beginObject();
        String name;
        ArrayList<String> availablePiles = new ArrayList<>();
        while (jsonReader.hasNext()){
            name = jsonReader.nextName();
            if (name.equals("count")){
                pickDamage.setCount(jsonReader.nextInt());
            }
            if (name.equals("availablePiles")){
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    availablePiles.add(jsonReader.nextString());
                }
                jsonReader.endArray();
                pickDamage.setAvailablePiles(availablePiles.toArray(new String[0]));
            }
        }
        jsonReader.endObject();
        return pickDamage;
    }
}
