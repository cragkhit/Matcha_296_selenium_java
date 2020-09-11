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

import java.util.List;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.Experimental;
import io.webfolder.cdp.annotation.Returns;
import io.webfolder.cdp.type.css.CSSComputedStyleProperty;
import io.webfolder.cdp.type.css.CSSMedia;
import io.webfolder.cdp.type.css.CSSRule;
import io.webfolder.cdp.type.css.CSSStyle;
import io.webfolder.cdp.type.css.GetInlineStylesForNodeResult;
import io.webfolder.cdp.type.css.GetLayoutTreeAndStylesResult;
import io.webfolder.cdp.type.css.GetMatchedStylesForNodeResult;
import io.webfolder.cdp.type.css.PlatformFontUsage;
import io.webfolder.cdp.type.css.RuleUsage;
import io.webfolder.cdp.type.css.SelectorList;
import io.webfolder.cdp.type.css.SourceRange;
import io.webfolder.cdp.type.css.StyleDeclarationEdit;
import io.webfolder.cdp.type.css.Value;

/**
 * This domain exposes CSS read/write operations
 * All CSS objects (stylesheets, rules, and styles) have an associated <code>id</code> used in subsequent operations on the related object
 * Each object type has a specific <code>id</code> structure, and those are not interchangeable between objects of different kinds
 * CSS objects can be loaded using the <code>get*ForNode()</code> calls (which accept a DOM node id)
 * A client can also discover all the existing stylesheets with the <code>getAllStyleSheets()</code> method (or keeping track of the <code>styleSheetAdded</code>/<code>styleSheetRemoved</code> events) and subsequently load the required stylesheet contents using the <code>getStyleSheet[Text]()</code> methods
 */
@Experimental
@Domain("CSS")
public interface CSS {
    /**
     * Enables the CSS agent for the given page. Clients should not assume that the CSS agent has been enabled until the result of this command is received.
     */
    void enable();

    /**
     * Disables the CSS agent for the given page.
     */
    void disable();

    /**
     * Returns requested styles for a DOM node identified by <tt>nodeId</tt>.
     * 
     * 
     * @return GetMatchedStylesForNodeResult
     */
    GetMatchedStylesForNodeResult getMatchedStylesForNode(Integer nodeId);

    /**
     * Returns the styles defined inline (explicitly in the "style" attribute and implicitly, using DOM attributes) for a DOM node identified by <tt>nodeId</tt>.
     * 
     * 
     * @return GetInlineStylesForNodeResult
     */
    GetInlineStylesForNodeResult getInlineStylesForNode(Integer nodeId);

    /**
     * Returns the computed style for a DOM node identified by <tt>nodeId</tt>.
     * 
     * 
     * @return Computed style for the specified DOM node.
     */
    @Returns("computedStyle")
    List<CSSComputedStyleProperty> getComputedStyleForNode(Integer nodeId);

    /**
     * Requests information about platform fonts which we used to render child TextNodes in the given node.
     * 
     * 
     * @return Usage statistics for every employed platform font.
     */
    @Experimental
    @Returns("fonts")
    List<PlatformFontUsage> getPlatformFontsForNode(Integer nodeId);

    /**
     * Returns the current textual content and the URL for a stylesheet.
     * 
     * 
     * @return The stylesheet text.
     */
    @Returns("text")
    String getStyleSheetText(String styleSheetId);

    /**
     * Returns all class names from specified stylesheet.
     * 
     * 
     * @return Class name list.
     */
    @Experimental
    @Returns("classNames")
    List<String> collectClassNames(String styleSheetId);

    /**
     * Sets the new stylesheet text.
     * 
     * 
     * @return URL of source map associated with script (if any).
     */
    @Returns("sourceMapURL")
    String setStyleSheetText(String styleSheetId, String text);

    /**
     * Modifies the rule selector.
     * 
     * 
     * @return The resulting selector list after modification.
     */
    @Returns("selectorList")
    SelectorList setRuleSelector(String styleSheetId, SourceRange range, String selector);

    /**
     * Modifies the keyframe rule key text.
     * 
     * 
     * @return The resulting key text after modification.
     */
    @Returns("keyText")
    Value setKeyframeKey(String styleSheetId, SourceRange range, String keyText);

    /**
     * Applies specified style edits one after another in the given order.
     * 
     * 
     * @return The resulting styles after modification.
     */
    @Returns("styles")
    List<CSSStyle> setStyleTexts(List<StyleDeclarationEdit> edits);

    /**
     * Modifies the rule selector.
     * 
     * 
     * @return The resulting CSS media rule after modification.
     */
    @Returns("media")
    CSSMedia setMediaText(String styleSheetId, SourceRange range, String text);

    /**
     * Creates a new special "via-inspector" stylesheet in the frame with given <tt>frameId</tt>.
     * 
     * @param frameId Identifier of the frame where "via-inspector" stylesheet should be created.
     * 
     * @return Identifier of the created "via-inspector" stylesheet.
     */
    @Returns("styleSheetId")
    String createStyleSheet(String frameId);

    /**
     * Inserts a new rule with the given <tt>ruleText</tt> in a stylesheet with given <tt>styleSheetId</tt>, at the position specified by <tt>location</tt>.
     * 
     * @param styleSheetId The css style sheet identifier where a new rule should be inserted.
     * @param ruleText The text of a new rule.
     * @param location Text position of a new rule in the target style sheet.
     * 
     * @return The newly created rule.
     */
    @Returns("rule")
    CSSRule addRule(String styleSheetId, String ruleText, SourceRange location);

    /**
     * Ensures that the given node will have specified pseudo-classes whenever its style is computed by the browser.
     * 
     * @param nodeId The element id for which to force the pseudo state.
     * @param forcedPseudoClasses Element pseudo classes to force when computing the element's style.
     */
    void forcePseudoState(Integer nodeId, List<String> forcedPseudoClasses);

    /**
     * Returns all media queries parsed by the rendering engine.
     */
    @Experimental
    @Returns("medias")
    List<CSSMedia> getMediaQueries();

    /**
     * Find a rule with the given active property for the given node and set the new value for this property
     * 
     * @param nodeId The element id for which to set property.
     */
    @Experimental
    void setEffectivePropertyValueForNode(Integer nodeId, String propertyName, String value);

    @Experimental
    @Returns("backgroundColors")
    List<String> getBackgroundColors(Integer nodeId);

    /**
     * For the main document and any content documents, return the LayoutTreeNodes and a whitelisted subset of the computed style. It only returns pushed nodes, on way to pull all nodes is to call DOM.getDocument with a depth of -1.
     * 
     * @param computedStyleWhitelist Whitelist of computed styles to return.
     * 
     * @return GetLayoutTreeAndStylesResult
     */
    @Experimental
    GetLayoutTreeAndStylesResult getLayoutTreeAndStyles(List<String> computedStyleWhitelist);

    /**
     * Enables the selector recording.
     */
    @Experimental
    void startRuleUsageTracking();

    /**
     * Obtain list of rules that became used since last call to this method (or since start of coverage instrumentation)
     */
    @Experimental
    @Returns("coverage")
    List<RuleUsage> takeCoverageDelta();

    /**
     * The list of rules with an indication of whether these were used
     */
    @Experimental
    @Returns("ruleUsage")
    List<RuleUsage> stopRuleUsageTracking();
}
