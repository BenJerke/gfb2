<template>
  <div class="session-container">
      <TaskList :tasks="taskList" class="session-task-list"/>        
      <Task class="session-active-task"> 
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
    props: ['taskList', 'tagList'],
    data(){
        return {
        }
    },
    computed: {},
    methods: {},
    created(){
    }

}
</script>

<style>

</style>