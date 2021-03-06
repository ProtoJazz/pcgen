/*
 * Copyright (c) 2007 Tom Parker <thpr@users.sourceforge.net>
 * 
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA
 */
package plugin.lsttokens.testsupport;

import java.net.URISyntaxException;

import org.junit.Test;

import pcgen.cdom.base.CDOMObject;
import pcgen.cdom.base.Loadable;
import pcgen.persistence.PersistenceLayerException;
import pcgen.rules.context.LoadContext;
import pcgen.rules.persistence.token.CDOMSecondaryToken;
import pcgen.rules.persistence.token.QualifierToken;
import plugin.qualifier.pobject.QualifiedToken;

public abstract class AbstractPrimitiveTokenTestCase<T extends CDOMObject, TC extends Loadable>
		extends AbstractCDOMTokenTestCase<T>
{

	private static QualifierToken<CDOMObject> qual = new QualifiedToken<CDOMObject>();

	public abstract CDOMSecondaryToken<?> getSubToken();

	private final String prim;
	private final String target;
	private final String good;

	protected AbstractPrimitiveTokenTestCase(String primitive, String tgt)
	{
		prim = primitive;
		target = tgt;
		good = target == null ? prim : prim + "=" + target;
	}

	public String getSubTokenName()
	{
		return getSubToken().getTokenName();
	}

	public abstract Class<TC> getTargetClass();

	@Override
	public void setUp() throws PersistenceLayerException, URISyntaxException
	{
		super.setUp();
		TokenRegistration.register(getSubToken());
		TokenRegistration.register(qual);
	}

	protected void construct(LoadContext loadContext, String one)
	{
		loadContext.getReferenceContext().constructCDOMObject(getTargetClass(), one);
	}

	@Override
	protected String getAlternateLegalValue()
	{
		return getSubTokenName() + '|' + "QUALIFIED[" + good + "]";
	}

	@Override
	protected String getLegalValue()
	{
		return getSubTokenName() + '|' + good;
	}

	@Override
	protected ConsolidationRule getConsolidationRule()
	{
		return ConsolidationRule.OVERWRITE;
	}

	@Test
	public void testPrimitiveEquals() throws PersistenceLayerException
	{
		assertFalse(parse(getSubTokenName() + '|' + "QUALIFIED[" + prim + "=]"));
		assertNoSideEffects();
	}

	@Test
	public void testPrimitivePipe() throws PersistenceLayerException
	{
		assertFalse(parse(getSubTokenName() + '|' + "QUALIFIED[" + good + "|]"));
		assertNoSideEffects();
	}

	@Test
	public void testPrimitiveComma() throws PersistenceLayerException
	{
		assertFalse(parse(getSubTokenName() + '|' + "QUALIFIED[" + good + ",]"));
		assertNoSideEffects();
	}

	@Test
	public void testPipePrimitive() throws PersistenceLayerException
	{
		assertFalse(parse(getSubTokenName() + '|' + "QUALIFIED[|" + good + "]"));
		assertNoSideEffects();
	}

	@Test
	public void testCommaPrimitive() throws PersistenceLayerException
	{
		assertFalse(parse(getSubTokenName() + '|' + "QUALIFIED[," + good + "]"));
		assertNoSideEffects();
	}

	@Test
	public void testPrimitiveDoublePipe() throws PersistenceLayerException
	{
		assertFalse(parse(getSubTokenName() + '|' + "QUALIFIED[TestWP2||"
				+ good + "]]"));
		assertNoSideEffects();
	}

	@Test
	public void testPrimitiveDoubleComma() throws PersistenceLayerException
	{
		assertFalse(parse(getSubTokenName() + '|' + "QUALIFIED[TYPE=Foo,,"
				+ good + "]"));
		assertNoSideEffects();
	}

	@Test
	public void testPrimitiveAll1() throws PersistenceLayerException
	{
		assertFalse(parse(getSubTokenName() + '|' + "QUALIFIED[ALL|" + good
				+ "]"));
		assertNoSideEffects();
	}

	@Test
	public void testPrimitiveAll2() throws PersistenceLayerException
	{
		assertFalse(parse(getSubTokenName() + '|' + "QUALIFIED[" + good
				+ "|ALL]"));
		assertNoSideEffects();
	}

	@Test
	public void testTypePrimitiveBadSyntax() throws PersistenceLayerException
	{
		assertFalse(parse(getSubTokenName() + '|' + "QUALIFIED[TYPE=Foo]"
				+ good));
		assertNoSideEffects();
	}

	@Test
	public void testPrimitiveTypeBadSyntax() throws PersistenceLayerException
	{
		assertFalse(parse(getSubTokenName() + '|' + "QUALIFIED[" + good
				+ "]TYPE=Foo"));
		assertNoSideEffects();
	}

	@Test
	public void testPrimitiveTypePipeBadSyntax()
			throws PersistenceLayerException
	{
		assertFalse(parse(getSubTokenName() + '|' + "QUALIFIED[" + good
				+ "]TYPE=Foo|TYPE=Bar"));
		assertNoSideEffects();
	}

	@Test
	public void testPrimitiveTypeCommaBadSyntax()
			throws PersistenceLayerException
	{
		assertFalse(parse(getSubTokenName() + '|' + "QUALIFIED[TYPE=Foo]"
				+ good + ",TYPE=Bar"));
		assertNoSideEffects();
	}

	@Test
	public void testPrimitiveUsedAsQualifier() throws PersistenceLayerException
	{
		assertFalse(parse(getSubTokenName() + '|' + good + "[" + good + "]"));
		assertNoSideEffects();
	}

	@Test
	public void testDotPrimitive() throws PersistenceLayerException
	{
		construct(primaryContext, "TestWP1");
		construct(secondaryContext, "TestWP1");
		boolean ret = parse(getSubTokenName() + '|' + "QUALIFIED[TestWP1." + good
				+ "]");
		if (ret)
		{
			assertConstructionError();
		}
		else
		{
			assertNoSideEffects();
		}
	}

	@Test
	public void testGoodBadNoSideEffect() throws PersistenceLayerException
	{
		construct(primaryContext, "TestWP1");
		construct(primaryContext, "TestWP2");
		construct(primaryContext, "TestWP3");
		construct(secondaryContext, "TestWP1");
		construct(secondaryContext, "TestWP2");
		construct(secondaryContext, "TestWP3");
		assertTrue(parse(getSubTokenName() + '|' + "QUALIFIED[TestWP1|TestWP2]"));
		assertTrue(parseSecondary(getSubTokenName() + '|'
				+ "QUALIFIED[TestWP1|TestWP2]"));
		assertFalse(parse(getSubTokenName() + '|' + "QUALIFIED[TestWP3||"
				+ good + "]"));
		assertNoSideEffects();
	}

	@Test
	public void testAllNoSideEffect() throws PersistenceLayerException
	{
		// Test with All
		construct(primaryContext, "TestWP1");
		construct(primaryContext, "TestWP2");
		construct(secondaryContext, "TestWP1");
		construct(secondaryContext, "TestWP2");
		assertTrue(parse(getSubTokenName() + '|' + "QUALIFIED[TestWP1|TestWP2]"));
		assertTrue(parseSecondary(getSubTokenName() + '|'
				+ "QUALIFIED[TestWP1|TestWP2]"));
		assertFalse(parse(getSubTokenName() + '|' + "QUALIFIED[" + good
				+ "|ALL]"));
		assertNoSideEffects();
	}

	@Test
	public void testPrimitiveGood() throws PersistenceLayerException
	{
		if (target != null)
		{
			construct(primaryContext, target);
			construct(secondaryContext, target);
		}
		runRoundRobin(getSubTokenName() + '|' + good);
	}

	@Test
	public void testQualifiedPrimitiveGood() throws PersistenceLayerException
	{
		if (target != null)
		{
			construct(primaryContext, target);
			construct(secondaryContext, target);
		}
		runRoundRobin(getSubTokenName() + '|' + "QUALIFIED[" + good + "]");
	}

	@Test
	public void doPrimitiveIllegalTarget(String illegal)
			throws PersistenceLayerException
	{
		String primitive = prim;
		if (illegal != null)
		{
			primitive += "=" + illegal;
		}
		boolean parse = parse(getSubTokenName() + '|' + "QUALIFIED["
				+ primitive + "]");
		if (parse)
		{
			assertConstructionError();
		}
		else
		{
			assertNoSideEffects();
		}
	}

}