package com.paper.text;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

import org.junit.Test;

public class TextDemo1 {
	

	@Test
	public void test1() throws Exception{
		//���������һ����Ҫ��������ȡ���ļ���calsspathĿ¼�����衰/����ͷ
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("tt/conf.properties");
		System.out.println("in: "+in);
		
		 Properties properties = new Properties();
		 
	     properties.load(in);  
	  
	     String driverClass = properties.getProperty("driver");  
	     String jdbcUrl = properties.getProperty("jdbcUrl"); 
	     String user = new String(properties.getProperty("user").getBytes("ISO-8859-1"), "UTF-8");  
	     
	     System.out.println("driver:"+driverClass);
	     System.out.println("jdbcUrl:"+jdbcUrl);
	     System.out.println("user:"+user);
	}
	
	@Test
	public void test2() throws Exception{
		Class clazz =Class.forName("com.paper.text.Person");
		
		Method [] mds =clazz.getMethods();
//		for(Method md:mds){
//			System.out.println(md.getName());
//		}
		
		mds =clazz.getDeclaredMethods();
//		for(Method md:mds){
//			System.out.println(md.getName());
//		}
		Method mdd =clazz.getDeclaredMethod("privateMD", String.class);
		
		Object obj =clazz.newInstance();
		mdd.invoke(obj, "hh");
	}
	
	@Test
	public void test3() throws Exception, Exception{
		Object obj = new Son();
		Class clazz =obj.getClass();
		Class superClazz =clazz.getSuperclass();
		
		Method[] mds =superClazz.getDeclaredMethods();
		
		for(Method md :mds){
			System.out.println(md.getName());
		}
		Method m =superClazz.getDeclaredMethod("privateMD",String.class);
	
		Object oo =superClazz.newInstance();
		m.setAccessible(true);
		m.invoke(oo,"asdasd");
		
		
	}
	
	
	public Object invoke(String className, String methodName, Object ... args) {  
	     Object obj = null;  
	     try {  
	         obj = Class.forName(className).newInstance();  
	         return invoke2(obj, methodName, args);  
	  
	     } catch (InstantiationException e) {  
	         e.printStackTrace();  
	     } catch (IllegalAccessException e) {  
	         e.printStackTrace();  
	     } catch (ClassNotFoundException e) {  
	         e.printStackTrace();  
	     }  
	     return invoke2(null, methodName, args);  
	 }  
	public Object invoke2(Object obj, String methodName, Object ... args) {  
	     //1����ȡMethod����  
	     Class [] parameterTypes = new Class[args.length];  
	     for (int i=0 ; i<args.length; i++){  
	         parameterTypes[i] = args[i].getClass();  
	     }  
	  
	     try {  
	         //2��ִ��Method����  
	         Method method = getMethod(obj.getClass(), methodName,parameterTypes);  
	         //ͨ������ִ��private����  
	         method.setAccessible(true);  
	         //3�����ط����ķ���ֵ  
	         return method.invoke(obj,args);  
	     } catch (Exception e) {  
	     }  
	  
	     return null;  
	 } 
	/** 
	  * ��ȡclazz ��methodName ������ �÷���������˽�з������������Ǹ����е�˽�з��� 
	  */  
	 public Method getMethod(Class clazz, String methodName, Class ... parameterTypes) {  
	     //ע�����ѭ��������ݣ�����  
	     for (; clazz != Object.class; clazz = clazz.getSuperclass()){  
	         try {  
	             return clazz.getDeclaredMethod(methodName, parameterTypes);  
	         } catch (Exception e) { //����ҪдException����Ȼ�����Ӧ�����в����쳣û�в���  
	         }  
	     }  
	   
	     return null;  
	 } 
	 
	 @Test  
	 public void testInvoke2() {  
	     Object obj = new Son();  
	     Object result1 = invoke2(obj, "sonsMD", 10);  
	     Object result2 = invoke2(obj, "privateMD");  
	     System.out.println(result1+":"+result2);  
	 }  
	 
	 @Test 
	 public void test5() throws Exception{
		Object obj = new Person();
		
		Class clazz =obj.getClass();
		
		Field[] fds = clazz.getDeclaredFields();
		for(Field fd:fds){
			System.out.println(fd.getName());
		}
		
		Field f1 =clazz.getDeclaredField("name");
		Person  p= new Person("Li",10);
		Object val = f1.get(p);  
		System.out.println(val);
		
		f1.set(p, "HA");
		System.out.println(p.name);
		
		Field f2 =clazz.getDeclaredField("age");
		f2.setAccessible(true);
		System.out.println(f2.get(p));
	 }
	 
	 
	 
	 
	 
	 
	 
	 
}
