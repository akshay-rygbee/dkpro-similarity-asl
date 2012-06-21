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
package de.tudarmstadt.ukp.similarity.algorithms.api;

/**
 * Similarity measure on terms (lexical units).
 */
public interface TermSimilarityMeasure
    extends SimilarityMeasure
{
	/**
	 * Returned as similarity if either of two compared terms could not be found.
	 *
	 * @see #getSimilarity(String, String)
	 */
	static final double NOT_FOUND = -1.0;

	// TODO what is this actually used for?
	void beginMassOperation();
	void endMassOperation();

	double getSimilarity(String string1, String string2)
		throws SimilarityException;
}