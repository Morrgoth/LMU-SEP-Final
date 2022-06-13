package bb.roborally.data.messages.type_adapters;

import bb.roborally.data.messages.LoginError;
import bb.roborally.data.messages.LoginRequest;
import bb.roborally.data.util.User;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class LoginErrorTypeAdapter extends TypeAdapter<LoginError> {
    @Override
    public void write(JsonWriter jsonWriter, LoginError loginError) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("user").value(loginError.getUser().getName());
        jsonWriter.name("message").value(loginError.getMessage());
        jsonWriter.endObject();
    }

    @Override
    public LoginError read(JsonReader jsonReader) throws IOException {
        LoginError loginError = new LoginError();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            if (jsonReader.nextName().equals("user")) {
                User user = new User(jsonReader.nextString());
                loginError.setUser(user);
            }
            if (jsonReader.nextName().equals("message")) {
                loginError.setMessage(jsonReader.nextString());
            }
        }
        jsonReader.endObject();
        return loginError;
    }
}
