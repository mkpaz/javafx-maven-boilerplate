package boilerplate.desktop.view;

import boilerplate.desktop.theme.Theme;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.StringConverter;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign2.MaterialDesignG;
import org.kordamp.ikonli.materialdesign2.MaterialDesignL;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.util.Objects;

import static boilerplate.desktop.Resources.ASSETS_DIR;
import static boilerplate.desktop.Resources.getResourceAsStream;
import static boilerplate.desktop.theme.Theme.SUPPORTED_THEMES;

public class MainWindow extends BorderPane {

    private ComboBox<Theme> themeSelect;

    public MainWindow() {
        setTop(createTopPane());
        setCenter(createCentralPane());
        setPadding(new Insets(10));
    }

    public void selectTheme(Theme theme) {
        themeSelect.setValue(Objects.requireNonNull(theme));
    }

    private Node createTopPane() {
        themeSelect = new ComboBox<>(FXCollections.observableArrayList(SUPPORTED_THEMES));
        themeSelect.getStyleClass().add("theme-select");

        themeSelect.setConverter(new StringConverter<>() {
            @Override
            public String toString(Theme theme) {
                return theme.getName();
            }

            @Override
            public Theme fromString(String name) {
                return SUPPORTED_THEMES.stream()
                        .filter(theme -> Objects.equals(name, theme.getName()))
                        .findFirst()
                        .orElse(null);
            }
        });

        themeSelect.valueProperty().addListener((obs, oldTheme, newTheme) -> {
            Scene scene = getScene();
            if (scene == null) { return; }
            if (oldTheme != null) { scene.getStylesheets().removeAll(oldTheme.getStylesheets()); }
            if (newTheme != null) { scene.getStylesheets().addAll(newTheme.getStylesheets()); }
        });

        HBox topPane = new HBox();
        topPane.getChildren().setAll(
                horizontalSpacer(),
                themeSelect
        );

        return topPane;
    }

    private Pane createCentralPane() {
        VBox centralPane = new VBox();

        ImageView logo = new ImageView(new Image(getResourceAsStream(ASSETS_DIR + "logo.png")));
        logo.setFitHeight(200);
        logo.setFitWidth(200);

        HBox links = new HBox();
        links.setAlignment(Pos.CENTER);
        links.getChildren().setAll(
                createLink(MaterialDesignG.GITHUB, "Repository", URI.create(System.getProperty("app.homepage"))),
                createLink(MaterialDesignL.LANGUAGE_JAVA, "OpenJFX", URI.create("https://openjfx.io"))
        );
        links.setSpacing(20);

        centralPane.setSpacing(20);
        centralPane.setAlignment(Pos.CENTER);
        centralPane.getChildren().addAll(
                logo,
                new Label(System.getProperty("app.description")),
                links
        );

        return centralPane;
    }

    private Node createLink(Ikon graphic, String name, URI url) {
        Hyperlink link = new Hyperlink(name);
        link.setOnAction(event -> {
            final Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
            if (desktop == null || !desktop.isSupported(Desktop.Action.BROWSE)) { return; }

            new Thread(() -> {
                try {
                    desktop.browse(url);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        });

        HBox box = new HBox(
                new FontIcon(graphic),
                link
        );
        box.setSpacing(5);
        box.setAlignment(Pos.CENTER);

        return box;
    }

    private Region horizontalSpacer() {
        Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);
        return region;
    }
}
