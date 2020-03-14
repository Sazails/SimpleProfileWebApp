# SimpleWebApp

The idea for this project is to create a simple profile based web application.

* USING:
    + Servlet: apache-tomcat-9.0.31
    + Database: postgresql-42.2.5

TODO:
* Login:
    + ~~Check if user exists.~~

* Register:
    + Add check for username (has letters, maybe few numbers).
    + ~~Add check for email (has @ symbol).~~
    + Add check for password (has letters, maybe few numbers).

* Profile options to add:
    + Add friends.
    + Remove friends.
    + Delete your account.
    + Modify your account details.
    + Send message to your email (updates/news).

DONE:
* Login:
    + Check if user exists.

* Register:
    + Check if details are correct (have minimum character length requirements and @ symbol for email).
    + Check if username does not exist.
    + Check if email does not exist.
    + Add user to the user database.

* Profile:
    + Display logged in email.
    + Display logged in username.
    + Method to check if username exists. (Needs to be added to the front-end on profile page)
    + Method to check if email exists. (Needs to be added to the front-end on profile page)
