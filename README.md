# Pecera
This project is an animated aquarium simulation built with JavaFX. 

### Requirements:
As its created in maven, we need to use the pom.xml to execute the libraries of JavaFX (this is required for JDK 9 and above).
Inside the pom.xml we have a plugin that manages and executes the libraries. 
#### For netbeans
We also need the nbaction.xml that triggers the plugin when running the application.

#### For IntelJ
Dont execute project from Run. Execute javafx:run from maven.