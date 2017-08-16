package com.spirit.porker.util;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import freemarker.template.utility.StringUtil;

public class DateUtil {

	/**
	 * The UTC time zone (often referred to as GMT).
	 */
	public static final TimeZone UTC_TIME_ZONE = TimeZone.getTimeZone("GMT");
	/**
	 * Number of milliseconds in a standard second.
	 * 
	 * @since 2.1
	 */
	public static final long MILLIS_PER_SECOND = 1000;
	/**
	 * Number of milliseconds in a standard minute.
	 * 
	 * @since 2.1
	 */
	public static final long MILLIS_PER_MINUTE = 60 * MILLIS_PER_SECOND;
	/**
	 * Number of milliseconds in a standard hour.
	 * 
	 * @since 2.1
	 */
	public static final long MILLIS_PER_HOUR = 60 * MILLIS_PER_MINUTE;
	/**
	 * Number of milliseconds in a standard day.
	 * 
	 * @since 2.1
	 */
	public static final long MILLIS_PER_DAY = 24 * MILLIS_PER_HOUR;

	public static final String FORMAT_TIMESTAMP = "yyyy-MM-dd HH:mm:ss";

	public static final String FORMAT_DATE = "yyyy-MM-dd";

	public static final String FORMAT_MONTH = "yyyy-MM";

	public static final String FORMAT_DATE_MIN = "yyyy-MM-dd HH:mm";
	public static final String FORMATDATEMIN = "yyyyMMddHHmm";
	public static final String FORMATTIMESTAMP = "yyyyMMddHHmmss";

	public static final SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

	public static final SimpleDateFormat yyyyMMddFormater = new SimpleDateFormat("yyyy-MM-dd");

