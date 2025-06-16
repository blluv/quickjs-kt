#include "timeout_interrupt_handler.h"
#include <stdint.h>

#ifdef _WIN32
    #include <windows.h>
#else
    #include <time.h>
#endif

uint64_t get_timestamp_ms() {
#ifdef _WIN32
    FILETIME ft;
    GetSystemTimePreciseAsFileTime(&ft);

    ULARGE_INTEGER uli;
    uli.LowPart = ft.dwLowDateTime;
    uli.HighPart = ft.dwHighDateTime;

    return (uint64_t)(uli.QuadPart / 10000);
#else
    struct timespec spec;
    clock_gettime(CLOCK_REALTIME, &spec);

    return (uint64_t)(spec.tv_sec) * 1000 + (uint64_t)(spec.tv_nsec / 1000000);
#endif
}

int timeout_interrupt_handler(JSRuntime *rt, void *opaque)
{
    uint64_t end_timestamp = *((uint64_t *)opaque);
    int isOver = get_timestamp_ms() >= end_timestamp;
    return isOver;
}