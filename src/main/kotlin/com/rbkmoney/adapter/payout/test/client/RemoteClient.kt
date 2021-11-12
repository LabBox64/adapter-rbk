package com.rbkmoney.adapter.payout.test.client

import com.rbkmoney.adapter.payout.test.client.model.BaseResponse
import com.rbkmoney.adapter.payout.test.client.model.MoneyTransferRequest
import com.rbkmoney.adapter.payout.test.client.model.StatusRequest

interface RemoteClient {

    fun moneyTransfer(request: MoneyTransferRequest): BaseResponse

    fun status(request: StatusRequest): BaseResponse
}
