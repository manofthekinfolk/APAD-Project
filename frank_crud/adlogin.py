import pymysql
import pymysql.cursors

con= pymysql.connect('127.0.0.1','root','password','pickupsport')

with con:
    cur =con.cursor()
    em=input("Please input your email address:")
    pw=input("Please input your password:")
    cur.execute("select * from Users where (Email, Password, admin)=('%s','%s', 1);"%(em,pw))
    p=cur.fetchall()
    if p == ():
        print(p)
        print('Login Failed, please try again')
        #call login page
    else:
        print('success')
        #call another html page
con.close()