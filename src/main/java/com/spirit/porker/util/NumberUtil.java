package com.spirit.porker.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Random;

public class NumberUtil {
	
	/**
	 * 生成指定范围的随机数
	 * @param lower
	 * @param upper
	 * @return
	 */
	public static int getRandom(int min,int max){
		
		Random random = new Random();
		
		int s = random.nextInt(max)%(max-min+1) + min;
		
		return s;
	}
	
	public static BigDecimal randomDouble(double min,double max) {
        if (max < min) {
        	return new BigDecimal(min);
        }
        
        if (min == max) {
            return new BigDecimal(min);
        }
        
        double random =  min + ((max - min) * new Random().nextDouble());
        DecimalFormat format = new DecimalFormat("#.00");
        return new BigDecimal(format.format(random));
    }
	
	public static int getRanInt(){
		Random r = new Random();
		return Math.abs(r.nextInt());
	}
	
	public static void main(String[] arg){
		System.out.println(randomDouble(0.1, 0.2));
		System.out.println(randomDouble(0.1, 0.2));
		System.out.println(randomDouble(0.1, 0.2));
		System.out.println(randomDouble(0.1, 0.2));
		System.out.println(randomDouble(0.1, 0.2));
		System.out.println(randomDouble(0.1, 0.2));
		System.out.println(randomDouble(0.1, 0.2));
		System.out.println(randomDouble(0.1, 0.2));
		System.out.println(randomDouble(0.1, 0.2));
		System.out.println(randomDouble(0.1, 0.2));
		System.out.println(randomDouble(0.1, 0.2));
		System.out.println(randomDouble(0.1, 0.2));
		System.out.println(randomDouble(0.1, 0.2));
		System.out.println(randomDouble(0.1, 0.2));
		System.out.println(randomDouble(0.1, 0.2));
		System.out.println(randomDouble(0.1, 0.2));
		System.out.println(randomDouble(0.1, 0.2));
		System.out.println(randomDouble(0.1, 0.2));
		System.out.println(randomDouble(0.1, 0.2));
		System.out.println(randomDouble(0.1, 0.2));
		System.out.println(randomDouble(0.1, 0.2));
		System.out.println(randomDouble(0.1, 0.2));
		System.out.println(randomDouble(0.1, 0.2));
		System.out.println(randomDouble(0.1, 0.2));
		System.out.println(randomDouble(0.1, 0.2));
		System.out.println(randomDouble(0.1, 0.2));
		System.out.println(randomDouble(0.1, 0.2));
		System.out.println(randomDouble(0.1, 0.2));
		System.out.println(randomDouble(0.1, 0.2));
		System.out.println(randomDouble(0.1, 0.2));
		System.out.println(randomDouble(0.1, 0.2));
		System.out.println(randomDouble(0.1, 0.2));
		System.out.println(randomDouble(0.1, 0.2));
		System.out.println(randomDouble(0.1, 0.2));
		System.out.println(randomDouble(0.1, 0.2));
		System.out.println(randomDouble(0.1, 0.2));
		System.out.println(randomDouble(0.1, 0.2));
		System.out.println(randomDouble(0.1, 0.2));
		System.out.println(randomDouble(0.1, 0.2));
		System.out.println(randomDouble(0.1, 0.2));
		System.out.println(randomDouble(0.1, 0.2));
		System.out.println(randomDouble(0.1, 0.2));
//		System.out.println(getRanInt());
//		System.out.println(getRanInt());
//		System.out.println(getRanInt());
//		System.out.println(getRanInt());
//		System.out.println(getRanInt());
		
	}

}
