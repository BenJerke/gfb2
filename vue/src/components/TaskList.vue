<template>
    <section class="task-list-container">
        <draggable :list="tasks" group="task-group">
        <div class="task-box" v-for="task in tasks" :key="task.id">
        <Task              
            :listView="true"
            :title="task.title" 
            :description="task.description" 
            :notes="task.notes"
            :estimatedDuration="task.estimatedDuration"
            :actualDuration="task.actualDuration"  
        />
        </div>
        </draggable>
    </section>
</template>

<script>
import Task from '@/components/Task.vue';
import draggable from 'vuedraggable';
export default {    
    name: 'task-list',
    components:{
        Task,
        draggable
    },
    props: ['tasks'],

}
</script>

<style>

</style>