import pymysql
import pymysql.cursors

con= pymysql.connect('127.0.0.1','root','password','pickupsport')

with con:
    cur =con.cursor()
    #ui=userid
    cur.execute("select * from events;")
    p=cur.fetchall()
    for i in p:
        print(i)
    ei=input("Please input the eventid you want to join")
    cur.execute("insert into UsersEvent where (FKUserID, FKEventID) = (%s,%s)"%(ui,ei))
    print("join successfully")


con.close()