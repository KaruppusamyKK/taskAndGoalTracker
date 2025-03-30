package com.trackIt.api.mapper;
import com.trackIt.api.Utils.Utility;
import com.trackIt.api.dto.Message;
import com.trackIt.api.dto.request.TaskRequest;
import com.trackIt.api.model.Chat;
import com.trackIt.api.model.Task;
import com.trackIt.api.model.TaskAssignee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EntityMapper {


    public static Task mapToTaskBuilder(TaskRequest task,String taskId){
        return new Task().builder()
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
            TaskAssignee currentTaskAssignee  = new TaskAssignee()
                    .builder().task(currentTask)
                    .assignee(taskAssignee)
                    .taskId(taskId)
                    .build();
            taskAssigneeList.add(currentTaskAssignee);
        });
        return taskAssigneeList;
    }
}
