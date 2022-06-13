package bb.roborally.data.messages.type_adapters.game_events;

import bb.roborally.data.messages.game_events.Animation;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class AnimationTypeAdapter extends TypeAdapter<Animation> {
    @Override
    public void write(JsonWriter jsonWriter, Animation animation) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("type").value(animation.getType());
        jsonWriter.endObject();
    }

    @Override
    public Animation read(JsonReader jsonReader) throws IOException {
        Animation animation = new Animation();
        jsonReader.beginObject();
        while(jsonReader.hasNext()){
            String name = jsonReader.nextName();
            if(name.equals("type")){
                animation.setType(jsonReader.nextString());
            }
        }
        jsonReader.endObject();
        return animation;
    }
}
