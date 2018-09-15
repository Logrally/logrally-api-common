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

interface CommonProperty<T : Comparable<*>> {
    val name: String
    fun get(props: DynamicProperties): T?
    fun set(props: MutableMap<String, Comparable<*>>, value: T)

    fun put(props: Map<String, Comparable<*>>, value: T) {
        set(props as MutableMap<String, Comparable<*>>, value)
    }
}

open class StringProperty(override val name: String) : CommonProperty<String> {
    override fun get(props: DynamicProperties): String? = props.get(name) as String?

    override fun set(props: MutableMap<String, Comparable<*>>, value: String) {
        props.put(name, value)
    }
}

/**
 * A set of common properties which are understood by the API.
 *
 * @author jansorg
 */
object CommonProperties {
    // username to identify the user who reported an occurrence
    val username = StringProperty("user")
    // email address to idenfity the user who reported an occurrence, can be used instead of username
    val email = StringProperty("email")

    // the environment where an error occurred, e.g. "DEV". "TEST", "PROD" or "PRODUCTION"
    val environment = StringProperty("env")

    // the version of the software where an error occurred, e.g. "5.2.1"
    val version = StringProperty("version")
    // a unique identifier of the code of the software where the error occurred, e.g. the git commit id
    val codeVersion = StringProperty("code_version")

    // the OS type where an error occurred, e.g. "linux", "windows", "macos", "ios"
    val platform = StringProperty("platform")

    // the OS version where an error occurred, e.g. "2010" or a build id
    val platformVersion = StringProperty("platform_version")

    // the name of the framework or software platform which is in use in the product, e.g. "IntelliJ", "Tomcat" or "Ruby on Rails"
    val framework = StringProperty("framework")
    // the version of the framework which is in use
    val frameworkVersion = StringProperty("framework_version")
}