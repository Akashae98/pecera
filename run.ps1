param(
    [Parameter(ValueFromRemainingArguments = $true)]
    [string[]]$MavenArgs = @("clean", "javafx:run")
)

Set-StrictMode -Version Latest
$ErrorActionPreference = "Stop"

$repoRoot = Split-Path -Parent $MyInvocation.MyCommand.Path
Set-Location $repoRoot

function Test-JdkHome {
    param([string]$Path)

    return $Path -and (Test-Path (Join-Path $Path "bin\java.exe"))
}

function Get-PreferredJdkHome {
    if (Test-JdkHome $env:JAVA_HOME) {
        return $env:JAVA_HOME
    }

    $candidates = @(
        "C:\Program Files\Java\jdk-24",
        "C:\Program Files\Java\latest"
    )

    foreach ($candidate in $candidates) {
        if (Test-JdkHome $candidate) {
            return $candidate
        }
    }

    $installedJdks = Get-ChildItem "C:\Program Files\Java" -Directory -ErrorAction SilentlyContinue |
        Where-Object { $_.Name -like "jdk-*" } |
        Sort-Object Name -Descending

    foreach ($jdk in $installedJdks) {
        if (Test-JdkHome $jdk.FullName) {
            return $jdk.FullName
        }
    }

    return $null
}

$jdkHome = Get-PreferredJdkHome
if (-not $jdkHome) {
    Write-Error "No JDK installation was found. Set JAVA_HOME to a JDK 24+ path and try again."
}

$env:JAVA_HOME = $jdkHome

Write-Host "Using JAVA_HOME=$env:JAVA_HOME"
Write-Host "Running: .\mvnw.cmd $($MavenArgs -join ' ')"

& ".\mvnw.cmd" @MavenArgs
exit $LASTEXITCODE
