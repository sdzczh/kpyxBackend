package cn.stylefeng.guns.core.util;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class DrawUtils {
    /**
     * 根据起始数和数量 抽取一定个数的方法
     * @param number  抽取目标数量
     * @param list 集
     * @return
     */
    public static Set<Integer> draw(Integer number, List<Integer> list){
        Random random = new Random();
        Set<Integer> set = new HashSet<>();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            int index = random.nextInt(list.size());
            if(set.contains(list.get(index))){
                i--;
            }else {
                set.add(list.get(index));
            }
            if(set.size() == number){
                break;
            }
        }
        return set;
    }
}
