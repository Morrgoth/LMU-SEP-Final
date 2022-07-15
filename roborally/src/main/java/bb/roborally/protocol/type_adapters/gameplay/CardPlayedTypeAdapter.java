package bb.roborally.protocol.type_adapters.gameplay;

import bb.roborally.protocol.gameplay.CardPlayed;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class CardPlayedTypeAdapter extends TypeAdapter<CardPlayed> {
    @Override
    public void write(JsonWriter jsonWriter, CardPlayed cardPlayed) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("clientID").value(cardPlayed.getClientID());
        jsonWriter.name("card").value(cardPlayed.getCard());
        jsonWriter.endObject();
    }

    @Override
    public CardPlayed read(JsonReader jsonReader) throws IOException {
        CardPlayed cardPlayed = new CardPlayed();
        jsonReader.beginObject();
        String name;
        while (jsonReader.hasNext()) {
            name = jsonReader.nextName();
            if (name.equals("clientID")) {
                cardPlayed.setClientID(jsonReader.nextInt());
            }
            if (name.equals("card")) {
                cardPlayed.setCard(jsonReader.nextString());
            }

        }
        jsonReader.endObject();
        return cardPlayed;
    }
}
