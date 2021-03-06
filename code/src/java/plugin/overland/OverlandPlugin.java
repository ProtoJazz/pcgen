/*
 * Copyright 2003 (C) Devon Jones
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
 * $Id$
 */
 package plugin.overland;

import gmgen.GMGenSystemView;
import gmgen.pluginmgr.messages.AddMenuItemToGMGenToolsMenuMessage;
import gmgen.pluginmgr.messages.RequestAddTabToGMGenMessage;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import pcgen.core.SettingsHandler;
import pcgen.gui2.tools.Utility;
import pcgen.pluginmgr.InteractivePlugin;
import pcgen.pluginmgr.PCGenMessage;
import pcgen.pluginmgr.PCGenMessageHandler;
import pcgen.pluginmgr.messages.FocusOrStateChangeOccurredMessage;
import pcgen.system.LanguageBundle;
import plugin.overland.gui.OverPanel;

/**
 * The <code>Overland Plugin</code> provides a number
 * of useful utilities that help with overland travel <br>
 * Created on February 26, 2003<br>
 * Updated on February 26, 2003
 * @author  Expires 2003
 * @author Vincent Lhote
 * @version 2.10
 */
public class OverlandPlugin implements InteractivePlugin
{
	/** Log name / plugin id */
	public static final String LOG_NAME = "Overland_Travel"; //$NON-NLS-1$

	/** The plugin menu item in the tools menu. */
	private JMenuItem overToolsItem = new JMenuItem();

	/** The user interface that this class will be using. */
	private OverPanel theView;

	/** The English name of the plugin. */
	private static final String NAME = "Overland Travel"; //$NON-NLS-1$
	/** Key of plugin tab. */
	private static final String IN_NAME = "in_plugin_overland_name"; //$NON-NLS-1$
	/** Mnemonic in menu for {@link #IN_NAME} */
	private static final String IN_NAME_MN = "in_mn_plugin_overland_name"; //$NON-NLS-1$

	/** The version number of the plugin. */
	private String version = "01.00.99.01.00"; //$NON-NLS-1$

	private PCGenMessageHandler messageHandler;

	/**
	 * Creates a new instance of OverlandPlugin
	 */
	public OverlandPlugin()
	{
		// Do Nothing
	}

	/**
	 * Starts the plugin, registering itself with the <code>TabAddMessage</code>.
	 */
    @Override
	public void start(PCGenMessageHandler mh)
	{
    	messageHandler = mh;
		File datadir = this.getDataDirectory();
		theView = new OverPanel(datadir);
		messageHandler.handleMessage(new RequestAddTabToGMGenMessage(this, getLocalizedName(), getView()));
		initMenus();
	}

	/**
	 * {@inheritDoc}
	 */
    @Override
	public void stop()
	{
		messageHandler = null;
	}

    @Override
	public int getPriority()
	{
		return SettingsHandler.getGMGenOption(LOG_NAME + ".LoadOrder", 90);
	}

	/**
	 * Accessor for name
	 * @return name
	 */
    @Override
	public String getPluginName()
	{
		return NAME;
	}
	
	private String getLocalizedName()
	{
		return LanguageBundle.getString(IN_NAME);
	}

	/**
	 * Gets the view that this class is using.
	 * @return the view.
	 */
	public Component getView()
	{
		return theView;
	}

	/**
	 * listens to messages from the GMGen system, and handles them as needed
	 * @param message the source of the event from the system
	 */
    @Override
	public void handleMessage(PCGenMessage message)
	{
		if (message instanceof FocusOrStateChangeOccurredMessage)
		{
			if (isActive())
			{
				overToolsItem.setEnabled(false);
			}
			else
			{
				overToolsItem.setEnabled(true);
			}
		}
	}

	/**
	 * Returns true if the pane is active
	 * @return true if the pane is active
	 */
	public boolean isActive()
	{
		JTabbedPane tp = Utility.getTabbedPaneFor(theView);
		return tp != null && JOptionPane.getFrameForComponent(tp).isFocused()
			&& tp.getSelectedComponent().equals(theView);
	}

	/**
	 * Initialise the menus for this plugin
	 */
	public void initMenus()
	{
		overToolsItem.setMnemonic(LanguageBundle.getMnemonic(IN_NAME_MN));
		overToolsItem.setText(getLocalizedName());
		overToolsItem.addActionListener(new ActionListener()
		{
            @Override
			public void actionPerformed(ActionEvent evt)
			{
				toolMenuItem(evt);
			}
		});
		messageHandler.handleMessage(new AddMenuItemToGMGenToolsMenuMessage(this, overToolsItem));
	}

	/**
	 * Sets the index for the pane 
	 * @param evt
	 */
	public void toolMenuItem(ActionEvent evt)
	{
		JTabbedPane tp = GMGenSystemView.getTabPane();

		for (int i = 0; i < tp.getTabCount(); i++)
		{
			if (tp.getComponentAt(i) instanceof OverPanel)
			{
				tp.setSelectedIndex(i);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public File getDataDirectory()
	{
		File dataDir =
				new File(SettingsHandler.getGmgenPluginDir(), getPluginName());
		return dataDir;
	}
}
