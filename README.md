# No Idle

Prevent your Windows system and running programs from detecting inactivity by automatically jiggling the mouse after a certain period of idling.

Developed in Java to allow use in systems which prevent the use of external executables.

## Installing

These steps will allow you to download the latest compiled binary, and have it automatically run at start-up.

1. Download the compiled binary from the [latest release](https://github.com/paolorodriguez/noidle/releases/latest) and place it in any directory, such as in your user profile (i.e. home) directory
1. Create a shortcut which runs the binary, similar to `"C:\Program Files\Java\jdk-12\bin\javaw.exe" -jar "C:\Users\Your Profile\noidle.jar"`
1. Open `shell:startup` in Windows Explorer and move your newly-created shortcut to the startup folder

## Built With

* [OpenJDK 12](https://openjdk.java.net/)
* [Java Native Access](https://github.com/java-native-access/jna)

## Authors

* **Paolo Rodríguez** - *Initial work* - [paolorodriguez](https://github.com/paolorodriguez)

## License

This project is licensed under the GNU Affero General Public License - see the [COPYING](COPYING) file for details
