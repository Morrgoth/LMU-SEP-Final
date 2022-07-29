package bb.roborally.protocol.type_adapters.game_events;

import bb.roborally.protocol.game_events.SelectedDamage;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * @author Tolga Engin
 */
public class SelectedDamageTypeAdapter extends TypeAdapter<SelectedDamage> {
    @Override
    public void write(JsonWriter jsonWriter, SelectedDamage selectedDamage) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("cards");
        jsonWriter.beginArray();
        for (String card: selectedDamage.getCards()) {
            jsonWriter.value(card);
        }
        jsonWriter.endArray();
        jsonWriter.endObject();
    }

    @Override
    public SelectedDamage read(JsonReader jsonReader) throws IOException {
        SelectedDamage selectedDamage = new SelectedDamage();
        jsonReader.beginObject();
        ArrayList<String> cards = new ArrayList<>();
        while(jsonReader.hasNext()) {
            String name = jsonReader.nextName();
            if (name.equals("cards")) {
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    cards.add(jsonReader.nextString());
                }
                jsonReader.endArray();
                selectedDamage.setCards(cards.toArray(new String[0]));
            }
        }
        jsonReader.endObject();
        return selectedDamage;
    }
}
