# Spigot Installer
SpigotInstaller install every stable version of spigot to remote or local maven repository.

## How to build
Run `./gradlew installDist` to build.
Build result will be generated under `build/install`

## Download compiled application
If you just want to download compiled application, download tar or zip from 
[jenkins](https://jenkins.heartpattern.io/job/HeartPattern/job/SpigotInstaller/job/master/)
and unzip it.

## How to run
### Install to local repository
Just execute `SpigotInstaller` or `SpigotInstaller.bat` in bin directory.
### Install to remote repository
Execute `SpigotInstaller` or `SpigotInstaller.bat` with --remote argument. You need to pass id and url of 
remote repository splited with `,`. Optionally, if your remote repository need credentials, you need to
configure credential in `settings.xml` file under `~/.m2` directory.

### Example usage
```
./SpigotInstaller
```
```
./SpigotInstaller.bat --remote MyRepository,https://maven.example.com/
```
