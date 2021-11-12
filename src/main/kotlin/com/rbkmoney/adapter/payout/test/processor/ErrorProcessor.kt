package com.rbkmoney.adapter.payout.test.processor

import com.rbkmoney.adapter.bank.payout.spring.boot.starter.processor.Processor
import com.rbkmoney.adapter.payout.test.client.model.BaseResponse
import com.rbkmoney.adapter.payout.test.model.EntryStateModelImpl
import com.rbkmoney.adapter.payout.test.model.ExitStateModelImpl

class ErrorProcessor : Processor<BaseResponse, EntryStateModelImpl, ExitStateModelImpl> {
    override fun process(response: BaseResponse, entryStateModel: EntryStateModelImpl): ExitStateModelImpl {
        return ExitStateModelImpl()
    }
}
