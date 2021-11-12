package com.rbkmoney.adapter.payout.test.client.converter

import com.rbkmoney.adapter.payout.test.client.model.BaseRequest

abstract class RequestConverter<T> {
    abstract fun convert(request: T): BaseRequest
}
