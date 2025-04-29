public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        Task task1 = new Task("Task 1", "Description 1", 0, TaskStatus.NEW);
        Task task2 = new Task("Task 2", "Description 2", 0, TaskStatus.NEW);

        Epic epic1 = new Epic("Epic 1", "Description 1", 0, TaskStatus.NEW);
        Epic epic2 = new Epic("Epic 2", "Description 2", 0, TaskStatus.NEW);

        manager.createTask(task1);
        manager.createTask(task2);

        manager.createEpic(epic1);
        manager.createEpic(epic2);

        Subtask subtask1 = new Subtask("Subtask 1", "Description 1", 0, TaskStatus.NEW, epic1.getId());
        Subtask subtask2 = new Subtask("Subtask 2", "Description 2", 0, TaskStatus.NEW, epic1.getId());
        Subtask subtask3 = new Subtask("Subtask 3", "Description 3", 0, TaskStatus.NEW, epic2.getId());

        manager.createSubtask(subtask1);
        manager.createSubtask(subtask2);
        manager.createSubtask(subtask3);

        System.out.println("Tasks: " + manager.getAllTasks());
        System.out.println("Epics: " + manager.getAllEpics());
        System.out.println("Subtasks: " + manager.getAllSubtasks());

        subtask1.setStatus(TaskStatus.DONE);
        manager.updateSubtask(subtask1);

        subtask2.setStatus(TaskStatus.IN_PROGRESS);
        manager.updateSubtask(subtask2);

        subtask3.setStatus(TaskStatus.DONE);
        manager.updateSubtask(subtask3);

        System.out.println("Tasks after update: " + manager.getAllTasks());
        System.out.println("Epics after update: " + manager.getAllEpics());
        System.out.println("Subtasks after update: " + manager.getAllSubtasks());

        manager.deleteTaskById(task1.getId());
        manager.deleteEpicById(epic1.getId());

        System.out.println("Tasks after delete: " + manager.getAllTasks());
        System.out.println("Epics after delete: " + manager.getAllEpics());
    }
}
