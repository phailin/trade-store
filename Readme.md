Design Approach choosen:

	1- Created a Spring Boot Rest API to store trade data to DB.
	2- In memory H2 database is used for this purpose.
	3- In real world solution, RDBMS should have been used and Caching should have been used to
	find the trade data, before querying the DB. Not used that for simplicity and as using in-memory DB.
	4- Logging framework should have been used, that I had not curently.
	5- Used a layered approach while implementing the solution. Controller -> Service -> DAO Layer (Repository).
	6- Used Hibernate Validator for input bean validation.
	7- Used @ControllerAdvice handle Exception at Application level.
	8- Used Spring MockMvc for testing Controller.
	9- Used Spring DataJpaTest for testing Repository.
	10- Should have written JUnit for Servie layer. But can not done due to time constraint. 
	11- All date are stored in LocalDate. Using time in UTC will be an improvement, when the app will be designed for multiple time zone.
	


Assumptions:

	1- TradeId and Version uniqely identify a trade.
	2- Created Date won't be passed as input. It will be assigned by the app.
	3- Expired value for trade won't be passed as input as well. As we are not accepting maturity date less than today's date,
	   we'll add the default value as "false". Later the scheduler will update it to "true" based on maturity date.
	   
	   
	