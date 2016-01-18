## Inspiration
One of our team members had to drop a class after forgetting about a test. We were disappointed in him too. But we figured that there were probably a bunch of other people that could have avoided the same sucky situation.

## What it does
Sillibus scans your class syllabuses and places the dates of your upcoming tests and assignments on your Google Calendar.

## How we built it
Everything in the backend is java and it uses some super long and complicated regular expressions to pull dates from the syllabus text. Then it shoots this information to our Android app and our web app to be displayed for users. From there, they can choose to automatically place these dates on their Google calendar.

## Challenges we ran into
We first tried to use natural language processing to recognize dates and assignment types. This was hella tough and way too complicated, so we switched over to regular expressions. Front end was pretty easy, but setting up the server was SUPER tough and we never totally got it right. We were using maven and were having a bunch of difficulties recognizing the location of our scripts.

We also had a tough time setting up the app but got most of it working in the end. One thing we weren't able to accomplish before the time ran out was getting the Google Calendar integrations figured out.

## Accomplishments that we're proud of
We didn't give up. It was our front-end guy's first time doing front-end work, and our Android developers first time working with Android so they totally crushed it. Also that server was an absolute pain in the booty--we're pretty proud we got as far as we did.


## What's next for Sillibus
Get the server up and running and get google calendar in there.
