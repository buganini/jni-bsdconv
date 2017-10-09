#include <jni.h>
#include <bsdconv.h>
#include <stdint.h>
#include "io_bsdconv_Bsdconv.h"

JNIEXPORT jlong JNICALL Java_io_bsdconv_Bsdconv__1bsdconv_1create
  (JNIEnv *env, jclass clazz, jstring jconv){
    const char *conv = (*env)->GetStringUTFChars(env, jconv, NULL);

    if(conv==NULL){
        return 0;
    }
    struct bsdconv_instance *ins = bsdconv_create(conv);
    (*env)->ReleaseStringUTFChars(env, jconv, conv);

    return (uintptr_t) ins;
  }

JNIEXPORT jbyteArray JNICALL Java_io_bsdconv_Bsdconv__1bsdconv_1conv
  (JNIEnv *env, jclass clazz, jlong jins, jbyteArray data_buf){
    struct bsdconv_instance *ins = (struct bsdconv_instance *)(uintptr_t) jins;
    int data_len = (*env)->GetArrayLength(env, data_buf);
    jbyte *jba = (*env)->GetByteArrayElements(env, data_buf, NULL);

    bsdconv_init(ins);
    ins->output_mode=BSDCONV_AUTOMALLOC;
    ins->input.data=(char *)jba;
    ins->input.len=data_len;
    ins->input.flags=0;
    ins->input.next=NULL;
    ins->flush=1;
    bsdconv(ins);

    (*env)->ReleaseByteArrayElements(env, data_buf, jba, 0);

    jbyteArray ret=(*env)->NewByteArray(env, ins->output.len);
    (*env)->SetByteArrayRegion(env, ret, 0, ins->output.len, ins->output.data);

    bsdconv_free(ins->output.data);

    return ret;
  }

JNIEXPORT void JNICALL Java_io_bsdconv_Bsdconv__1bsdconv_1init
  (JNIEnv *env, jclass clazz, jlong jins){
    struct bsdconv_instance *ins = (struct bsdconv_instance *)(uintptr_t) jins;
    bsdconv_init(ins);
  }


JNIEXPORT jstring JNICALL Java_io_bsdconv_Bsdconv__1bsdconv_1pack
  (JNIEnv *env, jclass self, jlong jins){
    struct bsdconv_instance *ins = (struct bsdconv_instance *)(uintptr_t) jins;

    char *conv = bsdconv_pack(ins);
    jstring ret = (*env)->NewStringUTF(env, conv);
    free(conv);

    return ret;
  }