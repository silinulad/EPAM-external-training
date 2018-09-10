import java.io.IOException;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.xml.handlers.ResultHandler;

public class Runner {

	public static void main(String[] args) {
		final String FILE_NAME = "src/results.xml";
		XMLReader readerXML = null;
		ResultHandler handler = null;
		try {
			readerXML = XMLReaderFactory.createXMLReader();
			handler = new ResultHandler();
			readerXML.setContentHandler(handler);
			readerXML.parse(FILE_NAME);

			// print a results collection
			for (Result result : handler.getResults()) {
				System.out.println(result);
			}

		} catch (SAXException e) {
			final String SAX_ERROR = "SAX error ";
			System.err.print(SAX_ERROR + e.getMessage());
		} catch (IllegalArgumentException e) {
			System.err.print(FILE_NAME + e.getMessage());
		} catch (IOException e) {
			final String IO_ERROR = "I/Î error ";
			System.err.print(IO_ERROR + e.getMessage());
		}
	}
}
