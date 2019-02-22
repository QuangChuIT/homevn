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

package com.home.app.service.kernel.log;

/**
 * @author Brian Wing Shun Chan
 */
public interface Log {

	void debug(Object msg);

	void debug(Throwable t);

	void debug(Object msg, Throwable t);

	void error(Object msg);

	void error(Throwable t);

	void error(Object msg, Throwable t);

	void fatal(Object msg);

	void fatal(Throwable t);

	void fatal(Object msg, Throwable t);

	void info(Object msg);

	void info(Throwable t);

	void info(Object msg, Throwable t);

	boolean isDebugEnabled();

	boolean isErrorEnabled();

	boolean isFatalEnabled();

	boolean isInfoEnabled();

	boolean isTraceEnabled();

	boolean isWarnEnabled();

	void trace(Object msg);

	void trace(Throwable t);

	void trace(Object msg, Throwable t);

	void warn(Object msg);

	void warn(Throwable t);

	void warn(Object msg, Throwable t);

}