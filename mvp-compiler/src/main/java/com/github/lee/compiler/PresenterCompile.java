package com.github.lee.compiler;

import com.github.lee.annotation.InjectLayout;
import com.github.lee.annotation.InjectPresenter;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

@AutoService(Processor.class)
public class PresenterCompile extends AbstractProcessor {
    private Messager messager;
    private Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
          messager = processingEnv.getMessager();
          filer = processingEnv.getFiler();

    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
      //获取InjectPresenter注解
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(InjectPresenter.class);
        for (Element item : elements) {
            System.out.println("item.getEnclosingElement() ================ " + item.getEnclosingElement());
        }

        TypeSpec.Builder myClass = TypeSpec.classBuilder("MyClass");
//        myClass.addModifiers(Modifier.PUBLIC);
        myClass.addJavadoc("Hello");
        TypeSpec build = myClass.build();
        JavaFile javaFile = JavaFile.builder("com.lee", build).build();
        try {
            javaFile.writeTo(filer);

        } catch (Exception e) {

        }
        messager.printMessage(Diagnostic.Kind.NOTE, "Hello World");
        return true;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.RELEASE_7;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> set = new HashSet<>();
        set.add(InjectLayout.class.getCanonicalName());
        return set;
    }
}