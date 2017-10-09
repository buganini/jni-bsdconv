#include <jni.h>
#include <bsdconv.h>
#include <stdint.h>
#include "io_buganini_Bsdconv.h"

JNIEXPORT jlong JNICALL Java_io_buganini_Bsdconv__1bsdconv_1create
  (JNIEnv *env, jobject self, jstring jconv){
    const char *conv = (*env)->GetStringUTFChars(env, jconv, NULL);

    if(conv==NULL){
        return 0;
    }
    struct bsdconv_instance *ins = bsdconv_create(conv);
    (*env)->ReleaseStringUTFChars(env, jconv, conv);

    return (uintptr_t) ins;
  }

JNIEXPORT jstring JNICALL Java_io_buganini_Bsdconv__1bsdconv_1pack
  (JNIEnv *env, jclass self, jlong jins){
    struct bsdconv_instance *ins = (struct bsdconv_instance *)(uintptr_t) jins;

    char *conv = bsdconv_pack(ins);
    jstring ret = (*env)->NewStringUTF(env, conv);
    free(conv);

    return ret;
  }