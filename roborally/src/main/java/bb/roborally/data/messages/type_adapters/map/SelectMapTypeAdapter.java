package bb.roborally.data.messages.type_adapters.map;

import bb.roborally.data.messages.map.SelectMap;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class SelectMapTypeAdapter extends TypeAdapter <SelectMap>{
	@Override
	public void write (JsonWriter jsonWriter, SelectMap selectMap) throws IOException {
		jsonWriter.beginObject();
			jsonWriter.name("availableMaps").value(selectMap.getAvailableMaps());
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
