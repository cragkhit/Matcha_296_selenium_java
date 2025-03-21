package example;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

@SuppressWarnings("restriction")
public class MarkdownView extends VBox {

	private SimpleStringProperty mdString = new SimpleStringProperty("");

	public MarkdownView(String mdString) {
		this.mdString.set(mdString);
		this.mdString.addListener((p, o, n) -> updateContent());
		getStylesheets().add("/css/mdfx.css");
		updateContent();
	}

	public MarkdownView() {
		this("");
	}

	private void updateContent() {
		MDFXNodeHelper content = new MDFXNodeHelper(this, mdString.getValue());
		getChildren().clear();
		getChildren().add(content);
	}

	public StringProperty mdStringProperty() {
		return mdString;
	}

	public void setMdString(String mdString) {
		this.mdString.set(mdString);
	}

	public String getMdString() {
		return mdString.get();
	}

	public boolean showChapter(int[] currentChapter) {
		return true;
	}

	public void setLink(Node node, String link, String description) {
		// TODO
		// com.jpro.web.Util.setLink(node, link, scala.Option.apply(description));
	}

	public Node generateImage(String url) {
		if (url.isEmpty()) {
			return new Group();
		} else {
			return new ImageView(new Image(url, true));
		}

	}
}
