package com.tuyu.sharding.util;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by tuyu on 12/15/16.
 */
public class DateUtil {

    public static final int DATELENGTH = 10;
    public static final int YYYY_LENGTH = 4;
    public static final int YYYYMM_LENGTH = 6;
    public static final int YYYYMMDD_LENGTH = 8;

    public static final int MONTHS_OF_YEAR = 12;
    public static final int MIN_TWO_NUMBER = 10;

    /**
     * 根据身份证得到年龄
     *
     * @param idcard
     *            身份证
     * @return 年龄
     * @throws Exception
     */
    public static int getAge(String idcard) throws Exception {
        int age = -1;
        int length = idcard.length();
        String birthday = "";
        if (length == 15) {
            birthday = idcard.substring(6, 8);
            birthday = "19" + birthday;
        } else if (length == 18) {
            birthday = idcard.substring(6, 10);
        } else {
            throw new Exception("错误的身份证号");
        }

        int currentYear = Calendar.getInstance().get(1);
        age = currentYear - (new Integer(birthday)).intValue();
        return age;
    }

    /**
     * 返回：YYYY年MM月dd日
     *
     * @param date
     *            日期
     * @return
     */
    public static String getChineseDate(Date date) {
        if (date == null) {
            return new String();
        } else {
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd",
                    new DateFormatSymbols());
            String dtrDate = df.format(date);
            return dtrDate.substring(0, 4) + "年"
                    + Integer.parseInt(dtrDate.substring(4, 6)) + "月"
                    + Integer.parseInt(dtrDate.substring(6, 8)) + "日";
        }
    }

    /**
     * 格式化日期
     *
     * @param date
     *            日期
     * @param format
     *            日期格式
     * @return
     */
    public static String formatDate(Date date, String format) {
        if (format == null || format.equals("")) {
            format = "yyyy-mm-dd";
        }
        if (date == null) {
            return "";
        } else {
            SimpleDateFormat df = new SimpleDateFormat(format);
            return df.format(date);
        }
    }

    /**
     * 得到当前日期
     *
     * @return 格式为YYYYMMDD
     */
    public static String getCurrentDateString() {
        Calendar cal = Calendar.getInstance();
        String currentDate = null;
        // java.util.Date d = cal.getTime();
        String currentYear = (new Integer(cal.get(1))).toString();
        String currentMonth = null;
        String currentDay = null;
        if (cal.get(Calendar.MONTH) < 9) {
            currentMonth = "0"
                    + (new Integer(cal.get(Calendar.MONTH) + 1)).toString();
        } else {
            currentMonth = (new Integer(cal.get(Calendar.MONTH) + 1))
                    .toString();
        }

        if (cal.get(Calendar.DAY_OF_MONTH) < 10) {
            currentDay = "0"
                    + (new Integer(cal.get(Calendar.DAY_OF_MONTH))).toString();
        } else {
            currentDay = (new Integer(cal.get(Calendar.DAY_OF_MONTH)))
                    .toString();
        }
        currentDate = currentYear + currentMonth + currentDay;
        return currentDate;
    }

    /**
     * 得到指定格式的当前日期
     *
     * @param strFormat
     *            日期格式
     * @return
     */
    public static String getCurrentDateString(String strFormat) {
        Calendar cal = Calendar.getInstance();
        // String currentDate = null;
        Date d = cal.getTime();
        return dateToString(d, strFormat);
    }

    /**
     * 计算date1-date2的月份 日期格式为YYYYMM，否则返回 -1
     *
     * @param newDate
     *            结束日期
     * @param oldDate
     *            开始日期
     * @return
     */
    public static int calBetweenTwoMonth(String newDate, String oldDate) {
        int length = 0;
        if (newDate.length() != 6 || oldDate.length() != 6) {
            length = -1;
        } else {
            int dealInt = Integer.parseInt(newDate);
            int alterInt = Integer.parseInt(oldDate);
            if (dealInt < alterInt) {
                length = -2;
            } else {
                int dealYearInt = Integer.parseInt(newDate.substring(0, 4));
                int dealMonthInt = Integer.parseInt(newDate.substring(4, 6));
                int alterYearInt = Integer.parseInt(oldDate.substring(0, 4));
                int alterMonthInt = Integer.parseInt(oldDate.substring(4, 6));
                length = (dealYearInt - alterYearInt) * 12
                        + (dealMonthInt - alterMonthInt);
            }
        }
        return length;
    }

    /**
     * 得到日期的年份
     *
     * @param date
     *            日期
     * @return
     */
    public static String getDateYearString(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy",
                new DateFormatSymbols());
        return df.format(date);
    }

    /**
     * 得到日期的月份
     *
     * @param date
     *            日期
     * @return
     */
    public static String getDateMonthString(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMM",
                new DateFormatSymbols());
        return df.format(date);
    }

    /**
     * 得到日期的YYYYMMDD格式
     *
     * @param date
     *            日期
     * @return
     */
    public static String getDateMonthDayString(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd",
                new DateFormatSymbols());
        return df.format(date);
    }

    /**
     * 计算连个日期之间的天数
     *
     * @param newDate
     *            新日期
     * @param oldDate
     *            旧日期
     * @return
     */
    public static int daysBetweenDates(Date newDate,
                                       Date oldDate) {
        int days = 0;
        Calendar calo = Calendar.getInstance();
        Calendar caln = Calendar.getInstance();
        calo.setTime(oldDate);
        caln.setTime(newDate);
        int oday = calo.get(Calendar.DAY_OF_YEAR);
        int nyear = caln.get(Calendar.YEAR);
        for (int oyear = calo.get(Calendar.YEAR); nyear > oyear;) {
            calo.set(Calendar.MONTH, 11);
            calo.set(Calendar.DATE, 31);
            days += calo.get(Calendar.DAY_OF_YEAR);
            oyear++;
            calo.set(Calendar.YEAR, oyear);
        }

        int nday = caln.get(Calendar.DAY_OF_YEAR);
        days = (days + nday) - oday;
        return days;
    }

    /**
     * 得到间隔一定天数的日期
     *
     * @param date
     *            日期
     * @param intBetween
     *            间隔天数
     * @return
     */
    public static Date getDateBetween(Date date,
                                                int intBetween) {
        Calendar calo = Calendar.getInstance();
        calo.setTime(date);
        calo.add(Calendar.DAY_OF_MONTH, intBetween);
        return calo.getTime();
    }

    /**
     * 得到间隔一定天数的日期字符串
     *
     * @param date
     *            日期
     * @param intBetween
     *            间隔天数
     * @param strFromat
     *            日期格式
     * @return
     */
    public static String getDateBetweenString(Date date,
                                              int intBetween, String strFromat) {
        Date dateOld = getDateBetween(date, intBetween);
        return dateToString(dateOld, strFromat);
    }

    /**
     * 对格式为YYYYMM的日期 增加指定月，并返回YYYYMM
     *
     * @param yearMonth
     *            YYYYMM的年月
     * @param addMonth
     *            月份数
     * @return
     */
    public static String increaseYearMonth(String yearMonth, int addMonth) {
        int year = (new Integer(yearMonth.substring(0, 4))).intValue();
        int month = (new Integer(yearMonth.substring(4, 6))).intValue();

        int totalMonth = year * 12 + month + addMonth;
        year = totalMonth / 12;
        month = totalMonth % 12;

        if (month <= 12 && month >= 10) {
            return year + (new Integer(month)).toString();
        } else if (month == 0) {
            return (year - 1) + "12";
        } else {
            return year + "0" + (new Integer(month)).toString();
        }
    }

    /**
     * 对格式为YYYYMM的日期 增加1月，并返回YYYYMM
     *
     * @param yearMonth
     *            YYYYMM的年月
     * @return
     */
    public static String increaseYearMonth(String yearMonth) {
        return increaseYearMonth(yearMonth, 1);
    }

    /**
     * 对格式为YYYYMM的日期 递减指定月，并返回YYYYMM
     *
     * @param yearMonth
     *            YYYYMM的年月
     * @return
     */
    public static String descreaseYearMonth(String yearMonth, int subMonth) {
        int year = (new Integer(yearMonth.substring(0, 4))).intValue();
        int month = (new Integer(yearMonth.substring(4, 6))).intValue();

        int totalMonth = year * 12 + month - subMonth;
        year = totalMonth / 12;
        month = totalMonth % 12;

        if (month <= 12 && month >= 10) {
            return year + (new Integer(month)).toString();
        } else if (month == 0) {
            return (year - 1) + "12";
        } else {
            return year + "0" + (new Integer(month)).toString();
        }
    }

    /**
     * 对格式为YYYYMM的日期 递减1月，并返回YYYYMM
     *
     * @param yearMonth
     *            YYYYMM的年月
     * @return
     */
    public static String descreaseYearMonth(String yearMonth) {
        return descreaseYearMonth(yearMonth, 1);
    }

    /**
     * 对格式为YYYY-MM的日期 增加指定月，并返回YYYY-MM
     *
     * @param yearMonth
     *            YYYY-MM的年月
     * @param addMonth
     *            月份数
     * @return
     */
    public static String increaseYearMonth2(String yearMonth, int addMonth) {
        int year = (new Integer(yearMonth.substring(0, 4))).intValue();
        int month = (new Integer(yearMonth.substring(5, 7))).intValue();

        int totalMonth = year * 12 + month + addMonth;
        year = totalMonth / 12;
        month = totalMonth % 12;

        if (month <= 12 && month >= 10) {
            return year + "-" + (new Integer(month)).toString();
        } else if (month == 0) {
            return (year - 1) + "-" + "12";
        } else {
            return year + "-" + "0" + (new Integer(month)).toString();
        }
    }

    /**
     * 对格式为YYYY-MM的日期 增加1月，并返回YYYY-MM
     *
     * @param yearMonth
     *            YYYY-MM的年月
     * @return
     */
    public static String increaseYearMonth2(String yearMonth) {
        return increaseYearMonth2(yearMonth, 1);
    }

    /**
     * 对格式为YYYY-MM的日期 递减指定月，并返回YYYY-MM
     *
     * @param yearMonth
     *            YYYY-MM的年月
     * @return
     */
    public static String descreaseYearMonth2(String yearMonth, int subMonth) {
        int year = (new Integer(yearMonth.substring(0, 4))).intValue();
        int month = (new Integer(yearMonth.substring(5, 7))).intValue();

        int totalMonth = year * 12 + month - subMonth;
        year = totalMonth / 12;
        month = totalMonth % 12;

        if (month <= 12 && month >= 10) {
            return year + "-" + (new Integer(month)).toString();
        } else if (month == 0) {
            return (year - 1) + "-" + "12";
        } else {
            return year + "-" + "0" + (new Integer(month)).toString();
        }
    }

    /**
     * 对格式为YYYY-MM的日期 递减1月，并返回YYYY-MM
     *
     * @param yearMonth
     *            YYYY-MM的年月
     * @return
     */
    public static String descreaseYearMonth2(String yearMonth) {
        return descreaseYearMonth2(yearMonth, 1);
    }

    /**
     * 得到当前日期
     *
     * @return
     */
    public static Date getCurrentDate() {
        Calendar cal = Calendar.getInstance();
        return cal.getTime();
    }

    /**
     * 得到当前的sqlDate
     *
     * @return
     */
    public static java.sql.Date getCurrentSqlDate() {
        return new java.sql.Date(DateUtil.getCurrentDate().getTime());
    }

    /**
     * 得到当前的年月，格式为YYYYMM
     *
     * @return
     */
    public static String getCurrentYearMonth() {
        Calendar cal = Calendar.getInstance();
        String currentYear = (new Integer(cal.get(Calendar.YEAR))).toString();
        String currentMonth = null;
        if (cal.get(Calendar.MONTH) < 9) {
            currentMonth = "0"
                    + (new Integer(cal.get(Calendar.MONTH) + 1)).toString();
        } else {
            currentMonth = (new Integer(cal.get(Calendar.MONTH) + 1))
                    .toString();
        }
        return currentYear + currentMonth;
    }

    /**
     * 得到当前的年
     *
     * @return
     */
    public static int getCurrentYear() {
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return currentYear;
    }

    /**
     * 得到当前月
     *
     * @return
     */
    public static int getCurrentMonth() {
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.MONTH) + 1;
        return currentYear;
    }

    /**
     * 得到当前的日期
     *
     * @return
     */
    public static int getCurrentDay() {
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.DATE);
        return currentYear;
    }

    /**
     * 得到指定日期的年
     *
     * @param date
     *            日期
     * @return
     */
    public static int getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int currentYear = cal.get(Calendar.YEAR);
        return currentYear;
    }

    /**
     * 得到指定日期的月份
     *
     * @param date
     *            日期
     * @return
     */
    public static int getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int currentYear = cal.get(Calendar.MONTH) + 1;
        return currentYear;
    }

    /**
     * 得到指定日期的小时
     *
     * @param date
     *            日期
     * @return
     */
    public static int getHour(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int currentYear = cal.get(Calendar.HOUR_OF_DAY);
        return currentYear;
    }

    /**
     * 得到指定日期的日
     *
     * @param date
     *            日期
     * @return
     */
    public static int getDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int currentYear = cal.get(Calendar.DATE);
        return currentYear;
    }

    /**
     * 指定格式的字符串转换成日期
     *
     * @param strDate
     *            日期字符串
     * @param oracleFormat
     *            日期格式
     * @return
     */
    public static Date stringToDate(String strDate,
                                              String oracleFormat) {
        if (strDate == null) {
            return null;
        }
        Hashtable<Integer, String> h = new Hashtable<Integer, String>();
        String javaFormat = new String();
        String s = oracleFormat.toLowerCase();
        if (s.indexOf("yyyy") != -1) {
            h.put(new Integer(s.indexOf("yyyy")), "yyyy");
        } else if (s.indexOf("yy") != -1) {
            h.put(new Integer(s.indexOf("yy")), "yy");
        }

        if (s.indexOf("mm") != -1) {
            h.put(new Integer(s.indexOf("mm")), "MM");
        }
        if (s.indexOf("dd") != -1) {
            h.put(new Integer(s.indexOf("dd")), "dd");
        }
        if (s.indexOf("hh24") != -1) {
            h.put(new Integer(s.indexOf("hh24")), "HH");
        }
        if (s.indexOf("mi") != -1) {
            h.put(new Integer(s.indexOf("mi")), "mm");
        }
        if (s.indexOf("ss") != -1) {
            h.put(new Integer(s.indexOf("ss")), "ss");
        }

        for (int intStart = 0; s.indexOf("-", intStart) != -1; intStart++) {
            intStart = s.indexOf("-", intStart);
            h.put(new Integer(intStart), "-");
        }

        for (int intStart = 0; s.indexOf("/", intStart) != -1; intStart++) {
            intStart = s.indexOf("/", intStart);
            h.put(new Integer(intStart), "/");
        }

        for (int intStart = 0; s.indexOf(" ", intStart) != -1; intStart++) {
            intStart = s.indexOf(" ", intStart);
            h.put(new Integer(intStart), " ");
        }

        for (int intStart = 0; s.indexOf(":", intStart) != -1; intStart++) {
            intStart = s.indexOf(":", intStart);
            h.put(new Integer(intStart), ":");
        }

        if (s.indexOf("年") != -1) {
            h.put(new Integer(s.indexOf("年")), "年");
        }
        if (s.indexOf("月") != -1) {
            h.put(new Integer(s.indexOf("月")), "月");
        }
        if (s.indexOf("日") != -1) {
            h.put(new Integer(s.indexOf("日")), "日");
        }
        if (s.indexOf("时") != -1) {
            h.put(new Integer(s.indexOf("时")), "时");
        }
        if (s.indexOf("分") != -1) {
            h.put(new Integer(s.indexOf("分")), "分");
        }
        if (s.indexOf("秒") != -1) {
            h.put(new Integer(s.indexOf("秒")), "秒");
        }

        int i = 0;
        while (h.size() != 0) {
            Enumeration<Integer> e = h.keys();
            int n = 0;
            while (e.hasMoreElements()) {
                i = e.nextElement().intValue();
                if (i >= n) {
                    n = i;
                }
            }
            String temp = h.get(new Integer(n));
            h.remove(new Integer(n));
            javaFormat = temp + javaFormat;
        }
        SimpleDateFormat df = new SimpleDateFormat(javaFormat);
        Date myDate = new Date();
        try {
            myDate = df.parse(strDate);
        } catch (ParseException e) {
            return null;
        }
        return myDate;
    }

    /**
     * 指定格式的字符串转换成sql日期
     *
     * @param strDate
     *            日期字符串
     * @param oracleFormat
     *            日期格式
     * @return
     */
    public static java.sql.Date stringToSqlDate(String strDate,
                                                String oracleFormat) {
        if (strDate == null) {
            return null;
        }
        Hashtable<Integer, String> h = new Hashtable<Integer, String>();
        String javaFormat = new String();
        String s = oracleFormat.toLowerCase();
        if (s.indexOf("yyyy") != -1) {
            h.put(new Integer(s.indexOf("yyyy")), "yyyy");
        } else if (s.indexOf("yy") != -1) {
            h.put(new Integer(s.indexOf("yy")), "yy");
        }

        if (s.indexOf("mm") != -1) {
            h.put(new Integer(s.indexOf("mm")), "MM");
        }
        if (s.indexOf("dd") != -1) {
            h.put(new Integer(s.indexOf("dd")), "dd");
        }
        if (s.indexOf("hh24") != -1) {
            h.put(new Integer(s.indexOf("hh24")), "HH");
        }
        if (s.indexOf("mi") != -1) {
            h.put(new Integer(s.indexOf("mi")), "mm");
        }
        if (s.indexOf("ss") != -1) {
            h.put(new Integer(s.indexOf("ss")), "ss");
        }

        for (int intStart = 0; s.indexOf("-", intStart) != -1; intStart++) {
            intStart = s.indexOf("-", intStart);
            h.put(new Integer(intStart), "-");
        }

        for (int intStart = 0; s.indexOf("/", intStart) != -1; intStart++) {
            intStart = s.indexOf("/", intStart);
            h.put(new Integer(intStart), "/");
        }

        for (int intStart = 0; s.indexOf(" ", intStart) != -1; intStart++) {
            intStart = s.indexOf(" ", intStart);
            h.put(new Integer(intStart), " ");
        }

        for (int intStart = 0; s.indexOf(":", intStart) != -1; intStart++) {
            intStart = s.indexOf(":", intStart);
            h.put(new Integer(intStart), ":");
        }

        if (s.indexOf("年") != -1) {
            h.put(new Integer(s.indexOf("年")), "年");
        }
        if (s.indexOf("月") != -1) {
            h.put(new Integer(s.indexOf("月")), "月");
        }
        if (s.indexOf("日") != -1) {
            h.put(new Integer(s.indexOf("日")), "日");
        }
        if (s.indexOf("时") != -1) {
            h.put(new Integer(s.indexOf("时")), "时");
        }
        if (s.indexOf("分") != -1) {
            h.put(new Integer(s.indexOf("分")), "分");
        }
        if (s.indexOf("秒") != -1) {
            h.put(new Integer(s.indexOf("秒")), "秒");
        }

        int i = 0;
        while (h.size() != 0) {
            Enumeration<Integer> e = h.keys();
            int n = 0;
            while (e.hasMoreElements()) {
                i = e.nextElement().intValue();
                if (i >= n) {
                    n = i;
                }
            }
            String temp = h.get(new Integer(n));
            h.remove(new Integer(n));
            javaFormat = temp + javaFormat;
        }
        SimpleDateFormat df = new SimpleDateFormat(javaFormat);
        Date myDate = new Date();
        try {
            myDate = df.parse(strDate);
        } catch (Exception e) {
            return null;
        }
        return new java.sql.Date(myDate.getTime());
    }

    /**
     * 日期转换成指定格式的字符串
     *
     * @param date
     *            日期
     * @param format
     *            格式
     * @return
     */
    public static String dateToString(Date date, String format) {
        if (date == null) {
            return "";
        }
        Hashtable<Integer, String> h = new Hashtable<Integer, String>();
        String javaFormat = new String();
        String s = format.toLowerCase();
        if (s.indexOf("yyyy") != -1) {
            h.put(new Integer(s.indexOf("yyyy")), "yyyy");
        } else if (s.indexOf("yy") != -1) {
            h.put(new Integer(s.indexOf("yy")), "yy");
        }
        if (s.indexOf("mm") != -1) {
            h.put(new Integer(s.indexOf("mm")), "MM");
        }
        if (s.indexOf("dd") != -1) {
            h.put(new Integer(s.indexOf("dd")), "dd");
        }
        if (s.indexOf("hh24") != -1) {
            h.put(new Integer(s.indexOf("hh24")), "HH");
        }
        if (s.indexOf("mi") != -1) {
            h.put(new Integer(s.indexOf("mi")), "mm");
        }
        if (s.indexOf("ss") != -1) {
            h.put(new Integer(s.indexOf("ss")), "ss");
        }
        for (int intStart = 0; s.indexOf("-", intStart) != -1; intStart++) {
            intStart = s.indexOf("-", intStart);
            h.put(new Integer(intStart), "-");
        }

        for (int intStart = 0; s.indexOf("/", intStart) != -1; intStart++) {
            intStart = s.indexOf("/", intStart);
            h.put(new Integer(intStart), "/");
        }

        for (int intStart = 0; s.indexOf(" ", intStart) != -1; intStart++) {
            intStart = s.indexOf(" ", intStart);
            h.put(new Integer(intStart), " ");
        }

        for (int intStart = 0; s.indexOf(":", intStart) != -1; intStart++) {
            intStart = s.indexOf(":", intStart);
            h.put(new Integer(intStart), ":");
        }

        if (s.indexOf("年") != -1) {
            h.put(new Integer(s.indexOf("年")), "年");
        }
        if (s.indexOf("月") != -1) {
            h.put(new Integer(s.indexOf("月")), "月");
        }
        if (s.indexOf("日") != -1) {
            h.put(new Integer(s.indexOf("日")), "日");
        }
        if (s.indexOf("时") != -1) {
            h.put(new Integer(s.indexOf("时")), "时");
        }
        if (s.indexOf("分") != -1) {
            h.put(new Integer(s.indexOf("分")), "分");
        }
        if (s.indexOf("秒") != -1) {
            h.put(new Integer(s.indexOf("秒")), "秒");
        }
        int i = 0;
        while (h.size() != 0) {
            Enumeration<Integer> e = h.keys();
            int n = 0;
            while (e.hasMoreElements()) {
                i = e.nextElement().intValue();
                if (i >= n) {
                    n = i;
                }
            }
            String temp = h.get(new Integer(n));
            h.remove(new Integer(n));
            javaFormat = temp + javaFormat;
        }
        SimpleDateFormat df = new SimpleDateFormat(javaFormat,
                new DateFormatSymbols());
        return df.format(date);
    }

    /**
     * sql日期转换成指定格式的字符串
     *
     * @param date
     *            日期
     * @param format
     *            格式
     * @return
     */
    public static String sqlDateToString(java.sql.Date date, String format) {
        if (date == null) {
            return "";
        }
        Hashtable<Integer, String> h = new Hashtable<Integer, String>();
        String javaFormat = new String();
        String s = format.toLowerCase();
        if (s.indexOf("yyyy") != -1) {
            h.put(new Integer(s.indexOf("yyyy")), "yyyy");
        } else if (s.indexOf("yy") != -1) {
            h.put(new Integer(s.indexOf("yy")), "yy");
        }
        if (s.indexOf("mm") != -1) {
            h.put(new Integer(s.indexOf("mm")), "MM");
        }
        if (s.indexOf("dd") != -1) {
            h.put(new Integer(s.indexOf("dd")), "dd");
        }
        if (s.indexOf("hh24") != -1) {
            h.put(new Integer(s.indexOf("hh24")), "HH");
        }
        if (s.indexOf("mi") != -1) {
            h.put(new Integer(s.indexOf("mi")), "mm");
        }
        if (s.indexOf("ss") != -1) {
            h.put(new Integer(s.indexOf("ss")), "ss");
        }

        for (int intStart = 0; s.indexOf("-", intStart) != -1; intStart++) {
            intStart = s.indexOf("-", intStart);
            h.put(new Integer(intStart), "-");
        }

        for (int intStart = 0; s.indexOf("/", intStart) != -1; intStart++) {
            intStart = s.indexOf("/", intStart);
            h.put(new Integer(intStart), "/");
        }

        for (int intStart = 0; s.indexOf(" ", intStart) != -1; intStart++) {
            intStart = s.indexOf(" ", intStart);
            h.put(new Integer(intStart), " ");
        }

        for (int intStart = 0; s.indexOf(":", intStart) != -1; intStart++) {
            intStart = s.indexOf(":", intStart);
            h.put(new Integer(intStart), ":");
        }

        if (s.indexOf("年") != -1) {
            h.put(new Integer(s.indexOf("年")), "年");
        }
        if (s.indexOf("月") != -1) {
            h.put(new Integer(s.indexOf("月")), "月");
        }
        if (s.indexOf("日") != -1) {
            h.put(new Integer(s.indexOf("日")), "日");
        }
        if (s.indexOf("时") != -1) {
            h.put(new Integer(s.indexOf("时")), "时");
        }
        if (s.indexOf("分") != -1) {
            h.put(new Integer(s.indexOf("分")), "分");
        }
        if (s.indexOf("秒") != -1) {
            h.put(new Integer(s.indexOf("秒")), "秒");
        }
        int i = 0;
        while (h.size() != 0) {
            Enumeration<Integer> e = h.keys();
            int n = 0;
            while (e.hasMoreElements()) {
                i = e.nextElement().intValue();
                if (i >= n) {
                    n = i;
                }
            }
            String temp = h.get(new Integer(n));
            h.remove(new Integer(n));
            javaFormat = temp + javaFormat;
        }
        SimpleDateFormat df = new SimpleDateFormat(javaFormat,
                new DateFormatSymbols());
        return df.format(date);
    }

    /**
     * 得到当前日期(java.sql.Date类型)，注意：没有时间，只有日期
     *
     * @return 当前日期
     */
    public static java.sql.Date getSqlDate() {
        Calendar oneCalendar = Calendar.getInstance();
        return getSqlDate(oneCalendar.get(Calendar.YEAR),
                oneCalendar.get(Calendar.MONTH) + 1,
                oneCalendar.get(Calendar.DATE));
    }

    /**
     * 根据所给年、月、日，得到日期(java.sql.Date类型)，注意：没有时间，只有日期。
     * 年、月、日不合法，会抛IllegalArgumentException(不需要catch)
     *
     * @param yyyy
     *            4位年
     * @param mm
     *            月
     * @param dd
     *            日
     * @return 日期
     */
    public static java.sql.Date getSqlDate(int yyyy, int mm, int dd) {
        if (!verityDate(yyyy, mm, dd)) {
            throw new IllegalArgumentException("This is illegimate date!");
        }

        Calendar oneCalendar = Calendar.getInstance();
        oneCalendar.clear();
        oneCalendar.set(yyyy, mm - 1, dd);
        return new java.sql.Date(oneCalendar.getTime().getTime());
    }

    /**
     * 根据所给年、月、日，检验是否为合法日期。
     *
     * @param yyyy
     *            4位年
     * @param mm
     *            月
     * @param dd
     *            日
     * @return
     */
    public static boolean verityDate(int yyyy, int mm, int dd) {
        boolean flag = false;

        if (mm >= 1 && mm <= 12 && dd >= 1 && dd <= 31) {
            if (mm == 4 || mm == 6 || mm == 9 || mm == 11) {
                if (dd <= 30) {
                    flag = true;
                }
            } else if (mm == 2) {
                if (yyyy % 100 != 0 && yyyy % 4 == 0 || yyyy % 400 == 0) {
                    if (dd <= 29) {
                        flag = true;
                    }
                } else if (dd <= 28) {
                    flag = true;
                }

            } else {
                flag = true;
            }

        }
        return flag;
    }

    /**
     * 得到日期的字符串 格式为yyyy-MM-dd
     *
     * @param d
     *            日期
     * @return
     */
    public static String getDateString(Date d) {
        return dateToString(d, "yyyy-MM-dd");
    }

    /**
     * 得到日期的时间 格式为HH24:MI:SS
     *
     * @param d
     *            日期
     * @return
     */
    public static String getTimeString(Date d) {
        return dateToString(d, "HH24:MI:SS");
    }

    /**
     * 得到日期的字符串 格式为 YYYY-MM-DD HH24:MI:SS
     *
     * @param d
     *            日期
     * @return
     */
    public static String getDateTimeString(Date d) {
        return dateToString(d, "YYYY-MM-DD HH24:MI:SS");
    }

    /**
     * 比较YYYYMM的时间大小
     *
     * @param s1
     *            日期1
     * @param s2
     *            日期2
     * @return
     */
    public static boolean yearMonthGreatEqual(String s1, String s2) {
        String temp1 = s1.substring(0, 4);
        String temp2 = s2.substring(0, 4);
        String temp3 = s1.substring(4, 6);
        String temp4 = s2.substring(4, 6);
        if (Integer.parseInt(temp1) > Integer.parseInt(temp2)) {
            return true;
        }

        if (Integer.parseInt(temp1) == Integer.parseInt(temp2)) {
            return Integer.parseInt(temp3) >= Integer.parseInt(temp4);
        } else {
            return false;
        }
    }

    /**
     * 得到oracle的日期字符串
     *
     * @param d
     *            日期
     * @return
     */
    public static String getOracleFormatDateStr(Date d) {
        return dateToString(d, "YYYY-MM-DD HH24:MI:SS");
    }

    /**
     * 得到年月的最后日
     *
     * @param yyyymm
     *            年月
     * @return
     */
    public static String getLastDay(String yyyymm) {
        int getYear = Integer.parseInt(yyyymm.substring(0, 4));
        int getMonth = Integer.parseInt(yyyymm.substring(4, 6));
        String getLastDay = "";
        if (getMonth == 2) {
            if (getYear % 4 == 0 && getYear % 100 != 0 || getYear % 400 == 0) {
                getLastDay = "29";
            } else {
                getLastDay = "28";
            }
        } else if (getMonth == 4 || getMonth == 6 || getMonth == 9
                || getMonth == 11) {
            getLastDay = "30";
        } else {
            getLastDay = "31";
        }

        return String.valueOf(getYear) + "年" + String.valueOf(getMonth) + "月"
                + getLastDay + "日";
    }

    /**
     * 得到两个日期的间隔天数
     *
     * @param strDateBegin
     *            开始日期
     * @param strDateEnd
     *            结束日期
     * @return
     */
    public static String getMonthBetween(String strDateBegin, String strDateEnd) {
        try {
            String strOut;
            if (strDateBegin.equals("") || strDateEnd.equals("")
                    || strDateBegin.length() != 6 || strDateEnd.length() != 6) {
                strOut = "";
            } else {
                int intMonthBegin = Integer.parseInt(strDateBegin.substring(0,
                        4))
                        * 12
                        + Integer.parseInt(strDateBegin.substring(4, 6));
                int intMonthEnd = Integer.parseInt(strDateEnd.substring(0, 4))
                        * 12 + Integer.parseInt(strDateEnd.substring(4, 6));
                strOut = String.valueOf(intMonthBegin - intMonthEnd);
            }
            return strOut;
        } catch (Exception e) {
            return "0";
        }
    }

    /**
     * 得到当前月份的第一天
     *
     * @return
     */
    public static Date getCurrentMonthFirstDay() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 00);
        c.set(Calendar.MINUTE, 00);
        c.set(Calendar.SECOND, 00);
        return c.getTime();
    }

    /**
     * 得到指定年月 的第一天
     *
     * @param year
     *            年
     * @param month
     *            月
     * @return
     */
    public static String getMonthFirstDay(int year, int month) {
        String strMonth;
        if (month < 10) {
            strMonth = "0" + month;
        } else {
            strMonth = "" + month;
        }
        return year + "-" + strMonth + "-01";
    }

    /**
     * 得到指定年月的最后1天
     *
     * @param year
     *            年
     * @param month
     *            月
     * @return
     */
    public static String getMonthLastDay(int year, int month) {
        String strMonth;
        if (month < 10) {
            strMonth = "0" + month;
        } else {
            strMonth = "" + month;
        }

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return year + "-" + strMonth + "-"
                + c.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 得到下个月的第一天
     *
     * @return
     */
    public static String getFirstDayOfNextMonth() {
        String strToday = getCurrentDateString();
        return increaseYearMonth(strToday.substring(0, 6)) + "01";
    }

    /**
     * 把YYYYMM的字符串转换成YYYY年MM月
     *
     * @param yearMonth
     *            年月
     * @return
     */
    public static String getYearAndMonth(String yearMonth) {
        if (yearMonth == null) {
            return "";
        }

        String ym = yearMonth.trim();
        if (6 != ym.length()) {
            return ym;
        } else {
            String year = ym.substring(0, 4);
            String month = ym.substring(4);
            return year + "年" + month + "月";
        }
    }

    /**
     * 递增指定月数的日期
     *
     * @param date
     *            日期
     * @param intBetween
     *            递增月数
     * @return
     */
    public static Date increaseMonth(Date date,
                                               int intBetween) {
        Calendar calo = Calendar.getInstance();
        calo.setTime(date);
        calo.add(Calendar.MONTH, intBetween);
        return calo.getTime();
    }

    /**
     * 递增指定年数的日期
     *
     * @param date
     *            日期
     * @param intBetween
     *            递增年数
     * @return
     */
    public static Date increaseYear(Date date,
                                              int intBetween) {
        Calendar calo = Calendar.getInstance();
        calo.setTime(date);
        calo.add(Calendar.YEAR, intBetween);
        return calo.getTime();
    }

    /**
     * 根据身份证号码获取出生日期(正确的身份证返回出生日期，错误的返回当前数据库的日期)
     *
     * @param idcard
     *            身份证
     * @return
     * @throws Exception
     */
    public static String getBirtday(String idcard) throws Exception {
        String birthday = "";
        int idLength = idcard.length();
        String yy = "";
        int year = 0;
        String mm = "";
        int month = 0;
        String dd = "";
        int day = 0;
        boolean leapYear = false;
        String today = (com.tuyu.sharding.util.DateUtil.dateToString(
                new Date(), "yyyy-mm-dd"));

        if (idLength == 15) {
            yy = "19" + idcard.substring(6, 8);
            mm = idcard.substring(8, 10);
            dd = idcard.substring(10, 12);
        } else if (idLength == 18) {
            yy = idcard.substring(6, 10);
            mm = idcard.substring(10, 12);
            dd = idcard.substring(12, 14);
        } else {
            return (com.tuyu.sharding.util.DateUtil.dateToString(
                    new Date(), "yyyy-mm-dd"));
        }
        year = (new Integer(yy)).intValue();
        month = (new Integer(mm)).intValue();
        day = (new Integer(dd)).intValue();
        if (year < 1900 || year > 2200) {
            return (today);
        }

        if (((year % 4) != 0) && ((year % 100) != 0)) { // 判断是否为闰年
            leapYear = false;
        } else {
            leapYear = true;
        }
        if (month == 2) {
            if (leapYear) {
                if (day < 1 || day > 29) {
                    return (today);
                }
            } else {
                if (day < 1 || day > 28) {
                    return (today);
                }
            }
        }
        if ((month == 1) || (month == 3) || (month == 5) || (month == 7)
                || (month == 8) || (month == 10) || (month == 12)) {
            if (day < 1 || day > 31) {
                return (today);
            }
        }
        if ((month == 4) || (month == 6) || (month == 9) || (month == 11)) {
            if (day < 1 || day > 30) {
                return (today);
            }
        }

        birthday = yy + "-" + mm + "-" + dd;
        return birthday;
    }

    /**
     * 根据身份证号码获取性别(返回值：1－男，2－女，空为身份证号码错误)
     *
     * @param iDCard
     *            身份证
     * @return
     */
    public static String getGender(String iDCard) {
        int gender = 1;

        if (iDCard.length() == 15) {
            gender = (new Integer(iDCard.substring(14, 15))).intValue() % 2;
        } else if (iDCard.length() == 18) {
            int number17 = (new Integer(iDCard.substring(16, 17))).intValue();
            gender = number17 % 2;
        }
        if (gender == 1) {
            return "1";
        } else if (gender == 0) {
            return "2";
        } else {
            return "";
        }
    }

    /**
     * 获得指定日期 当月的第一天（月份）
     *
     * @param date
     *            日期
     * @return
     */
    public static Date beginDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 00);
        c.set(Calendar.MINUTE, 00);
        c.set(Calendar.SECOND, 00);
        c.set(Calendar.MILLISECOND, 00);
        return c.getTime();
    }

    /**
     * 获得指定日期当 月的最后1天（月份）
     *
     * @param date
     *            日期
     * @return
     */
    public static Date endDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 1);
        c.add(Calendar.DAY_OF_MONTH, -1);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTime();
    }

    /**
     * 获得当前日期 Date 类型
     *
     * @return
     */
    public static Date getCurrentBeginDate() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 00);
        c.set(Calendar.MINUTE, 00);
        c.set(Calendar.SECOND, 00);
        c.set(Calendar.MILLISECOND, 00);
        return c.getTime();
    }

    /**
     * 获得当前日期 Date 类型
     *
     * @return
     */
    public static Date getCurrentEndDate() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTime();
    }

    /**
     * 得到当前的星期
     *
     * @return
     */
    public static String getCurrentWeekDay() {
        Calendar calendar = Calendar.getInstance(Locale.CHINESE);

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        String weekDayString = "";

        if (dayOfWeek == 0) {
            weekDayString = "日";
        } else if (dayOfWeek == 1) {
            weekDayString = "一";
        } else if (dayOfWeek == 2) {
            weekDayString = "二";
        } else if (dayOfWeek == 3) {
            weekDayString = "三";
        } else if (dayOfWeek == 4) {
            weekDayString = "四";
        } else if (dayOfWeek == 5) {
            weekDayString = "五";
        } else if (dayOfWeek == 6) {
            weekDayString = "六";
        } else if (dayOfWeek == 1) {
            weekDayString = "一";
        }

        return "星期" + weekDayString;
    }
}
