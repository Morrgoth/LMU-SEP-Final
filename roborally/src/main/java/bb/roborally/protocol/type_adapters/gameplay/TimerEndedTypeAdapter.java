package bb.roborally.protocol.type_adapters.gameplay;

import bb.roborally.protocol.gameplay.TimerEnded;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bence Ament
 */
public class TimerEndedTypeAdapter extends TypeAdapter<TimerEnded> {
    @Override
    public void write(JsonWriter jsonWriter, TimerEnded timerEnded) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("clientIDs");
        jsonWriter.beginArray();
        for (int clientID: timerEnded.getClientIDs()) {
            jsonWriter.value(clientID);
        }
        jsonWriter.endArray();
        jsonWriter.endObject();
    }

    @Override
    public TimerEnded read(JsonReader jsonReader) throws IOException {
       TimerEnded timerEnded = new TimerEnded();
       List<Integer> clientIDs = new ArrayList<>();
       jsonReader.beginObject();
       String name;
       while(jsonReader.hasNext()) {
           name = jsonReader.nextName();
           if (name.equals("clientIDs")) {
               jsonReader.beginArray();
               while (jsonReader.hasNext()) {
                    clientIDs.add(jsonReader.nextInt());
               }
               timerEnded.setClientIDs(clientIDs.stream().mapToInt(i -> i).toArray());
               jsonReader.endArray();
           }
       }
       jsonReader.endObject();
       return timerEnded;
    }
}
