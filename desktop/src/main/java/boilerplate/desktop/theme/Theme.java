package boilerplate.desktop.theme;

import java.util.List;
import java.util.Set;

import static boilerplate.desktop.Resources.STYLES_DIR;

public interface Theme {

    List<Theme> SUPPORTED_THEMES = List.of(
            new LightTheme(),
            new DarkTheme()
    );

    String THEME_DIR = STYLES_DIR + "theme/";

    String getName();

    Set<String> getStylesheets();

    boolean isLight();
}