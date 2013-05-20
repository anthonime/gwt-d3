/**
 * 
 */
package org.gwtd3.demo.client.testcases.arrays;

import org.gwtd3.api.arrays.Array;
import org.gwtd3.api.arrays.ForEachCallback;
import org.gwtd3.api.core.Value;

/**
 * @author <a href="mailto:schiochetanthoni@gmail.com">Anthony Schiochet</a>
 * 
 */
public class Callbacks {

	/**
	 * Return a callback that return true only if the element is greater than the given value.
	 * 
	 * @param than
	 * @return
	 */
	public static ForEachCallback<Boolean> greaterThan(final double than) {
		return new ForEachCallback<Boolean>() {
			@Override
			public Boolean forEach(final Object thisArg, final Value element, final int index, final Array<Value> array) {
				System.out.println("received " + element.asDouble() + " > " + than + " : " + (element.asDouble() > than));
				return element.asDouble() > than;
			}
		};
	}
}
