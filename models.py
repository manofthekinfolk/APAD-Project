from datetime import datetime
from flaskblog import db, login_manager
from flask_login import UserMixin


@login_manager.user_loader
def load_user(user_id):
    return User.query.get(int(user_id))


class Users(db.Model, UserMixin): #No FK in table, declare FK in venue, UserEvent, and Event
    id = db.Column(db.Integer, primary_key=True)
    email = db.Column(db.String(120), unique=True, nullable=False)
    first_name = db.Column(db.String(40), nullable=False)
    last_name = db.Column(db.String(40), nullable=False)
    street_Address =  db.Column(db.String(40), nullable=False)
    city = db.Column(db.String(40), nullable=False)
    state = db.Column(db.String(20), nullable=False)
    password = db.Column(db.String(60), nullable=False)
    admin = db.Column(db.Boolean,nullable=False)
    #FK
    Venue = db.relationhsips('Venue', db.ForeignKey('user.id'), lazy=True, backref='AdminCreator')
    UserEvent = db.relationship('UserEvent', secondary=UserEvent, lazy='subquery', backref=db.backref('users', lazy=True)) #for user event table
    Event = db.relationship('Event', lazy='subquery', backref='users') #for Event participation table
    def __repr__(self):
        return f"Users({self.email}', '{self.first_name}', '{self.last_name}', '{self.street_Address}', '{self.state}', '{self.city}', '{self.admin}')"
#

class Venues(db.Model): #FK from User, Declare FK for Events
    id = db.Column(db.Integer, primary_key=True)
    venue = db.Column(db.String(100), nullable=False)
    event_capacity = db.Column(db.Text, nullable=False)
    #Fk relationships
    venue_event = db.relationship('Event', backref='Venue', lazy=True) 
    user_id = db.Column(db.Integer, db.ForeignKey('users.id'), nullable=False) #should be good

    def __repr__(self):
        return f"Venues('{self.venue}', '{self.event_capacity}')"

UserEvent = db.Table('UserEvent',db.Column(db.Integer, db.ForeignKey('venue.id'), primary_key=True),
                                 dbColumn(db.Integer, db.ForeignKey('event.id'), primary_key=True))

class Events(db.Model): #2 FK declare for User-Event and Event and Creator? 
    id = db.Column(db.Integer, primary_key=True)
    event_name = db.Column(db.String(100), nullable=False)
    event_description = db.Column(db.String(1000), nullable=False)
    user_capacity = db.Column(db.Integer, nullable=False)
    time_start = db.Column(db.Integer, nullable=False) #dc data type
    time_end = db.Column(db.Integer, nullable=False) #dc data type
    tag = db.Column(db.String(10))
    #fk
    venue_id = db.Column(db.Integer, db.ForeignKey('venue.id'), nullable=False)
    tag_id = db.Column(db.Integer, db.ForeignKey('tag.id'), nullable=False)
    user_id = db.Column(db.Integer, db.ForeignKey('user.id'), nullable=False)

    def __repr__(self):
        return f"Events('{self.event_name}', '{self.event_description}', '{self.user_capacity}', '{self.time_start}', '{self.time_end}', '{self.tag}')"

class Sports(db.Model): #Declare FK for Events
    id = db.Column(db.Integer, primary_key=True)
    sport_name = db.Column(db.String(50), nullable=False)
    event_sport = db.relationship('Events', backref='sport_name', lazy=True)

    def __repr__(self):
        return f"Sports('{self.sport_name}',)"

class Tags(db.Model): #
    id = db.Column(db.Integer, primary_key=True)
    Sport_Name = db.Column(db.String(50), nullable=False)
    addresses = db.relationship('Address', backref='person', lazy=True)
    def __repr__(self):
        return f"Tags('{self.Sport}', '{self.date_posted}')"

