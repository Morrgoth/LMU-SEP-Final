package bb.roborally.data.messages.type_adapters.game_events;

import bb.roborally.data.messages.game_events.SelectedDamage;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class SelectedDamageTypeAdapter extends TypeAdapter<SelectedDamage> {
    @Override
    public void write(JsonWriter jsonWriter, SelectedDamage selectedDamage) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("cards").value(selectedDamage.getCards());
        jsonWriter.endObject();
    }

    @Override
    public SelectedDamage read(JsonReader jsonReader) throws IOException {
        SelectedDamage selectedDamage = new SelectedDamage();
        jsonReader.beginObject();
        while(jsonReader.hasNext()) {
            String name = jsonReader.nextName();
            if(name.equals("cards")){
                selectedDamage.setCards(jsonReader.nextString());
            }
        }
        jsonReader.endObject();
        return selectedDamage;
    }
}
