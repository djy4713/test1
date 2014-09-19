package nlp.yuqing.dz.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期操作工具
 */
public class TimeUtil {
	
	
	/**
	 * 格式化输出日期的字符串形式
	 */
	public static String getDateString(Date date)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return sdf.format(date);
	}
	
	
}
