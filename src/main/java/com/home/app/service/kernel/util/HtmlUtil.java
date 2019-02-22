/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.home.app.service.kernel.util;

/**
 * @author Brian Wing Shun Chan
 * @author Clarence Shen
 * @author Harry Mark
 * @author Samuel Kong
 */
public class HtmlUtil {

	public static String escape(String html) {
		return getHtml().escape(html);
	}

	public static String escape(String html, int mode) {
		return getHtml().escape(html, mode);
	}

	public static String escapeAttribute(String attribute) {
		return getHtml().escapeAttribute(attribute);
	}

	public static String escapeCSS(String css) {
		return getHtml().escapeCSS(css);
	}

	public static String escapeHREF(String href) {
		return getHtml().escapeHREF(href);
	}

	public static String escapeJS(String js) {
		return getHtml().escapeJS(js);
	}

	public static String escapeURL(String url) {
		return getHtml().escapeURL(url);
	}
	
	public static String escapeUnicode(String text){
		return getHtml().escapeUnicode(text);
	}
	
	public static String unescapeUnicode(String text){
		return getHtml().unescapeUnicode(text);
	}

	public static String extractText(String html) {
		return getHtml().extractText(html);
	}

	public static String extractPathFromId(String Id){
		return getHtml().extractPathFromId(Id);
	}
	
	public static String fromInputSafe(String html) {
		return getHtml().fromInputSafe(html);
	}

	public static Html getHtml() {
		return _html;
	}

	public static String replaceMsWordCharacters(String html) {
		return getHtml().replaceMsWordCharacters(html);
	}
	
	public static String rebuildAllImageTag(
			String source, int width, int height, String alt, String allowhost,
			boolean escapse) {

		return getHtml().rebuildAllImageTag(
				source, width, height, alt, allowhost, escapse);
	}

	public static String rebuildImgTag(
			String imgTag, int width, int height, String alt, String allowhost,
			boolean escapse) {

		return getHtml().rebuildImgTag(
				imgTag, width, height, alt, allowhost, escapse);
	}

	/**
	 * @see if removetag is true then remove all tag found in tags array else
	 *      keep all tag
	 */

	public static String removeHtml(
			String source, String[] startTags, String[] replaceStartTags,
			String[] endTags, String[] replaceEndTags, boolean keep) {

		return getHtml().removeHtml(
				source, startTags, replaceStartTags, endTags, replaceEndTags, keep);
	}

	public static String stripBetween(String html, String tag) {
		return getHtml().stripBetween(html, tag);
	}

	public static String stripComments(String html) {
		return getHtml().stripComments(html);
	}

	public static String stripHtml(String html) {
		return getHtml().stripHtml(html);
	}

	public static String toInputSafe(String html) {
		return getHtml().toInputSafe(html);
	}

	public static String unescape(String html) {
		return getHtml().unescape(html);
	}

	public static String wordBreak(String html, int columns) {
		return getHtml().wordBreak(html, columns);
	}

	public void setHtml(Html html) {
		_html = html;
	}

	private static Html _html;
	
	public static final String[] _START_TAGS = {
		"<p>","<strong>", "<em>",
		"<span style=\"text-decoration: underline;\">",
		"<span style=\"text-decoration: line-through;\">"
	};

	public static final String[] _REPLACE_START_TAGS = {
		"&lt;p&gt;", "&lt;strong&gt;", "&lt;em&gt;",
		"&lt;span style=\"text-decoration: underline;\"&gt;",
		"&lt;span style=\"text-decoration: line-through;\"&gt;"
	};

	public static final String[] _END_TAGS = {
		"</p>", "</strong>", "</em>", "</span>", "</span>"
	};

	public static final String[] _REPLACE_END_TAGS = {
		"&lt;/p&gt;", "&lt;/strong&gt;", "&lt;/em&gt;", "&lt;/span&gt;",
		"&lt;/span&gt;"
	};

}