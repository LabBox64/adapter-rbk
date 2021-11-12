package com.rbkmoney.adapter.payout.test.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.NotEmpty

@Validated
@ConstructorBinding
@ConfigurationProperties(prefix = "adapter")
data class AdapterTestPayoutProperties(
    @NotEmpty val url: String,
    @NotEmpty val username: String,
    @NotEmpty val password: String
)
