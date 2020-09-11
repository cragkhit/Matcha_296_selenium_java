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

import java.util.ArrayList;
import java.util.List;

/**
 * Provides information about the GPU(s) on the system
 */
public class GPUInfo {
    private List<GPUDevice> devices = new ArrayList<>();

    private Object auxAttributes;

    private Object featureStatus;

    private List<String> driverBugWorkarounds = new ArrayList<>();

    /**
     * The graphics devices on the system. Element 0 is the primary GPU.
     */
    public List<GPUDevice> getDevices() {
        return devices;
    }

    /**
     * The graphics devices on the system. Element 0 is the primary GPU.
     */
    public void setDevices(List<GPUDevice> devices) {
        this.devices = devices;
    }

    /**
     * An optional dictionary of additional GPU related attributes.
     */
    public Object getAuxAttributes() {
        return auxAttributes;
    }

    /**
     * An optional dictionary of additional GPU related attributes.
     */
    public void setAuxAttributes(Object auxAttributes) {
        this.auxAttributes = auxAttributes;
    }

    /**
     * An optional dictionary of graphics features and their status.
     */
    public Object getFeatureStatus() {
        return featureStatus;
    }

    /**
     * An optional dictionary of graphics features and their status.
     */
    public void setFeatureStatus(Object featureStatus) {
        this.featureStatus = featureStatus;
    }

    /**
     * An optional array of GPU driver bug workarounds.
     */
    public List<String> getDriverBugWorkarounds() {
        return driverBugWorkarounds;
    }

    /**
     * An optional array of GPU driver bug workarounds.
     */
    public void setDriverBugWorkarounds(List<String> driverBugWorkarounds) {
        this.driverBugWorkarounds = driverBugWorkarounds;
    }
}
