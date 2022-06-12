package bb.roborally.data.messages.type_adapters;

import bb.roborally.data.messages.gameplay.SelectedCard;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class SelectedCardTypeAdapter extends TypeAdapter<SelectedCard> {

    @Override
    public void write(JsonWriter jsonWriter, SelectedCard selectedCard) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("card").value(selectedCard.getCard());
        jsonWriter.name("register").value(selectedCard.getRegister());
        jsonWriter.endObject();
    }

    @Override
    public SelectedCard read(JsonReader jsonReader) throws IOException {
        SelectedCard selectedCard = new SelectedCard();
        String name;
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            name = jsonReader.nextName();
            if (name.equals("card")) {
                selectedCard.setCard(jsonReader.nextString());
            }
            if (name.equals("register")) {
                selectedCard.setRegister(jsonReader.nextInt());
            }
        }
        jsonReader.endObject();
        return selectedCard;
    }
}
