/*
 * Selects word from database based on the word length
 * Checks with UsedWords to make sure the word has not been used before
 * Adds letters based on difficulty
 * If the answer is wrong, check which letters are in the right spot
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.stream.Stream;

public class SelectWord {
    /*
     * the output when checking which letters are in the right spot
     * ex: answer is bingo, guess is bnigo, output is b__go
     */
    private static String output;

    //returns output
    public static String getOutput(){
        return output;
    }

    //selects a random five letter word
    public static String SelectFiveLetterWord() throws IOException{
        String answer = "";
        int n = (int)Math.floor(Math.random() * (686 - 0 + 1)); //selects random line from five letter word database
        try (Stream<String> lines = Files.lines(Paths.get("Database-Related\\fiveLetterWords.txt"))){
            answer = lines.skip(n).findFirst().get(); //returns the word from line n
            if(UsedWords.checkUsed(answer) == true){
                return SelectFiveLetterWord();
            }
            return answer;
        }
        catch(IOException e){
            System.out.println(e);
        }
        return "nv"; //not valid
    }

    //selects a random eight letter word
    public static String SelectEightLetterWord() throws IOException{
        String answer = "";
        int n = (int)Math.floor(Math.random() * (504 - 0 + 1)); //selects random line from five letter word database
        try (Stream<String> lines = Files.lines(Paths.get("Database-Related\\eightLetterWords.txt"))){
            answer = lines.skip(n).findFirst().get(); //returns the word from line n
            if(UsedWords.checkUsed(answer) == true){
                return SelectEightLetterWord();
            }
            return answer;
        }
        catch(IOException e){
            System.out.println(e);
        }
        return "nv"; //not valid
    }

    //selects a random twelve letter word
    public static String SelectTwelveLetterWord() throws IOException{
        String answer = "";
        int n = (int)Math.floor(Math.random() * (71 - 0 + 1)); //selects random line from five letter word database
        try (Stream<String> lines = Files.lines(Paths.get("Database-Related\\twelveLetterWords.txt"))){
            answer = lines.skip(n).findFirst().get(); //returns the word from line n
            if(UsedWords.checkUsed(answer) == true){
                return SelectTwelveLetterWord();
            }
            return answer;
        }
        catch(IOException e){
            System.out.println(e);
        }
        return "nv"; //not valid
    }

    /* 
    //adds letters based on difficulty
    public static String addLetters(int difficulty, String answer){
        String added = "";
        String addedAnswer = answer;
        if(answer.length()==5 && difficulty==2){
            Random r = new Random();
            char c = (char)(r.nextInt(26) + 'a');
            added += c;
        }
        if (added.equals("r") || added.equals("d") || added.equals("y") || added.equals("s")){
                addLetters(difficulty, answer);
        }
        addedAnswer += added.toLowerCase();
        return addedAnswer;
    }
    */

    /*
     * Adds 1 letter for difficulty 2
     * Adds 2 letters for difficulty 3
     */
    public static String addLetters(){
        int difficulty = Word.getDifficulty();
        String added = "";
        String addedAnswer = Word.getAnswer();
        int numAdded = 0;
        Random r;
        char c;
        if(difficulty==2){
            numAdded = 1;
        }
        else if(difficulty==3){
            numAdded = 2;
        }
        for(int i = 1; i <= numAdded; i++){
            r = new Random();
            c = (char)(r.nextInt(26) + 'a');
            if(c == 'r' || c == 'd' || c == 'y' || c == 's'){
                addLetters();
            }
            added += c;
        }
        addedAnswer += added.toLowerCase();
        return addedAnswer;
    }

    //if guess is wrong, checks which letters are in the right spot
    public static String checkCorrect(String response, String answer){
        output = "";
        for(int i = 0; i < answer.length(); i++){
            if(response.charAt(i)==(answer.charAt(i))){
                output += response.substring(i, i+1);
            }
            else{
                output += "_";
            }
        }
        return output;
    }

}
