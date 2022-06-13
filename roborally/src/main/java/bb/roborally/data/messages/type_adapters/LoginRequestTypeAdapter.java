package bb.roborally.data.messages.type_adapters;

import bb.roborally.data.messages.LoginRequest;
import bb.roborally.data.util.User;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class LoginRequestTypeAdapter extends TypeAdapter<LoginRequest> {
    @Override
    public void write(JsonWriter jsonWriter, LoginRequest loginRequest) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("user").value(loginRequest.getUser().getName());
        jsonWriter.endObject();
    }

    @Override
    public LoginRequest read(JsonReader jsonReader) throws IOException {
        LoginRequest loginRequest = new LoginRequest();
        jsonReader.beginObject();
        if (jsonReader.nextName().equals("user")) {
            User user = new User(jsonReader.nextString());
            loginRequest.setUser(user);
        }
        jsonReader.endObject();
        return loginRequest;
    }
}
