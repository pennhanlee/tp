package seedu.bookmark.logic.algorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_BOOKMARK;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.bookmark.testutil.Assert.assertThrows;
import static seedu.bookmark.testutil.TypicalWords.HARRY;
import static seedu.bookmark.testutil.TypicalWords.MISSPELT_HARRY;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.bookmark.model.WordBank;
import seedu.bookmark.model.wordstore.Word;
import seedu.bookmark.testutil.TypicalWords;

public class SuggestionAlgorithmTest {
    private WordBank wordBank = TypicalWords.getTypicalWordBank();
    private SuggestionAlgorithm suggestionAlgorithm = new SuggestionAlgorithm(wordBank);

    //------------------------- instantiate Object ---------------------//

    @Test
    public void create_suggestionAlgo_nullPointerException() {
        assertThrows(NullPointerException.class, () -> new SuggestionAlgorithm(null));
    }

    @Test
    public void create_suggestionAlgo_correctValue() {
        WordBank wb = TypicalWords.getTypicalWordBank();
        SuggestionAlgorithm sa = new SuggestionAlgorithm(wb);

        assertEquals(wb, sa.getWordBank());

    }

    //------------------------- findSuggestion() ----------------------//

    @Test
    public void findSuggestion_sourceWordnull() {
        assertThrows(NullPointerException.class, () -> suggestionAlgorithm
                                                            .findSuggestion(null, PREFIX_NAME));
    }

    @Test
    public void findSuggestion_prefixNull() {
        assertThrows(NullPointerException.class, () -> suggestionAlgorithm
                                                            .findSuggestion(HARRY, null));
    }



    //default wordstore is Name as it has a larger collection (do not want to throw exception for this feature)
    @Test
    public void findSuggestion_prefixInvalid() {
        ArrayList<Word> suggestions = suggestionAlgorithm.findSuggestion(MISSPELT_HARRY, PREFIX_BOOKMARK);

    }

    @Test
    public void findSuggestion_correctParameters() {
        ArrayList<Word> suggestions = suggestionAlgorithm.findSuggestion("Haary", PREFIX_NAME);
        int distance = suggestionAlgorithm.calculateDistance("Haary", "Harry"); //distance = 1
        Word harry = new Word("Harry", distance);
        assertTrue(suggestions.contains(harry));
    }

    @Test
    public void findSuggestion_distanceLimit() {
        ArrayList<Word> suggestions = suggestionAlgorithm.findSuggestion("Haaaaary", PREFIX_NAME);
        int distance = suggestionAlgorithm.calculateDistance("Haaaaary", "Harry"); //distance = 4
        Word harry = new Word("Harry", distance);
        assertFalse(suggestions.contains(harry));
    }

    //------------------------ calculateDistance ---------------------//

    @Test
    public void calculateDistance_sourceWordNull() {
        assertThrows(NullPointerException.class, () -> suggestionAlgorithm
                                                            .calculateDistance(HARRY, null));
    }

    @Test
    public void calculateDistance_targetWordNull() {
        assertThrows(NullPointerException.class, () -> suggestionAlgorithm
                                                            .calculateDistance(null, HARRY));
    }

    @Test
    public void calculateDistance_correctInput() {
        int substitutionDistance = suggestionAlgorithm
                                    .calculateDistance("Hbrry", "Harry"); //substitution (cost 1)
        assertEquals(1, substitutionDistance);

        int positionSwapDistance = suggestionAlgorithm
                                    .calculateDistance("Hrary", "Harry"); //adjacent swapping (cost 1)
        assertEquals(1, positionSwapDistance);

        int missingLetterDistance = suggestionAlgorithm
                                    .calculateDistance("Hrry", "Harry"); //insertion (cost 1)
        assertEquals(1, missingLetterDistance);

        int extraLetterDistance = suggestionAlgorithm
                                    .calculateDistance("Haarry", "Harry"); //deletion (cost 1)
        assertEquals(1, extraLetterDistance);

        int substitutionDistanceBig = suggestionAlgorithm
                                        .calculateDistance("Hbbby", "Harry"); // (cost 3)
        assertEquals(3, substitutionDistanceBig);

        int positionSwapDistanceBig = suggestionAlgorithm
                .calculateDistance("Hyrra", "Harry"); //adjacent swapping (cost 2) (becomes insert&delete)
        assertEquals(2, positionSwapDistanceBig);

        int missingLetterDistanceBig = suggestionAlgorithm
                .calculateDistance("Hy", "Harry"); //insertion (cost 3)
        assertEquals(3, missingLetterDistanceBig);

        int extraLetterDistanceBig = suggestionAlgorithm
                .calculateDistance("Haaaarry", "Harry"); //deletion (cost 3)
        assertEquals(3, extraLetterDistanceBig);

    }

}
