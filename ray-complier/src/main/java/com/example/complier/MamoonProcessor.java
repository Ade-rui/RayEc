package com.example.complier;

import com.example.annotations.AppRegisterGenerator;
import com.example.annotations.EntryGenerator;
import com.example.annotations.PayEntryGenerator;
import com.google.auto.service.AutoService;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.SimpleAnnotationValueVisitor7;

/**
 * Created by wrf on 2018/1/31.
 */

@AutoService(Processor.class)
public class MamoonProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        generatoEntryCode(roundEnvironment);
        generatoPayEntryCode(roundEnvironment);
        generatoAppRegisterCode(roundEnvironment);
        return true;
    }

    private void generatoPayEntryCode(RoundEnvironment roundEnvironment){
        final PayEntryVisitor visitor = new PayEntryVisitor(processingEnv.getFiler());
        scan(roundEnvironment, PayEntryGenerator.class,visitor);
    }

    private void generatoEntryCode(RoundEnvironment roundEnvironment){
        final EntryVistor vistor = new EntryVistor(processingEnv.getFiler());
        scan(roundEnvironment, EntryGenerator.class,vistor);
    }

    private void generatoAppRegisterCode(RoundEnvironment roundEnvironment){
        final AppRegisterVisitor visitor = new AppRegisterVisitor(processingEnv.getFiler());
        scan(roundEnvironment, AppRegisterGenerator.class,visitor);
    }


    private void scan(RoundEnvironment env, Class<? extends Annotation> generatorClass, SimpleAnnotationValueVisitor7 visitor) {
        for (Element element : env.getElementsAnnotatedWith(generatorClass)) {
            List<? extends AnnotationMirror> annotationMirrors = element.getAnnotationMirrors();

            for (AnnotationMirror annotationMirror : annotationMirrors) {
                Map<? extends ExecutableElement, ? extends AnnotationValue> elementValues = annotationMirror.getElementValues();

                Set<? extends Map.Entry<? extends ExecutableElement, ? extends AnnotationValue>> entries = elementValues.entrySet();
                for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : entries) {
                    entry.getValue().accept(visitor,null);
                }
            }
        }
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        final Set<String> typeNames = new LinkedHashSet<>();
        final Set<Class<? extends Annotation>> types = getSupportAnnotations();
        for (Class<? extends Annotation> type : types) {
            typeNames.add(type.getCanonicalName());
        }

        return typeNames;
    }

    private Set<Class<? extends Annotation>> getSupportAnnotations() {
        final Set<Class<? extends Annotation>> types = new LinkedHashSet<>();
        types.add(EntryGenerator.class);
        types.add(PayEntryGenerator.class);
        types.add(AppRegisterGenerator.class);
        return types;
    }
}
