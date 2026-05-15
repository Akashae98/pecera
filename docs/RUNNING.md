# Running Guide

This guide explains how to run the JavaFX aquarium after cloning the repository.

## Recommended Path

On Windows PowerShell, the simplest option is:

```powershell
.\run.ps1
```

That helper script:

- locates a usable JDK automatically
- sets `JAVA_HOME` for the current PowerShell session
- runs the Maven Wrapper with `clean javafx:run`

## Requirements

- JDK `24` or newer
- Internet access on first run so Maven Wrapper and JavaFX dependencies can be downloaded
- Optional: global Maven installation if you prefer `mvn` over the wrapper

## Check Your Environment

```bash
java -version
mvn -version
```

If `mvn` is not recognized, that is fine. The project includes Maven Wrapper.

## Run Options

### 1. Windows helper script

```powershell
.\run.ps1
```

Custom Maven goals:

```powershell
.\run.ps1 clean compile
.\run.ps1 javafx:run
```

### 2. Maven Wrapper

From the repository root:

```bash
./mvnw clean javafx:run
```

On Windows PowerShell:

```powershell
.\mvnw.cmd clean javafx:run
```

### 3. Compile first, then run

```bash
./mvnw clean compile
./mvnw javafx:run
```

On Windows PowerShell:

```powershell
.\mvnw.cmd clean compile
.\mvnw.cmd javafx:run
```

### 4. Global Maven installation

If Maven is installed globally, this should behave the same:

```bash
mvn clean javafx:run
```

## IDE Usage

### NetBeans

This project includes `nbactions.xml`, so the Run action uses `javafx:run`.

Recommended steps:

1. Open the project as a Maven project.
2. Select a JDK compatible with the version configured in `pom.xml`.
3. Click Run.

### IntelliJ IDEA

Open the project as a Maven project and run the Maven goal `javafx:run` from the Maven tool window.

## Troubleshooting

### `mvn` command not found

Use the wrapper instead:

```powershell
.\mvnw.cmd clean javafx:run
```

### Java version is too old

The project currently targets Java `24` in `pom.xml`.

Install JDK 24 or newer, or lower the configured release if you intentionally want to support an older JDK.

### `JAVA_HOME` is missing

On Windows, `mvnw.cmd` may fail even if `java` works in the terminal.

Temporary PowerShell example:

```powershell
$env:JAVA_HOME='C:\Program Files\Java\jdk-24'
.\mvnw.cmd clean javafx:run
```

### First dependency download failed

Retry with:

```powershell
.\mvnw.cmd clean compile
.\mvnw.cmd javafx:run
```

## Related Files

- `pom.xml`
- `mvnw`
- `mvnw.cmd`
- `run.ps1`
- `nbactions.xml`
