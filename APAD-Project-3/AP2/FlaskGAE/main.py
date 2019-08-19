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

from flask import Flask,jsonify,session,redirect,url_for,escape
import pymysql
from flask import render_template
from flask import request

db_user = os.environ.get('CLOUD_SQL_USERNAME')
db_password = os.environ.get('CLOUD_SQL_PASSWORD')
db_name = os.environ.get('CLOUD_SQL_DATABASE_NAME')
db_connection_name = os.environ.get('CLOUD_SQL_CONNECTION_NAME')

app = Flask(__name__)
# Set the secret key to some random bytes. Keep this really secret!
# I used python to generate this: python -c 'import os; print(os.urandom(16))'
app.secret_key = b'Zc\x92\x19e\xdd\x16<fpO\xc2\x03,\xbb\x0c'

def db_connect():
    # When deployed to App Engine, the `GAE_ENV` environment variable will be
    # set to `standard`
    if os.environ.get('GAE_ENV') == 'standard':
        # If deployed, use the local socket interface for accessing Cloud SQL
        unix_socket = '/cloudsql/{}'.format(db_connection_name)
        cnx = pymysql.connect(user=db_user, password=db_password,
                              unix_socket=unix_socket, db=db_name, cursorclass=pymysql.cursors.DictCursor)
    else:
        # If running locally, use the TCP connections instead
        # Set up Cloud SQL Proxy (cloud.google.com/sql/docs/mysql/sql-proxy)
        # so that your application can use 127.0.0.1:3306 to connect to your
        # Cloud SQL instance
        host = '127.0.0.1'
        cnx = pymysql.connect(user=db_user, password=db_password,
                              host=host, db=db_name,cursorclass=pymysql.cursors.DictCursor)
    return cnx
    
@app.route('/')
def main():

    cnx = db_connect()
    with cnx.cursor() as cursor:
         cursor.execute('SELECT candidate,email FROM votes;')
         result = cursor.fetchall()
    cnx.close()

    return str(result)
# [END gae_python37_cloudsql_mysql]

# This route sends an array of JSON user objects as a string
@app.route('/list')
def list():
    cnx = db_connect()
    with cnx.cursor() as cursor:
        cursor.execute('SELECT candidate,email FROM votes;')
        users = cursor.fetchall()
        desc = cursor.description
    cnx.close()
    if request.user_agent.platform == "windows":
        return render_template('users.html', users=users, desc=desc)
        return jsonify(users)
    elif request.user_agent.platform == "android":
        return jsonify(users)

@app.route('/hello')
@app.route('/hello/<name>')
def hello(name=None):
    
    if request.user_agent.platform == "windows":
        return render_template('hello.html', name=name)
    elif request.user_agent.platform == "android":
        if name:
            myresp = "Hello " + name
        else: 
            myresp = "Hello Anon"
        return myresp


# The following three routes are to test flask sessions
@app.route('/home')
def home():
    if 'username' in session:
        return 'Logged in as %s' % escape(session['username'])
    return 'You are not logged in'

@app.route('/login', methods=['GET', 'POST'])
def login():
    if request.method == 'POST':
        session['username'] = request.form['username']
        return redirect(url_for('home'))
    return '''
        <form method="post">
            <p><input type=text name=username>
            <p><input type=submit value=Login>
        </form>
    '''

@app.route('/logout')
def logout():
    # remove the username from the session if it's there
    session.pop('username', None)
    return redirect(url_for('home'))


# The following three routes are to test if we can use flask sessions without a POST
@app.route('/alogin/<name>')
def alogin(name):  
    session['aname'] = name
    return redirect(url_for('ahome'))

@app.route('/ahome')
def ahome():
    if 'aname' in session:
        return 'Logged in as %s' % escape(session['aname'])
    return 'You are not logged in'     

@app.route('/alogout')
def alogout():
    # remove the aname from the session if it's there
    session.pop('aname', None)
    return redirect(url_for('ahome'))
    
if __name__ == '__main__':
    app.run(host='127.0.0.1', port=8080, debug=True)
