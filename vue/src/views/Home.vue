<template>
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
    <TaskList :tasks="taskList" />
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
      taskList: null,
    }
  },
  computed: {},
  methods: {
    async getTasks(){
      await taskService.getUserTasks();
    }
  },
  created(){
    this.taskList = this.getTasks();
  }

};
</script>
