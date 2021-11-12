package com.rbkmoney.adapter.payout.test.client.converter

import org.springframework.http.ResponseEntity

abstract class ResponseConverter<T> {
    abstract fun convert(response: ResponseEntity<String>): T
}
