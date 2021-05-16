# CodingTest
Application Architecture:  
MVVM and MVP(to showcase i have used both)  
Major DIffrence between MVP and MVVM:  
 - In MVP all buisness related logic will be written in Presenter and throgh reference view will get data.
 - In MVVM all buisness logic will be in viewmodel and we will be observe changes made in viemodel by using LiveData.
 - MVVM survive Configuration changes.
 - One to one mapping in MVP between view and presenter.
 - One to many mapping in MVVM between view and viewmodel.
 
 
Core Logic:  
 - Used Okhttp to get Data from from Websocket url.  
 - Once onMessage get called from Websocket saving data in database.
 - Used Room Db(Abstact layer over Sqlite).
 - Populating Recyclerview from latest data.
 - Based on AQI showing diffrent color as per requirment in Recyclerview item background.
 - On Click of Specific item from list fetching city specific data from Database.
 - Showing city specific data in Chart(used MpChart Library).

Time Taken:  
Due to health issue didnt get much time to work.Completed all this task in 6-7 hours.
