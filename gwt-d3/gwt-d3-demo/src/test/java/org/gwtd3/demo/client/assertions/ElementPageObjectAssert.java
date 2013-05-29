package org.gwtd3.demo.client.assertions;

import org.gwtd3.demo.client.pageobjects.ElementPageObject;

public class ElementPageObjectAssert<S extends ElementPageObjectAssert<S, A>, A extends ElementPageObject<?>>
		extends PageObjectAssert<S, A> {

	protected ElementPageObjectAssert(A actual, Class<?> selfType) {
		super(actual, selfType);
	}

}
