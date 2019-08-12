import pymysql
import pymysql.cursors

con= pymysql.connect('127.0.0.1','root','password','pickupsport')

with con:
    cur =con.cursor()
    fn=input("Please input your first name:")
    ln=input("Please input your last name:")
    em=input("Please input your email address:")
    pw=input("Please input your password:")
    sa=input("Please input your street address:")
    ct=input("Please input your city")
    cur.execute('insert into Users(FirstName, LastName, Email, Password, StreetAddress, City, Admin) values ("%s","%s","%s","%s","%s","%s",0)'%(fn,ln,em,pw,sa,ct))
    print("Thank you for registeration")
con.close()