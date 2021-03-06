/*
 * ResizableEquipTypeToken.java
 * Copyright 2008 (C) James Dempsey <jdempsey@users.sourceforge.net>
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
 * Created on 3/05/2008
 *
 * $Id: ResizableEquipTypeToken.java 6171 2008-05-03 12:35:57Z jdempsey $
 */
package plugin.lsttokens.gamemode;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import pcgen.cdom.base.Constants;
import pcgen.core.GameMode;
import pcgen.persistence.lst.GameModeLstToken;

/**
 * <code>ResizableEquipTypeToken</code> parses the list of equipment
 * types designated as able to be automatically resized. 
 *
 * Last Editor: $Author: jdempsey $
 * Last Edited: $Date: 2008-05-03 14:35:57 +0200 (Sa, 03 Mai 2008) $
 *
 * @author Stefan Radermacher &lt;zaister@users.sourceforge.net&gt;
 * @version $Revision: 6171 $
 */
public class MonsterRolesToken implements GameModeLstToken
{

	/* (non-Javadoc)
	 * @see pcgen.persistence.lst.LstToken#getTokenName()
	 */
    @Override
	public String getTokenName()
	{
		return "MONSTERROLES";
	}

	/* (non-Javadoc)
	 * @see pcgen.persistence.lst.GameModeLstToken#parse(pcgen.core.GameMode, java.lang.String, java.net.URI)
	 */
    @Override
	public boolean parse(GameMode gameMode, String value, URI source)
	{
		List<String> list = new ArrayList<String>();
		final StringTokenizer aTok = new StringTokenizer(value, Constants.PIPE, false);

		while (aTok.hasMoreTokens())
		{
			final String aString = aTok.nextToken();
			list.add(aString);
		}
		gameMode.setMonsterRoleList(list);
		return true;
	}
}
