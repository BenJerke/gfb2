<template>
  <div>
    <div class="task-card-view" v-if="listView === false">
        <section class="task-labels">
            <h1 class="task-title">
                {{ title }}
            </h1>
            <h2 class="task-description">
                {{description}}
            </h2>
        </section>
        <section class="task-notes">
            {{ notes }}
        </section>
        <section class="task-controls">
            <button class="toggle-pause">
                Pause/Resume
            </button>
            <button class="skip">
                Skip
            </button>
            <button class="complete-task">
                Complete
            </button>
            <button class="cancel-task">
                Cancel
            </button>
        </section>
        <section class="task-time-display">
            <span class="estimated-duration">
            Estimated Duration: {{ estimatedDuration }}
            </span>
            <br>
            <span class="actual-duration">
            Actual Duration: {{ actualDuration }}
            </span>
            <button class="hide">
            Show/Hide
            </button>         
        </section>
    </div>

    <div class="task-list-view" v-if="listView === true">
        <h2 class="list-view-task-title">
            {{title}}
        </h2>
    </div>
  </div>
</template>

<script>
export default {
    name: "task",
    props:['title', 'description', 'estimatedDuration', 'actualDuration', 'notes', 'listView'],
    data(){
        return {
            timeActivated: null, 
            timeDeactivated: null,
            totalActiveDuration: null,               
        }
    },
    computed: {},
    methods: {
        activateTask(){            
            this.timeActivated = Date.now();
        },
        deactivateTask(){
            // when we stop working on a task for any reason, three things need to happen: 
            // set the time when we deactivated the task 
            // add the time we spent working on the task to the total active duration 
            // clear timeActivated and timeDeactivated  
            this.timeDeactivated = Date.now();            
            this.totalActiveDuration += (this.timeDeactivated - this.timeActivated);
            this.timeActivated = null; 
            this.timeDeactivated = null; 
        },
        initializeTask(){
            // We might be pulling a task from the server, but we might also be creating a new one. 
            // Creation should always feed data to task via props. 
            if(this.actualDuration){
                // Gonna have to parse this, but that's a problem for later.  
                // Tests would be nice to have... ugh. 
                this.totalActiveDuration = Date.parse(this.actualDuration);
            }
            
            if(this.estimatedDuration){

            }
        }
    },
}
</script>

<style>

</style>