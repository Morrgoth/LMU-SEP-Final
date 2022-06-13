package bb.roborally.data.messages.type_adapters;

import bb.roborally.data.messages.lobby_messages.SetStatus;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class SetStatusTypeAdapter extends TypeAdapter <SetStatus>{

	@Override
	public void write (JsonWriter jsonWriter, SetStatus setStatus) throws IOException {
		jsonWriter.beginObject();
			jsonWriter.name("ready").value(setStatus.isReady());
		jsonWriter.endObject();
	}

	@Override
	public SetStatus read (JsonReader jsonReader) throws IOException {
		SetStatus setStatus = new SetStatus();
		jsonReader.beginObject();
			String name ;
			while(jsonReader.hasNext()){
				name = jsonReader.nextName();
				if(name.equals("ready")){
					setStatus.setReady(jsonReader.nextBoolean());
				}
			}
		jsonReader.endObject();
		return setStatus;
	}
}
