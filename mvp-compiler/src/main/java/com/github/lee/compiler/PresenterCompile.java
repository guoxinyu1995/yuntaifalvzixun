package com.github.lee.compiler;

import com.github.lee.annotation.InjectPresenter;
import com.google.auto.service.AutoService;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

@AutoService(Processor.class)
public class PresenterCompile extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
      //获取InjectPresenter注解
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(InjectPresenter.class);
        for (Element item : elements) {
            System.out.println("item.getEnclosingElement() ================ " + item.getEnclosingElement());
        }
        return true;
    }
}