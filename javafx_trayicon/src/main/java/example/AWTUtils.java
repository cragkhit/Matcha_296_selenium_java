package example;

/*
 * Copyright (c) 2021 Dustin K. Redmond & contributors
 */
import java.awt.MenuItem;
import java.util.StringJoiner;
import javafx.application.Platform;
import javafx.event.ActionEvent;

public class AWTUtils {

	public static MenuItem convertFromJavaFX(javafx.scene.control.MenuItem fxItem)
			throws UnsupportedOperationException {
		MenuItem awtItem = new MenuItem(fxItem.getText());

		// some JavaFX to AWT translations aren't possible of supported
		// build list of which unsupported methods have been called on
		// the passed JavaFX MenuItem
		StringJoiner sj = new StringJoiner(",");
		if (fxItem.getGraphic() != null) {
			sj.add("setGraphic()");
		}
		if (fxItem.getAccelerator() != null) {
			sj.add("setAccelerator()");
		}
		if (fxItem.getCssMetaData().size() > 0) {
			sj.add("getCssMetaData().add()");
		}
		if (fxItem.getOnMenuValidation() != null) {
			sj.add("setOnMenuValidation()");
		}
		if (fxItem.getStyle() != null) {
			sj.add("setStyle()");
		}
		String errors = sj.toString();
		if (!errors.isEmpty()) {
			throw new UnsupportedOperationException(
					String.format("The following methods were called on the "
							+ "passed JavaFX MenuItem (%s), these methods are not"
							+ "supported by FXTrayIcon.", errors));
		}

		// Set the onAction event to be performed via ActionListener action
		if (fxItem.getOnAction() != null) {
			awtItem.addActionListener(e -> Platform
					.runLater(() -> fxItem.getOnAction().handle(new ActionEvent())));
		}
		// Disable the MenuItem if the FX item is disabled
		awtItem.setEnabled(!fxItem.isDisable());

		return awtItem;
	}

}
