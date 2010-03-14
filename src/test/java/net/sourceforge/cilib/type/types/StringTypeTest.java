/**
 * Copyright (C) 2003 - 2009
 * Computational Intelligence Research Group (CIRG@UP)
 * Department of Computer Science
 * University of Pretoria
 * South Africa
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package net.sourceforge.cilib.type.types;

import org.junit.Assert;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;

/**
 *
 * @author Gary Pampara
 */
public class StringTypeTest {

    @Test
    public void testClone() {
        StringType t = new StringType("test string");
        StringType clone = t.getClone();
        Assert.assertTrue(t.getString().equals(clone.getString()));
        Assert.assertEquals(t.getString(), clone.getString());
    }

    @Test
    public void testDimensionality() {
        StringType s = new StringType("This is a StringType");
        Assert.assertEquals(1, Types.getDimension(s));
    }

    @Test
    public void whitespaceReplacement() {
        StringType type = new StringType("This is a string with whitespace");
        Assert.assertThat(type.toString(), is(equalTo("This_is_a_string_with_whitespace")));
    }

}
