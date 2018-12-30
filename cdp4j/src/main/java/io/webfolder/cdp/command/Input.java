/**
 * cdp4j Commercial License
 *
 * Copyright 2017, 2018 WebFolder OÜ
 *
 * Permission  is hereby  granted,  to "____" obtaining  a  copy of  this software  and
 * associated  documentation files  (the "Software"), to deal in  the Software  without
 * restriction, including without limitation  the rights  to use, copy, modify,  merge,
 * publish, distribute  and sublicense  of the Software,  and to permit persons to whom
 * the Software is furnished to do so, subject to the following conditions:
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR  IMPLIED,
 * INCLUDING  BUT NOT  LIMITED  TO THE  WARRANTIES  OF  MERCHANTABILITY, FITNESS  FOR A
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL  THE AUTHORS  OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.webfolder.cdp.command;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.Experimental;
import io.webfolder.cdp.annotation.Optional;
import io.webfolder.cdp.type.constant.KeyEventType;
import io.webfolder.cdp.type.constant.MouseButtonType;
import io.webfolder.cdp.type.constant.MouseEventType;
import io.webfolder.cdp.type.constant.TouchEventType;
import io.webfolder.cdp.type.input.GestureSourceType;
import io.webfolder.cdp.type.input.TouchPoint;
import java.util.List;

@Domain("Input")
public interface Input {
    /**
     * Dispatches a key event to the page.
     * 
     * @param type Type of the key event.
     * @param modifiers Bit field representing pressed modifier keys. Alt=1, Ctrl=2, Meta/Command=4, Shift=8
     * (default: 0).
     * @param timestamp Time at which the event occurred.
     * @param text Text as generated by processing a virtual key code with a keyboard layout. Not needed for
     * for <code>keyUp</code> and <code>rawKeyDown</code> events (default: "")
     * @param unmodifiedText Text that would have been generated by the keyboard if no modifiers were pressed (except for
     * shift). Useful for shortcut (accelerator) key handling (default: "").
     * @param keyIdentifier Unique key identifier (e.g., 'U+0041') (default: "").
     * @param code Unique DOM defined string value for each physical key (e.g., 'KeyA') (default: "").
     * @param key Unique DOM defined string value describing the meaning of the key in the context of active
     * modifiers, keyboard layout, etc (e.g., 'AltGr') (default: "").
     * @param windowsVirtualKeyCode Windows virtual key code (default: 0).
     * @param nativeVirtualKeyCode Native virtual key code (default: 0).
     * @param autoRepeat Whether the event was generated from auto repeat (default: false).
     * @param isKeypad Whether the event was generated from the keypad (default: false).
     * @param isSystemKey Whether the event was a system key event (default: false).
     * @param location Whether the event was from the left or right side of the keyboard. 1=Left, 2=Right (default:
     * 0).
     */
    void dispatchKeyEvent(KeyEventType type, @Optional Integer modifiers,
            @Optional Double timestamp, @Optional String text, @Optional String unmodifiedText,
            @Optional String keyIdentifier, @Optional String code, @Optional String key,
            @Optional Integer windowsVirtualKeyCode, @Optional Integer nativeVirtualKeyCode,
            @Optional Boolean autoRepeat, @Optional Boolean isKeypad, @Optional Boolean isSystemKey,
            @Optional Integer location);

    /**
     * This method emulates inserting text that doesn't come from a key press,
     * for example an emoji keyboard or an IME.
     * 
     * @param text The text to insert.
     */
    @Experimental
    void insertText(String text);

    /**
     * Dispatches a mouse event to the page.
     * 
     * @param type Type of the mouse event.
     * @param x X coordinate of the event relative to the main frame's viewport in CSS pixels.
     * @param y Y coordinate of the event relative to the main frame's viewport in CSS pixels. 0 refers to
     * the top of the viewport and Y increases as it proceeds towards the bottom of the viewport.
     * @param modifiers Bit field representing pressed modifier keys. Alt=1, Ctrl=2, Meta/Command=4, Shift=8
     * (default: 0).
     * @param timestamp Time at which the event occurred.
     * @param button Mouse button (default: "none").
     * @param clickCount Number of times the mouse button was clicked (default: 0).
     * @param deltaX X delta in CSS pixels for mouse wheel event (default: 0).
     * @param deltaY Y delta in CSS pixels for mouse wheel event (default: 0).
     */
    void dispatchMouseEvent(MouseEventType type, Double x, Double y, @Optional Integer modifiers,
            @Optional Double timestamp, @Optional MouseButtonType button,
            @Optional Integer clickCount, @Optional Double deltaX, @Optional Double deltaY);

    /**
     * Dispatches a touch event to the page.
     * 
     * @param type Type of the touch event. TouchEnd and TouchCancel must not contain any touch points, while
     * TouchStart and TouchMove must contains at least one.
     * @param touchPoints Active touch points on the touch device. One event per any changed point (compared to
     * previous touch event in a sequence) is generated, emulating pressing/moving/releasing points
     * one by one.
     * @param modifiers Bit field representing pressed modifier keys. Alt=1, Ctrl=2, Meta/Command=4, Shift=8
     * (default: 0).
     * @param timestamp Time at which the event occurred.
     */
    void dispatchTouchEvent(TouchEventType type, List<TouchPoint> touchPoints,
            @Optional Integer modifiers, @Optional Double timestamp);

    /**
     * Emulates touch event from the mouse event parameters.
     * 
     * @param type Type of the mouse event.
     * @param x X coordinate of the mouse pointer in DIP.
     * @param y Y coordinate of the mouse pointer in DIP.
     * @param button Mouse button.
     * @param timestamp Time at which the event occurred (default: current time).
     * @param deltaX X delta in DIP for mouse wheel event (default: 0).
     * @param deltaY Y delta in DIP for mouse wheel event (default: 0).
     * @param modifiers Bit field representing pressed modifier keys. Alt=1, Ctrl=2, Meta/Command=4, Shift=8
     * (default: 0).
     * @param clickCount Number of times the mouse button was clicked (default: 0).
     */
    @Experimental
    void emulateTouchFromMouseEvent(MouseEventType type, Integer x, Integer y,
            MouseButtonType button, @Optional Double timestamp, @Optional Double deltaX,
            @Optional Double deltaY, @Optional Integer modifiers, @Optional Integer clickCount);

    /**
     * Ignores input events (useful while auditing page).
     * 
     * @param ignore Ignores input events processing when set to true.
     */
    void setIgnoreInputEvents(Boolean ignore);

    /**
     * Synthesizes a pinch gesture over a time period by issuing appropriate touch events.
     * 
     * @param x X coordinate of the start of the gesture in CSS pixels.
     * @param y Y coordinate of the start of the gesture in CSS pixels.
     * @param scaleFactor Relative scale factor after zooming (>1.0 zooms in, <1.0 zooms out).
     * @param relativeSpeed Relative pointer speed in pixels per second (default: 800).
     * @param gestureSourceType Which type of input events to be generated (default: 'default', which queries the platform
     * for the preferred input type).
     */
    @Experimental
    void synthesizePinchGesture(Double x, Double y, Double scaleFactor,
            @Optional Integer relativeSpeed, @Optional GestureSourceType gestureSourceType);

    /**
     * Synthesizes a scroll gesture over a time period by issuing appropriate touch events.
     * 
     * @param x X coordinate of the start of the gesture in CSS pixels.
     * @param y Y coordinate of the start of the gesture in CSS pixels.
     * @param xDistance The distance to scroll along the X axis (positive to scroll left).
     * @param yDistance The distance to scroll along the Y axis (positive to scroll up).
     * @param xOverscroll The number of additional pixels to scroll back along the X axis, in addition to the given
     * distance.
     * @param yOverscroll The number of additional pixels to scroll back along the Y axis, in addition to the given
     * distance.
     * @param preventFling Prevent fling (default: true).
     * @param speed Swipe speed in pixels per second (default: 800).
     * @param gestureSourceType Which type of input events to be generated (default: 'default', which queries the platform
     * for the preferred input type).
     * @param repeatCount The number of times to repeat the gesture (default: 0).
     * @param repeatDelayMs The number of milliseconds delay between each repeat. (default: 250).
     * @param interactionMarkerName The name of the interaction markers to generate, if not empty (default: "").
     */
    @Experimental
    void synthesizeScrollGesture(Double x, Double y, @Optional Double xDistance,
            @Optional Double yDistance, @Optional Double xOverscroll, @Optional Double yOverscroll,
            @Optional Boolean preventFling, @Optional Integer speed,
            @Optional GestureSourceType gestureSourceType, @Optional Integer repeatCount,
            @Optional Integer repeatDelayMs, @Optional String interactionMarkerName);

    /**
     * Synthesizes a tap gesture over a time period by issuing appropriate touch events.
     * 
     * @param x X coordinate of the start of the gesture in CSS pixels.
     * @param y Y coordinate of the start of the gesture in CSS pixels.
     * @param duration Duration between touchdown and touchup events in ms (default: 50).
     * @param tapCount Number of times to perform the tap (e.g. 2 for double tap, default: 1).
     * @param gestureSourceType Which type of input events to be generated (default: 'default', which queries the platform
     * for the preferred input type).
     */
    @Experimental
    void synthesizeTapGesture(Double x, Double y, @Optional Integer duration,
            @Optional Integer tapCount, @Optional GestureSourceType gestureSourceType);

    /**
     * Dispatches a key event to the page.
     * 
     * @param type Type of the key event.
     */
    void dispatchKeyEvent(KeyEventType type);

    /**
     * Dispatches a mouse event to the page.
     * 
     * @param type Type of the mouse event.
     * @param x X coordinate of the event relative to the main frame's viewport in CSS pixels.
     * @param y Y coordinate of the event relative to the main frame's viewport in CSS pixels. 0 refers to
     * the top of the viewport and Y increases as it proceeds towards the bottom of the viewport.
     */
    void dispatchMouseEvent(MouseEventType type, Double x, Double y);

    /**
     * Dispatches a touch event to the page.
     * 
     * @param type Type of the touch event. TouchEnd and TouchCancel must not contain any touch points, while
     * TouchStart and TouchMove must contains at least one.
     * @param touchPoints Active touch points on the touch device. One event per any changed point (compared to
     * previous touch event in a sequence) is generated, emulating pressing/moving/releasing points
     * one by one.
     */
    void dispatchTouchEvent(TouchEventType type, List<TouchPoint> touchPoints);

    /**
     * Emulates touch event from the mouse event parameters.
     * 
     * @param type Type of the mouse event.
     * @param x X coordinate of the mouse pointer in DIP.
     * @param y Y coordinate of the mouse pointer in DIP.
     * @param button Mouse button.
     */
    @Experimental
    void emulateTouchFromMouseEvent(MouseEventType type, Integer x, Integer y,
            MouseButtonType button);

    /**
     * Synthesizes a pinch gesture over a time period by issuing appropriate touch events.
     * 
     * @param x X coordinate of the start of the gesture in CSS pixels.
     * @param y Y coordinate of the start of the gesture in CSS pixels.
     * @param scaleFactor Relative scale factor after zooming (>1.0 zooms in, <1.0 zooms out).
     */
    @Experimental
    void synthesizePinchGesture(Double x, Double y, Double scaleFactor);

    /**
     * Synthesizes a scroll gesture over a time period by issuing appropriate touch events.
     * 
     * @param x X coordinate of the start of the gesture in CSS pixels.
     * @param y Y coordinate of the start of the gesture in CSS pixels.
     */
    @Experimental
    void synthesizeScrollGesture(Double x, Double y);

    /**
     * Synthesizes a tap gesture over a time period by issuing appropriate touch events.
     * 
     * @param x X coordinate of the start of the gesture in CSS pixels.
     * @param y Y coordinate of the start of the gesture in CSS pixels.
     */
    @Experimental
    void synthesizeTapGesture(Double x, Double y);
}
