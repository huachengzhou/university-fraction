package com.ng.todo.common.utils;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 实现描述：通用工具类
 *
 * @author red
 * @version v1.0.0
 */
public abstract class LangUtils {

    private static String[] chars = new String[]{"a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};

    /**
     * uuid
     *
     * @return
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }


    /**
     * 短UUID
     *
     * @return
     */
    public static String shortUuid() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();
    }

    public static int nvl(Integer i, int defaults) {
        return i == null ? defaults : i;
    }

    public static long nvl(Long l, long defaults) {
        return l == null ? defaults : l;
    }

    public static boolean nvl(Boolean b, boolean defaults) {
        return b == null ? defaults : b;
    }

    public static boolean nvl(String bool, boolean defaults) {
        return bool == null ? defaults : Boolean.parseBoolean(bool);
    }

    public static String nvl(String string, String defaults) {
        return string == null ? defaults : string;
    }

    /**
     * <pre>
     *     功能类似 Guava 的 Lists.transform，
     *     不同点：Guava 是生成的 TransformingRandomAccessList，使用到List中的元素时才会生成一个新的对象，这个对象不在原 List 中存在。
     *     本方法是预生成对象，使用 ArrayList 替代 TransformingRandomAccessList。
     * </pre>
     *
     * @param fromList
     * @param function
     * @param <F>
     * @param <T>
     * @return
     */
    public static <F, T> List<T> transform(Collection<F> fromList, Function<? super F, ? extends T> function) {
        List<T> result = Lists.newArrayList();
        if (fromList == null) {
            return result;
        }

        for (F input : fromList) {
            T apply = function.apply(input);
            if (apply != null) {
                result.add(apply);
            }
        }
        return result;
    }

    public static <T> List<T> filter(Collection<T> fromList, Predicate<? super T> predicate) {
        List<T> result = Lists.newArrayList();

        if (fromList == null) {
            return result;
        }

        for (T input : fromList) {
            if (predicate.apply(input)) {
                result.add(input);
            }
        }
        return result;
    }

    /**
     * 排序list
     *
     * @param list
     * @param comparator
     * @param <T>
     */
    public static <T> void sort(List<T> list, Comparator<T> comparator) {
        Ordering<T> ordering = new Ordering<T>() {
            @Override
            public int compare(T left, T right) {
                return comparator.compare(left, right);
            }
        };

        Collections.sort(list, ordering);
    }

    public static List<Long> longList(List<String> stringList) {
        List<Long> result = Lists.newArrayList();
        for (String string : stringList) {
            result.add(Long.valueOf(string));
        }
        return result;
    }

    /**
     * 提取数字
     *
     * @param text
     * @return
     */
    public static String getNumber(String text) {
        if (StringUtils.isEmpty(text)) {
            return "0";
        }
        if (NumberUtils.isNumber(text)) {
            return text;
        }
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(text);
        String s = m.replaceAll("").trim();
        return StringUtils.isNotBlank(s) ? s : "0";
    }

}
