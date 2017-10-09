PREFIX?=/usr
CFLAGS+=-O2 -Wall

all: jni

jni: src/main/jni/io_bsdconv_Bsdconv.c src/main/jni/io_bsdconv_Bsdconv.h
	$(CC) ${CFLAGS} src/main/jni/io_bsdconv_Bsdconv.c -L${PREFIX}/lib -o out/libbsdconv-jni.so -I${JAVA_HOME}/include/ -I${JAVA_HOME}/include/linux/ -shared -fPIC -lbsdconv

install:
	install -m 444 out/libbsdconv-jni.so ${PREFIX}/lib/jni

gen_header:
	javah -classpath src/main/java/ -d src/main/jni/ io.bsdconv.Bsdconv
