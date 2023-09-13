/*
All time values are milliseconds. 
*/
const MILLISECONDS_PER_SECOND = 1000; 
const MILLISECONDS_PER_MINUTE = 60000;
const MILLISECONDS_PER_HOUR = 36000000;
const MILLISECONDS_PER_DAY = 36000000 * 24;
function pluralizer(count, unit){
    let outputString = "";
    if (count > 1){
        outputString += count + ' ' + unit + 's';
    } else if (count > 0) {
        outputString += count + ' ' + unit;
    }
    return outputString; 
}

export default{
    convertMillisecondsToDurationString(ms){        
        let runningTotal = ms; 
        let weeks = 0;
        let days = 0; 
        let hours = 0; 
        let minutes = 0; 
        let seconds = 0;
        let milliseconds = 0;
        let durationString = "";
        if(runningTotal / (MILLISECONDS_PER_DAY * 7) >= 1){
            // integer divide, then take the remainder.            
            weeks = Math.trunc(runningTotal / (MILLISECONDS_PER_DAY * 7));
            runningTotal -= weeks * MILLISECONDS_PER_DAY * 7;
            durationString += pluralizer(weeks, 'week'); 
        }
        if(runningTotal / MILLISECONDS_PER_DAY >= 1){
            days = Math.trunc(runningTotal / MILLISECONDS_PER_DAY); 
            runningTotal -= days * MILLISECONDS_PER_DAY;
            if (weeks >= 1){
                durationString += ', ' + pluralizer(days, 'day');
            } else {
                durationString += pluralizer(days, 'day');
            }            
        }
        if(runningTotal / MILLISECONDS_PER_HOUR >= 1){
            hours = Math.trunc(runningTotal / MILLISECONDS_PER_HOUR);
            runningTotal -= hours * MILLISECONDS_PER_HOUR; 
            if (days >= 1 || weeks >= 1){
                durationString += ', ' + pluralizer(hours, 'hour'); 
            } else {
                durationString += pluralizer(hours, 'hour');
            }            
        }
        if(runningTotal / MILLISECONDS_PER_MINUTE >= 1){
            minutes = Math.trunc(runningTotal / MILLISECONDS_PER_MINUTE);
            runningTotal -= minutes * MILLISECONDS_PER_MINUTE;
            if(days >= 1 || weeks >= 1 || hours >= 1){
                durationString += ', ' + pluralizer(minutes, 'minute');
            } else {
                durationString += pluralizer(minutes, 'minute');
            }
        }
        if(runningTotal / MILLISECONDS_PER_SECOND >= 1){
            seconds = Math.trunc(runningTotal / MILLISECONDS_PER_SECOND);
            runningTotal -= seconds * MILLISECONDS_PER_SECOND; 
            if (days >= 1 || weeks >= 1 || hours >= 1 || minutes >= 1){
                durationString += ', ' + pluralizer(seconds, 'second');
            } else {
                durationString += pluralizer(seconds, 'second');
            }
        }
        if(runningTotal > 0){
            milliseconds = runningTotal; 
            if(days >= 1 || weeks >= 1 || hours >= 1 || minutes >= 1 || seconds >= 1){
                durationString += ', ' + pluralizer(milliseconds, 'millisecond');
            } else {
                durationString += pluralizer(milliseconds, 'millisecond');
            }
        }
        return durationString;        
    },
    sumTimeValuesToMilliseconds(minutes, hours, days, weeks, months, years){
        let ms = 0; 
        if(minutes){
            ms += MILLISECONDS_PER_MINUTE * minutes; 
        }
        if(hours){
            ms += MILLISECONDS_PER_HOUR * hours;
        }
        if(days){
            ms += MILLISECONDS_PER_DAY * days;
        }
        if(weeks){
            ms += MILLISECONDS_PER_DAY * 7 * weeks;
        }
        if (months){
            // yes, this will technically not work
            ms += MILLISECONDS_PER_DAY * 30 * months;
        }
        if(years){
            ms += MILLISECONDS_PER_DAY * 365 + years;
        }
        return ms;
    },
    getLocaleDateString(ms){
        const d = new Date(ms);
        return d.toLocaleDateString();
    }

}