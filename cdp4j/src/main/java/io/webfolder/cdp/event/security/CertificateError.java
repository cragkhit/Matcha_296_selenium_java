/**
 * The MIT License
 * Copyright © 2017 WebFolder OÜ
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package io.webfolder.cdp.event.security;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.EventName;

/**
 * There is a certificate error
 * If overriding certificate errors is enabled, then it should be handled with the handleCertificateError command
 * Note: this event does not fire if the certificate error has been allowed internally
 */
@Domain("Security")
@EventName("certificateError")
public class CertificateError {
    private Integer eventId;

    private String errorType;

    private String requestURL;

    /**
     * The ID of the event.
     */
    public Integer getEventId() {
        return eventId;
    }

    /**
     * The ID of the event.
     */
    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    /**
     * The type of the error.
     */
    public String getErrorType() {
        return errorType;
    }

    /**
     * The type of the error.
     */
    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    /**
     * The url that was requested.
     */
    public String getRequestURL() {
        return requestURL;
    }

    /**
     * The url that was requested.
     */
    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }
}
