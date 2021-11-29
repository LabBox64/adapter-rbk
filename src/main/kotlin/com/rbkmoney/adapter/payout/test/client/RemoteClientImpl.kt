package com.rbkmoney.adapter.payout.test.client

import com.fasterxml.jackson.databind.ObjectMapper
import com.rbkmoney.adapter.payout.test.client.model.BaseResponse
import com.rbkmoney.adapter.payout.test.client.model.MoneyTransferRequest
import com.rbkmoney.adapter.payout.test.client.model.StatusRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class RemoteClientImpl(
    private val restTemplate: RestTemplate,
    private val objectMapper: ObjectMapper,
    @Value("\${adapter.url}") private val basePath: String
) : RemoteClient {

    override fun moneyTransfer(request: MoneyTransferRequest): BaseResponse {
        TODO("Not yet implemented")
    }

    override fun status(request: StatusRequest): BaseResponse {
        TODO("Not yet implemented")
    }
}
