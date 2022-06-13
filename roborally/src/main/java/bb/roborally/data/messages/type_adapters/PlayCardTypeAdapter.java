package bb.roborally.data.messages.type_adapters;

import bb.roborally.data.messages.gameplay.PlayCard;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class PlayCardTypeAdapter extends TypeAdapter<PlayCard> {
    @Override
    public void write(JsonWriter jsonWriter, PlayCard playCard) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("card").value(playCard.getCard());
        jsonWriter.endObject();
    }

    @Override
    public PlayCard read(JsonReader jsonReader) throws IOException {
        PlayCard playCard = new PlayCard();
        jsonReader.beginObject();
        String name;
        while (jsonReader.hasNext()) {
            name = jsonReader.nextName();
            if (name.equals("card")) {
                playCard.setCard(jsonReader.nextString());
            }

        }
        jsonReader.endObject();
        return playCard;
    }
}
