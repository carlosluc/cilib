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
package net.sourceforge.cilib.pso.velocityprovider;

import java.util.HashMap;
import junit.framework.Assert;
import net.sourceforge.cilib.entity.EntityTest;
import net.sourceforge.cilib.entity.EntityType;
import net.sourceforge.cilib.entity.Particle;
import net.sourceforge.cilib.math.random.ProbabilityDistributionFuction;
import net.sourceforge.cilib.math.random.UniformDistribution;
import net.sourceforge.cilib.pso.guideprovider.NBestGuideProvider;
import net.sourceforge.cilib.pso.guideprovider.PBestGuideProvider;
import net.sourceforge.cilib.pso.particle.ParameterizedParticle;
import net.sourceforge.cilib.type.types.container.Vector;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kristina
 */
public class BareBonesVelocityProviderTest {
    
    public BareBonesVelocityProviderTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }


    /**
     * Test of get method, of class BareBonesVelocityProvider.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        ParameterizedParticle particle = new ParameterizedParticle();
        particle.setCandidateSolution(Vector.of(0.2,0.2,0.2));
        particle.setLocalGuideProvider(new PBestGuideProvider());
        particle.setGlobalGuideProvider(new NBestGuideProvider());
        
        particle.getProperties().put(EntityType.Particle.VELOCITY, Vector.of(0.1,0.1,0.1));
        particle.getProperties().put(EntityType.Particle.BEST_POSITION, Vector.of(0.1,0.1,0.1));
        ParameterizedParticle nBest = particle.getClone();
        nBest.getProperties().put(EntityType.Particle.BEST_POSITION, Vector.of(0.9,0.9,0.9));
        particle.setNeighbourhoodBest(nBest);
        
        BareBonesVelocityProvider instance = new BareBonesVelocityProvider();
        instance.setRandomDistribution(new UniformDistribution());
        Vector result = instance.get(particle);
        
        Assert.assertTrue(result.get(0).doubleValue() < 0.8 && result.get(0).doubleValue() > 0.5);
        Assert.assertTrue(result.get(1).doubleValue() < 0.8 && result.get(1).doubleValue() > 0.5);
        Assert.assertTrue(result.get(2).doubleValue() < 0.8 && result.get(2).doubleValue() > 0.5);
        
    }

    /**
     * Test of getRandomDistribution method, of class BareBonesVelocityProvider.
     */
    @Test
    public void testGetRandomDistribution() {
        System.out.println("getRandomDistribution");
        BareBonesVelocityProvider instance = new BareBonesVelocityProvider();
        instance.setRandomDistribution(new UniformDistribution());
        
        Assert.assertEquals(instance.getRandomDistribution().getClass(), UniformDistribution.class);
    }

    /**
     * Test of setRandomDistribution method, of class BareBonesVelocityProvider.
     */
    @Test
    public void testSetRandomDistribution() {
        System.out.println("setRandomDistribution");
        BareBonesVelocityProvider instance = new BareBonesVelocityProvider();
        instance.setRandomDistribution(new UniformDistribution());
        
        Assert.assertEquals(instance.getRandomDistribution().getClass(), UniformDistribution.class);
    }


    /**
     * Test of getControlParameterVelocity method, of class BareBonesVelocityProvider.
     */
    @Test
    public void testGetControlParameterVelocity() {
        System.out.println("getControlParameterVelocity");
        ParameterizedParticle particle = new ParameterizedParticle();
        particle.setCandidateSolution(Vector.of(0.2,0.2,0.2));
        particle.setLocalGuideProvider(new PBestGuideProvider());
        particle.setGlobalGuideProvider(new NBestGuideProvider());
        
        particle.getInertia().setParameter(0.2);
        particle.getInertia().setBestValue(0.1);
        particle.getSocialAcceleration().setParameter(0.2);
        particle.getSocialAcceleration().setBestValue(0.1);
        particle.getCognitiveAcceleration().setParameter(0.2);
        particle.getCognitiveAcceleration().setBestValue(0.1);
        particle.getVmax().setParameter(0.2);
        particle.getVmax().setBestValue(0.1);
        
        particle.getProperties().put(EntityType.Particle.VELOCITY, Vector.of(0.1,0.1,0.1));
        particle.getProperties().put(EntityType.Particle.BEST_POSITION, Vector.of(0.1,0.1,0.1));
        ParameterizedParticle nBest = new ParameterizedParticle();
        nBest.getInertia().setParameter(0.2);
        nBest.getInertia().setBestValue(0.9);
        nBest.getSocialAcceleration().setParameter(0.2);
        nBest.getSocialAcceleration().setBestValue(0.9);
        nBest.getCognitiveAcceleration().setParameter(0.2);
        nBest.getCognitiveAcceleration().setBestValue(0.9);
        nBest.getVmax().setParameter(0.2);
        nBest.getVmax().setBestValue(0.9);
        
        particle.setNeighbourhoodBest(nBest);
        
        BareBonesVelocityProvider instance = new BareBonesVelocityProvider();
        instance.setRandomDistribution(new UniformDistribution());
        HashMap<String, Double> result = instance.getControlParameterVelocity(particle);
        
        Assert.assertTrue(result.get("InertiaVelocity") < 0.8 && result.get("InertiaVelocity").doubleValue() > 0.5);
        Assert.assertTrue(result.get("SocialAccelerationVelocity").doubleValue() < 0.8 && result.get("SocialAccelerationVelocity").doubleValue() > 0.5);
        Assert.assertTrue(result.get("CognitiveAccelerationVelocity").doubleValue() < 0.8 && result.get("CognitiveAccelerationVelocity").doubleValue() > 0.5);
        Assert.assertTrue(result.get("VmaxVelocity").doubleValue() < 0.8 && result.get("VmaxVelocity").doubleValue() > 0.5);
    }
}