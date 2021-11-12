package com.rbkmoney.adapter.payout.test.servlet

import com.rbkmoney.damsel.withdrawals.provider_adapter.AdapterSrv
import com.rbkmoney.woody.thrift.impl.http.THServiceBuilder
import javax.servlet.GenericServlet
import javax.servlet.Servlet
import javax.servlet.ServletConfig
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.annotation.WebServlet

@WebServlet("/adapter/test/payout")
class AdapterServlet(
    private val payoutAdapterServiceLogDecorator: AdapterSrv.Iface
) : GenericServlet() {

    private lateinit var servlet: Servlet

    override fun init(config: ServletConfig) {
        super.init(config)
        servlet = THServiceBuilder().build(AdapterSrv.Iface::class.java, payoutAdapterServiceLogDecorator)
    }

    override fun service(request: ServletRequest, response: ServletResponse) =
        servlet.service(request, response)
}
