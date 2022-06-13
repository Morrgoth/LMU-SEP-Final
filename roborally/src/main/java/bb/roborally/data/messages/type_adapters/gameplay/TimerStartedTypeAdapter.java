package bb.roborally.data.messages.type_adapters.gameplay;

import bb.roborally.data.messages.gameplay.TimerStarted;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class TimerStartedTypeAdapter extends TypeAdapter<TimerStarted> {
    @Override
    public void write(JsonWriter jsonWriter, TimerStarted timerStarted) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.endObject();
    }

    @Override
    public TimerStarted read(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        jsonReader.endObject();
        return new TimerStarted();
    }
}
