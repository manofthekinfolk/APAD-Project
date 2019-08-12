import pymysql
import pymysql.cursors

con= pymysql.connect('127.0.0.1','root','password','pickupsport')

with con:
    cur =con.cursor()
    ve=input("please input venue name")
    cur.execute("select * from events where FKVenueID=(select PKVenueID where Venue = '%s')"%(ve))
    p=cur.fetchall
    for x in p:
        print (x)

con.close()