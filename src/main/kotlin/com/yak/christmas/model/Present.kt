package com.yak.christmas.model

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonProperty

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
data class Present(
        var id: Int,
        @JsonProperty (value = "presentName") var presentName: String,
        @JsonProperty (value = "link") var link: String? = null,
        var description: String? = null
        )