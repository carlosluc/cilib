/**
 * Computational Intelligence Library (CIlib)
 * Copyright (C) 2003 - 2010
 * Computational Intelligence Research Group (CIRG@UP)
 * Department of Computer Science
 * University of Pretoria
 * South Africa
 *
 * This library is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, see <http://www.gnu.org/licenses/>.
 */
package net.sourceforge.cilib.functions.continuous.unconstrained;

import net.sourceforge.cilib.functions.ContinuousFunction;
import net.sourceforge.cilib.type.types.container.Vector;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class Bukin4Test {

    private ContinuousFunction function;

    public Bukin4Test() {
    }

    @Before
    public void instantiate() {
        this.function = new Bukin4();
    }

    /** Test of evaluate method, of class za.ac.up.cs.ailib.Functions.Beale4. */
    @Test
    public void testEvaluate() {
        Vector x = Vector.of(1,2);
        assertEquals(400.11, function.apply(x), 0.0);

        x.setReal(0, -10.0);
        x.setReal(1, 0.0);
        assertEquals(0.0, function.apply(x), 0.0);
    }
}
