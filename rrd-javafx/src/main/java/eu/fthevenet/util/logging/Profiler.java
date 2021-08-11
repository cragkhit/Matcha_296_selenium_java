/*
 *    Copyright 2017 Frederic Thevenet
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 */

package eu.fthevenet.util.logging;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * A utility class that measures and reports the execution time of a portion of
 * code.
 * <p>
 * A time interval is measured from the creation of the object to its closing.
 * It implements {@code AutoCloseable} and can be used in conjunction with a
 * try-with-resource statement to measure the amount of time totalTime from
 * entering to exiting the try block.
 *
 * @author Frederic Thevenet
 */
public final class Profiler implements AutoCloseable {
    private final Elapsed elapsed;
    private final OutputDelegate writeCallback;
    private final AtomicBoolean closed = new AtomicBoolean(false);
    private final long startTime;
    private final long thresholdMs;

    /**
     * A functional interface that represents the action to output the measured
     * interval (for compatibility with &lt; 1.8)
     */
    public interface OutputDelegate {
        /**
         * Invoke
         *
         * @param e Elapsed
         */
        void invoke(Elapsed e);
    }

    /**
     * A class that encapsulate the interval measured by a {@link Profiler}
     * object.
     */
    public static class Elapsed {
        private String message;
        private long nanoSec;

        /**
         * Initializes a new instance of the {@link Elapsed} class.
         */
        public Elapsed() {
            this("", 0);
        }

        /**
         * Initializes a new instance of the {@link Elapsed} class with the
         * specified message.
         *
         * @param message The specified message
         */
        public Elapsed(String message) {
            this(message, 0);
        }

        /**
         * Initializes a new instance of the {@link Elapsed} class with the
         * specified message and initial value for the totalTime time.
         *
         * @param intialValue the initial value for the totalTime time.
         * @param message     The specified message
         */
        public Elapsed(String message, long intialValue) {
            this.nanoSec = intialValue;
            this.setMessage(message);
        }

        /**
         * Gets the {@link Elapsed}'s message.
         *
         * @return the perf totalTime's message.
         */
        public String getMessage() {
            return message;
        }

        /**
         * Sets the {@link Elapsed}'s message.
         *
         * @param message the perf totalTime's message.
         */
        public void setMessage(String message) {
            this.message = message;
        }

        /**
         * Gets the {@link Elapsed} time in ns.
         *
         * @return the totalTime time in ns.
         */
        public long getNanos() {
            return nanoSec;
        }

        /**
         * Gets the {@link Elapsed} time in μs.
         *
         * @return the totalTime time in μs.
         */
        public long getMicros() {
            return Math.round(nanoSec * Math.pow(10, -3));
        }

        /**
         * Gets the {@link Elapsed} time in ms.
         *
         * @return the totalTime time in ms.
         */
        public long getMillis() {
            return Math.round(nanoSec * Math.pow(10, -6));
        }

        /**
         * Gets the {@link Elapsed} time in s.
         *
         * @return the totalTime time in s.
         */
        public long getSeconds() {
            return Math.round(nanoSec * Math.pow(10, -9));
        }

        @Override
        public String toString() {
            return this.getMessage() + ": " + getMillis() + " ms";
        }

        /**
         * Returns a string representation of the profiler, composed ot the
         * message and the time interval in ns.
         *
         * @return a string representation of the profiler, composed ot the
         * message and the time interval in ns.
         */
        public String toNanoString() {
            return this.getMessage() + ": " + getNanos() + " ns";
        }

        /**
         * Returns a string representation of the profiler, composed ot the
         * message and the time interval in μs.
         *
         * @return a string representation of the profiler, composed ot the
         * message and the time interval in μs.
         */
        public String toMicroString() {
            return this.getMessage() + ": " + getMicros() + " μs";
        }

        /**
         * Returns a string representation of the profiler, composed ot the
         * message and the time interval in ms.
         *
         * @return a string representation of the profiler, composed ot the
         * message and the time interval in ms.
         */
        public String toMilliString() {
            return this.getMessage() + ": " + getMillis() + " ms";
        }

