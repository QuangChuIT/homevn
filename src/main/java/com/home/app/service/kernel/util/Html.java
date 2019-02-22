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
public interface Html {

	String escape(String text);

	String escape(String text, int mode);

	String escapeAttribute(String attribute);

	String escapeCSS(String css);

	String escapeHREF(String href);

	String escapeJS(String js);

	String escapeURL(String url);
	
	String escapeUnicode(String text);
	
	String unescapeUnicode(String text);

	String extractText(String html);

	String extractPathFromId(String Id);
	
	String fromInputSafe(String text);

	String replaceMsWordCharacters(String text);
	
	String rebuildAllImageTag(
            String source, int width, int height, String alt, String allowhost,
            boolean escapse);

		String rebuildImgTag(
                String imgTag, int width, int height, String alt, String allowhost,
                boolean escapse);

		String removeHtml(
                String source, String[] startTags, String[] replaceStartTags,
                String[] endTags, String[] replaceEndTags, boolean keep);

	String stripBetween(String text, String tag);

	String stripComments(String text);

	String stripHtml(String text);

	String toInputSafe(String text);

	String unescape(String text);

	String wordBreak(String text, int columns);

}