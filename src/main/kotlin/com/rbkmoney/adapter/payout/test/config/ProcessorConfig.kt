package com.rbkmoney.adapter.payout.test.config

import com.rbkmoney.adapter.bank.payout.spring.boot.starter.processor.Processor
import com.rbkmoney.adapter.payout.test.client.model.BaseResponse
import com.rbkmoney.adapter.payout.test.model.EntryStateModelImpl
import com.rbkmoney.adapter.payout.test.model.ExitStateModelImpl
import com.rbkmoney.adapter.payout.test.processor.ErrorProcessor
import com.rbkmoney.adapter.payout.test.processor.SuccessProcessor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ProcessorConfig {
    @Bean
    fun responseProcessorChain(): Processor<BaseResponse, EntryStateModelImpl, ExitStateModelImpl> {
        return SuccessProcessor(ErrorProcessor())
    }
}
