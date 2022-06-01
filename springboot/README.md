Table of contents
* [Introduction](#Introduction)
* [Quick Start](#Quick_Start)
* [Implementation](#Implementation)
* [Rest API Usage](#Rest_API_Usage)
* [Test](#Test)
* [Deployment](#Deployment)
* [Improvements](#Improvements)

# Introduction
This springboot application is the proof of concept for a new version of Jarvis Trading Application, which has the ability to source the stock market data from IEXCloud and update the application's database info. Using an external, independent postgres database to maintain a stateless property, this application allows the lookup/search for simplified/relevant stock market tickers, the creation (and deletion) of trader accounts, use of associated funds account, and the trading of stocks using those accounts. This application is built with a three-tier architecture, and uses Springboot as its base, alongside with running the Tomcat webservlet. Some of the application's API endpoints may be accessed using SwaggerUI.

# Quick Start
- Prequiresites: Docker, CentOS 7
- create a docker network: sudo docker network create trading-net
- Docker scripts:
	- /psql/Dockerfile: initiates psql database, initiate tables using .sql script
		- build image:
			cd ./springboot/psql</br>
			docker build -t trading-psql .<br>
			docker image ls -f reference=trading-psql<br>
	- /Dockerfile: compiles and builds app jar, create image
		- build image:
			cd ./springboot/<br>
			docker build -t trading-app . <br>
			docker image ls -f reference=trading-psql <br>
  - start containers:
  	docker run --name trading-psql-dev \ <br>
	-e POSTGRES_PASSWORD=password \ <br>
	-e POSTGRES_DB=jrvstrading \ <br>
	-e POSTGRES_USER=postgres \ <br>
	--network trading-net \ <br>
	-d -p 5432:5432 trading-psql<br>

	IEX_PUB_TOKEN="your_token" <br>
	docker run --name trading-app-dev \ <br>
	-e "PSQL_URL=jdbc:postgresql://trading-psql-dev:5432/jrvstrading" \ <br>
	-e "PSQL_USER=postgres" \ <br>
	-e "PSQL_PASSWORD=password" \ <br>
	-e "IEX_PUB_TOKEN=${IEX_PUB_TOKEN}" \ <br>
	--network trading-net \ <br>
	-p 8080:8080 -t trading-app<br>
- Try trading-app with SwaggerUI: localhost/8080
![Swagger UI Usage](src/SwaggerPreview.JPG)

# Implemenation
## Architecture
![Component Diagram](src/ComponentDiagram.png)
- Components Overview
  - Controller layer: Handles communication/relaying information towards interfaces/user endpoints, provides managed selective access to functionalities.
  - Service layer: Handles the business logic, processes and implementation of functions.
  - DAO layer: Storage and entities, representing real entities in programmatically understandable manner.
  - SpringBoot: Runs and manages webservlet/TomCat and IoC of application.
  - PSQL and IEX: PSQL serves as independent external storage, permitting the application proper to become stateless. IEX serves as an external data source, used to retrieve up-to-date business relevant data.

## REST API Usage
### Swagger
Swagger automatically describes the structure of the API so that machines can read them. In this case, swagger is useful for testing the API's endpoints and to simulate API usage.
### Quote Controller
- Quote controller concerns itself with quote/ticker datas. The controller is able to connect to IEXQuote to get live data and will cahce them in PSQL database as needed.
	- GET `/quote/iex/ticker/{ticker}`: get a specific Ticker data from IEX, and cache that ticker into PSQL.
	- GET `/quote/iexMarketData`: gather all cached ticker data from IEX, and save them in PSQL.
### Trader-Account Controller
- Trader-Account controller manages the trader and its corresponding accounts. This controller may conduct transactions such as making deposits and wihdrawals from the account, as well as making purchases. 
	- DELETE `/traderId/{traderId}`: Delete the trader ID- only if funds of account are 0 and no open positions.
	- POST `/trader/`: Create new trader with attached DTO. 
	- POST `/firstname/{firstname}/lastname/{lastname}/dob/{dob}/country/{country}/email/{email}`: Create trader with given parameters. 
	- PUT `/deposit/traderId/{traderId}/amount/{amount}`: Add funds into trader account by amount.
	- PUT `/withdraw/traderId/{traderId}/amount/{amount}`: Withdraw funds from trader account by amount.

# Test 
The application is tested with JUnit and integration tests on code level, while SwaggerUI is used to verify endpoints and program outputs. Code coverage class is 68% while methods coverage is 79%. The relatively low coverage is expected since the usage of interfaces and abstract classes together with the proof-of-concept nature of the application results in several unused or stub methods and redundant/inappropriate functions.

# Deployment
The application is saved on github and dockerizable with dockerfiles.
- PSQL Dockerfile initiates psql, then executes the .sql file in the sql_ddl folder to initiate the necessary tables. 
- Base Dockerfile compiles and creates a .jar of the application, which is next wrapped into an image.

# Improvements
- Inclusion of all components
- Remove redundant/stub functions/endpoints
- Implement an overview system/class for greater oversight/admin view
