LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE := mail
LOCAL_SRC_FILES := mail.c

include $(BUILD_SHARED_LIBRARY)