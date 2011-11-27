/*
 * Copyright (c) 2011 Andrejs Jermakovics.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package imageicons;

import java.util.LinkedList;

import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;

public class ImageIconDecorator implements ILabelDecorator {

	private static final int MAX_ICON_SIZE = 16;
	private static final String PNG_EXT = "png";
	private static final String GIF_EXT = "gif";
	private static final String ICO_EXT = "ico";

	private LinkedList<Image> createdImages = new LinkedList<Image>();
	
	public ImageIconDecorator() {
	}
	
	@Override
	public Image decorateImage(Image image, Object element) {
		try {
			if( !(element instanceof IStorage) )
				return null;
			
			IStorage stor = (IStorage) element;
			IPath path = stor.getFullPath();
			
			if( GIF_EXT.equalsIgnoreCase(path.getFileExtension()) || PNG_EXT.equalsIgnoreCase(path.getFileExtension()) || ICO_EXT.equalsIgnoreCase(path.getFileExtension()) ){
				ImageData data = new ImageData(stor.getContents());

				if( data != null && data.width <= MAX_ICON_SIZE && data.height <= MAX_ICON_SIZE ) {
					Image img = new Image(Display.getCurrent(), data);
					createdImages.add(img);
					return img;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			ImageIconsPlugin.log(e);
		}

		return null;
	}

	@Override
	public String decorateText(String text, Object element) {
		return null;
	}

	@Override
	public void addListener(ILabelProviderListener listener) {
		
	}

	@Override
	public void dispose() {
		for(Image img: createdImages)
			img.dispose();
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
	}

}
