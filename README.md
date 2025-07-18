# Pecera
This project is an animated aquarium simulation built with JavaFX. 

### Requirements:
As its created in maven, we need to use the pom.xml to execute the libraries of JavaFX (this is required for JDK 9 and above).
Inside the pom.xml we have a plugin that manages and executes the libraries. 
#### For netbeans
We also need the nbaction.xml that triggers the plugin when running the application.

#### For IntelJ
Dont execute project from Run. Execute javafx:run from maven.
###### Alternative to maven. 
Execute "scripts/javafx_vm_opts". 

Use the correct script depending on your operating system. Copy the result.

Create a Run Configuration of type Application. Paste to "Vm options" the previous line.

With this configuration, JARs are imported automatically, ignoring Maven altogether.
If more modules are used, you have to add them manually.