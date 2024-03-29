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
package dkpro.similarity.uima.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.resource.ResourceInitializationException;

import de.tudarmstadt.ukp.dkpro.core.api.resources.ResourceUtils;
import dkpro.similarity.uima.io.util.CombinationPair;

/**
 * Reader for the datasets used in the SEMEVAL 2014 Task 1: SICK-er
 * 
 * <a href="http://alt.qcri.org/semeval2014/task1/">(website)</a>
 */
public class SickCorpusReader
	extends CombinationReader
{
	
	public static final String PARAM_INPUT_FILE = "InputFile";
	@ConfigurationParameter(name=PARAM_INPUT_FILE, mandatory=true)
	private String inputFile;
	
	
	protected String getInputFile()
    {
        return inputFile;
    }

    @Override
	public List<CombinationPair> getAlignedPairs()
		throws ResourceInitializationException
	{
		List<CombinationPair> pairs = new ArrayList<CombinationPair>();
			
		List<String> lines = new ArrayList<String>();
		InputStream is = null;
		BufferedReader br = null;
		URL url;
		try {
			url = ResourceUtils.resolveLocation(inputFile, this, this.getUimaContext());
			is = url.openStream();
			br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String strLine;
			while ((strLine = br.readLine()) != null)   {
				lines.add(strLine);
			}
		}
		catch (IOException e) {
			throw new ResourceInitializationException(e);
		}
		finally {
			IOUtils.closeQuietly(br);
			IOUtils.closeQuietly(is);
		}
		
		for (int i = 0; i < lines.size(); i++)
		{
			String line = lines.get(i);
			String[] linesplit = line.split("\t");
			
//			pair_ID  sentence_A  sentence_B  relatedness_score   entailment_judgment
			String text1 = linesplit[1];
			String text2 = linesplit[2];
				
			CombinationPair pair = new CombinationPair(url.toString());
			pair.setID1(linesplit[0] + "_1");
			pair.setID2(linesplit[0] + "_2");
			pair.setText1(text1);
			pair.setText2(text2);
			
			pairs.add(pair);
		}

		return pairs;
	}
}
