# Google maps search
Project made for school in which you there is a searchbar with autocomplete for countries, and then there will be a google map with the capital of the country you searched for.

## Implementation
The list of the countries is loaded to a CrudRepository from https://restcountries.eu/rest/v2/all, I added a circuit breaker, so if the endpoint is unavailable it will pull the countries from a file in the project, and if it also is unavailable it'll pull the data from a yml configuration file.  
I used java with spring to create an endpoint for the searchbar in UI, and the frontend is done mostly in angularJs.

## Technologies
backend: java, spring, hystrix  
frontend: js, jquery, angularJs, google maps
