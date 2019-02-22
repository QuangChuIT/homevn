package com.home.app.impl.util;

import com.home.app.service.kernel.util.*;
import net.htmlparser.jericho.Source;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlImpl implements Html {
	public static final int ESCAPE_MODE_ATTRIBUTE = 1;
	public static final int ESCAPE_MODE_CSS = 2;
	public static final int ESCAPE_MODE_JS = 3;
	public static final int ESCAPE_MODE_TEXT = 4;
	public static final int ESCAPE_MODE_URL = 5;

	public String escape(String text) {

		if (text == null) {
			return null;
		}

		if (text.length() == 0) {
			return StringPool.BLANK;
		}

		// Escape using XSS recommendations from
		// http://www.owasp.org/index.php/Cross_Site_Scripting
		// #How_to_Protect_Yourself

		StringBuilder sb = new StringBuilder(text.length());

		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			switch (c) {
			case '<':
				sb.append("&lt;");

				break;

			case '>':
				sb.append("&gt;");

				break;

			case '&':
				sb.append("&amp;");

				break;

			case '"':
				sb.append("&#034;");

				break;

			case '\'':
				sb.append("&#039;");

				break;

			case '\u00bb': // '�'
				sb.append("&#187;");

				break;

			default:
				sb.append(c);

				break;
			}
		}

		return sb.toString();
	}

	public String escape(String text, int type) {

		if (text == null) {
			return null;
		}

		if (text.length() == 0) {
			return StringPool.BLANK;
		}

		String prefix = StringPool.BLANK;
		String postfix = StringPool.BLANK;

		if (type == ESCAPE_MODE_ATTRIBUTE) {
			prefix = "&#x";
			postfix = StringPool.SEMICOLON;
		} 
		else if (type == ESCAPE_MODE_CSS) {
			prefix = StringPool.BACK_SLASH;
		} 
		else if (type == ESCAPE_MODE_JS) {
			prefix = "\\x";
		} 
		else if (type == ESCAPE_MODE_URL) {
			return HttpUtil.encodeURL(text, true);
		} 
		else {
			return escape(text);
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);

			if ((Character.isLetterOrDigit(c)) || (c == CharPool.DASH)
					|| (c == CharPool.UNDERLINE)) {

				sb.append(c);
			} 
			else {
				sb.append(prefix);
				sb.append(Integer.toHexString(c));
				sb.append(postfix);
			}
		}

		return sb.toString();
	}

	public String escapeAttribute(String attribute) {

		return escape(attribute, ESCAPE_MODE_ATTRIBUTE);
	}

	public String escapeCSS(String css) {

		return escape(css, ESCAPE_MODE_CSS);
	}

	public String escapeHREF(String href) {

		if (href == null) {
			return null;
		}

		if (href.length() == 0) {
			return StringPool.BLANK;
		}

		if (href.indexOf(StringPool.COLON) == 10) {
			String protocol = href.substring(0, 10).toLowerCase();

			if (protocol.equals("javascript")) {
				return StringUtil.replaceFirst(href, StringPool.COLON, "%3a");
			}
		}

		return href;
	}

	public String escapeJS(String js) {

		return escape(js, ESCAPE_MODE_JS);
	}

	public String escapeURL(String url) {

		return escape(url, ESCAPE_MODE_URL);
	}

	public String extractText(String html) {

		if (html == null) {
			return null;
		}

		Source source = new Source(html);

		return source.getTextExtractor().toString();
	}

	public String extractPathFromId(String Id){
		
		if(Id == null || Id.isEmpty()){
			return StringPool.BLANK;			
		}
		
		int idLength = Id.length();
		
		if(idLength <= 2){
			return StringPool.BLANK;			 
		}
		
		StringBuilder sb = new StringBuilder(idLength / 2 + idLength);		
		
		for(int i = 0; i < idLength; i += 2){

			if((i + 2) < idLength){
			
				sb.append(File.separator);
				sb.append(Id.substring(i, i + 2));
			}
		}

		return sb.toString();
	}
	
	public String fromInputSafe(String text) {

		return StringUtil.replace(text, "&amp;", "&");
	}

	public String replaceMsWordCharacters(String text) {

		return StringUtil.replace(text, _MS_WORD_UNICODE, _MS_WORD_HTML);
	}

	public String rebuildAllImageTag(String source, int width, int height,
			String alt, String allowhost, boolean escapse) {

		if (source == null || source.isEmpty()) {
			return source;
		}

		String SourceLowerCase = source.toLowerCase();
		StringBuilder sb = new StringBuilder();

		int indx = SourceLowerCase.indexOf("<img");
		int x = 0;

		while (indx > -1) {
			int indxSlash = SourceLowerCase.indexOf("/>", indx);

			if (indxSlash > -1) {

				String imgTag = rebuildImgTag(
						source.substring(indx, indxSlash + 2), width, height,
						alt, allowhost, escapse);

				sb.append(source.substring(x, indx));
				sb.append(imgTag);

				indx = SourceLowerCase.indexOf("<img", indxSlash + 2);
				x = indxSlash + 2;
			} 
			else {
				indx = SourceLowerCase.indexOf("<img", indx + 4);
			}
		}

		sb.append(source.substring(x));

		return sb.toString();
	}

	public String rebuildImgTag(String imgTag, int width, int height,
			String alt, String allowhost, boolean escapse) {

		if (imgTag == null || imgTag.isEmpty()) {
			return imgTag;
		}

		String imgTagLowerCase = imgTag.toLowerCase();
		String srcAtt = "";

		int indxSrc = imgTagLowerCase.indexOf("src=\"");

		if (indxSrc > -1) {
			int indxEnd = imgTagLowerCase.indexOf("\"", indxSrc + 5);

			if (indxEnd == -1) {
				indxEnd = imgTagLowerCase.indexOf(" ", indxSrc + 5);
			} 
			else {
				srcAtt = imgTag.substring(indxSrc + 5, indxEnd);
			}

		}

		if ((srcAtt.indexOf("/") == 0)
				|| (allowhost == null 
				|| srcAtt.toLowerCase().indexOf(allowhost) == 0)) {

			StringBuilder sb = new StringBuilder();

			sb.append("<img src=\"" + srcAtt + "\" ");

			if (width > 0) {
				sb.append("width=" + width + "px ");
			}

			if (height > 0) {
				sb.append("height=" + height + "px ");
			}

			sb.append("alt=\"" + alt + "\" title=\"" + alt + "\" />");

			if (escapse) {

				imgTag = sb.toString();
				imgTag = imgTag.replaceAll("<", "&lt;");
				imgTag = imgTag.replaceAll(">", "&gt;");

				return imgTag;

			} 
			else {
				return sb.toString();
			}
		} 
		else {
			return StringPool.BLANK;
		}

	}

	public String removeHtml(String source, String[] startTags,
			String[] replaceStartTags, String[] endTags,
			String[] replaceEndTags, boolean keep) {

		if (source == null || source.isEmpty()) {
			return source;
		}

		if (!keep) {
			return source.replaceAll("\\<.*?>", "");
		}

		for (int i = 0; i < startTags.length; i++) {

			String tag = startTags[i];
			int indxTag = source.indexOf(tag);

			if (indxTag != -1) {
				source = removeHTML(source, startTags[i], replaceStartTags[i],
						endTags[i], replaceEndTags[i]);
			}
		}

		source = source.replaceAll("\\<.*?>", "");
		source = source.replaceAll("&lt;", "<");
		source = source.replaceAll("&gt;", ">");

		return source;
	}

	public String stripBetween(String text, String tag) {

		return StringUtil.stripBetween(text, "<" + tag, "</" + tag + ">");
	}

	public String stripComments(String text) {

		return StringUtil.stripBetween(text, "<!--", "-->");
	}

	public String rebuildLinkOnHTML(String domainSource, String domainDst,
			String htmlContent) throws Exception {

		if (htmlContent == null || htmlContent.isEmpty())
			return null;

		if (!domainDst.startsWith("http://")
				&& !domainDst.startsWith("https://"))
			domainDst = "http://" + domainDst;

		htmlContent = StringUtil.replace(htmlContent, domainSource, domainDst);

		// remove /web/guest
		htmlContent = StringUtil.replace(htmlContent, "/web/guest/", "/");
		htmlContent = StringUtil.replace(htmlContent, "/web/guest", "/");

		// for facebook like
		htmlContent = StringUtil.replace(htmlContent, "#SOCIAL_SHARE#",
				"https://vnreview.vn");

		return htmlContent;
	}

	public String stripHtml(String text) {

		if (text == null) {
			return null;
		}

		text = stripComments(text);

		StringBuilder sb = new StringBuilder(text.length());

		int x = 0;
		int y = text.indexOf("<");

		while (y != -1) {
			sb.append(text.substring(x, y));
			sb.append(StringPool.SPACE);

			// Look for text enclosed by <script></script>

			boolean scriptFound = isScriptTag(text, y + 1);

			if (scriptFound) {
				int pos = y + _TAG_SCRIPT.length;

				// Find end of the tag

				pos = text.indexOf(">", pos);

				if (pos >= 0) {

					// Check if preceding character is / (i.e. is this instance
					// of <script/>)

					if (text.charAt(pos - 1) != '/') {

						// Search for the ending </script> tag

						for (;;) {
							pos = text.indexOf("</", pos);

							if (pos >= 0) {
								if (isScriptTag(text, pos + 2)) {
									y = pos;

									break;
								} else {

									// Skip past "</"

									pos += 2;
								}
							} else {
								break;
							}
						}
					}
				}
			}

			x = text.indexOf(">", y);

			if (x == -1) {
				break;
			}

			x++;

			if (x < y) {

				// <b>Hello</b

				break;
			}

			y = text.indexOf("<", x);
		}

		if (y == -1) {
			sb.append(text.substring(x, text.length()));
		}

		return sb.toString();
	}

	public String toInputSafe(String text) {

		return StringUtil.replace(text, new String[] { "&", "\"" },
				new String[] { "&amp;", "&quot;" });
	}

	public String unescape(String text) {

		if (text == null) {
			return null;
		}

		if (text.length() == 0) {
			return StringPool.BLANK;
		}

		// Optimize this

		text = StringUtil.replace(text, "&lt;", "<");
		text = StringUtil.replace(text, "&gt;", ">");
		text = StringUtil.replace(text, "&amp;", "&");
		text = StringUtil.replace(text, "&#034;", "\"");
		text = StringUtil.replace(text, "&#039;", "'");
		text = StringUtil.replace(text, "&#040;", "(");
		text = StringUtil.replace(text, "&#041;", ")");
		text = StringUtil.replace(text, "&#044;", ",");
		text = StringUtil.replace(text, "&#035;", "#");
		text = StringUtil.replace(text, "&#037;", "%");
		text = StringUtil.replace(text, "&#059;", ";");
		text = StringUtil.replace(text, "&#061;", "=");
		text = StringUtil.replace(text, "&#043;", "+");
		text = StringUtil.replace(text, "&#045;", "-");

		return text;
	}

	public String escapeUnicode(String text) {

		if (text == null) {
			return null;
		}

		if (text.length() == 0) {
			return StringPool.BLANK;
		}

		// Optimize this with
		// http://www.theukwebdesigncompany.com/articles/entity-escape-characters.php

		text = StringUtil.replace(text, "à", "&agrave;");
		text = StringUtil.replace(text, "á", "&aacute;");
		text = StringUtil.replace(text, "â", "&acirc;");
		text = StringUtil.replace(text, "ã", "&atilde;");

		text = StringUtil.replace(text, "è", "&egrave;");
		text = StringUtil.replace(text, "é", "&eacute;");
		text = StringUtil.replace(text, "ê", "&ecirc;");

		text = StringUtil.replace(text, "ì", "&igrave;");
		text = StringUtil.replace(text, "í", "&iacute;");
		text = StringUtil.replace(text, "î", "&icirc;");

		text = StringUtil.replace(text, "ð", "&eth;");

		text = StringUtil.replace(text, "ò", "&ograve;");
		text = StringUtil.replace(text, "ó", "&oacute;");
		text = StringUtil.replace(text, "ô", "&ocirc;");
		text = StringUtil.replace(text, "õ", "&otilde;");

		text = StringUtil.replace(text, "ù", "&ugrave;");
		text = StringUtil.replace(text, "ú", "&uacute;");
		text = StringUtil.replace(text, "û", "&ucirc;");

		text = StringUtil.replace(text, "ý", "&yacute;");

		return text;
	}
	
	public String unescapeUnicode(String text) {

		if (text == null) {
			return null;
		}

		if (text.length() == 0) {
			return StringPool.BLANK;
		}

		// Optimize this with
		// http://www.theukwebdesigncompany.com/articles/entity-escape-characters.php

		text = StringUtil.replace(text, "&agrave;", "à");
		text = StringUtil.replace(text, "&aacute;", "á");
		text = StringUtil.replace(text, "&acirc;", "â");
		text = StringUtil.replace(text, "&atilde;", "ã");

		text = StringUtil.replace(text, "&egrave;", "è");
		text = StringUtil.replace(text, "&eacute;", "é");
		text = StringUtil.replace(text, "&ecirc;", "ê");

		text = StringUtil.replace(text, "&igrave;", "ì");
		text = StringUtil.replace(text, "&iacute;", "í");
		text = StringUtil.replace(text, "&icirc;", "î");

		text = StringUtil.replace(text, "&eth;", "ð");

		text = StringUtil.replace(text, "&ograve;", "ò");
		text = StringUtil.replace(text, "&oacute;", "ó");
		text = StringUtil.replace(text, "&ocirc;", "ô");
		text = StringUtil.replace(text, "&otilde;", "õ");

		text = StringUtil.replace(text, "&ugrave;", "ù");
		text = StringUtil.replace(text, "&uacute;", "ú");
		text = StringUtil.replace(text, "&ucirc;", "û");

		text = StringUtil.replace(text, "&yacute;", "ý");

		return text;
	}

	public String wordBreak(String text, int columns) {

		StringBuilder sb = new StringBuilder();

		int length = 0;
		int lastWrite = 0;
		int pos = 0;

		Pattern pattern = Pattern.compile("([\\s<&]|$)");

		Matcher matcher = pattern.matcher(text);

		while (matcher.find()) {
			if (matcher.start() < pos) {
				continue;
			}

			while ((length + matcher.start() - pos) >= columns) {
				pos += columns - length;

				sb.append(text.substring(lastWrite, pos));
				sb.append("<wbr/>");

				length = 0;
				lastWrite = pos;
			}

			length += matcher.start() - pos;

			String group = matcher.group();

			if (group.equals(StringPool.AMPERSAND)) {
				int x = text.indexOf(StringPool.SEMICOLON, matcher.start());

				if (x != -1) {
					length++;
					pos = x + 1;
				}

				continue;
			}

			if (group.equals(StringPool.LESS_THAN)) {
				int x = text.indexOf(StringPool.GREATER_THAN, matcher.start());

				if (x != -1) {
					pos = x + 1;
				}

				continue;
			}

			if (group.equals(StringPool.SPACE)
					|| group.equals(StringPool.NEW_LINE)) {

				length = 0;
				pos = matcher.start() + 1;
			}
		}

		sb.append(text.substring(lastWrite));

		return sb.toString();
	}

	protected boolean isScriptTag(String text, int pos) {

		if (pos + _TAG_SCRIPT.length + 1 <= text.length()) {
			char item;

			for (int i = 0; i < _TAG_SCRIPT.length; i++) {
				item = text.charAt(pos++);

				if (Character.toLowerCase(item) != _TAG_SCRIPT[i]) {
					return false;
				}
			}

			item = text.charAt(pos);

			// Check that char after "script" is not a letter (i.e. another tag)

			return !Character.isLetter(item);
		} 
		else {
			return false;
		}
	}

	private String removeHTML(String source, String startTag,
			String replaceStartTag, String endTag, String replaceEndTag) {

		String sourceLowerCase = source.toLowerCase();
		StringBuilder sb = new StringBuilder();

		int indxST = sourceLowerCase.indexOf(startTag);
		int x = 0;

		while (indxST > -1) {

			sb.append(source.substring(x, indxST));
			sb.append(replaceStartTag);

			x = indxST + startTag.length();

			int indxET = sourceLowerCase.indexOf(endTag, x);

			if (indxET > -1) {
				sb.append(source.substring(x, indxET));
				sb.append(replaceEndTag);

				x = indxET + endTag.length();
			} 
			else {
				sb.append(replaceEndTag);
			}

			indxST = sourceLowerCase.indexOf(startTag, x);
		}

		sb.append(source.substring(x));

		return sb.toString();

	}

	private static final String[] _MS_WORD_UNICODE = new String[] { "\u00ae",
			"\u2019", "\u201c", "\u201d" };

	private static final String[] _MS_WORD_HTML = new String[] { "&reg;",
			StringPool.APOSTROPHE, StringPool.QUOTE, StringPool.QUOTE };

	private static final char[] _TAG_SCRIPT = { 's', 'c', 'r', 'i', 'p', 't' };
}
