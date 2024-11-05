package org.example;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.LoaderClassPath;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;
public class FibonacciTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) {

        if ("org.example.FibonacciCalculator".equals(className.replace("/", "."))) {
            try {
                ClassPool classPool = ClassPool.getDefault();
                classPool.insertClassPath(new LoaderClassPath(loader));
                CtClass ctClass = classPool.get("org.example.FibonacciCalculator");

                // Interceptar
                CtMethod method = ctClass.getDeclaredMethod("calculateFibonacci");


                method.addLocalVariable("startTime", CtClass.longType);
                method.insertBefore("startTime = System.nanoTime();");
                method.insertAfter("System.out.println(\"Tiempo de ejecución: \" + " +
                        "(System.nanoTime() - startTime) + \" nanosegundos\");");

                byte[] byteCode = ctClass.toBytecode();
                ctClass.detach();
                return byteCode;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
