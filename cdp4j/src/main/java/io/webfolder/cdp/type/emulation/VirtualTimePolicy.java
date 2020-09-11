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
package io.webfolder.cdp.type.emulation;

import com.google.gson.annotations.SerializedName;

/**
 * advance: If the scheduler runs out of immediate work, the virtual time base may fast forward to allow the next delayed task (if any) to run; pause: The virtual time base may not advance; pauseIfNetworkFetchesPending: The virtual time base may not advance if there are any pending resource fetches
 */
public enum VirtualTimePolicy {
    @SerializedName("advance")
    Advance("advance"),

    @SerializedName("pause")
    Pause("pause"),

    @SerializedName("pauseIfNetworkFetchesPending")
    PauseIfNetworkFetchesPending("pauseIfNetworkFetchesPending");

    public final String value;

    VirtualTimePolicy(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
