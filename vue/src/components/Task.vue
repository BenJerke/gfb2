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
            <button class="toggle-pause" @click="pauseOrResumeTask()">
                Pause/Resume
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
            Estimated Duration: {{ estimatedDurationString }}
            </span>
            <br>
            <span class="actual-duration">
            Actual Duration: {{ durationString }}
            </span>
            <!-- <button class="hide">
            Show/Hide
            </button>          -->
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
const STATUS_PAUSED = 'Paused';
const STATUS_COMPLETE = 'Complete';
const STATUS_CANCELLED = 'Cancelled';
const STATUS_IN_PROGRESS = 'In Progress';
import timeFuncs from '@/services/TimeConverterService.js';

export default {
    name: "task",
    props:['title', 'description', 'estimatedDuration', 'actualDuration', 'notes', 'listView'],
    data(){
        return {
            // all of these are gonna be numbers of milliseconds. 
            timeActivated: null, 
            timeDeactivated: null,
            totalActiveDuration: null,
            active: null,
            durationString: "",                  
        }
    },
    computed: {
        // activeTaskDuration(){
        //     let val = 0;
        //     let output = "";
        //     if(this.timeActivated){
        //         val = Date.now() - this.timeActivated;
        //         output += timeFuncs.convertMillisecondsToDurationString(val);
        //     }
        //     return output;
        // }
    },
    methods: {
        activateTask(){            
            this.timeActivated = Date.now();            
            this.status = STATUS_IN_PROGRESS;
            this.active = setInterval(this.updateDurationString, 10)            
        },
        updateDurationString(){
            this.durationString = timeFuncs.convertMillisecondsToDurationString(Date.now() - this.timeActivated);
        },
        deactivateTask(){
            // when we stop working on a task for any reason, three things need to happen: 
            // set the time when we deactivated the task 
            // add the time we spent working on the task to the total active duration 
            // clear timeActivated and timeDeactivated
            clearInterval(this.active); 
            this.timeDeactivated = Date.now();       
            this.totalActiveDuration += (this.timeDeactivated - this.timeActivated);
            this.timeActivated = null; 
            this.timeDeactivated = null;
        },
        pauseOrResumeTask(){
            if(this.status == STATUS_PAUSED || this.status == undefined){
                console.log('unpausing')
                this.activateTask();
            } else {
                this.deactivateTask();
                this.status = STATUS_PAUSED;
            }
        },
        cancelTask(){
            this.deactivateTask();
            this.status = STATUS_CANCELLED;            
        },
        completeTask(){
            this.deactivateTask();
            this.status = STATUS_COMPLETE;
        },
        initializeTask(){
            // We might be pulling a task from the server, but we might also be creating a new one. 
            // Creation should always feed data to task via props. 
            if(this.actualDuration){
                // Gonna have to parse this, but that's a problem for later.  
                // Tests would be nice to have... ugh. 
                this.totalActiveDuration = parseInt(this.actualDuration);
            }
            if(this.estimatedDuration){
                console.log('load estimate duration');
            }
        }
    },
}
</script>

<style>

</style>