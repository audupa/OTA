/**
 * 
 */
package com.intuit.traveldiary.dao;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Vijayan Srinivasan
 * @since Jan 23, 2015 4:11:25 AM
 * 
 *        This annotation helps to mark the fields in the DO classes which is
 *        computed and not located in the table schema.
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Formula {

}
