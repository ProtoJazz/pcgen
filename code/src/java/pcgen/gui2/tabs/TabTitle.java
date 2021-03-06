/*
 * TabTitle.java
 * Copyright 2010 Connor Petty <cpmeister@users.sourceforge.net>
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 * 
 * Created on Aug 3, 2010, 5:36:10 PM
 */
package pcgen.gui2.tabs;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Hashtable;

import pcgen.system.LanguageBundle;
import pcgen.util.enumeration.Tab;

/**
 * A container for information relating to how a character tab should be
 * displayed.
 * 
 * @author Connor Petty &lt;cpmeister@users.sourceforge.net&gt;
 * @version $Revision: $
 */
public class TabTitle
{

	public static final String FOREGROUND = "foreground"; //$NON-NLS-1$
	public static final String BACKGROUND = "background"; //$NON-NLS-1$
	public static final String ENABLED = "enabled"; //$NON-NLS-1$
	public static final String TITLE = "title"; //$NON-NLS-1$
	public static final String ICON = "icon"; //$NON-NLS-1$
	public static final String TOOLTIP = "tooltip"; //$NON-NLS-1$
	public static final String TAB = "tab"; //$NON-NLS-1$
	private PropertyChangeSupport support;
	private Hashtable<String, Object> table;

	/**
	 * Create a new TabTitle instance for a specific tab.
	 * @param tab The tab to be represented.
	 */
	public TabTitle(Tab tab)
	{
		this(tab.label(), tab);
	}

	/**
	 * Create a new TabTitle instance for a specific tab.
	 * @param title The title to be displayed on the tab.
	 * @param tab The tab to be represented.
	 */
	public TabTitle(String title, Tab tab)
	{
		this();
		if (title.startsWith("in_")) //$NON-NLS-1$
		{
			putValue(TITLE, LanguageBundle.getString(title));
		}
		else
		{
			putValue(TITLE, title);
		}
		if (tab != null)
		{
			putValue(TAB, tab);
		}
	}

	/**
	 * Create a new, empty TabTitle instance.
	 */
	public TabTitle()
	{
		support = new PropertyChangeSupport(this);
		table = new Hashtable<>();
	}

	public void addPropertyChangeListener(PropertyChangeListener l)
	{
		support.addPropertyChangeListener(l);
	}

	public void removePropertyChangeListener(PropertyChangeListener l)
	{
		support.removePropertyChangeListener(l);
	}

	public Object getValue(String prop)
	{
		return table.get(prop);
	}

	public void putValue(String prop, Object value)
	{
		support.firePropertyChange(prop, table.put(prop, value), value);
	}

	public void setEnabled(boolean enable)
	{
		putValue(ENABLED, enable);
	}

	public boolean isEnabled()
	{
		return (Boolean) getValue(ENABLED);
	}

	public Tab getTab()
	{
		return (Tab) getValue(TAB);
	}
}
