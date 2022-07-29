package bb.roborally.protocol.type_adapters.chat;

import bb.roborally.protocol.chat.SendChat;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * @author Zeynab Baiani
 */
public class SendChatTypeAdapter extends TypeAdapter <SendChat> {
    @Override
    public void write(JsonWriter jsonWriter, SendChat sendChat) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("message").value(sendChat.getMessage());
        jsonWriter.name("to").value(sendChat.getTo());
        jsonWriter.endObject();
    }

    @Override
    public SendChat read(JsonReader jsonReader) throws IOException {
        SendChat sendChat = new SendChat();
        jsonReader.beginObject();
        String name;
        while (jsonReader.hasNext()){
            name = jsonReader.nextName();
            if(name.equals("message")){
                sendChat.setMessage(jsonReader.nextString());
            }
            if(name.equals("to")){
                sendChat.setTo(jsonReader.nextInt());
            }
        }
        jsonReader.endObject();
        return sendChat;
    }
}
