#!/bin/bash

# Busca todos los .jar de JavaFX para Linux (ajustable si usás otro SO)
JARS=$(find ~/.m2/repository/org/openjfx/ -name '*-linux.jar' | paste -sd: -)

# Verifica que se encontraron JARs
if [[ -z "$JARS" ]]; then
  echo "❌ No se encontraron .jar de JavaFX con sufijo '-linux.jar' en ~/.m2"
  echo "💡 Asegúrate de que Maven ya haya descargado las dependencias."
  exit 1
fi

# Imprime la línea completa lista para pegar en VM options
echo "--module-path \"$JARS\" --add-modules javafx.controls,javafx.graphics,javafx.base"