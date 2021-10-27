package boilerplate.desktop.theme;

import java.util.Set;

public class DarkTheme implements Theme {

    @Override
    public String getName() {
        return "Dark";
    }

    @Override
    public Set<String> getStylesheets() {
        return Set.of(THEME_DIR + "dark.css");
    }

    @Override
    public boolean isLight() {
        return false;
    }
}
