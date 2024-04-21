# TODO
- Create the following classes:
  - User Class
    - Variables: UserID, FirstName, LastName, Username, Age
    - Functions: Getters & Setters for variables, ToString
  - Song Class
    - Variables: SongID, SongName, Metadata (Artist, Album, Last Played), Associated User
    - Functions: Getters & Setters for variables, ToString
  - UserDAO Class
  - SongDAO Class
  - UserController Class
  - SongController Class
- Add database functionality in:
  - DAO classes
  - Controller classes
  - `PppApplication.java`
- User Stories:
  - Select all songs from the songs table
  - Select one song by its ID
  - Select all songs that belong to a certain user (find by user id FK)
  - Insert a new song into the songs table
  - Insert a new user into the users table
  - Update a user
  - Delete a song
  - Extra Functionality (At least one of the following):
    - Sort by listening time of specific user (listening history)
    - Sort by listening time of any user (global listening history)
    - Return resultSets of specific metadata (Album, Artist, Genre)
    - (INSERT MORE FUNCTIONALITY IDEAS HERE IF APPLICABLE)
