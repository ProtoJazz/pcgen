/*
 * CombatantHasBeenUpdatedMessage.java
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
 * Created on 18/02/2014 10:30:08 pm
 *
 * $Id$
 */
package gmgen.pluginmgr.messages;

import gmgen.plugin.Combatant;
import pcgen.pluginmgr.PCGenMessage;

/**
 * The Class <code>CombatantHasBeenUpdatedMessage</code> encapsulates a
 * message that a specific combatant has been modified.
 *
 * <br>
 * Last Editor: $Author$
 * Last Edited: $Date$
 * 
 * @author James Dempsey &lt;jdempsey@users.sourceforge.net&gt;
 * @version $Revision$
 */

@SuppressWarnings("serial")
public class CombatantHasBeenUpdatedMessage extends PCGenMessage
{

	private final Combatant combatant;

	/**
	 * Create a new instance of CombatantHasBeenUpdatedMessage
	 * @param source The source of the message.
	 * @param combatant The combatant that was updated.
	 */
	public CombatantHasBeenUpdatedMessage(Object source, Combatant combatant)
	{
		super(source);
		this.combatant = combatant;
	}

	public Combatant getCombatant()
	{
		return combatant;
	}

}
