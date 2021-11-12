package com.rbkmoney.adapter.payout.test.converter.exit

import com.rbkmoney.adapter.bank.payout.spring.boot.starter.converter.ExitStateToProcessResultConverter
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.model.AdapterState
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.model.EntryStateModel
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.model.ExitStateModel
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.service.IntentService
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.state.serializer.AdapterStateSerializer
import com.rbkmoney.adapter.common.model.PollingInfo
import com.rbkmoney.adapter.payout.test.model.ExitStateModelImpl
import com.rbkmoney.damsel.msgpack.Value
import com.rbkmoney.damsel.withdrawals.provider_adapter.Intent
import com.rbkmoney.damsel.withdrawals.provider_adapter.ProcessResult
import mu.KotlinLogging
import org.springframework.stereotype.Component
import java.time.Instant

@Component
class ExitToProcessResultConverterImpl(
    private val stateSerializer: AdapterStateSerializer,
    private val intentService: IntentService
) : ExitStateToProcessResultConverter<ExitStateModelImpl> {

    private val log = KotlinLogging.logger { }

    override fun convert(exitStateModel: ExitStateModelImpl): ProcessResult {
        log.info { "ExitState converter. Exit state model: $exitStateModel" }

        if (exitStateModel.getErrorCode() != null) {
            return ProcessResult().setIntent(intentService.getFailureByCodeAndDesc(exitStateModel))
        }

        val entryStateModel: EntryStateModel = exitStateModel.entryStateModel
        val step = entryStateModel.state.step
        var intent = Intent()
        if (exitStateModel.nextState.pollingInfo == null) {
            initPollingInfo(exitStateModel)
        }
        val nextState: AdapterState = exitStateModel.nextState
        val processResult = ProcessResult().apply {
            setNextState(Value.bin(stateSerializer.writeByte(nextState)))
            setIntent(intent)
        }
        log.info { "ExitState converter. ProcessResult: $processResult" }
        return processResult
    }

    private fun initPollingInfo(exitStateModel: ExitStateModel) {
        val pollingInfo = PollingInfo().apply {
            startDateTimePolling = Instant.now()
            maxDateTimePolling = intentService.extractMaxDateTimeInstant(exitStateModel.entryStateModel)
        }
        exitStateModel.nextState.pollingInfo = pollingInfo
    }
}
