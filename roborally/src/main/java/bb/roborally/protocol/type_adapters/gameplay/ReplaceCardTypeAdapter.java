package bb.roborally.protocol.type_adapters.gameplay;

import bb.roborally.protocol.gameplay.ReplaceCard;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class ReplaceCardTypeAdapter extends TypeAdapter<ReplaceCard> {
    @Override
    public void write(JsonWriter jsonWriter, ReplaceCard replaceCard) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("register").value(replaceCard.getRegister());
        jsonWriter.name("newCard").value(replaceCard.getNewCard());
        jsonWriter.name("clientID").value(replaceCard.getClientID());
        jsonWriter.endObject();
    }

    @Override
    public ReplaceCard read(JsonReader jsonReader) throws IOException {
        ReplaceCard replaceCard = new ReplaceCard();
        jsonReader.beginObject();
        String name;
        while (jsonReader.hasNext()) {
            name = jsonReader.nextName();
            if (name.equals("register")) {
                replaceCard.setRegister(jsonReader.nextInt());
            }
            if (name.equals("newCard")) {
                replaceCard.setNewCard(jsonReader.nextString());
            }
            if (name.equals("clientID")) {
                replaceCard.setClientID(jsonReader.nextInt());
            }
        }
        jsonReader.endObject();
        return replaceCard;
    }
}
