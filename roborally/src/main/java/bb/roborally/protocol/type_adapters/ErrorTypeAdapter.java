package bb.roborally.protocol.type_adapters;

import bb.roborally.protocol.Error;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * @author Zeynab Baiani
 */
public class ErrorTypeAdapter extends TypeAdapter<Error> {

    @Override
    public void write(JsonWriter jsonWriter, Error error) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("error").value(error.getError());
        jsonWriter.endObject();
    }

    @Override
    public Error read(JsonReader jsonReader) throws IOException {
        Error error = new Error();
        jsonReader.beginObject();
        String name;
        while (jsonReader.hasNext()){
            name = jsonReader.nextName();
            if (name.equals("error")){
                error.setError(jsonReader.nextString());
            }
        }
        jsonReader.endObject();
        return error;
    }
}
