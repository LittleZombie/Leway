//
// Created by user on 2017/5/21.
//

#include <stdio.h>
#include <string.h>
#include <jni.h>

jstring JNICALL Java_com_leway_taipei_1hejiang_OrderInfoActivity_getFromEmail(JNIEnv *env, jobject obj) {
return (*env)->NewStringUTF(env, "xxxxx@gmail.com");
}

jstring JNICALL Java_com_leway_taipei_1hejiang_OrderInfoActivity_getFromEmailP(JNIEnv *env, jobject obj){
return (*env)->NewStringUTF(env, "xxxxxx");
}

jstring JNICALL Java_com_leway_taipei_1hejiang_OrderInfoActivity_getToEmail(JNIEnv *env, jobject obj){
    return (*env)->NewStringUTF(env, "xxxx@gmail.com");
}
//n19920115@gmail.com