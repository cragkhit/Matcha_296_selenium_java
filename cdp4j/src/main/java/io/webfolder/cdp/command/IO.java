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
package io.webfolder.cdp.command;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.Experimental;
import io.webfolder.cdp.annotation.Optional;
import io.webfolder.cdp.type.io.ReadResult;

/**
 * Input/Output operations for streams produced by DevTools
 */
@Experimental
@Domain("IO")
public interface IO {
    /**
     * Read a chunk of the stream
     * 
     * @param handle Handle of the stream to read.
     * @param offset Seek to the specified offset before reading (if not specificed, proceed with offset following the last read).
     * @param size Maximum number of bytes to read (left upon the agent discretion if not specified).
     * 
     * @return ReadResult
     */
    ReadResult read(String handle, @Optional Integer offset, @Optional Integer size);

    /**
     * Close the stream, discard any temporary backing storage.
     * 
     * @param handle Handle of the stream to close.
     */
    void close(String handle);

    /**
     * Read a chunk of the stream
     * 
     * @param handle Handle of the stream to read.
     * 
     * @return ReadResult
     */
    ReadResult read(String handle);
}
