package bb.roborally.protocol.type_adapters.game_events;

import bb.roborally.protocol.game_events.Energy;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * @author Muqiu Wang
 */
public class EnergyTypeAdapter extends TypeAdapter<Energy> {
    @Override
    public void write(JsonWriter jsonWriter, Energy energy) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("clientID").value(energy.getClientID());
        jsonWriter.name("count").value(energy.getCount());
        jsonWriter.name("source").value(energy.getSource());
        jsonWriter.endObject();
    }

    @Override
    public Energy read(JsonReader jsonReader) throws IOException {
        Energy energy = new Energy();
        jsonReader.beginObject();
        while(jsonReader.hasNext()){
            String name = jsonReader.nextName();
            if(name.equals("clientID")){
                energy.setClientID(jsonReader.nextInt());
            }
            if(name.equals("count")){
                energy.setCount(jsonReader.nextInt());
            }
            if(name.equals("source")){
                energy.setSource(jsonReader.nextString());
            }
        }
        jsonReader.endObject();
        return energy;
    }
}
