package com.example.complier;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor7;

/**
 * Created by wrf on 2018/1/31.
 */

public class PayEntryVisitor extends SimpleAnnotationValueVisitor7<Void,Void> {

    final Filer FILER;
    private String mPackageNmae;

    public PayEntryVisitor(Filer FILER) {
        this.FILER = FILER;
    }

    @Override
    public Void visitString(String s, Void aVoid) {
        mPackageNmae = s;
        return aVoid;
    }

    @Override
    public Void visitType(TypeMirror typeMirror, Void aVoid) {
        generateJavaCode(typeMirror);
        return aVoid;
    }

    private void generateJavaCode(TypeMirror typeMirror) {
        final TypeSpec activity = TypeSpec.classBuilder("WXPayEntryActivity")
                .addModifiers(Modifier.PUBLIC)
                .addModifiers(Modifier.FINAL)
                .superclass(TypeName.get(typeMirror))
                .build();
        final JavaFile javaFile = JavaFile.builder(mPackageNmae + ".wxapi", activity)
                .addFileComment("微信入口文件")
                .build();

        try {
            javaFile.writeTo(FILER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
