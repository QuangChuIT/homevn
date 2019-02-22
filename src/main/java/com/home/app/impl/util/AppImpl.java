package com.home.app.impl.util;

import com.home.app.service.kernel.json.JSONFactoryUtil;
import com.home.app.service.kernel.json.JSONObject;
import com.home.app.service.kernel.log.Log;
import com.home.app.service.kernel.log.LogFactoryUtil;
import com.home.app.service.kernel.util.StringPool;
import com.home.app.service.util.App;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppImpl implements App {

    public String formatDate(Date datetime) throws Exception {
        return formatDate(datetime, _default_date_format);
    }

    public String formatDate(Date datetime, String pattern) throws Exception {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

            return simpleDateFormat.format(datetime);
        } catch (Exception e) {
            throw new Exception(e);
        }

    }

    public Date convertDate(String stringDate) throws Exception {
        return convertDate(stringDate, _default_date_format);
    }

    public Date convertDate(String stringDate, String pattern) throws Exception {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

            return simpleDateFormat.parse(stringDate);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

	/*public JSONObject authenWithCMS(HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		try {

			JSONObject jsonObjectReturn = JSONFactoryUtil.createJSONObject();

			boolean booleanLogined = false;

			if (session.getAttribute(WebKeys.SESSION_KEY_LOGIN_WEB) != null) {
				booleanLogined = (boolean) session.getAttribute(WebKeys.SESSION_KEY_LOGIN_WEB);
			}

			if (!booleanLogined) {
				String cookieAsString = CookieUtil.cookieAsString(request);

				JSONObject jsonObject = HttpUtil.authen(_servletAuthenURL, cookieAsString);

				if (jsonObject == null) {
					return null;
				}
				else {

					jsonObject = JSONFactoryUtil.createJSONObject(jsonObject.getString("result"));

					long userId = jsonObject.getLong(WebKeys.USER_ID);
					String userName = jsonObject.getString(WebKeys.USER_NAME);
					boolean login = jsonObject.getBoolean(WebKeys.LOGIN);

					if (login && userId != 0 && userName != null) {

						Account account = AccountLocalServiceUtil.getAccountByTypeAccount(String.valueOf(userId), AccountConstant.VnreviewAccount);

						if(account == null){
							account = AccountLocalServiceUtil.addAccount(0, String.valueOf(userId), userName, "", "", "",
									"", AccountConstant.VnreviewAccount, AccountConstant.Active );
						}

						session.setAttribute(WebKeys.USER_ID, account.getAccountId());
						session.setAttribute(WebKeys.USER_NAME, userName);
						session.setAttribute(WebKeys.SESSION_KEY_LOGIN_WEB, login);

						jsonObjectReturn.put(WebKeys.USER_ID, account.getAccountId());
						jsonObjectReturn.put(WebKeys.USER_NAME, userName);

						return jsonObjectReturn;
					}
					else {
						return null;
					}
				}
			}
			else {

				long userId = 0;
				String userName = StringPool.BLANK;

				if (session.getAttribute(WebKeys.USER_ID) != null) {
					userId = (Long) session.getAttribute(WebKeys.USER_ID);
				}
				else {
					throw new Exception("session logined not null but can not get userId from session");
				}

				if (session.getAttribute(WebKeys.USER_NAME) != null) {
					userName = (String) session.getAttribute(WebKeys.USER_NAME);
				}
				else {
					throw new Exception("session logined not null but can not get username from session");
				}

				jsonObjectReturn.put(WebKeys.USER_ID, userId);
				jsonObjectReturn.put(WebKeys.USER_NAME, userName);

				return jsonObjectReturn;
			}
		}
		catch (Exception e) {

			_log.error(e);
			session.removeAttribute(WebKeys.SESSION_KEY_LOGIN_WEB); // remove login

			return null;
		}
	}*/

    public boolean isSigned(HttpServletRequest request) throws Exception {
        try {
            HttpSession session = request.getSession();

            if (session.getAttribute(WebKeys.SESSION_KEY_LOGIN_WEB) != null) {
                boolean logined = (boolean) session.getAttribute(WebKeys.SESSION_KEY_LOGIN_WEB);

                if (logined) {
                    return logined;
                }
            }

            return false;
        } catch (Exception e) {
            _log.error(e);
            return false;
        }
    }

    public long getUserId(HttpServletRequest request) throws Exception {
        try {
            HttpSession session = request.getSession();

            if (session.getAttribute(WebKeys.USER_ID) != null) {
                return (Long) session.getAttribute(WebKeys.USER_ID);
            } else {
                return 0L;
            }
        } catch (Exception e) {
            return 0L;
        }
    }

    public String getUserName(HttpServletRequest request) throws Exception {
        try {
            HttpSession session = request.getSession();

            if (session.getAttribute(WebKeys.USER_NAME) != null) {
                return (String) session.getAttribute(WebKeys.USER_NAME);
            } else {
                return StringPool.BLANK;
            }
        } catch (Exception e) {
            return StringPool.BLANK;
        }
    }

    public JSONObject getUser(HttpServletRequest request) throws Exception {
        try {
            HttpSession session = request.getSession();
            JSONObject user = JSONFactoryUtil.createJSONObject();

            if (session.getAttribute(WebKeys.USER_NAME) != null) {
                user.put(WebKeys.USER_NAME, (String) session.getAttribute(WebKeys.USER_NAME));
            } else {
                throw new Exception("Can get Username from session");
            }

            if (session.getAttribute(WebKeys.USER_ID) != null) {
                user.put(WebKeys.USER_ID, (Long) session.getAttribute(WebKeys.USER_ID));
            } else {
                throw new Exception("Can get Username from session");
            }

            return user;
        } catch (Exception e) {

            _log.error(e);
            return null;
        }
    }

    private static final String _servletAuthenURL = "https://vnreview.vn/verify/login";

    private static final String _default_date_format = "dd/MM/yyyy HH:mm:ss";

    private static Log _log = LogFactoryUtil.getLog(AppImpl.class);
}

/*
	G   Era designator  Text    AD
	y   Year    Year    1996; 96
	Y   Week year   Year    2009; 09
	M   Month in year   Month   July; Jul; 07
	w   Week in year    Number  27
	W   Week in month   Number  2
	D   Day in year Number  189
	d   Day in month    Number  10
	F   Day of week in month    Number  2
	E   Day name in week    Text    Tuesday; Tue
	u   Day number of week (1 = Monday, ..., 7 = Sunday)    Number  1
	a   Am/pm marker    Text    PM
	H   Hour in day (0-23)  Number  0
	k   Hour in day (1-24)  Number  24
	K   Hour in am/pm (0-11)    Number  0
	h   Hour in am/pm (1-12)    Number  12
	m   Minute in hour  Number  30
	s   Second in minute    Number  55
	S   Millisecond Number  978
	z   Time zone   General time zone   Pacific Standard Time; PST; GMT-08:00
	Z   Time zone   RFC 822 time zone   -0800
	X   Time zone   ISO 8601 time zone  -08; -0800; -08:00
* */