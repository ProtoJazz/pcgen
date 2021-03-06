/*
 * Copyright 2015 (C) Thomas Parker <thpr@users.sourceforge.net>
 * 
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * 
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */
package pcgen.cdom.content;

import pcgen.base.calculation.PCGenModifier;
import pcgen.base.formula.base.LegalScope;

/**
 * A VarModifier is a container for all the information necessary to modify a
 * variable. This includes the scope, the variable name, and the PCGenModifier
 * to be applied. This allows that grouping of information to be passed as a
 * single unit of information.
 * 
 * @param <T>
 *            The format of the variable modified by the PCGenModifier in this
 *            VarModifier
 */
public class VarModifier<T>
{

	/**
	 * The name of the Variable to be modified when this VarModifier is applied.
	 */
	public final String varName;

	/**
	 * The Scope of the variable to be modified when this VarModifier is
	 * applied.
	 */
	public final LegalScope legalScope;

	/**
	 * The PCGenModifier to be applied to the Variable when this VarModifier is
	 * applied.
	 */
	public final PCGenModifier<T> modifier;

	/**
	 * Constructs a new VarModifier containing all the information necessary to
	 * modify a variable.
	 * 
	 * @param varName
	 *            The name of the Variable to be modified when this VarModifier
	 *            is applied
	 * @param legalScope
	 *            the LegalScope in which the Modifier is applied
	 * @param modifier
	 *            The PCGenModifier to be applied to the Variable when this
	 *            VarModifier is applied
	 * @throws IllegalArgumentException
	 *             if any of the parameters are null
	 */
	public VarModifier(String varName, LegalScope legalScope,
		PCGenModifier<T> modifier)
	{
		if (varName == null)
		{
			throw new IllegalArgumentException("Var Name cannot be null");
		}
		if (legalScope == null)
		{
			throw new IllegalArgumentException("LegalScope cannot be null");
		}
		if (modifier == null)
		{
			throw new IllegalArgumentException("Modifier cannot be null");
		}
		this.varName = varName;
		this.legalScope = legalScope;
		this.modifier = modifier;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		return varName.hashCode() ^ modifier.hashCode();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object o)
	{
		if (o instanceof VarModifier)
		{
			VarModifier<?> other = (VarModifier<?>) o;
			return other.varName.equals(varName)
				&& other.legalScope.equals(legalScope)
				&& other.modifier.equals(modifier);
		}
		return false;
	}
}
