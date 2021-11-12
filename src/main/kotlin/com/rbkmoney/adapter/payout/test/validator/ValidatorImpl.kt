package com.rbkmoney.adapter.payout.test.validator

import com.rbkmoney.adapter.bank.payout.spring.boot.starter.validator.WithdrawalValidator
import com.rbkmoney.damsel.msgpack.Value
import com.rbkmoney.damsel.withdrawals.provider_adapter.Withdrawal
import org.springframework.stereotype.Component

@Component
class ValidatorImpl : WithdrawalValidator {

    override fun validate(withdrawal: Withdrawal, state: Value, options: Map<String, String>) {
    }
}
