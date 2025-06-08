package com.hngocs.mainproject.utils;


import android.app.Activity;

import com.hngocs.mainproject.R;

public class Healthcare {
    public static BMIResult calculate(double height, double weight, Activity context){
        double BMI=weight/Math.pow(height,2);
        BMIResult result=new BMIResult();
        result.setBMI(BMI);
        String des="";
        if (BMI<18.5)
            des=context.getResources().getString(R.string.title_slim);
        else if(BMI<23)
            des=context.getResources().getString(R.string.title_normal);
        else
            des=context.getResources().getString(R.string.title_fat);
        result.setDescription(des);
        return result;
    }
}

