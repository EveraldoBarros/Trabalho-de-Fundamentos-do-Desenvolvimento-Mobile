@echo off
set "ZIP=gradle-8.1.1-bin.zip"
set "URL=https://services.gradle.org/distributions/gradle-8.1.1-bin.zip"
powershell -NoProfile -Command "Invoke-WebRequest -Uri '%URL%' -OutFile '%ZIP%'"
powershell -NoProfile -Command "Add-Type -AssemblyName System.IO.Compression.FileSystem; $zip=[System.IO.Compression.ZipFile]::OpenRead('%ZIP%'); $entry=$zip.Entries | Where-Object { $_.FullName -eq 'gradle-8.1.1/lib/gradle-wrapper.jar' }; if ($entry) { if (-not (Test-Path 'gradle\wrapper')) { New-Item -ItemType Directory -Path 'gradle\wrapper' | Out-Null }; $entry.ExtractToFile('gradle\wrapper\gradle-wrapper.jar',$true) }; $zip.Dispose()"
del /q "%ZIP%"
