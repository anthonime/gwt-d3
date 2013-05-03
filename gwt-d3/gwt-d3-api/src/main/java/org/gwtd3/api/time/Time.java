package org.gwtd3.api.time;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Provide access to time routines.
 * 
 * @author <a href="mailto:eric.citaire@gmail.com">Eric Citaire</a>
 * 
 */
public class Time extends JavaScriptObject {

	protected Time() {
		super();
	}

	/**
	 * Create a new local time formatter for a given specifier.
	 * <p>
	 * The specifier string may contain the following directives.
	 * <ul>
	 * <li> <code>%a</code> - abbreviated weekday name.
	 * <li> <code>%A</code> - full weekday name.
	 * <li> <code>%b</code> - abbreviated month name.
	 * <li> <code>%B</code> - full month name.
	 * <li> <code>%c</code> - date and time, as "%a %b %e %H:%M:%S %Y".
	 * <li> <code>%d</code> - zero-padded day of the month as a decimal number
	 * [01,31].
	 * <li> <code>%e</code> - space-padded day of the month as a decimal number [
	 * 1,31]; equivalent to %_d.
	 * <li> <code>%H</code> - hour (24-hour clock) as a decimal number [00,23].
	 * <li> <code>%I</code> - hour (12-hour clock) as a decimal number [01,12].
	 * <li> <code>%j</code> - day of the year as a decimal number [001,366].
	 * <li> <code>%m</code> - month as a decimal number [01,12].
	 * <li> <code>%M</code> - minute as a decimal number [00,59].
	 * <li> <code>%p</code> - either AM or PM.
	 * <li> <code>%S</code> - second as a decimal number [00,61].
	 * <li> <code>%U</code> - week number of the year (Sunday as the first day of
	 * the week) as a decimal number [00,53].
	 * <li> <code>%w</code> - weekday as a decimal number [0(Sunday),6].
	 * <li> <code>%W</code> - week number of the year (Monday as the first day of
	 * the week) as a decimal number [00,53].
	 * <li> <code>%x</code> - date, as "%m/%d/%y".
	 * <li> <code>%X</code> - time, as "%H:%M:%S".
	 * <li> <code>%y</code> - year without century as a decimal number [00,99].
	 * <li> <code>%Y</code> - year with century as a decimal number.
	 * <li> <code>%Z</code> - time zone offset, such as "-0700".
	 * <li> <code>%%</code> - a literal "%" character.
	 * </ul>
	 * For %U, all days in a new year preceding the first Sunday are considered
	 * to be in week 0. For %W, all days in a new year preceding the first
	 * Monday are considered to be in week 0. In some implementations of
	 * strftime and strptime (as in Python), a directive may include an optional
	 * field width or precision; this feature is not yet implemented in D3, but
	 * may be added in the future. Also note that the JavaScript environment
	 * does not provide a standard API for accessing locale-specific date and
	 * time formatters, so D3's implementation is fixed to a locale at compile
	 * time based on the $LOCALE environment variable.
	 * <p>
	 * The % sign indicating a directive may be immediately followed by a
	 * padding modifier:
	 * <p>
	 * <ul>
	 * <li> <code>0</code> - zero-padding
	 * <li> <code>_</code> - space-padding
	 * <li> <code>-</code> - disable padding
	 * </ul>
	 * <p>
	 * If no padding modifier is specified, the default is 0 for all directives,
	 * except for %e which defaults to _).
	 * 
	 * @see <a
	 *      href="https://github.com/mbostock/d3/wiki/Time-Formatting#wiki-format">Official
	 *      API documentation</a>
	 * 
	 * @param specifier
	 *            the specifier string.
	 * @return the formatted string.
	 */
	public final native Format format(String specifier) /*-{
		return this.format(specifier);
	}-*/;

	/**
	 * Construct a linear time scale.
	 * 
	 * @return time scale
	 */
	public final native TimeScale scale()/*-{
		return this.scale();
	}-*/;

}
