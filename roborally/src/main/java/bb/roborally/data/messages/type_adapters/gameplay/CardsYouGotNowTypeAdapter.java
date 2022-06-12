package bb.roborally.data.messages.type_adapters.gameplay;

import bb.roborally.data.messages.gameplay.CardsYouGotNow;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CardsYouGotNowTypeAdapter extends TypeAdapter<CardsYouGotNow> {
    @Override
    public void write(JsonWriter jsonWriter, CardsYouGotNow cardsYouGotNow) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("cards");
        jsonWriter.beginArray();
        for (String card: cardsYouGotNow.getCards()) {
            jsonWriter.value(card);
        }
        jsonWriter.endArray();
        jsonWriter.endObject();
    }

    @Override
    public CardsYouGotNow read(JsonReader jsonReader) throws IOException {
        CardsYouGotNow cardsYouGotNow = new CardsYouGotNow();
        List<String> cards = new ArrayList<>();
        jsonReader.beginObject();
        String name;
        while(jsonReader.hasNext()) {
            name = jsonReader.nextName();
            if (name.equals("cards")) {
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    cards.add(jsonReader.nextString());
                }
                cardsYouGotNow.setCards(cards.toArray(new String[0]));
                jsonReader.endArray();
            }
        }
        jsonReader.endObject();
        return cardsYouGotNow;
    }
}
