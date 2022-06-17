package bb.roborally.data.messages.type_adapters;

import bb.roborally.data.messages.ChatMessage;
import bb.roborally.game.User;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class ChatMessageTypeAdapter extends TypeAdapter<ChatMessage> {

    @Override
    public void write(JsonWriter jsonWriter, ChatMessage chatMessage) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("sender").value(chatMessage.getSender().getName());
        jsonWriter.name("message").value(chatMessage.getMessage());
        jsonWriter.endObject();
    }

    @Override
    public ChatMessage read(JsonReader jsonReader) throws IOException {
        ChatMessage chatMessage = new ChatMessage();
        jsonReader.beginObject();
        while(jsonReader.hasNext()) {
            String name = jsonReader.nextName();
            if (name.equals("sender")) {
                chatMessage.setSender(new User(jsonReader.nextString()));
            } else if (name.equals("message")) {
                chatMessage.setMessage(jsonReader.nextString());
            }
        }
        return chatMessage;
    }
}
