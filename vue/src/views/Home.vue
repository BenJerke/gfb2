<template>
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
    <button @click="selectSessionTasks()"> Start Working </button>
    <TaskList 
      :tasks="taskList" 
      class="all-tasks"
    />
    <TaskList 
      :tasks="selectedTasks" 
      class="selected-task-list"
    />
  </div>
</template>

<script>
import TaskList from '@/components/TaskList.vue';
import taskService from '@/services/TaskService.js'; 

export default {
  name: "home",
  components: {
    TaskList
  },
  data(){
    return {
      taskList: [],
      selectedTasks: [],
      userTags: [],
    }
  },
  computed: {},
  methods: {
    async getTasks(){
      try {
      const response = await taskService.getUserTasks();
      if (!tasks){
        // TODO: refactor this once we're actually using axios to call the backend. 
        throw new Error(`HTTP error: ${response.status}`); 
      }
      const tasks = response.json();
      return tasks; 
      } catch(error){
        console.error(`Could not get task list: ${error}`)
      }

    },
    selectSessionTasks(){
      this.$store.commit('SELECT_SESSION_TASKS', this.selectedTasks);
      this.$router.push({name: 'session'});
      }
  },
  created(){
    // this.taskList = this.getTasks(); 
    // idk if this is the right thing to do. guess we'll find out. 
    this.taskList = taskService.getUserTasks();    
  }

};
</script>

<style scoped>
.all-tasks{
  border: 1px solid black; 
}
</style>
