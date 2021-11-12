package com.rbkmoney.adapter.payout.test.handler

import com.rbkmoney.adapter.bank.payout.spring.boot.starter.handler.CommonHandlerImpl
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.model.Step
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.processor.Processor
import com.rbkmoney.adapter.payout.test.client.RemoteClient
import com.rbkmoney.adapter.payout.test.client.model.BaseResponse
import com.rbkmoney.adapter.payout.test.client.model.MoneyTransferRequest
import com.rbkmoney.adapter.payout.test.model.EntryStateModelImpl
import com.rbkmoney.adapter.payout.test.model.ExitStateModelImpl
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class MoneyTransferHandler(
    remoteClient: RemoteClient,
    converter: Converter<EntryStateModelImpl, MoneyTransferRequest>,
    responseProcessorChain: Processor<BaseResponse, EntryStateModelImpl, ExitStateModelImpl>
) : CommonHandlerImpl<MoneyTransferRequest, BaseResponse, EntryStateModelImpl, ExitStateModelImpl>(
    remoteClient::moneyTransfer,
    converter,
    responseProcessorChain
) {
    override fun isHandle(entryStateModel: EntryStateModelImpl) =
        entryStateModel.state.step === Step.PAYOUT
}
