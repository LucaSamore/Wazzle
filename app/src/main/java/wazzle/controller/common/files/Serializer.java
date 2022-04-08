package wazzle.controller.common.files;

import java.io.IOException;
import java.util.List;

public interface Serializer<X> {
	@SuppressWarnings("unchecked")
	void serialize(final String path, final X... toBeWritten) throws IOException;
}
