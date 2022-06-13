package bb.roborally.data.messages.type_adapters.lobby;

import bb.roborally.data.messages.lobby.PlayerValues;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class PlayerValuesTypeAdapter extends TypeAdapter<PlayerValues> {

	@Override
	public void write (JsonWriter jsonWriter, PlayerValues playerValues) throws IOException {
		jsonWriter.beginObject();
			jsonWriter.name("name").value(playerValues.getName());
			jsonWriter.name("figure").value(playerValues.getFigure());
		jsonWriter.endObject();
	}

	@Override
	public PlayerValues read(JsonReader jsonReader) throws IOException {
		PlayerValues playerValues = new PlayerValues();
		jsonReader.beginObject();
		String name;
		while(jsonReader.hasNext()){
			name = jsonReader.nextName();
			if(name.equals("name")){
				playerValues.setName(jsonReader.nextString());
			}
			if(name.equals("figure")){
				playerValues.setFigure(jsonReader.nextInt());
			}
		}
		jsonReader.endObject();
		return playerValues;
	}
}
