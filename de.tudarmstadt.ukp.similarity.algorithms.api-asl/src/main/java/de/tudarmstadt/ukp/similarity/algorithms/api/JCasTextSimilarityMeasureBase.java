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

import java.util.Collection;

import org.apache.commons.lang.NotImplementedException;

public abstract class JCasTextSimilarityMeasureBase
	extends TextSimilarityMeasureBase
	implements JCasTextSimilarityMeasure
{
	@Override
	public double getSimilarity(Collection<String> stringList1,
			Collection<String> stringList2)
		throws SimilarityException
	{
		throw new SimilarityException(new NotImplementedException());
	}

	@Override
	public double getSimilarity(String string1, String string2)
		throws SimilarityException
	{
		throw new SimilarityException(new NotImplementedException());
	}
}
