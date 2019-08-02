package my.examples.todolist.util;

public enum PriorityType {
    HIGH, MEDIUM, LOW;

    @Override
    public String toString() {
        switch (this) {
            case HIGH:
                return "HIGH";
            case MEDIUM:
                return "MEDIUM";
            case LOW:
                return "LOW";
            default:
                return "LOW";
        }
    }
}
