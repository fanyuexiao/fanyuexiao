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
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class FyxProxy {
    protected FyxInvocationHandler h;

    public FyxProxy(FyxInvocationHandler h) {
        this.h = h;
    }

    public static Object newProxyInstance(Object o,FyxInvocationHandler h) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, ClassNotFoundException {
        Class clazz = o.getClass().getInterfaces()[0];
        String infName = clazz.getSimpleName();
        String line = "\n"; //换行
        String tab = "\t";//tab
        String packageContent = "package com.dilraba;" + line;
        String importContent = "import " + clazz.getName() + ";" +line
                + "import " + FyxProxy.class.getName() + ";" + line
                + "import java.lang.reflect.UndeclaredThrowableException;" + line
                + "import java.lang.reflect.Method;" + line
                + "import dynamicproxy.FyxInvocationHandler;" + line;
        String classFirstLineContent = "public class $ProxyFyx extends FyxProxy implements " + infName + "{" + line;
        String fieldContent = "";
        String constructorContent = tab + "public $ProxyFyx (FyxInvocationHandler h){" + line
                +tab + tab + "super(h);" + line
                +tab + "}" + line;
        String methodContent = "";
        String staticContent = tab + "static {" + line
                + tab + tab + "try {" + line;
        Method[] methods = clazz.getDeclaredMethods();
        int methodsFlag = 0;
        for (Method method : methods) {
            String returnType = method.getReturnType().getSimpleName();//String
            String methodName = method.getName();//say
            Class[] args = method.getParameterTypes();//[String.class]   [Integer.class]
            String argsContent = "";
            String paramsContent = "";
            int argsflag = 0;
            fieldContent += tab + "private static Method m" + methodsFlag + ";" + line;
            staticContent += tab + tab + tab + "m" + methodsFlag
                    + "=Class.forName(\"" + clazz.getCanonicalName()
                    + "\").getMethod(\"" + methodName + "\"";
            for (Class arg : args){
                argsContent += arg.getSimpleName() + " p" + argsflag + ",";
                paramsContent += "p" + argsflag + ",";
                argsflag += 1;
                staticContent += ",Class.forName(\"" + arg.getCanonicalName() + "\")";
            }
            staticContent += ");" + line;
            if (argsContent.length() > 0){
                argsContent = argsContent.substring(0, argsContent.lastIndexOf(",") - 1);
                paramsContent = paramsContent.substring(0, paramsContent.lastIndexOf(",") - 1);
            }
            methodContent += tab + "public " + returnType + " " + methodName + "(" + argsContent + ") {" + line
                + tab + tab + "try {" + line
                + tab + tab + tab;
            if (!"void".equals(returnType)) {
                methodContent += "return (" + returnType + ")";
            }
            methodContent += "super.h.invoke(this,m" + methodsFlag + ",new Object[]{" + paramsContent + "});" + line
                + tab + tab + "}" + line
                + tab + tab + "catch (RuntimeException | Error var2) { throw var2; }" + line
                + tab + tab + "catch (Throwable var3) { throw new UndeclaredThrowableException(var3); }" + line
                + tab + "}" + line;
            methodsFlag += 1;
        }
        staticContent += tab + tab + "}" + line
                + tab + tab + "catch (NoSuchMethodException var2) { throw new NoSuchMethodError(var2.getMessage()); }" + line
                + tab + tab + "catch (ClassNotFoundException var3) { throw new NoClassDefFoundError(var3.getMessage()); }" + line
                + tab + "}" + line;
        String content = packageContent + importContent + classFirstLineContent + fieldContent + constructorContent + methodContent + staticContent + "}";

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
        Constructor constructor = c.getConstructor(FyxInvocationHandler.class);

        return constructor.newInstance(h);
    }
}
