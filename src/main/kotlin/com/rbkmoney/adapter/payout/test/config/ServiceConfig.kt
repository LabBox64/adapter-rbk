package com.rbkmoney.adapter.payout.test.config

import com.rbkmoney.adapter.bank.payout.spring.boot.starter.converter.WithdrawalToEntryStateConverter
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.flow.StepResolver
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.handler.CommonHandler
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.handler.GetQuoteHandler
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.handler.HandleCallbackHandler
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.service.PayoutAdapterService
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.service.PayoutAdapterServiceLogDecorator
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.validator.WithdrawalValidator
import com.rbkmoney.adapter.payout.test.converter.exit.ExitToProcessResultConverterImpl
import com.rbkmoney.adapter.payout.test.model.EntryStateModelImpl
import com.rbkmoney.adapter.payout.test.model.ExitStateModelImpl
import com.rbkmoney.damsel.withdrawals.provider_adapter.AdapterSrv
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class ServiceConfig {

    @Bean
    fun payoutAdapterService(
        withdrawalToEntryStateConverter: WithdrawalToEntryStateConverter<EntryStateModelImpl>,
        exitStateToProcessResultConverter: ExitToProcessResultConverterImpl,
        handlers: List<CommonHandler<EntryStateModelImpl, ExitStateModelImpl>>,
        stepResolver: StepResolver<EntryStateModelImpl, ExitStateModelImpl>,
        validator: WithdrawalValidator,
        getQuoteHandler: GetQuoteHandler,
        callbackResult: HandleCallbackHandler
    ): AdapterSrv.Iface =
        PayoutAdapterService(
            withdrawalToEntryStateConverter,
            exitStateToProcessResultConverter,
            handlers,
            stepResolver,
            validator,
            getQuoteHandler,
            callbackResult
        )

    @Bean
    @Primary
    fun payoutAdapterServiceLogDecorator(payoutAdapterService: AdapterSrv.Iface): AdapterSrv.Iface =
        PayoutAdapterServiceLogDecorator(payoutAdapterService)
}
