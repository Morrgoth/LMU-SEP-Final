package roborally.src.main.java.bb.roborally.data.messages.type_adapters;

import roborally.src.main.java.bb.roborally.data.messages.lobby_messages.PlayerValues;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import roborally.src.main.java.bb.roborally.data.messages.lobby_messages.SelectMap;

import java.io.IOException;

public class SelectMapTypeAdapter extends TypeAdapter <SelectMap>{
	@Override
	public void write (JsonWriter jsonWriter, PlayerValues playerValues) throws IOException {
		jsonWriter.beginObject();
			jsonWriter.name("availableMaps").value(SelectMap.getAvailableMaps());
		jsonWriter.endObject();
	}

	@Override
	public SelectMap read(JsonReader jsonReader) throws IOException {
		SelectMap selectMap = new SelectMap();
		jsonReader.beginObject();
			String name;
			while(jsonReader.hasNext()){
				name = jsonReader.nextName();
				if(name.equals("availableMaps")){
					selectMap.setAvailableMaps(jsonReader.nextString());
				}
			}
		jsonReader.endObject();
		return selectMap;
	}
}
