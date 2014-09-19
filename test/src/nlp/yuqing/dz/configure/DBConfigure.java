package nlp.yuqing.dz.configure;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/*
 * 数据库配置文件DataAccess.properties  信息的读取
 */

public class DBConfigure {
	
	private static String propertyFileName = "DataAccess";
    private ResourceBundle resourceBundle;
    public DBConfigure() {
        resourceBundle = ResourceBundle.getBundle(propertyFileName);
    }
    /**
     * 函数作用：读取数据库配置文件DataAccess.properties中各个字段的值
     */
    public String getString(String key) {
        if (key == null || key.equals("") || key.equals("null")) {
            return "";
        }
        String result = "";
        try {
            result = resourceBundle.getString(key);
        } catch (MissingResourceException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    /**
     *  函数作用，根据location,读取该区域含有的regionId
     *  返回：比如(97,9701,9702,98,99)
     *  location是1表示本地local_region_id
     *  location是2表示周边surround_region_id
     *  location是3表示境外oversease_region_id
     *  location是4表示全部all
     */
    public String getRegionIDStr(int location){
    	String regionIdStr = null;
		switch(location){
			case 1 :{
				String localRegionIDStr = getString("local_region_id");
				regionIdStr = "("+localRegionIDStr+")";
				break;
			}
			case 2 :{
				String SurroundRegionIDStr = getString("surround_region_id");
				regionIdStr ="("+SurroundRegionIDStr+")";
				regionIdStr="(SELECT regionID FROM wdyq_region where provinceID IN "+regionIdStr+")";
				break;
			}
			case 3:{
				String OverSeasRegionIDStr = getString("oversease_region_id");
				regionIdStr = "("+OverSeasRegionIDStr+")";
				regionIdStr="(SELECT regionID FROM wdyq_region where provinceID IN "+regionIdStr+")";
				break;
			}
			
			default:
			{
				regionIdStr = null;
				break;
			}
		}
		return regionIdStr;
    }
    
    public static void main(String[] args){
    	DBConfigure dbc = new DBConfigure();
    	System.out.println(dbc.getRegionIDStr(2));
    }
}