	public static final SimpleDateFormat yyyyMMddHHmmFormater = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 把时间转换成字符串
	 * 
	 * @param date
	 *            时间
	 * @param formatStr
	 *            要转换的格式
	 * @return
	 */
	public static String dateToString(Date date, String formatStr) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		sdf.format(date);
		return sdf.format(date);
	}

	/**
	 * 根据传入的时间字符串和格式类型，将String转化为Date类型
	 * 
	 * @param dateString,format
	 * @return Date
	 */
	public static Date stringFormatDate(String dateString, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean isBeforeSomeHours(Date now, Date when, Double someHours) {
		Double someHoursMs = DateUtils.MILLIS_PER_HOUR * someHours;
		Long aftMs = when.getTime();
		Long befMs = now.getTime();
		return ((aftMs - befMs) > someHoursMs) ? true : false;
	}

	public static String getSimpleTime(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(time);
	}

	public static int getCurrentHour() {
		Calendar curr = Calendar.getInstance();
		return curr.get(Calendar.HOUR_OF_DAY);
	}

	public static int getHour(Date date) {
		Calendar curr = Calendar.getInstance();
		curr.setTime(date);
		return curr.get(Calendar.HOUR_OF_DAY);
	}

	public static int getHour(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar curr = Calendar.getInstance();
		curr.setTime(sdf.parse(date));
		return curr.get(Calendar.HOUR_OF_DAY);
	}

	public static String getSimpleDate() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(d);
	}

	public static String getDate() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(d);
	}

	public static String getStartTime() {
		Calendar calendar = Calendar.getInstance();
		// 凌晨0到1点特殊处理
		if (getCurrentHour() == 0) {
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 1);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		return sdf.format(calendar.getTime());
	}

	public static String getOneDayBeforeStartTime() {
		Calendar calendar = Calendar.getInstance();
		// 凌晨0到1点特殊处理
		if (getCurrentHour() == 0) {
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 2);
		} else {
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 1);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		return sdf.format(calendar.getTime());
	}

	public static String getWeekStartTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 7);
		// 凌晨0到1点特殊处理
		if (getCurrentHour() == 0) {
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 1);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		return sdf.format(calendar.getTime());
	}

	public static String getMonthStartTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
		// 凌晨0到1点特殊处理
		if (getCurrentHour() == 0) {
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 1);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		return sdf.format(calendar.getTime());
	}

	public static String getBeforeOneHourStartTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
		return sdf.format(calendar.getTime());
	}

	public static String getBeforeOneHourEndTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:59:59");
		return sdf.format(calendar.getTime());
	}

	public static String getBeforeTwoHourStartTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 2);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
		return sdf.format(calendar.getTime());
	}

	public static String getBeforeTwoHourEndTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 2);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:59:59");
		return sdf.format(calendar.getTime());
	}

	public static String getBeforeHourStartTime(int timeSlot) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - timeSlot);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
		return sdf.format(calendar.getTime());
	}

	public static String getBeforeHourEndTime(int timeSlot) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - timeSlot);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:59:59");
		return sdf.format(calendar.getTime());
	}

	public static String getOneDayBeforeHourEndTime(int timeSlot) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 1);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - timeSlot);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:59:59");
		return sdf.format(calendar.getTime());
	}

	public static String getAfterHourStartTime(int timeSlot, String time) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
		Date timeDate = sdf.parse(time);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(timeDate);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + timeSlot);
		return sdf.format(calendar.getTime());
	}

	public static String getBeforeHourStartTime(int timeSlot, String time) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
		Date timeDate = sdf.parse(time);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(timeDate);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - timeSlot);
		return sdf.format(calendar.getTime());
	}

	public static String getBeforeHourEndTime(int timeSlot, String time) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:59:59");
		Date timeDate = sdf.parse(time);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(timeDate);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - timeSlot);
		return sdf.format(calendar.getTime());
	}

	public static String getAfterDayStartTime(int timeSlot, String time) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		Date timeDate = sdf.parse(time);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(timeDate);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + timeSlot);
		return sdf.format(calendar.getTime());
	}

	public static String getBeforeDayStartTime(int timeSlot, String time) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		Date timeDate = sdf.parse(time);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(timeDate);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - timeSlot);
		return sdf.format(calendar.getTime());
	}

	public static String getBeforeDayEndTime(int timeSlot, String time) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		Date timeDate = sdf.parse(time);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(timeDate);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - timeSlot);
		return sdf.format(calendar.getTime());
	}

	public static String getBeforeDayTime(int timeSlot, String time) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
		Date timeDate = sdf.parse(time);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(timeDate);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - timeSlot);
		return sdf.format(calendar.getTime());
	}

	public static String getBeforeDaySimpleEndTime(int timeSlot, String time) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date timeDate = sdf.parse(time);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(timeDate);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - timeSlot);
		return sdf.format(calendar.getTime());
	}

	public static String getBeforeWeekStartTime(int dayTimeSlot, int timeSlot) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - dayTimeSlot);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - timeSlot);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
		return sdf.format(calendar.getTime());
	}

	public static String getBeforeWeekEndTime(int dayTimeSlot, int timeSlot) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - dayTimeSlot);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - timeSlot);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:59:59");
		return sdf.format(calendar.getTime());
	}

	public static String getBeforeMonthStartTime(int monthTimeSlot, int timeSlot) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - monthTimeSlot);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - timeSlot);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
		return sdf.format(calendar.getTime());
	}

	public static String getBeforeMonthEndTime(int monthTimeSlot, int timeSlot) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - monthTimeSlot);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - timeSlot);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:59:59");
		return sdf.format(calendar.getTime());
	}

	public static String getBeforeOneWeekStartTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 7);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
		return sdf.format(calendar.getTime());
	}

	public static String getBeforeOneWeekEndTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 7);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:59:59");
		return sdf.format(calendar.getTime());
	}

	public static String getBeforeOneWeekStartDayTime(String time) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sdf.parse(time));
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 7);
		return sdf.format(calendar.getTime());
	}

	public static String getBeforeOneWeekEndDayTime(String time) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sdf.parse(time));
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 7);
		return sdf.format(calendar.getTime());
	}

	public static String getBeforeOneWeekTime(String time) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sdf.parse(time));
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 7);
		return sdf.format(calendar.getTime());
	}

	public static String getBeforeOneMonthStartTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
		return sdf.format(calendar.getTime());
	}

	public static String getBeforeOneMonthEndTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:59:59");
		return sdf.format(calendar.getTime());
	}

	public static String getBeforeOneMonthStartDayTime(String time) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sdf.parse(time));
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
		return sdf.format(calendar.getTime());
	}

	public static String getBeforeOneMonthEndDayTime(String time) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sdf.parse(time));
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
		return sdf.format(calendar.getTime());
	}

	public static String getBeforeOneMonthTime(String time) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sdf.parse(time));
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
		return sdf.format(calendar.getTime());
	}

	public static int getDiffSeconds(String beginTime, String endTime) throws ParseException {
		Long beginL = stringFormatDate(beginTime, DateUtil.FORMAT_TIMESTAMP).getTime();
		Long endL = stringFormatDate(endTime, DateUtil.FORMAT_TIMESTAMP).getTime();
		return (int) (endL - beginL);
	}

	public static int getDiffHours(String beginTime, String endTime) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
		Long beginL = sdf.parse(beginTime).getTime();
		Long endL = sdf.parse(endTime).getTime();
		long diff = endL - beginL;
		// 计算差多少天
		long day = diff / MILLIS_PER_DAY;
		// 计算差多少小时
		long hour = diff % MILLIS_PER_DAY / MILLIS_PER_HOUR;
		return (int) (day * 24 + hour);
	}

	public static int getDiffDays(String beginTime, String endTime) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Long beginL = sdf.parse(beginTime).getTime();
		Long endL = sdf.parse(endTime).getTime();
		long diff = endL - beginL;
		// 计算差多少天
		long day = diff / MILLIS_PER_DAY;
		return (int) day;
	}

	/**
	 * 计算两个日期之间的差别，不足一天算一天
	 * @param beginTime
	 * @param endTime
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static int getDiffDays(String beginTime, String endTime,String format)  {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Long beginL;
		try {
			beginL = sdf.parse(beginTime).getTime();
			Long endL = sdf.parse(endTime).getTime();
			long diff = endL - beginL;
			// 计算差多少天
			long day = diff / MILLIS_PER_DAY;
			
			if(day == 0 && (diff % MILLIS_PER_DAY) >0){
				day = 1;
			}

			return (int) day;
		} catch (ParseException e) {
			LoggerUtil.error("解析时间一长", e);
		}
		
		return 0;
	}

	public static String getPercent(int molecular, int denominator) {
		if (molecular == 0 && denominator == 0) {
			return "<td>&nbsp;</td>";
		} else if (molecular != 0 && denominator == 0) {
			return "<td>∞</td>";
		}
		double molecular_double = molecular * 1.0;
		double tempresult = molecular_double / denominator - 1;
		DecimalFormat df = new DecimalFormat("0.00%");

		return tempresult < 0
				? ("<td style=\"text-align:left;font-family:'Microsoft YaHei';font-size:14px;font-weight:bold;color:red\">"
						+ "↓&nbsp;" + df.format(Math.abs(tempresult)) + "</td>")
				: ("<td style=\"text-align:left;font-family:'Microsoft YaHei';font-size:14px;font-weight:bold;color:green\">"
						+ "↑&nbsp;" + df.format(Math.abs(tempresult)) + "</td>");
	}

	public static List<Map<String, String>> getTimeMap() {
		List<Map<String, String>> timeMapList = new ArrayList<Map<String, String>>();
		int hour = getCurrentHour();
		if (hour == 0)
			hour = 24;
		for (int i = hour; i > 0; i--) {
			Map<String, String> timeMap = new HashMap<String, String>();
			timeMap.put("timeSlot", hour - i + "-" + (hour - i + 1));
			timeMap.put("hourLowerTime_order", getBeforeHourStartTime(i));
			timeMap.put("hourUpperTime_order", getBeforeHourEndTime(i));
			// 环比时间
			timeMap.put("twoHoursLowerTime_order", getBeforeHourStartTime(i + 1));
			timeMap.put("twoHoursUpperTime_order", getBeforeHourEndTime(i + 1));
			// 日同比时间
			timeMap.put("dayLowerTime_order", getBeforeWeekStartTime(1, i));
			timeMap.put("dayUpperTime_order", getBeforeWeekEndTime(1, i));
			// 周同比时间
			timeMap.put("weekLowerTime_order", getBeforeWeekStartTime(7, i));
			timeMap.put("weekUpperTime_order", getBeforeWeekEndTime(7, i));
			// 月同比时间
			timeMap.put("monthLowerTime_order", getBeforeMonthStartTime(1, i));
			timeMap.put("monthUpperTime_order", getBeforeMonthEndTime(1, i));
			timeMapList.add(timeMap);
		}
		return timeMapList;
	}

	public static List<Map<String, String>> getTimeMap(String startTime, String endTime, String hourFlag)
			throws ParseException {
		List<Map<String, String>> timeMapList = new ArrayList<Map<String, String>>();
		if ("1".equals(hourFlag)) {
			int hours = getDiffHours(startTime, endTime);
			for (int i = hours; i > -1; i--) {
				Map<String, String> timeMap = new HashMap<String, String>();
				timeMap.put("timeSlot", getAfterHourStartTime(hours - i, startTime));
				timeMap.put("lowerTime_order", getAfterHourStartTime(hours - i, startTime));
				timeMap.put("upperTime_order", getBeforeHourEndTime(i, endTime));
				timeMapList.add(timeMap);
			}
			return timeMapList;
		} else {
			int days = getDiffDays(startTime, endTime);
			for (int i = days; i > -1; i--) {
				Map<String, String> timeMap = new HashMap<String, String>();
				timeMap.put("timeSlot", getBeforeDaySimpleEndTime(i, endTime));
				timeMap.put("lowerTime_order", getAfterDayStartTime(days - i, startTime));
				timeMap.put("upperTime_order", getBeforeDayEndTime(i, endTime));
				timeMapList.add(timeMap);
			}
			return timeMapList;
		}
	}

	/**
	 * 
	 * @Title: betweenHours
	 * @Description: beginHour<=currentHour<endHour
	 * @param beginHour
	 * @param currentHour
	 * @param endHour
	 * @return: boolean
	 */
	public static boolean betweenHours(Double beginHour, Double currentHour, Double endHour) {
		return ((beginHour <= currentHour) && (currentHour < endHour)) ? true : false;
	}

	public static void main(String[] args) throws ParseException {
		System.out.println(getYMD(new Timestamp(System.currentTimeMillis())));
		System.out.println(getHm(new Timestamp(System.currentTimeMillis())));
		System.out.println(getCurTime());
		
	}

	/**
	 * 入参数格式要求 yyyy-MM-dd hh:mm:ss
	 * 
	 * @param date
	 */
	public static Map<String, String> getDayAndHourMinute(String date) {

		Map<String, String> map = new HashMap<String, String>();
		if (StringUtils.isBlank(date)) {
			return null;
		}

		String yyyyMMdd = date.split("\\s+")[0];
		String yyyy = yyyyMMdd.substring(0, 4);
		String MM = yyyyMMdd.substring(5, 7);
		String dd = yyyyMMdd.substring(8, 10);
		String hhmmss = date.split("\\s+")[1];
		String hhmm = hhmmss.substring(0, 5);

		StringBuffer sb = new StringBuffer();
		// sb.append(yyyy).append("年");
		sb.append(MM).append("月");
		sb.append(dd).append("日");
		map.put("yyyyMMdd", sb.toString());

		sb.setLength(0);
		sb.append(hhmm);

		map.put("hhmm", sb.toString());

		return map;
	}

	/**
	 * 根据传入的日期和格式类型，将Date转化为String类型
	 * 
	 * @param date,format
	 * @return String
	 */
	public static String dateFormatString(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 根据传入的时间，增加的年数，增加月数，增加天数，返回时间格式 入参格式：String,int,int,int,String
	 * 
	 * @param date_str
	 *            ,year_num,month_num,day_num,format
	 * @return String
	 */
	public static String addYearMonthDay(String pram_date, int year_num, int month_num, int day_num, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar rightNow = Calendar.getInstance();
		Date dt = null;
		try {
			dt = sdf.parse(pram_date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		rightNow.setTime(dt);
		rightNow.add(Calendar.YEAR, year_num); // 日期加year_num年
		rightNow.add(Calendar.MONTH, month_num); // 日期加month_num个月
		rightNow.add(Calendar.DAY_OF_YEAR, day_num); // 日期加day_num天
		Date right_date = rightNow.getTime();
		return sdf.format(right_date);
	}

	/**
	 * 根据传入的时间，增加的小时数，增加分钟数，增加秒数，返回时间格式 入参格式：String,int,int,int,String
	 * 
	 * @param date_str
	 *            ,hour_num,minute_num,second_num,format
	 * @return String
	 */
	public static String addHourMinuteSecond(String pram_date, int hour_num, int minute_num, int second_num,
			String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar rightNow = Calendar.getInstance();
		Date dt = null;
		try {
			dt = sdf.parse(pram_date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		rightNow.setTime(dt);
		rightNow.add(Calendar.HOUR_OF_DAY, hour_num); // 日期加hour_num小时
		rightNow.add(Calendar.MINUTE, minute_num); // 日期加minute_num个分钟
		rightNow.add(Calendar.SECOND, second_num); // 日期加second_num秒
		Date right_date = rightNow.getTime();
		return sdf.format(right_date);
	}

	/**
	 * 根据传入的diffTime值类型（天，时，分，秒），返回相应的结束时间跟起始时间的差值 入参格式：yyyy-MM-dd
	 * HH:mm:ss,yyyy-MM-dd HH:mm:ss,long
	 * 
	 * @param startTime
	 *            ,entTime,diffTime
	 *            diffTime的值为MILLIS_PER_DAY（天），MILLIS_PER_HOUR（
	 *            小时），MILLIS_PER_MINUTE（分钟），MILLIS_PER_SECOND（秒）
	 * @return long
	 */
	public static long diffTime(String startTime, String endTime, long diffTime) {
		SimpleDateFormat format = new SimpleDateFormat(FORMAT_TIMESTAMP);
		Date d1 = null;
		Date d2 = null;

		try {
			d1 = format.parse(startTime);
			d2 = format.parse(endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// 毫秒ms
		long diff = d2.getTime() - d1.getTime();

		return diff / diffTime;
	}

	/**
	 * 找出两个日期时间间隔的天数，
	 * 
	 * @param beginTime
	 * @param endTime
	 * @return
	 * @throws ParseException
	 */
	public static int getDifferentDay(String beginTime, String endTime) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date beingDate = sdf.parse(beginTime);
		Date endDate = sdf.parse(endTime);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(beingDate);
		int beginDay = calendar.get(Calendar.DAY_OF_YEAR);
		calendar.setTime(endDate);
		int endDay = calendar.get(Calendar.DAY_OF_YEAR);

		return endDay - beginDay + 1;

	}

	public static String formatTimeStamp(Timestamp timestamp) {
		String yyyyMMdd = yyyyMMddFormater.format(timestamp);
		return yyyyMMdd;
	}

	public static String formatTimeStamp(Timestamp timestamp, String format) {

		if (timestamp == null) {
			return null;
		}

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

		String yyyyMMdd = simpleDateFormat.format(timestamp);
		return yyyyMMdd;
	}

	/**
	 * @param str
	 * @return
	 * @throws ParseException
	 */
	public static Timestamp string2Timestmap(String str) throws Exception {

		if (StringUtils.isBlank(str)) {
			return null;
		}
		Date date = yyyyMMddFormater.parse(str);
		return new Timestamp(date.getTime());
	}

	public static String getSomeDayFromNow(int someDayNum) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, someDayNum);

		return sdf.format(calendar.getTime());
	}

	public static String convertDay(String yyyyMMdd) throws ParseException {
		SimpleDateFormat yyyyMMddFormater = new SimpleDateFormat("yyyymmdd");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		Date date = yyyyMMddFormater.parse(yyyyMMdd);
		return sdf.format(date);
	}

	public static String today() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		return sdf.format(calendar.getTime());
	}

	public static String tomorrow() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return sdf.format(calendar.getTime());

	}

	public static String todayMinute() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		return sdf.format(calendar.getTime());
	}

	public static String tomorrowMinute() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return sdf.format(calendar.getTime());
	}
	
	/**
	 * 获取从当前时间开始的前两天信息
	 */
	public static String getTwoDaysBefore(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, -2);
