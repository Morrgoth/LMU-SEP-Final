package bb.roborally.protocol.type_adapters.game_events;

import bb.roborally.protocol.game_events.DrawDamage;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Zeynab Baiani
 */
public class DrawDamageTypeAdapter extends TypeAdapter<DrawDamage> {
    @Override
    public void write(JsonWriter jsonWriter, DrawDamage drawDamage) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("clientID").value(drawDamage.getClientID());
        jsonWriter.name("cards").beginArray();
        for (String card: drawDamage.getCards()) {
            jsonWriter.value(card);
        }
        jsonWriter.endArray();
        jsonWriter.endObject();
    }

    @Override
    public DrawDamage read(JsonReader jsonReader) throws IOException {
        DrawDamage drawDamage = new DrawDamage();
        jsonReader.beginObject();
        String name;
        while (jsonReader.hasNext()){
            name = jsonReader.nextName();
            if(name.equals("clientID")){
                drawDamage.setClientID(jsonReader.nextInt());
            }
            if(name.equals("cards")){
                ArrayList<String> cards = new ArrayList<>();
                jsonReader.beginArray();
                while(jsonReader.hasNext()) {
                    cards.add(jsonReader.nextString());
                }
                jsonReader.endArray();
                drawDamage.setCards(cards.toArray(new String[0]));
            }
    }
        jsonReader.endObject();
        return drawDamage;
}
}
