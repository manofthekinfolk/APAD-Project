# Copyright 2018 Google LLC
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

# [START gae_python37_cloudsql_mysql]
import os

from flask import Flask, escape, request, render_template, url_for, flash, redirect, session
from forms import RegistrationForm, LoginForm, AdduserForm,AddvenueForm, CheckForm, DeleteeventsForm, AddvenueForm, SearcheventForm, JoineventForm, DeletevenueForm, DeleteuserForm
import pymysql
import pymysql.cursors
app = Flask(__name__)
app.config.update(dict(
    SECRET_KEY="powerful secretkey",
    WTF_CSRF_SECRET_KEY="a csrf secret key"
))

db_user = os.environ.get('CLOUD_SQL_USERNAME')
db_password = os.environ.get('CLOUD_SQL_PASSWORD')
db_name = os.environ.get('CLOUD_SQL_DATABASE_NAME')
db_connection_name = os.environ.get('CLOUD_SQL_CONNECTION_NAME')
unix_socket = '/cloudsql/{}'.format(db_connection_name)
cnx = pymysql.connect(user=db_user, password=db_password,
                        unix_socket=unix_socket, db=db_name)
con = pymysql.connect(user=db_user, password=db_password,unix_socket=unix_socket, db=db_name)
#con= pymysql.connect('127.0.0.1','root','password','pickupsport')





def main():
    # When deployed to App Engine, the `GAE_ENV` environment variable will be
    # set to `standard`
    if os.environ.get('GAE_ENV') == 'standard':
        # If deployed, use the local socket interface for accessing Cloud SQL
        unix_socket = '/cloudsql/{}'.format(db_connection_name)
        cnx = pymysql.connect(user=db_user, password=db_password,
                              unix_socket=unix_socket, db=db_name)
    else:
        # If running locally, use the TCP connections instead
        # Set up Cloud SQL Proxy (cloud.google.com/sql/docs/mysql/sql-proxy)
        # so that your application can use 127.0.0.1:3306 to connect to your
        # Cloud SQL instance
        host = '127.0.0.1'
        cnx = pymysql.connect(user=db_user, password=db_password,
                              host=host, db=db_name)

    with cnx.cursor() as cursor:
        cursor.execute('SELECT NOW() as now;')
        result = cursor.fetchall()
        current_time = result[0][0]
        #id=input("User ID")
        #cur.execute("select events.EventDescription, events.FieldName, events.TimeStart, events.TimeEnd from (events inner join UsersEvent on events.PKEventID = UsersEvent.FKEventID) inner join Users on Users.PKUserID = Userevent.FKUserID and Users.PKUserID = %s;"%(id))
        #cursor.execute('create table test(id int primary key);')
    cnx.close()

    return str(current_time)
# [END gae_python37_cloudsql_mysql]
posts =[
    {
        'author': 'Colin',
        'title': 'Badminton',
        'content': '2v2',
        'dat_posted': 'January 1st, 2019',
    },
    {
        'author': 'Frank',
        'title': 'Basketball',
        'content': '5v5',
        'date_posted': 'January 1st, 2019',
    },
]



@app.route('/')
@app.route("/home")
def home():
    if session.get('logedin') is not None:
        return render_template('home.html', posts=posts)
    else: 
        session['logedin'] = None
        session['admin'] = None
        session['id'] = None
        return render_template('home.html', posts=posts)


@app.route("/about")
def about():
    return render_template('about.html', title='About')


@app.route("/register", methods=['GET', 'POST'])
def register():
    form = RegistrationForm()
    if form.validate_on_submit():
        print(form.username.data)
        flash(f'Account created for {form.username.data}!', 'success')
        return redirect(url_for('home'))
    return render_template('register.html', title='Register', form=form)


# @app.route("/login", methods=['GET', 'POST'])
# def login():
#     form = LoginForm()
#     if form.validate_on_submit():
#         if form.email.data == 'admin@blog.com' and form.password.data == 'password':
#             flash('You have been logged in!', 'success')
#             return redirect(url_for('home'))
#         else:
#             flash('Login Unsuccessful. Please check username and password', 'danger')
#     return render_template('login.html', title='Login', form=form)
@app.route("/login",methods=['GET', 'POST'])
def login():
    form = LoginForm()
    if form.validate_on_submit():
        with con:
            cur =con.cursor()
            em=form.email.data
            pw=form.password.data
            cur.execute("select PKUserID from Users where (Email, Password)=('%s','%s');"%(em,pw))
            p=cur.fetchall()
            session['id'] = p[0][0]
            print(session['id'])
            cur.execute("select Admin from Users where (Email, Password)=('%s','%s');"%(em,pw))
            p=cur.fetchall()
            session['admin'] = p[0][0]
            #print(p)
            if p == ():
                #print('Login Failed, please try again')
                flash(f'Login Failed, please try again', 'danger')
                return render_template('login.html',title='Login', form=form)
                #call current page
            else:
                #print('success')
                flash(f'Login Success', 'success')
                session['logedin']= True
                if p[0][0]==1:
                    return redirect(url_for('deleteuser'))
                else:
                    return redirect(url_for('home'))
                #call another html page
    return render_template('login.html', title='Login', form=form)

@app.route("/logout",methods=['GET', 'POST'])
def logout():
    if session['logedin']==True:
        session.pop('id', None)
        session.pop('admin', None)
        session.pop('logedin', None)
        flash(f'Logout Success', 'success')
    else:
        flash(f'Do you wish to login?', 'success')
        return redirect(url_for('login'))
    return redirect(url_for('home'))


