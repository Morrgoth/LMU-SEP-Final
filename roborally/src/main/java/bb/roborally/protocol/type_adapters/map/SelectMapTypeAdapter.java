package bb.roborally.protocol.type_adapters.map;

import bb.roborally.protocol.map.SelectMap;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;

public class SelectMapTypeAdapter extends TypeAdapter <SelectMap>{
	@Override
	public void write (JsonWriter jsonWriter, SelectMap selectMap) throws IOException {
		jsonWriter.beginObject();
		jsonWriter.name("availableMaps");
		jsonWriter.beginArray();
		for (String map: selectMap.getAvailableMaps()) {
			jsonWriter.value(map);
		}
		jsonWriter.endArray();
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
					ArrayList<String> maps = new ArrayList<>();
					jsonReader.beginArray();
					while (jsonReader.hasNext()) {
						maps.add(jsonReader.nextString());
					}
					selectMap.setAvailableMaps(maps.toArray(new String[0]));
					jsonReader.endArray();
				}
			}
		jsonReader.endObject();
		return selectMap;
	}
}
