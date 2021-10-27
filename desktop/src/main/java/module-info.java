module boilerplate.desktop {

    requires boilerplate.base;

    requires java.desktop;
    requires javafx.controls;

    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.materialdesign2;

    exports boilerplate.desktop;
    exports boilerplate.desktop.theme;
    exports boilerplate.desktop.view;

    // resources
    opens boilerplate.desktop.assets;
    opens boilerplate.desktop.assets.fonts;
    opens boilerplate.desktop.assets.styles;
    opens boilerplate.desktop.assets.styles.theme;
}