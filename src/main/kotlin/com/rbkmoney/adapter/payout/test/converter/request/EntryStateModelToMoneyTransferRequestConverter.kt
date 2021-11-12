package com.rbkmoney.adapter.payout.test.converter.request

import com.rbkmoney.adapter.payout.test.client.model.MoneyTransferRequest
import com.rbkmoney.adapter.payout.test.model.EntryStateModelImpl
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class EntryStateModelToMoneyTransferRequestConverter : Converter<EntryStateModelImpl, MoneyTransferRequest> {
    override fun convert(model: EntryStateModelImpl): MoneyTransferRequest {
        val options = model.options
        return MoneyTransferRequest("") 
    }
}
