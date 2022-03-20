package wazzle.controller.common.files;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

final class CustomSerializer implements JsonSerializer<List<? extends Serializable>> {
	
	// I wanna cry :(
	
	@Override
	public JsonElement serialize(List<? extends Serializable> src, Type typeOfSrc, JsonSerializationContext context) {
        if (src == null) {
            return JsonNull.INSTANCE;
        }
        
        var array = new JsonArray();
        
        src.forEach(i -> {
        	JsonElement jsonElement = context.serialize(i, i.getClass());
            array.add(jsonElement);
        });
        
        return array;
	}
}
