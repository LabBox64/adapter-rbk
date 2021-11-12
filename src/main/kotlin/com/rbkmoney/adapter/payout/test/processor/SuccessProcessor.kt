package com.rbkmoney.adapter.payout.test.processor

import com.rbkmoney.adapter.bank.payout.spring.boot.starter.processor.Processor
import com.rbkmoney.adapter.payout.test.client.model.BaseResponse
import com.rbkmoney.adapter.payout.test.model.EntryStateModelImpl
import com.rbkmoney.adapter.payout.test.model.ExitStateModelImpl
import mu.KotlinLogging

class SuccessProcessor(
    private val next: Processor<BaseResponse, EntryStateModelImpl, ExitStateModelImpl>
) : Processor<BaseResponse, EntryStateModelImpl, ExitStateModelImpl> {

    private val log = KotlinLogging.logger { }

    override fun process(response: BaseResponse, entryStateModel: EntryStateModelImpl): ExitStateModelImpl {
        return ExitStateModelImpl()
    }

    companion object {
        fun isSuccess(response: BaseResponse?): Boolean =
            response != null && response.errorCode == null && response.errorMessage == null
    }
}
