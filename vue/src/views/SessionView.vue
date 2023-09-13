<template>
  <div class="session-container">
    <!--       
      Get a list of tasks defined in the home view. 
    -->
      <TaskList :tasks="taskList" class="session-task-list"/>     
      <Task class="session-active-task" 
      :list-view="false" 
      :title="taskList[0].title"
      :description="taskList[0].description"
      :estimatedDuration="taskList[0].estimatedDuration"
      :actualDuration="taskList[0].actualDuration"
      :notes="taskList[0].notes"
      /> 
  </div>
</template>

<script>
import TaskList from '@/components/TaskList.vue';
import Task from '@/components/Task.vue';
export default {
    name: 'session-view',
    components: {
        TaskList,
        Task    
    },
    data(){
        return {
            taskList: [],
        }
    },
    computed: {},
    methods: {
        getSessionTasks(){
            this.taskList = this.$store.state.sessionTasks; 
        }
    },
    created(){
        this.getSessionTasks();        
    }

}
</script>

<style>

</style>