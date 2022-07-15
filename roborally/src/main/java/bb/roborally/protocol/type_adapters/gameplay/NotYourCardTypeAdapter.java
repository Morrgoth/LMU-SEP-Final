package bb.roborally.protocol.type_adapters.gameplay;

import bb.roborally.protocol.gameplay.NotYourCards;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * @author Veronika Heckel
 */
public class NotYourCardTypeAdapter extends TypeAdapter<NotYourCards> {
    @Override
    public void write(JsonWriter jsonWriter, NotYourCards notYourCards) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("ClientID").value(notYourCards.getClientID());
        jsonWriter.name("CardsInHand").value(notYourCards.getCardsInHand());
        jsonWriter.endObject();

    }

    @Override
    public NotYourCards read(JsonReader jsonReader) throws IOException {
        NotYourCards notYourCards = new NotYourCards();
        jsonReader.beginObject();
        String name;
        while(jsonReader.hasNext()){
            name = jsonReader.nextName();
            if(name.equals("ClientID")){
                notYourCards.setClientID(jsonReader.nextInt());
            }
            if(name.equals("CardsInHand")){
                notYourCards.setCardsInHand(jsonReader.nextInt());
            }
        }
        jsonReader.endObject();
        return notYourCards;
    }
}
