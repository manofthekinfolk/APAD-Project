from flask_wtf import FlaskForm
from wtforms import StringField, PasswordField, SubmitField, BooleanField
from wtforms.validators import DataRequired, Length, Email, EqualTo


class RegistrationForm(FlaskForm):
    username = StringField('Username',
                           validators=[DataRequired(), Length(min=2, max=20)])
    email = StringField('Email',
                        validators=[DataRequired(), Email()])
    password = PasswordField('Password', validators=[DataRequired()])
    cpassword = PasswordField('Confirm Password',
                                     validators=[DataRequired(), EqualTo('password')])
    submit = SubmitField('Sign Up')

class AdduserForm(FlaskForm):
    firstname = StringField('firstname',validators=[DataRequired()])
    lastname = StringField('lastname',validators=[DataRequired()])
    email = StringField('email',validators=[DataRequired(), Email()])
    streetaddress = StringField('streetaddress',validators=[DataRequired()])
    city = StringField('city',validators=[DataRequired()])
    admin = StringField('admin')
    password = PasswordField('password', validators=[DataRequired()])
    confirmpassword = PasswordField('Confirm Password', validators=[DataRequired(), EqualTo('password')])
    submit = SubmitField('Sign Up')

class AddvenueForm(FlaskForm):
    id = StringField('id',validators=[DataRequired()])
    venuename = StringField('venuename',validators=[DataRequired()]) 
    eventcapacity = StringField('eventcapacity',validators=[DataRequired()])  #dc

class LoginForm(FlaskForm):
    email = StringField('Email',
                        validators=[DataRequired(), Email()])
    password = PasswordField('Password', validators=[DataRequired()])
    remember = BooleanField('Remember Me')
    submit = SubmitField('Login')

class CheckForm(FlaskForm):
    id = StringField('What is Your ID?',validators=[DataRequired()])
    submit = SubmitField('Search')

class SearcheventForm(FlaskForm):
    venue = StringField('Which Venue?',validators=[DataRequired()])
    submit = SubmitField('Search')

class DeleteeventsForm(FlaskForm):
    id = StringField('What is Event ID?',validators=[DataRequired()])
    submit = SubmitField('Delete')

class DeleteuserForm(FlaskForm):
    userid = StringField("What is User's Email?",validators=[DataRequired()])
    submit = SubmitField('Delete')

class DeletevenueForm(FlaskForm):
    venueid = StringField('What is Venue Name?',validators=[DataRequired()])
    submit = SubmitField('Delete')

class AddvenueForm(FlaskForm):
    venue = StringField('Venue',validators=[DataRequired()])
    eventcapacity = StringField('Event Capacity',validators=[DataRequired()])
    submit = SubmitField('Add')

class JoineventForm(FlaskForm):
    userid = StringField('Your ID',validators=[DataRequired()])
    eventid = StringField('Event ID',validators=[DataRequired()])
    submit = SubmitField('Join')    


