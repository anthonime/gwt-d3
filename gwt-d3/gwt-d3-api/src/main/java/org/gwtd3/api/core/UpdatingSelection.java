/**
 * 
 */
package org.gwtd3.api.core;

/**
 * @author <a href="mailto:schiochetanthoni@gmail.com">Anthony Schiochet</a>
 * 
 */
public class UpdatingSelection extends Selection {

	protected UpdatingSelection() {
	};

	public final native EnteringSelection enter()/*-{
		return this.enter();
	}-*/;

	public final native Selection exit()/*-{
		return this.exit();
	}-*/;
}
