package bb.roborally.protocol.type_adapters.gameplay;

import bb.roborally.protocol.gameplay.CurrentCards;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CurrentCardsTypeAdapter extends TypeAdapter<CurrentCards> {
    @Override
    public void write(JsonWriter jsonWriter, CurrentCards currentCards) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("activeCards");
        jsonWriter.beginArray();
        for (Map.Entry<Integer, String> card: currentCards.getActiveCards().entrySet()) {
            jsonWriter.beginObject();
            jsonWriter.name("clientID").value(card.getKey());
            jsonWriter.name("card").value(card.getValue());
            jsonWriter.endObject();
        }
        jsonWriter.endArray();
        jsonWriter.endObject();
    }

    @Override
    public CurrentCards read(JsonReader jsonReader) throws IOException {
        CurrentCards currentCards = new CurrentCards();
        HashMap<Integer, String> activeCards = new HashMap<>();
        jsonReader.beginObject();
        String name;
        while(jsonReader.hasNext()) {
            name = jsonReader.nextName();
            if (name.equals("activeCards")) {
                jsonReader.beginArray();
                while(jsonReader.hasNext()) {
                    jsonReader.beginObject();
                    String key;
                    int clientID = 0;
                    String card = null;
                    while (jsonReader.hasNext()) {
                        key = jsonReader.nextName();
                        if (key.equals("clientID")) {
                            clientID = jsonReader.nextInt();
                        }
                        if (key.equals("card")) {
                            card = jsonReader.nextString();
                        }
                    }
                    activeCards.put(clientID, card);
                    jsonReader.endObject();
                }
                currentCards.setActiveCards(activeCards);
                jsonReader.endArray();
            }
        }
        jsonReader.endObject();
        return currentCards;
    }
}
