package com.easyvalid.cn.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.easyvalid.cn.annotation.Regular;
import com.easyvalid.cn.annotation.Valid;
import com.easyvalid.cn.annotation.Valids;
import com.easyvalid.cn.basevalid.IValid;
import com.easyvalid.cn.basevalid.impl.BeanMethodValid;
import com.easyvalid.cn.basevalid.impl.IsBlankValid;
import com.easyvalid.cn.basevalid.impl.IsNullValid;
import com.easyvalid.cn.basevalid.impl.RegexValid;
import com.easyvalid.cn.bean.FieldAndIValid;
import com.easyvalid.cn.bean.ValidErrorBean;

public class ValidManager {

	@SuppressWarnings("unchecked")
	private static final Map<Class, List<FieldAndIValid>> cacheValid = new HashMap<Class, List<FieldAndIValid>>();

	/**
	 * 检测所有属性，并且返回所有属性的验证不通过信息
	 * 
	 * @param o
	 * @return
	 */
	public static List<ValidErrorBean> validAll(Object o) {

		List<FieldAndIValid> favList = getCacheValid(o);
		List<ValidErrorBean> validAllErrorList = null;
		for (FieldAndIValid fav : favList) {
			ValidErrorBean veb = valid(o, fav);
			if (veb != null) {
				if (validAllErrorList == null) {
					validAllErrorList = new ArrayList<ValidErrorBean>();
				}
				validAllErrorList.add(veb);
			}
		}
		return validAllErrorList;
	}

	/**
	 * 检测所有属性, 如果有一个属性检测不通过，那么返回该错误信息
	 */
	public static ValidErrorBean validOne(Object o) {

		List<FieldAndIValid> favList = getCacheValid(o);
		ValidErrorBean veb = null;
		for (FieldAndIValid fav : favList) {
			veb = valid(o, fav);
			if (veb != null) {
				break;
			}
		}

		return veb;
	}

	/**
	 * 验证属性
	 * 
	 * @param o
	 *            需要验证的对象
	 * @param fav
	 * @return
	 */
	private static ValidErrorBean valid(Object o, FieldAndIValid fav) {

		Object proValue = null;
		try {
			proValue = fav.getGetMethod().invoke(o, new Object[] {});
		} catch (Exception e) {
			throw new RuntimeException("获取属性值失败", e);
		}

		return fav.getIvalid().valid(o, proValue);
	}

	/**
	 * 获取验证器，如果没有 初始或验证器
	 * 
	 * @param o
	 * @return
	 */
	private static List<FieldAndIValid> getCacheValid(Object o) {
		Class<?> clazz = o.getClass();
		List<FieldAndIValid> favList = cacheValid.get(clazz);

		if (favList == null) {
			synchronized (clazz) {
				favList = cacheValid.get(clazz);
				if (favList == null) {
					initClassToCahce(clazz);
					favList = cacheValid.get(clazz);
				}

			}
		}
		return favList;
	}

	/**
	 * 将验证类加入缓存中
	 * 
	 * @param clazz
	 */
	@SuppressWarnings("unchecked")
	private static void initClassToCahce(Class clazz) {

		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			setIValidCache(clazz, field, field.getName(), field
					.getAnnotation(Valid.class));
			Valids valids = field.getAnnotation(Valids.class);
			if (valids != null) {
				for (Valid valid : valids.value()) {
					setIValidCache(clazz, field, field.getName(), valid);
				}
			}
		}
		// valid order sort
		Collections.sort(cacheValid.get(clazz),
				new Comparator<FieldAndIValid>() {
					@Override
					public int compare(FieldAndIValid o1, FieldAndIValid o2) {
						return o1.getOrder() - o2.getOrder();
					}
				});

	}

	/**
	 * 提取验证类的属性到缓存中
	 * 
	 * @param clazz
	 * @param proName
	 * @param valid
	 */
	@SuppressWarnings("unchecked")
	private static void setIValidCache(Class clazz, Field field,
			String proName, Valid valid) {
		IValid ivalid = null;
		if (valid != null) {
			Regular regular = valid.regular();
			if (Regular.ISBLANK.equals(regular)) {
				ivalid = new IsBlankValid(proName, valid.desc());
			} else if (Regular.ISNULL.equals(regular)) {
				ivalid = new IsNullValid(proName, valid.desc());
			} else if (Regular.REG.equals(regular)) {
				if (StringUtils.isBlank(valid.value())) {
					throw new RuntimeException(
							"@valid 的regular属性为REG， 但是他的value 为空, class : "
									+ clazz.getName() + " 属性：" + proName);
				}
				ivalid = new RegexValid(proName, valid.desc(), valid.value());
			} else if (Regular.BMETHOD.equals(regular)) {
				ivalid = new BeanMethodValid(proName, valid.desc(), valid
						.value());
			}
		}

		if (ivalid != null) {
			List<FieldAndIValid> favList = cacheValid.get(clazz);
			if (favList == null) {
				favList = new ArrayList<FieldAndIValid>();
				cacheValid.put(clazz, favList);
			}
			favList.add(new FieldAndIValid(clazz, field, ivalid, valid));
		}
	}
}
