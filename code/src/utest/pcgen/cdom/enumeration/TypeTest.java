/*
 * TypeTest.java
 * Copyright 2008 (C) Jasper Spaans <jasperspaans@users.sourceforge.net>
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
 */
package pcgen.cdom.enumeration;

import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

import org.junit.Test;


/**
 * The Class <code>TypeTest</code> tests that the Type
 * class is functioning correctly. 
 * 
 * Last Editor: $Author: $
 * Last Edited: $Date:  $
 * 
 * @author Jasper Spaans <jasperspaans@users.sourceforge.net>
 * @version $Revision:  $
 */
public class TypeTest extends TestCase
{
	
	/**
	 * Test whether type can be sorted, by adding it to a hashset.
	 * Added to check fix on Bug with tracker nr. 2413116 
	 */
	@Test
	public void testSortable()
	{
		try
		{
			Set<Type> typeset = new HashSet<>();
			typeset.add(Type.getConstant("testitem 1"));
			typeset.add(Type.getConstant("testitem 2"));
		}
		catch (ClassCastException cce)
		{
			fail();
		}

	}

}
