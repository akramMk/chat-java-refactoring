package fr.univ_lyon1.info.m1.elizagpt.model.response;

import fr.univ_lyon1.info.m1.elizagpt.interfaces.Response;

/**
 * Factory class for creating a response chain for the ElizaGPT conversational model.
 */
public final class ResponseFactory {

    /*
     *  Private constructor to prevent instantiation
     */
    private ResponseFactory() {
        // Private constructor to prevent instantiation
    }
    /**
     * Creates and returns a response chain with predefined response handlers.
     *
     * @return The starting point of the response chain.
     */
    public static Response createResponseChain() {
        // Initialize response handlers
        Response myNameResponse = new MyNameResponse();
        Response nameResponse = new NameResponse();
        Response iamResponse = new IamResponse();
        Response questionResponse = new QuestionResponse();
        Response oceanResponse = new OceanResponse();
        Response goodbyeResponse = new GoodbyeResponse();
        Response whoIsHandler = new WhoIsResponse();
        Response defaultResponse = new DefaultResponse();

        // Set up the chain of responsibility
        myNameResponse.setNextHandler(nameResponse);
        nameResponse.setNextHandler(oceanResponse);
        oceanResponse.setNextHandler(whoIsHandler);
        whoIsHandler.setNextHandler(goodbyeResponse);
        goodbyeResponse.setNextHandler(questionResponse);
        questionResponse.setNextHandler(iamResponse);
        iamResponse.setNextHandler(defaultResponse);

        // Return the starting point of the response chain
        return myNameResponse;
    }
}
