package boilerplate.desktop;

import java.io.InputStream;
import java.util.Objects;

public final class Resources {

    public static final String MODULE_DIR = "/boilerplate/desktop/";
    public static final String ASSETS_DIR = "/boilerplate/desktop/assets/";
    public static final String FONTS_DIR = "/boilerplate/desktop/assets/fonts/";
    public static final String STYLES_DIR = "/boilerplate/desktop/assets/styles/";

    public static InputStream getResourceAsStream(String resource) {
        return Objects.requireNonNull(Launcher.class.getResourceAsStream(resource));
    }
}
