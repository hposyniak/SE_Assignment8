import java.util.LinkedList;


public class Category {

    private String name;
    private LinkedList questions = new LinkedList();

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addQuestion(int i) {

        questions.addLast(getName() + " Question " + i);

    }
    public void removeQuestion(){

        System.out.println(questions.removeFirst());

    }
}