package dynamicproxy;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class FyxProxy {
    public static Object newProxyInstance(Object o,FyxInvocationHandler h) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, ClassNotFoundException {
        Class clazz = o.getClass().getInterfaces()[0];
        String infName = clazz.getSimpleName();
        String line = "\n"; //换行
        String tab = "\t";//tab
        String packageContent = "package com.dilraba;" + line;
        String importContent = "import " + clazz.getName() + ";" +line;
        String classFirstLineContent = "public class $ProxyFyx implements " + infName + "{" + line;
        String fieldContent = tab + "private " + infName + " o;" + line;
        String constructorContent = tab + "public $ProxyFyx (" + infName + " o) {" + line
                +tab + tab + "this.o = o;" + line
                +tab + "}" + line;
        String methodContent = "";
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            String returnType = method.getReturnType().getSimpleName();//String
            String methodName = method.getName();//say
            Class[] args = method.getParameterTypes();//[String.class]   [Integer.class]
            String argsContent = "";
            String paramsContent = "";
            int flag = 0;
            for (Class arg : args){
                argsContent += arg.getSimpleName() + " p" + flag + ",";
                paramsContent += "p" + flag + ",";
                flag += 1;
            }
            if (argsContent.length() > 0){
                argsContent = argsContent.substring(0, argsContent.lastIndexOf(",") - 1);
                paramsContent = paramsContent.substring(0, paramsContent.lastIndexOf(",") - 1);
            }
            methodContent += tab + "public " + returnType + " " + methodName + "(" + argsContent + ") {" + line
                + tab + tab + "System.out.println(\"log\");" + line
                + tab + tab + "o." + methodName + "(" + paramsContent + ");" + line
                + tab + "}" + line;
        }
        String content = packageContent + importContent + classFirstLineContent + fieldContent + constructorContent + methodContent + "}";

        File file = new File("c:\\com\\dilraba\\$ProxyFyx.java");
        try{
            if (file.exists()){
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager standardJavaFileManager = javaCompiler.getStandardFileManager(null,null,null);
        Iterable<? extends JavaFileObject> javaFileObjects = standardJavaFileManager.getJavaFileObjects(file);
        JavaCompiler.CompilationTask t = javaCompiler.getTask(null, standardJavaFileManager, null,null,null,javaFileObjects);
        t.call();

        //Class.forName()只能加载当前项目的class文件，即classpath下的，所以加载动态生成的class文件应该用classloader
        //注意：类名中包含了包名，所以地址只写c盘
        URL[] urls = new URL[0];
        try {
            urls = new URL[]{new URL("file:C:\\\\")};
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        URLClassLoader urlClassLoader = new URLClassLoader(urls);
        Class c = urlClassLoader.loadClass("com.dilraba.$ProxyFyx");
        Constructor constructor = c.getConstructor(clazz);
        Object object = constructor.newInstance(o);

        return object;
    }
}
