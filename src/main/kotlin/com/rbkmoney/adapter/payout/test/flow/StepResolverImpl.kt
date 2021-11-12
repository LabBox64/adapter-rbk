package com.rbkmoney.adapter.payout.test.flow

import com.rbkmoney.adapter.bank.payout.spring.boot.starter.flow.StepResolver
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.model.Step
import com.rbkmoney.adapter.payout.test.model.EntryStateModelImpl
import com.rbkmoney.adapter.payout.test.model.ExitStateModelImpl
import org.springframework.stereotype.Component

@Component
class StepResolverImpl : StepResolver<EntryStateModelImpl, ExitStateModelImpl> {
    override fun resolveEntry(entryStateModel: EntryStateModelImpl): Step =
        entryStateModel.state.step ?: Step.PAYOUT

    override fun resolveExit(exitStateModel: ExitStateModelImpl): Step =
        exitStateModel.entryStateModel.state.step
}
