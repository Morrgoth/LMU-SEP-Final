package bb.roborally.data.messages.type_adapters;

import bb.roborally.data.messages.chat.SendChat;
import bb.roborally.data.messages.connection.HelloServer;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class SendChatTypeAdapter extends TypeAdapter <SendChat> {
    @Override
    public void write(JsonWriter jsonWriter, SendChat sendChat) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("message").value(sendChat.getMessage());
        jsonWriter.name("to").value(sendChat.getTo());
        jsonWriter.name("from").value(sendChat.getFrom());
        jsonWriter.name("isPrivate").value(sendChat.isPrivate());
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
            if(name.equals("from")){
                sendChat.setFrom(jsonReader.nextInt());
            }
            if(name.equals("isPrivate")){
                sendChat.setPrivate(jsonReader.nextBoolean());
            }
        }
        jsonReader.endObject();
        return sendChat;
    }
}
