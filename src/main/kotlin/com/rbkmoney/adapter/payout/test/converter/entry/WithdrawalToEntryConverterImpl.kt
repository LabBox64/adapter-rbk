package com.rbkmoney.adapter.payout.test.converter.entry

import com.rbkmoney.adapter.bank.payout.spring.boot.starter.converter.WithdrawalToEntryStateConverter
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.state.deserializer.AdapterStateDeserializer
import com.rbkmoney.adapter.payout.test.model.EntryStateModelImpl
import com.rbkmoney.cds.client.storage.CdsClientStorage
import com.rbkmoney.damsel.msgpack.Value
import com.rbkmoney.damsel.withdrawals.provider_adapter.Withdrawal
import org.springframework.stereotype.Component

@Component
class WithdrawalToEntryConverterImpl(
    private val cdsClientStorage: CdsClientStorage,
    private val adapterStateDeserializer: AdapterStateDeserializer
) : WithdrawalToEntryStateConverter<EntryStateModelImpl> {

    override fun convert(
        withdrawal: Withdrawal,
        state: Value?,
        options: MutableMap<String, String>?
    ): EntryStateModelImpl {
        val entryModel = EntryStateModelImpl()
        val data = state?.let {
            if (state.isSetBin && state.bin.isNotEmpty()) {
                state.bin
            } else null
        }
        val adapterState = adapterStateDeserializer.read(data)
        val trxinfo = adapterState.trxInfo
        trxinfo?.trxExtra?.let {
            entryModel.state = adapterState
            entryModel.withdrawalId = withdrawal.getId()
            entryModel.amount = withdrawal.body.amount
            entryModel.currencyCode = withdrawal.body.currency.symbolicCode

            val cardData = cdsClientStorage.getCardData(withdrawal)
            entryModel.pan = cardData.pan
            entryModel.options = options
        }
        return entryModel
    }
}
