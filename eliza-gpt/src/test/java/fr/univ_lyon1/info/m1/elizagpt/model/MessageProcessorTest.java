package fr.univ_lyon1.info.m1.elizagpt.model;

import org.junit.jupiter.api.Test;
import fr.univ_lyon1.info.m1.elizagpt.interfaces.Search;
import fr.univ_lyon1.info.m1.elizagpt.model.searchStrategy.RegexSearchStrategy;
import fr.univ_lyon1.info.m1.elizagpt.model.searchStrategy.SubstringSearchStrategy;
import fr.univ_lyon1.info.m1.elizagpt.model.searchStrategy.WordCompleteSearchStrategy;
import org.junit.jupiter.api.BeforeEach;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for the {@link MessageProcessor} class.
 */
public class MessageProcessorTest {
        private MessageProcessor messageProcessor;

        /**
         * Set up the test environment before each test method is executed.
         */
        @BeforeEach
        public void setUp() {
                messageProcessor = new MessageProcessor();
        }

        /**
         * Test case to verify the response.
         */
        @Test
        public void testNameResponse() {
                String userText = "Je m'appelle Ryadh";
                messageProcessor.usertext(userText);

                // Assuming the greeting message is added to the message list
                assertThat(messageProcessor.getMessages(), 
                hasItem(hasProperty("content", equalTo("Bonjour Ryadh."))));
        }

        /**
         * Test case to verify the response.
         */
        @Test
        public void testMyNameResponse() {
                String userText = "Quel est mon nom ?";
                messageProcessor.usertext(userText);

                // Assuming the greeting message is added to the message list
                assertThat(messageProcessor.getMessages(),
                hasItem(hasProperty("content", equalTo("Je ne connais pas votre nom."))));
                userText = "Je m'appelle Ryadh";
                messageProcessor.usertext(userText);
                userText = "Quel est mon nom ?";
                messageProcessor.usertext(userText);

                // Assuming the greeting message is added to the message list
                assertThat(messageProcessor.getMessages(),
                hasItem(hasProperty("content", equalTo("Votre nom est Ryadh."))));
        }

        /**
         * Test case to verify the response.
         */
        @Test
        public void testOceanResponse() {
                String userText = "Quel est le plus grand océan du monde ?";
                messageProcessor.usertext(userText);

                // Assuming the greeting message is added to the message list
                assertThat(messageProcessor.getMessages(), hasItem(hasProperty("content",
                equalTo("Le plus grand océan du monde est l'océan Pacifique."))));
        }

        /**
         * Test case to verify the response.
         */
        @Test
        public void testIamResponse() {
                String userText = "Je mange";
                messageProcessor.usertext(userText);
                String[] expectedTexts = {"Pourquoi dites-vous que vous mangez ?",
                "Pourquoi pensez-vous que vous mangez ?", "Êtes-vous sûr que vous mangez ?" };
                // Assuming the greeting message is added to the message list
                assertThat(messageProcessor.getMessages(), hasItem(hasProperty("content",
                anyOf(is(expectedTexts[0]), is(expectedTexts[1]), is(expectedTexts[2])))));
        }

        /**
         * Test case to verify the response.
         */
        @Test
        public void testQuestionResponse() {
                String userText = "Comment as-tu fait ?";
                messageProcessor.usertext(userText);
                String[] expectedTexts = {"Je vous renvoie la question.", 
                "Ici, c'est moi qui pose les questions." };
                // Assuming the greeting message is added to the message list
                assertThat(messageProcessor.getMessages(),
                hasItem(hasProperty("content", anyOf(is(expectedTexts[0]), is(expectedTexts[1])))));
        }

        /**
         * Test case to verify the response.
         */
        @Test
        public void testWhoIsResponse() {
                String userText = "Qui est le plus beau ?";
                messageProcessor.usertext(userText);

                // Assuming the greeting message is added to the message list
                assertThat(messageProcessor.getMessages(), hasItem(hasProperty("content",
                equalTo("Le plus beau est bien sûr votre enseignant de MIF01 !"))));
        }

