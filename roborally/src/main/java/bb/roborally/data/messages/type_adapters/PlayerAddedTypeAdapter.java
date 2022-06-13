package bb.roborally.data.messages.type_adapters;

import bb.roborally.data.messages.lobby_messages.PlayerAdded;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class PlayerAddedTypeAdapter extends TypeAdapter<PlayerAdded> {

	@Override
	public void write (JsonWriter jsonWriter, PlayerAdded playerAdded) throws IOException {
		jsonWriter.beginObject();
			jsonWriter.name("clientID").value(playerAdded.getClientID());
			jsonWriter.name("name").value(playerAdded.getName());
			jsonWriter.name("figure").value(playerAdded.getFigure());
		jsonWriter.endObject();
	}

	@Override
	public PlayerAdded read(JsonReader jsonReader) throws IOException {
		PlayerAdded playerAdded = new PlayerAdded();
		jsonReader.beginObject();
		String name;
		while(jsonReader.hasNext()){
			name = jsonReader.nextName();
			if(name.equals("clientID")){
				playerAdded.setClientID(jsonReader.nextInt());
			}
			if(name.equals("name")){
				playerAdded.setName(jsonReader.nextString());
			}
			if(name.equals("figure")){
				playerAdded.setFigure(jsonReader.nextInt());
			}
		}
		jsonReader.endObject();
		return playerAdded;
	}
}
