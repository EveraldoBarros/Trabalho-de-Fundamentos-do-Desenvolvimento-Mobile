$env:JAVA_HOME = 'C:\Program Files\Microsoft\jdk-17.0.18.8-hotspot'
$env:Path = "$env:JAVA_HOME\bin;" + $env:Path
Write-Host "JAVA_HOME=$env:JAVA_HOME"
Write-Host "PATH contains Java=" + ($env:Path -like '*java.exe*')
Get-Command java | Format-List -Property Name,Source
.\gradlew.bat assembleDebug
