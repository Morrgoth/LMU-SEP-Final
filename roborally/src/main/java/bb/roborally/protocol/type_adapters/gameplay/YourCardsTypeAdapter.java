package bb.roborally.protocol.type_adapters.gameplay;

import bb.roborally.protocol.gameplay.YourCards;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Veronika Heckel
 */
public class YourCardsTypeAdapter extends TypeAdapter<YourCards> {

    @Override
    public void write(JsonWriter jsonWriter, YourCards yourCards) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("cardsInHand");
        jsonWriter.beginArray();
        for (String cardInHand: yourCards.getCardsInHand()) {
            jsonWriter.value(cardInHand);
        }
        jsonWriter.endArray();
        jsonWriter.endObject();
    }

    @Override
    public YourCards read(JsonReader jsonReader) throws IOException {
        YourCards yourCards = new YourCards();
        List<String> cardsInHand = new ArrayList<>();
        jsonReader.beginObject();
        String name;
        while(jsonReader.hasNext()) {
            name = jsonReader.nextName();
            if (name.equals("cardsInHand")) {
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    cardsInHand.add(jsonReader.nextString());
                }
                yourCards.setCardsInHand(cardsInHand.toArray(new String[0]));
                jsonReader.endArray();
            }
        }
        jsonReader.endObject();
        return yourCards;
    }
}


