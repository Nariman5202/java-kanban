import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TaskManager {
    private Map<Integer, Task> tasks = new HashMap<>();
    private Map<Integer, Subtask> subtasks = new HashMap<>();
    private Map<Integer, Epic> epics = new HashMap<>();
    private int currentId = 1;

    public int generateId() {
        return currentId++;
    }

    public void createTask(Task task) {
        task.id = generateId();
        tasks.put(task.getId(), task);
    }

    public void createSubtask(Subtask subtask) {
        subtask.id = generateId();
        subtasks.put(subtask.getId(), subtask);
        Epic epic = epics.get(subtask.getEpicId());
        if (epic != null) {
            epic.addSubtask(subtask);
        }
    }

    public void createEpic(Epic epic) {
        epic.id = generateId();
        epics.put(epic.getId(), epic);
    }

    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public void updateSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
        Epic epic = epics.get(subtask.getEpicId());
        if (epic != null) {
            epic.updateStatus();
        }
    }

    public void updateEpic(Epic epic) {
        epics.put(epic.getId(), epic);
    }

    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    public Subtask getSubtaskById(int id) {
        return subtasks.get(id);
    }

    public Epic getEpicById(int id) {
        return epics.get(id);
    }

    public List<Task> getAllTasks() {
        return tasks.values().stream().collect(Collectors.toList());
    }

    public List<Subtask> getAllSubtasks() {
        return subtasks.values().stream().collect(Collectors.toList());
    }

    public List<Epic> getAllEpics() {
        return epics.values().stream().collect(Collectors.toList());
    }

    public List<Subtask> getSubtasksByEpicId(int epicId) {
        return subtasks.values().stream()
                .filter(subtask -> subtask.getEpicId() == epicId)
                .collect(Collectors.toList());
    }

    public void deleteTaskById(int id) {
        tasks.remove(id);
    }

    public void deleteSubtaskById(int id) {
        Subtask subtask = subtasks.remove(id);
        if (subtask != null) {
            Epic epic = epics.get(subtask.getEpicId());
            if (epic != null) {
                epic.updateStatus();
            }
        }
    }

    public void deleteEpicById(int id) {
        epics.remove(id);
    }

    public void deleteAllTasks() {
        tasks.clear();
    }

    public void deleteAllSubtasks() {
        subtasks.clear();
    }

    public void deleteAllEpics() {
        epics.clear();
    }
}
