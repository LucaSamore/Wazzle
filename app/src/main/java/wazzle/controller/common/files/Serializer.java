package wazzle.controller.common.files;

import java.io.IOException;
import java.util.List;

public interface Serializer<X> {
	void serialize(final String path, List<X> toBeWritten) throws IOException;
}
