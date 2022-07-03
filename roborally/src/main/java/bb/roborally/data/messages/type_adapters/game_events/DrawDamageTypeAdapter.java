package bb.roborally.data.messages.type_adapters.game_events;

import bb.roborally.data.messages.game_events.DrawDamage;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class DrawDamageTypeAdapter extends TypeAdapter<DrawDamage> {
    @Override
    public void write(JsonWriter jsonWriter, DrawDamage drawDamage) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("clientID").value(drawDamage.getClientID());
        jsonWriter.name("cards").value(drawDamage.getCards());
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
                drawDamage.setCards(jsonReader.nextString());
            }
    }
        jsonReader.endObject();
        return drawDamage;
}
}
