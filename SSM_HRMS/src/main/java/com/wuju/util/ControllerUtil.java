package com.wuju.util;

import com.wuju.model.Resume;
import com.wuju.model.ResumeForIV;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ControllerUtil {
    public static String timestampToStr(Timestamp trBegin) {
        // �õ��ַ�����Ӧ��Timestamp���ں�ʱ��
//        String trBegin = trBegin1.replace("T", " ");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");

        java.util.Date date = new java.util.Date(trBegin.getTime());
        String trBegin1 = sdf1.format(date);
        String trBegin2 = sdf2.format(date);
        return trBegin1+"T"+trBegin2;
    }

    public static Timestamp strToTimestamp(String trBegin1) {
        // �õ��ַ�����Ӧ��Timestamp���ں�ʱ��
        String trBegin = trBegin1.replace("T", " ");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        java.util.Date date = new java.util.Date();
        try {
            date = sdf.parse(trBegin);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // �������Թٺ�����ʱ�䡢�ص�
        return new Timestamp(date.getTime());
    }

    // ����ͬ���Ե�ֵ���ݸ���ͬ����
    public static void transferAttributeValues(Resume resume, ResumeForIV resumeForIV) {
        Class<? extends Resume> resumeClass = resume.getClass();
        Field[] declaredFields = resumeClass.getDeclaredFields();
        Method[] declaredMethods = resumeClass.getDeclaredMethods();
        Class<? extends ResumeForIV> resumeForIVClass = resumeForIV.getClass();
        Method[] declaredMethods1 = resumeForIVClass.getDeclaredMethods();
        for (Field field : declaredFields) {
            String name = field.getName(); // �ҵ�resume��������
            System.out.println("transferAttributeValues: " + name);
            for (Method method : declaredMethods) {
                if (method.getName().equalsIgnoreCase("get" + name)){
                    System.out.println("transferAttributeValues: " + method.getName());
                    try {
                        Object get = method.invoke(resume);// ͨ��get��������ȡresume����ֵ
                        for (Method method1 : declaredMethods1) {
                            if (method1.getName().equalsIgnoreCase("set" + name)){
                                System.out.println("transferAttributeValues: " + method1.getName());
                                method1.invoke(resumeForIV,get);// ͨ��set����������resumeForIV����ֵ
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
