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
package net.sourceforge.cilib.problem;

import net.sourceforge.cilib.problem.dataset.DataSetBuilder;
import net.sourceforge.cilib.problem.solution.Fitness;
import net.sourceforge.cilib.type.DomainRegistry;
import net.sourceforge.cilib.type.types.Type;
import net.sourceforge.cilib.util.Cloneable;

/**
 * <p>
 * Optimisation problems are characterized by a domain that specifies the search space and
 * a fitness given a potential solution. This interface ensures that an
 * {@linkplain net.sourceforge.cilib.algorithm.OptimisationAlgorithm optimization algorithm} has
 * all the information it needs to find a solution to a given optimisation problem. In addition, it is the
 * responsibility of an optimisation problem to keep track of the number of times the fitness has
 * been evaluated.
 * </p>
 * <p>All optimisation problems must implement this interface.</p>
 *
 */
public interface Problem extends Cloneable {

    /**
     * {@inheritDoc}
     */
    @Override
    Problem getClone();

    /**
     * Returns the fitness of a potential solution to this problem. The solution object is described
     * by the domain of this problem, see {@link #getDomain()}. An instance of
     * {@link net.sourceforge.cilib.problem.InferiorFitness} should be returned if the solution
     * falls outside the search space of this problem.
     *
     * @param solution The potential solution found by the optimisation algorithm.
     * @return The fitness of the solution.
     */
    Fitness getFitness(Type solution);

    /**
     * Returns the number of times the underlying fitness function has been evaluated.
     *
     * @return The number fitness evaluations.
     */
    int getFitnessEvaluations();

    /**
     * Returns the domain component that describes the search space for this problem.
     *
     * @return A {@link net.sourceforge.cilib.type.DomainRegistry} object representing the search space.
     */
    DomainRegistry getDomain();

    void setDomain(String domain);

}
