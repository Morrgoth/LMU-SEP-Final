package bb.roborally.protocol.type_adapters.map;

import bb.roborally.protocol.map.MapSelected;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class MapSelectedTypeAdapter extends TypeAdapter <MapSelected>{

	@Override
	public void write (JsonWriter jsonWriter, MapSelected mapSelected) throws IOException {
		jsonWriter.beginObject();
			jsonWriter.name("map").value(mapSelected.getMap());
		jsonWriter.endObject();
	}

	@Override
	public MapSelected read(JsonReader jsonReader) throws IOException {
		MapSelected mapSelected = new MapSelected();
		jsonReader.beginObject();
			String name;
			while(jsonReader.hasNext()){
				name = jsonReader.nextName();
				if(name.equals("map")){
					mapSelected.setMap(jsonReader.nextString());
				}
			}
		jsonReader.endObject();
		return mapSelected;
	}
}
