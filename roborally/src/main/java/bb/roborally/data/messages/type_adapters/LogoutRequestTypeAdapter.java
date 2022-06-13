package bb.roborally.data.messages.type_adapters;

import bb.roborally.data.messages.LoginRequest;
import bb.roborally.data.messages.LogoutRequest;
import bb.roborally.data.util.User;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class LogoutRequestTypeAdapter extends TypeAdapter<LogoutRequest> {
    @Override
    public void write(JsonWriter jsonWriter, LogoutRequest logoutRequest) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("user").value(logoutRequest.getUser().getName());
        jsonWriter.endObject();
    }

    @Override
    public LogoutRequest read(JsonReader jsonReader) throws IOException {
        LogoutRequest logoutRequest = new LogoutRequest();
        jsonReader.beginObject();
        if (jsonReader.nextName().equals("user")) {
            User user = new User(jsonReader.nextString());
            logoutRequest.setUser(user);
        }
        jsonReader.endObject();
        return logoutRequest;
    }
}