@app.route("/adduser", methods=['GET', 'POST'])
def adduser():
    form = AdduserForm()
    print(form.validate_on_submit())
    print(form.firstname.data)
    if form.validate_on_submit():
        print(form.firstname.data)
        with con:
            cur =con.cursor()
            fn=form.firstname.data
            ln=form.lastname.data
            em=form.email.data
            pw=form.password.data
            sa=form.streetaddress.data
            ct=form.city.data
            cur.execute('insert into Users(FirstName, LastName, Email, Password, StreetAddress, City, Admin) values ("%s","%s","%s","%s","%s","%s",0)'%(fn,ln,em,pw,sa,ct))
        flash(f'Account created for {form.firstname.data}!', 'success')
        return redirect(url_for('home'))
    return render_template('adduser.html', title='Add User', form=form)
        

@app.route("/deleteuser", methods=['GET', 'POST'])
def deleteuser():
    form = DeleteuserForm()
    if session['logedin']==True:
        if session['admin']==True:
            if form.validate_on_submit():
                with con:
                    cur =con.cursor()
                    userid = form.userid.data
                    cur.execute("delete from Users where Email = '%s'"%(userid))    
                flash(f'User Deleted', 'success')
                return redirect(url_for('deleteuser'))
            return render_template('deleteuser.html', title='Delete User', form=form)
        else:
            flash(f'You do not have access to this page', 'danger')
            return redirect(url_for('home'))
    else:
        flash(f'Please Login First', 'success')
        return redirect(url_for('login'))


@app.route("/addvenue", methods=['GET', 'POST'])
def addvenue():
    form = AddvenueForm()
    if session['logedin']==True:
        if session['admin']==True:
            if form.validate_on_submit():
                with con:
                    cur =con.cursor()
                    vn=form.venue.data
                    ec=form.eventcapacity.data
                    cur.execute("insert into Venues (Venue,EventCapacity) values ('%s',%s);"%(vn,ec))
                flash(f'Venue added for {form.venue.data}!', 'success')
                return redirect(url_for('deleteuser'))
            return render_template('addvenue.html', title='Add Venue', form=form)
        else:
            flash(f'You do not have access to this page', 'danger')
            return redirect(url_for('home'))
    else:
        flash(f'Please Login First', 'success')
        return redirect(url_for('login'))
    

    
@app.route("/deletevenue", methods=['GET', 'POST'])
def deletevenue():
    form = DeletevenueForm()
    if session['logedin']==True:
        if session['admin']==True:
            if form.validate_on_submit():
                with con:
                    cur =con.cursor()
                    id = form.venueid.data
                    #cur.execute("delete from Venues where PKVenueID=('%s')"%(id)) 
                    cur.execute("delete from Venues where Venue=('%s')"%(id))         
                flash(f'Venue Deleted', 'success')
                return redirect(url_for('deleteuser'))
            return render_template('deletevenue.html', title='Delete Venue', form=form)
        else:
            flash(f'You do not have access to this page', 'danger')
            return redirect(url_for('home'))
    else:
        flash(f'Please Login First', 'success')
        return redirect(url_for('login'))

@app.route("/deleteevent", methods=['GET', 'POST'])
def deleteevent():
    form = DeleteeventsForm()
    if session['logedin']==True:
        if session['admin']==True:           
            if form.validate_on_submit():
                with con:
                    cur =con.cursor()
                    id = form.id.data
                    cur.execute("delete from events where PKEventID = %s"%(id))            
                    flash(f'Event Deleted', 'success')
                return redirect(url_for('deleteuser'))
            return render_template('deleteevent.html', title='Delete Event', form=form)
        else:
            flash(f'You do not have access to this page', 'danger')
            return redirect(url_for('home'))
    else:
        flash(f'Please Login First', 'success')
        return redirect(url_for('login'))

@app.route("/joinevent", methods=['GET', 'POST'])
def joinevent():
    form = JoineventForm()
    if session['logedin']==True:
        if form.validate_on_submit():
            with con:
                cur =con.cursor()
                userid = form.userid.data
                eventid = form.eventid.data
                cur.execute("insert into UsersEvent(FKUserID, FKEventID) values (%s,%s);"%(userid,eventid))
            flash(f'Event Joined', 'success')
            return redirect(url_for('home'))
        return render_template('joinevent.html', title='Join Event', form=form)
    else:
        flash(f'Please Login First', 'success')
        return redirect(url_for('login'))

@app.route("/eventhejoin",methods=['GET', 'POST'])
def eventhejoin():
    form = CheckForm()
    if session['logedin']==True:
        if form.validate_on_submit():
            with con:
                cur =con.cursor()
                id = form.id.data
                result = cur.execute("select FieldName, EventDescription, TimeStart, TimeEnd from(events join UsersEvent on UsersEvent.FKEventID=events.PKEventID ) where FKUserID = %s;"%(id))
                if result >0:
                    userinfo = cur.fetchall()   
                    return render_template('eventhejoin.html', userinfo = userinfo, form=form)
        return render_template('eventhejoin.html',title='What Event I Join', form=form)
    else:
        flash(f'Please Login First', 'success')
        return redirect(url_for('login'))


@app.route("/searchevent",methods=['GET', 'POST'])
def searchevent():
    form = SearcheventForm()
    if session['logedin']==True:
        if form.validate_on_submit():
            with con:
                cur =con.cursor()
                ve=form.venue.data
                result = cur.execute("select * from events join Venues on PKVenueID=events.FKVenueID where FKVenueID=(select PKVenueID where Venue = '%s');"%(ve))
                if result >0:
                    userinfo = cur.fetchall()
                    return render_template('searchevent.html', userinfo = userinfo, form=form) 
        return render_template('searchevent.html',title='What Event in this Venue?', form=form)
    else:
        flash(f'Please Login First', 'success')
        return redirect(url_for('login'))
    






# if __name__ == '__main__':
#     app.run(host='127.0.0.1', port=8080, debug=True)
