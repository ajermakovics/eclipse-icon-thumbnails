/*
 * Copyright (c) 2011 Andrejs Jermakovics.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package imageicons;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class ImageIconsPlugin extends AbstractUIPlugin {

	private static ImageIconsPlugin plugin;
	public final static String PLUGIN_ID = "ImageIcons";

	public ImageIconsPlugin() {
		
	}

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		
		plugin = this;
	}
	
	@Override
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
	}
	
	public static ImageIconsPlugin getDefault() {
		return plugin;
	}
	
	public static void log(IStatus status) {
		if( plugin != null )
			plugin.getLog().log(status);
		else
			ResourcesPlugin.getPlugin().getLog().log(status);
	}
	
	public static void log(Exception e) {
		Status status = new Status(IStatus.ERROR, PLUGIN_ID, e.getMessage(), e);
		log(status);
	}
	
}
