/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_baeldung_jni_ExampleObjectsJNI */

#ifndef _Included_com_baeldung_jni_ExampleObjectsJNI
#define _Included_com_baeldung_jni_ExampleObjectsJNI
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_baeldung_jni_ExampleObjectsJNI
 * Method:    createUser
 * Signature: (Ljava/lang/String;D)Lcom/baeldung/jni/UserData;
 */
JNIEXPORT jobject JNICALL Java_com_baeldung_jni_ExampleObjectsJNI_createUser
  (JNIEnv *, jobject, jstring, jdouble);

/*
 * Class:     com_baeldung_jni_ExampleObjectsJNI
 * Method:    printUserData
 * Signature: (Lcom/baeldung/jni/UserData;)V
 */
JNIEXPORT void JNICALL Java_com_baeldung_jni_ExampleObjectsJNI_printUserData
  (JNIEnv *, jobject, jobject);

#ifdef __cplusplus
}
#endif
#endif
