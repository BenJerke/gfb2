<template>
  <div class="task-entry-container">
    <form>
        <input type="text"  id="" placeholder="Title">
        <textarea v-model="newTask.description" placeholder="Description">        
        <input type="number" v-model="estimateHours" placeholder="How many hours will this take?">
        <input type="number" v-model="estimateMinutes" placeholder="How many minutes will this take?">
        <textarea v-model="newTask.notes" placeholder="Notes"></textarea>
        <button @submit.prevent="addTask"></button>      
    </form>
  </div>
</template>

<script>
import timeFuncs from '@/services/TimeConverterService.js';
export default {
    name: 'task-entry', 
    data(){
        return {
            newTask:{
                title: '',
                description: '',
                status: 'New',
                estimatedDuration: 0,
                notes: '',                
            },
            estimateHours: 0,
            estimateMinutes: 0
        }
    },
    methods: {
        addTask(){
            newTask.estimatedDuration = timeFuncs.sumTimeValuesToMilliseconds(estimateMinutes, estimateHours, 0, 0, 0); 
            console.log(newTask);
            this.$store.commit('ADD_NEW_TASK', newTask);
        }
    }
}
</script>

<style>

</style>