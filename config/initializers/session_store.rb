# Be sure to restart your server when you modify this file.

# Your secret key for verifying cookie session data integrity.
# If you change this key, all old sessions will become invalid!
# Make sure the secret is at least 30 characters and all random, 
# no regular words or you'll be exposed to dictionary attacks.
ActionController::Base.session = {
  :key         => '_lunchplanner_session',
  :secret      => '121a446c9509279f5c34739fd269e87aa00aafe5c465353f3aff5f773883f1298203c9e03c4f2eda34a3e7333729f9c419e57b3e121629d3975de5214228aebe'
}

# Use the database for sessions instead of the cookie-based default,
# which shouldn't be used to store highly confidential information
# (create the session table with "rake db:sessions:create")
# ActionController::Base.session_store = :active_record_store
