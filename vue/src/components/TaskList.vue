<template>
    <section class="task-list-container">
        <Task 
            v-for="task in tasks" :key="task.id" 
            :listView="true"
            :title="task.title" 
            :description="task.description" 
            :notes="task.notes"
            :estimatedDuration="task.estimatedDuration"
            :actualDuration="task.actualDuration"  
        />
    </section>
</template>

<script>
import Task from '@/components/Task.vue';
export default {    
    name: 'task-list',
    components:{
        Task
    },
    props: ['tasks'],

}
</script>

<style>

</style>