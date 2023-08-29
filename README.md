# GFB2
The long-awaited sequel to Goldfish Brain; the world's first time-tracking application written by Ben Jerke. 

## Goals: 
1. Allow users to create lists of tasks.
2. Allow users to associate tasks with projects.
3. Allow users to classify tasks and projects with custom-defined tags.
4. Allow users to automatically track time spent working on a given task.
5. Allow users to manually log time spent working on a given task.
6. Allow users to estimate the amount of time it will take to accomplish a given task.
7. Make it easier for users to explain what they've been doing on the computer all day by providing reports and visualizations on their data. 

## Architecture: 
- Frontend: Vue.js
- Backend: Java REST API (powered by SpringBoot) 
- Database: Postgres

## OK, but why? 
Most people I know can recall at least one instance of getting up and walking into a different room, only to immediately forget what they came for. I identify with this experience. I get distracted pretty easily, and I forget things all the time. When it happens, I often catch myself wishing I had a personal heads-up display with my current objectives; like in a video game. This is essentially what I'm trying to make. There are a few extra bells and whistles, though - I like the idea of being able to see trends in the way I spend my time, and in how much time I think it will take to accomplish things. I especially like the idea of tracking estimate accuracy over time. Like, are you getting better or worse at predicting how long stuff takes? That seems like an important question. 
My hope is that habitual use of this application will improve my time management and organization skills. Maybe it'll help yours too. 

## Just use a spreadsheet, you dork. 
But where's the fun in that?!
Jokes aside, the original GFB could spit out a CSV file with all your session's tasks and associated timings on it. As long as you didn't mind the bugs, it worked well enough. I could probably incorporate something similar here (or maybe even provide a way to import data). We'll see how much time I have. 
