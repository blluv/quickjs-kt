#ifndef QJS_KT_TIMEOUT_INTERRUPT_HANDLER_H
#define QJS_KT_TIMEOUT_INTERRUPT_HANDLER_H

#include "quickjs.h"

uint64_t get_timestamp_ms();
int timeout_interrupt_handler(JSRuntime *runtime, void *end_timestamp);

#endif //QJS_KT_TIMEOUT_INTERRUPT_HANDLER_H