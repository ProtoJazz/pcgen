/*
 * Hyperactive.java
 * Copyright 2003 (C) Greg Bingleman <byngl@hotmail.com>
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
 * Created on January 23, 2003, 10:03 PM
 *
 * $Id: Hyperactive.java 1817 2006-12-27 23:54:53Z jdempsey $
 */
package pcgen.gui2.tools;

import java.io.IOException;
import pcgen.util.Logging;

import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

/**
 * This makes URLs load in a browser when clicked.
 *
 * @author     Greg Bingleman &lt;byngl@hotmail.com&gt;
 * @version    $Revision: 1817 $
 */
public final class Hyperactive implements HyperlinkListener
{

	@Override
	public void hyperlinkUpdate(HyperlinkEvent e)
	{
		if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
		{
			final JEditorPane pane = (JEditorPane) e.getSource();

//			if (e instanceof HTMLFrameHyperlinkEvent)
//			{
//				final HTMLFrameHyperlinkEvent evt = (HTMLFrameHyperlinkEvent) e;
//				final HTMLDocument doc = (HTMLDocument) pane.getDocument();
//				doc.processHTMLFrameHyperlinkEvent(evt);
//			}
//			else
//			{
			try
			{
				Utility.viewInBrowser(e.getURL());
			}
			catch (IOException t)
			{
				JOptionPane.showMessageDialog(pane, "<html>An error occurred while opening your browser.<br>" +
						"Please check PCGen's browser settings.</html>", "Could not open browser", JOptionPane.ERROR_MESSAGE);
				Logging.errorPrint(
						"Exception in Hyperactive::hyperlinkUpdate", t);
			}
//			}
		}
	}

}
