import pymysql
import pymysql.cursors

con= pymysql.connect('127.0.0.1','root','password','pickupsport')

with con:
    cur =con.cursor()
    #id=#userid
    cur.execute("select events.EventDescription, events.FieldName, events.TimeStart, events.TimeEnd from (events inner join UsersEvent on events.PKEventID = UsersEvent.FKEventID) inner join Users on Users.PKUserID = Userevent.FKUserID and Users.PKUserID = %s;"%(id))
    p=cur.execute()
    for x in p:
        print (x)
    #flash page

con.close()