package bb.roborally.protocol.type_adapters.lobby;

import bb.roborally.protocol.lobby.PlayerStatus;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * @author Philipp Keyzman
 */
public class PlayerStatusTypeAdapter extends TypeAdapter <PlayerStatus>{
	@Override
	public void write (JsonWriter jsonWriter, PlayerStatus playerStatus) throws IOException {
		jsonWriter.beginObject();
			jsonWriter.name("clientID").value(playerStatus.getClientID());
			jsonWriter.name("ready").value(playerStatus.isReady());
		jsonWriter.endObject();
	}

	@Override
	public PlayerStatus read(JsonReader jsonReader) throws IOException {
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
		return playerStatus;
	}
}
