package com.whc.util;

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author wang_haichun
 * @date 2017/11/24
 */
public class DateHandleUtil {

	private final static Logger logger = Logger.getLogger(DateHandleUtil.class);

	/**
	 * 
	 * @param day
	 * @return
	 */
	public static Date getDate(int day) {

		Calendar nowDay = Calendar.getInstance();

		nowDay.add(Calendar.DATE, -1 * day);
		// 设置当前时刻的时钟为0
		nowDay.set(Calendar.HOUR_OF_DAY, 0);
		// 设置当前时刻的分钟为0
		nowDay.set(Calendar.MINUTE, 0);
		// 设置当前时刻的秒钟为0
		nowDay.set(Calendar.SECOND, 0);
		// 设置当前的毫秒钟为0
		nowDay.set(Calendar.MILLISECOND, 0);
		return nowDay.getTime();
	}


	/**
	 * 字符串的日期格式的计算
	 */
	public static int daysBetween(String smdate, String bdate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(parseDate(smdate,"yyyy-MM-dd"));
		long time1 = cal.getTimeInMillis();
		cal.setTime(parseDate(bdate,"yyyy-MM-dd"));
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	public static String formatDate(Date date, String partt) {
		return new SimpleDateFormat(partt).format(date);
	}

	public static Date parseDate(String date, String partt) {
		try {
			return new SimpleDateFormat(partt).parse(date);
		} catch (ParseException e) {
			logger.error("日期转换异常" + e);
		}
		return new Date();
	}

	public static String formatDataStr(String date, String sourcePart, String targetPart) {
		return formatDate(parseDate(date, sourcePart), targetPart);
	}


	/**
	 * 将时间戳转换为时间
	 * @param s
	 * @return
	 */
	public static Date stampToDate(String s){
		long lt = new Long(s);
		Date date = new Date(lt * 1000);
		return date;
	}

	/**
	 * 获取当前时间之前天数日期
	 * @param day
	 * @return
	 */
	public static String getNowBeforeDay(int day){
		Date date=new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, -day);
		date = calendar.getTime();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
}
