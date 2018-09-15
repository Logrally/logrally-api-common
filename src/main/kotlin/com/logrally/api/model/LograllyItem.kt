/**
 * Copyright 2018 Joachim Ansorg
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.logrally.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.nio.charset.StandardCharsets
import java.security.MessageDigest

/**
 * An item in the Logrally database.
 *
 * @author jansorg
 */
@JsonClass(generateAdapter = true)
data class LograllyItem(
        val language: String,
        val stack: String,
        val projectId: String,
        @Json(name = "occurrences") internal val _occurrences: Int? = null,
        @Json(name = "guid") internal val _guid: String? = null,
        @Json(name = "id") internal val _id: String? = null
) {
    val id: String get() = _id ?: throw IllegalStateException("id not defined")
    val guid: String get() = _guid ?: throw IllegalStateException("guid not defined")
    val occurrences: Int get() = _occurrences ?: throw IllegalStateException("occurrences not defined")

    val md5Hash by lazy {
        val digest = MessageDigest.getInstance("MD5")

        val data = language + stack
        val hash = digest.digest(data.toByteArray(StandardCharsets.UTF_8))
        String(hash, StandardCharsets.UTF_8)
    }
}