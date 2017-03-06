#include "BackupDataSort.h"

void test_memory();
int get_from(int *data, int index);
void set_to(int *data, int index, int value);

void prepare_sort(double failure_rate);
void insertion_sort(int *data, int length);

JNIEXPORT void JNICALL Java_BackupDataSort_sort
(JNIEnv *env, jobject object, jintArray data, jdouble failure_rate) {
    int length;
    int *array;
    jboolean *is_copy = 0;

    length = (int) (*env)->GetArrayLength(env, data);
    array = (int *) (*env)->GetIntArrayElements(env, data, is_copy);

    prepare_sort((double) failure_rate);
    insertion_sort(array, length);

    (*env)->SetIntArrayRegion(env, data, 0, (jsize) length, (jint*) array);
}

int memoryAccesses = 0;
double failure_rate = 0;

void prepare_sort(double rate) {
    memoryAccesses = 0;
    failure_rate = rate;
}

void test_memory() {

}

int get_from(int *data, int index) {
    test_memory();
    memoryAccesses++;
    return data[index];
}

void set_to(int *data, int index, int value) {
    test_memory();
    memoryAccesses++;
    data[index] = value;
}

void swap(int *data, int index1, int index2) {
    int temp = data[index1];
    set_to(data, index1, get_from(data, index2));
    set_to(data, index2, temp);
}

void insertion_sort(int *data, int length) {
    for (int i = 0; i < length; i++) {
        int item_value = get_from(data, i);
        int compare_index = i;

        while (compare_index > 0) {
            if (get_from(data, compare_index - 1) > item_value) {
                swap(data, compare_index - 1, compare_index);
                compare_index--;
            } else {
                break;
            }
        }
    }
}