/*
 * MethodAcceptor.java
 *
 * Created on 2002��1��8��, ����2:24
 */

package org.javautil.reflect;
import java.lang.reflect.Method;
/**
 *
 * @author  Administrator
 * @version 
 */
public interface MethodFilter {
    
    public boolean accepts( Class owner, Method m);
    
}

