Add-Type -AssemblyName System.IO.Compression.FileSystem
$zip = Join-Path $PWD 'gradle-8.1.1-bin.zip'
$url = 'https://services.gradle.org/distributions/gradle-8.1.1-bin.zip'
Invoke-WebRequest -Uri $url -OutFile $zip
$zipFile = [System.IO.Compression.ZipFile]::OpenRead($zip)
$entry = $zipFile.Entries | Where-Object { $_.FullName -eq 'gradle-8.1.1/lib/gradle-wrapper.jar' }
if ($entry) {
    $targetDir = Join-Path $PWD 'gradle\wrapper'
    if (-not (Test-Path $targetDir)) { New-Item -ItemType Directory -Path $targetDir | Out-Null }
    $targetPath = Join-Path $targetDir 'gradle-wrapper.jar'
    $entry.ExtractToFile($targetPath, $true)
    Write-Host "Extracted wrapper jar to $targetPath"
} else {
    Write-Error "gradle-wrapper.jar entry not found in zip"
}
$zipFile.Dispose()
Remove-Item -Force $zip
