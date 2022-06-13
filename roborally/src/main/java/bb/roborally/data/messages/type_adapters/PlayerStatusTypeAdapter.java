package roborally.src.main.java.bb.roborally.data.messages.type_adapters;

import roborally.src.main.java.bb.roborally.data.messages.lobby.PlayerStatus;
import roborally.src.main.java.bb.roborally.data.messages.lobby.PlayerValues;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class PlayerStatusTypeAdapter extends TypeAdapter <PlayerStatus>{
	@Override
	public void write (JsonWriter jsonWriter, PlayerStatus playerStatus) throws IOException {
		jsonWriter.beginObject();
			jsonWriter.name("clientID").value(playerStatus.getClientID());
			jsonWriter.name("ready").value(playerStatus.isReady());
		jsonWriter.endObject();
	}

	@Override
	public PlayerValues read(JsonReader jsonReader) throws IOException {
		PlayerStatus playerStatus = new PlayerStatus();
		jsonReader.beginObject();
		String name;
		while(jsonReader.hasNext()){
			name = jsonReader.nextName();
			if(name.equals("clientID")){
				playerStatus.setClientID(jsonReader.nextInt());
			}
			if(name.equals("ready")){
				playerStatus.setReady(jsonReader.nextBoolean());
			}
		}
		jsonReader.endObject();
		return playerValues;
	}
}