        /**
         * Returns a string representation of the profiler, composed ot the
         * message and the time interval in s.
         *
         * @return a string representation of the profiler, composed ot the
         * message and the time interval in s.
         */
        public String toSecondString() {
            return this.getMessage() + ": " + getSeconds() + " s";
        }
    }

    /**
     * Returns a new instance of the {@link Profiler} class.
     *
     * @param message       The message associated to the perf totalTime.
     * @param writeCallback The callback that will be invoked to log the results of the
     *                      totalTime.
     * @return The new instance of the Profiler class
     */
    public static Profiler start(String message, OutputDelegate writeCallback) {
        return new Profiler(new Elapsed(message), writeCallback, -1);
    }

    /**
     * Returns a new instance of the {@link Profiler} class.
     *
     * @param message       The message associated to the perf totalTime.
     * @param writeCallback The callback that will be invoked to log the results of the
     *                      totalTime.
     * @param threshold     If the elapsed time is greater or equal to that threshold
     *                      value (in ms) the message is logged otherwise it isn't.
     * @return The new instance of the Profiler class
     */
    public static Profiler start(String message, OutputDelegate writeCallback, long threshold) {
        return new Profiler(new Elapsed(message), writeCallback, threshold);
    }

    /**
     * Returns a new instance of the {@link Profiler} class.
     *
     * @param message The message associated to the perf totalTime.
     * @return The newly created Profiler instance.
     */
    public static Profiler start(String message) {
        return new Profiler(new Elapsed(message), null, -1);
    }

    /**
     * Returns a new instance of the {@link Profiler} class.
     *
     * @param message   The message associated to the perf totalTime.
     * @param threshold If the elapsed time is greater or equal to that threshold
     *                  value (in ms) the message is logged otherwise it isn't.
     * @return The newly created Profiler instance.
     */
    public static Profiler start(String message, long threshold) {
        return new Profiler(new Elapsed(message), null, threshold);
    }

    /**
     * Returns a new instance of the {@link Profiler} class.
     *
     * @param writeCallback The callback that will be invoked to log the results of the
     *                      totalTime.
     * @return The new instance of the Profiler class.
     */
    public static Profiler start(OutputDelegate writeCallback) {
        return new Profiler(new Elapsed(""), writeCallback, -1);
    }

    /**
     * Returns a new instance of the {@link Profiler} class.
     *
     * @param writeCallback The callback that will be invoked to log the results of the
     *                      totalTime.
     * @param threshold     If the elapsed time is greater or equal to that threshold
     *                      value (in ms) the message is logged otherwise it isn't.
     * @return The new instance of the Profiler class.
     */
    public static Profiler start(OutputDelegate writeCallback, long threshold) {
        return new Profiler(new Elapsed(""), writeCallback, threshold);
    }

    /**
     * Returns a new instance of the {@link Profiler} class.
     *
     * @param elapsed A Elapsed object that will be used to store the results of the
     *                totalTime.
     * @return The newly created Profiler instance.
     */
    public static Profiler start(Elapsed elapsed) {
        return new Profiler(elapsed, null, -1);
    }

    /**
     * Returns a new instance of the {@link Profiler} class.
     *
     * @param elapsed   A Elapsed object that will be used to store the results of the
     *                  totalTime.
     * @param threshold If the elapsed time is greater or equal to that threshold
     *                  value (in ms) the message is logged otherwise it isn't.
     * @return The newly created Profiler instance.
     */
    public static Profiler start(Elapsed elapsed, long threshold) {
        return new Profiler(elapsed, null, threshold);
    }

    private Profiler(Elapsed elapsed, OutputDelegate writeCallback, long thresholdMs) {
        this.elapsed = elapsed;
        this.writeCallback = writeCallback;
        this.thresholdMs = thresholdMs;
        this.startTime = System.nanoTime();

    }

    @Override
    public void close() {
        if (closed.compareAndSet(false, true)) {
            long stopTime = System.nanoTime();
            this.elapsed.nanoSec += stopTime - this.startTime;
            if (writeCallback != null) {
                if (this.elapsed.getMillis() >= thresholdMs) {
                    writeCallback.invoke(this.elapsed);
                }
            }
        }
    }
}
