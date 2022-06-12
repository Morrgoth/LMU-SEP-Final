package bb.roborally.data.messages.type_adapters;

import bb.roborally.data.messages.gameplay.CardSelected;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class CardSelectedTypeAdapter extends TypeAdapter<CardSelected> {
    @Override
    public void write(JsonWriter jsonWriter, CardSelected cardSelected) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("clientID").value(cardSelected.getClientID());
        jsonWriter.name("register").value(cardSelected.getRegister());
        jsonWriter.name("filled").value(cardSelected.isFilled());
        jsonWriter.endObject();
    }

    @Override
    public CardSelected read(JsonReader jsonReader) throws IOException {
        CardSelected cardSelected = new CardSelected();
        jsonReader.beginObject();
        String name;
        while (jsonReader.hasNext()) {
            name = jsonReader.nextName();
            if (name.equals("clientID")) {
                cardSelected.setClientID(jsonReader.nextInt());
            }
            if (name.equals("register")) {
                cardSelected.setRegister(jsonReader.nextInt());
            }
            if (name.equals("filled")) {
                cardSelected.setFilled(jsonReader.nextBoolean());
            }
        }
        jsonReader.endObject();
        return cardSelected;
    }
}
