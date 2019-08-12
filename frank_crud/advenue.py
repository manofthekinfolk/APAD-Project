import pymysql
import pymysql.cursors

con= pymysql.connect('127.0.0.1','root','password','pickupsport')

with con:
    cur =con.cursor()
    i=input("Delete or Add?")
    if i=="1":
        vn=input("Please input Venuename:")
        ec=input("Please input Eventcapacity:")
        cur.execute("insert into Venues (Venue,EventCapacity) values ('%s',%s);"%(vn,ec))
        #call flash or other page
    else:
        cur.execute("select * from Venues;")
        p=cur.fetchall()
        for i in p:
            print(i)
        id=input("please input ID:")
        cur.execute("delete from Venues where PKVenueID=('%s')"%(id))
            #call flash or other page

con.close()