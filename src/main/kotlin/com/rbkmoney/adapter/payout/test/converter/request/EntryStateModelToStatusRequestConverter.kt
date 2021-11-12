package com.rbkmoney.adapter.payout.test.converter.request

import com.rbkmoney.adapter.payout.test.client.model.StatusRequest
import com.rbkmoney.adapter.payout.test.model.EntryStateModelImpl
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class EntryStateModelToStatusRequestConverter : Converter<EntryStateModelImpl, StatusRequest> {
    override fun convert(model: EntryStateModelImpl): StatusRequest {
        val options: Map<String, String> = model.options
        return StatusRequest("") 
    }
}
