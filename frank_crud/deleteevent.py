import pymysql
import pymysql.cursors

con= pymysql.connect('127.0.0.1','root','password','pickupsport')

with con:
    cur =con.cursor()
    cur.execute("select * from events;")
    p=cur.execute()
    for x in p:
        print (x)
    id=input("please input the id you want to delete:")
    cur.execute("delete from events where PKEventID = %s"%(id))
    #flash page

con.close()