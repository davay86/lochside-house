Lochside House Hotel – read me


Design -

The Spring boot application runs on a H2 database with pre-seeded data. Data retrieval is carried out using the Spring Repository interface, which enhances Hibernate functionality. 

The app is then exposed by rest methods in the BookingController, which makes use of the repositories to retrieve and update data.


Test -

The application is tested throughout using JUnit and Mockito where appropriate. Integration tests are also in place to test full function of the application. This is done using SpringBootTest annotation running the rest methods from the given TestRestTemplate class.


Running Application -

To run the application from the command line you have to navigate to the root of the project and run the following command.

	mvn spring-boot:run

Alternatively the app can be accessed on AWS through the following URLs.

	http://lochside-hotel.us-west-2.elasticbeanstalk.com/roomBookings?room=[room id]
    	http://lochside-hotel.us-west-2.elasticbeanstalk.com/customerBookings?customer=[customer id]
	http://lochside-hotel.us-west-2.elasticbeanstalk.com/availability?room=[room id]&from=[‘yyyy-mm-dd’]&to=[‘yyyy-mm-dd’]
	
	NB The /createBooking cannot be accessed through a url but can be tested through Postman passing in the following params
		customer : [cust_id]
		room : [room_id]
		fromDate : ['yyyy-mm-dd']
		toDate : ['yyyy-mm-dd']

Pre-seeded Data -

INSERT INTO CUSTOMERS VALUES (101,'David');
INSERT INTO CUSTOMERS VALUES (102,'Ashley');

INSERT INTO ROOMS VALUES (1001,100,'Single');
INSERT INTO ROOMS VALUES (1002,100,'Single');
INSERT INTO ROOMS VALUES (1003,200,'Double');
INSERT INTO ROOMS VALUES (1004,200,'Double');
INSERT INTO ROOMS VALUES (1005,200,'Double');

INSERT INTO BOOKINGS VALUES (1,101,1002,TO_DATE('17/12/2017', 'DD/MM/YYYY'),TO_DATE('19/12/2017', 'DD/MM/YYYY'));
INSERT INTO BOOKINGS VALUES (2,102,1002,TO_DATE('21/12/2017', 'DD/MM/YYYY'),TO_DATE('26/12/2017', 'DD/MM/YYYY'));
INSERT INTO BOOKINGS VALUES (3,102,1001,TO_DATE('10/10/2017', 'DD/MM/YYYY'),TO_DATE('19/10/2017', 'DD/MM/YYYY'));
INSERT INTO BOOKINGS VALUES (4,101,1005,TO_DATE('12/09/2017', 'DD/MM/YYYY'),TO_DATE('04/09/2017', 'DD/MM/YYYY'));
