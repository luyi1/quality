package com.ge.digital.quality.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeanCopyUtils {

	private static final Logger log = LoggerFactory.getLogger(BeanCopyUtils.class);

	public static List convertDataResultToVos(List<Object[]> values, String columns, Class clazz) throws Exception {
		String[] columnArray = StringUtils.split(columns, ",");
		Map<String, Integer> compare = new HashMap<>();
		int i = 0;
		for (String column : columnArray) {
			String newColumn = "";
			if (column.indexOf(".") > -1) {
				newColumn = column.substring(column.indexOf(".") + 1, column.length());
			}
			compare.put(newColumn.toLowerCase(), i);
			i++;
		}
		List result = new ArrayList<>();
		BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
		PropertyDescriptor[] fields = beanInfo.getPropertyDescriptors();
		for (Object[] value : values) {
			Object object = clazz.newInstance();
			for (PropertyDescriptor propertyDescriptor : fields) {
				if (propertyDescriptor.getName().toLowerCase().equals("class"))
					continue;
				log.info("field：{}，", propertyDescriptor.getName());
				String lowerCaseField = propertyDescriptor.getName().toLowerCase();
				if (compare.containsKey(lowerCaseField)) {
					Object fieldValue = value[compare.get(lowerCaseField)];
					if (propertyDescriptor.getPropertyType() == Date.class) {
						if (fieldValue != null && fieldValue instanceof String) {
							String[] dateFormats = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss" };
							Date date = DateUtils.parseDate(fieldValue.toString(), dateFormats);
							propertyDescriptor.getWriteMethod().invoke(object, date);
						} else {
							propertyDescriptor.getWriteMethod().invoke(object, fieldValue);
						}
					} else {
						if (fieldValue != null)
							propertyDescriptor.getWriteMethod().invoke(object, fieldValue.toString());
						else
							propertyDescriptor.getWriteMethod().invoke(object, fieldValue);
					}

				}
			}
			result.add(object);
		}
		return result;
	}

}
