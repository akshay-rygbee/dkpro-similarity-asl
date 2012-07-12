/*******************************************************************************
 * Copyright 2012
 * Ubiquitous Knowledge Processing (UKP) Lab
 * Technische Universität Darmstadt
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package de.tudarmstadt.ukp.similarity.algorithms.lsr.path;

import de.tudarmstadt.ukp.dkpro.lexsemresource.Entity;
import de.tudarmstadt.ukp.dkpro.lexsemresource.LexicalSemanticResource;
import de.tudarmstadt.ukp.dkpro.lexsemresource.exception.LexicalSemanticResourceException;
import de.tudarmstadt.ukp.similarity.algorithms.api.SimilarityException;

/**
 * Implements the distance measure by Rada et al. (1989).
 * The distance between two terms a and b is defined as the minimum number of edges separating
 * the nodes that represent a and b in the semantic net.
 * 
 * @author zesch
 *
 */
public class PathLengthComparator
	extends PathBasedComparator
{
	protected boolean distanceMeasure = true;
	protected final double NOT_RELATED = Double.POSITIVE_INFINITY;
	
    private double diameter = Double.MAX_VALUE;
    private boolean convertToRelatedness = false;
    
	public PathLengthComparator(LexicalSemanticResource lexSemResource)
		throws LexicalSemanticResourceException
	{
		super(lexSemResource);
    }

	public PathLengthComparator(LexicalSemanticResource lexSemResource,	boolean convertToRelatedness)
		throws LexicalSemanticResourceException
	{
		super(lexSemResource);
		this.convertToRelatedness = convertToRelatedness;
        
        if (convertToRelatedness) {
            this.diameter = getEntityGraph().getDiameter();
        }
    }

	@Override
    public double getSimilarity(Entity e1, Entity e2)
		throws SimilarityException, LexicalSemanticResourceException
	{
        // entity overrides the equals method with a usable variant
        if (e1.equals(e2)) {
            return 0.0;
        }
        return getShortestPathLength(e1, e2);
    }

//    private double getRelatedness(String token1, String token2) throws LexicalSemanticResourceException {
//        double relatedness;
//        // try to use the getShortestPathLength method
//        // catch the exception if the method is not implemented and use the (slower) generic computation
//        try {
//            relatedness = lexSemResource.getShortestPathLength(token1, token2);
//            return relatedness;
//        }
//        catch (UnsupportedOperationException e) {
//            logger.info("Falling back to generic implementation of shortest path (might be slow).");
//            relatedness = computeShortestPathLength(token1, token2);
//            return relatedness;
//        }
//    }
    
//    private double computeShortestPathLength(String token1, String token2) {
//        //TODO how to handle lower case/upper case problem?
//        token1 = token1.toLowerCase();
//        token2 = token2.toLowerCase();
//        
//        
//        if (this.entityGraph.containsVertex(token1) && this.entityGraph.containsVertex(token2)) {
//            // return path length of zero, if the tokens are equal
//            if (token1.equals(token2)) {
//                return 0;
//            }
//            
//            // The directed graph is treated as a undirected graph.
//            UndirectedGraph<String, DefaultEdge> undirectedGraph = new AsUndirectedGraph<String, DefaultEdge>(this.entityGraph.getGraph());
//
//            // get the path from token1 to token2
//            DijkstraShortestPath<String, DefaultEdge> dijkstra = new DijkstraShortestPath<String, DefaultEdge>(undirectedGraph, token1, token2, Double.POSITIVE_INFINITY);
//            List<DefaultEdge> edgeList = dijkstra.getPathEdgeList();
//            
//            if (edgeList == null) {
//                return NO_PATH;
//            }
//            else {
//                return edgeList.size();
//            }
//        }
//        // if the given tokens are not in the graph, return NOT_FOUND
//        else {
//            return NOT_FOUND;
//        }
//    }
}
