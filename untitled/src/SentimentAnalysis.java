/*------------------------------------------------------------------------------------------------------------------------
Team Name:  PATH
Members: 	Hoa Tuong Minh Nguyen
		    Tuan-Anh Ho
		    Paul Valdez
		    Ali Fayed
Team Leader: Hoa Tuong Minh Nguyen

------------------------------------------------------------------------------------------------------------------------*/
/**
 * Use a language model, chatGPT, to help your team write a program that generates output a message
 * about the sentiment of the text string, whether it’s “Positive”, “Negative” or “Neutral”. .
 * @author  Team PATH
 * @version 1.0
 */
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;

import java.util.Properties;
import java.util.Scanner;

public class SentimentAnalysis {

    public static String analyzeSentiment(String text) {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");

        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        Annotation annotation = new Annotation(text);
        pipeline.annotate(annotation);

        String sentiment = "";
        for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
            sentiment = sentence.get(SentimentCoreAnnotations.SentimentClass.class);
        }

        return sentiment;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a text string: ");
        String text = scanner.nextLine();

        String sentiment = analyzeSentiment(text);
        System.out.println("The sentiment of the text is: " + sentiment);
    }
}