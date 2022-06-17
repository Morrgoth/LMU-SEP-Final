package bb.roborally.data.messages.type_adapters;

import bb.roborally.data.messages.LogoutConfirmation;
import bb.roborally.game.User;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class LogoutConfirmationTypeAdapter extends TypeAdapter<LogoutConfirmation> {
    @Override
    public void write(JsonWriter jsonWriter, LogoutConfirmation logoutConfirmation) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("user").value(logoutConfirmation.getUser().getName());
        jsonWriter.endObject();
    }

    @Override
    public LogoutConfirmation read(JsonReader jsonReader) throws IOException {
        LogoutConfirmation logoutConfirmation = new LogoutConfirmation();
        jsonReader.beginObject();
        if (jsonReader.nextName().equals("user")) {
            User user = new User(jsonReader.nextString());
            logoutConfirmation.setUser(user);
        }
        jsonReader.endObject();
        return logoutConfirmation;
    }
}
