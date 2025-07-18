# Buscar los jars JavaFX para Windows
$jars = Get-ChildItem -Path $env:USERPROFILE\.m2\repository\org\openjfx -Filter "*-win.jar" -Recurse |
        ForEach-Object { $_.FullName } |
        Sort-Object |
        -join ";"

if (-not $jars) {
    Write-Host "No se encontraron archivos JavaFX para Windows en el repositorio local de Maven."
    exit 1
}

# Módulos a agregar
$modules = "javafx.controls,javafx.graphics,javafx.base"

# Imprimir línea para VM options
Write-Output "--module-path `"$jars`" --add-modules $modules"