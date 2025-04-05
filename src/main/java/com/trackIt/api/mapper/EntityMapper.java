package com.trackIt.api.mapper;
import com.trackIt.api.Utils.Utility;
import com.trackIt.api.dto.Message;
import com.trackIt.api.dto.request.AssigneeRequest;
import com.trackIt.api.dto.request.TaskRequest;
import com.trackIt.api.model.Chat;
import com.trackIt.api.model.Notification;
import com.trackIt.api.model.Task;
import com.trackIt.api.model.TaskAssignee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EntityMapper {


    public static Task mapToTaskBuilder(TaskRequest task,String taskId){
        new Task();
        return Task.builder()
                .taskId(taskId)
                .description(task.description())
                .taskName(task.taskName())
                .priority(task.priority())
                .assigner(task.assigner())
                .status(task.status())
                .startDate(task.startDate())
                .dueDate(task.dueDate())
                .timeTracked(task.timeTracked())
                .timeEstimate(task.timeEstimate())
                .build();
    }

    public static void updateNonNullTaskFields(Task existingTask, Task taskRequest) {
        Optional.ofNullable(taskRequest.getDescription()).ifPresent(existingTask::setDescription);
        Optional.ofNullable(taskRequest.getTaskName()).ifPresent(existingTask::setTaskName);
        Optional.ofNullable(taskRequest.getPriority()).ifPresent(existingTask::setPriority);
        Optional.ofNullable(taskRequest.getAssigner()).ifPresent(existingTask::setAssigner);
        Optional.ofNullable(taskRequest.getStatus()).ifPresent(existingTask::setStatus);
        Optional.ofNullable(taskRequest.getTimeTracked()).ifPresent(existingTask::setTimeTracked);
        Optional.ofNullable(taskRequest.getTimeEstimate()).ifPresent(existingTask::setTimeEstimate);
        Optional.ofNullable(taskRequest.getStartDate()).ifPresent(existingTask::setStartDate);
        Optional.ofNullable(taskRequest.getDueDate()).ifPresent(existingTask::setDueDate);
    }

    public static Chat mapToChatBuilder(Message request,Task task) {
        return Chat.builder()
                .sender(request.sender())
                .content(request.content())
                .taskId(request.taskId())
                .chatId(Utility.generateRandomString())
                .task(task)
                .build();
    }


    public static List<TaskAssignee> mapToTaskAssigneeBuilder(TaskRequest request, Task currentTask,String taskId) {
        List<TaskAssignee> taskAssigneeList = new ArrayList<>();
        request.assignee().forEach(taskAssignee -> {
            new TaskAssignee();
            TaskAssignee currentTaskAssignee  = TaskAssignee
                    .builder().task(currentTask)
                    .assignee(taskAssignee)
                    .taskId(taskId)
                    .assigner(request.assigner())
                    .build();
            taskAssigneeList.add(currentTaskAssignee);
        });
        return taskAssigneeList;
    }

    public static List<Notification> mapNotificationEntity(AssigneeRequest notificationRequest, String message){
        List<Notification> notificationsList = new ArrayList<>();
        notificationRequest.assigneeList().forEach(list->{
            Notification notification = Notification.builder()
                    .taskName(notificationRequest.taskName())
                    .description(notificationRequest.description())
                    .Message(message)
                    .sender(notificationRequest.assigner())
                    .notificationReceiver(list)
                    .notificationId(Utility.generateRandomString())
                    .build();
            notificationsList.add(notification);
        });
        return notificationsList;
    }

    public static List<TaskAssignee> mapNotificationToAssigneeEntity(AssigneeRequest notificationRequest,Task task) {
        List<TaskAssignee> taskAssigneeList = new ArrayList<>();
        notificationRequest.assigneeList().forEach(assigneeList->{
             TaskAssignee assignee = new TaskAssignee();
            TaskAssignee.builder()
                    .assignee(assigneeList)
                    .assigner(notificationRequest.assigner())
                    .taskId(task.getTaskId())
                    .task(task)
                    .build();
            taskAssigneeList.add(assignee);
        });
        return taskAssigneeList;
    }
}
