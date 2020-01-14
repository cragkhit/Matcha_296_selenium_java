package example;

// https://stackoverflow.com/questions/25061696/xsltproc-doesnt-recognize-xslt-2-0/25061774
// https://stackoverflow.com/questions/4604497/xslt-processing-with-java
// https://stackoverflow.com/questions/4604497/xslt-processing-with-java   
// NOTE: can possibly call saxon.jar directly 
// https://mvnrepository.com/artifact/net.sf.saxon/Saxon-HE

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class App {
	public static void main(String[] args)
			throws IOException, URISyntaxException, TransformerException {
		TransformerFactory factory = TransformerFactory.newInstance();
		Source xslt = new StreamSource(new File(args[1]));
		Transformer transformer = factory.newTransformer(xslt);

		Source text = new StreamSource(new File(args[0]));
		System.err
				.println(String.format("transforming %s with %s", args[0], args[1]));
		transformer.transform(text, new StreamResult(new File(args[2])));
	}
}
