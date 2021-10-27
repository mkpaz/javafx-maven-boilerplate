package boilerplate.desktop.theme;

import java.util.Set;

public class LightTheme implements Theme {

    @Override
    public String getName() {
        return "Light";
    }

    @Override
    public Set<String> getStylesheets() {
        return Set.of(THEME_DIR + "light.css");
    }

    @Override
    public boolean isLight() {
        return true;
    }
}
