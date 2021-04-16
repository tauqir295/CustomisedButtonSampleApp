co# Sample app
This is a sample app which consumes mobile design library.

### Features screen
1. Sign up
2. Login
3. Home screen

#### Session expiry
There are 2 aspect of session timeout when login session expires and user is sent back to login screen
1.  App is in background for more than 10 seconds
    Here normal activity lifecycle events `onResume` and `onPause` are used.

2.  App is in foreground and inactive for 30 seconds
    WorkManager is used for managing the session timeout. 
    
    `Requirement - if login session expires if app is in foreground for more than 30 seconds.`
    
    `Additional added - If user interacts with app with in 30 seconds then session will be alive for next 30 seconds.`

