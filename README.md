# Building and running instructions

## Initial setup
Clone this repository onto your local machine. To do this, click on the green "Code" icon at the top right and copy the link shown. Then open your terminal (or command prompt if on windows) and use the command

### `git clone`

## Backend

The backend is built using Spring boot and Java. The bse file for the this is the "chainalysis" folder at the top of the file structure. Open this folder in IntelliJ (or the IDE of your choice) and run the application. On IntelliJ, this will be the green arrow button on the top toolbar. After the application has started, the backend should be up and running, and you can see the results of the two endpoints at [http://localhost:8080/prices/coinbase](http://localhost:8080/prices/coinbase) and [http://localhost:8080/prices/coinbase](http://localhost:8080/prices/coinbase). Leave this running, and now we can move to the front end.

Note: I understand that this is not the ideal instruction for running this. I did want to provide a way to run the backend from the command line, but after having difficulties, I ultimately decided that this would be sufficient because of the differenes in my configurations with gradle/java and the configuration on your local machine. I'm sorry if this causes problems, but I will include a live version to showcase the application.

## Front end

In your terminal navigate back to the base folder in the repository. You should see the above "chainalysis" folder at the top again, as well as other files such as "src", "package.json". Run the command

### `npm start`

This should open up a link to [http://localhost:3000](http://localhost:3000). After a second, you should see a webpage showing three cards: two for the buy/sell information and one for recommendations.

# Question Answers
1. Yes, there were some sub-optimal choices I made due to limited time. I know that I did submit this with plenty of time before the ten day limit, but I do have midterms currently and so I wanted to get this done as soon as possible. I also have a soft offer deadline at the end of October, and so I did not want to ask for extra time. One sub-optimal choice I made was not giving a button for the user to click in order to update the information and recommendations. The user must refresh the page in order to get the latest information. I also felt that I could have cleaned up some of the code in the front end and back end to improve the ability of the application to be built further if needed. For example, I could have probably combined the two endpoints if I included a parameter that took in which exchange to pull data from.
2. I don't think the spring boot application necessarily needs a service class, but I believe it is much better practice to keep the controller relatively simple, and do most of the heavy lifting in the service class. Also, I suppose I could have just printed out the data for the prices in a list like manner, but I decided to use cards and tables instead to improve readibility. 
3. To be completely transperent, I do not have much experience working in scalability. However, if I were to make a choice, I would change the backend so that the data is stored in a database, that is updated each time the external apis are called. These external apis would only be called if a certain amount of time has passed between a new user loading the page. If this time period has not been exceeded, we can simply pull the data from the database instead of performing an api call. 
4. I wish I could have made the front end look a little better, as it is definetely not particularly aesthetically pleasing. I would also like to give the user the ability to choose which exchanges they would like to see, and then perform an aggregate recommendation based on the user's choices.

Note: My live solution is also included in the base file structure. I would also just like to say that this was a really unique and fun assessment, so thank you for giving me the oppurtunity!
