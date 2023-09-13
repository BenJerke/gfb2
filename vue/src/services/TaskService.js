//import axios from 'axios';

export default{
    getUserTasks(){
        //return axios.get('/tasks', user);
        return [
            {
                id: 1,
                title: "Dummy Task 1",
                description: "Dummy Task Description",
                estimatedDuration: 3600000,
                actualDuration: 1200000,
                status: "In Progress",                
                notes: "this is some test data."
            },
            {
                id: 2,
                title: "Dummy Task 2",
                description: "Dummy Task Description",
                estimatedDuration: 7200000,
                actualDuration: 9000000, 
                status: "In Progress",             
                notes: "this is some test data."
            },
            {
                id: 3,
                title: "Dummy Task 3",
                description: "Dummy Task Description",
                estimatedDuration: "2h 40m",
                actualDuration: "1h 30m",
                status: "Paused",                
                notes: "this is some test data."
            }        
        ]
    },
    convertDurationStringsToMilliseconds(){   

    }
}