package words.driver;

import words.result.ResultI;
import words.result.ResultKmostFreq;
import words.util.ClearResults;
import words.util.ComponentI;
import words.util.FileProcessor;
import words.util.FileProcessorI;
import words.util.MyArrayList;
import words.util.MyLogger;
import words.visitor.BritToAmericanAnalysis;
import words.visitor.KmostAnalysis;
import words.visitor.VisitorI;

/**
 * @author placeholder
 *
 */
public class Driver {
	private static void startAnalysis(FileProcessorI file, VisitorI... visitor) {
		ComponentI myArray = new MyArrayList.Builder().fileProcessor(file).build();

		for (VisitorI visitors : visitor) {
			myArray.accept(visitors);
		}

	}

	private static void saveResults(ResultI... finalRes) {
		for (ResultI results : finalRes) {
			results.writeToFile();
		}
	}

	public static void main(String[] args) throws Exception {

		/*
		 * As the build.xml specifies the arguments as argX, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */

		String inputFileSentences = "", inputFileEnglishAmerican = "", opKmostFrequentWords = "", outputFile = "",
				defaultString = "";
		int KmostFrequentWords = 0;
		
		if (args.length != 6 || args[0].equals("${arg0}") ||
				args[1].equals("${arg1}") || args[2].equals("${arg2}")
				|| args[3].equals("${arg3}") || args[4].equals("${arg4}") ||
				args[5].equals("${arg5}")) {

			System.err.println("Error: Incorrect number of arguments. Program accepts 6 argumnets.");
			System.exit(0);

			}
			// ClearResults clear = new ClearResults();
			
			inputFileSentences = args[0];
			inputFileEnglishAmerican = args[1];
			KmostFrequentWords = Integer.parseInt(args[2]);
			opKmostFrequentWords = args[3];
			outputFile = args[4];
			//defaultString = args[5];
			int debugLevel = Integer.parseInt(args[5]);
			MyLogger.setDebugValue(debugLevel);
			// FileProcessor instance for reading sentences from the input file
			FileProcessorI file = new FileProcessor(inputFileSentences);

			ResultI topKres = new ResultKmostFreq(opKmostFrequentWords);

			// print res

			VisitorI kMostAnalysis = new KmostAnalysis(KmostFrequentWords, topKres);

			ResultI BritRes = new ResultKmostFreq(outputFile);

			VisitorI BritResAnalyzer = new BritToAmericanAnalysis(inputFileSentences, inputFileEnglishAmerican, BritRes,
					outputFile);

			startAnalysis(file, kMostAnalysis, BritResAnalyzer);
			// cleanReasults(opKmostFrequentWords);
			// printResults(res);

			saveResults(topKres, BritRes);
			ClearResults.clearResults(opKmostFrequentWords, KmostFrequentWords);

		}
	}

