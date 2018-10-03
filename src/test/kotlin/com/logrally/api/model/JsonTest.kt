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

import com.logrally.api.http.response.LoginResponse
import kotlinx.serialization.json.JSON
import org.junit.Assert
import org.junit.Test

/**
 * @author jansorg
 */
class JsonTest {
    @Test
    fun loginResponse() {
        val original = LoginResponse(User("my-username", "mail@example.com"), "token-value")
        val data = JSON.stringify(original)
        val back: LoginResponse = JSON.parse(data)

        Assert.assertEquals(original, back)
    }

    @Test
    fun project() {
        val original = Project("short name", "name", "my project is this", "123-abc")
        val data = JSON.stringify(original)
        val back: Project = JSON.parse(data)

        Assert.assertEquals(original, back)
    }

    @Test
    fun organisation() {
        val original = Organisation("my-project", "owner-id")
        val data = JSON.stringify(original)
        val back: Organisation = JSON.parse(data)

        Assert.assertEquals(original, back)
    }

    @Test
    fun item() {
        val original = LograllyItem("JAVA", "my\nstack", "project-id", 1, "my-guid")
        val data = JSON.stringify(original)
        val back: LograllyItem = JSON.parse(data)

        Assert.assertEquals(original, back)
    }

    @Test
    fun occurrence() {
        val original = LograllyOccurrence(LograllyItem("JAVA", "my\nstack", "project-id", 1, "my-guid"), "my occurrence", "my note", properties = mapOf("a" to "b", "b" to 123))
        val data = JSON.stringify(original)
        val back: LograllyOccurrence = JSON.parse(data)

        Assert.assertEquals(original, back)
        Assert.assertEquals("b", back.get("a"))
        Assert.assertEquals(123, back.get("b"))
    }
}