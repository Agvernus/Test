package myExceptions;

public class InconsequenceException extends Exception {
    @Override
    public String getMessage() {
        return "Каждое следующее число одного подмножества не может быть меньше предыдущего. Исправьте ошибку и попробуйте снова.";
    }
}
