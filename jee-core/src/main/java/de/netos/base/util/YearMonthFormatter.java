package de.netos.base.util;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target({
		FIELD, PARAMETER, METHOD })
public @interface YearMonthFormatter {

}
