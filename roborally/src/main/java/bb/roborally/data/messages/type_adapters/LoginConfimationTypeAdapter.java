package bb.roborally.data.messages.type_adapters;

import bb.roborally.data.messages.LoginConfirmation;
import bb.roborally.data.messages.LoginRequest;
import bb.roborally.data.util.User;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class LoginConfimationTypeAdapter extends TypeAdapter<LoginConfirmation> {
    @Override
    public void write(JsonWriter jsonWriter, LoginConfirmation loginConfirmation) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("user");
        jsonWriter.value(loginConfirmation.getUser().getName());
        jsonWriter.endObject();
    }

    @Override
    public LoginConfirmation read(JsonReader jsonReader) throws IOException {
        LoginConfirmation loginConfirmation = new LoginConfirmation();
        jsonReader.beginObject();
        if (jsonReader.nextName().equals("user")) {
            User user = new User(jsonReader.nextString());
            loginConfirmation.setUser(user);
        }
        jsonReader.endObject();
        return loginConfirmation;
    }
}
