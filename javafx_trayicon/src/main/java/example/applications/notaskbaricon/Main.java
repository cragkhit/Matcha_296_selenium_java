package example.applications.notaskbaricon;
/*
 * Copyright (c) 2021 Michael Sims, Dustin Redmond and contributors
 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.net.URL;

import example.FXTrayIcon;
import example.applications.RunnableTest;

public class Main extends Application {

	@Override
	public void start(Stage stage) {
		URL iconFile = new RunnableTest().getIcon();
		stage.initStyle(StageStyle.UTILITY);
		stage.setHeight(0);
		stage.setWidth(0);
		Stage mainStage = new Stage();
		mainStage.initOwner(stage);
		mainStage.initStyle(StageStyle.UNDECORATED);

		Label label = new Label("No TaskBar Icon");
		Label label2 = new Label("Type a message and click the button");
		label2.setAlignment(Pos.CENTER_LEFT);
		TextField tfInput = new TextField();
		Button button = new Button("Show Alert");
		button.setOnAction(e -> showMessage(tfInput.getText()));

		VBox vbox = new VBox();
		vbox.setPadding(new Insets(10, 20, 10, 20));
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(20);
		vbox.getChildren().addAll(label, label2, tfInput, button);

		StackPane root = new StackPane();
		root.getChildren().add(vbox);
		mainStage.setScene(new Scene(root, 250, 200));
		mainStage.initStyle(StageStyle.UTILITY); // This is what makes the icon
																							// disappear in Windows.
		if (FXTrayIcon.isSupported()) {
			icon = new FXTrayIcon(stage, iconFile);

			MenuItem menuShowStage = new MenuItem("Show Stage");
			MenuItem menuHideStage = new MenuItem("Hide Stage");
			MenuItem menuShowMessage = new MenuItem("Show Message");
			MenuItem menuExit = new MenuItem("Exit");
			menuShowStage.setOnAction(e -> {
				Platform.runLater(() -> com.sun.javafx.application.PlatformImpl
						.setTaskbarApplication(false));
				mainStage.show();
			});
			menuHideStage.setOnAction(e -> {
				Platform.runLater(() -> com.sun.javafx.application.PlatformImpl
						.setTaskbarApplication(true));
				mainStage.hide();
			});
			menuShowMessage.setOnAction(e -> showMessage());
			menuExit.setOnAction(e -> System.exit(0));
			icon.addMenuItem(menuShowStage);
			icon.addMenuItem(menuHideStage);
			icon.addMenuItem(menuShowMessage);
			icon.addMenuItem(menuExit);
			icon.show();
		}

	}

	private FXTrayIcon icon;

	private void showMessage() {
		icon.showInfoMessage("Check It Out!", "Look Ma, No Taskbar Icon!");
	}

	private void showMessage(String message) {
		icon.showInfoMessage("Message For You!", message);
	}
}