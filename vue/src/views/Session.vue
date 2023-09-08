<template>
  <div class="session-container">
    <section class="session-task-list">
        
    </section>
    <section class="session-active-task">
        
    </section> 
  </div>
</template>

<script>
import TaskList from '@/components/TaskList.vue';

export default {

    name: 'session-view',
    components: {
        TaskList,
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