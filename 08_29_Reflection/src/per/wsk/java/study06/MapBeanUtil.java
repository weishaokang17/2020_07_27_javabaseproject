package per.wsk.java.study06;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author weishaokang
 * @date 2020-08-30 23:24
 * @project 2020_07_27_javabaseproject
 * @description:
 *
 *
 * Map与Bean的互相转换
 */
public class MapBeanUtil {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchFieldException {
        Map<String,Object> map = new HashMap<>();
        map.put("name","奥迪");
        map.put("price",200000);
        map.put("color","blue");
        map.put("lifetime",30.5);

        Car car = mapToBean(map, Car.class);

        System.out.println(car);
        /*
        Car{name='奥迪', price=200000.0, color='blue', lifetime=30.5}
         */
        Map resultMap = beanToMap(car,Car.class);
        System.out.println(resultMap);

        /*
        {color=blue, price=200000.0, name=奥迪, lifetime=30.5}
         */
    }


    public static <T> T mapToBean(Map map,Class clazz) throws IllegalAccessException, InstantiationException, NoSuchFieldException {
        T result = (T)clazz.newInstance();

        Set<Map.Entry> set = map.entrySet();
        for(Map.Entry entry : set){
            Object key = entry.getKey();

            Field field = clazz.getDeclaredField(String.valueOf(key));
            field.setAccessible(true);
            field.set(result,entry.getValue());
        }

        return result;
    }


    public static <T> Map beanToMap(T bean,Class<T> clazz) throws IllegalAccessException, InstantiationException {
        HashMap hashMap = new HashMap();

        Field[] fields = clazz.getDeclaredFields();

        for(Field f : fields){
            String name = f.getName();

            f.setAccessible(true);

            Object o = f.get(bean);

            hashMap.put(name,o);
        }

        return hashMap;
    }


}
