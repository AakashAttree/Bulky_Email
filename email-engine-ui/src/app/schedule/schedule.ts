export class Schedule{
    id!: number;
    emailId !: number;
    jobName!:string;
    jobGroupName!:string;
    mode!:string;
    dayOfWeek!:string | null;
    dayOfMonth!:string | null;
    month!:string | null;
    date!:string | null;
    hour!:string;
    minute!:string;
    second!:string;
    startDate!:Date | null;
    endDate!:Date | null;
    lastExecutionTime!:Date | null;
    nextExecutionTime!:Date | null;
}