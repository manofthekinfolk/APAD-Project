import pymysql
import pymysql.cursors

con= pymysql.connect('127.0.0.1','root','password','pickupsport')

with con:
    cur =con.cursor()
    em=input("Please input your email address:")
    pw=input("Please input your password:")
    cur.execute("select * from Users where (Email, Password)=('%s','%s');"%(em,pw))
    p=cur.fetchall()
    if p == ():
        print('Login Failed, please try again')
        #call current page
    else:
        print('success')
        #call another html page
con.close()