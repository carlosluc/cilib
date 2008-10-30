/*
 * Copyright (C) 2003 - 2008
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
package net.sourceforge.cilib.neuralnetwork.foundation.measurements;

import net.sourceforge.cilib.algorithm.Algorithm;
import net.sourceforge.cilib.measurement.Measurement;
import net.sourceforge.cilib.neuralnetwork.foundation.NeuralNetworkController;
import net.sourceforge.cilib.neuralnetwork.foundation.NeuralNetworkProblem;
import net.sourceforge.cilib.type.types.Int;
import net.sourceforge.cilib.type.types.Type;

/**
 * TODO: Complete this javadoc.
 */
public class DcPatternCount implements Measurement {
	private static final long serialVersionUID = -5891911509569750125L;

	public DcPatternCount() {
	}

	public DcPatternCount(DcPatternCount rhs) {
//		super(rhs);
		throw new UnsupportedOperationException("public DcPatternCount(DcPatternCount rhs)");
	}

	public DcPatternCount getClone() {
		return new DcPatternCount(this);
	}

	public String getDomain() {
		return "Z";
	}

	public Type getValue(Algorithm algorithm) {
		int size = ((NeuralNetworkProblem) ((NeuralNetworkController) algorithm).getOptimisationProblem()).getEvaluationStrategy().getData().getCandidateSetSize();
		return new Int(size);
	}
}
