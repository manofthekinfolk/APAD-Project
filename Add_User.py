nm = input("User name: ")
pw = input("Password: ")
login = (nm,pw)
#placeholder= '?' # For SQLite. See DBAPI paramstyle.
#placeholders= ', '.join(placeholder for unused in l)
querya = []
queryall = []
queryn = []
c.execute('SELECT FN,LN FROM User WHERE TRUE;')
p1 = c.fetchall()
for x in p1:
    queryall.append(x)
c.execute('SELECT FN,LN FROM User WHERE Admin == 1;')
p1 = c.fetchall()
for x in p1:
    querya.append(x)
c.execute('SELECT LN FROM User WHERE Admin == 0;')
p1 = c.fetchall()
for x in p1:
    queryp.append(x)
#c.execute(query, l)

print(querya)
print(queryp)
print(login)

            
for i in queryall:
    if login == i:
        print("Login Success")
        for j in querya:
            if login == j:
                print("Admin")
                break
            else:
                print("User")
                break