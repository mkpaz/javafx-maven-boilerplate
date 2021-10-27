package boilerplate.desktop;

import boilerplate.desktop.view.MainWindow;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import static boilerplate.desktop.Resources.*;
import static boilerplate.desktop.theme.Theme.SUPPORTED_THEMES;
import static java.nio.charset.StandardCharsets.UTF_8;

public class Launcher extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        loadApplicationProperties();
        Image appIcon = new Image(getResourceAsStream(ASSETS_DIR + "app-icon.png"));

        MainWindow mainWindow = new MainWindow();
        Scene scene = new Scene(mainWindow, 800, 600);
        scene.getStylesheets().addAll(
                FONTS_DIR + "index.css",
                STYLES_DIR + "index.css"
        );

        stage.setScene(scene);
        stage.setTitle(System.getProperty("app.name"));
        stage.getIcons().add(appIcon);
        stage.setResizable(false);
        mainWindow.selectTheme(SUPPORTED_THEMES.get(0));
        stage.setOnCloseRequest(t -> Platform.exit());

        Platform.runLater(() -> {
            stage.show();
            stage.requestFocus();
        });
    }

    private void loadApplicationProperties() {
        try {
            Properties properties = new Properties();
            properties.load(new InputStreamReader(getResourceAsStream(MODULE_DIR + "application.properties"), UTF_8));
            properties.forEach((key, value) -> System.setProperty(
                    String.valueOf(key),
                    String.valueOf(value)
            ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
