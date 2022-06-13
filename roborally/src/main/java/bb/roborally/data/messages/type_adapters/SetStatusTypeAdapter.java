package roborally.src.main.java.bb.roborally.data.messages.type_adapters;

import roborally.src.main.java.bb.roborally.data.messages.lobby.PlayerValues;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import roborally.src.main.java.bb.roborally.data.messages.lobby.SetStatus;

import java.io.IOException;

public class SetStatusTypeAdapter extends TypeAdapter <SetStatus>{

	@Override
	public void write (JsonWriter jsonWriter, SetStatus setStatus) throws IOException {
		jsonWriter.beginObject();
			jsonWriter.name("ready").value(setStatus.isReady());
		jsonWriter.endObject();
	}

	@Override
	public void read (JsonReader jsonReader) throws IOException {
		SetStatus setStatus = new SetStatus();
		jsonReader.beginObject();
		String name;
		while(jsonReader.hasNext()){
			if(name.equals("ready").nextBoolean()){
				setStatus.setReady(jsonReader.nextBoolean());
			}
		}
		jsonReader.endObject();
		return setStatus;
	}
}