        /**
         * Test case to verify the response.
         */
        @Test
        public void testGoodbyeResponse() {
                String[] expectedTexts = {"Oh non, c'est trop triste de se quitter !",
                 "Au revoir Ryadh.", "Au revoir." };
                String userText = "Au revoir.";
                messageProcessor.usertext(userText);

                // Assuming the greeting message is added to the message list
                assertThat(messageProcessor.getMessages(), 
                hasItem(hasProperty("content",
                anyOf(is(expectedTexts[0]), is(expectedTexts[1]), is(expectedTexts[2])))));
                userText = "Je m'appelle Ryadh";
                messageProcessor.usertext(userText);
                userText = "Au revoir.";
                messageProcessor.usertext(userText);
                // Assuming the greeting message is added to the message list
                assertThat(messageProcessor.getMessages(), 
                hasItem(hasProperty("content",
                anyOf(is(expectedTexts[0]), is(expectedTexts[1]), is(expectedTexts[2])))));
        }

        /**
         * Test case to verify the response.
         */
        @Test
        public void testDefaultResponse() {
                String[] expectedTexts = {
                                "Il fait beau aujourd'hui, vous ne trouvez pas ?",
                                "Je ne comprends pas.",
                                "Hmmm, hmm ...",
                                "Qu'est-ce qui vous fait dire cela, Ryadh ?",
                                "Qu'est-ce qui vous fait dire cela ?"
                };
                String userText = "Fete des lumières.";
                messageProcessor.usertext(userText);

                assertThat(messageProcessor.getMessages(), 
                hasItem(hasProperty("content", anyOf(is(expectedTexts[0]),
                is(expectedTexts[1]), is(expectedTexts[2]),
                 is(expectedTexts[3]), is(expectedTexts[4])))));

                userText = "Je m'appelle Ryadh";
                messageProcessor.usertext(userText);
                userText = "Fete des lumières.";
                messageProcessor.usertext(userText);
                assertThat(messageProcessor.getMessages(), 
                hasItem(hasProperty("content", anyOf(is(expectedTexts[0]),
                is(expectedTexts[1]), is(expectedTexts[2]), 
                is(expectedTexts[3]), is(expectedTexts[3])))));
        }

        /**
         * Test case to validate the text search functionality of the MessageProcessor.
         * Different search strategies (WordComplete, Substring, Regex) are tested with
         * a sample search text.
         */
        @Test
        public void testTextsearch() {
                // Assuming there is a message containing "searchText"
                String searchText = "j";
                // DEFAULT MESSAGE IS ALREADY MADE "BONJOUR";
                // Perform search
                Search searchStrategy = WordCompleteSearchStrategy.getInstance();
                assertThat(messageProcessor.textsearch(searchText, searchStrategy), is(empty()));
                searchStrategy = SubstringSearchStrategy.getInstance();
                assertThat(messageProcessor.textsearch(searchText, searchStrategy),
                                hasItem(hasProperty("content", equalTo("Bonjour."))));
                searchStrategy = RegexSearchStrategy.getInstance();
                searchText = ".*j.*";
                assertThat(messageProcessor.textsearch(searchText, searchStrategy),
                                hasItem(hasProperty("content", equalTo("Bonjour."))));

        }

        /**
         * Test case to validate the deletion of a specific message by its ID.
         * The expected behavior is to remove the specified message from the list of
         * messages.
         */
        @Test
        public void testDeleteMessage() {
                // Add a message and get its ID
                messageProcessor.usertext("Message to be deleted");
                String messageId = messageProcessor.getMessages().get(1).getId();

                // Delete the message
                messageProcessor.deleteMessage(messageId);

                // Ensure the message is deleted
                assertThat(messageProcessor.getMessages(), 
                not(hasItem(hasProperty("id", 
                equalTo(messageId)))));
        }
}
