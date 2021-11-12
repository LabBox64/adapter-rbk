package com.rbkmoney.adapter.payout.test.handler

import com.rbkmoney.adapter.bank.payout.spring.boot.starter.handler.CommonHandlerImpl
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.model.Step
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.processor.Processor
import com.rbkmoney.adapter.payout.test.client.RemoteClient
import com.rbkmoney.adapter.payout.test.client.model.BaseResponse
import com.rbkmoney.adapter.payout.test.client.model.StatusRequest
import com.rbkmoney.adapter.payout.test.model.EntryStateModelImpl
import com.rbkmoney.adapter.payout.test.model.ExitStateModelImpl
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class StatusHandler(
    remoteClient: RemoteClient,
    converter: Converter<EntryStateModelImpl, StatusRequest>,
    responseProcessorChain: Processor<BaseResponse, EntryStateModelImpl, ExitStateModelImpl>
) : CommonHandlerImpl<StatusRequest, BaseResponse, EntryStateModelImpl, ExitStateModelImpl>(
    remoteClient::status,
    converter,
    responseProcessorChain
) {
    override fun isHandle(entryStateModel: EntryStateModelImpl) =
        entryStateModel.state.step === Step.CHECK
}
