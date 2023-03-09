package com.KoreaIT.java.AM;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {
	 /** 현재날짜시간포맷팅 Str */
	public static String getNowDateTimeStr() {
		LocalDateTime now = LocalDateTime.now();
		String formatNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		return formatNow;
	}
}
 