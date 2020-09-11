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
package io.webfolder.cdp.type.systeminfo;

/**
 * Describes a single graphics processor (GPU)
 */
public class GPUDevice {
    private Double vendorId;

    private Double deviceId;

    private String vendorString;

    private String deviceString;

    /**
     * PCI ID of the GPU vendor, if available; 0 otherwise.
     */
    public Double getVendorId() {
        return vendorId;
    }

    /**
     * PCI ID of the GPU vendor, if available; 0 otherwise.
     */
    public void setVendorId(Double vendorId) {
        this.vendorId = vendorId;
    }

    /**
     * PCI ID of the GPU device, if available; 0 otherwise.
     */
    public Double getDeviceId() {
        return deviceId;
    }

    /**
     * PCI ID of the GPU device, if available; 0 otherwise.
     */
    public void setDeviceId(Double deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * String description of the GPU vendor, if the PCI ID is not available.
     */
    public String getVendorString() {
        return vendorString;
    }

    /**
     * String description of the GPU vendor, if the PCI ID is not available.
     */
    public void setVendorString(String vendorString) {
        this.vendorString = vendorString;
    }

    /**
     * String description of the GPU device, if the PCI ID is not available.
     */
    public String getDeviceString() {
        return deviceString;
    }

    /**
     * String description of the GPU device, if the PCI ID is not available.
     */
    public void setDeviceString(String deviceString) {
        this.deviceString = deviceString;
    }
}
