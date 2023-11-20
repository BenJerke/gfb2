import Vue from 'vue';
import Vuex from 'vuex';
import axios from 'axios';

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));
// on boot, grab all the user's tasks. 

if(currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

/*
If we do something during our session that needs to be communicated to the server, we'll track it here. 

1. Creating a new task
- Get task data from the user.
- If we created the task during our session, add it to session task list. 
- Add it to the new task list. 

2. Updating a task
- If the task status is new, find task on new task list and change it. 
- If the task status is not new, add it to the update queue. 
- If the task is cancelled or complete, change display accordingly. 

3. Deleting a task
- Hide the task from all places it may be displayed. 
- Put task's ID in the delete list. 

Each of our operations will process tasks one by one. 
If an  fails, we need to put it in the retry list. 
If 

*/

export default new Vuex.Store({
  
  state: {
    token: currentToken || '',
    user: currentUser || {},
    tasks: [],
    activeTask: {}
  },
  mutations: {
    // User Login/Permissions
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user',JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },
    // Task Data Management
    SELECT_SESSION_TASKS(state, selectedSessionTasks){
      state.sessionTasks = selectedSessionTasks; 
    },        
    QUEUE_TASK_FOR_CREATION(state, task){      
      state.newTasks.push(task);
    },
    QUEUE_TASK_FOR_DELETION(state, taskId){
      state.deletedTasks.push(taskId);
    },
    QUEUE_TASK_FOR_UPDATE(state, task){
      state.tasksToUpdate.push(task);
    },
    PURGE_CREATION_QUEUE(){

    },
    PURGE_UPDATE_QUEUE(){
      
    },
    PURGE_DELETION_QUEUE(){

    },
    // In-Session Data Mgmt
  },
  getters:{
    newSessionTasks(state){
      return state.sessionTasks;
    }
  }
})
