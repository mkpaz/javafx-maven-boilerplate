# JavaFX Maven boilerplate

Maven boilerplate project for creating native JavaFX desktop apps with reasonable defaults.

![alt](screenshot.png?raw=true)

## Features

- Multi-module project structure
- Includes some plugins and [dependencies](https://github.com/mkpaz/javafx-maven-template/blob/master/pom.xml#L57) you'll probably need anyway. Just make sure you're using the latest versions
- Includes basic assets structure (fonts, styles and themes), so you won't need to waste your time on this either
- Produces:
  - portable cross-platform artifact, namely Java [application image](https://docs.oracle.com/en/java/javase/17/jpackage/packaging-overview.html#GUID-DAE6A497-6E6F-4895-90CA-3C71AF052271)
  - Windows `exe` and `msi` installers
  - Linux [AppImage](https://appimage.org/) plus `deb` and `rpm` installers
- Doesn't require to store any platform specific artifacts in repository
- Release notes from [changelog](CHANGELOG.md) generation

Generated app size is around 60Mb, installers ~40Mb. It directly depends on how many modules you're using. Check `jlink` options to shrink image size even more.

## Requirements

- Java 17+, feel free to bump Java version when its out
- Maven 3.6+ or use bundled Maven wrapper for demo purposes
- For local build:
  - `exe`, `msi` - [Wix Toolset](https://wixtoolset.org/) is required
  - `deb` - [dpkg-dev](https://pkgs.org/download/dpkg-dev) and [fakeroot](https://pkgs.org/search/?q=fakeroot) required
  - `rpm` - [rpm-build](https://pkgs.org/download/rpm-build) is required

## Local build

To generate everything but native installers:

```shell
mvn clean package
```

To create a new release:

```shell
mvn clean install
```

To run the app:

```shell
# omit java.home if OS Java version is the same as project Java version
# more explanation in pom.xml
mvn javafx:run -Djava.home=<path-to-jdk>
```

To run the app in debug mode:

```shell
mvn javafx:run@debug
```

After that use you IDE to connect to the JVM via run configuration for remote debugging. Default port is `9090`.

## Github Actions

To issue a new release create a tag that matches `v*` and push it into remote repository. Check the [workflow](.github/workflows/tagged-release.yml) for details. Note that it uses your [changelog](CHANGELOG.md) to generate release notes, so don't forget to update it.

Also don't forget to update the workflow if you want to rename the desktop module.

## FAQ

### Why not Maven archetype?

Because it's easier to maintain a template as an ordinary project you can run and experiment with. Maven archetypes is good for bootstrapping but you can also use Bash to rename a project:

```shell
find . -depth -type f -regex ".*\.\(xml\|java\)$" -exec sed -i -e 's/boilerplate/myrootpackagename/g' {} \;
find . -depth -type d -name 'boilerplate' -execdir rename 's/boilerplate/myrootpackagename/' '{}' \+
```

### Can I use not modularized dependencies?

Absolutely. You have two options. Either inject `module-info.java` into existing library by using Moditect Maven plugin. You can find an example [here](desktop/pom.xml). Or use pre-packaged dependencies from [GuicedEE](https://guicedee.com/) project.

### macOS dmg support?

Not planned.