//		calendar.set(Calendar.HOUR, 0);
//		calendar.set(Calendar.MINUTE, 0);
//		calendar.set(Calendar.SECOND, 0);
		return sdf.format(calendar.getTime());
	}
	
	/**
	 * 
	 * @param timestamp yyyy-mm-dd hh:mm:ss
	 * @return
	 */
	public static String getYMD(Timestamp timestamp){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(timestamp);
	}
	/**
	 * 
	 * @param timestamp yyyy-mm-dd hh:mm:ss
	 * @return
	 */
	public static String getMD(Timestamp timestamp){
		SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
		return sdf.format(timestamp);
	}
	
	/**
	 * 
	 * @param timestamp yyyy-mm-dd hh:mm:ss
	 * @return
	 */
	public static String getHm(Timestamp timestamp){
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
		return sdf.format(timestamp);
	}
	
	/**
	 * 
	 * @param timestamp 
	 * @return yyyy-mm-dd hh:mm:ss
	 */
	public static String convertHHmmdd(Timestamp timestamp){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		return sdf.format(timestamp);
	}
	
	/**
	 * @return yyyymmddhhmmss+纳秒时间
	 */
	public static String getCurTime(){
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
		String prefix = simpleDateFormat.format(calendar.getTime());
		return prefix;
	}
	
}
