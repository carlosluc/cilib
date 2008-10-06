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
package net.sourceforge.cilib.problem.coevolution;

import net.sourceforge.cilib.coevolution.score.EntityScore;
import net.sourceforge.cilib.coevolution.score.EntityScoreboard;
import net.sourceforge.cilib.entity.EntityType;
import net.sourceforge.cilib.games.game.Game;
import net.sourceforge.cilib.problem.Fitness;
import net.sourceforge.cilib.problem.OptimisationProblemAdapter;
import net.sourceforge.cilib.type.DomainRegistry;
import net.sourceforge.cilib.type.types.Blackboard;
import net.sourceforge.cilib.type.types.Type;

/**
 * @author leo
 * Optimize either a single player game or agains hand coded opponents
 */
public class GameLearningOptimizationProblem extends
		PerformanceEvaluationOptimizationProblem {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5779885760175795987L;
	protected Game game;

	/**
	 * 
	 */
	public GameLearningOptimizationProblem() {

	}

	/**
	 * @param copy
	 */
	public GameLearningOptimizationProblem(
			GameLearningOptimizationProblem copy) {
		super(copy);
		game = copy.game.getClone();
	}

	/* (non-Javadoc)
	 * @see net.sourceforge.cilib.problem.OptimisationProblemAdapter#getClone()
	 */
	@Override
	public OptimisationProblemAdapter getClone() {
		return new GameLearningOptimizationProblem(this);
	}

	/* (non-Javadoc)
	 * @see net.sourceforge.cilib.problem.OptimisationProblem#getBehaviouralDomain()
	 */
	@Override
	public DomainRegistry getBehaviouralDomain() {
		// TODO Auto-generated method stub
		return game.getDomainForPlayer(1); //player one is the player being optimized
	}

	/* (non-Javadoc)
	 * @see net.sourceforge.cilib.problem.OptimisationProblem#getDomain()
	 */
	@Override
	public DomainRegistry getDomain() {
		// TODO Auto-generated method stub
		return game.getDomainForPlayer(1); //player one is the player being optimized
	}
	/**
	 * This method should be called after all the players have been initialized for this game
	 * the required amount of games are played and the scores are stored
	 * @param currentPlayerID the id of the player being optimized
	 * @param currentScore the score's
	 */
	public void playGame(int currentPlayerID, EntityScore currentScore){	
		for(int i = 0; i < amountEvaluations; ++i){
			game.playGame();
			game.setEntityScore(currentPlayerID, currentScore);	
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Fitness calculateFitness(Type solution) {
		//one fixed set of competitors
		if(!(solution instanceof Blackboard))
			throw new RuntimeException("Please use the PropertyBasedFitnessCalculator with the entity optimizing the game");
		
		//for each game, add the score {win/lose/tie : score} to a scoreboard. then use the scoring strategy to assign a score to the player
		EntityScore score = new EntityScore(1,1); //in the case of optimizing against static opponents the opponent id is not so important, or is it?!?
		// initialize the first player, which is the one being optimized, to the contents of solution
		@SuppressWarnings("unchecked")
		Blackboard<Enum<?>, Type> blackboard = (Blackboard<Enum<?>, Type>) solution;
		game.initializeAgent(1, blackboard.get(EntityType.CANDIDATE_SOLUTION));
		playGame(1, score);
		EntityScoreboard board = new EntityScoreboard();//(EntityScoreboard)((Blackboard<Enum<?>, Type>)solution).get(EntityType.Coevolution.BOARD);
		board.mergeEntityScore(score);
		//need to store the entityscoreboard and current competition round in blackboard. should I put them in the blackboard here, or somewhere else? 
		return fitnessCalculation.calculateFitnessFromScoreBoard(board, 1);
	}

	public void setGame(Game game) {
		this.game = game;
	}

}