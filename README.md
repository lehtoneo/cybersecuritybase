# Cybersecuritybase-project

My “application” is some kind of chat, where you can log in and leave messages for other people to read. When starting the app, it automatically creates two users: “admin”, with password “admin” and “user” with password “user”.


## Flaws:
### 1.	BROKEN AUTHENTICATION
The first flaw of many in my application is broken authentication, which is OWASP top 10-list’s number 2 security flaw. 
In the application, you can easily use for example OWASP zap to brute force passwords. First, you can create your own user to the app, and take a look at some registered user (who have sent messages). After that, you can just brute force password field as you know registered users. 

How to fix: 

It’s not the simplest fix. You could for example add limits to how many times one can try to log in to a user before the user is locked. After the user is logged, it could be unlocked by some kind of email verification. However, that requires a lot of code refactoring. Another fix is a bit more straightforward. One could add requirements to password strength. 
### 2.	CROSS-SITE SCRIPTING
The second flaw is OWASP top-10-list’s number 7 flaw, which is CROSS-SITE SCRIPTING. 
When logging in, one can send a message; for example “<script>alert('test')</script>”, which creates a pop up with text “test” to whoever visits the main page. This is obviously not dangerous, but it’s a clear sign that the web page can’t defend against XSS.

How to fix:

Go to main.html and change “th:utext=..” to th:text, on row 22. You could add extra protection by checking that each message sent to the server is safe. This could be done by MainController class, which saves the sent messages to “db”.

### 3.	BROKEN ACCESS CONTROL
Broken access control is OWASP top 10-list’s number 5 flaw. To put it simply, the flaw means that attacker can gain access to data/pages they should not be able to access. For example, normal user (attacker) gains admin rights somehow, or is able to view data which should be only available for real admins. In this app, the access control is completely messed up.
The application contains html file “admin.html”. The page should only be accessible for the user “admin”, but at the moment, any authenticated user can visit the page by simply logging in and visiting page http://localhost:8080/admin. 

How to fix:

Spring SecurityConfiguration class offers quite good tools to fix this problem. One can simply edit the configure(HttpSecurity)-method so that only admins can access certain page. To see how: [click here](https://github.com/lehtoneo/cybersecuritybase/blob/master/pictures/fixAdminrights1.png). Problem is now that even admin can’t visit the page, since the admin hasn’t been given admin rights. One can simply fix this by editing CustomUserDetailsService-class’s loadByUserName(username) method. To see how: [click here](https://github.com/lehtoneo/cybersecuritybase/blob/master/pictures/fixAdminrights2.png).
### 4.	SENSITIVE DATA EXPOSURE
OWASP top 10-list’s #3 flaw is Sensitive data exposure. According to OWASP top-10 article, one of the reasons to sensitive data exposure is missing protection on data at rest, which is also problem in this application. 
When adding sensitive data to database, it should be encrypted. When this app saves passwords to database, they are not encrypted at all. You can see this by viewing GreatPasswordEncoder class in the repository. This means that if attacker somehow gains access to database, he or she has all of the user information. 

How to fix:

Again, spring offers quite easy tools to fix this. Go to SecurityConfiguration class and edit the passwordEncoder method so that it returns BCryptPasswordEncoder(). 
Further fixes: 
Password encoder only encrypts the password, but everything else should be encrypted when inserting to database. In this case, the username could be also encrypted. If the app saved some sensitive data such as bank details, it’s obvious that also this information should be encrypted.

### 5.	SECURITY MISCONFIGURATION 
Number 6 in the OWASP top 10-list is security misconfiguration, which is also present in this app.
Spring security offers CSRF-protection by default, but in the app the protection is disabled.

How to fix:

Edit the configure(HttpSecurity) method in SecurityConfiguration class by removing the line “http.csrf().disable(); “.
### 6.	Using components with known vulnerabilities
In the OWASP article top-10 list, “Using components with known vulnerabilities” is in the place 9. It’s said in the article that vulnerable frameworks should not be used.
The application is using spring boot version 1.4.2 which is over three years old release. It’s safe to say that the release has some known vulnerabilities.

How to fix:

Change the line 16 in the pom.xml file. The version is currently 1.4.2, you could change it to for example 1.5.9, which seems to work in this app without refactoring code. 
