# Compilation & Installation

```
# native library
make
sudo make install PREFIX=/usr # or your prefix
# or just copy out/libbsdconv-jni.so to wherever in you java.library.path

# java library
gradle jar # build/libs/jni-bsdconv-${VERSION}.jar
```