/*
 * PCGenMessage.java
 * Copyright James Dempsey, 2014
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
 * Created on 15/02/2014 2:40:34 pm
 *
 * $Id$
 */
package pcgen.pluginmgr;

import java.util.EventObject;

/**
 * The Class <code>PCGenMessage</code> describes an event in PCGen which
 * listeners can respond to. 
 *
 * <br>
 * Last Editor: $Author$
 * Last Edited: $Date$
 * 
 * @author James Dempsey &lt;jdempsey@users.sourceforge.net&gt;
 * @version $Revision$
 */

public class PCGenMessage extends EventObject
{
	/** Version number for serialisation*/
	private static final long serialVersionUID = 858210889717389487L;
	private boolean consumed;

	/**
	 * Create a new instance of PCGenMessage
	 * @param source The object which initiated the message.
	 */
	public PCGenMessage(Object source)
	{
		super(source);
	}

	/**
	 * Flag the message as consumed. This will stop the message from being sent 
	 * to further handlers. Note: Not all messages can be consumed. If this is not 
	 * allowed the message class can return false. 
	 * @return true of the message could be marked as consumed. 
	 */
	public boolean consume()
	{
		consumed = true;
		return true;
	}

	/**
	 * @return the consumed flag
	 */
	public boolean isConsumed()
	{
		return consumed;
	}
}
