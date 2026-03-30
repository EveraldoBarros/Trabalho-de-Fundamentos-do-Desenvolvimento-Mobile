import pathlib
import urllib.request
import zipfile

url = 'https://services.gradle.org/distributions/gradle-8.1.1-bin.zip'
zip_path = pathlib.Path('gradle-8.1.1-bin.zip')

print('Downloading', url)
urllib.request.urlretrieve(url, zip_path)
print('Downloaded', zip_path)

with zipfile.ZipFile(zip_path, 'r') as z:
    entry = 'gradle-8.1.1/lib/gradle-wrapper.jar'
    dest = pathlib.Path('gradle/wrapper/gradle-wrapper.jar')
    dest.parent.mkdir(parents=True, exist_ok=True)
    with z.open(entry) as src, open(dest, 'wb') as out:
        out.write(src.read())
    print('Extracted', dest)

zip_path.unlink()
print('Removed zip', zip_path)
