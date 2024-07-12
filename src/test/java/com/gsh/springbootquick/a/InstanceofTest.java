package com.gsh.springbootquick.a;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.core.ResolvableType;

/**
 * @author GSH
 * @create 2023/1/5 23:35
 */
public class InstanceofTest {

    public static void main(String[] args) {
        BeanDefinition beanDefinition = new BeanDefinition() {
            @Override
            public void setAttribute(String name, Object value) {

            }

            @Override
            public Object getAttribute(String name) {
                return null;
            }

            @Override
            public Object removeAttribute(String name) {
                return null;
            }

            @Override
            public boolean hasAttribute(String name) {
                return false;
            }

            @Override
            public String[] attributeNames() {
                return new String[0];
            }

            @Override
            public void setParentName(String s) {

            }

            @Override
            public String getParentName() {
                return null;
            }

            @Override
            public void setBeanClassName(String s) {

            }

            @Override
            public String getBeanClassName() {
                return "BeanClassName";
            }

            @Override
            public void setScope(String s) {

            }

            @Override
            public String getScope() {
                return null;
            }

            @Override
            public void setLazyInit(boolean b) {

            }

            @Override
            public boolean isLazyInit() {
                return false;
            }

            @Override
            public void setDependsOn(String... strings) {

            }

            @Override
            public String[] getDependsOn() {
                return new String[0];
            }

            @Override
            public void setAutowireCandidate(boolean b) {

            }

            @Override
            public boolean isAutowireCandidate() {
                return false;
            }

            @Override
            public void setPrimary(boolean b) {

            }

            @Override
            public boolean isPrimary() {
                return false;
            }

            @Override
            public void setFactoryBeanName(String s) {

            }

            @Override
            public String getFactoryBeanName() {
                return null;
            }

            @Override
            public void setFactoryMethodName(String s) {

            }

            @Override
            public String getFactoryMethodName() {
                return null;
            }

            @Override
            public ConstructorArgumentValues getConstructorArgumentValues() {
                return null;
            }

            @Override
            public MutablePropertyValues getPropertyValues() {
                return null;
            }

            @Override
            public void setInitMethodName(String s) {

            }

            @Override
            public String getInitMethodName() {
                return null;
            }

            @Override
            public void setDestroyMethodName(String s) {

            }

            @Override
            public String getDestroyMethodName() {
                return null;
            }

            @Override
            public void setRole(int i) {

            }

            @Override
            public int getRole() {
                return 0;
            }

            @Override
            public void setDescription(String s) {

            }

            @Override
            public String getDescription() {
                return null;
            }

            @Override
            public ResolvableType getResolvableType() {
                return null;
            }

            @Override
            public boolean isSingleton() {
                return false;
            }

            @Override
            public boolean isPrototype() {
                return false;
            }

            @Override
            public boolean isAbstract() {
                return false;
            }

            @Override
            public String getResourceDescription() {
                return null;
            }

            @Override
            public BeanDefinition getOriginatingBeanDefinition() {
                return null;
            }
        };

        BeanDefinition definition = new AbstractBeanDefinition() {
            @Override
            public void setParentName(String s) {

            }

            @Override
            public String getParentName() {
                return "pname";
            }

            @Override
            public AbstractBeanDefinition cloneBeanDefinition() {
                return null;
            }
        };

        System.out.println(beanDefinition instanceof AbstractBeanDefinition abd);
        System.out.println(definition instanceof AbstractBeanDefinition abd2);
        if (beanDefinition instanceof AbstractBeanDefinition abd) {
            System.out.println(abd.getBeanClassName()+"..");
        }
        if (definition instanceof AbstractBeanDefinition abd2){
            System.out.println(abd2.getBeanClassName());
        }

        Object a = new String("a");
        if (a instanceof String b){
            System.out.println(b);
        }

        var c = "c";
        c = null;
        System.out.println(c);
    }
}
